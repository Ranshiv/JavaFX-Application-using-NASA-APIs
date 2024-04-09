package org.example.nasa;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SearchController {

    @FXML
    private Label nameLabel;
    @FXML
    private Label apparentMagnitudeLabel;
    @FXML
    private Label absoluteMagnitudeLabel;
    @FXML
    private Label distanceLightYearLabel;
    @FXML
    private Label spectralClassLabel;
    @FXML
    private TextField searchField;
    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void handleSearchButton() {
        // Fetch data based on search text
        String searchText = searchField.getText();
        List<Star> stars = fetchDataFromAPI(searchText);

        // Display the first search result in labels
        if (!stars.isEmpty()) {
            Star firstStar = stars.get(0);
            nameLabel.setText("Name: " + firstStar.getName());
            apparentMagnitudeLabel.setText("Apparent Magnitude: " + firstStar.getApparentMagnitude());
            absoluteMagnitudeLabel.setText("Absolute Magnitude: " + firstStar.getAbsoluteMagnitude());
            distanceLightYearLabel.setText("Distance Light Year: " + firstStar.getDistanceLightYear());
            spectralClassLabel.setText("Spectral Class: " + firstStar.getSpectralClass());
        } else {
            // If no results found, clear the labels and show an error notification
            clearLabels();
            showErrorNotification("No data found", "No stars found with the given search criteria.");
        }
    }

    private void showErrorNotification(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private List<Star> fetchDataFromAPI(String searchText) {
        List<Star> stars = new ArrayList<>();

        try {
            // Make an API request using the search text
            String apiKey = "3zFgutiJYlvLIjJv0r5TgA==NgW7vEftZCNAOoQ4";
            String apiUrl = "https://api.api-ninjas.com/v1/stars?name=" + searchText;
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("x-api-key", apiKey);

            // Read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // Parse the JSON response
            JsonArray jsonArray = JsonParser.parseString(response.toString()).getAsJsonArray();
            for (JsonElement element : jsonArray) {
                JsonObject starObject = element.getAsJsonObject();
                stars.add(new Star(
                        starObject.get("name").getAsString(),
                        starObject.get("apparent_magnitude").getAsString(),
                        starObject.get("absolute_magnitude").getAsString(),
                        starObject.get("distance_light_year").getAsString(),
                        starObject.get("spectral_class").getAsString()
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stars; // Return an empty list if no data is fetched
    }

    private void clearLabels() {
        nameLabel.setText("");
        apparentMagnitudeLabel.setText("");
        absoluteMagnitudeLabel.setText("");
        distanceLightYearLabel.setText("");
        spectralClassLabel.setText("");
    }

    @FXML
    private void returnToHomeScreen() throws IOException {
        // Load the home screen FXML file
         FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
         Parent root = loader.load();
         Scene scene = new Scene(root);
         primaryStage.setScene(scene);
         primaryStage.show();
    }
}
