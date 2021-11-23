package com.example.ejemploservicios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText palabra;
    private Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        palabra= findViewById(R.id.palabra);
        boton= findViewById(R.id.boton);

        boton.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        Intent datos = new  Intent(getBaseContext(),guardaPalabras.class);
        Bundle paquete = new Bundle();
        paquete.putString("palabra", palabra.getText().toString());

        datos.putExtras(paquete);
        startService(datos);

    }
}