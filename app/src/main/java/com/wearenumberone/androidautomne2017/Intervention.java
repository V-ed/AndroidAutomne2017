package com.wearenumberone.androidautomne2017;

import java.util.Date;

/**
 * Created by patof on 2017-10-20.
 */



public class Intervention {

    private int img;
    private String tabType;
    private String nom;
    private String prenom;
    private String heureDebut;

    public Intervention(int img, String tabType, String nom, String prenom, String heureDebut) {
        this.img = img;
        this.tabType = tabType;
        this.nom = nom;
        this.prenom = prenom;
        this.heureDebut = heureDebut;
    }

    public int getImg() {
        return img;
    }

    public String getTabType() {
        return tabType;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getHeureDebut() {
        return heureDebut;
    }
}
