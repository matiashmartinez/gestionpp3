package com.martinezmath.gestionpp3.utils;

import com.martinezmath.gestionpp3.modelo.Servicio;

// Importaciones para iText 2 (lowagie)
import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

// Importaciones estándar de Java
import java.awt.Color; // Reemplaza a BaseColor
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

// Interfaz gráfica (Swing)
import javax.swing.JOptionPane;

public class PDFUtil {

    private static final Font titulo = FontFactory.getFont(FontFactory.HELVETICA, 26, Font.BOLDITALIC);
    private static final Font parrafo = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);

    // En iText 2, se usan las constantes directas de la clase Font, no el enumerado FontFamily
    private static final Font categoria = new Font(Font.TIMES_ROMAN, 18, Font.BOLD);
    private static final Font subCategoria = new Font(Font.TIMES_ROMAN, 16, Font.BOLD);
    private static final Font fontAzul = new Font(Font.HELVETICA, 16, Font.BOLD, Color.BLUE); // Usamos java.awt.Color
    private static final Font fontBold = new Font(Font.TIMES_ROMAN, 14, Font.BOLD);

    public void mostrarReporte(Servicio s) {

        Document documento = new Document();
        String ruta = System.getProperty("user.home");

        try {
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/R-" + s.getIdservicio() + ".pdf"));
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(PDFUtil.class.getName()).log(Level.SEVERE, "Error al crear el archivo PDF", ex);
            return; // Detener la ejecución si no se puede crear el archivo
        }

        documento.open();
        documento.addTitle("MegaTecnología Servicio Técnico");

        Chunk linea = Chunk.NEWLINE;
        Image header = null;
        Image wsp = null;

        try {
            URL urlHeader = getClass().getResource("/com/martinezmath/gestionpp3/recursos/logosuperior2.png");
            if (urlHeader != null) {
                header = Image.getInstance(urlHeader);
                header.scaleToFit(350, 650);
                header.setAlignment(Chunk.ALIGN_CENTER);
            } else {
                Logger.getLogger(PDFUtil.class.getName()).log(Level.WARNING, "No se encontró la imagen del encabezado");
            }

            URL urlWsp = getClass().getResource("/com/martinezmath/gestionpp3/recursos/contacto.png");
            if (urlWsp != null) {
                wsp = Image.getInstance(urlWsp);
                wsp.setAlignment(Chunk.ALIGN_CENTER);
                wsp.scaleToFit(250, 400);
            }

        } catch (BadElementException ex) {
            Logger.getLogger(PDFUtil.class.getName()).log(Level.SEVERE, "Error al cargar las imágenes del PDF", ex);
        } catch (IOException ex) {
            System.getLogger(PDFUtil.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        Paragraph subt = new Paragraph();
        Paragraph pagoSubt = new Paragraph();
        Paragraph pago = new Paragraph();
        Paragraph contacto = new Paragraph();
        Paragraph espacio = new Paragraph();
        Paragraph fecha = new Paragraph();

        fecha.add(LocalDate.now().toString());
        fecha.setAlignment(Chunk.ALIGN_TOP);
        fecha.setAlignment(Chunk.ALIGN_RIGHT);
        contacto.setAlignment(Chunk.ALIGN_CENTER);
        subt.setAlignment(Chunk.ALIGN_CENTER);
        subt.setFont(fontAzul);
        espacio.add("\n");

        pagoSubt.setFont(fontBold);
        pagoSubt.add("\n Métodos de pago: \n");

        pago.add("\n Formas de pago: \n  "
                + "\nEfectivo, Débito, Transferencia \n"
                + "\nConsultar por pagos con tarjetas de crédito \n\n");

        //ESTRUCTURA TABLA CLIENTE
        PdfPTable tablaCliente = new PdfPTable(4);
        tablaCliente.addCell("DNI");
        tablaCliente.addCell("Apellido");
        tablaCliente.addCell("Nombre");
        tablaCliente.addCell("Teléfono");

        try {
            documento.add(espacio);
        } catch (DocumentException ex) {
            Logger.getLogger(PDFUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        //ESTRUCTURA TABLA ENCABEZADO SERVICIO TECNICO
        PdfPTable tablaST = new PdfPTable(6);
        tablaST.addCell("Tipo de orden ST");
        tablaST.addCell("ID Servicio Técnico");
        tablaST.addCell("Fecha Estimada");
        tablaST.addCell("Costo estimado");
        tablaST.addCell("Factura");
        tablaST.addCell("Tipo producto");

        //SINCRONIZACION DE DATOS DE TABLA  CLIENTE
        tablaCliente.addCell(s.getCliente().getDni());
        tablaCliente.addCell(s.getCliente().getApellido());
        tablaCliente.addCell(s.getCliente().getNombre());
        tablaCliente.addCell(s.getCliente().getTelefono());

        //ESTRUCTURA TABLA DETALLE SERVICIO TECNICO - 
        PdfPTable tablaDetalleST = new PdfPTable(1);
        tablaDetalleST.addCell("Detalle");

        //SINCRONIZACION DE DATOS TABLA SERVICIO TECNICO - DETALLE
        tablaDetalleST.addCell(s.getDetalle());

        //SINCRONIZACION DE DATOS DE TABLA SERVICIO TECNICO - ENCABEZADO
        tablaST.addCell(s.getTipoServicio().getDescripcion());
        tablaST.addCell(Integer.toString(s.getIdservicio()));
        tablaST.addCell(s.getFechaServicio().toString());
        tablaST.addCell("$" + s.getCosto());
        tablaST.addCell(s.getNumFactura());
        tablaST.addCell(s.getTipoProducto());

        try {
            documento.add(fecha);
            if (header != null) documento.add(header);

            documento.add(subt);
            documento.add(espacio);
            documento.add(tablaCliente);
            documento.add(espacio);
            documento.add(tablaST);
            documento.add(espacio);
            documento.add(tablaDetalleST);

            documento.add(espacio);

            if (wsp != null) documento.add(wsp);

            documento.add(contacto);

            documento.close();
            JOptionPane.showMessageDialog(null, "Documento creado exitosamente", "Atención", 1);
            
        } catch (DocumentException e) {
            Logger.getLogger(PDFUtil.class.getName()).log(Level.SEVERE, "Error al ensamblar el documento", e);
        }
    }
}