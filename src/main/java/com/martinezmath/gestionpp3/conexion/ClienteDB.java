package com.martinezmath.gestionpp3.conexion;

import com.martinezmath.gestionpp3.modelo.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

public class ClienteDB {

    public ClienteDB() {
    }

    public ObservableList<Cliente> buscarTodos() {
        EntityManager em = Conexion.getEntityManager();
        try {
            // JPQL: Traemos clientes activos ordenados por ID descendente
            TypedQuery<Cliente> query = em.createQuery(
                "SELECT c FROM Cliente c WHERE c.baja = 0 ORDER BY c.idCliente DESC", Cliente.class);
            List<Cliente> lista = query.getResultList();
            return FXCollections.observableArrayList(lista);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar clientes: " + e.getMessage());
            return FXCollections.observableArrayList();
        } finally {
            em.close();
        }
    }

    public boolean insertarCliente(Cliente c) {
        EntityManager em = Conexion.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(c); // Hibernate hace el INSERT y gestiona el ID automáticamente
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

    public boolean editarCliente(Cliente cliente) {
        EntityManager em = Conexion.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(cliente); // Hibernate detecta los cambios y hace el UPDATE
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            return false;
        } finally {
            em.close();
        }
    }

    public boolean bajaCliente(Cliente cliente) {
        EntityManager em = Conexion.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Cliente c = em.find(Cliente.class, cliente.getIdCliente());
            if (c != null) {
                c.setBaja(1); // Baja lógica
            }
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            return false;
        } finally {
            em.close();
        }
    }

    public Cliente buscarClientePorId(int idCliente) {
        EntityManager em = Conexion.getEntityManager();
        try {
            return em.find(Cliente.class, idCliente);
        } catch (Exception e) {
            System.out.println("Error al buscar cliente por ID: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }
}