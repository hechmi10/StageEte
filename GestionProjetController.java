package com.example.planificationtaches;

import com.dlsc.formsfx.model.structure.DateField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import java.time.LocalDate;

import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class GestionProjetController implements Initializable {

    @FXML
    private TableView<Project> projectTable;

    @FXML
    private TableColumn<Project, Integer> idColumn;

    @FXML
    private TableColumn<Project,String> nomProjetColumn;

    @FXML
    private TableColumn<Project,Date> dateDebutColumn;

    @FXML
    private TableColumn<Project,Date> dateFinColumn;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField idField;

    @FXML
    private TextField nomProjetField;

    @FXML
    private DatePicker dateDebutField;

    @FXML
    private DatePicker dateFinField;

    @FXML
    private TextField updateNomProjetField;

    @FXML
    private DatePicker nouveauDateDebutField;

    @FXML
    private DatePicker nouveauDateFinField;

    @FXML
    private Button addProjectButton;

    @FXML
    private Button updateProjectButton;

    @FXML
    private Button deleteProjectButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("projetId"));
        nomProjetColumn.setCellValueFactory(new PropertyValueFactory<>("nomProjet"));
        dateDebutColumn.setCellValueFactory(new PropertyValueFactory<>("debut"));
        dateFinColumn.setCellValueFactory(new PropertyValueFactory<>("fin"));

        anchorPane.setPadding(new Insets(20));
        loadProjects();
    }

    private void loadProjects() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taskplanner", "root", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM projets")) {

            ObservableList<Project> Projects = FXCollections.observableArrayList();

            while (rs.next()) {
                int id = Integer.parseInt(String.valueOf(rs.getInt("projectId")));
                String nom = rs.getString("nomProjet");
                Date debut = Date.valueOf(rs.getString("dateDebut"));
                Date fin = Date.valueOf(rs.getString("dateFin"));


                Projects.add(new Project(id, nom, debut,fin));
            }

            projectTable.setItems(Projects);

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL errors as needed
        }
    }

    @FXML
    public void handleAddProjectButton(ActionEvent actionEvent) {
        int id= Integer.parseInt(idField.getText());
        String nom=nomProjetField.getText();
        Date debut= convertToDate(dateDebutField);
        Date fin=convertToDate(dateFinField);
        Project newProjet=new Project(id,nom,debut,fin);
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taskplanner", "root", "");
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO projets (projectId, nomProjet, dateDebut, dateFin) VALUES (?, ?, ?, ?)")) {

            stmt.setInt(1, newProjet.getProjetId());
            stmt.setString(2, newProjet.getNomProjet());
            stmt.setDate(3, newProjet.getDebut());
            stmt.setDate(4, newProjet.getFin());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 1) {
                projectTable.getItems().add(newProjet);
                clearFields();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        idField.clear();
        nomProjetField.clear();
        dateDebutField.getEditor().clear();
        dateFinField.getEditor().clear();
    }

    @FXML
    public void handleUpdateProjectButton(ActionEvent actionEvent) {
        Project selectedProject=projectTable.getSelectionModel().getSelectedItem();
        if(selectedProject==null){
            showWarning("Projet non selectionné");
        }else{
            try(Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/taskplanner","root","");
            PreparedStatement stmt=conn.prepareStatement("UPDATE projets SET dateDebut=?, dateFin=? WHERE nomProjet=? ")){
                stmt.setDate(1,convertToDate(nouveauDateDebutField));
                stmt.setDate(2,convertToDate(nouveauDateFinField));
                stmt.setString(3,selectedProject.getNomProjet());
                int rowsUpdated=stmt.executeUpdate();
                if(rowsUpdated>0){
                    selectedProject.setDebut(convertToDate(nouveauDateDebutField));
                    selectedProject.setFin(convertToDate(nouveauDateFinField));
                    projectTable.refresh();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    public void handleDeleteProjectButton(ActionEvent actionEvent) {
        Project selectedProject=projectTable.getSelectionModel().getSelectedItem();
        if(selectedProject==null){
            showWarning("Projet non selectionné");
        }else{
            try(Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/taskplanner","root","")){
                PreparedStatement stmt=conn.prepareStatement("Delete From projets where projectId=?");
                    stmt.setInt(1,selectedProject.getProjetId());
                    int rowsDeleted=stmt.executeUpdate();
                    if(rowsDeleted>0){

                        projectTable.getItems().remove(selectedProject);
                        projectTable.refresh();
                    }
            }catch(SQLException e){
                throw new RuntimeException(e);
            }
        }
    }

    private java.sql.Date convertToDate(DatePicker datePicker) {
        LocalDate localDate = datePicker.getValue();
        if (localDate != null) {
            return java.sql.Date.valueOf(localDate);
        }
        return null;
    }
    public void showInformation(String message){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
    public void showWarning(String message){
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Avertissement");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
    public void showError(String message){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}
