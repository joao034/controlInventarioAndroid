package com.example.controlinventario;

import java.util.List;

public class ProcesoValidacionDetalle {

    private int id, procesoPertenece;
    private Funcionario funcionario;
    private ProcesoValidacion procesoValidacion;
    private String observacion, estado;

    public ProcesoValidacionDetalle() {
    }

    public ProcesoValidacionDetalle(int id, int procesoPertenece, Funcionario funcionario, String observacion, String estado) {
        this.id = id;
        this.procesoPertenece = procesoPertenece;
        this.funcionario = funcionario;
        this.observacion = observacion;
        this.estado = estado;
    }

    public ProcesoValidacionDetalle(int id, int procesoPertenece, Funcionario funcionario, ProcesoValidacion procesoValidacion, String observacion, String estado) {
        this.id = id;
        this.procesoPertenece = procesoPertenece;
        this.funcionario = funcionario;
        this.procesoValidacion = procesoValidacion;
        this.observacion = observacion;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProcesoPertenece() {
        return procesoPertenece;
    }

    public void setProcesoPertenece(int procesoPertenece) {
        this.procesoPertenece = procesoPertenece;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
