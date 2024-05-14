package com.example.me180102_mh180095;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.me180102_mh180095.adaptadores.ListaComprasAdaptador;
import com.example.me180102_mh180095.basededatos.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.me180102_mh180095.modelos.Compras;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private ListaComprasAdaptador comprasAdaptador;
    private List<Compras> lista_compras;
    private FloatingActionButton agregar_compra;
    private ListView lista_nombres;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        agregar_compra = (FloatingActionButton) findViewById(R.id.btn_agregar);
        lista_nombres = (ListView) findViewById(R.id.lista_compras);
        dbHelper = new DBHelper(this);

        File database = getApplicationContext().getDatabasePath(DBHelper.DBNOMBRE);
        if(false == database.exists()) {
            dbHelper.getReadableDatabase();
            if(copyDatabase(this)) {
                Toast.makeText(this, "Copiado de base de datos correcto", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error copiado de base de datos", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        lista_compras = dbHelper.obtener_compras();
        comprasAdaptador = new ListaComprasAdaptador(this, lista_compras);
        lista_nombres.setAdapter(comprasAdaptador);

        agregar_compra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();

        if (intent != null) {
            // Extraer los datos del Intent, por ejemplo:
            String datoExtra = intent.getStringExtra("datoExtra");
            // Hacer algo con los datos recibidos
        }

    }

    private boolean copyDatabase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(dbHelper.DBNOMBRE);
            String outFileName = dbHelper.DBUBICACION + dbHelper.DBNOMBRE;
            Log.e("path", outFileName);
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity","Copia de Base de Datos");
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}