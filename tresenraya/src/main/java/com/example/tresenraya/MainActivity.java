package com.example.tresenraya;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {
    private boolean juegalamaquina;
    private boolean jueganX;
    private boolean[] pulsado;
    private LogicaTresEnRaya partida;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CrearTabla();
        iniciarBoleanpulsado();
        partida= new LogicaTresEnRaya();
        juegalamaquina=false;
        jueganX=true;
    }
    private void CrearTabla() {
        //int numnames = names.length;
        TableLayout tablero = findViewById(R.id.Tablero);
        TableRow fila0 = new TableRow(this);
        TableRow fila1 = new TableRow(this);
        TableRow fila2 = new TableRow(this);
        fila0.setLayoutParams(getLayoutParams());
        fila1.setLayoutParams(getLayoutParams());
        fila2.setLayoutParams(getLayoutParams());
        fila0.addView(getButton(1, Color.BLACK, Typeface.BOLD, Color.GREEN));
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
        button.setTypeface(Typeface.DEFAULT, typeface);
        button.setBackgroundColor(bgColor);
        //suministra parámetros al padre de la View
        button.setLayoutParams(getLayoutParams());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pulsado[0]==false){
                    if(jueganX==true){
                        puntuar(0);
                        button.setText("X");
                        jueganX= false;
                    }else{
                        puntuar(0);
                        button.setText("O");
                        jueganX= true;
                    }
                    pulsado[0]= true;
                }
            }
        });
        return button;
    }
    private TableRow.LayoutParams getLayoutParams() {
        //ancho (tan grande como el padre - rellleno) y alto (tan grande como su contenido interno + relleno)
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
}