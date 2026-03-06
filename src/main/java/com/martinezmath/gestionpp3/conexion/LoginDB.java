/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.martinezmath.gestionpp3.conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import pp3.st.modelo.Login;
import com.martinezmath.gestionpp3.modelo.Usuario;

/**
 *
 * @author Admin
 */
public class LoginDB {

    //recibe y verifica si es login correcto, login traido del controlador login, los cuales son obtenidos de los txtFields
    public boolean validarLoginDB(Usuario usuario) {

        String username = usuario.getUsername();
        String password = usuario.getPassword();

        System.out.println("Username traido: " + username + "\n");
        System.out.println("Password sin cifrar: " + usuario.getPassword());
        System.out.println("Password cifrado: " + password);

        String query = "SELECT username, password, baja FROM usuario WHERE baja = 0 AND username =?";
        JdbcHelper2 jdbc = new JdbcHelper2();
        ResultSet rs = null;
        try {
            PreparedStatement statement = jdbc.getConnection().prepareStatement(query);
            statement.setString(1, usuario.getUsername());
            rs = statement.executeQuery();

            while (rs.next()) {
                username = rs.getString("username");
                password = rs.getString("password");
                boolean baja = rs.getBoolean("baja");
                System.out.println("valor username: " + username);
                System.out.println("valor password: " + password);
                if (username.equalsIgnoreCase(usuario.getUsername().trim())
                        && password.equals(usuario.getPassword().trim())) {
                    System.out.println("Correcto validar login");
                    return true;
                } else {
                    System.out.println("Login incorrecto");
                    return false;
                }

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar logins: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            jdbc.closeResultSet(rs);
            jdbc.closeConnection();
        }

        return false;

    }

    public boolean insertarLogin(Login l) {
        String query = "INSERT INTO login (usuario, password, tipo "
                + ") "
                + "VALUES ('"
                + l.getUsuario() + "', '"
                + l.getPassword() + "',' "
                + l.getTipo() + "',' "
                + ")";

        JdbcHelper jdbc = new JdbcHelper();
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;
    }

    public boolean editarLogin(Login login) {

        JdbcHelper jdbc = new JdbcHelper();

        int id = login.getIdLogin();
        String usuario = login.getUsuario();
        String password = login.getPassword();
        String tipo = login.getTipo();

        String query = "UPDATE login SET "
                + "usuario='" + usuario + "'"
                + ",password='" + password + "'"
                + ",tipo='" + tipo + "'"
                + "WHERE idLogin=" + id
                + ";";

        boolean exito = jdbc.ejecutarQuery(query);

        return exito;

    }

    public Login buscarLoginPorId(int idLogin) {

        try {
            String query = "SELECT * FROM login where idLogin =  " + idLogin;
            JdbcHelper jdbc = new JdbcHelper();
            ResultSet rs = jdbc.realizarConsulta(query);

            while (rs.next()) {
                if (idLogin == rs.getInt("idLogin")) {

                    int id = rs.getInt("idLogin");
                    String usuario = rs.getString("usuario");
                    String password = rs.getString("password");
                    String tipo = rs.getString("tipo");

//                    int baja = rs.getInt("baja");
                    Login login = new Login(id, usuario, password, tipo
                    );

                    System.out.println("Conexion cerrada");
                    rs.close();

                    return login;
                }

            }
        } catch (SQLException ex) {
            ex.getMessage();
            System.out.println("Error al procesar con la bd");
        }
        return null;

    }

    public boolean eliminarLogin(int idLogin) {

        JdbcHelper jdbc = new JdbcHelper();

        String query = "UPDATE login SET baja=1 where idLogin = "
                + idLogin;

        boolean exito = jdbc.ejecutarQuery(query);

        return exito;

    }

}
