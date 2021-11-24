package com.sujal.unitconverter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView area_card, length_card, mileage_card, speed_card;

        area_card = findViewById(R.id.cardView_area);
        length_card = findViewById(R.id.cardView_length);
        mileage_card = findViewById(R.id.cardView_mileage);
        speed_card = findViewById(R.id.cardView_speed);

        area_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AreaActivity.class));
            }
        });

        length_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LengthActivity.class));
            }
        });

        mileage_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MileageActivity.class));
            }
        });

        speed_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SpeedActivity.class));
            }
        });
    }
}