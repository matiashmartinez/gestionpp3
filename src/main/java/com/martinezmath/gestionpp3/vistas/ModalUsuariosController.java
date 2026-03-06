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
        // TODO
        configurarComboTipoU();
        configurarTablas();
        listaU.addAll(udb.buscarTodos());
        System.out.println("listaU: " + listaU.toString());
        tblUsuarios.setItems(listaU);

    }

    private void checkListaU() {
        if (udb.buscarTodos() == null) {
            tblUsuarios.setDisable(true);
        }
    }

    private void configurarTablas() {
        colIdU.setCellValueFactory(new PropertyValueFactory<Usuario, Integer>("idUsuario"));
        colUsernameU.setCellValueFactory(new PropertyValueFactory<Usuario, String>("username"));
        colTipoU.setCellValueFactory(new PropertyValueFactory<Usuario, String>("rol"));
         colEmail.setCellValueFactory(new PropertyValueFactory<Usuario, String>("email"));

    }

    public void configurarComboTipoU() {
        listaTU.addAll(Arrays.asList("Administrador", "Técnico"));
        cmbTipoU.setItems(listaTU);

    }

    private Usuario getCamposUsuarios() {
        String username = tfUsername.getText();
        String email = tfEmailU.getText();
        String password = Cifrado.md5(tfPasswordU.getText());
        String tipoUsuario = cmbTipoU.getSelectionModel().getSelectedItem();

        Usuario usuario = new Usuario(0,  username, password, tipoUsuario, email);
        if (validarCampos(usuario)) {
            
        }
        return usuario;
    }

    //Insertar nuevo usuario
    private boolean insertarUsuarioOk(Usuario u) {
        if (udb.insertarUsuario(u)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atención");
            alert.setHeaderText("Usuario agregado a la base de datos correctamente");
            alert.setContentText("Puede revisarlo en la tabla de usuarios\n");

            alert.showAndWait();
            System.out.println("Usuario agregado OK");
            listaU.clear();
            listaU.addAll(udb.buscarTodos());
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Atención");
            alert.setHeaderText("No se ha podido insertar el nuevo usuario");
            alert.setContentText("Revise los campos o compruebe la conexion con la base de datos\n");

            alert.showAndWait();
        }
        return false;

    }

    //VALIDAR SI ES EMAIL VALIDO, SI PASSWORD ESTA NULO Y EL ROL
    private boolean validarCampos(Usuario u) {
        if (isEmail(u.getUsername()) && tfPasswordU.getText().length() == 0 && cmbTipoU.getSelectionModel().getSelectedItem() != null) {
            System.out.println("Datos de formulario correcto");
            return true;

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Atención");
            alert.setHeaderText("Datos incorrectos ");
            alert.setContentText("Por favor revise los datos del formulario que sean  correctos\n");
        }
        return false;
    }

    public boolean isEmail(String correo) {
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^[\\w\\-\\_\\+]+(\\.[\\w\\-\\_]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$");
        mat = pat.matcher(correo);
        if (mat.find()) {
            return true;
        } else {
            return false;
        }
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public PrincipalController getPc() {
        return pc;
    }

    public void setPc(PrincipalController pc) {
        this.pc = pc;
    }

    @FXML
    private void onInsertarUsuario(ActionEvent event) {
        insertarUsuarioOk(getCamposUsuarios());
    }

}
