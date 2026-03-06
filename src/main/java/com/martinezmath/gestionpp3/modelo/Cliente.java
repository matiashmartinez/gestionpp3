package com.martinezmath.gestionpp3.modelo;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cliente {

    private IntegerProperty idCliente;
    private StringProperty dni;
    private StringProperty nombre;
    private StringProperty apellido;
    private StringProperty telefono;
    private StringProperty email;

    public Cliente() {
    }

    public Cliente(int idCliente, String dni, String apellido, String nombre) {
        this.idCliente = new SimpleIntegerProperty(idCliente);
        this.dni = new SimpleStringProperty(dni);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
    }

    public Cliente(Integer idCliente, String dni, String apellido, String nombre, String telefono,
            String email) {
        this.idCliente = new SimpleIntegerProperty(idCliente);
        this.dni = new SimpleStringProperty(dni);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.telefono = new SimpleStringProperty(telefono);
        this.email = new SimpleStringProperty(email);
    }

    public Cliente(Integer idCliente) {
        this.idCliente = new SimpleIntegerProperty(idCliente);

    }

//    Metodos atributo: idCliente
    public int getIdCliente() {
        return idCliente.get();
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = new SimpleIntegerProperty(idCliente);
    }

    public IntegerProperty IdClienteProperty() {
        return idCliente;
    }
    //Metodos atributo: dni

    public String getDni() {
        return dni.get();
    }

    public void setDni(String dni) {
        this.dni = new SimpleStringProperty(dni);
    }

    public StringProperty DniProperty() {
        return dni;
    }

    //Metodos atributo: nombre
    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre = new SimpleStringProperty(nombre);
    }

    public StringProperty NombreProperty() {
        return nombre;
    }

    //Metodos atributo: apellido
    public String getApellido() {
        return apellido.get();
    }

    public void setApellido(String apellido) {
        this.apellido = new SimpleStringProperty(apellido);
    }

    public StringProperty ApellidoProperty() {
        return apellido;
    }

    //Metodos atributo: telefono
    public String getTelefono() {
        return telefono.get();
    }

    public void setTelefono(String telefono) {
        this.telefono = new SimpleStringProperty(telefono);
    }

    public StringProperty TelefonoProperty() {
        return telefono;
    }
    //Metodos atributo: email

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email = new SimpleStringProperty(email);
    }

    public StringProperty EmailProperty() {
        return email;
    }

    @Override
    public String toString() {
        return this.getIdCliente() + this.getApellido() + this.getNombre() + this.getDni();

    }

}
