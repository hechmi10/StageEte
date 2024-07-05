package com.example.planificationtaches;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TaskApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TaskApplication.class.getResource("PageAcceuil.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 920, 740);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setTitle("TaskPlanner");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}