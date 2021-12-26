package com.example.touristapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BestHotelsAdapter extends RecyclerView.Adapter<BestHotelsAdapter.RecentsViewHolder> {
    Context context;
    List<BestHotels> bestHotelsList;

    public BestHotelsAdapter(Context context, List<BestHotels> bestHotelsList) {
        this.context = context;
        this.bestHotelsList = bestHotelsList;
    }

    @NonNull
    @Override
    public RecentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.activity_best_hotels,parent,false);
        //creating a recycleview row item layout file
        return new RecentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentsViewHolder holder, int position) {
        holder.cityName.setText(bestHotelsList.get(position).getCityName());
        holder.placeName.setText(bestHotelsList.get(position).getPlaceName());
        holder.price.setText(bestHotelsList.get(position).getPrice());
        holder.placeImage.setImageResource(bestHotelsList.get(position).getImageUrl());


    }

    @Override
    public int getItemCount() {
        return bestHotelsList.size();
    }

    public static final class RecentsViewHolder extends RecyclerView.ViewHolder{
        ImageView placeImage;
        TextView placeName,cityName,price;
        public RecentsViewHolder(@NonNull View itemView) {
            super(itemView);

            placeImage=itemView.findViewById(R.id.place_image);
            placeName=itemView.findViewById(R.id.place_name);
            cityName=itemView.findViewById(R.id.city_name);
            price=itemView.findViewById(R.id.price);
        }
    }
}
