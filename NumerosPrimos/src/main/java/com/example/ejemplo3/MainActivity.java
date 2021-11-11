package com.example.ejemplo3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ForkJoinPool;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button Boton;
    private TextView Texto;
    private EditText Numero;
    private int ListaPrimos[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Boton= findViewById(R.id.Boton);
        Boton.setOnClickListener(this);
        Texto=findViewById(R.id.Texto);
        Numero= findViewById(R.id.Numero);

        ListaPrimos= new int[999];
        calcularNumerosPrimos();

    }

    public void onClick(View view){
        try {
            int numeroCojido=Integer.parseInt(Numero.getText().toString());
            if(numeroCojido<1000 && numeroCojido>0) {
                String Salida = String.valueOf(ListaPrimos[numeroCojido-1]);
                Texto.setText(Salida);
            }else{
                Texto.setText(R.string.Texto_error_numero);
            }
        }catch (Exception e){
            Texto.setText(R.string.Texto_error_Letras);
        }

    }
    private void calcularNumerosPrimos(){
        ListaPrimos[0]=2;
        int numeroLista =1;
        int numero =3;
        while(numeroLista<999){

            int resto=numero%2;
            if(resto==1){
                ListaPrimos[numeroLista]=numero;
                numeroLista++;
            }
            numero++;
        }
    }
}