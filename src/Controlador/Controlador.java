package Controlador;

import Excepciones.ProductoException;
import Modelo.Modelo;
import Producto.Producto;
import Producto.ProductoDAO;
import Vista.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author josue
 */
public class Controlador implements ActionListener {

    private final Modelo Modelo;
    private final Vista Vista;
    private final ProductoDAO ProductoDAO;

    public Controlador(Modelo Modelo, Vista Vista, ProductoDAO ProductoDAO) {
        this.Modelo = Modelo;
        this.Vista = Vista;
        this.ProductoDAO = ProductoDAO;

        this.Vista.configurarTabla();

        // Cargamos los datos en un hilo separado para no bloquear la vista
        this.Vista.cargarTabla(ProductoDAO.obtenerProductos());

        this.Vista.setLocationRelativeTo(null);
        this.Vista.addActionListener(this);
        this.Vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case "Insertar" -> {

                try {
                    Producto nuevoProducto = Modelo.comprobarProductoAgregar(Vista.getNombre(), Vista.getPrecio(), Vista.getCategoria());
                    ProductoDAO.agregarProducto(nuevoProducto);
                    Vista.mostrarMensajeExito("Producto agregado correctamente");
                    Vista.limpiarCampos();
                    Vista.cargarTabla(ProductoDAO.obtenerProductos());
                } catch (ProductoException ex) {
                    Vista.mostrarMensajeError(ex.getMessage());
                } catch (Exception ex) {
                    Vista.mostrarMensajeError("Error inesperado: " + ex.getMessage());
                }

            }
            case "Actualizar" -> {

                try {
                    Producto nuevoProducto = Modelo.comprobarDatosActualizar(Vista.getId(), Vista.getNombre(), Vista.getPrecio(), Vista.getCategoria());
                    ProductoDAO.actualizarProducto(nuevoProducto);
                    Vista.mostrarMensajeExito("Se ha actualizado el producto con el id " + nuevoProducto.getId() + " correctamente");
                    Vista.limpiarCampos();
                    Vista.cargarTabla(ProductoDAO.obtenerProductos());
                } catch (ProductoException ex) {
                    Vista.mostrarMensajeError(ex.getMessage());
                } catch (Exception ex) {
                    Vista.mostrarMensajeError("Error inesperado: " + ex.getMessage());
                }

            }
            case "Eliminar" -> {
                try {
                    ProductoDAO.eliminarProducto(Modelo.validarId(Vista.getId().trim()));
                    Vista.mostrarMensajeExito("Se ha eliminado el producto con el id " + Vista.getId().trim() + " con exito");
                    Vista.limpiarCampos();
                    Vista.cargarTabla(ProductoDAO.obtenerProductos());
                } catch (ProductoException ex) {
                    Vista.mostrarMensajeError(ex.getMessage());
                } catch (Exception ex) {
                    Vista.mostrarMensajeError("Error inesperado: " + ex.getMessage());
                }
            }
            case "Buscar" -> {
                try {
                    Vista.cargarTabla(ProductoDAO.buscarProductos(Vista.getNombreBuscar()));
                } catch (ProductoException ex) {
                    Vista.mostrarMensajeError(ex.getMessage());
                }
            }

        }

    }

}
