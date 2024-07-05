package com.example.planificationtaches;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class GestionTacheController implements Initializable {


    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView<Task> taskTable;

    @FXML
    private TableColumn<Task,String> nomColumn;

    @FXML
    private TableColumn<Task, Date> dateDebutColumn;

    @FXML
    private TableColumn<Task,Date> dateFinColumn;

    @FXML
    private TableColumn<Task,Integer> estimationColumn;

    @FXML
    private TableColumn<Task,Integer> idProjetColumn;

    @FXML
    private TableColumn<Task,String> cinEmployeeColumn;

    @FXML
    private TextField nomTacheField;

    @FXML
    private DatePicker dateDebutField;

    @FXML
    private DatePicker dateFinField;

    @FXML
    private TextField estimationField;

    @FXML
    private TextField idProjetField;

    @FXML
    private TextField cinEmployeeField;

    @FXML
    private Button addTaskButton;

    @FXML
    private Button updateTaskButton;

    @FXML
    private Button deleteTaskButton;

    @FXML
    private TextField updateNomTacheField;

    @FXML
    private DatePicker nouveauDateDebutField;

    @FXML
    private DatePicker nouveauDateFinField;

    @FXML
    private TextField nouveauEstimationField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nomTache"));
        dateDebutColumn.setCellValueFactory(new PropertyValueFactory<>("debut"));
        dateFinColumn.setCellValueFactory(new PropertyValueFactory<>("fin"));
        estimationColumn.setCellValueFactory(new PropertyValueFactory<>("estimation"));
        idProjetColumn.setCellValueFactory(new PropertyValueFactory<>("tacheProjetId"));
        cinEmployeeColumn.setCellValueFactory(new PropertyValueFactory<>("cinEmployé"));
        anchorPane.setPadding(new Insets(20));
        loadTasks();
    }

    public void loadTasks(){
        try(Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/taskplanner","root","");
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery("select * from taches");){
            ObservableList<Task> Tasks= FXCollections.observableArrayList();
            while(rs.next()){
                String nom=rs.getString("nomTache");
                Date debut=Date.valueOf(rs.getString("dateDebut"));
                Date fin=Date.valueOf(rs.getString("dateFin"));
                int estimation=Integer.parseInt(String.valueOf(rs.getInt("estimation")));
                int idProjet=rs.getInt("taskProjectId");
                String cinEmployé=rs.getString("cinEmployé");
                Tasks.add(new Task(nom,debut,fin,estimation,idProjet,cinEmployé));
            }
            taskTable.setItems(Tasks);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private java.sql.Date convertToDate(DatePicker datePicker) {
        LocalDate localDate = datePicker.getValue();
        if (localDate != null) {
            return java.sql.Date.valueOf(localDate);
        }
        return null;
    }

    @FXML
    public void handleAddTaskButton(){
        String nom=nomTacheField.getText();
        Date debut=convertToDate(dateDebutField);
        Date fin=convertToDate(dateFinField);
        int estimation=Integer.parseInt(estimationField.getText());
        int idProjet=Integer.parseInt(idProjetField.getText());
        String cinEmployé=cinEmployeeField.getText();
        Task newTask=new Task(nom,debut,fin,estimation,idProjet,cinEmployé);
        try(Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/taskplanner","root","");
            PreparedStatement stmt=conn.prepareStatement("Insert into taches (nomTache,dateDebut,dateFin,estimation,taskProjectId,cinEmployé) values(?,?,?,?,?,?)");){
            stmt.setString(1,newTask.getNomTache());
            stmt.setDate(2,newTask.getDebut());
            stmt.setDate(3,newTask.getFin());
            stmt.setInt(4,newTask.getEstimation());
            stmt.setInt(5,newTask.getTacheProjetId());
            stmt.setString(6,newTask.getCinEmployé());
            int rowsInserted=stmt.executeUpdate();
            if(rowsInserted>0){
                taskTable.getItems().add(newTask);
                taskTable.refresh();
                clearFields();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void handleUpdateTaskButton(){
        Task selectedTask=taskTable.getSelectionModel().getSelectedItem();
        if(selectedTask==null){
            showWarning("Tache non sélectionné");
        }else{
            try(Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/taskplanner","root","");
            PreparedStatement stmt=conn.prepareStatement("Update taches Set dateDebut=?,dateFin=?,estimation=? Where nomTache=?")){
                stmt.setDate(1,convertToDate(nouveauDateDebutField));
                stmt.setDate(2,convertToDate(nouveauDateFinField));
                stmt.setInt(3,Integer.parseInt(nouveauEstimationField.getText()));
                stmt.setString(4,selectedTask.getNomTache());
                int rowsUpdated=stmt.executeUpdate();
                if(rowsUpdated>0){
                    selectedTask.setDebut(convertToDate(nouveauDateDebutField));
                    selectedTask.setFin(convertToDate(nouveauDateFinField));
                    selectedTask.setEstimation(Integer.parseInt(nouveauEstimationField.getText()));
                    taskTable.refresh();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void handleDeleteTaskButton(){
        Task selectedTask=taskTable.getSelectionModel().getSelectedItem();
        if(selectedTask==null){
            showWarning("Tache non sélectionné");
        }else{
            try(Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/taskplanner","root","");
            PreparedStatement stmt=conn.prepareStatement("Delete From taches Where nomTache=?")){
                stmt.setString(1,selectedTask.getNomTache());
                int rowsDeleted=stmt.executeUpdate();
                if(rowsDeleted>0){
                    taskTable.getItems().remove(selectedTask);
                    taskTable.refresh();
                }
            }catch(SQLException e){

            }
        }
    }

    public void showWarning(String message){
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Avertissement");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
    public void clearFields(){
        nomTacheField.clear();
        dateDebutField.getEditor().clear();
        dateFinField.getEditor().clear();
        estimationField.clear();
        idProjetField.clear();
        cinEmployeeField.clear();
    }
}
