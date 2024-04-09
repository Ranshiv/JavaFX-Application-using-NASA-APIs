package org.example.nasa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.InputStream;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(loader.load());


        // Set the primaryStage for the controller
        Controller controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
//        scene.getStylesheets().add(getClass().getResource("/org/example/nasa/styles.css").toExternalForm());
        controller.openSearchView();
        primaryStage.setMinWidth(950);
        primaryStage.setMinHeight(600);
        primaryStage.setMaxWidth(950);
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

        primaryStage.setResizable(false);
        primaryStage.show();

        System.out.println("Primary Stage is successfully shown");

//
//        try {
//            String videoFile = Objects.requireNonNull(getClass().getResource("/Videos/BlackHole.mp4")).toExternalForm();
//            videoEmbed(videoFile, primaryStage, root);
//        } catch (NullPointerException e) {
//            System.err.println("Failed to load video file: " + e.getMessage());
//        }


//        videoEmbed(videoFile, primaryStage, root);


//        public static void videoEmbed(String videoFile, Stage primaryStage, StackPane root){
//            Media media = new Media(new File(videoFile).toURI().toString());
//            MediaPlayer mediaPlayer = new MediaPlayer(media);
//            mediaPlayer.setAutoPlay(true);  // SO that video can autoplay itself (LOOP)
//            MediaView mediaView = new MediaView(mediaPlayer);
//            mediaView.fitWidthProperty().bind(primaryStage.widthProperty());
//            mediaView.fitHeightProperty().bind(primaryStage.heightProperty());
//            root.getChildren().add(mediaView);
//        }
    }
}
