package aplicacion.alimentos;

import aplicacion.*;
import java.io.Serializable;

/**
 * Contiene la informacion de una fruta arcoiris.
 * Aumenta el tamaño de quien la consume en 3 unidades
 * @author Cristian Castellanos - Diego Gonzalez
 */
public class FrutaArcoiris extends Fruta implements Serializable {
    
    /**
     * Crea una fruta arcoiris en la posicion designada del tablero dado
     * @param tablero
     * @param posicion 
     */
    public FrutaArcoiris (Tablero tablero,Posicion posicion){
            super(tablero,posicion);
            setIcon("imagenes/tricolor1.png");
            this.puntaje = 3;
	}
    
    @Override
    public void accion(Serpiente serpiente){
        serpiente.crecer(3);
    }
    
    

}
