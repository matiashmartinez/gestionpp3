/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.martinezmath.gestionpp3.conexion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Matyas
 */
public class DashboardDB {

    //idTipoServicio es igual a codigo  Tipo de Servicio, ID NO es autoincremental.
//    public static ArrayList getCantidad() {
//        ArrayList arr = new ArrayList();
//        ArrayList arr2 = new ArrayList();
//
//        arr.set(0, "SELECT COUNT(idTipoServicio) from servicio WHERE idTipoServicio=15");
//        arr.set(1, "SELECT COUNT(idTipoServicio) from servicio WHERE idTipoServicio=45");
//        for (int i = 0; i < arr.size(); i++) {
//            JdbcHelper jdbc = new JdbcHelper();
//            ResultSet rs = jdbc.realizarConsulta(arr.get(i).toString());
//            try {
//                while (rs.next()) {
//                    arr2
//                            .add(rs.getInt(1));
//
//                }
//
//            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, "Error al traer consulta: " + ex.getMessage(),
//                        "Error", JOptionPane.ERROR_MESSAGE);
//            }
//            try {
//                Conexion.getConnection().close();
//            } catch (SQLException ex) {
//                System.err.print("Ha ocurrido un error al cerrar conexión con la base de datos");
//            }
//            Conexion.cierraConexion();
//
//            return arr2;
//
//        }
//        return null;
//    }

}
