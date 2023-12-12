package com.example.appstylesv2.fragments;

import static com.example.appstylesv2.api.ValuesApi.BASE_URL;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.appstylesv2.R;
import com.example.appstylesv2.adapter.ClothesAdapter;
import com.example.appstylesv2.api.ServiceClothes;
import com.example.appstylesv2.model.Clothes;
import com.example.appstylesv2.model.ResponseClothes;
import com.example.appstylesv2.remote.ClienteRetrofit;
import java.util.ArrayList;
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
    FragmentClothesView clothesView;
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
        recyclerView = view.findViewById(R.id.recycler_view_clothes);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        showClothes();
        return view;
    }

    private void showClothes() {
        retrofit = ClienteRetrofit.getClient(BASE_URL);
        ServiceClothes serviceClothes = retrofit.create(ServiceClothes.class);
        Call <ResponseClothes> call = serviceClothes.accessClothes();
        call.enqueue(new Callback<ResponseClothes>() {
            @Override
            public void onResponse(Call<ResponseClothes> call, Response<ResponseClothes> response) {
                if (response.isSuccessful()){
                    ResponseClothes body = response.body();
                    clothes = body.getClothes();
                    clothesAdapter = new ClothesAdapter(clothes);
                    clothesAdapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Log.i("CLICK", "onClick: sifdghlskdhfglkshdlkfg");
                            //Toast.makeText(getContext(), "haciendo clc", Toast.LENGTH_SHORT).show();
                            Clothes clothes1;
                            //OBJETO
                            clothes1 = clothes.get(recyclerView.getChildAdapterPosition(v));
                            if (clothes1 != null && (clothes1.toString().length()>0)){

                                clothesView = new FragmentClothesView();
                                Bundle bundleServicio = new Bundle();
                                bundleServicio.putSerializable("dataClothes", clothes1);
                                clothesView.setArguments(bundleServicio);
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.replace(R.id.frame_container,clothesView).addToBackStack(null).commit();


                            }
                        }

                    });
                    recyclerView.setAdapter(clothesAdapter);
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