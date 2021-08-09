package com.example.controlinventario;

import java.io.Serializable;

public class Funcionario implements Serializable {
    private String id, nombre, apellido;
    private int numActivos;

    public Funcionario(String id) {
        this.id = id;
    }

    public Funcionario(String id, String nombre, String apellido, int numActivos) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.numActivos = numActivos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getNumActivos() {
        return numActivos;
    }

    public void setNumActivos(int numActivos) {
        this.numActivos = numActivos;
    }

    @Override
    public String toString() {
        return nombre + ' ' + apellido ;
    }
}
