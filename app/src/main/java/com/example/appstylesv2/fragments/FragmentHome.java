package com.example.appstylesv2.fragments;

import static com.example.appstylesv2.api.ValuesApi.BASE_URL;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.appstylesv2.R;
import com.example.appstylesv2.adapter.ClothesAdapter;
import com.example.appstylesv2.api.ServiceClothes;
import com.example.appstylesv2.model.Clothes;
import com.example.appstylesv2.model.ResponseClothes;
import com.example.appstylesv2.remote.ClienteRetrofit;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FragmentHome extends Fragment {
    private Retrofit retrofit;
    private ArrayList<Clothes> clothes;
    private ResponseClothes responseClothes;
    private RecyclerView recyclerView;
    private ClothesAdapter clothesAdapter;
    public FragmentHome() {
        // Required empty public constructor
    }

    public static FragmentHome newInstance() {
        FragmentHome fragment = new FragmentHome();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_clothes);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        showClothes();
        return view;
    }

    private void showClothes() {
        retrofit = ClienteRetrofit.getClient(BASE_URL);
        ServiceClothes serviceClothes = retrofit.create(ServiceClothes.class);
        Call <ResponseClothes> call = serviceClothes.accessClothes();
        call.enqueue(new Callback<ResponseClothes>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ResponseClothes> call, Response<ResponseClothes> response) {
                if (response.isSuccessful()){
                    ResponseClothes body = response.body();
                    clothes = body.getClothes();
                    clothesAdapter = new ClothesAdapter(clothes,getContext());
                    recyclerView.setAdapter(clothesAdapter);
                    Toast.makeText(getContext(),""+response.body().getClothes(), Toast.LENGTH_LONG).show();
                    //ArrayList<Clothes> clothes = body.getClothes();
                }else{
                    Toast.makeText(getContext(),"Error: "+response.message(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseClothes> call, Throwable t) {
                Toast.makeText(getContext(),"Error: ",Toast.LENGTH_LONG).show();
                Log.i("ERROR", "onFailure: "+t.getMessage());
            }
        });
    }
}