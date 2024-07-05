package com.example.planificationtaches;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class HomeController {

    @FXML
    private Button Connexionbutton;

    @FXML
    private Button EmployeeButton;

    @FXML
    private Button ProjetButton;

    @FXML
    private Button TacheButton;

    private Stage stage;

    @FXML
    private VBox mainContent;

    @FXML
    private void handleConnexion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ConnexionEmployé.fxml"));
            Parent connexionPage = loader.load();
            ConnexionEmployéController controller = loader.getController();
            mainContent.getChildren().clear();
            mainContent.getChildren().add(connexionPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleEmployeeGestion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionEmployé.fxml"));
            Parent GestionEmployeePage = loader.load();
            GestionEmployéController controller = loader.getController();
            mainContent.getChildren().clear();
            mainContent.getChildren().add(GestionEmployeePage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleProjectGestion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionProjet.fxml"));
            Parent GestionProjetPage = loader.load();
            GestionProjetController controller = loader.getController();
            mainContent.getChildren().clear();
            mainContent.getChildren().add(GestionProjetPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleTaskGestion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionTache.fxml"));
            Parent gestionTachePage = loader.load();
            GestionTacheController controller=loader.getController();
            mainContent.getChildren().clear();
            mainContent.getChildren().add(gestionTachePage);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void showInformation(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
