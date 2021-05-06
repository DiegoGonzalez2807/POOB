package domain;
import java.io.*;

/** 
 * Contiene la informacion de los posibles errores que se den en el codigo
 * @author Cristian Castellanos- Diego Gonzalez
 * @version 2.0
*/

public class AutomataExcepcion extends Exception implements Serializable{
    public static final String OPCION_CONSTRUCCION = "Opcion en construccion";
    public static final String CLASE_NO_ENCONTRADA = "La clase no ha sido encontrada";

    /**
     * Crea una excepcion para este caso
     * @param message Mensaje a mostrar
     */

    public AutomataExcepcion(String message){
        super(message);
    }
}
