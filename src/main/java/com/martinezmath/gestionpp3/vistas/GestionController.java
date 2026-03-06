package com.martinezmath.gestionpp3.vistas;

import com.lowagie.text.DocumentException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import com.martinezmath.gestionpp3.Main;
import com.martinezmath.gestionpp3.conexion.ClienteDB;
import com.martinezmath.gestionpp3.conexion.ServicioDB;
import com.martinezmath.gestionpp3.conexion.TipoServicioDB;
import com.martinezmath.gestionpp3.modelo.Cliente;
import com.martinezmath.gestionpp3.modelo.Estado;
import com.martinezmath.gestionpp3.modelo.Servicio;
import com.martinezmath.gestionpp3.modelo.TipoServicio;
//import com.martinezmath.gestionpp3.utils.PDFUtil;

/**
 * FXML Controller class
 *
 * @author Matyas
 */
public class GestionController implements Initializable {

    private ObservableList<Cliente> lista = FXCollections.observableArrayList();
    private ObservableList<TipoServicio> listaTS = FXCollections.observableArrayList();
    private ObservableList<String> listaProductos = FXCollections.observableArrayList();
    private ObservableList<Servicio> listaServicio = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Cliente, Integer> colId;
    @FXML
    private TableColumn<Cliente, String> colDni;
    @FXML
    private TableColumn<Cliente, String> colApellido;
    @FXML
    private TableColumn<Cliente, String> colNombre;
    @FXML
    private TableColumn<Cliente, String> colTelefono;
    @FXML
    private TableColumn<Cliente, String> colEmail;
    @FXML
    private TextField tfBuscador;
    @FXML
    private TextField tfDni;
    @FXML
    private TextField tfApellido;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfEmail;
    @FXML
    private Button btNuevoCliente;
    @FXML
    private Button btEditarCliente;
    @FXML
    private Button btLimpiarCamposC;
    @FXML
    private TableView<Cliente> tablaClientes;
    private Main main;
    @FXML
    private ComboBox<TipoServicio> cmbTipo;
    @FXML
    private DatePicker dateEstimada;
    @FXML
    private TextArea txtDetalle;
    @FXML
    private TextField tfNumRma;
    @FXML
    private TextField tfCosto;
    @FXML
    private ComboBox<String> cmbTipoProd;
    @FXML
    private TableView<Servicio> tablaServicios;
    @FXML
    private TableColumn<Servicio, Integer> colServicioId;
    @FXML
    private TableColumn<Servicio, LocalDate> colServicioFechaEst;
    @FXML
    private TableColumn<Servicio, String> colServicioDetalle;
    @FXML
    private TableColumn<Servicio, String> colServicioEstado;
    @FXML
    private Button btEliminarServicio;
    @FXML
    private Button btEliminarCliente;
    @FXML
    private Label labelIdCliente;

    ClienteDB dao = new ClienteDB();
    TipoServicioDB daoTS = new TipoServicioDB();
    ServicioDB daoServicio = new ServicioDB();

    private Integer estadoServicio;

    @FXML
    private TextField tfIdServicio;
    @FXML
    private TableColumn<Servicio, LocalDate> colServicioFechaRec;
    @FXML
    private CheckBox checkReporteAuto;
    @FXML
    private Label labelEstado;
    @FXML
    private Tab tabPanel1;
    @FXML
    private Tab tabPanel2;
    @FXML
    private Tab tabPanel3;
    @FXML
    private TabPane tabGestion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setLabelIdCliente();
        configurarTablas();
        setTextFieldIdTipoServicio();

        listaTS.setAll(daoTS.buscarTodos());
        cmbTipo.setItems(listaTS);
        llenarListaTipoProductos();
        cmbTipoProd.setItems(listaProductos);

        listeners();
        checkReporteAuto.setSelected(false);
    }

    private void llenarListaTipoProductos() {
        listaProductos.add("Periférico");
        listaProductos.add("PC ");
        listaProductos.add("Notebook");
        listaProductos.add("Otro");
    }

    private void setLabelIdCliente() {
        labelIdCliente.setText("Auto");
    }

    private void setTextFieldIdTipoServicio() {
        tfIdServicio.setText("Auto");
    }

    private void configurarTablas() {
        colId.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("idCliente"));
        colDni.setCellValueFactory(new PropertyValueFactory<Cliente, String>("dni"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellido"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefono"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Cliente, String>("email"));

        colServicioId.setCellValueFactory(new PropertyValueFactory<Servicio, Integer>("idServicio"));
        colServicioDetalle.setCellValueFactory(new PropertyValueFactory<Servicio, String>("detalle"));
        colServicioEstado.setCellValueFactory(new PropertyValueFactory<Servicio, String>("estado"));
        colServicioFechaEst.setCellValueFactory(new PropertyValueFactory<Servicio, LocalDate>("fechaServicio"));
        colServicioFechaRec.setCellValueFactory(new PropertyValueFactory<Servicio, LocalDate>("fechaServicioRec"));
        
        lista.addAll(dao.buscarTodos());
        tablaClientes.setItems(lista);
    }

    @FXML
    private void tfBuscar(KeyEvent event) {
        lista.clear();
        lista.addAll(dao.buscarTodos());
        tablaClientes.setItems(lista);
        
        FilteredList<Cliente> filteredData = new FilteredList<>(lista, p -> true);
        
        tfBuscador.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredData.setPredicate((Cliente cliente) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                if (cliente.getApellido().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                } else if (cliente.getDni().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                }
                return false;
            });
        });
        
        SortedList<Cliente> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tablaClientes.comparatorProperty());
        tablaClientes.setItems(sortedData);
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private boolean validarInsertarCliente() {
        if ("".equals(tfDni.getText()) || tfDni.getText().matches(".*[a-zA-Z].*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Atención");
            alert.setHeaderText("Falta DNI o es inválido");
            alert.setContentText("Por favor revise el DNI ingresado.\n");
            return false;
        }
        return true;
    }

    @FXML
    private void onNuevoCliente(ActionEvent event) {
        try {
            if (!tfDni.getText().isEmpty() && tfDni.getText().length() == 8) {
                if (nuevoClienteOk()) {
                    System.out.println("Cliente insertado");
                    limpiarCamposNuevoCliente();
                    tablaClientes.setItems(dao.buscarTodos());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Información");
                    alert.setHeaderText("Nuevo cliente");
                    alert.setContentText("Se ha registrado correctamente un nuevo cliente a la base de datos.\n");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No se ha podido registrar al cliente");
                alert.setContentText("Datos incorrectos\n");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean nuevoClienteOk() {
        Cliente c = getTextFieldsCliente(false);
        System.out.println("Datos cliente a insertar: \n" + c.toString());
        dao.insertarCliente(c);
        lista.clear();
        return true;
    }

    private boolean nuevoServicioOk() {
        Cliente c = tablaClientes.getSelectionModel().getSelectedItem();
        Servicio s = new Servicio();
        
        // Asignamos el estado inicial (ID 1 = Pendiente en tu DB)
        Estado e = new Estado();
        e.setIdEstado(1);
        
//        PDFUtil pdfUtil = new PDFUtil();

        if (c != null) {
            System.out.println("Datos cliente a insertar:" + c.toString() + "\n");
            
            // Hibernate asume el ID. No hacemos set de ID.
            s.setTipoServicio(cmbTipo.getValue());
            s.setFechaServicio(dateEstimada.getValue());
            s.setDetalle(txtDetalle.getText());
            s.setNumFactura(tfNumRma.getText());
            s.setTipoProducto(cmbTipoProd.getValue());
            s.setCosto(tfCosto.getText());
            s.setCliente(c);
            s.setEstado(e);

            if (checkReporteAuto.isSelected()) {
//                pdfUtil.mostrarReporte(s);
            }
            if (daoServicio.insertarServicio(s)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atención");
                alert.setHeaderText("Se ha registrado una nueva solicitud de servicio técnico");
                alert.setContentText("Cliente: " + c.getApellido() + " " + c.getNombre());
                listaServicio.clear();
                tabGestion.getSelectionModel().select(tabPanel3);
                limpiarCamposNuevoServicio();
                return true;
            }
        }
        return false;
    }

    @FXML
    private void onEditarCliente(ActionEvent event) {
        Cliente c;
        if (tablaClientes.getSelectionModel().getSelectedItem() != null) {
            c = tablaClientes.getSelectionModel().getSelectedItem();
            if (editarClienteOk()) {
                limpiarCamposNuevoCliente();
                tablaClientes.setItems(dao.buscarTodos());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atención");
                alert.setHeaderText("Se han editado los datos del cliente");
                alert.setContentText("");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Atención");
                alert.setHeaderText("No se ha podido editar el cliente");
                alert.setContentText("Posible error en la conexión con la base de datos\n");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Atención");
            alert.setHeaderText("Ningún cliente seleccionado");
            alert.setContentText("Por favor seleccione un elemento de la lista de clientes\n");
            alert.showAndWait();
        }
    }

    private void onEditarServicioEstado(ActionEvent event) {
        if (tablaServicios.getSelectionModel().getSelectedItem() != null) {
            Servicio s = tablaServicios.getSelectionModel().getSelectedItem();
            if (editarServicioEstadoOk(s, this.estadoServicio)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atención");
                alert.setHeaderText("Se ha actualizado el estado del servicio");
                alert.setContentText("");
                alert.showAndWait();
                setTablaServicios(tablaClientes.getSelectionModel().getSelectedItem());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Atención");
            alert.setHeaderText("Ningún servicio seleccionado");
            alert.setContentText("Por favor seleccione un elemento de la tabla de servicios\n");
            alert.showAndWait();
        }
    }

    private boolean editarClienteOk() {
        if (dao.editarCliente(getTextFieldsCliente(true))) {
            lista.clear();
            return true;
        }
        return false;
    }

    private boolean editarServicioEstadoOk(Servicio s, int estado) {
        if (daoServicio.editarServicioEstado(s, estado)) {
            listaServicio.clear();
            return true;
        }
        return false;
    }

    @FXML
    private void onEliminarCliente(ActionEvent event) {
        if (tablaClientes.getSelectionModel().getSelectedItem() != null) {
            if (eliminarClienteOk()) {
                limpiarCamposNuevoCliente();
                tablaClientes.setItems(dao.buscarTodos());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atención");
                alert.setHeaderText("Se ha dado de baja un cliente");
                alert.setContentText("");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Atención");
                alert.setHeaderText("No se ha podido dar de baja al cliente");
                alert.setContentText("Posible error en la conexión con la base de datos\n");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Atención");
            alert.setHeaderText("Ningún cliente seleccionado");
            alert.setContentText("Por favor seleccione un elemento de la lista de clientes\n");
            alert.showAndWait();
        }
    }

    private boolean eliminarClienteOk() {
        if (dao.bajaCliente(getTextFieldsCliente(true))) {
            lista.clear();
            return true;
        }
        return false;
    }

    @FXML
    public void limpiarCamposNuevoCliente() {
        setLabelIdCliente();
        tfDni.setText("");
        tfApellido.setText("");
        tfNombre.setText("");
        tfTelefono.setText("");
        tfEmail.setText("");
        tablaClientes.refresh();
        btNuevoCliente.setDisable(false);
    }

    public void limpiarCamposNuevoServicio() {
        tfNumRma.setText("");
        txtDetalle.setText("");
        tfCosto.setText("");
    }

    public Cliente getTextFieldsCliente(boolean editando) {
        String dni = tfDni.getText();
        String apellido = tfApellido.getText();
        String nombre = tfNombre.getText();
        String telefono = tfTelefono.getText();
        String email = tfEmail.getText();

        if (editando) {
            Integer idClienteE = tablaClientes.getSelectionModel().getSelectedItem().getIdCliente();
            return new Cliente(idClienteE, dni, apellido, nombre, telefono, email);
        } else {
            // Mandamos NULL en el ID para que Hibernate sepa que es nuevo
            return new Cliente(null, dni, apellido, nombre, telefono, email);
        }
    }

    private void listeners() {
        tablaClientes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> setTextFieldsClientes(newValue));

        tablaClientes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> setTablaServicios(newValue));
    }

    private boolean validarRegistrarServicio() {
        if (dateEstimada.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Atención");
            alert.setHeaderText("Campo fecha vacío");
            alert.setContentText("Por favor seleccione una fecha estimada en la que estará finalizado el servicio\n");
            alert.showAndWait();
            return false;
        }
        if (cmbTipo.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Atención");
            alert.setHeaderText("Falta tipo servicio");
            alert.setContentText("Por favor seleccione el tipo de servicio de la nueva solicitud\n");
            return false;
        }
        return true;
    }

    @FXML
    private void onRegistrar(ActionEvent event) throws DocumentException {
        if (validarRegistrarServicio()) {
            if (tablaClientes.getSelectionModel().getSelectedItem() != null) {
                if (nuevoServicioOk()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Atención");
                    alert.setHeaderText("Se ha registrado un nuevo servicio correctamente");
                    alert.setContentText("");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Atención");
                alert.setHeaderText("No se ha seleccionado cliente");
                alert.setContentText("Por favor seleccione al cliente que desea registrar una nueva solicitud");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atención");
            alert.setHeaderText("No ha sido posible registrar la nueva solicitud");
            alert.setContentText("Por favor revise los campos e intente nuevamente");
            alert.showAndWait();
        }
    }

    @FXML
    private void onImprimir(ActionEvent event) {
//        PDFUtil pdf = new PDFUtil();
        Servicio s = tablaServicios.getSelectionModel().getSelectedItem();
        if (s != null) {
            if (daoServicio.getServicioById(s) != null) {
//                pdf.mostrarReporte(s);
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Atención");
                alert.setHeaderText("Reporte generado");
                alert.setContentText("");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void onEnviarEmail(ActionEvent event) {
    }

    private void setTextFieldsClientes(Cliente c) {
        if (c != null) {
            labelIdCliente.setText(String.valueOf(c.getIdCliente()));
            tfDni.setText(c.getDni());
            tfApellido.setText(c.getApellido());
            tfNombre.setText(c.getNombre());
            tfTelefono.setText(c.getTelefono());
            tfEmail.setText(c.getEmail());
            btNuevoCliente.setDisable(true);
        }
    }

    private void setTablaServicios(Cliente c) {
        listaServicio.clear();
        if (c != null) {
            listaServicio.addAll(daoServicio.buscarTodos(c));
            tablaServicios.setItems(listaServicio);
        }
    }

    private boolean eliminarServicio() {
        Servicio s = tablaServicios.getSelectionModel().getSelectedItem();
        if (daoServicio.eliminarServicioEstado(s)) {
            listaServicio.clear();
            tablaServicios.refresh();
            return true;
        }
        return false;
    }

    @FXML
    private void onEliminarServicio(ActionEvent event) {
        if (tablaServicios.getSelectionModel().getSelectedItem() == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Atención");
            alerta.setHeaderText("No hay seleccionado ningún servicio de la tabla");
            alerta.setContentText("Por favor seleccione el servicio que desea eliminar");
            alerta.showAndWait();
        } else if (eliminarServicio()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atención");
            alert.setHeaderText("Se ha eliminado correctamente el servicio");
            alert.setContentText("");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Atención");
            alert.setHeaderText("No se ha eliminado correctamente el servicio");
            alert.setContentText("");
            alert.showAndWait();
        }
    }

    public Integer getEstadoServicio() {
        return estadoServicio;
    }

    public void setEstadoServicio(Integer estadoServicio) {
        this.estadoServicio = estadoServicio;
    }

    @FXML
    private void onServicioFinalizado(ActionEvent event) {
        this.setEstadoServicio(2);
        onEditarServicioEstado(null);
    }

    @FXML
    private void onServicioEntregado(ActionEvent event) {
        this.setEstadoServicio(3);
        onEditarServicioEstado(null);
    }

    public CheckBox getCheckReporteAuto() {
        return checkReporteAuto;
    }

    public void setCheckReporteAuto(CheckBox checkReporteAuto) {
        this.checkReporteAuto = checkReporteAuto;
    }
}