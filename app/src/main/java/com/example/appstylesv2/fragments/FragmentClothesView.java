package com.example.appstylesv2.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appstylesv2.R;
import com.example.appstylesv2.model.Clothes;

public class FragmentClothesView extends Fragment {

    private TextView tvDetailClo_name;
    private TextView tvDetailClo_details;
    private View view;
    public FragmentClothesView() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_clothes_view, container, false);
        inicio(view);
        Bundle objetoClothes = getArguments();
        miObjetoClothes(objetoClothes);
        return view;
    }

    private void inicio(View view) {
        tvDetailClo_name = view.findViewById(R.id.tvDetailClo_name);
        tvDetailClo_details = view.findViewById(R.id.tvDetailClo_details);
    }

    private void miObjetoClothes(Bundle objetoClothes) {
        Clothes clothes = null;
        if (objetoClothes !=null && !(objetoClothes.isEmpty())){
            Toast.makeText(getContext(), ""+objetoClothes, Toast.LENGTH_SHORT).show();
            clothes = (Clothes) objetoClothes.getSerializable("dataClothes");
            tvDetailClo_name.setText(clothes.getClo_name());
            tvDetailClo_details.setText(clothes.getClo_details());
        }
    }
}