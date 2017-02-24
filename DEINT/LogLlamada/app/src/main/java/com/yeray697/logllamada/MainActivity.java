package com.yeray697.logllamada;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList<Llamada> llamadas = getLlamadas();
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<Llamada>(this,android.R.layout.simple_list_item_1,llamadas));
    }

    private ArrayList<Llamada> getLlamadas() {
        String[] projection = new String[]{CallLog.Calls.TYPE, CallLog.Calls.NUMBER, CallLog.Calls.DURATION};
        Uri llamadasUri = CallLog.Calls.CONTENT_URI;
        ContentResolver cr = getContentResolver();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        Cursor cur = cr.query(llamadasUri, projection, null, null, null);
        ArrayList<Llamada> llamadas = new ArrayList<>();
        Llamada aux;
        if (cur.moveToFirst()){
            int tipo;
            String tipoLlamada = "";
            String duracion = "";
            String telefono = "";
            int colTipo = cur.getColumnIndex(CallLog.Calls.TYPE);
            int colTelefono = cur.getColumnIndex(CallLog.Calls.NUMBER);
            int colDuracion = cur.getColumnIndex(CallLog.Calls.DURATION);
            do{
                aux = new Llamada();
                tipo = cur.getInt(colTipo);
                telefono = cur.getString(colTelefono);
                duracion = cur.getString(colDuracion);
                if (tipo == CallLog.Calls.INCOMING_TYPE){
                    tipoLlamada = "ENTRADA";
                } else if(tipo == CallLog.Calls.OUTGOING_TYPE){
                    tipoLlamada = "SALIDA";
                } else if(tipo == CallLog.Calls.MISSED_TYPE){
                    tipoLlamada = "PERDIDA";
                }
                aux.setDuracion(duracion);
                aux.setNumero(telefono);
                aux.setTipo(tipoLlamada);
                llamadas.add(aux);
            } while (cur.moveToNext());
        }
        return llamadas;
    }


}
