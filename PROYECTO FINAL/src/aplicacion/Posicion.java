package aplicacion;

import java.io.Serializable;

/**
 * Contiene la informacion de la posicion x,y de un objeto del juego
 * @author Cristian Castellanos- Diego Gonzalez
 * @version 1.0
 * 
 * 
 */
public class Posicion implements Serializable  {

	private int x;

	private int y;

        /**
         * Genera una posicion, que indica una casilla en un tablero
         * @param x - Fila
         * @param y - Columna
         */
	public Posicion (int x, int y) {
            this.x=x;
            this.y=y;
	}
        
        public Posicion(int[] posicion){
            this(posicion[0],posicion[1]);
        }
        
	public int[] getPosicion() {
		return new int[]{x,y};
	}
        
        /**
         * Aumenta la coordenada x de la posicion en n unidades
         * @param n - Distancia a aumnetar la coordenada x
         */
        public void moverHorizontalmente(int n){
            x+=n;
        }
        
        /**
         * Aumenta la coordenada y de la posicion en n unidades
         * @param n - Distancia a aumnetar la coordenada y
         */
        public void moverVerticalmente(int n){
            y+=n;
        }

        
}
