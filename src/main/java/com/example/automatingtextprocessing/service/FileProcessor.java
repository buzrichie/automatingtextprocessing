package com.example.automatingtextprocessing.service;


import com.example.automatingtextprocessing.util.RegexUtil;

import java.io.*;


public class FileProcessor {

    // Reads the content of a file into a String.

    public static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath + " - " + e.getMessage());
        }

        return content.toString();
    }

    // Writes a string to a specified file.
    public static void writeFile(String filePath, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(content);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + filePath + " - " + e.getMessage());
        }
    }

    // Applies regex replacement to a single file and writes result to an output file.
    public static void processSingleFile(String inputPath, String outputPath, String pattern, String replacement) {
        String content = readFile(inputPath);
        String updatedContent = RegexUtil.replaceMatches(pattern, replacement, content);
        writeFile(outputPath, updatedContent);
    }

    //Batch processes all .txt files in a directory, writing results to another directory.
    public static void batchProcessDirectory(String inputDirPath, String outputDirPath, String pattern, String replacement) {
        File inputDir = new File(inputDirPath);
        File[] files = inputDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

        if (files == null || files.length == 0) {
            System.out.println("No .txt files found in directory: " + inputDirPath);
            return;
        }

        boolean done = new File(outputDirPath).mkdirs();

        for (File file : files) {
            String inputPath = file.getAbsolutePath();
            String outputPath = outputDirPath + File.separator + file.getName();

            processSingleFile(inputPath, outputPath, pattern, replacement);
            System.out.println("Processed: " + file.getName());
        }
    }
}
