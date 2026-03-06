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
public class Estado {

    private IntegerProperty idEstado;
    private StringProperty descripcion;

    public Estado() {
    }
    
    
    

    public Estado(int idEstado, String descripcion) {
        this.idEstado = new SimpleIntegerProperty(idEstado);
        this.descripcion = new SimpleStringProperty(descripcion);
    }

    public Estado(String descripcion) {
        this.descripcion =  new SimpleStringProperty(descripcion);
    }

    //Metodos atributo: idEstado
    public int getIdEstado() {
        return idEstado.get();
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = new SimpleIntegerProperty(idEstado);
    }

    public IntegerProperty IdEstadoProperty() {
        return idEstado;
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
        return  descripcion.get();
    }
    
    
    
}
