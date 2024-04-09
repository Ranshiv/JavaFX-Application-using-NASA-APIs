package org.example.nasa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;
import java.io.InputStream;
import java.util.Stack;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(loader.load());

        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

        // Set the primaryStage for the controller
        Controller controller = loader.getController();
        controller.setPrimaryStage(primaryStage);

        primaryStage.setMinWidth(850);
        primaryStage.setMinHeight(600);
        primaryStage.setMaxWidth(850);
        primaryStage.setMaxHeight(600);

        // Set title for the stage
        primaryStage.setTitle("NASA Celestial Object Application for Star type G,K,M");

        // Set application icon
        try {
            InputStream iconStream = getClass().getResourceAsStream("/images/nasa.jpg");
            if (iconStream != null) {
                Image icon = new Image(iconStream);
                primaryStage.getIcons().add(icon);
            } else {
                System.out.println("Icon not found");
            }
        } catch (Exception e) {
            System.out.println("Failed to load icon: " + e.getMessage());
        }

        StackPane root = new StackPane();
        primaryStage.setScene(scene);
        primaryStage.show();
        String videoFile = getClass().getResource("/Videos/Black Hole.mp4").toExternalForm();

        videoEmbed(videoFile,primaryStage,root);

    }

    public static void videoEmbed(String videoFile, Stage primaryStage, StackPane root){
        Media media = new Media(new File(videoFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);  // SO that video can autoplay itself (LOOP)
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.fitWidthProperty().bind(primaryStage.widthProperty());
        mediaView.fitHeightProperty().bind(primaryStage.heightProperty());
        root.getChildren().add(mediaView);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
