package com.example.controlinventario;

import java.io.Serializable;

public class Activo implements Serializable {
    private String id, nombre, estado, codBarras, idFuncionario, revision;

    public Activo(String id, String nombre, String estado, String codBarras, String idFuncionario, String revision) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.codBarras = codBarras;
        this.idFuncionario = idFuncionario;
        this.revision = revision;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public String getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(String idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getRevision() {        return revision;    }

    public void setRevision(String revision) {        this.revision = revision;    }
}
