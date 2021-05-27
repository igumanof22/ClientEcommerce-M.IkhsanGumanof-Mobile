package com.example.clientecommerce_mikhsangumanof;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder>{
    private Context context;
    private List<Food> foodList;

    FoodAdapter(Context context, List<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.food_list, parent, false);
        return new FoodViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food = foodList.get(position);
        Glide.with(context).load(food.getGambar()).error(R.drawable.restomenu).into(holder.foodImage);
        holder.foodTitle.setText(food.getNama());
        holder.foodDesc.setText(food.getKet());
        holder.foodPrice.setText("Harga : "+food.getHarga());
        holder.foodPromo.setText("Promo : "+food.getPromo());
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    static class FoodViewHolder extends RecyclerView.ViewHolder{
        ImageView foodImage;
        TextView foodTitle, foodDesc, foodPrice, foodPromo;
        CardView cardView;
        FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            foodTitle = itemView.findViewById(R.id.foodTitle);
            foodDesc = itemView.findViewById(R.id.foodDesc);
            foodPrice = itemView.findViewById(R.id.foodPrice);
            foodPromo = itemView.findViewById(R.id.foodPromo);
            foodImage = itemView.findViewById(R.id.foodImage);
            cardView = itemView.findViewById(R.id.foodCard);
        }
    }
}
