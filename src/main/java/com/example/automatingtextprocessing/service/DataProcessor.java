package com.example.automatingtextprocessing.service;


import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataProcessor {

    private static final Pattern WORD_PATTERN = Pattern.compile("\\b\\w+\\b");

    // Calculates word frequency from a stream of lines.
    public static Map<String, Long> calculateWordFrequency(Stream<String> lines) {
        return lines
                .flatMap(line -> WORD_PATTERN.matcher(line).results()
                        .map(match -> match.group().toLowerCase()))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    // Summarizes the text: word count, average word length, total lines.
    public static List<String> summarizeText(Stream<String> lines) {
        List<String> allWords = lines
                .flatMap(line -> WORD_PATTERN.matcher(line).results()
                        .map(match -> match.group()))
                .toList();

        long totalWords = allWords.size();
        double averageLength = allWords.stream()
                .mapToInt(String::length)
                .average()
                .orElse(0.0);

        return List.of(
                "Total Words: " + totalWords,
                "Average Word Length: " + String.format("%.2f", averageLength)
        );
    }

    // Filters lines matching a regex pattern.
    public static List<String> findLinesMatching(Stream<String> lines, String pattern) {
        Pattern regex = Pattern.compile(pattern);
        return lines
                .filter(line -> regex.matcher(line).find())
                .collect(Collectors.toList());
    }
}
