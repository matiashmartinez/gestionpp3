# Sistema de Gestión SmartHard 
# Sistema de Gestión SmartHard - Servicio Técnico Integral

Este proyecto es una solución de software diseñada para la administración completa de un taller de soporte técnico de computación (**SmartHard**). El sistema centraliza la recepción de equipos, el diagnóstico, la reparación y el seguimiento de órdenes de trabajo, cubriendo tanto servicios técnicos generales como la gestión de garantías (RMA).

## 🚀 Propósito del Proyecto
El objetivo es profesionalizar el flujo de trabajo del área técnica, permitiendo:
* Sustituir el registro en papel o planillas sueltas por una base de datos robusta.
* Gestionar reparaciones de hardware, instalaciones de software y mantenimiento preventivo.
* Mantener un historial detallado por equipo y cliente.
## 🚀 Novedades de la Última Versión (Migración a Maven & JDK 11)

El proyecto ha sido sometido a una migración tecnológica y refactorización profunda para cumplir con los estándares de desarrollo modernos:

* **Migración a Maven:** Transición de un proyecto tradicional a un modelo basado en `pom.xml` para la gestión automatizada de dependencias (PDFBox, JasperReports, MySQL Connector, etc.).
* **Actualización a JDK 11+:** Implementación del sistema de módulos de Java (`module-info.java`) asegurando la compatibilidad y seguridad del código.
* **Refactorización de Interfaz:** Eliminación de dependencias heredadas de Swing (`JOptionPane`) reemplazándolas por alertas nativas de JavaFX para evitar bloqueos en el hilo gráfico.
* **Gestión de Configuración:** Implementación de un lector de propiedades dinámico (`config.properties`) centralizado en `src/main/resources`, permitiendo cambiar las credenciales de la base de datos sin recompilar el código Java.
* **Visor de PDF Integrado:** Soporte nativo para visualizar reportes de órdenes técnicas directamente en la aplicación utilizando `WebView`, `WebEngine` y renderizado de imágenes con `PDFBox`.

## 🛠️ Stack Tecnológico

* **Lenguaje:** Java (JDK 11)
* **Framework UI:** JavaFX 13 (Controls, FXML, Web, Swing)
* **Gestor de Dependencias:** Maven
* **Base de Datos:** MySQL 8.0
* **Reportes y Exportación:** JasperReports, Apache PDFBox, Apache POI
* **Patrón de Arquitectura:** MVC (Modelo-Vista-Controlador)

## 📦 Módulos Principales (Épicas)

1.  **Gestión de Clientes (Epic 1):** ABM completo de clientes con validaciones de campos (DNI, Email) y búsqueda dinámica con filtros en tiempo real.
2.  **Gestión de Orden de Servicio (Epic 2):** Creación de solicitudes de servicio técnico vinculadas a clientes. Control de estados dinámicos (Pendiente ➔ Finalizado ➔ Entregado) y gestión de costos.
3.  **Reportes de Orden Técnica (Epic 3):** Generación automática de comprobantes en formato PDF para el cliente y el taller, con visualización integrada en la app.
4.  **Gestión de Usuarios y Seguridad (Epic 4):** Sistema de login con cifrado de contraseñas y control de acceso basado en roles (Técnicos, Administradores).

## ⚙️ Instalación y Configuración

### Prerrequisitos
* Java Development Kit (JDK) 11 o superior.
* Apache Maven instalado.
* Servidor MySQL ejecutándose localmente o en red.

### Pasos para ejecutar

1. **Clonar el repositorio:**
   ```bash
   git clone [https://github.com/tu-usuario/gestionpp3.git](https://github.com/tu-usuario/gestionpp3.git)
   cd gestionpp3