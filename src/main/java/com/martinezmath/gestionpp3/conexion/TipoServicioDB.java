package com.martinezmath.gestionpp3.conexion;

import com.martinezmath.gestionpp3.modelo.TipoServicio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author Matyas
 */
public class TipoServicioDB {

    public ObservableList<TipoServicio> buscarTodos() {
        System.out.println("buscartodos TipoServicio (vía Hibernate)");
        EntityManager em = Conexion.getEntityManager();
        try {
            // JPQL: Traemos todos los tipos de servicio que estén activos
            TypedQuery<TipoServicio> query = em.createQuery("SELECT t FROM TipoServicio t WHERE t.baja = 0", TipoServicio.class);
            List<TipoServicio> lista = query.getResultList();
            
            return FXCollections.observableArrayList(lista);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al buscar tipos de servicios: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return FXCollections.observableArrayList(); // Retornamos lista vacía para que no colapse JavaFX
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}