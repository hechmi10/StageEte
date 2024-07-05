package com.example.planificationtaches;

import java.io.Serializable;

public class Employee implements Serializable {
    private String cin;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String motDePasse;
    private String numtel;

    public Employee(String id, String nom, String prenom, String adresse, String email,String mdp,String tel) {
        this.cin = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.motDePasse=mdp;
        this.numtel=tel;
    }

    public String getCin() {
        return cin;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEmail() {
        return email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }
}