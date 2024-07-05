package com.example.planificationtaches;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.*;
import java.util.Random;
import java.util.ResourceBundle;

public class GestionEmployéController implements Initializable{
    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private TableColumn<Employee, String> cinColumn;

    @FXML
    private TableColumn<Employee, String> nomColumn;

    @FXML
    private TableColumn<Employee, String> prenomColumn;

    @FXML
    private TableColumn<Employee, String> adresseColumn;

    @FXML
    private TableColumn<Employee, String> emailColumn;

    @FXML
    private TableColumn<Employee, String> motDePasseColumn;

    @FXML
    private TableColumn<Employee, String> numTelColumn;

    @FXML
    private TextField cinField;

    @FXML
    private GridPane gridPane;

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField adresseField;

    @FXML
    private TextField numTelField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField updateEmailField;

    @FXML
    private TextField motDePasseField;

    @FXML
    private TextField nouveauMotDePasseField;

    @FXML
    private Button addEmployeeButton;

    @FXML
    private Button updateEmployeeButton;

    @FXML
    private Button deleteEmployeeButton;

    @FXML
    private AnchorPane anchorPane;

    // Initialize method to setup the controller
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize table columns
        cinColumn.setCellValueFactory(new PropertyValueFactory<>("cin"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        motDePasseColumn.setCellValueFactory(new PropertyValueFactory<>("motDePasse"));
        numTelColumn.setCellValueFactory(new PropertyValueFactory<>("numtel"));
        anchorPane.setPadding(new Insets(20));
        loadEmployees();
    }

    // Method to load employees from database
    private void loadEmployees() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taskplanner", "root", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM employees")) {

            ObservableList<Employee> employees = FXCollections.observableArrayList();

            while (rs.next()) {
                String cin = String.valueOf(rs.getInt("cin"));
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String adresse = rs.getString("adresse");
                String email = rs.getString("email");
                String mdp=rs.getString("motDePasse");
                String numtel=rs.getString("numTel");

                employees.add(new Employee(cin, nom, prenom, adresse, email,mdp,numtel));
            }

            employeeTable.setItems(employees);

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL errors as needed
        }
    }

    // Method to handle button click event to add an employee
    @FXML
    private void handleAddEmployeeButton(ActionEvent event) {
        String cin = cinField.getText();
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String adresse = adresseField.getText();
        String email = emailField.getText();

        // Generate a random password (8 characters)
        String password = generateRandomPassword(8);
        String numtel=numTelField.getText();

        Employee newEmployee = new Employee(cin, nom, prenom, adresse, email, password,numtel);

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taskplanner", "root", "");
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO employees (cin, nom, prenom, adresse, email, motDePasse,numTel) VALUES (?, ?, ?, ?, ?, ?, ?)")) {

            stmt.setString(1, newEmployee.getCin());
            stmt.setString(2, newEmployee.getNom());
            stmt.setString(3, newEmployee.getPrenom());
            stmt.setString(4, newEmployee.getAdresse());
            stmt.setString(5, newEmployee.getEmail());
            stmt.setString(6, newEmployee.getMotDePasse());
            stmt.setString(7,newEmployee.getNumtel());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 1) {
                employeeTable.getItems().add(newEmployee);
                clearFields();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to generate a random password of specified length
    private String generateRandomPassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
        Random random = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }

        return password.toString();
    }

    // Method to clear input fields after adding an employee
    private void clearFields() {
        cinField.clear();
        nomField.clear();
        prenomField.clear();
        adresseField.clear();
        emailField.clear();
        numTelField.clear();
    }

    // Method to handle button click event to update an employee
    @FXML
    private void handleUpdateEmployeeButton(ActionEvent event) {
        Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
        if (selectedEmployee == null) {
            showAlert("Employé non selectionné");
        } else {
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taskplanner", "root", "");
                 PreparedStatement stmt = conn.prepareStatement("UPDATE employees SET motDePasse=? WHERE email=?")) {
                stmt.setString(1, nouveauMotDePasseField.getText());
                stmt.setString(2, selectedEmployee.getEmail());
                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated > 0) {
                    selectedEmployee.setMotDePasse(nouveauMotDePasseField.getText());
                    employeeTable.refresh();
                } else {
                    showAlert("Échec de la mise à jour");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    private void handleDeleteEmployeeButton(ActionEvent event) {
        Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
        if (selectedEmployee == null) {
            showAlert( "Employé non selectionné.");
            return;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/taskplanner", "root", "")) {
            String sql = "DELETE FROM employees WHERE Cin = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, selectedEmployee.getCin());

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                employeeTable.getItems().remove(selectedEmployee); // Remove from TableView
            } else {
                showAlert( "Echec de suppression");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Erreur de la base de données");
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
