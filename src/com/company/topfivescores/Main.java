package com.company.topfivescores;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Score {
    String name;
    int points;

    public Score(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return String.format("%-20s%5s", name, points);
    }
}

public class Main {

    static String scoreFile = "/tmp/scores";
    static String fieldSep = ":";

    public static void main(String[] args) throws IOException {
        addScore("James Gosling", 300);
        addScore("Anders Hejlsberg", 500);
        addScore("Chris Lattner", 400);
        addScore("Brendan Eich", 200);
        addScore("Bjarne Stroustrup", 600);
        addScore("Guido van Rossum", 100);
    }

    static void addScore(String name, int points) throws IOException {
        List<Score> scores = new ArrayList<>();

        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(scoreFile))){
            for (String line; (line = bufferedReader.readLine()) != null; ){
                String[] fields = line.split(fieldSep);
                scores.add(new Score(fields[0], Integer.parseInt(fields[1])));
            }
        } catch (Exception e) {

        }

        scores.add(new Score(name, points));

        scores = scores.stream()
                .sorted(Comparator.comparing(Score::getPoints).reversed())
                .limit(5)
                .collect(Collectors.toList());

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(scoreFile))){
            for (Score score:scores) {
                bufferedWriter.write(score.name + fieldSep + score.points);
                bufferedWriter.newLine();
            }
        }

        System.out.println("\033[31m** TOP 5 SCORE **\033[0m\n" + scores.stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n")));
    }
}
