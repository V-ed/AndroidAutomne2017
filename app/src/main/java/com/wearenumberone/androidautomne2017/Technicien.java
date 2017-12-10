package com.wearenumberone.androidautomne2017;

import java.io.Serializable;

/**
 * Created by V-ed on 2017-12-10.
 */

public class Technicien implements Serializable {

    private String name;
    private String email;
    private String password;
    private String telephone;

    public Technicien(String name, String email, String password, String telephone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Nom = " + getName() + "\n" + "Email =  " + getEmail() + "\n" + "Password = " + getPassword() + "\n" + "Telephone = " + getTelephone();
    }

}
