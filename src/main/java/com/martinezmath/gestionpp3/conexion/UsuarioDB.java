package com.martinezmath.gestionpp3.conexion;

import com.martinezmath.gestionpp3.modelo.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

public class UsuarioDB {

    public ObservableList<Usuario> buscarTodos() {
        EntityManager em = Conexion.getEntityManager();
        try {
            // JPQL: Consultamos a la entidad 'Usuario' (la clase), no a la tabla
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.baja = 0", Usuario.class);
            List<Usuario> lista = query.getResultList();
            return FXCollections.observableArrayList(lista);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar usuarios: " + e.getMessage());
            return FXCollections.observableArrayList();
        } finally {
            em.close();
        }
    }

    public boolean insertarUsuario(Usuario u) {
        EntityManager em = Conexion.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(u); // Hibernate genera el INSERT automáticamente
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

    public boolean editarUsuario(Usuario usuario) {
        EntityManager em = Conexion.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(usuario); // Hibernate genera el UPDATE comparando los campos
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            return false;
        } finally {
            em.close();
        }
    }

    public boolean bajaUsuario(Usuario usuario) {
        EntityManager em = Conexion.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            // Buscamos el usuario en este contexto de persistencia para modificarlo
            Usuario u = em.find(Usuario.class, usuario.getIdUsuario());
            if (u != null) {
                u.setBaja(1); // Marcamos la baja lógica
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
}