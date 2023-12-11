package com.example.appstylesv2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appstylesv2.R;
import com.example.appstylesv2.model.Clothes;

import java.util.ArrayList;
import java.util.List;

public class ClothesAdapter extends RecyclerView.Adapter<ClothesAdapter.ViewHolder> {
    private ArrayList<Clothes> clothesList;
    private Context context;
//
    public ClothesAdapter(ArrayList<Clothes> clothesList, Context context) {
        this.clothesList = clothesList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.clothes_view, parent, false);
        return (new ViewHolder(view));
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.tvClo_id.setText(clothesList.get(i).getClo_id());
        holder.tvClo_name.setText(clothesList.get(i).getClo_name());
        holder.tvClo_price.setText(clothesList.get(i).getClo_price());
        holder.tvClo_stock.setText(clothesList.get(i).getClo_stock());
        holder.tvClo_details.setText(clothesList.get(i).getClo_details());
    }
    @Override
    public int getItemCount() {
        return clothesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvClo_id;
        public TextView tvClo_name;
        public TextView tvClo_price;
        public TextView tvClo_stock;
        public TextView tvClo_details;

        public ViewHolder(@NonNull View view) {
            super(view);
            tvClo_id = view.findViewById(R.id.tvClo_id);
            tvClo_name = view.findViewById(R.id.tvClo_name);
            tvClo_price = view.findViewById(R.id.tvClo_price);
            tvClo_stock = view.findViewById(R.id.tvClo_stock);
            tvClo_details = view.findViewById(R.id.tvClo_details);
        }
    }
}