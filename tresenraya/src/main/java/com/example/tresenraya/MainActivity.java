package com.example.tresenraya;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private boolean juegalamaquina;
    private boolean jueganX;
    private boolean[] pulsado;
    private LogicaTresEnRaya partida;
    private Button empezarDeNuevo;
    private TableRow fila0;
    private TableRow fila1;
    private TableRow fila2;
    private Button boton0;
    private Button boton1;
    private Button boton2;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crearBotonEmpezar();
        CrearPartida();
    }

    private void crearBotonEmpezar() {
        empezarDeNuevo= findViewById(R.id.EmpezarPartida);
        empezarDeNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               partida.iniciar();
               iniciarBoleanpulsado();
            }
        });
    }

    private void CrearPartida() {
        CrearTabla();
        iniciarBoleanpulsado();
        partida= new LogicaTresEnRaya();
        juegalamaquina=false;
        jueganX=true;
    }

    private void CrearTabla() {
        //int numnames = names.length;
        TableLayout tablero = findViewById(R.id.Tablero);
        fila0 = new TableRow(this);
        fila1 = new TableRow(this);
        fila2 = new TableRow(this);
        creaBoton0();
        fila0.addView(boton0);
        fila0.addView(getButton(1, Color.BLACK, Typeface.BOLD, Color.GREEN));
        fila0.addView(getButton(2, Color.BLACK, Typeface.BOLD, Color.GREEN));
        fila1.addView(getButton(3, Color.BLACK, Typeface.BOLD, Color.GREEN));
        fila1.addView(getButton(4, Color.BLACK, Typeface.BOLD, Color.GREEN));
        fila1.addView(getButton(5, Color.BLACK, Typeface.BOLD, Color.GREEN));
        fila2.addView(getButton(6, Color.BLACK, Typeface.BOLD, Color.GREEN));
        fila2.addView(getButton(7, Color.BLACK, Typeface.BOLD, Color.GREEN));
        fila2.addView(getButton(8, Color.BLACK, Typeface.BOLD, Color.GREEN));
        tablero.addView(fila0, getTblLayoutParams());
        tablero.addView(fila1, getTblLayoutParams());
        tablero.addView(fila2, getTblLayoutParams());

    }
    private Button getButton(int id,int color, int typeface, int bgColor) {
        Button button = new Button(this);
        button.setId(id);
        button.setText(" ");
        button.setTextColor(color);
        //fijamos el relleno (espacio dentro del View)
        button.setPadding(20, 20, 20, 20);
        //fijamos el tipo de la letra y el estilo
        button.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        button.setBackgroundColor(bgColor);
        //suministra parámetros al padre de la View
        button.setLayoutParams(getLayoutParams());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pulsado[id]==false){
                    if(jueganX==true){
                        puntuar(id);
                        button.setText("X");
                        jueganX= false;
                        mandarMensaje("Juega el Jugador 2");
                        if(partida.GanaJugador1()==true){
                            mandarMensaje("Ha ganado el Jugador 1");
                            bloquearBotones();
                        }
                    }else{
                        puntuar(id);
                        button.setText("O");
                        jueganX= true;
                        mandarMensaje("Juega el Jugador 1");
                        if(partida.GanaJugador2()==true){
                            mandarMensaje("Ha ganado el Jugador 2");
                            bloquearBotones();
                        }
                    }
                    pulsado[id]= true;
                }
            }
        });
        return button;
    }
    private void creaBoton0() {
        int id =0;
         boton0 = new Button(this);
        boton0.setId(id);
        boton0.setText(" ");
        boton0.setTextColor( Color.BLACK);
        //fijamos el relleno (espacio dentro del View)
        boton0.setPadding(20, 20, 20, 20);
        //fijamos el tipo de la letra y el estilo
        boton0.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        boton0.setBackgroundColor(Color.GREEN);
        //suministra parámetros al padre de la View
        boton0.setLayoutParams(getLayoutParams());
        boton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pulsado[id]==false){
                    if(jueganX==true){
                        puntuar(id);
                        boton0.setText("X");
                        jueganX= false;
                        mandarMensaje("Juega el Jugador 2");
                        if(partida.GanaJugador1()==true){
                            mandarMensaje("Ha ganado el Jugador 1");
                            bloquearBotones();
                        }
                    }else{
                        puntuar(id);
                        boton0.setText("O");
                        jueganX= true;
                        mandarMensaje("Juega el Jugador 1");
                        if(partida.GanaJugador2()==true){
                            mandarMensaje("Ha ganado el Jugador 2");
                            bloquearBotones();
                        }
                    }
                    pulsado[id]= true;
                }
            }
        });
    }
    private TableRow.LayoutParams getLayoutParams() {
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(2, 0, 2, 2);
        return params;
    }
    // Método que construye un LayoutParams para una TableLayout
    private TableLayout.LayoutParams getTblLayoutParams() {
        return new TableLayout.LayoutParams(
                20,20);
    }
    private void iniciarBoleanpulsado(){
        pulsado = new boolean[9];
        for (int cont=0; cont< pulsado.length; cont++){
            pulsado[cont]=false;
        }
    }
    private void puntuar(int pos){
        if(jueganX==true){
            partida.MueveJugador1(pos);
        }else{
            partida.MueveJugador2(pos);
        }
    }

    private void mandarMensaje(String mensaje){
        Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        mensaje, Toast.LENGTH_LONG);
        toast1.show();
    }
    private void bloquearBotones(){
        for (int cont=0; cont< pulsado.length; cont++){
            pulsado[cont]=true;
        }
    }

}