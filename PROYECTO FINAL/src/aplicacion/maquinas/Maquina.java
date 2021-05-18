package aplicacion.maquinas;

import aplicacion.*;
import java.awt.Color;
import java.io.Serializable;


public abstract class Maquina extends Usuario  implements Serializable {

        /**
         * Crea un usuario Maquina, su nombre por defecto es maquina y los colores son rojo y azul
         */
	public Maquina (String nombre, Color[] colores,Posicion posicion, String sentido,Tablero tablero) {
            super(nombre, colores, posicion, nombre, tablero);
	}

	/**
	 * Indica a la maquina cual es la prioridad de su serpiente.
	 */
	public abstract void prioridad();
        
        

}
