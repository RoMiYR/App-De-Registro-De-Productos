
package Main;

import Controlador.Controlador;
import Modelo.Modelo;
import Producto.ProductoDAO;
import Vista.Vista;

/**
 *
 * @author josue
 */
public class Main {
    public static void main(String[] args) {
        
        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        ProductoDAO productoDAO = new ProductoDAO();
        
        new Controlador(modelo, vista, productoDAO);
        
    }
}
