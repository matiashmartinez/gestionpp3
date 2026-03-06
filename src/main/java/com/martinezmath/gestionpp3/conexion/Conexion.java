package com.martinezmath.gestionpp3.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import com.martinezmath.gestionpp3.utils.ConfigReader;

public class Conexion {

    private static Connection connection = null;

    // Patrón Singleton: Si la conexión no existe, la crea. Si ya existe, te la devuelve.
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                String url = ConfigReader.getProperty("db.url");
                String username = ConfigReader.getProperty("db.username");
                String password = ConfigReader.getProperty("db.password");

                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("¡Conexión a BD exitosa!");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("Error al conectar con la Base de Datos: " + ex.getMessage());
            ex.printStackTrace();
            
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de Base de Datos");
                alert.setHeaderText("Fallo al conectar con el servidor MySQL");
                alert.setContentText("Detalle: " + ex.getMessage());
                alert.showAndWait();
            });
        }
        return connection;
    }

    public static void cierraConexion() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null; // Reseteamos la variable
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException ex) {
            System.err.println("Error al cerrar conexión: " + ex.getMessage());
        }
    }
}