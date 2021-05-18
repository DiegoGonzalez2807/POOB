package aplicacion.maquinas;


import aplicacion.*;
import java.awt.Color;
import java.io.Serializable;

public class Glotona extends Maquina implements Serializable  {

	public Glotona (String nombre, Color[] colores,Posicion posicion, String sentido,Tablero tablero) {
            super(nombre, colores, posicion, nombre, tablero);
	}

    @Override
    public void prioridad() {
    }

}
