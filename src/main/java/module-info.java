module org.example.nasa {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires javafx.media;


    opens org.example.nasa to javafx.fxml;
    exports org.example.nasa;
}