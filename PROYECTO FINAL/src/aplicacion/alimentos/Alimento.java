package aplicacion.alimentos;

import aplicacion.*;
import java.io.Serializable;

/**
 * Contiene la informacion de los alimentos que puede consumir la serpiente
 * @author Cristian Castellanos - Diego Gonzalez
 * @version 1.0
 */
public abstract class Alimento extends Objeto implements Serializable {

        /**
         * Crea un alimento en el tablero asignado, en la posicion dada
         * @param tablero Tablero en donde se encuentra el alimento
         * @param posicion Posicion en el tablero en donde se encuentra el objeto
         */
        public Alimento(Tablero tablero, Posicion posicion){
            this.tablero=tablero;
            this.posicion = posicion;
            tablero.setObjeto(this,posicion.getPosicion()[0],posicion.getPosicion()[1]);
        }
        
	protected int puntaje;
        
	public int getPuntaje() {
		return puntaje;
	}

        /**
         * Genera la accion del alimento en la serpiente designada
         * @param serpiente
         * @throws SnOOpExcepcion 
         *      MUERTE:_INTOXICADO - Si el alimento es veneno
         */
	public abstract void accion(Serpiente serpiente) throws SnOOpExcepcion;
                

}
