package com.martinezmath.gestionpp3.conexion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Conexion {
    
    private static EntityManagerFactory emf = null;

    // Inicializamos el Factory en un bloque estático con un truco para JavaFX
    static {
        try {
            // config especial por javafx
            Thread.currentThread().setContextClassLoader(Conexion.class.getClassLoader());
            
            emf = Persistence.createEntityManagerFactory("SmartHardPU");
            System.out.println("¡Hibernate conectado exitosamente!");
        } catch (Exception e) {
            System.err.println("Error crítico al iniciar Hibernate: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static EntityManager getEntityManager() {
        if (emf == null) {
            throw new IllegalStateException("El EntityManagerFactory no se inicializó correctamente.");
        }
        return emf.createEntityManager();
    }

    public static void cerrarFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}