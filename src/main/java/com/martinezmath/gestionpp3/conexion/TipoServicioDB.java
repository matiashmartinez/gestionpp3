/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.martinezmath.gestionpp3.conexion;

import com.martinezmath.gestionpp3.modelo.TipoServicio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;


/**
 *
 * @author Matyas
 */
public class TipoServicioDB {

    public ObservableList<TipoServicio> buscarTodos() {
        System.out.println("buscartodos TipoServicio");
        try {
            String query = "SELECT idtipoServicio, descripcion from tiposervicio";
            JdbcHelper jdbc = new JdbcHelper();
            ResultSet rs = jdbc.realizarConsulta(query);

            ObservableList<TipoServicio> listaTipoServicio = FXCollections.observableArrayList();

            try {
                while (rs.next()) {
                    Integer idTipoServicio = rs.getInt("idtipoServicio");
                    String descripcion = rs.getString("descripcion");

                    listaTipoServicio.add(new TipoServicio(idTipoServicio, descripcion));

                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al buscar tipos de servicios: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            Conexion.getConnection().close();
            Conexion.cierraConexion();
            return listaTipoServicio;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    

}
