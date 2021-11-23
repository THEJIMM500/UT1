package com.example.ejemploservicios;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class guardaPalabras extends Service {
    @Nullable
    private BufferedWriter escritor;
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        File archivo = new File("palabras.txt");
        try {
            escritor= new BufferedWriter(new FileWriter(archivo));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int onStartCommand(Intent intent, int flags, int startId){

        Bundle paquete = intent.getExtras();
        String palabra = paquete.getString("palabra");

        try {
            escritor.write(palabra);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Queremos que el servicio continúe ejecutándose hasta que es explícitamente parado,
        // así que devolvemos sticky
        return START_STICKY;
    }

    public void onDestroy(){
        try {
            escritor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
