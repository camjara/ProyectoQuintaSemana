package com.example.mascotasrating.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.mascotasrating.R;

public class BiografiaActivity extends AppCompatActivity {
    ImageButton ibMejoresMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biografia);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        ibMejoresMascotas = (ImageButton) findViewById(R.id.ibMejoresRating);
        ibMejoresMascotas.setVisibility(View.INVISIBLE);
        miActionBar.setTitle("Petagram");
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}