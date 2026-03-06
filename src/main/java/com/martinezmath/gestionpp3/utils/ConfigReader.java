package com.martinezmath.gestionpp3.utils;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Mati
 */
public class ConfigReader {
    
    // Creamos las propiedades una sola vez en la memoria
    private static final Properties properties = new Properties();

    // El bloque 'static' carga el archivo automáticamente la primera vez que usas la clase
    static {
        // getResourceAsStream lee directamente desde la carpeta target/classes de Maven
        try (InputStream inputStream = ConfigReader.class.getResourceAsStream("/utils/config.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                System.err.println("Error: No se encontró el archivo en resources/utils/config.properties");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ¡ESTE ES EL MÉTODO QUE LE FALTABA A TU CLASE CONEXION!
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}