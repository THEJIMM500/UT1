package com.example.ejemplocomunicacionactiviti;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActividadConRespuesta extends AppCompatActivity implements View.OnClickListener {

    private TextView texto;
    private Button boton;
    private  int numero;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_con_respuesta);

        texto= findViewById(R.id.numero);
        boton= findViewById(R.id.botonAccion);

        Bundle paquete = getIntent().getExtras();

        numero = paquete.getInt("numero");

        texto.setText(numero+"");

        boton.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        numero= numero*2;
        //preparamos un paquete para regresarlo
        Bundle paqueteRespuesta = new Bundle();
        paqueteRespuesta.putInt("numero",numero);

        Intent respuesta = new Intent();
        respuesta.putExtras(paqueteRespuesta);
        //devolvemos el resultado
        setResult(RESULT_OK,respuesta);
        finish();

    }
}
