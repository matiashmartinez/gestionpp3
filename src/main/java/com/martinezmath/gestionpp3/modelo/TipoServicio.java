/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.martinezmath.gestionpp3.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Matyas
 */
public class TipoServicio {

    private IntegerProperty idTipoServicio;
    private StringProperty descripcion;

    public TipoServicio() {
    }
    
    
    

    public TipoServicio(int idTipoServicio, String descripcion) {
        this.idTipoServicio = new SimpleIntegerProperty(idTipoServicio);
        this.descripcion = new SimpleStringProperty(descripcion);
    }

    public TipoServicio(String tsDescripcion) {
        this.descripcion = new SimpleStringProperty(tsDescripcion);
    }

    //Metodos atributo: idTipoServicio
    public int getIdTipoServicio() {
        return idTipoServicio.get();
    }

    public void setIdTipoServicio(int idTipoServicio) {
        this.idTipoServicio = new SimpleIntegerProperty(idTipoServicio);
    }

    public IntegerProperty IdTipoServicioProperty() {
        return idTipoServicio;
    }
    //Metodos atributo: descripcion

    public String getDescripcion() {
        return descripcion.get();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = new SimpleStringProperty(descripcion);
    }

    public StringProperty DescripcionProperty() {
        return descripcion;
    }

    @Override
    public String toString() {
        return  idTipoServicio.get() + " | " + descripcion.get();
    }

   
    
    
    
}
