package com.example.planificationtaches;

import java.sql.Date;

public class Task {
    private String nomTache;
    private Date debut;
    private Date fin;
    private int estimation;
    private int tacheProjetId;
    private String cinEmployé;

    public Task(String nomTache, Date debut, Date fin, int estimation, int tacheProjetId, String cinEmployé) {
        this.nomTache = nomTache;
        this.debut = debut;
        this.fin = fin;
        this.estimation = estimation;
        this.tacheProjetId = tacheProjetId;
        this.cinEmployé = cinEmployé;
    }

    public String getNomTache() {
        return nomTache;
    }

    public void setNomTache(String nomTache) {
        this.nomTache = nomTache;
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

    public int getEstimation() {
        return estimation;
    }

    public void setEstimation(int estimation) {
        this.estimation = estimation;
    }

    public int getTacheProjetId() {
        return tacheProjetId;
    }

    public void setTacheProjetId(int tacheProjetId) {
        this.tacheProjetId = tacheProjetId;
    }

    public String getCinEmployé() {
        return cinEmployé;
    }

    public void setCinEmployé(String cinEmployé) {
        this.cinEmployé = cinEmployé;
    }
}
