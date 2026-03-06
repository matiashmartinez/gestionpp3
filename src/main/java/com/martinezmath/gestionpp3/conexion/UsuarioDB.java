/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.martinezmath.gestionpp3.conexion;

import com.martinezmath.gestionpp3.modelo.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;


/**
 *
 * @author Mati
 */
public class UsuarioDB {

    public ObservableList<Usuario> buscarTodos() {
        System.out.println("buscartodos usuarios");
        try {
            String query = "SELECT * from usuario where baja=0";
            JdbcHelper jdbc = new JdbcHelper();
            ResultSet rs = jdbc.realizarConsulta(query);

            ObservableList<Usuario> usuarios = FXCollections.observableArrayList();

            try {
                while (rs.next()) {
                    Integer idUsuario = rs.getInt("idusuario");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String rol = rs.getString("rol");
                    String email = rs.getString("email");

                    Usuario user = new Usuario(idUsuario, username, password, rol, email);
                    usuarios.add(user);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al buscar usuarios: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            Conexion.getConnection().close();
            Conexion.cierraConexion();
            return usuarios;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean insertarUsuario(Usuario u) {
        String query = "INSERT INTO usuario (idusuario,username, password, rol,email, baja "
                + ") "
                + "VALUES ("
                + 0 + ", '"
                + u.getUsername() + "', '"
                + u.getPassword() + "',' "
                + u.getRol() + "','"
                + u.getEmail() + " ', "
                + 0 + " "
                + ")";

        JdbcHelper jdbc = new JdbcHelper();
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;
    }

    public boolean editarUsuario(Usuario usuario) {
        System.out.println("Editando usuario bd");

        JdbcHelper jdbc = new JdbcHelper();

        int id = usuario.getIdUsuario();
        String username = usuario.getUsername();
        String password = usuario.getPassword();
        String rol = usuario.getRol();
        String email = usuario.getEmail();

        String query = "UPDATE usuario SET "
                + "username=' " + username + " ' "
                + ",password=' " + password + " ' "
                + ",rol=' " + rol + "'"
                + ",email=' " + email + " ' "
                + " WHERE idcliente=" + id
                + ";";

        boolean exito = jdbc.ejecutarQuery(query);
        Conexion.cierraConexion();
        return exito;

    }

    public boolean bajaUsuario(Usuario usuario) {
        System.out.println("Editando usuario bd" + (usuario.getIdUsuario()));

        JdbcHelper jdbc = new JdbcHelper();

        int id = usuario.getIdUsuario();

        String query = "UPDATE usuario SET baja=1 "
                + " WHERE idusuario=" + id
                + ";";

        boolean exito = jdbc.ejecutarQuery(query);
        Conexion.cierraConexion();
        return exito;

    }
}
