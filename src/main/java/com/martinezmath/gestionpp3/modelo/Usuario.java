package com.martinezmath.gestionpp3.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario") // Le dice a Hibernate: "Esta clase representa la tabla 'usuario'"
public class Usuario {

    @Id // Indica que esta es la Clave Primaria (Primary Key)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Equivale a AUTO_INCREMENT en MySQL
    @Column(name = "idusuario")
    private Integer idUsuario;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "rol")
    private String rol;

    @Column(name = "baja")
    private Integer baja = 0; // 0 para activo, 1 para baja lógica

    // 1. Hibernate EXIGE un constructor vacío
    public Usuario() {
    }

    // 2. Constructor completo (sin id y sin baja, porque los maneja la DB)
    public Usuario(String username, String password, String rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    // 3. Constructor para el Login
    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // --- GETTERS Y SETTERS ESTÁNDAR Puros ---

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Integer getBaja() {
        return baja;
    }

    public void setBaja(Integer baja) {
        this.baja = baja;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", username=" + username + ", rol=" + rol + '}';
    }
}