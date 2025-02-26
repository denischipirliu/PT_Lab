package org.application.pt2024_30421_chipirliu_denis_assignment_3.presentation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * This class represents the Application
 */
public class Application extends javafx.application.Application {
    /**
     * This constructor creates a new application
     */
    public Application() {
    }
    /**
     * This method starts the application
     * @param stage the stage
     * @throws IOException if the file is not found
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method launches the application
     * @param args the arguments
     */
    public static void main(String[] args) {
        launch();
    }
}