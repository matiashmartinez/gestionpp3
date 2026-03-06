💻 Sistema de Gestión SmartHard - Servicio Técnico Integral
SmartHard es una solución de software de escritorio diseñada para la administración completa y profesional de un taller de soporte técnico de computación. El sistema centraliza la recepción de equipos, el diagnóstico, la reparación y el seguimiento de órdenes de trabajo, cubriendo tanto servicios técnicos generales como la gestión de garantías (RMA).

El propósito principal es sustituir el registro en papel o planillas sueltas por una base de datos robusta, manteniendo un historial detallado por equipo y cliente, y agilizando la gestión diaria del taller.

📦 Módulos Principales (Épicas)
👥 Gestión de Clientes: ABM completo con validaciones de campos (DNI, Email) y búsqueda dinámica con filtros en tiempo real.

🛠️ Gestión de Orden de Servicio: Creación de solicitudes de servicio técnico vinculadas a clientes. Control de estados dinámicos (Pendiente ➔ Finalizado ➔ Entregado) y gestión de costos.

📄 Reportes de Orden Técnica: Generación automática de comprobantes en formato PDF para el cliente y el taller, con visualización integrada directamente en la aplicación.

🔐 Gestión de Usuarios y Seguridad: Sistema de login con cifrado de contraseñas (MD5) y control de acceso basado en roles (Técnicos, Administradores).

🛠️ Stack Tecnológico y Arquitectura
Lenguaje: Java (JDK 11+)

Frontend / UI: JavaFX 13 (Controladores FXML, Binding automático de tablas, WebView)

Backend / ORM: Hibernate (Implementación de JPA / Jakarta Persistence)

Base de Datos: MySQL 8.0+

Gestor de Dependencias: Apache Maven

Reportes y Exportación: JasperReports, Apache PDFBox, Apache POI, iText

Patrón de Diseño: MVC (Modelo-Vista-Controlador) con capa DAO (Data Access Object)

Modularidad: Java Platform Module System (JPMS)

🚀 Evolución Arquitectónica: Migración y Refactorización
El proyecto ha sido sometido a una migración tecnológica y refactorización profunda para cumplir con los estándares de ingeniería de software modernos. Se pasó de un enfoque monolítico con JDBC a una arquitectura desacoplada:

1. Transición a ORM (JPA/Hibernate)
Pureza del Modelo (POJOs): Se limpiaron las Entidades reemplazando los envoltorios propios de la interfaz (StringProperty) por tipos de datos estándar, independizando el modelo de la vista.

Automatización SQL: Se eliminó la dependencia de consultas SQL manuales concatenadas (JdbcHelper), delegando el mapeo relacional, la creación de tablas (DDL) y la gestión de transacciones (ACID) al motor de Hibernate.

Generación de IDs Segura: Se delegó la creación de claves primarias a la base de datos (GenerationType.IDENTITY), previniendo colisiones en entornos concurrentes.

2. Modernización del Ecosistema (Maven & JPMS)
Gestión Automatizada: Transición de un proyecto tradicional a un modelo basado en pom.xml para la inyección de dependencias (PDFBox, POI, Hibernate, MySQL Connector).

Aislamiento de Módulos (JPMS): Implementación del sistema de módulos de Java (module-info.java) asegurando la compatibilidad mediante la apertura selectiva de paquetes (opens) para permitir la reflexión segura por parte de Hibernate y JavaFX.

Sincronización de Recursos: Optimización del ciclo de Build de Maven para garantizar la correcta inyección del persistence.xml en el classpath.

3. Refactorización de Interfaz y UI/UX
Eliminación de Legacy Swing: Se erradicaron las dependencias heredadas como JOptionPane, reemplazándolas por alertas nativas y asíncronas de JavaFX para evitar bloqueos en el hilo gráfico.

Visor de PDF Integrado: Soporte nativo para visualizar reportes técnicos dentro de la app mediante WebView, WebEngine y renderizado con PDFBox.

⚙️ Instalación y Configuración
Prerrequisitos
Java Development Kit (JDK) 11 o superior.

Apache Maven instalado en el sistema.

Servidor MySQL (8.0+) ejecutándose localmente o en red.

Pasos para el Despliegue
1. Clonar el repositorio:

Bash
git clone https://github.com/tu-usuario/gestionpp3.git
cd gestionpp3
2. Configurar la Base de Datos:

Crea un esquema vacío en MySQL llamado pp3_final.

Verifica y ajusta tus credenciales de base de datos en el archivo de configuración de JPA ubicado en: src/main/resources/META-INF/persistence.xml

Nota: Gracias a la propiedad hibernate.hbm2ddl.auto = update, el sistema construirá automáticamente las tablas y relaciones la primera vez que se ejecute.

3. Compilar y Ejecutar: Abre tu terminal en la raíz del proyecto y ejecuta:

Bash
mvn clean install
mvn javafx:run