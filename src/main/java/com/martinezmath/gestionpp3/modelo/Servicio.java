package com.martinezmath.gestionpp3.modelo;




import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Matyas
 */
public class Servicio {

    private IntegerProperty idservicio;

    private LocalDate fechaServicio;
    private LocalDate fechaServicioRec;

    private StringProperty detalle;
    private StringProperty costo;
    private StringProperty numFactura;
    private StringProperty tipoProducto;
    private IntegerProperty idEstado;
    private IntegerProperty idCliente;
    private IntegerProperty idUsuario;

    private TipoServicio tipoServicio;
    private Estado estado;
    private Cliente cliente;

    public Servicio() {
    }

    public Servicio(int idservicio, LocalDate fecha, LocalDate fechaRecibido,
            String detalle, String costo, String numFactura, String tipoProducto,
            TipoServicio ts, Estado estado, Cliente cliente) {
        this.idservicio = new SimpleIntegerProperty(idservicio);
        this.fechaServicio = fecha;
        this.fechaServicioRec = fechaRecibido;
        this.detalle = new SimpleStringProperty(detalle);
        this.costo = new SimpleStringProperty(costo);
        this.numFactura = new SimpleStringProperty(numFactura);
        this.tipoProducto = new SimpleStringProperty(tipoProducto);
        this.tipoServicio = ts;
        this.estado = estado;
        this.cliente = cliente;
    }

    //Metodos atributo: idservicio
    public int getIdservicio() {
        return idservicio.get();
    }

    public void setIdservicio(int idservicio) {
        this.idservicio = new SimpleIntegerProperty(idservicio);
    }

    public IntegerProperty IdservicioProperty() {
        return idservicio;
    }

    //Metodos atributo: fechaServicio
    public LocalDate getFechaServicio() {
        return fechaServicio;
    }

    public void setFechaServicio(LocalDate fechaServicio) {
        this.fechaServicio = fechaServicio;
    }

    //Metodos atributo: fechaServicioRecibido
    public LocalDate getFechaServicioRec() {
        return fechaServicioRec;
    }

    public void setFechaServicioRec(LocalDate fechaServicioRec) {
        this.fechaServicioRec = fechaServicioRec;
    }

    //Metodos atributo: detalle
    public String getDetalle() {
        return detalle.get();
    }

    public void setDetalle(String detalle) {
        this.detalle = new SimpleStringProperty(detalle);
    }

    public StringProperty DetalleProperty() {
        return detalle;
    }
    //Metodos atributo: costo

    public String getCosto() {
        return costo.get();
    }

    public void setCosto(String costo) {
        this.costo = new SimpleStringProperty(costo);
    }

    public StringProperty CostoProperty() {
        return costo;
    }
    //Metodos atributo: numFactura

    public String getNumFactura() {
        return numFactura.get();
    }

    public void setNumFactura(String numFactura) {
        this.numFactura = new SimpleStringProperty(numFactura);
    }

    public StringProperty NumFacturaProperty() {
        return numFactura;
    }

    //Metodos atributo: tipoProducto
    public String getTipoProducto() {
        return tipoProducto.get();
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = new SimpleStringProperty(tipoProducto);
    }

    public StringProperty TipoProduct0Property() {
        return tipoProducto;
    }

    //Metodos atributo: idEstado
    public int getIdEstado() {
        return idEstado.get();
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = new SimpleIntegerProperty(idEstado);
    }

    public IntegerProperty IdEstadoProperty() {
        return idEstado;
    }
    //Metodos atributo: idCliente

    public int getIdCliente() {
        return idCliente.get();
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = new SimpleIntegerProperty(idCliente);
    }

    public IntegerProperty IdClienteProperty() {
        return idCliente;
    }
    //Metodos atributo: idUsuario

    public int getIdUsuario() {
        return idUsuario.get();
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = new SimpleIntegerProperty(idUsuario);
    }

    public IntegerProperty IdUsuarioProperty() {
        return idUsuario;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return idservicio
                + ", " + fechaServicio + " " + detalle + ", costo=" + costo + "tipoProd:" + tipoProducto + " , "
                + estado.getDescripcion() + cliente.getIdCliente()
                + ", idUsuario=" + idUsuario
                + tipoServicio.getDescripcion()
                + cliente.getIdCliente() + " " + cliente.getDni() + cliente.getApellido();
    }

}
