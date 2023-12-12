package com.example.appstylesv2.model;

public class User {
    public String use_id;
    public String use_name;
    public String use_email;
    public String use_password;
    public String use_phone;
    public String use_address;

    public String getUse_id() {
        return use_id;
    }

    public void setUse_id(String use_id) {
        this.use_id = use_id;
    }

    public String getUse_name() {
        return use_name;
    }

    public void setUse_name(String use_name) {
        this.use_name = use_name;
    }

    public String getUse_email() {
        return use_email;
    }

    public void setUse_email(String use_email) {
        this.use_email = use_email;
    }

    public String getUse_password() {
        return use_password;
    }

    public void setUse_password(String use_password) {
        this.use_password = use_password;
    }

    public String getUse_phone() {
        return use_phone;
    }

    public void setUse_phone(String use_phone) {
        this.use_phone = use_phone;
    }

    public String getUse_address() {
        return use_address;
    }

    public void setUse_address(String use_address) {
        this.use_address = use_address;
    }

    @Override
    public String toString() {
        return "User{" +
                "use_id='" + use_id + '\'' +
                ", use_name='" + use_name + '\'' +
                ", use_email='" + use_email + '\'' +
                ", use_password='" + use_password + '\'' +
                ", use_phone='" + use_phone + '\'' +
                ", use_address='" + use_address + '\'' +
                '}';
    }
}
