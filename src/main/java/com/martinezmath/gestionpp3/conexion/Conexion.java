package com.martinezmath.gestionpp3.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import com.martinezmath.gestionpp3.utils.ConfigReader;


// Asegúrate de importar tu ConfigReader


/**
 *
 * @author Mati
 */
public class Conexion {

    // Usando el método directo que creamos en ConfigReader
    private String url = ConfigReader.getProperty("db.url");
    private String username = ConfigReader.getProperty("db.username");
    private String password = ConfigReader.getProperty("db.password");

    public static Connection connection = null;

    public Conexion() {
    }

    public Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                connection = DriverManager.getConnection(this.url, this.username, this.password);
            } catch (SQLException ex) {
                System.getLogger(Conexion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            return connection;
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            
            // Usamos Platform.runLater por si la conexión se llama desde un hilo secundario
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de Base de Datos");
                alert.setHeaderText("Fallo al conectar con el servidor");
                alert.setContentText("Asegúrese de que el servidor MySQL esté encendido.\nError: " + ex.getMessage());
                alert.showAndWait();
            });
            
            // ELIMINADO el connection.close() aquí porque connection es nulo si falla.
            return null;
        }
    }

    public static void cierraConexion() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexion cerrada");
            }
        } catch (SQLException sqle) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, sqle);
        }
    }

    // Getters y Setters
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    
    public String getUsername() { return username; }
    public void setUser(String user) { this.username = user; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public static Connection getConnection() { return connection; }
}