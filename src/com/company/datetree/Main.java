package com.company.datetree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        dateTree("/home/gerard/datetree/directori");
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
}