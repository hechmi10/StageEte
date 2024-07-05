package com.example.planificationtaches;

import java.sql.Date;

public class Project {
    private int projetId;
    private String nomProjet;
    private Date debut;
    private Date fin;

    public Project(int id,String nom,Date debut,Date fin) {
        this.projetId = id;
        this.nomProjet = nom;
        this.debut = debut;
        this.fin = fin;

    }

    public int getProjetId() {
        return projetId;
    }

    public void setProjetId(int projetId) {
        this.projetId = projetId;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }
}
