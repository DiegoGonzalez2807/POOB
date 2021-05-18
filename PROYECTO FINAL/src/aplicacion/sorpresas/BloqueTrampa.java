package aplicacion.sorpresas;

import aplicacion.*;
import java.io.Serializable;

/**
 * Contiene la informacion de un bloque trampa.
 * @author Cristian Castellanos - Diego Gonzalez
 */
public class BloqueTrampa extends Sorpresa implements Serializable {

    /**
     * Crea un bloque que aparece dentro del tablero en la posicion dada
     * @param tablero
     * @param posicion 
     */
    public BloqueTrampa(Tablero tablero, Posicion posicion) {
        super(tablero,posicion);
	}
    
    

}
