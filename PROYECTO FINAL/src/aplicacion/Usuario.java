package aplicacion;

import java.awt.Color;
import java.io.Serializable;

public abstract class Usuario implements Serializable  {

	protected String nombre;

	protected Color[] colores;

	protected int puntos;

	protected int sorpresas;

	protected String sorpresa;

	protected Serpiente serpiente;
        
        protected Tablero tablero;

	/**
	 * Genera un nuevo usuario 
	 */
	public Usuario (String nombre, Color[] colores,Posicion posicion, String sentido,Tablero tablero) {
            //Se inicializan los atributos 
            this.nombre = nombre;
            this.colores = colores;
            this.tablero = tablero;
            serpiente = new Serpiente(colores,posicion,sentido,tablero);
            puntos = 0;
            sorpresas = 0;
	}

	/**
	 * Mueve la serpiente en la direccion indicada dentro del tablero
	 * @param direccion - Direccion hacia donde se mueve la serpiente, esta puede ser Arriba, Abajo, Izquierda, Derecha.
	 * 
	 */
	public void mover(String direccion) throws SnOOpExcepcion{
            try{serpiente.mover(direccion);}
            catch(SnOOpExcepcion e){throw e;}
        }

	public int getPuntaje() {
		return serpiente.getLongitud();
	}

	public void sumPuntaje(int puntaje) {
            this.puntos += puntaje;
	}

	public final void setColor(Color[] colores) {
            this.colores=colores;
	}

	public final void setNombre(String nombre) {
            this.nombre=nombre;
	}
        
        public final String getNombre(){return this.nombre;}

	public Serpiente getSnake() {
		return serpiente;
	}
        
        public void setSentido(String sentido){
            this.serpiente.setSentido(sentido);
        }
        
        public void setTablero(Tablero tablero){this.tablero = tablero;}
        
        /**
         * Funcion que devuelve las sorpresas usadas
         * @return Numero de sorpresas usadas por el jugador
         */
        public int getSorpresas(){
            return this.sorpresas;
        }
        
        /**
         * Acciona el la accion de la sopresa que tiene la serpiente en el momento
         */
        public void usarSorpresa(){
            serpiente.usarSorpresa(this);
        }
}
