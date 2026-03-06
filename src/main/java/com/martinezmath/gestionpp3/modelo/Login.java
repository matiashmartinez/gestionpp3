/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pp3.st.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Admin
 */
public class Login {

    private IntegerProperty idLogin;
    private StringProperty usuario;
    private StringProperty password;
    private StringProperty tipo;

    public Login(String usuario, String password) {
        this.usuario = new SimpleStringProperty(usuario);
        this.password = new SimpleStringProperty(password);

    }

    public Login(int idLogin, String usuario, String password, String tipo) {
        this.idLogin = new SimpleIntegerProperty(idLogin);
        this.usuario = new SimpleStringProperty(usuario);
        this.password = new SimpleStringProperty(password);
        this.tipo = new SimpleStringProperty(tipo);
    }

    public Login() {

    }

    //Metodos atributo: idLogin
    public int getIdLogin() {
        return idLogin.get();
    }

    public void setIdLogin(int idLogin) {
        this.idLogin = new SimpleIntegerProperty(idLogin);
    }

    public IntegerProperty IdLoginProperty() {
        return idLogin;
    }
    //Metodos atributo: usuario

    public String getUsuario() {
        return usuario.get();
    }

    public void setUsuario(String usuario) {
        this.usuario = new SimpleStringProperty(usuario);
    }

    public StringProperty UsuarioProperty() {
        return usuario;
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

    //Metodos atributo: tipo
    public String getTipo() {
        return tipo.get();
    }

    public void setTipo(String tipo) {
        this.tipo = new SimpleStringProperty(tipo);
    }

    public StringProperty tipoProperty() {
        return tipo;
    }

}
