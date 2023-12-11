package com.example.appstylesv2;

import static com.example.appstylesv2.api.ValuesApi.BASE_URL;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appstylesv2.api.ServiceLogin;
import com.example.appstylesv2.model.Credentials;
import com.example.appstylesv2.model.Loger;
import com.example.appstylesv2.model.ResponseCredentials;
import com.example.appstylesv2.remote.ClienteRetrofit;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Login extends AppCompatActivity {

    private Retrofit retrofit;
    private EditText usuario;
    private EditText contrasena;
    private Button ingresar;
    private Button crearCuenta;
    //private boolean obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        linkInit();
        ingresar.setOnClickListener(this::processLogin);
        crearCuenta.setOnClickListener(this::crearCuenta);
    }
    private void crearCuenta(View view) {
        Intent go = new Intent(getApplicationContext(), CrearCuenta.class);
        startActivity(go);
    }

    private void processLogin(View view) {
        if (!validEmail(usuario.getText().toString()) && contrasena.getText().length() <= 3) {
            alertView("Error en los datos");
        } else {
            String password = md5(contrasena.getText().toString());
            Loger loger = new Loger();
            loger.setUse_email(usuario.getText().toString());
            loger.setUse_password(password);
            retrofit = ClienteRetrofit.getClient(BASE_URL);
            ServiceLogin serviceLogin = retrofit.create(ServiceLogin.class);
            Call<ResponseCredentials> call = serviceLogin.accessLogin(loger);
            call.enqueue(new Callback<ResponseCredentials>() {
                @Override
                public void onResponse(Call<ResponseCredentials> call, Response<ResponseCredentials> response) {
                    if (response.isSuccessful()) {
                        ResponseCredentials body = response.body();
                        String mensaje = body.getMensaje();
                        ArrayList<Credentials> list = body.getCredencials();
                        //Toast.makeText(Login.this, "ingresando al app", Toast.LENGTH_SHORT).show();
                        if (mensaje.equals("OK") && !isNullOrEmpty(list)) {
                            for (Credentials e : list) {
                                SharedPreferences shared = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = shared.edit();
                                editor.putString("key", e.getUse_key());
                                editor.putString("identificador", e.getUse_identifier());
                                editor.putString("id", e.getUse_id());
                                editor.commit();
                                goTo();
                            }
                        } else {
                            alertView("error en credenciales");
                        }
                    } else {
                        alertView("usuario no existe, intente de nuevo");
                    }
                }

                @Override
                public void onFailure(Call<ResponseCredentials> call, Throwable t) {
                    Log.i("response", t.getMessage());
                    alertView("Error en el servidor, comuniquese con el desarrollador");
                }
            });
        }

    }

    private void linkInit() {
        this.usuario = findViewById(R.id.etUsuario);
        this.contrasena = findViewById(R.id.etContrasena);
        this.ingresar = findViewById(R.id.btnIngresar);
        this.crearCuenta = findViewById(R.id.btnRegistrarse);
    }

    private void alertView(String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Login");
        builder.setMessage(mensaje);
        builder.setPositiveButton("Aceptar", null);
        builder.create();
        builder.show();
    }

    public boolean validEmail(String data) {
        Pattern pattern =
                Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~\\-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
        Matcher mather = pattern.matcher(data);
        if (mather.find()) {
            System.out.println("El email ingresado es válido.");
            return true;
        } else {
            System.out.println("El email ingresado es inválido.");
        }
        return false;
    }

    public static String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte[] messageDigest = digest.digest();
            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null) return true;
        if (obj instanceof String)
            return ((String) obj).trim().equals("") || ((String) obj).equalsIgnoreCase("NULL");
        if (obj instanceof Integer) return ((Integer) obj) == 0;
        if (obj instanceof Long) return ((Long) obj).equals(new Long(0));
        if (obj instanceof Double) return ((Double) obj).equals(0.0);
        if (obj instanceof Collection) return (((Collection) obj).isEmpty());
        return false;
    }

    private void goTo() {
        try {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}