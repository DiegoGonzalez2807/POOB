package aplicacion.alimentos;

import aplicacion.*;
import java.io.Serializable;

/**
 * Contiene la informacion del veneno.
 * La serpiente que lo come morirá por intoxicación.
 * @author Cristian Castellanos - Diego Gonzalez
 */
public class Veneno extends Alimento implements Serializable  {
    
    /**
     * Crea un alimento veeno en la posicion dada, del tablero designado
     * @param tablero
     * @param posicion 
     */
    public Veneno (Tablero tablero,Posicion posicion) {
        super(tablero,posicion);
        setIcon("imagenes/veneno1.png");
        this.puntaje = 2;
	}

    @Override
    public void accion(Serpiente serpiente) throws SnOOpExcepcion {
        throw new SnOOpExcepcion(SnOOpExcepcion.MUERTE_INTOXICADO);
    }



}
