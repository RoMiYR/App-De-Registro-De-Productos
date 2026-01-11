# 📦 Sistema de Registro de Productos (CRUD)

¡Bienvenidos! Este es mi proyecto final tras completar el 90% de mi formación intensiva en Java. Es un sistema de gestión de inventario robusto que conecta una interfaz gráfica con una base de datos real.

## 🚀 Características principales
- **Arquitectura MVC:** Separación total de responsabilidades (Modelo, Vista, Controlador).
- **Persistencia de Datos:** Conexión estable a base de datos MySQL mediante JDBC.
- **Seguridad y Robustez:** Implementación de `PreparedStatement` para evitar Inyecciones SQL y manejo de excepciones personalizadas para validar datos (como precios negativos).
- **Interfaz Intuitiva:** Buscador en tiempo real, tabla dinámica y gestión completa de productos (Crear, Leer, Actualizar, Eliminar).

## 🛠️ Tecnologías utilizadas
- **Lenguaje:** Java 21 (Core Java)
- **Base de Datos:** MySQL
- **Librerías:** JDBC MySQL Driver
- **IDE:** NetBeans

## 🏗️ Estructura del Proyecto
El código está organizado siguiendo estándares de la industria:
- `src/Modelo`: Lógica de negocio y entidades.
- `src/DAO`: Acceso a datos (Consultas SQL).
- `src/Controlador`: Intermediario entre la vista y la lógica.
- `src/Vista`: Interfaz gráfica desarrollada con Swing.

## 📝 Cómo probar el proyecto
1. Clona este repositorio.
2. Importa el archivo SQL (adjunto abajo) en tu servidor MySQL.
3. Configura las credenciales de conexión en la clase `Conexion`.
4. Ejecuta el archivo `Main.java`.

### Script SQL para la Base de Datos
```sql
CREATE DATABASE sistema_productos;
USE sistema_productos;

CREATE TABLE productos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    precio DOUBLE NOT NULL,
    categoria VARCHAR(50)
);

Este proyecto representa un salto gigante en mi carrera como desarrollador, pasando de conceptos básicos a la creación de software profesional funcional.
