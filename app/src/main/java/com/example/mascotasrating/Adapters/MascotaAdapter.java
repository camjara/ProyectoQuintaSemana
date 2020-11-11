package com.example.mascotasrating.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mascotasrating.BD.BD;
import com.example.mascotasrating.BD.DataBaseHelper;
import com.example.mascotasrating.Models.Mascota;
import com.example.mascotasrating.R;

import java.util.ArrayList;

public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder> {
    ArrayList<Mascota> listaMascotas;
    Context context;

    public MascotaAdapter(Context c,ArrayList<Mascota> listaMascotas) {
        this.context = c;
        this.listaMascotas = listaMascotas;
    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota,parent,false);
        return new MascotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MascotaViewHolder mascotaViewHolder, int position) {
        final Mascota mascota = listaMascotas.get(position);
        mascotaViewHolder.tvNombre.setText(mascota.getNombre());
        mascotaViewHolder.tvCantidadRating.setText(String.valueOf(mascota.getRating()));
        mascotaViewHolder.ivFoto.setImageResource(mascota.getFoto());
        mascotaViewHolder.ivFoto.setBackgroundColor(mascota.getColor());
        mascotaViewHolder.ibRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mascota.setRating(mascota.getRating() + 1);
                DataBaseHelper dbh = new DataBaseHelper(context);
                SQLiteDatabase db = dbh.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("rating",mascota.getRating());
                int respuesta = 0;
                respuesta =  db.update(BD.TABLE_MASCOTA,values,"id = ?", new String[]{String.valueOf(mascota.getId())});
                if(respuesta <= 0)
                    Toast.makeText(context, "No se puedo actualizar el rating", Toast.LENGTH_LONG).show();
                db.close();
                mascotaViewHolder.tvCantidadRating.setText(String.valueOf(mascota.getRating()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaMascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivFoto,ivRating;
        private ImageButton ibRating;
        private TextView tvNombre, tvCantidadRating;

        public MascotaViewHolder(@NonNull View v) {
            super(v);
            ivFoto = (ImageView) v.findViewById(R.id.ivFoto);
            ivRating = (ImageView) v.findViewById(R.id.ivRating);
            ibRating = (ImageButton) v.findViewById(R.id.ibRating);
            tvNombre = (TextView) v.findViewById(R.id.tvNombre);
            tvCantidadRating = (TextView) v.findViewById(R.id.tvCantidadRating);
        }
    }
}
