package com.example.appstylesv2.model;

import java.io.Serializable;

public class Clothes implements Serializable{
    private String clo_id;
    private String clo_name;
    private String clo_price;
    private String clo_stock;
    private String clo_details;


    public String getClo_id() {
        return clo_id;
    }

    public void setClo_id(String clo_id) {
        this.clo_id = clo_id;
    }

    public String getClo_name() {
        return clo_name;
    }

    public void setClo_name(String clo_name) {
        this.clo_name = clo_name;
    }

    public String getClo_price() {
        return clo_price;
    }

    public void setClo_price(String clo_price) {
        this.clo_price = clo_price;
    }

    public String getClo_stock() {
        return clo_stock;
    }

    public void setClo_stock(String clo_stock) {
        this.clo_stock = clo_stock;
    }

    public String getClo_details() {
        return clo_details;
    }

    public void setClo_details(String clo_details) {
        this.clo_details = clo_details;
    }


    @Override
    public String toString() {
        return "Clothes{" +
                "clo_id=" + clo_id +
                ", clo_name='" + clo_name + '\'' +
                ", clo_price='" + clo_price + '\'' +
                ", clo_stock='" + clo_stock + '\'' +
                ", clo_details='" + clo_details + '\'' +
                '}';
    }
}
