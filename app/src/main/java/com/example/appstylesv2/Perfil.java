package com.example.appstylesv2;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Perfil extends AppCompatActivity {
    private Button btnCrearUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);
        begin();
        //btnCrearUser.setOnClickListener(this::RegistrarUsers);
    }

    private void begin() {
        this.btnCrearUser = findViewById(R.id.btnRegistrarCuenta);
    }
}
