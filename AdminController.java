package com.example.planificationtaches;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class AdminController {
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

    private Admin admin;

    public void setAdmin(Admin a){
        this.admin=a;
        mettreAJourInterface();
    }

    private void mettreAJourInterface() {
        nameLabel.setText("Nom:" + admin.getNom());
        surnameLabel.setText("Prènom:"+admin.getPrenom());
        emailLabel.setText("Email:"+admin.getEmail());
        telLabel.setText("Numéro de téléphone:"+admin.getNumtel());
    }
}
