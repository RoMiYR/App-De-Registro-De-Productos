package Excepciones;

/**
 *
 * @author josue
 */
public class PrecioInvalidoException extends ProductoException {

    public PrecioInvalidoException() {
        super("El precio debe ser un numero positivo y valido.");
    }

}
