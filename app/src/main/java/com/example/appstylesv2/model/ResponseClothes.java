package com.example.appstylesv2.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class ResponseClothes{
    private ArrayList<Clothes> clothes;

    public ResponseClothes() {
    }

    public ArrayList<Clothes> getClothes() {
        return clothes;
    }

    public void setClothes(ArrayList<Clothes> clothes) {
        this.clothes = clothes;
    }
}
