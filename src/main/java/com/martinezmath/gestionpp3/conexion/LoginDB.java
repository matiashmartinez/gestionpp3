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

        String username = usuario.getUsername().trim();
        String password = usuario.getPassword().trim();

        System.out.println("Username traido: " + username);
        System.out.println("Password cifrado a comparar: " + password);

        // Usamos la tabla usuario y filtramos por username y baja=0 directamente en SQL
        String query = "SELECT username, password, baja FROM usuario WHERE username = ? AND baja = 0";
        
        ResultSet rs = null;
        PreparedStatement statement = null;
        
        try {
            // Obtenemos la conexión directamente de tu clase Conexion centralizada
            statement = Conexion.getConnection().prepareStatement(query);
            statement.setString(1, username);
            rs = statement.executeQuery();

            // Usamos if(rs.next()) porque el username es UNIQUE, solo esperamos 1 o 0 resultados
            if (rs.next()) {
                String dbUsername = rs.getString("username");
                String dbPassword = rs.getString("password");
                
                System.out.println("Valor username en DB: " + dbUsername);
                System.out.println("Valor password en DB: " + dbPassword);
                
                // Comparamos lo que ingresó el usuario con lo que hay en la base de datos
                if (dbUsername.equalsIgnoreCase(username) && dbPassword.equals(password)) {
                    System.out.println("Correcto validar login");
                    return true;
                } else {
                    System.out.println("Login incorrecto: La contraseña no coincide");
                    return false;
                }

            } else {
                System.out.println("Login incorrecto: El usuario no existe o está dado de baja");
            }
            
        } catch (SQLException ex) {
            // Imprime la traza completa en rojo en la consola (Outputs) de NetBeans
            ex.printStackTrace(); 
            JOptionPane.showMessageDialog(null, "Error SQL al conectar: " + ex.getMessage(), "Error BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Buenas prácticas: cerramos los recursos para no saturar MySQL
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                Conexion.cierraConexion();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
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
