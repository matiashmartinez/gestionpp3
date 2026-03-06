package com.martinezmath.gestionpp3.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "estado")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idestado")
    private Integer idEstado;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "baja")
    private Integer baja = 0;

    public Estado() {}

    public Estado(String descripcion) {
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Integer getIdEstado() { return idEstado; }
    public void setIdEstado(Integer idEstado) { this.idEstado = idEstado; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Integer getBaja() { return baja; }
    public void setBaja(Integer baja) { this.baja = baja; }

    @Override
    public String toString() { return descripcion; }
}