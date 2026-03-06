
package com.martinezmath.gestionpp3.conexion;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import com.martinezmath.gestionpp3.modelo.Cliente;
import com.martinezmath.gestionpp3.modelo.Estado;
import com.martinezmath.gestionpp3.modelo.Servicio;
import com.martinezmath.gestionpp3.modelo.TipoServicio;

/**
 *
 * @author Matyas
 */
public class ServicioDB {

    //PESTAÑA SOLICITUD
    public Integer traerUltimoIdServicio() {

        Servicio s = new Servicio();
        String query = "SELECT  idservicio  "
                + " FROM servicio "
                + " ORDER by idservicio DESC LIMIT 1";
        JdbcHelper jdbc = new JdbcHelper();
        ResultSet rs = jdbc.realizarConsulta(query);
        try {
            while (rs.next()) {
                s.setIdservicio(rs.getInt("idservicio"));

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar ultimo idservicio: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        try {
            Conexion.getConnection().close();
        } catch (SQLException ex) {
            System.err.print("Ha ocurrido un error al cerrar conexión con la base de datos");
        }
        Conexion.cierraConexion();

        return s.getIdservicio();

    }

    public ObservableList<Servicio> buscarTodos(Cliente c) {
        System.out.println("buscartodos Servicio");
        try {

            String query = "select s.idservicio,s.fecha,s.fechaRecibido,s.costo,s.detalle,s.numFactura,s.tipoProducto,s.baja,\n"
                    + "c.idcliente,c.dni,c.apellido,c.nombre,c.telefono,\n"
                    + "c.email,ts.idtipoServicio,ts.descripcion as tipo,e.idestado,e.descripcion as Estado from servicio s \n"
                    + "join cliente c on s.idCliente = c.idcliente \n"
                    + "join tiposervicio ts on ts.idtipoServicio=s.idTipoServicio\n"
                    + "join estado e on e.idestado = s.idEstado\n"
                    + "join usuario u on u.idusuario = s.idUsuario\n"
                    + "having c.dni=" + c.getDni() + " AND s.baja=0";

            JdbcHelper jdbc = new JdbcHelper();
            ResultSet rs = jdbc.realizarConsulta(query);

            ObservableList<Servicio> servicios = FXCollections.observableArrayList();

            try {
                while (rs.next()) {
                    Integer idServicio = rs.getInt("idservicio");

                    Date fecha = rs.getDate("fecha");
                    Date fechaRec = rs.getDate("fechaRecibido");
                    String costo = rs.getString("costo");
                    String detalle = rs.getString("detalle");
                    String numFactura = rs.getString("numFactura");
                    String tipoProducto = rs.getString("tipoProducto");
                    int baja = rs.getInt("baja");

                    int idCliente = rs.getInt("idcliente");
                    String dni = rs.getString("dni");
                    String apellido = rs.getString("apellido");
                    String nombre = rs.getString("nombre");
                    String telefono = rs.getString("telefono");
                    String email = rs.getString("email");
                    int idTS = rs.getInt("idtipoServicio");
                    String tsDescripcion = rs.getString("tipo");
                    int idEstado = rs.getInt("idestado");
                    String estado = rs.getString("estado");
                    System.out.println("Fecha:" + fecha.toLocalDate());
                    servicios.add(new Servicio(idServicio, fecha.toLocalDate(), fechaRec.toLocalDate(), detalle, costo, numFactura, tipoProducto,
                            new TipoServicio(idTS, tsDescripcion),
                            new Estado(idEstado, estado),
                            new Cliente(idCliente, dni, apellido, nombre, telefono, email)));

                }

                System.out.println("Servicio:" + servicios.toString());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al buscar clientes: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            Conexion.getConnection().close();
            Conexion.cierraConexion();
            return servicios;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean insertarServicio(Servicio s) {
        String query = "INSERT INTO `servicio`(`idservicio`, `idTipoServicio`, "
                + "`fecha`,fechaRecibido, `detalle`, `costo`, `numFactura`,`tipoProducto`, `idEstado`, "
                + "`idCliente`, `idUsuario`, `baja`) "
                + "VALUES (" + s.getIdservicio()
                + "," + s.getTipoServicio().getIdTipoServicio() + ",'"
                + s.getFechaServicio() + "',"
                + "curdate(),'"
                + s.getDetalle() + "','"
                + s.getCosto() + "','"
                + s.getNumFactura() + "','"
                +s.getTipoProducto() + "',"
                + s.getEstado().getIdEstado() + ","
                + s.getCliente().getIdCliente() + ","
                + "1,0)";

        JdbcHelper jdbc = new JdbcHelper();
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;
    }

    
    //TAB 3 - Cambiar estados de pendiente a finalizado, de finalizado a entregado o viceversa
    public boolean editarServicioEstado(Servicio s, int estado) {
        String query = "UPDATE `servicio` SET `idEstado` = " + estado + " WHERE idservicio= " + s.getIdservicio();

        JdbcHelper jdbc = new JdbcHelper();
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;
    }

    //TAB 3 - Baja lógica
    public boolean eliminarServicioEstado(Servicio s) {
        String query = "DELETE from servicio" + " WHERE idservicio= " + s.getIdservicio();

        JdbcHelper jdbc = new JdbcHelper();
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;
    }

    public Servicio getServicioById(Servicio s) {
        System.out.println("buscartodos Servicio");
        try {

            String query = "select s.idservicio,s.fecha,s.fechaRecibido,s.costo,s.detalle,s.numFactura,s.tipoProducto,s.baja,\n"
                    + "c.idcliente,c.dni,c.apellido,c.nombre,c.telefono,\n"
                    + "c.email,ts.idtipoServicio,ts.descripcion as tipo,e.idestado,e.descripcion as Estado from servicio s \n"
                    + "join cliente c on s.idCliente = c.idcliente \n"
                    + "join tiposervicio ts on ts.idtipoServicio=s.idTipoServicio\n"
                    + "join estado e on e.idestado = s.idEstado\n"
                    + "join usuario u on u.idusuario = s.idUsuario\n"
                    + "having s.idservicio=" + s.getIdservicio() + " AND s.baja=0";

            JdbcHelper jdbc = new JdbcHelper();
            ResultSet rs = jdbc.realizarConsulta(query);

            try {
                while (rs.next()) {
                    Integer idServicio = rs.getInt("idservicio");

                    Date fecha = rs.getDate("fecha");
                    Date fechaRec = rs.getDate("fechaRecibido");
                    String costo = rs.getString("costo");
                    String detalle = rs.getString("detalle");
                    String numFactura = rs.getString("numFactura");
                    String tipoProducto = rs.getString("tipoProducto");
                    int baja = rs.getInt("baja");

                    int idCliente = rs.getInt("idcliente");
                    String dni = rs.getString("dni");
                    String apellido = rs.getString("apellido");
                    String nombre = rs.getString("nombre");
                    String telefono = rs.getString("telefono");
                    String email = rs.getString("email");
                    int idTS = rs.getInt("idtipoServicio");
                    String tsDescripcion = rs.getString("tipo");
                    int idEstado = rs.getInt("idestado");
                    String estado = rs.getString("estado");
                    System.out.println("Fecha:" + fecha.toLocalDate());
                    Servicio s2 = new Servicio(idServicio, fecha.toLocalDate(), 
                            fechaRec.toLocalDate(), detalle, costo, numFactura, tipoProducto,
                            new TipoServicio(idTS, tsDescripcion),
                            new Estado(idEstado, estado),
                            new Cliente(idCliente, dni, apellido, nombre, telefono, email));
                    System.out.println("Servicio:" + s2.toString());
                    return s2;
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al buscar clientes: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            Conexion.getConnection().close();
            Conexion.cierraConexion();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
