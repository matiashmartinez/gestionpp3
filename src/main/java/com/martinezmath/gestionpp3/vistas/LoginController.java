/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.martinezmath.gestionpp3.vistas;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.martinezmath.gestionpp3.Main;
import com.martinezmath.gestionpp3.conexion.LoginDB;
import com.martinezmath.gestionpp3.modelo.Usuario;
import com.martinezmath.gestionpp3.utils.Cifrado;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class LoginController implements Initializable {

    @FXML
    private TextField tfUsuario;
    @FXML
    private PasswordField tfPassword;

    private Main main;
    LoginDB ldb = new LoginDB();
    Usuario usuario = new Usuario(null, null);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void onIngresar(ActionEvent event) {

        if (tfUsuario.getText().isEmpty() || tfPassword.getText().isEmpty()) {
            System.out.println("Validacion incorrecta");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Usuario y/o contraseña inválidos");
            alert.setHeaderText("Por favor digite su usuario y contraseña");
            alert.setContentText("Acceso no autorizado");

            alert.showAndWait();
        } else {
            Usuario u = new Usuario(null, null);
            u.setPassword(Cifrado.md5(tfPassword.getText()));
            u.setUsername(tfUsuario.getText());
            if (ldb.validarLoginDB(u)) {
                try {
                    System.out.println("Usuario onIngresar:" + u.toString());

                    System.out.println("Validacion correcta recibida");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);

                    alert.setTitle("Ingreso");
                    alert.setHeaderText("Ingresando al sistema");
                    alert.setContentText("Acceso autorizado");

                    alert.showAndWait();

                    main.verVistaPrincipal();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("Login incorrecto");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Usuario y/o contraseña inválidos");
                alert.setHeaderText("El usuario y/o contraseña ingresados son incorrectos");
                alert.setContentText("Acceso no autorizado");

                alert.showAndWait();
            }
        }
    }

    private void onCancelar(ActionEvent event) {
        try {
            main.stop();

        } catch (Exception ex) {
            Logger.getLogger(LoginController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setMain(Main main) {
        this.main = main;
    }

}
