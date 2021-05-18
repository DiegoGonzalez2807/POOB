package aplicacion.sorpresas;

import aplicacion.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * COntiene la informacion de la sorpresa Division
 * Reduce a la mitad el tamaño del contrincante
 * @author Cristian Castellanos - Diego Gonzalez
 */
public class Division extends Sorpresa implements Serializable  {
    
    /**
     * Crea una sorpresa division en la posicion del tablero designada.
     * @param tablero
     * @param posicion 
     */
    public Division (Tablero tablero, Posicion posicion) {
        super(tablero,posicion);
	}

    @Override
    public void efecto(Usuario jugador){
        ArrayList<Usuario> jugadores = tablero.getSnOOpe().getJugadores();
        Serpiente snake = null;
        for(Usuario player:jugadores){
            if(!player.getNombre().equals(jugador.getNombre())){
                snake = player.getSnake();
                snake.disminuir((int)(snake.getLongitud()/2));
            }
        }
    }

}
