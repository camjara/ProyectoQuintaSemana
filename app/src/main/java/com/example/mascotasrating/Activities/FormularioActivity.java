package com.example.mascotasrating.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.mascotasrating.R;
import com.example.mascotasrating.tools.JavaMailAPI;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.Store;

public class FormularioActivity extends AppCompatActivity implements View.OnClickListener {
    TextInputEditText etNombre,etCorreo,etMensaje;
    Button btnEnviarComentario;
    ImageButton ibMejoresMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        ibMejoresMascotas = (ImageButton) findViewById(R.id.ibMejoresRating);
        ibMejoresMascotas.setVisibility(View.INVISIBLE);
        miActionBar.setTitle("Petagram");
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        etNombre = (TextInputEditText) findViewById(R.id.etNombre);
        etCorreo = (TextInputEditText) findViewById(R.id.etCorreo);
        etMensaje = (TextInputEditText) findViewById(R.id.etMensaje);
        btnEnviarComentario = (Button) findViewById(R.id.btnEnviarComentario);
        btnEnviarComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarComentario();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnEnviarComentario:
                enviarComentario();
                break;
        }
    }

    private void enviarComentario() {
        String nombre = etNombre.getText().toString();
        String correo = etCorreo.getText().toString();
        String mensaje = etMensaje.getText().toString();
        //Send Mail
        JavaMailAPI javaMailAPI = new JavaMailAPI(this,correo,nombre,mensaje);

        javaMailAPI.execute();
    }
}