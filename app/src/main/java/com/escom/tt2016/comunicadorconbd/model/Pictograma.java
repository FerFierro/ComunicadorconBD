package com.escom.tt2016.comunicadorconbd.model;

/**
 * creamos una clase llamada Pictograma que sera el modelo que representara
 * la tabla Pictograma de la base de datos.
 * */
public class Pictograma {
    public int id;
    public String nombre;
    public int categoria;
    public int idDrawable;

    public Pictograma(String nombre, int categoria, int idDrawable) {

        this.nombre = nombre;
        this.categoria = categoria;
        this.idDrawable = idDrawable;
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

    @Override
    public String toString() {
        return nombre;
    }

}