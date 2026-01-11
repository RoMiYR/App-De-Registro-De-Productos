package Producto;

import Excepciones.IdNoEncontradoException;
import Excepciones.ProductoException;
import java.sql.*; //Importamos todas las librerias de Java SQL.
import java.util.ArrayList;

/**
 *
 * @author josue
 */
public class ProductoDAO {

    //Variables importantes para realizar la conexion//
    //NO TOCAR//
    private static final String URL = "jdbc:mysql://localhost:3306/Productos";
    private static final String USER = "root";
    private static final String PASS = "1986";

    //Metodo para realizar la conexion.
    public static Connection getConnection() {

        try {
            // No necesitas Class.forName si usas JDBC 4.0+
            return DriverManager.getConnection(URL, USER, PASS);

        } catch (SQLException ex) {
            throw new ProductoException("Ha ocurrido un error inesperado al realizar la conexion a la base de datos!");
        }
    }

    //Este metodo recibe un producto y lo agrega a la base de datos.
    public void agregarProducto(Producto producto) {

        //Preparamos la consulta SQL.
        String consultaSQL = "INSERT INTO productos (nombre, precio, categoria) VALUES (?,?,?)";

        //Preparamos la consulta y usamos un try-catch-source
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(consultaSQL);) {

            //Agregamos los parametros.
            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setString(3, producto.getCategoria());

            //Si por alguna extraña razon no se inserta el producto, lanzamos la excepcion.
            if (ps.executeUpdate() == 0) {
                throw new ProductoException("No se pudo insertar el producto, intentalo de nuevo");
            }

        } catch (SQLException ex) {
            //Lanzamos el error.
            throw new ProductoException("Error de SQL en ProductoDAO: " + ex.getMessage());
        }

    }

    //Metodo para actualizar un producto.
    public void actualizarProducto(Producto producto) {

        //Preparamos la consulta SQL.
        String consultaSQL = "UPDATE Productos SET nombre=?, precio=?, categoria=? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(consultaSQL);) {

            //Agregamos los parametros.
            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setString(3, producto.getCategoria());
            ps.setInt(4, producto.getId());

            if (ps.executeUpdate() == 0) {
                throw new IdNoEncontradoException(producto.getId());
            }

        } catch (SQLException ex) {
            throw new ProductoException("Error de SQL en ProductoDAO: " + ex.getMessage());
        }

    }

    public void eliminarProducto(int id) {
        String consultaSQL = "DELETE FROM productos WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(consultaSQL);) {

            ps.setInt(1, id);

            if (ps.executeUpdate() == 0) {
                throw new IdNoEncontradoException(id);
            }

        } catch (SQLException ex) {
            throw new ProductoException("Error de SQL en ProductoDAO: " + ex.getMessage());
        }

    }

    public ArrayList<Producto> obtenerProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        String consultaSQL = "SELECT * FROM productos";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(consultaSQL); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Producto productoDB = new Producto(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4));
                productos.add(productoDB);
            }

        } catch (SQLException ex) {
            throw new ProductoException("Error: No se pudo completar la conexion a la base de datos!");
        }
        return productos;
    }

    public ArrayList<Producto> buscarProductos(String nombre) {
    ArrayList<Producto> productos = new ArrayList<>();
    // Usamos '?' como marcador de posición estándar
    String consultaSQL = "SELECT * FROM productos WHERE nombre LIKE ?";

    try (Connection conn = getConnection(); 
         PreparedStatement ps = conn.prepareStatement(consultaSQL)) {

        // 1. Configuramos el parámetro CON los comodines aquí
        ps.setString(1, "%" + nombre + "%");

        // 2. Ejecutamos DESPUÉS de setear el parámetro
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                // Usamos nombres de columnas para mayor claridad
                Producto productoDB = new Producto(
                    rs.getInt("id"), 
                    rs.getString("nombre"), 
                    rs.getDouble("precio"), 
                    rs.getString("categoria")
                );
                productos.add(productoDB);
            }
        }

    } catch (SQLException ex) {
        throw new ProductoException("Error al buscar productos: " + ex.getMessage());
    }
    return productos;
}

}
