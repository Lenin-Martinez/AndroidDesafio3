package com.example.me180102_mh180095.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.me180102_mh180095.CompraActivity;
import com.example.me180102_mh180095.R;
import com.example.me180102_mh180095.basededatos.DBHelper;
import com.example.me180102_mh180095.modelos.Compras;

import java.util.List;

public class ListaComprasAdaptador extends BaseAdapter {
    private Context context;
    private List<Compras> lista_compras;
    private DBHelper dbHelper;

    public ListaComprasAdaptador(Context context, List<Compras> lista_compras) {
        this.context = context;
        this.lista_compras = lista_compras;
    }

    @Override
    public int getCount() {
        return lista_compras.size();
    }

    @Override
    public Object getItem(int posicion) {
        return lista_compras.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return lista_compras.get(posicion).getId();
    }

    @Override
    public View getView(int posicion, View convertView, ViewGroup parent) {
        View vista = View.inflate(context, R.layout.compras_lista, null);
        TextView lbl_nombre = (TextView)vista.findViewById(R.id.lbl_nombre);
        TableLayout table_compra = (TableLayout) vista.findViewById(R.id.table_compra);

        lbl_nombre.setText(String.valueOf(lista_compras.get(posicion).getNombre()));
        table_compra.setId(lista_compras.get(posicion).getId());

        dbHelper = new DBHelper(context);
        table_compra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Compras compra = dbHelper.obtener_compra(table_compra.getId());

                Intent intent = new Intent(context, CompraActivity.class);
                intent.putExtra("id", table_compra.getId());
                intent.putExtra("nombre", compra.getNombre());
                intent.putExtra("fecha", compra.getFecha());
                intent.putExtra("objeto1", compra.getObjeto1());
                intent.putExtra("objeto2", compra.getObjeto2());
                intent.putExtra("objeto3", compra.getObjeto3());
                intent.putExtra("objeto4", compra.getObjeto4());
                intent.putExtra("objeto5", compra.getObjeto5());
                context.startActivity(intent);

            }
        });

        return vista;
    }
}

