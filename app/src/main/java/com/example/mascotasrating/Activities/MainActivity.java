package com.example.mascotasrating.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mascotasrating.Adapters.MascotaAdapter;
import com.example.mascotasrating.Adapters.PageAdapter;
import com.example.mascotasrating.BD.BD;
import com.example.mascotasrating.BD.DataBaseHelper;
import com.example.mascotasrating.Models.Mascota;
import com.example.mascotasrating.R;
import com.example.mascotasrating.fragments.PerfilFragment;
import com.example.mascotasrating.fragments.RecyclerViewFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class MainActivity extends AppCompatActivity  {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ArrayList<Mascota> listaMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        miActionBar.setTitle("Petagram");
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager();
        almacenarMascotasBD();
        /*if(toolbar != null)
        {
            setSupportActionBar(toolbar);
        }*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.contacto:
                Intent contacto = new Intent(this,FormularioActivity.class);
                startActivity(contacto);
                break;
            case R.id.acerca_de:
                Intent acerca_de = new Intent(this, BiografiaActivity.class);
                startActivity(acerca_de);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Fragment> agregarFragments()
    {
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new RecyclerViewFragment());
        fragments.add(new PerfilFragment());

        return fragments;
    }
    private void setupViewPager()
    {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),-1,agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_menu1);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_menu4);
    }
    private void almacenarMascotasBD()
    {
        DataBaseHelper dbh = new DataBaseHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+BD.TABLE_MASCOTA,null,null);
        if(cursor.isAfterLast()) {
            ContentValues values = new ContentValues();
            long respuesta = 0;
            for (int i = 0; i < 7; i++) {
                values.put("nombre", getNombre());
                values.put("rating", 0);
                values.put("color", getColor());
                values.put("foto", getImagen());
                respuesta = db.insert(BD.TABLE_MASCOTA, null, values);
                if (respuesta <= 0)
                    Toast.makeText(this, "Error al registrar la mascota", Toast.LENGTH_LONG).show();
            }
        }
        db.close();

    }

    private String getNombre()
    {
        String listaNombres [] = {"Lester","Manolo","Susy","Toby","Lulú","Simon","Lana"};
        Random r = new Random();
        int numero = r.nextInt(7);
        return  listaNombres[numero];
    }

    private int getImagen()
    {
        int ListaAnimales  [] = {R.drawable.ic_caballo,R.drawable.ic_elefante,R.drawable.ic_mono,R.drawable.ic_perro,R.drawable.ic_tigre,R.drawable.ic_toro,R.drawable.ic_vaca};
        Random r = new Random();
        int numero = r.nextInt(7);
        return ListaAnimales[numero];
    }

    private int getColor()
    {
        int ListaColores  [] = {R.color.azul,R.color.amarillo,R.color.aguamarina,R.color.verde,R.color.rojo,R.color.naranja,R.color.fucsia};
        Random r = new Random();
        int numero = r.nextInt(7);
        return ListaColores[numero];
    }


}