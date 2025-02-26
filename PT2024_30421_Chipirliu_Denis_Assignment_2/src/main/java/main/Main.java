package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("Queue Simulator");
            primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view.fxml"))));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
