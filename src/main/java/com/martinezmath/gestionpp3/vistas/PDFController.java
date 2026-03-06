/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.martinezmath.gestionpp3.vistas;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.PDFRenderer;
import com.martinezmath.gestionpp3.Main;
import javafx.embed.swing.SwingFXUtils;

/**
 * FXML Controller class
 *
 * @author Mati
 */
public class PDFController implements Initializable {

    @FXML
    private StackPane root;
    @FXML
    private ImageView image;

    private Stage primaryStage;
    private PDFRenderer pdfRenderer;
    private Main main;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }

    private void openPDF() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open PDF");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf")
        );
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            try {
                PDDocument document = PDDocument.load(file);
                pdfRenderer = new PDFRenderer(document);

                PDPage firstPage = document.getPage(0);
                BufferedImage pageImage = pdfRenderer.renderImageWithDPI(0, 96);

                Image fxImage = SwingFXUtils.toFXImage(pageImage, null);
                image.setImage(fxImage);

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

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setRoot(StackPane root) {
        this.root = root;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public void setPdfRenderer(PDFRenderer pdfRenderer) {
        this.pdfRenderer = pdfRenderer;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    
    
    
}
