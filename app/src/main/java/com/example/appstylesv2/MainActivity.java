package com.example.appstylesv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.appstylesv2.fragments.FragmentCarrito;
import com.example.appstylesv2.fragments.FragmentHome;
import com.example.appstylesv2.fragments.FragmentPerfil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    FragmentPerfil fragmentPerfil = new FragmentPerfil();
    FragmentHome fragmentHome = new FragmentHome();
    FragmentCarrito fragmentCarrito = new FragmentCarrito();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        selecc(navigation);
        loadFragment(fragmentHome);
    }
    public void loadFragment(Fragment fr){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container,fr);
        transaction.commit();

    }
    private void selecc(BottomNavigationView navigation){
        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.perfilFragment){
                    loadFragment(fragmentPerfil);
                    return true;
                } else if (id == R.id.homeFragment) {
                    loadFragment(fragmentHome);
                    return true;
                } else if (id == R.id.comprasFragment) {
                    loadFragment(fragmentCarrito);
                    return true;
                }
                return false;
            }
        });
    }
}