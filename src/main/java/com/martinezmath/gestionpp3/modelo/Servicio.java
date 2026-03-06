package com.martinezmath.gestionpp3.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "servicio")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idservicio")
    private Integer idServicio;

    @Column(name = "fecha", nullable = false)
    private LocalDate fechaServicio;

    @Column(name = "fechaRecibido")
    private LocalDate fechaServicioRec;

    @Column(name = "detalle", columnDefinition = "TEXT")
    private String detalle;

    @Column(name = "costo")
    private String costo;

    @Column(name = "numFactura")
    private String numFactura;

    @Column(name = "tipoProducto")
    private String tipoProducto;

    @Column(name = "baja")
    private Integer baja = 0;

    // --- RELACIONES MÁGICAS DE HIBERNATE ---

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idTipoServicio", referencedColumnName = "idtipoServicio")
    private TipoServicio tipoServicio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idEstado", referencedColumnName = "idestado")
    private Estado estado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCliente", referencedColumnName = "idcliente")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUsuario", referencedColumnName = "idusuario")
    private Usuario usuario; // El técnico que tomó el equipo

    public Servicio() {}

    // Getters y Setters
    public Integer getIdServicio() { return idServicio; }
    public void setIdServicio(Integer idServicio) { this.idServicio = idServicio; }
    public LocalDate getFechaServicio() { return fechaServicio; }
    public void setFechaServicio(LocalDate fechaServicio) { this.fechaServicio = fechaServicio; }
    public LocalDate getFechaServicioRec() { return fechaServicioRec; }
    public void setFechaServicioRec(LocalDate fechaServicioRec) { this.fechaServicioRec = fechaServicioRec; }
    public String getDetalle() { return detalle; }
    public void setDetalle(String detalle) { this.detalle = detalle; }
    public String getCosto() { return costo; }
    public void setCosto(String costo) { this.costo = costo; }
    public String getNumFactura() { return numFactura; }
    public void setNumFactura(String numFactura) { this.numFactura = numFactura; }
    public String getTipoProducto() { return tipoProducto; }
    public void setTipoProducto(String tipoProducto) { this.tipoProducto = tipoProducto; }
    public Integer getBaja() { return baja; }
    public void setBaja(Integer baja) { this.baja = baja; }
    
    // Getters y Setters de los Objetos
    public TipoServicio getTipoServicio() { return tipoServicio; }
    public void setTipoServicio(TipoServicio tipoServicio) { this.tipoServicio = tipoServicio; }
    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}