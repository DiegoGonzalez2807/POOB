package aplicacion.maquinas;

import aplicacion.*;
import java.awt.Color;
import java.io.Serializable;

public class Prudente extends Maquina implements Serializable {

    public Prudente (String nombre, Color[] colores,Posicion posicion, String sentido,Tablero tablero) {
            super(nombre, colores, posicion, sentido, tablero);
    }

    @Override
    public void prioridad() {
    }

}
