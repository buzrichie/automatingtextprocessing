package com.example.automatingtextprocessing.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
    // Matches all sequence of patternStr in the provide Text
    public static List<String> findMatches(String patternStr, String text) {
        List<String> matches = new ArrayList<>();

        try {
            Pattern pattern = Pattern.compile(patternStr);
            Matcher matcher = pattern.matcher(text);
//            boolean b = matcher.find(); // To match the first occurrence of a pattern.
            while (matcher.find()) {
                matches.add(matcher.group());
            }

        } catch (Exception e) {
            System.err.println("Invalid regex pattern: " + e.getMessage());
        }

        return matches;
    }

//  Returns all the processed Data out of the original text
    public static String replaceMatches(String patternStr, String replacement, String text) {
        try {
            Pattern pattern = Pattern.compile(patternStr);
            Matcher matcher = pattern.matcher(text);
            return matcher.replaceAll(replacement);
        } catch (Exception e) {
            System.err.println("Invalid regex pattern: " + e.getMessage());
            return text;
        }
    }
}
