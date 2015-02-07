package com.codigoandroide.guardardatos;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    EditText ETNombre;
    TextView TVTitulo;
    CheckBox CBRecordar;
    String Nombre;
    boolean recordar=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TVTitulo = (TextView)findViewById(R.id.TVTitulo);
        ETNombre = (EditText)findViewById(R.id.ETNombre);
        CBRecordar = (CheckBox)findViewById(R.id.CBRecordar);


        // Comprobamos si recordar es true
        SharedPreferences prefs = this.getSharedPreferences("MisDatos", Context.MODE_PRIVATE);
        recordar=prefs.getBoolean("Recordar", false);

        if (recordar) {
            Nombre=prefs.getString("Nombre", "nombre");
            TVTitulo.setText("Hola "+Nombre);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void btGuardar (View view) {
        Nombre=ETNombre.getText().toString();
        TVTitulo.setText("Hola "+Nombre);

        // Comprobamos si esta marcada la opcion de recordar datos
        if (CBRecordar.isChecked()) {
            recordar=true;
            //Si esta marcado guardamos los datos
            SharedPreferences prefs =
                    this.getSharedPreferences("MisDatos", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("Recordar", recordar);
            editor.putString("Nombre", Nombre);
            editor.commit();
        }
    }
}
