package com.martinezmath.gestionpp3.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import com.martinezmath.gestionpp3.utils.ConfigReader;

public class JdbcHelper2 {

    private Connection connection;
    
    // Leemos directamente las propiedades usando el método estático correcto
    private String url = ConfigReader.getProperty("db.url");
    private String username = ConfigReader.getProperty("db.username");
    private String password = ConfigReader.getProperty("db.password");

    // Método principal para obtener la conexión
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            // Aseguramos que el driver esté cargado (buena práctica en Maven)
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.err.println("Driver MySQL no encontrado.");
            }
            // Establece la conexión con los datos del .properties
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }

    // para leer (SELECT)
    public ResultSet realizarConsulta(String query) {
        ResultSet rs = null;
        try {
            Connection conn = getConnection();
            Statement stQuery = conn.createStatement();
            rs = stQuery.executeQuery(query);
            
        } catch (SQLException ex) {
            mostrarAlerta("Error de Lectura", "Error al ejecutar consulta.", ex.getMessage());
        }
        return rs;
    }

    // para insert, update y delete
    public boolean ejecutarQuery(String query) {
        boolean exito = false;
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            int affectedRows = ps.executeUpdate();
            
            if (affectedRows != 0) {
                exito = true;
            }
            System.out.println("Affected rows: " + affectedRows);
            
        } catch (SQLException ex) {
            mostrarAlerta("Error de Escritura", "Error al ejecutar SQL.", query + "\n" + ex.getMessage());
            exito = false;
        } finally {
            // Cerramos la conexión para liberar recursos
            closeConnection();
        }
        return exito;
    }

    public void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar el ResultSet: " + ex.getMessage());
            }
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                    System.out.println("Conexion cerrada exitosamente.");
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    }
    
    // Alertas de JavaFX para reemplazar los viejos JOptionPane de Swing
    private void mostrarAlerta(String titulo, String encabezado, String contenido) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(titulo);
            alert.setHeaderText(encabezado);
            alert.setContentText(contenido);
            alert.showAndWait();
        });
    }
}