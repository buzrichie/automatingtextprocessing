package com.example.automatingtextprocessing;

import com.example.automatingtextprocessing.service.DataProcessor;
import com.example.automatingtextprocessing.util.RegexUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class DashboardController {

    @FXML private TextArea inputTextArea;
    @FXML private TextField regexField;
    @FXML private TextField replacementField;
    @FXML private TextArea resultArea;
    @FXML private Label fileNameLabel;

    private File selectedFile;


    @FXML
    public void handleChooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Text File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                String content = Files.readString(selectedFile.toPath());
                inputTextArea.setText(content);
                fileNameLabel.setText("Loaded: " + selectedFile.getName());
            } catch (Exception e) {
                showError("Error loading file: " + e.getMessage());
            }
        }
    }

    @FXML
    private void handleUnselectFile() {
        selectedFile = null;
        fileNameLabel.setText("No file selected");
        inputTextArea.clear();
    }

    @FXML
    public void handleFindMatches() {
        String pattern = regexField.getText();
        String text = inputTextArea.getText();

        try {
            List<String> matches = RegexUtil.findMatches(pattern, text);
            resultArea.setText(String.join("\n", matches));
        } catch (Exception e) {
            showError("Regex error: " + e.getMessage());
        }
    }
    @FXML
    public void handleReplaceText() {
        String pattern = regexField.getText();
        String replacement = replacementField.getText();
        String text = inputTextArea.getText();

        try {
            String updated = RegexUtil.replaceMatches(pattern, replacement, text);
            inputTextArea.setText(updated);
            resultArea.setText("Text replaced successfully.\n" + updated);
        } catch (Exception e) {
            showError("Regex error: " + e.getMessage());
        }
    }
    @FXML
    public void handleSaveFile() {
        if (selectedFile != null) {
            try {
                Files.writeString(selectedFile.toPath(), inputTextArea.getText());
                resultArea.setText("File saved: " + selectedFile.getName());
            } catch (Exception e) {
                showError("Error saving file: " + e.getMessage());
            }
        } else {
            showError("No file selected to save.");
        }
    }

    @FXML
    private void handleWordFrequency() {

        if (inputTextArea.getText().isEmpty()){
            resultArea.setText("Text Area is Empty");
            return;
        }
        Stream<String> lines = inputTextArea.getText().lines();
        Map<String, Long> frequency = DataProcessor.calculateWordFrequency(lines);
        StringBuilder result = new StringBuilder("Word Frequency:\n");
        frequency.forEach((word, count) -> result.append(word).append(": ").append(count).append("\n"));
        resultArea.setText(result.toString());
    }

    @FXML
    private void handleSummarizeText() {
        Stream<String> lines = inputTextArea.getText().lines();
        List<String> summary = DataProcessor.summarizeText(lines);
        resultArea.setText(String.join("\n", summary));
    }

    @FXML
    private void handleFindMatchingLines() {
        String pattern = regexField.getText();
        if (pattern == null || pattern.isBlank()) {
            resultArea.setText("Please enter a valid regex pattern.");
            return;
        }
        Stream<String> lines = inputTextArea.getText().lines();
        List<String> matchingLines = DataProcessor.findLinesMatching(lines, pattern);
        resultArea.setText("Matching Lines:\n" + String.join("\n", matchingLines));
    }


    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
    }
}


