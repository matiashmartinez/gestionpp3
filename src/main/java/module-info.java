module com.martinezmath.gestionpp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires java.logging;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires itext; // <-- Mejor aquí agrupado
    requires java.base;
    requires javafx.web;
    requires javafx.swing;

    // Permite que JavaFX acceda a los archivos FXML y controladores
    opens com.martinezmath.gestionpp3 to javafx.fxml;
    opens com.martinezmath.gestionpp3.vistas to javafx.fxml;
    opens com.martinezmath.gestionpp3.utils to javafx.fxml;
    opens com.martinezmath.gestionpp3.modelo to javafx.base, javafx.fxml,org.hibernate.orm.core;
    opens com.martinezmath.gestionpp3.conexion to org.hibernate.orm.core;
  

    // Exporta el paquete principal para que la JVM pueda lanzar la App
    exports com.martinezmath.gestionpp3;
    requires javafx.webEmpty;
    requires jakarta.persistence;
    requires org.hibernate.orm.core; // <-- ¡Faltaba el motor principal!
    requires java.naming;
    
}