package com.martinezmath.gestionpp3.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "tiposervicio")
public class TipoServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtipoServicio")
    private Integer idTipoServicio;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "baja")
    private Integer baja = 0;

    public TipoServicio() {}

    public TipoServicio(String descripcion) {
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Integer getIdTipoServicio() { return idTipoServicio; }
    public void setIdTipoServicio(Integer idTipoServicio) { this.idTipoServicio = idTipoServicio; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Integer getBaja() { return baja; }
    public void setBaja(Integer baja) { this.baja = baja; }

    @Override
    public String toString() { return idTipoServicio + " | " + descripcion; }
}