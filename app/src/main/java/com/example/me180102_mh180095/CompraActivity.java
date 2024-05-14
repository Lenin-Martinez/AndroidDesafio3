package com.example.me180102_mh180095;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.me180102_mh180095.basededatos.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CompraActivity extends AppCompatActivity {

    private ImageButton btn_eliminar, btn_modificar;
    private TextView lbl_nombre, txt_fecha, txt_objeto1, txt_objeto2, txt_objeto3, txt_objeto4, txt_objeto5;
    private FloatingActionButton btn_atras;
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);

        btn_eliminar = (ImageButton) findViewById(R.id.btn_eliminar);
        btn_modificar = (ImageButton) findViewById(R.id.btn_editar);

        lbl_nombre = (TextView) findViewById(R.id.nombre_compra);
        txt_fecha = (TextView) findViewById(R.id.txt_fecha);
        txt_objeto1 = (TextView) findViewById(R.id.txt_objeto1);
        txt_objeto2 = (TextView) findViewById(R.id.txt_objeto2);
        txt_objeto3 = (TextView) findViewById(R.id.txt_objeto3);
        txt_objeto4 = (TextView) findViewById(R.id.txt_objeto4);
        txt_objeto5 = (TextView) findViewById(R.id.txt_objeto5);

        btn_atras = (FloatingActionButton) findViewById(R.id.btn_atras);
        dbHelper = new DBHelper(this);
        Intent intent = getIntent();

        lbl_nombre.setText(intent.getStringExtra("nombre"));
        lbl_nombre.setId(intent.getIntExtra("id", -1));

        txt_fecha.setText(intent.getStringExtra("fecha"));
        txt_objeto1.setText(intent.getStringExtra("objeto1"));
        txt_objeto2.setText(intent.getStringExtra("objeto2"));
        txt_objeto3.setText(intent.getStringExtra("objeto3"));
        txt_objeto4.setText(intent.getStringExtra("objeto4"));
        txt_objeto5.setText(intent.getStringExtra("objeto5"));


        btn_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompraActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogo1.setTitle("Importante");
                dialogo1.setMessage("¿ Desea eliminar esta lista de compras ?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        aceptar();
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        cancelar();
                    }
                });
                dialogo1.show();

            }
        });

        btn_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentModificar = new Intent(CompraActivity.this, InfoActivity.class);
                intentModificar.putExtra("id", lbl_nombre.getId());
                intentModificar.putExtra("nombre", intent.getStringExtra("nombre"));
                intentModificar.putExtra("fecha", intent.getStringExtra("fecha"));
                intentModificar.putExtra("objeto1", intent.getStringExtra("objeto1"));
                intentModificar.putExtra("objeto2", intent.getStringExtra("objeto2"));
                intentModificar.putExtra("objeto3", intent.getStringExtra("objeto3"));
                intentModificar.putExtra("objeto4", intent.getStringExtra("objeto4"));
                intentModificar.putExtra("objeto5", intent.getStringExtra("objeto5"));
                startActivity(intentModificar);
            }
        });

    }

    public void aceptar() {
        Intent intent = new Intent(CompraActivity.this, MainActivity.class);
        dbHelper.eliminar_compra(lbl_nombre.getId());
        startActivity(intent);

        Toast t=Toast.makeText(this,"Compra eliminada con exito", Toast.LENGTH_SHORT);
        t.show();
    }

    public void cancelar() {
        //finish();
    }

}
