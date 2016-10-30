package com.escom.tt2016.comunicadorconbd.model;

/**
 * creamos una clase llamada Pictograma que sera el modelo que representara
 * la tabla Pictograma de la base de datos.
 * */
public class Pictograma {

    public static final int PIC_NORMAL=0;
    public static final  int PIC_SELECCIONADO=1;
    public int id;
    public String nombre;
    public int categoria;
    public int idDrawable;
    public int tipo;




    public Pictograma(String nombre, int categoria, int idDrawable,int tipo) {

        this.nombre = nombre;
        this.categoria = categoria;
        this.idDrawable = idDrawable;
        this.tipo=tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

    public void setIdDrawable(int idDrawable) {
        this.idDrawable = idDrawable;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    @Override
    public String toString() {
        return ("Nombre: "     +nombre+"\n" +
                "Categoria: "  +categoria+"\n"+
                "IdDrawable: " +idDrawable+"\n");
    }

}