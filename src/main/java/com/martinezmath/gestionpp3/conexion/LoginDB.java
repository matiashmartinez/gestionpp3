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
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

/**
 *
 * @author Admin
 */
public class LoginDB {

    //recibe y verifica si es login correcto, login traido del controlador login, los cuales son obtenidos de los txtFields
public boolean validarLoginDB(Usuario usuarioIngresado) {
        
        System.out.println("Intentando login con Hibernate para: " + usuarioIngresado.getUsername());
        
        // 1. Pedimos una "sesión" a nuestra fábrica
        EntityManager em = (EntityManager) Conexion.getEntityManager();
        
        try {
            // 2. Consulta JPQL. ¡Ojo aquí! Le consultamos a la CLASE Usuario, no a la tabla.
            String jpql = "SELECT u FROM Usuario u WHERE u.username = :user AND u.password = :pass AND u.baja = 0";
            
            TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
            query.setParameter("user", usuarioIngresado.getUsername().trim());
            query.setParameter("pass", usuarioIngresado.getPassword().trim());

            // 3. Ejecutamos. getSingleResult() trae un objeto completo o lanza excepción si no hay coincidencias
            Usuario usuarioEncontrado = query.getSingleResult();
            
            System.out.println("¡Acceso concedido a través de ORM! Rol: " + usuarioEncontrado.getRol());
            return true;

        } catch (NoResultException e) {
            // Esta excepción es nativa de Hibernate: se dispara si la contraseña o el usuario están mal
            System.out.println("Login incorrecto: Credenciales inválidas o usuario inactivo.");
            return false;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error en el servidor de base de datos: " + ex.getMessage());
            return false;
            
        } finally {
            // 4. SIEMPRE cerrar el EntityManager para liberar memoria
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

//    public boolean insertarLogin(Login l) {
//        String query = "INSERT INTO login (usuario, password, tipo "
//                + ") "
//                + "VALUES ('"
//                + l.getUsuario() + "', '"
//                + l.getPassword() + "',' "
//                + l.getTipo() + "',' "
//                + ")";
//
//        JdbcHelper jdbc = new JdbcHelper();
//        boolean exito = jdbc.ejecutarQuery(query);
//        return exito;
//    }
//
//    public boolean editarLogin(Login login) {
//
//        JdbcHelper jdbc = new JdbcHelper();
//
//        int id = login.getIdLogin();
//        String usuario = login.getUsuario();
//        String password = login.getPassword();
//        String tipo = login.getTipo();
//
//        String query = "UPDATE login SET "
//                + "usuario='" + usuario + "'"
//                + ",password='" + password + "'"
//                + ",tipo='" + tipo + "'"
//                + "WHERE idLogin=" + id
//                + ";";
//
//        boolean exito = jdbc.ejecutarQuery(query);
//
//        return exito;
//
//    }
//
//    public Login buscarLoginPorId(int idLogin) {
//
//        try {
//            String query = "SELECT * FROM login where idLogin =  " + idLogin;
//            JdbcHelper jdbc = new JdbcHelper();
//            ResultSet rs = jdbc.realizarConsulta(query);
//
//            while (rs.next()) {
//                if (idLogin == rs.getInt("idLogin")) {
//
//                    int id = rs.getInt("idLogin");
//                    String usuario = rs.getString("usuario");
//                    String password = rs.getString("password");
//                    String tipo = rs.getString("tipo");
//
////                    int baja = rs.getInt("baja");
//                    Login login = new Login(id, usuario, password, tipo
//                    );
//
//                    System.out.println("Conexion cerrada");
//                    rs.close();
//
//                    return login;
//                }
//
//            }
//        } catch (SQLException ex) {
//            ex.getMessage();
//            System.out.println("Error al procesar con la bd");
//        }
//        return null;
//
//    }
//
//    public boolean eliminarLogin(int idLogin) {
//
//        JdbcHelper jdbc = new JdbcHelper();
//
//        String query = "UPDATE login SET baja=1 where idLogin = "
//                + idLogin;
//
//        boolean exito = jdbc.ejecutarQuery(query);
//
//        return exito;
//
//    }

}
