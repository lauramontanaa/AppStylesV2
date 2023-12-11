package com.example.appstylesv2.model;

public class PerfilUsuario {
    private String use_name;
    private String use_email;
    private String use_phone;
    private String use_address;

    public String getUse_name() {
        return use_name;
    }

    public String getUse_email() {
        return use_email;
    }

    public String getUse_phone() {
        return use_phone;
    }

    public String getUse_address() {
        return use_address;
    }

    @Override
    public String toString() {
        return "PerfilUsuario{" +
                "use_name='" + use_name + '\'' +
                ", use_email='" + use_email + '\'' +
                ", use_phone='" + use_phone + '\'' +
                ", use_address='" + use_address + '\'' +
                '}';
    }
}
