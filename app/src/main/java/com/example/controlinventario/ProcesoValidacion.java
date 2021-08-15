package com.example.controlinventario;

import java.io.Serializable;
import java.util.List;

public class ProcesoValidacion implements Serializable {
    private int id;
    private String titulo, fecha;
    List<ProcesoValidacionDetalle> detalles;

    public ProcesoValidacion(int id, String titulo, String fecha) {
        this.id = id;
        this.titulo = titulo;
        this.fecha = fecha;
    }

    public ProcesoValidacion(String titulo){
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return this.titulo;
    }
}
