/*
Name -> Ranshiv Kumar
Student id -> 200555490
Purpose -> Assignment 2
Date -> 8- April-2024
 */

package org.example.nasa;

import com.google.gson.JsonElement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javafx.stage.Stage;

public class Controller {

    //    Variables for Tableview and Tablecolumn components present in FXML (To help make connection of Java with FXML)
    @FXML
    private TableView<Star> tableView;
    @FXML
    private TableColumn<Star, String> nameColumn;
    @FXML
    private TableColumn<Star, String> apparentMagnitudeColumn;
    @FXML
    private TableColumn<Star, String> absoluteMagnitudeColumn;
    @FXML
    private TableColumn<Star, String> distanceLightYearColumn;
    @FXML
    private TableColumn<Star, String> spectralClassColumn;

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

//    Initialized Method -> That will connect Java to API and will fetch the data from it

    public void initialize() {
        System.out.println("Controller initialized");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        apparentMagnitudeColumn.setCellValueFactory(new PropertyValueFactory<>("apparentMagnitude"));
        absoluteMagnitudeColumn.setCellValueFactory(new PropertyValueFactory<>("absoluteMagnitude"));
        distanceLightYearColumn.setCellValueFactory(new PropertyValueFactory<>("distanceLightYear"));
        spectralClassColumn.setCellValueFactory(new PropertyValueFactory<>("spectralClass"));

//        Try and Catch block
        try {
            String apiKey = "3zFgutiJYlvLIjJv0r5TgA==NgW7vEftZCNAOoQ4";
            URL url = new URL("https://api.api-ninjas.com/v1/stars?name=Kepler");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("x-api-key", apiKey);
            connection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
//            Accepting response code from the API
            int responseCode = connection.getResponseCode();
//            If response code is equal to 200
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }
//Parsing JSON Response
            JsonArray jsonArray = JsonParser.parseString(response.toString()).getAsJsonArray();
//            For loop to store the parsed data into an array
            for (JsonElement element : jsonArray) {
                JsonObject starObject = element.getAsJsonObject();
                tableView.getItems().add(new Star(
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
    }

    @FXML
    public void openSearchView() throws IOException {
        System.out.println("Search Interface initiated");
        if (primaryStage == null) {
            System.err.println("Primary stage is not set!");
            return;
        }

        // Load the search view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Search.fxml"));
        Parent root = loader.load();

        // Get the controller for the search view
        SearchController searchController = loader.getController();

        // Pass the primary stage to the SearchController
        searchController.setPrimaryStage(primaryStage);

        // Set the primary stage and show the scene
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
