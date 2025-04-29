module com.example.automatingtextprocessing {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.automatingtextprocessing to javafx.fxml;
    exports com.example.automatingtextprocessing;
}