/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.martinezmath.gestionpp3.vistas;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;

import com.martinezmath.gestionpp3.Main;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;




/**
 * FXML Controller class
 *
 * @author Matyas
 */
public class PrincipalController implements Initializable {

    StackPane root = null;

    ImageView image = null;

    
    PDFRenderer pdfRenderer;
    Main main;
    private Stage stage;
    private WebView webView;
    private WebEngine webEngine;
    private File path;

    @FXML
    private MenuItem menuItemUsuarios;
    @FXML
    private MenuItem menuItemPrincipal;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    private void verClientes(ActionEvent event) {
        main.centrar_scena_gestion();
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private void onMostrarModalUsuarios(ActionEvent event) {
        main.mostrarModalUsuarios();

    }

    @FXML
    private void onMostrarScenePrincipal(ActionEvent event) {
        main.centrar_scena_dashboard();
    }

    @FXML
    private void onMostrarSceneGestionar(ActionEvent event) {
        main.centrar_scena_gestion();
    }

    @FXML
    public void imprimir() {
        try {
            Stage st = new Stage();
            int IMAGE_DPI = 150;
            double SCALE_FACTOR = 0.5;
            
            // Cargar el archivo PDF
            PDDocument document = null;
            
            document = PDDocument.load(path);
            
            // Convertir la página del PDF a una imagen
            PDFRenderer renderer = new PDFRenderer(document);
            PDPage page = document.getPage(0); // número de página
            BufferedImage image = null;
            
            try {
                image = renderer.renderImageWithDPI(0, IMAGE_DPI);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            // Crear el visualizador de PDF con WebView
            String imagePath = "file://" + path.getAbsolutePath();
            
            webEngine.loadContent(
                    "<img src=\"" + imagePath + "\">");
            
            // Crear el panel de JavaFX que contiene el visualizador de PDF
            StackPane root = new StackPane();
            
            root.getChildren()
                    .add(webView);
            
            // Crear la escena de JavaFX y mostrarla
            Scene scene = new Scene(root, image.getWidth() * SCALE_FACTOR, image.getHeight() * SCALE_FACTOR);
            
            st.setScene(scene);
            
            st.show();
            
            // Cerrar el documento PDF al cerrar la aplicación
            document.close();
        } catch (IOException ex) {
            System.getLogger(PrincipalController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public void setPrimaryStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void openPDF() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open PDF");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf")
        );
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try {
                PDDocument document = PDDocument.load(file);
                pdfRenderer = new PDFRenderer(document);

                PDPage firstPage = document.getPage(0);
//                BufferedImage pageImage = pdfRenderer.renderImageWithDPI(0, 96);
//
//                Image fxImage = SwingFXUtils.toFXImage(pageImage, null);
//                image.setImage(fxImage);

                double width = firstPage.getCropBox().getWidth();
                double height = firstPage.getCropBox().getHeight();

                image.setFitWidth(width);
                image.setFitHeight(height);

                root.setPrefWidth(width);
                root.setPrefHeight(height);

                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
