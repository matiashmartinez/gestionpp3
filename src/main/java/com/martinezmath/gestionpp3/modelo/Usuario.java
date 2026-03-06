
package com.martinezmath.gestionpp3.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Mati
 */
public class Usuario {

    private IntegerProperty idUsuario;
    private StringProperty username;
    private StringProperty password;
    private StringProperty rol;
    private StringProperty email;

    public Usuario(int idUsuario, String username, String password,
            String rol, String email) {
        this.idUsuario = new SimpleIntegerProperty(idUsuario);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.rol = new SimpleStringProperty(rol);
        this.email = new SimpleStringProperty(email);

    }

    public Usuario(String username, String password) {
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
    }

    //Metodos atributo: idUsuario
    public int getIdUsuario() {
        return idUsuario.get();
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = new SimpleIntegerProperty(idUsuario);
    }

    public IntegerProperty IdUsuarioProperty() {
        return idUsuario;
    }
    //Metodos atributo: username

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username = new SimpleStringProperty(username);
    }

    public StringProperty UsernameProperty() {
        return username;
    }
    //Metodos atributo: password

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password = new SimpleStringProperty(password);
    }

    public StringProperty PasswordProperty() {
        return password;
    }
    //Metodos atributo: rol

    public String getRol() {
        return rol.get();
    }

    public void setRol(String rol) {
        this.rol = new SimpleStringProperty(rol);
    }

    public StringProperty RolProperty() {
        return rol;
    }

    //Metodos atributo: rol
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
        return "Usuario{" + "idUsuario=" + idUsuario + ", username=" + username + ", password=" + password
                + ", rol=" + rol + " ,email= " + email + '}';
    }

}
