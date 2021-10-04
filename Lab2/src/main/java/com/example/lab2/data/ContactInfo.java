package com.example.lab2.data;

public class ContactInfo {
    private String telephone;
    private String email;

    public ContactInfo() {}

    public ContactInfo(String telephone, String email) {
        this.telephone = telephone;
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
