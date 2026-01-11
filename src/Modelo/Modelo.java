package Modelo;

import Excepciones.PrecioInvalidoException;
import Excepciones.ProductoException;
import Producto.Producto;

/**
 *
 * @author josue
 */
public class Modelo {

    //Metodo para comprobar los datos y retornar un producto nuevo.
    public Producto comprobarProductoAgregar(String nombre, String precio, String categoria) {

        //Llamamos al metodo auxiliar para validar mas rapido.
        if (esInvalido(nombre) || esInvalido(precio) || esInvalido(categoria)) {
            throw new ProductoException("Campos incompletos: Asegurate de llenar nombre, precio y categoria.");
        }

        //Intentamos hacer la conversion de String a Integer.
        try {
            double precioDouble = Double.parseDouble(precio);
            validarPrecio(precioDouble);
            //Si todo sale bien, creamos el producto y lo enviamos por el return.
            return new Producto(nombre, precioDouble, categoria);
        } catch (NumberFormatException ex) {
            throw new PrecioInvalidoException();
        }

    }

    //Metodo para comprobar los datos y actualizar un producto de la lista ya existente.
    public Producto comprobarDatosActualizar(String id, String nombre, String precio, String categoria) {

        if(id.trim().isEmpty()){
            throw new ProductoException("Error: Debes ingresar un Id");
        }
        
        //Creamos un nuevo producto con el metodo que ya ocupamos.
        Producto producto = comprobarProductoAgregar(nombre, precio, categoria);

        //Si no es nulo, intentamos convertir el String a Integer dentro de un bloque try-catch.
        try {
            producto.setId(Integer.parseInt(id));
            return producto;
        } catch (NumberFormatException ex) {
            throw new ProductoException("Error: El ID debe ser un numero entero valido.");
        }

    }

    //Metodos de apoyo.
    //Metodo para validar un String.
    private boolean esInvalido(String cadena) {
        return cadena == null || cadena.isBlank();
    }

    //Metodo para validar el precio.
    public void validarPrecio(double precio) {
        if (precio <= 0) {
            throw new ProductoException("El precio debe ser un numero mayor a 0.00");

        }
    }

    public int validarId(String id) {
        try {
            int idEntero = Integer.parseInt(id);
            if (idEntero <= 0) {
                throw new ProductoException("El id debe ser mayor a 0!");
            }
            return idEntero;
        } catch (NumberFormatException ex) {
            throw new ProductoException("El ID '" + id + "' no es un número válido. Por favor, ingresa solo números enteros.");
        }
    }

}
