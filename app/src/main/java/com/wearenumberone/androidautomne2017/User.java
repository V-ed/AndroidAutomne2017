package com.wearenumberone.androidautomne2017;

import java.io.Serializable;

/**
 * Created by 201412728 on 2017-12-05.
 */

public class User implements Serializable {

    private String email;
    private String password;
    private String telephone;

    public User(String email, String password, String telephone) {
        this.email = email;
        this.password = password;
        this.telephone = telephone;
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
        return "Email =  " + email + "\n" + "Password = " + password + "\n" + "Telephone = " + telephone;
    }

}
