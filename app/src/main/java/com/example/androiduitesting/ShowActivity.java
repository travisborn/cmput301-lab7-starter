package com.example.androiduitesting;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        TextView nameView = findViewById(R.id.tv_city_name);
        Button backBtn = findViewById(R.id.btn_back);

        // get the name that was clicked
        String cityName = getIntent().getStringExtra("CITY_NAME");
        if (cityName == null) {
            cityName = "(unknown)";
        }
        nameView.setText(cityName);

        backBtn.setOnClickListener(v -> finish());
    }
}
