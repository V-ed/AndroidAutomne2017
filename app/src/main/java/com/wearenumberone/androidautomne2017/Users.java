package com.wearenumberone.androidautomne2017;

import java.io.Serializable;

/**
 * Created by 201412728 on 2017-12-05.
 */

public class Users implements Serializable {
    private int id;

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

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    private String email;
    private String password;
    private int telephone;

    public Users(){}

    public Users(String email, String password, int telephone){
        this.email = email;
        this.password = password;
        this.telephone = telephone;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Email =  " + email + "\n" + "Password = " + password +"\n" + "Telephone = " +telephone);
        return sb.toString();
    }

}
