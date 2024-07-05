package com.example.planificationtaches;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label nameLabel;

    @FXML
    private Label surnameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label telLabel;

    private Employee employee;


    public void setEmployee(Employee e){
        this.employee=e;
        mettreAJourInterface();
    }

    private void mettreAJourInterface() {
        nameLabel.setText("Nom:" + employee.getNom());
        surnameLabel.setText("Prènom:"+employee.getPrenom());
        emailLabel.setText("Email:"+employee.getEmail());
        telLabel.setText("Numéro de téléphone:"+employee.getNumtel());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        anchorPane.setPadding(new Insets(20));
    }
}
