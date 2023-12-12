package com.example.appstylesv2.fragments;

import static com.example.appstylesv2.api.ValuesApi.BASE_URL;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appstylesv2.R;
import com.example.appstylesv2.api.ServiceUsers;
import com.example.appstylesv2.model.ResponseUser;
import com.example.appstylesv2.model.User;
import com.example.appstylesv2.remote.ClienteRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class FragmentPerfil extends Fragment {

    public Retrofit retrofit;
    public ResponseUser responseUser;
    public List<User> UsersList;
    public TextView use_name;
    public TextView use_email;
    public TextView use_phone;
    public TextView use_address;
    public FragmentPerfil() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        use_name = (TextView) view.findViewById(R.id.tvNombreUser);
        use_email = (TextView) view.findViewById(R.id.tvInfoCorreo);
        use_phone = (TextView) view.findViewById(R.id.tvInfoCel);
        use_address = (TextView) view.findViewById(R.id.tvInfoDire);
        getUser();
        return view;
    }
    public void getUser(){
        retrofit = ClienteRetrofit.getClient(BASE_URL);
        ServiceUsers serviceUsers = retrofit.create(ServiceUsers.class);
        Call<ResponseUser> call = serviceUsers.infoUser();
        call.enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                if (response.isSuccessful()){
                    responseUser = response.body();
                    UsersList = responseUser.getUsers();
                    use_name.setText(UsersList.get(0).getUse_name());
                    use_email.setText(UsersList.get(0).getUse_email());
                    use_phone.setText(UsersList.get(0).getUse_phone());
                    use_address.setText(UsersList.get(0).getUse_address());
                    Toast.makeText(getContext(), "sirve"+UsersList.get(0).toString(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "No sirve", Toast.LENGTH_SHORT).show();

                }
            }



            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
                Toast.makeText(getContext(), "Error de peticion", Toast.LENGTH_SHORT).show();
            }
        });
    }
}