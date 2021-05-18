package aplicacion;

import java.io.Serializable;


/**
 * Contiene la informacion de las excepciones que se dan en el juego
 * @author Cristian Castellanos - Diego Gonzalez
 * @version 1.0
 */
public class SnOOpExcepcion extends Exception implements Serializable  {

	public static final String COLOR_INVALIDO = "El color ingresado es invalido";

	public static final String FIN = "El juego ha terminado";

	public static final String CHOQUE_PROPIO = "La serpiente ha chocado con su cuerpo";

        public static final String CHOQUE_VECINO = "La serpiente ha chocado con la serpiente del otro jugador";
        
        public static final String CHOQUE_BLOQUE = "La serpiente ha chocado con un bloque";
        
        public static final String CHOQUE_FRONTERA = "La serpiente ha chocado con la frontera";
        
	public static final String COLOR_REPETIDO = "El color ya fue seleccionado";

	public static final String MUERTE_INTOXICADO = "La serpiente ha sido envenenada";

	public static final String MULTIPLO_CINCO = "La serpiente tiene una longitud multiplo de 5";
        
        public static final String COMER_ALIMENTO = "Se ha detectado un alimento";
        
        public static final String COMER_SORPRESA = "Se ha detectado una sorpresa";
        
        public static final String DESTRUIR = "El proyectil ha alcanzado el objetivo";
        
        public SnOOpExcepcion(String e){
            super(e);
        }

}
