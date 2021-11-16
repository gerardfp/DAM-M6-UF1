package com.company.flatten;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        flatten("/home/gerard/flatten/niats");
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