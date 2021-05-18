package aplicacion.alimentos;

import aplicacion.*;
import java.awt.Color;
import java.io.Serializable;

/**
 * Contiene la informacion de una fruta
 * Aumenta el tamaño de quien la consume en 1 unidad. En caso que la serpiente 
 * tenga uno de los colores de la fruta aumenta 2 unidades. 
 * @author Cristian Castellanos - Diego Gonzalez
 */
public class Fruta extends Alimento implements Serializable {

    /**
     * Crea una fruta en la posicion del tablero asignado
     * @param tablero
     * @param posicion 
     */
    public Fruta (Tablero tablero,Posicion posicion) {
        super(tablero,posicion);
        setIcon("imagenes/manzana1.png");
        this.puntaje = 1;
	}
    
    @Override
    public void accion(Serpiente serpiente) {
       Color [] colores = serpiente.getColor();
       if(this.color.equals(colores[0]) || this.color.equals(colores[1])){
           serpiente.crecer(2);
       }
       else{
           serpiente.crecer(1);
       }
    }

}
