package com.example.ejemplocomunicacionactiviti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button actividadSinRespuesta;
    private Button actividadConRespuesta;
    private Button actividadExplicita;
    private Intent mensajero;
    private EditText correo;
    private EditText numero;
    private TextView texto;
    public final String campoNumero ="numero";
    public final int requestCode= 0001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actividadSinRespuesta = findViewById(R.id.AcctividadSinRespuesta);
        actividadSinRespuesta.setOnClickListener(this);

        actividadConRespuesta = findViewById(R.id.acctividadConRespuesta);
        actividadConRespuesta.setOnClickListener(this);

        actividadExplicita = findViewById(R.id.actividadImplicita);
        actividadExplicita.setOnClickListener(this);

        numero = findViewById(R.id.Numero);
        correo = findViewById(R.id.correo);

        texto = findViewById(R.id.acctividadConRespuesta);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.AcctividadSinRespuesta:
                mensajero = new Intent(this, ActividadSinRespuesta.class);
                startActivity(mensajero);
                break;

            case R.id.acctividadConRespuesta:
                mensajero = new Intent(this,ActividadConRespuesta.class);

                //añadimos los datos extra que lleva
                mensajero.putExtra(campoNumero,Integer.parseInt(numero.getText().toString()));

                //Iniciamos la actividad y esperamos el resultado
                startActivityForResult(mensajero,requestCode);

                break;

            case R.id.actividadImplicita:
                mensajero = new Intent();
                mensajero.setAction(Intent.ACTION_SEND);
                String direccionCorreo= correo.getText().toString();

                //preparamos y añadimos la informacion
                mensajero.setData(Uri.parse("mailto:"));
                mensajero.putExtra(Intent.EXTRA_EMAIL,direccionCorreo);
                mensajero.putExtra(Intent.EXTRA_SUBJECT,"Este es el asunto");
                mensajero.putExtra(Intent.EXTRA_TEXT,"Este es el mensaje");
                mensajero.setType("message/rfc822");
                //Creamos el Choser
                Intent chooser=mensajero.createChooser(mensajero,"Escoje la Aplicacion");
                //lanzamos la acctividad
                startActivity(mensajero);
                Toast.makeText(this.getApplicationContext(),"Enviar Mail",
                        Toast.LENGTH_LONG).show();
                break;

        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //aqui filtramos los resultados que nos devuelvan los intens
        //y los codigo de resultado que nos regresen
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == requestCode) && (resultCode == RESULT_OK)) {

        }
    }

}