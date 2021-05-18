package aplicacion.sorpresas;

import aplicacion.*;
import java.io.Serializable;

public abstract class Sorpresa extends Objeto implements Serializable  {

	private Posicion posicion;

        /**
         * Crea una sorpresa en el tablero en la posicion dada
         * @param tablero Tablero en donde se pone la sorpresa
         * @param posicion Posicion en donde se pone la sorpresa
         */
        public Sorpresa(Tablero tablero, Posicion posicion){
            this.tablero=tablero;
            this.posicion = posicion;
            tablero.setObjeto(this,posicion.getPosicion()[0],posicion.getPosicion()[1]);
        }
        
        /**
         * Realiza el efecto de la persona designada en la serpiente dada
         * @param usuario Usuario que acciona la sorpresa
         */
	public void efecto(Usuario jugador) {

	}

}
