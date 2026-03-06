package com.martinezmath.gestionpp3.vistas;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import com.martinezmath.gestionpp3.Main;
import com.martinezmath.gestionpp3.conexion.UsuarioDB;
import com.martinezmath.gestionpp3.modelo.Usuario;
import com.martinezmath.gestionpp3.utils.Cifrado;

/**
 * FXML Controller class
 *
 * @author Mati
 */
public class ModalUsuariosController implements Initializable {

    @FXML
    private TextField tfEmailU;
    @FXML
    private PasswordField tfPasswordU;
    @FXML
    private ComboBox<String> cmbTipoU;
    @FXML
    private TableView<Usuario> tblUsuarios;
    @FXML
    private Button btnAgregarU;
    @FXML
    private Button btnCerrarU;
    @FXML
    private TextField tfBuscarU;
    @FXML
    private Button btnLimpiarU;
    @FXML
    private TableColumn<Usuario, Integer> colIdU;
    @FXML
    private TableColumn<Usuario, String> colUsernameU;
    @FXML
    private TableColumn<Usuario, String> colTipoU;

    UsuarioDB udb = new UsuarioDB();
    Main main;
    PrincipalController pc;

    private ObservableList<Usuario> listaU = FXCollections.observableArrayList();
    private ObservableList<String> listaTU = FXCollections.observableArrayList();
    @FXML
    private AnchorPane usuarios;
    @FXML
    private TableColumn<Usuario, String> colEmail;
    @FXML
    private Button btnEditar;
    @FXML
    private TextField tfUsername;
    @FXML
    private Button btnLimpiarU1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarComboTipoU();
        configurarTablas();
        
        listaU.addAll(udb.buscarTodos());
        System.out.println("listaU: " + listaU.toString());
        tblUsuarios.setItems(listaU);
    }

    private void checkListaU() {
        if (udb.buscarTodos() == null || udb.buscarTodos().isEmpty()) {
            tblUsuarios.setDisable(true);
        }
    }

    private void configurarTablas() {
        colIdU.setCellValueFactory(new PropertyValueFactory<Usuario, Integer>("idUsuario"));
        colUsernameU.setCellValueFactory(new PropertyValueFactory<Usuario, String>("username"));
        colTipoU.setCellValueFactory(new PropertyValueFactory<Usuario, String>("rol"));
        
        // Cuidado aquí: Tu modelo de Usuario no tenía el atributo "email", pero tu tabla sí. 
        // Si no lo agregaste al modelo antes, esta columna quedará vacía.
        // colEmail.setCellValueFactory(new PropertyValueFactory<Usuario, String>("email")); 
    }

    public void configurarComboTipoU() {
        listaTU.addAll(Arrays.asList("Administrador", "Técnico"));
        cmbTipoU.setItems(listaTU);
    }

    private Usuario getCamposUsuarios() {
        String username = tfUsername.getText();
        // String email = tfEmailU.getText(); // Lo comento porque tu Entidad Usuario actual no tiene email
        String password = Cifrado.md5(tfPasswordU.getText());
        String tipoUsuario = cmbTipoU.getSelectionModel().getSelectedItem();

        // 1. CAMBIO CLAVE: Usamos el constructor sin ID de Hibernate (le pasamos null o usamos el que tiene 3 parámetros)
        Usuario usuario = new Usuario(username, password, tipoUsuario);
        
        // Si habías agregado email al modelo, sería: new Usuario(null, username, password, tipoUsuario, email);

        if (validarCampos(usuario)) {
            return usuario;
        } else {
            return null; // Retornamos nulo si la validación falla
        }
    }

    //Insertar nuevo usuario
    private boolean insertarUsuarioOk(Usuario u) {
        if (u != null && udb.insertarUsuario(u)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atención");
            alert.setHeaderText("Usuario agregado a la base de datos correctamente");
            alert.setContentText("Puede revisarlo en la tabla de usuarios\n");
            alert.showAndWait();
            
            System.out.println("Usuario agregado OK");
            listaU.clear();
            listaU.addAll(udb.buscarTodos());
            tblUsuarios.setItems(listaU); // Refrescamos la tabla
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Atención");
            alert.setHeaderText("No se ha podido insertar el nuevo usuario");
            alert.setContentText("Revise los campos o compruebe la conexion con la base de datos\n");
            alert.showAndWait();
            return false;
        }
    }

    // VALIDACIÓN CORREGIDA
    private boolean validarCampos(Usuario u) {
        // Tu código anterior pedía que el password tuviera longitud 0, lo cual no tiene sentido para crear un usuario.
        // Y estabas validando el username como si fuera un email.
        
        if (!tfUsername.getText().trim().isEmpty() && 
            tfPasswordU.getText().length() > 0 && 
            cmbTipoU.getSelectionModel().getSelectedItem() != null) {
            
            System.out.println("Datos de formulario correctos");
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Atención");
            alert.setHeaderText("Datos incompletos");
            alert.setContentText("Por favor, complete todos los campos (Username, Password y Rol)\n");
            alert.showAndWait();
            return false;
        }
    }

    public boolean isEmail(String correo) {
        Pattern pat = Pattern.compile("^[\\w\\-\\_\\+]+(\\.[\\w\\-\\_]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$");
        Matcher mat = pat.matcher(correo);
        return mat.find();
    }

    public Main getMain() { return main; }
    public void setMain(Main main) { this.main = main; }
    public PrincipalController getPc() { return pc; }
    public void setPc(PrincipalController pc) { this.pc = pc; }

    @FXML
    private void onInsertarUsuario(ActionEvent event) {
        Usuario nuevoUsuario = getCamposUsuarios();
        if(nuevoUsuario != null){
            insertarUsuarioOk(nuevoUsuario);
        }
    }
}