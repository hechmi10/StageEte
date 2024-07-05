package com.example.planificationtaches;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnexionEmployéController {

    @FXML
    private VBox mainContent;
    @FXML
    private AnchorPane anchorPane;

    private Connection connection;

    @FXML
    private TextField loginEmailField;

    @FXML
    private PasswordField loginPasswordField;

    @FXML
    private Label loginErrorLabel;


    public ConnexionEmployéController() {
        // Initialize database connection
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/taskplanner", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database connection error
        }
    }

    @FXML
    public void initialize() {
        anchorPane.setPadding(new Insets(20));
    }

    public Employee loginEmployee(String email, String password) {
        String query = "SELECT * FROM employees WHERE email = ? AND motDePasse = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String cin = resultSet.getString("cin");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String adresse = resultSet.getString("adresse");
                String numtel = resultSet.getString("numtel");
                return new Employee(cin, nom, prenom, adresse, email, password, numtel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Admin loginAdmin(String email, String password) {
        String query = "SELECT * FROM admins WHERE email = ? AND motDePasse = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String cin = resultSet.getString("cin");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String adresse = resultSet.getString("adresse");
                String numtel = resultSet.getString("numtel");
                return new Admin(cin, nom, prenom, adresse, email, password, numtel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @FXML
    public void handleLogin() {
        String email = loginEmailField.getText();
        String password = loginPasswordField.getText();

        Employee employee = loginEmployee(email, password);
        Admin admin=loginAdmin(email,password);

        if(admin !=null){
            loginEmailField.clear();
            loginPasswordField.clear();
            loginErrorLabel.setText("");
            try {
                // Load the FXML file for the dashboard
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfileAdmin.fxml"));
                Parent root = loader.load();

                // Get the controller for the dashboard
                AdminController controller = loader.getController();
                controller.setAdmin(admin);
                mainContent.getChildren().clear();
                mainContent.getChildren().add(root);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else{
            // Login failed, display error message
            loginErrorLabel.setText("Email ou mot de passe incorrect.");
        }
            if(employee !=null){
            loginEmailField.clear();
            loginPasswordField.clear();
            loginErrorLabel.setText("");
            try {
                // Load the FXML file for the dashboard
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PageProfile.fxml"));
                Parent root = loader.load();

                // Get the controller for the dashboard
                ProfileController controller = loader.getController();
                controller.setEmployee(employee);
                mainContent.getChildren().clear();
                mainContent.getChildren().add(root);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            // Login failed, display error message
            loginErrorLabel.setText("Email ou mot de passe incorrect.");
        }
    }
    public void showInformation(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
