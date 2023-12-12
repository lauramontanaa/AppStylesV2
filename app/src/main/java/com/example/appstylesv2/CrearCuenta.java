package com.example.appstylesv2;

import static com.example.appstylesv2.api.ValuesApi.BASE_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appstylesv2.api.ServiceUsers;
import com.example.appstylesv2.model.ResponseUser;
import com.example.appstylesv2.model.User;
import com.example.appstylesv2.remote.ClienteRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CrearCuenta extends AppCompatActivity {
    Retrofit retrofit;
    private EditText use_name;
    private EditText use_email;
    private EditText use_password;
    private EditText use_phone;
    private EditText use_address;
    private Button btnCrearUser;
    private ResponseUser respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);
        begin();
        btnCrearUser.setOnClickListener(this::RegistrarUsers);


    }

    private void RegistrarUsers(View view) {
        User user = new User();
        user.setUse_name(this.use_name.getText().toString());
        user.setUse_email(this.use_email.getText().toString());
        user.setUse_phone(this.use_phone.getText().toString());
        user.setUse_address(this.use_address.getText().toString());
        user.setUse_password(this.use_password.getText().toString());
        retrofit = ClienteRetrofit.getClient(BASE_URL);
        //Toast.makeText(CrearCuenta.this, "Funciono", Toast.LENGTH_SHORT).show();
        Call<ResponseUser> call = ServiceUsers.createUser(user);
        call.enqueue(new Callback<ResponseUser>() {
                @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                respuesta = response.body();
                Toast.makeText(CrearCuenta.this, "Funciono"+respuesta, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
                Toast.makeText(CrearCuenta.this, "ERROR"+t, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void begin(){
        this.use_name = findViewById(R.id.etNombre);
        this.use_email = findViewById(R.id.etCorreo);
        this.use_password = findViewById(R.id.etCrearContrasena);
        this.use_phone = findViewById(R.id.etCelu);
        this.use_address = findViewById(R.id.etDireccion);
        this.btnCrearUser = findViewById(R.id.btnRegistrarCuenta);
    }



}