package com.example.me180102_mh180095.modelos;

public class Compras {

    private int id;
    private String nombre,fecha;
    private String objeto1,objeto2,objeto3,objeto4,objeto5;


    public Compras(int id, String nombre, String fecha, String objeto1, String objeto2, String objeto3, String objeto4, String objeto5) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.objeto1 = objeto1;
        this.objeto2 = objeto2;
        this.objeto3 = objeto3;
        this.objeto4 = objeto4;
        this.objeto5 = objeto5;
    }

    public Compras(String nombre, String fecha, String objeto1, String objeto2, String objeto3, String objeto4, String objeto5) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.objeto1 = objeto1;
        this.objeto2 = objeto2;
        this.objeto3 = objeto3;
        this.objeto4 = objeto4;
        this.objeto5 = objeto5;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //====================================================

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //====================================================

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    //====================================================

    public String getObjeto1() {  return objeto1; }

    public void setObjeto1(String objeto1) {
        this.objeto1 = objeto1;
    }

    //====================================================

    public String getObjeto2() {  return objeto2; }

    public void setObjeto2(String objeto2) {
        this.objeto2 = objeto2;
    }

    //====================================================

    public String getObjeto3() {  return objeto3; }

    public void setObjeto3(String objeto3) {
        this.objeto3 = objeto3;
    }

    //====================================================

    public String getObjeto4() {  return objeto4; }

    public void setObjeto4(String objeto4) {
        this.objeto4 = objeto4;
    }

    //====================================================

    public String getObjeto5() {  return objeto5; }

    public void setObjeto5(String objeto5) {
        this.objeto5 = objeto5;
    }
}

