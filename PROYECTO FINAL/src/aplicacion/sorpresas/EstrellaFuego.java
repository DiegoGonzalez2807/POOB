package aplicacion.sorpresas;

import aplicacion.*;
import java.awt.Color;
import java.io.Serializable;

public class EstrellaFuego extends Sorpresa implements Serializable {

    private Circle proyectil;
    public EstrellaFuego(Tablero tablero, Posicion posicion) {
        super(tablero,posicion);
	}

    @Override
    public void efecto(Usuario jugador){
        Serpiente python = jugador.getSnake();
        Cabeza cabeza = python.getCabeza();
        Proyectil proyectil = new Proyectil(cabeza.getSentido(),cabeza.getPosicion());
        
    }
}
