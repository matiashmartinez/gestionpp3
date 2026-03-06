/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.martinezmath.gestionpp3.vistas;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import com.martinezmath.gestionpp3.Main;

/**
 * FXML Controller class
 *
 * @author Matyas
 */
public class DashboardController implements Initializable {

    @FXML
    private Label labelUser;
    @FXML
    private Label cantRmaPerifericos;
    @FXML
    private Label cantRmaPCArm;
    @FXML
    private Label cantRmaComp;
    @FXML
    private Label labelCantSTF;
    @FXML
    private Label cantSTFNotebook1;
    @FXML
    private Label cantRmaPerifericosF;
    @FXML
    private Label cantRmaPCArmF;
    @FXML
    private Label cantRmaCompF;
    @FXML
    private Label cantSTFinalizadosPC;
    @FXML
    private Label cantSTFNotebook;
    @FXML
    private Label cantWaitRAM;
    @FXML
    private Label cantWaitSSD;
    @FXML
    private Label cantWaitFuente;
    @FXML
    private Label cantWaitMother;
    @FXML
    private Label cantWaitProcesador;
    @FXML
    private Label cantWaitGabinete;
    @FXML
    private Label cantWaitRed;
    
    
    private Main main;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setMain(Main main) {
        this.main = main;
    }

    
    
    
}
