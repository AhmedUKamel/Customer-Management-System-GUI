package com.example.customermanagementsystemgui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class HelloApplication extends Application {
    @Override
    public void start(Stage window) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        window.setTitle("Customer management app");
        window.setScene(scene);
        window.show();
    }
    // Main Method
    public static void main(String[] args) {
        launch();
    }
}