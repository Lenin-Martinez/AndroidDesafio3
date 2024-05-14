package com.example.me180102_mh180095;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.me180102_mh180095.basededatos.DBHelper;
import com.example.me180102_mh180095.modelos.Compras;

public class InfoActivity extends AppCompatActivity {

    private EditText txt_nombre, txt_fecha, txt_objeto1, txt_objeto2, txt_objeto3, txt_objeto4, txt_objeto5;
    private ImageButton btn_cancelar, btn_guardar;
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();

        txt_nombre = (EditText) findViewById(R.id.txt_nombre);
        txt_nombre.setId(intent.getIntExtra("id",-1));
        txt_fecha = (EditText) findViewById(R.id.txt_fecha);
        txt_objeto1 = (EditText) findViewById(R.id.txt_objeto1);
        txt_objeto2 = (EditText) findViewById(R.id.txt_objeto2);
        txt_objeto3 = (EditText) findViewById(R.id.txt_objeto3);
        txt_objeto4 = (EditText) findViewById(R.id.txt_objeto4);
        txt_objeto5 = (EditText) findViewById(R.id.txt_objeto5);


        btn_cancelar = (ImageButton) findViewById(R.id.btn_cancelar);
        btn_guardar = (ImageButton) findViewById(R.id.btn_guardar);
        dbHelper = new DBHelper(this);

        if (!intentIsEmpty(intent)) {
            txt_nombre.setText(intent.getStringExtra("nombre"));
            txt_fecha.setText(intent.getStringExtra("fecha"));
            txt_objeto1.setText(intent.getStringExtra("objeto1"));
            txt_objeto2.setText(intent.getStringExtra("objeto2"));
            txt_objeto3.setText(intent.getStringExtra("objeto3"));
            txt_objeto4.setText(intent.getStringExtra("objeto4"));
            txt_objeto5.setText(intent.getStringExtra("objeto5"));
        }

        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (intentIsEmpty(intent)) {
                    try {
                        dbHelper.agregar_compra(new Compras(txt_nombre.getText().toString().trim(),
                                txt_fecha.getText().toString().trim(),
                                txt_objeto1.getText().toString().trim(),
                                txt_objeto2.getText().toString().trim(),
                                txt_objeto3.getText().toString().trim(),
                                txt_objeto4.getText().toString().trim(),
                                txt_objeto5.getText().toString().trim()
                        ));

                        Toast.makeText(getApplicationContext(), "Compra agregada a la agenda", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(), "Error al agregar compra", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    try {
                        dbHelper.editar_compra(new Compras(txt_nombre.getText().toString().trim(),
                                txt_fecha.getText().toString().trim(),
                                txt_objeto1.getText().toString().trim(),
                                txt_objeto2.getText().toString().trim(),
                                txt_objeto3.getText().toString().trim(),
                                txt_objeto4.getText().toString().trim(),
                                txt_objeto5.getText().toString().trim()),
                                txt_nombre.getId());

                        Toast.makeText(getApplicationContext(), "Compra actualizada con exito", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Error al actualizar compra", Toast.LENGTH_SHORT).show();
                    }
                }

                Intent intent = new Intent(InfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean intentIsEmpty(Intent intent) {
        if (intent != null && intent.hasExtra("id")) {
            return false;
        } else {
            return true;
        }
    }

}