//package com.martinezmath.gestionpp3.conexion;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import javax.swing.JOptionPane;
//
//public class JdbcHelper {
//
//    // Para leer (SELECT) - Devuelve el ResultSet
//    public ResultSet realizarConsulta(String query) {
//        ResultSet rs = null;
//        try {
//            Connection conn = Conexion.getConnection();
//            if (conn != null) {
//                Statement stQuery = conn.createStatement();
//                rs = stQuery.executeQuery(query);
//            }
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Error SQL al leer: " + ex.getMessage());
//        }
//        return rs;
//    }
//
//    // Para insert, update y delete
//    public boolean ejecutarQuery(String query) {
//        boolean exito = false;
//        try {
//            Connection conn = Conexion.getConnection();
//            if (conn != null) {
//                PreparedStatement ps = conn.prepareStatement(query);
//                int affectedRows = ps.executeUpdate();
//                if (affectedRows > 0) {
//                    exito = true;
//                }
//                System.out.println("Filas afectadas: " + affectedRows);
//            }
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Error SQL al escribir: " + ex.getMessage());
//            exito = false;
//        } 
//        return exito;
//    }
//}