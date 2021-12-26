package com.example.touristapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class LocalHotelsActivity extends AppCompatActivity {
Button goBack;
    BestHotelsAdapter bestHotelsAdapter;
    RecyclerView recentRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_hotels);
        goBack = findViewById(R.id.mainActivityButton);
        List<BestHotels> bestHotelsList =new ArrayList<>();
        bestHotelsList.add(new BestHotels("Hotel Vellezerit Guri","Ferizaj","from $25",R.drawable.hotel_one));
        bestHotelsList.add(new BestHotels("Hotel Margjeka","Prizren","from $50",R.drawable.hotel_two));
        bestHotelsList.add(new BestHotels("Boga Alpine Resort","Peje","from $17",R.drawable.hotel_three));

        setRecentRecycler(bestHotelsList);

        goBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

    }
    private void setRecentRecycler(List<BestHotels> bestHotelsList){
        recentRecycler=findViewById(R.id.recent_recycler);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recentRecycler.setLayoutManager(layoutManager);
        bestHotelsAdapter =new BestHotelsAdapter(this, bestHotelsList);
        recentRecycler.setAdapter(bestHotelsAdapter);
    }

}