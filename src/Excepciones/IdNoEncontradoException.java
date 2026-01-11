/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excepciones;

/**
 *
 * @author josue
 */
public class IdNoEncontradoException extends ProductoException {

    public IdNoEncontradoException(int id) {
        super("El producto con ID " + id + " no existe en la base de datos");
    }

}
