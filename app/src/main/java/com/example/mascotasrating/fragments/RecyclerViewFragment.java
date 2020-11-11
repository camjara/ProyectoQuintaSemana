package com.example.mascotasrating.fragments;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mascotasrating.Activities.PrincipalesRatingActivity;
import com.example.mascotasrating.Adapters.MascotaAdapter;
import com.example.mascotasrating.BD.BD;
import com.example.mascotasrating.BD.DataBaseHelper;
import com.example.mascotasrating.Models.Mascota;
import com.example.mascotasrating.R;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class RecyclerViewFragment extends Fragment {
    ArrayList<Mascota> listaMascotas;
    RecyclerView rvListaMascotas;
    ImageButton ibMejoresRating;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_recyclerview,container,false);

        rvListaMascotas = (RecyclerView) v.findViewById(R.id.rvListaMascotas);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvListaMascotas.setLayoutManager(manager);
        ibMejoresRating = (ImageButton) getActivity().findViewById(R.id.ibMejoresRating);
        ibMejoresRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarMejoresRating();
            }
        });

        listaMascotas = new ArrayList<>();
        cargarListadoMascotas();
        inicalizarAdaptador();

        return v;
    }


    private void inicalizarAdaptador(){
        MascotaAdapter adapter = new MascotaAdapter(getActivity(),listaMascotas);
        rvListaMascotas.setAdapter(adapter);
    }


    private void cargarListadoMascotas()
    {
        DataBaseHelper dbh = new DataBaseHelper(getActivity());
        SQLiteDatabase db = dbh.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + BD.TABLE_MASCOTA,new String[]{},null);
        while(cursor.moveToNext()) {
            listaMascotas.add(new Mascota(cursor.getInt(cursor.getColumnIndex("id")),cursor.getString(cursor.getColumnIndex("nombre")), cursor.getInt(cursor.getColumnIndex("rating")), cursor.getInt(cursor.getColumnIndex("foto")), cursor.getInt(cursor.getColumnIndex("color"))));
        }
        db.close();
    }



    private void mostrarMejoresRating()
    {
        listaMascotas.sort(new Comparator<Mascota>() {
            @Override
            public int compare(Mascota mascota, Mascota t1) {
                int result = -1;
                if(mascota.getRating() < t1.getRating())
                    result = -1;
                else if(mascota.getRating() == t1.getRating())
                    result = 0;
                else
                    result = 1;

                return result;
            }
        });

        Intent intent = new Intent(getActivity(), PrincipalesRatingActivity.class);
        int cont = 1;
        for (int i = listaMascotas.size() - 1; i > 1; i --) {
            String [] mascota = {listaMascotas.get(i).getNombre(),String.valueOf(listaMascotas.get(i).getRating()),String.valueOf(listaMascotas.get(i).getFoto()),String.valueOf(listaMascotas.get(i).getColor())};
            intent.putExtra(String.valueOf(cont), mascota);
            cont++;
        }
        startActivity(intent);
    }

}
