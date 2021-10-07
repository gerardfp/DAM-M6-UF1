package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
//        flatten("/home/gerard/flatten/niats");
//        treediff("/home/gerard/treediff/dirA", "/home/gerard/treediff/dirB");
        dateTree("/home/gerard/datetree/directori");
    }

    public static void flatten(String directory) throws IOException {
        Path baseDir = Paths.get(directory);

        for (Path file : Files.walk(baseDir)
                .filter(Files::isRegularFile)
                .collect(Collectors.toList())) {
            Files.move(file, baseDir.resolve(file.getFileName()));
        }

        for (Path dir : Files.walk(baseDir)
                .filter(Files::isDirectory)
                .filter(dir -> !dir.equals(baseDir))
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList())) {
            Files.delete(dir);
        }
    }

    public static void treediff(String directoryA, String directoryB) throws IOException {

        class ComparedFile {
            Path path;
            byte[] hash;
            boolean found;
            boolean samePath;
            List<Path> paths = new ArrayList<>();

            ComparedFile(Path dir, Path file) {
                path = dir.relativize(file);
                try {
                    hash = MessageDigest.getInstance("MD5").digest(Files.readAllBytes(file));
                } catch (NoSuchAlgorithmException | IOException e) {
                    e.printStackTrace();
                }
            }

            void findInList(List<ComparedFile> list) {
                for (ComparedFile file : list) {
                    if (Arrays.equals(hash, file.hash)) {
                        found = true;
                        if (path.equals(file.path)) {
                            samePath = true;
                        } else {
                            paths.add(file.path);
                        }
                    }
                }
            }

            @Override
            public String toString() {
                return path.toString() + (paths.isEmpty() ? "" : " -> " + Arrays.toString(paths.toArray()));
            }
        }

        Path dirA = Paths.get(directoryA);
        Path dirB = Paths.get(directoryB);

        List<ComparedFile> filesA = Files.walk(dirA)
                .filter(Files::isRegularFile)
                .map(file -> new ComparedFile(dirA, file))
                .collect(Collectors.toList());

        List<ComparedFile> filesB = Files.walk(dirB)
                .filter(Files::isRegularFile)
                .map(file -> new ComparedFile(dirB, file))
                .collect(Collectors.toList());

        List<ComparedFile> filesNotInB = new ArrayList<>();
        List<ComparedFile> filesOtherPathInB = new ArrayList<>();
        List<ComparedFile> filesNotInA = new ArrayList<>();
        List<ComparedFile> filesOtherPathInA = new ArrayList<>();
        List<ComparedFile> filesInAandB = new ArrayList<>();

        for (ComparedFile fileA : filesA) {
            fileA.findInList(filesB);
            if (!fileA.found) filesNotInB.add(fileA);
            if (fileA.samePath) filesInAandB.add(fileA);
            if (!fileA.paths.isEmpty()) filesOtherPathInB.add(fileA);
        }

        for (ComparedFile fileB : filesB) {
            fileB.findInList(filesA);
            if (!fileB.found) filesNotInA.add(fileB);
            if (!fileB.paths.isEmpty()) filesOtherPathInA.add(fileB);
        }

        System.out.println("\033[34mFitxers del DirectoriA que no estan al DirectoriB\033[0m");
        filesNotInB.forEach(System.out::println);
        System.out.println("\033[34mFitxers del DirectoriA que estan al DirectoriB però en una altra ruta\033[0m");
        filesOtherPathInB.forEach(System.out::println);
        System.out.println("\033[34mFitxers del DirectoriB que no estan al DirectoriA\033[0m");
        filesNotInA.forEach(System.out::println);
        System.out.println("\033[34mFitxers del DirectoriB que estan al DirectoriA però en una altra ruta\033[0m");
        filesOtherPathInA.forEach(System.out::println);
        System.out.println("\033[34mFitxers que estan a la mateixa ruta al DirectoriA i al DirectoriB\033[0m");
        filesInAandB.forEach(System.out::println);
    }

    public static void dateTree(String directory) throws IOException {
        flatten(directory);

        Path dir = Paths.get(directory);

        for (Path file : Files.walk(dir).filter(Files::isRegularFile).collect(Collectors.toList())) {
            LocalDateTime time = LocalDateTime.parse(Files.getLastModifiedTime(file).toString(), DateTimeFormatter.ISO_DATE_TIME);

            Path datePath = dir.resolve(time.getYear() + "/" + time.getMonthValue() + "/" + time.getDayOfMonth());
            Files.createDirectories(datePath);
            Files.move(file, datePath.resolve(file.getFileName()));
        }
    }
}

/*

a b c

x a b

 */