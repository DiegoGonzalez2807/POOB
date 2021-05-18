package aplicacion.maquinas;


import aplicacion.*;
import java.awt.Color;
import java.io.Serializable;

public class Distraida extends Maquina implements Serializable  {

	public Distraida (String nombre, Color[] colores,Posicion posicion, String sentido,Tablero tablero) {
            super(nombre, colores, posicion, nombre, tablero);
            
	}

    @Override
    public void prioridad() {
        
    }

}
