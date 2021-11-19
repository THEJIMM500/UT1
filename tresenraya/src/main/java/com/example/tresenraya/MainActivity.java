package com.example.tresenraya;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private boolean juegalamaquina;
    private boolean juegaContraLaMaquina;
    private boolean jueganX;
    private boolean[] pulsado;
    private LogicaTresEnRaya partida;
    private ToggleButton modo_juego;
    private TextView indicador_modo_juego;
    private Button empezarDeNuevo;
    private TableRow fila0;
    private TableRow fila1;
    private TableRow fila2;
    private ArrayList<Button> listaDeBotones;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaDeBotones=new ArrayList<Button>();
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
                reiniciarBotones();
                jueganX= empiezanLasX();

            }
        });
    }

    private void CrearPartida() {
        CrearTabla();
        iniciarBoleanpulsado();

        indicador_modo_juego= findViewById(R.id.TextoModoJuego);
        indicador_modo_juego.setText(R.string.JvJ);
        crearTogleBotton();
        partida= new LogicaTresEnRaya();
        juegaContraLaMaquina= false;
        juegalamaquina=false;
        jueganX=empiezanLasX();
    }

    private void crearTogleBotton() {
        modo_juego =findViewById(R.id.ModoJuego);
        modo_juego.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    indicador_modo_juego.setText(R.string.JVM);
                    juegaContraLaMaquina=true;
                } else {
                    indicador_modo_juego.setText(R.string.JvJ);
                    juegaContraLaMaquina=false;
                }
            }
        });
    }

    private void CrearTabla() {
        //int numnames = names.length;
        TableLayout tablero = findViewById(R.id.Tablero);
        fila0 = new TableRow(this);
        fila1 = new TableRow(this);
        fila2 = new TableRow(this);
        fila0.addView(getButton(0, Color.BLACK, Typeface.BOLD, Color.GREEN));
        fila0.addView(getButton(1, Color.BLACK, Typeface.BOLD, Color.GREEN));
        fila0.addView(getButton(2, Color.BLACK, Typeface.BOLD, Color.GREEN));
        fila1.addView(getButton(3, Color.BLACK, Typeface.BOLD, Color.GREEN));
        fila1.addView(getButton(4, Color.BLACK, Typeface.BOLD, Color.GREEN));
        fila1.addView(getButton(5, Color.BLACK, Typeface.BOLD, Color.GREEN));
        fila2.addView(getButton(6, Color.BLACK, Typeface.BOLD, Color.GREEN));
        fila2.addView(getButton(7, Color.BLACK, Typeface.BOLD, Color.GREEN));
        fila2.addView(getButton(8, Color.BLACK, Typeface.BOLD, Color.GREEN));
        tablero.addView(fila0);
        tablero.addView(fila1);
        tablero.addView(fila2);

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
        //esto hace que se vena las lineas de los botones
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

                        JuegaMaquina();
                        if(partida.GanaJugador1()==true){
                            mandarMensaje("Ha ganado el Jugador 1");
                            bloquearBotones();
                        }else{
                            empate();
                        }

                    }else{
                        puntuar(id);
                        button.setText("O");
                        jueganX= true;
                        mandarMensaje("Juega el Jugador 1");

                        JuegaMaquina();
                        if(partida.GanaJugador2()==true){
                            mandarMensaje("Ha ganado el Jugador 2");
                            bloquearBotones();
                        }else{
                            empate();
                        }
                    }
                    pulsado[id]= true;
                }
            }
        });
        listaDeBotones.add(button);
        return button;
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
    private void reiniciarBotones(){
        for (int cont =0;cont< listaDeBotones.size(); cont++) {
            Button boton = listaDeBotones.get(cont);
            boton.setText(" ");
        }
    }
    private void empate (){
        if(partida.quedanMovimientos()==false){
            mandarMensaje("Los Jugadores han empatado");
        }
    };
    private boolean empiezanLasX(){
        double numeroAleatorio= Math.round(Math.random());
        if(numeroAleatorio==1){
            mandarMensaje("juegan las X");
            return true;
        }else{
        mandarMensaje("juegan las O");
            return false;
        }
    }
    private void JuegaMaquina(){
        if(juegaContraLaMaquina==true) {
            if(jueganX== true){
                int posicionJugada=partida.MueveOrdenador1();
                Button boton= listaDeBotones.get(posicionJugada);
                boton.setText("X");
                pulsado[posicionJugada]=true;
                jueganX=false;
                mandarMensaje("Juega el Jugador 2");
            }else{
                int posicionJugada=partida.MueveOrdenador2();
                Button boton= listaDeBotones.get(posicionJugada);
                boton.setText("O");
                jueganX=true;
                mandarMensaje("Juega el Jugador 1");
            }
        }

    }
}
