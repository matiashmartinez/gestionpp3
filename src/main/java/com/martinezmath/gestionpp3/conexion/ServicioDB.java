package com.martinezmath.gestionpp3.conexion;

import com.martinezmath.gestionpp3.modelo.Cliente;
import com.martinezmath.gestionpp3.modelo.Estado;
import com.martinezmath.gestionpp3.modelo.Servicio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ServicioDB {

    // Método dummy para que tu controlador no marque error si olvidaste borrar la llamada.
    // Con Hibernate ya no necesitamos buscar el último ID manual.
    public Integer traerUltimoIdServicio() {
        return 0; 
    }

    public ObservableList<Servicio> buscarTodos(Cliente c) {
        EntityManager em = Conexion.getEntityManager();
        try {
            // Buscamos los servicios que pertenezcan al ID del cliente seleccionado
            TypedQuery<Servicio> query = em.createQuery(
                "SELECT s FROM Servicio s WHERE s.baja = 0 AND s.cliente.idCliente = :idCli ORDER BY s.idServicio DESC", Servicio.class);
            query.setParameter("idCli", c.getIdCliente());
            
            List<Servicio> lista = query.getResultList();
            return FXCollections.observableArrayList(lista);
        } catch (Exception e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        } finally {
            em.close();
        }
    }

    public boolean insertarServicio(Servicio s) {
        EntityManager em = Conexion.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(s); // Hace el INSERT con todas las relaciones automáticamente
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    // Cambiar estado (Pendiente -> Finalizado -> Entregado)
    public boolean editarServicioEstado(Servicio s, int idEstado) {
        EntityManager em = Conexion.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            // Buscamos el servicio en la BD
            Servicio serv = em.find(Servicio.class, s.getIdServicio());
            // Buscamos el nuevo estado en la BD
            Estado nuevoEstado = em.find(Estado.class, idEstado);
            
            if (serv != null && nuevoEstado != null) {
                serv.setEstado(nuevoEstado); // Actualizamos el objeto
            }
            tx.commit(); // Hibernate detecta el cambio y hace el UPDATE
            return true;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    // Eliminar físicamente (Basado en tu código original DELETE FROM)
    public boolean eliminarServicioEstado(Servicio s) {
        EntityManager em = Conexion.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Servicio serv = em.find(Servicio.class, s.getIdServicio());
            if (serv != null) {
                em.remove(serv); // Hace el DELETE
            }
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    // Usado para imprimir el reporte PDF
    public Servicio getServicioById(Servicio s) {
        EntityManager em = Conexion.getEntityManager();
        try {
            return em.find(Servicio.class, s.getIdServicio());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
}