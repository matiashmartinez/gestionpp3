
package com.martinezmath.gestionpp3.conexion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Conexion {
    
    // Lee el nombre exacto que pusimos en el persistence.xml
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("SmartHardPU");

    // Este método te dará la sesión de base de datos lista para usar
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void cerrarFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}