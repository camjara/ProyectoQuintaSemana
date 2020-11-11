package com.example.mascotasrating.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.mascotasrating.Adapters.MascotaAdapter;
import com.example.mascotasrating.BD.BD;
import com.example.mascotasrating.BD.DataBaseHelper;
import com.example.mascotasrating.Models.Mascota;
import com.example.mascotasrating.R;

import java.util.ArrayList;

public class PrincipalesRatingActivity extends AppCompatActivity {
    RecyclerView rvListaMejoresMascotas;
    ArrayList<Mascota> listaMejoresMascotas;
    ImageButton ibMejoresMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principales_rating);
        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        ibMejoresMascotas = (ImageButton) findViewById(R.id.ibMejoresRating);
        ibMejoresMascotas.setVisibility(View.INVISIBLE);
        miActionBar.setTitle("Petagram");
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        rvListaMejoresMascotas = (RecyclerView) findViewById(R.id.rvListaMejoresMascotas);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvListaMejoresMascotas.setLayoutManager(manager);

        listaMejoresMascotas = new ArrayList<>();
        cargarMascotasBD();
        cargarAdptador();
    }

    private void cargarAdptador()
    {
        MascotaAdapter adaptador = new MascotaAdapter(this,listaMejoresMascotas);
        rvListaMejoresMascotas.setAdapter(adaptador);
    }

    private void cargarMascotasBD()
    {
        DataBaseHelper dbh = new DataBaseHelper(getApplicationContext());
        SQLiteDatabase db = dbh.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + BD.TABLE_MASCOTA + " ORDER BY rating DESC LIMIT 5",new String[]{},null);
        while(cursor.moveToNext())
        {
            listaMejoresMascotas.add(new Mascota(cursor.getInt(cursor.getColumnIndex("id")),cursor.getString(cursor.getColumnIndex("nombre")), cursor.getInt(cursor.getColumnIndex("rating")), cursor.getInt(cursor.getColumnIndex("foto")), cursor.getInt(cursor.getColumnIndex("color"))));
        }
        db.close();
    }

   /* private void cargarMascotas()
    {
        Bundle bundle = getIntent().getExtras();
        for (int i = 1; i <= 5 ;i++)
        {
            String[] mascota = (String[]) bundle.get(String.valueOf(i));
            listaMejoresMascotas.add(new Mascota(mascota[0],Integer.parseInt(mascota[1]),Integer.parseInt(mascota[2]),Integer.parseInt(mascota[3])));
        }
    }*/
}