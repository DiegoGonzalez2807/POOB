package aplicacion.alimentos;

import aplicacion.*;
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Contiene la informacion de un dulce
 *Disminuye el tamaño de quien lo consume en 1 unidad. En caso que el dulce tenga 
 *uno de los colores de la serpiente contrincante disminuye 2 unidades
 * @author Cristian Castellanos - Diego Gonzalez
 */
public class Dulce extends Alimento implements Serializable  {

    
    public Dulce (Tablero tablero,Posicion posicion) {
        super(tablero,posicion);
        this.icono = "imagenes/dulce1.png";
        this.puntaje = 2;
	}

    @Override
    public void accion(Serpiente serpiente) {
        ArrayList<Usuario> usuarios = tablero.getSnOOpe().getJugadores();
        boolean safe = false;
        for(Usuario usuario:usuarios){
            Serpiente snake = usuario.getSnake();
            Color[] colores = snake.getColor();
            if(!snake.equals(serpiente) && (color.equals(colores[0])||color.equals(colores[1]))){
                safe = true;
                break;
            }
        }
        if(safe){
            serpiente.disminuir(2);
        }
        else{
            serpiente.disminuir(1);
        }
    }

}
