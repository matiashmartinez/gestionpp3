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

/**
 *
 * @author Mati
 */
public class ClienteDB {

    public ClienteDB() {
    }

    public ObservableList<Cliente> buscarTodos() {
        System.out.println("buscartodos");
        try {
            String query = "SELECT idcliente,dni,apellido,nombre,telefono,email FROM cliente where baja=0 order by idcliente desc";
            JdbcHelper jdbc = new JdbcHelper();
            ResultSet rs = jdbc.realizarConsulta(query);

            ObservableList<Cliente> clientes = FXCollections.observableArrayList();

            try {
                while (rs.next()) {
                    Integer idCliente = rs.getInt("idcliente");
                    String dni = rs.getString("dni");
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    String telefono = rs.getString("telefono");
                    String email = rs.getString("email");

//                String razonSocial = rs.getString("razonSocial");
                    clientes.add(new Cliente(idCliente, dni, apellido, nombre,
                            telefono, email));

                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al buscar clientes: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            Conexion.getConnection().close();
            Conexion.cierraConexion();
            return clientes;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Integer traerUltimoIdCliente() {

        Cliente clientes = new Cliente(0);
        String query = "SELECT  idcliente  "
                + " FROM cliente "
                + " ORDER by idcliente DESC LIMIT 1";
        JdbcHelper jdbc = new JdbcHelper();
        ResultSet rs = jdbc.realizarConsulta(query);
        try {
            while (rs.next()) {
                clientes.setIdCliente(rs.getInt("idcliente"));

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar clientes: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        try {
            Conexion.getConnection().close();
        } catch (SQLException ex) {
            System.err.print("Ha ocurrido un error al cerrar conexión con la base de datos");
        }
        Conexion.cierraConexion();
        return clientes.getIdCliente();
    }

    public boolean insertarCliente(Cliente c) {
        String query = "INSERT INTO cliente (idcliente,dni, apellido, nombre, "
                + "telefono, email) "
                + "VALUES ('"
                + c.getIdCliente() + "', '"
                + c.getDni() + "', '"
                + c.getApellido() + "',' "
                + c.getNombre() + "',' "
                + c.getTelefono() + "',' "
                + c.getEmail() + "'"
                + ")";

        JdbcHelper jdbc = new JdbcHelper();
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;
    }

    public boolean editarCliente(Cliente cliente) {
        System.out.println("Editando cliente bd");

        JdbcHelper jdbc = new JdbcHelper();

        int id = cliente.getIdCliente();
        String dni = cliente.getDni();
        String nombre = cliente.getNombre();
        String apellido = cliente.getApellido();
        String telefono = cliente.getTelefono();
        String email = cliente.getEmail();

        String query = "UPDATE cliente SET "
                + "dni='" + dni + "'"
                + ",nombre='" + nombre + "'"
                + ",apellido='" + apellido + "'"
                + ",telefono='" + telefono + "'"
                + ",email='" + email + " " + "'"
                + "WHERE idcliente=" + id
                + ";";

        boolean exito = jdbc.ejecutarQuery(query);
        Conexion.cierraConexion();
        return exito;

    }

    public boolean bajaCliente(Cliente cliente) {
        System.out.println("Editando cliente bd" + cliente.getIdCliente());

        JdbcHelper jdbc = new JdbcHelper();

        int id = cliente.getIdCliente();

        String query = "UPDATE cliente SET baja=1 "
                + " WHERE idcliente=" + id
                + ";";

        boolean exito = jdbc.ejecutarQuery(query);
        Conexion.cierraConexion();
        return exito;

    }

    public Cliente buscarClientePorId(int idCliente) {

        try {
            String query = "SELECT * FROM cliente where idCliente =  " + idCliente;
            JdbcHelper jdbc = new JdbcHelper();
            ResultSet rs = jdbc.realizarConsulta(query);

            while (rs.next()) {
                if (idCliente == rs.getInt("idCliente")) {
                    System.out.println("Validación de id correcta desde la bd");

                    int id = rs.getInt("idCliente");
                    String dni = rs.getString("dni");
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");

                    String telefono = rs.getString("telefono");
                    String email = rs.getString("email");

                    Cliente cliente = new Cliente(id, dni, nombre, apellido,
                            telefono, email);

                    System.out.println("Conexion cerrada");

                    Conexion.cierraConexion();
                    return cliente;
                }

            }
        } catch (SQLException ex) {
            ex.getMessage();
            System.out.println("Error al procesar con la bd");
        }
        return null;

    }

    public boolean eliminarLogin(int idCliente) {

        JdbcHelper jdbc = new JdbcHelper();

        String query = "UPDATE cliente SET baja=1 where idCliente = "
                + idCliente;

        boolean exito = jdbc.ejecutarQuery(query);
        Conexion.cierraConexion();
        return exito;

    }

}
