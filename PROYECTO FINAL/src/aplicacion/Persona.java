package aplicacion;

import java.awt.Color;
import java.io.Serializable;

public class Persona extends Usuario implements Serializable  {
    
    public Persona(String nombre, Color[] colores,Posicion posicion, String sentido,Tablero tablero){
        super(nombre, colores, posicion, sentido, tablero);
    }
}
