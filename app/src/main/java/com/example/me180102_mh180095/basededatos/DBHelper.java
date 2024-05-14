package com.example.me180102_mh180095.basededatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.me180102_mh180095.modelos.Compras;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNOMBRE = "Listasdb.db";
    private Context contexto;
    public static String DBUBICACION = null;

    private SQLiteDatabase comprasDB;

    public DBHelper(Context contexto) {
        super(contexto, DBNOMBRE, null, 1);
        this.DBUBICACION  = "/data/data/" + contexto.getPackageName() + "/databases/";
        this.contexto = contexto;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase() {
        String dbPath = contexto.getDatabasePath(DBNOMBRE).getPath();
        if(comprasDB != null && comprasDB.isOpen()) {
            return;
        }
        comprasDB = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if(comprasDB!=null) {
            comprasDB.close();
        }
    }

    public List<Compras> obtener_compras() {
        Compras compras = null;
        List<Compras> lista_compras = new ArrayList<>();
        openDatabase();
        Cursor cursor = comprasDB.rawQuery("SELECT * FROM Compras", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            compras = new Compras(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),

                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7)
            );
            lista_compras.add(compras);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return lista_compras;
    }

    public Compras obtener_compra(int id) {
        Compras compra = null;
        List<Compras> lista_compras = new ArrayList<>();
        openDatabase();
        Cursor cursor = comprasDB.rawQuery("SELECT * FROM Compras WHERE id=?", new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {
            // Creamos un objeto Compra utilizando los datos del cursor
            compra = new Compras(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),

                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7)
            );
        }

        cursor.close();
        closeDatabase();
        return compra;
    }

    // Método para agregar un nuevo registro
    public void agregar_compra(Compras compras) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues compra = new ContentValues();
        // Llena el ContentValues con los valores del nuevo registro
        compra.put("nombre", compras.getNombre());
        compra.put("fecha", compras.getFecha());
        compra.put("objeto1", compras.getObjeto1());
        compra.put("objeto2", compras.getObjeto2());
        compra.put("objeto3", compras.getObjeto3());
        compra.put("objeto4", compras.getObjeto4());
        compra.put("objeto5", compras.getObjeto5());
        // Inserta el registro en la tabla
        db.insert("Compras", null, compra);
        db.close();
    }

    // Método para editar un registro existente
    public void editar_compra(Compras compras, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues compra = new ContentValues();
        // Llena el ContentValues con los valores actualizados del registro
        compra.put("nombre", compras.getNombre());
        compra.put("fecha", compras.getFecha());
        compra.put("objeto1", compras.getObjeto1());
        compra.put("objeto2", compras.getObjeto2());
        compra.put("objeto3", compras.getObjeto3());
        compra.put("objeto4", compras.getObjeto4());
        compra.put("objeto5", compras.getObjeto5());
        // Actualiza el registro en la tabla
        db.update("Compras", compra, "id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    // Método para eliminar un registro
    public void eliminar_compra(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Elimina el registro de la tabla basado en la condición
        db.delete("Compras", "id=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
