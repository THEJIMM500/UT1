package com.example.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int numeroAleatorio;
    private int nIntentos;
    private TextView textIntentos;
    private Button boton;
    private EditText texto;
    private Intent llamada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nIntentos = 3;
        numeroAleatorio =(int)Math.ceil(Math.random()*10);
        textIntentos= findViewById(R.id.intentos);
        textIntentos.setText("quedan "+nIntentos+"");

        texto= findViewById(R.id.cojerNumero);
        boton= findViewById(R.id.boton1);
        boton.setOnClickListener(this);
    }
    public void onClick(View view) {
        int numero= Integer.parseInt(texto.getText().toString());

        if(numero!=numeroAleatorio&& nIntentos>0) {
            Bundle paquete = new Bundle();
            paquete.putInt("intentos", nIntentos);
            llamada= new Intent(this, faseFallo.class);
            llamada.putExtras(paquete);

            startActivityForResult(llamada,0001);
        }else{

        }

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==0001 && resultCode== RESULT_OK){
            Bundle datos= data.getExtras();
            nIntentos=datos.getInt("intentos");
            textIntentos.setText("quedan "+nIntentos+"");
        }
    }
}
