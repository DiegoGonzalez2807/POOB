package domain;
/** 
 * Contiene la informacion de los posibles errores que se den en el codigo
 * @author Cristian Castellanos- Diego Gonzalez
 * @version 2.0
*/

public class AutomataExcepcion extends Exception{
    public static final String OPCION_CONSTRUCCION = "Opcion en construccion";

    /**
     * Crea una excepcion para este caso
     * @param message Mensaje a mostrar
     */

    public AutomataExcepcion(String message){
        super(message);
    }
}
