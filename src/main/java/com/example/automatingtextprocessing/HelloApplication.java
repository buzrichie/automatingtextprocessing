package com.example.automatingtextprocessing;

import com.example.automatingtextprocessing.service.DataProcessor;
import com.example.automatingtextprocessing.service.DataService;
import com.example.automatingtextprocessing.service.FileProcessor;
import com.example.automatingtextprocessing.util.RegexUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

//        String sentence = "My name is Richmond. I am good";
//        String pattern = "+[a-z.]d$";

//        Eg 1.
//        List<String> matches=RegexUtil.findMatches(pattern, sentence);
//        for(String match : matches){
//            System.out.println("match: "+match);
//        }

//        Eg 2.
//        String result = RegexUtil.replaceMatches(pattern, "sad", sentence);
//        System.out.println(result);

/*       Eg 3: Buffer.
        String inputDir = "src/main/java/com/example/automatingtextprocessing/data/input";
        String outputDir = "src/main/java/com/example/automatingtextprocessing/data/output";
        String pattern = "\\b\\d{3}-\\d{2}-\\d{4}\\b"; // Example: match SSN format
        String replacement = "[REDACTED]";

        FileProcessor.batchProcessDirectory(inputDir, outputDir, pattern, replacement);
*/
//        Eg: 4 Data - Processing with Steam API
       /* String filePath = "src/main/java/com/example/automatingtextprocessing/data/input/dataset1.txt";

        Path path = Paths.get(filePath);
        try (Stream<String> lines = Files.lines(path)) {

            // Word frequency
            Map<String, Long> freq = DataProcessor.calculateWordFrequency(lines);
            System.out.println("Word Frequency:");
            freq.forEach((word, count) -> System.out.println(word + ": " + count));

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Stream<String> lines = Files.lines(path)) {

            // Text summary
            List<String> summary = DataProcessor.summarizeText(lines);
            System.out.println("\nSummary:");
            summary.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Stream<String> lines = Files.lines(path)) {
            // Find lines with email addresses
            String pattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z.-]+\\.[a-zA-Z]{2,}";
            List<String> matches = DataProcessor.findLinesMatching(lines, pattern);

            System.out.println("\nLines with Email Addresses:");
            matches.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }

        */

//        Eg 5
        try {
            DataService<String> manager = new DataService<>();

            manager.addEntry("101", "Line one of file");
            manager.addEntry("102", "Another content line");

            System.out.println("All Entries:");
            manager.getAllEntries().forEach(System.out::println);

            System.out.println("\nUpdating ID 101...");
            manager.updateEntry("101", "Updated line content");

            System.out.println("Entry 101: " + manager.getEntry("101"));

            System.out.println("\nDeleting ID 102...");
            manager.deleteEntry("102");

            System.out.println("All Entries After Deletion:");
            manager.getAllEntries().forEach(System.out::println);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }


        launch();
    }
}