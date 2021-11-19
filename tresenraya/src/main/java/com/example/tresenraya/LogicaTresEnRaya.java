package com.example.tresenraya;

public class LogicaTresEnRaya {
    private int tablero[];

    public LogicaTresEnRaya(){
        tablero = new int[9];
        iniciar();
    }

    public void iniciar() {
        for (int cont =0;cont<tablero.length;cont++){
            tablero[cont]=0;
        }
    }

    public int getTablero (int pos){
        return tablero[pos-1];
    }

    public void MueveJugador1(int pos){
        if( tablero[pos]==0){
            tablero[pos]=1;
        }
    }
    public int MueveOrdenador1(){
        int numeroAleatorio= (int) Math.round(Math.random()*8);
        while(MovimientoValido(numeroAleatorio)==false){
            numeroAleatorio= (int) Math.round(Math.random()*8);
        }
        tablero[numeroAleatorio]=1;
        return numeroAleatorio;
    }
    public int MueveOrdenador2(){
        int numeroAleatorio= (int) Math.round(Math.random()*8);
        while(MovimientoValido(numeroAleatorio)==false){
            numeroAleatorio= (int) Math.round(Math.random()*8);
        }
        tablero[numeroAleatorio]=2;
        return numeroAleatorio;
    }
    public void MueveJugador2(int pos){
        if( tablero[pos]==0){
            tablero[pos]=2;
        }
    }
    public boolean MovimientoValido(int pos){
        if( tablero[pos]==0){
            return true;
        }else{
            return false;
        }
    }
    public boolean quedanMovimientos(){
        for (int cont =0;cont<tablero.length;cont++){
            if(tablero[cont]==0) {
                return true;
            }
        }
        return false;
    }
    public boolean GanaJugador1(){
        //horizontales
        if((tablero[0]==1)&&(tablero[1]==1)&&(tablero[2]==1)){
            return true;
        }
        if((tablero[3]==1)&&(tablero[4]==1)&&(tablero[5]==1)){
            return true;
        }
        if((tablero[6]==1)&&(tablero[7]==1)&&(tablero[8]==1)){
            return true;
        }
        //verticales
        if((tablero[0]==1)&&(tablero[3]==1)&&(tablero[6]==1)){
            return true;
        }
        if((tablero[1]==1)&&(tablero[4]==1)&&(tablero[7]==1)){
            return true;
        }
        if((tablero[2]==1)&&(tablero[5]==1)&&(tablero[8]==1)){
            return true;
        }
        //diagonales
        if((tablero[0]==1)&&(tablero[4]==1)&&(tablero[8]==1)){
            return true;
        }
        if((tablero[2]==1)&&(tablero[4]==1)&&(tablero[6]==1)){
            return true;
        }
        return false;
    }
    public boolean GanaJugador2(){
        //horizontales
        if((tablero[0]==2)&&(tablero[1]==2)&&(tablero[2]==2)){
            return true;
        }
        if((tablero[3]==2)&&(tablero[4]==2)&&(tablero[5]==2)){
            return true;
        }
        if((tablero[6]==2)&&(tablero[7]==2)&&(tablero[8]==2)){
            return true;
        }
        //verticales
        if((tablero[0]==2)&&(tablero[3]==2)&&(tablero[6]==2)){
            return true;
        }
        if((tablero[1]==2)&&(tablero[4]==2)&&(tablero[7]==2)){
            return true;
        }
        if((tablero[2]==2)&&(tablero[5]==2)&&(tablero[8]==2)){
            return true;
        }
        //diagonales
        if((tablero[0]==2)&&(tablero[4]==2)&&(tablero[8]==2)){
            return true;
        }
        if((tablero[2]==2)&&(tablero[4]==2)&&(tablero[6]==2)){
            return true;
        }
        return false;
    }

}
