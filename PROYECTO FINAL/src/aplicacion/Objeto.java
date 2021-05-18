package aplicacion;
import java.awt.Color;
import java.io.Serializable;

/**
 * Clase que representa un objeto en el juego
 * @author Cristian Castellanos- Diego Gonzalez
 * @version 1.0
 */
public abstract class Objeto implements Serializable {

	protected String icono;

	protected Color color;

	protected Posicion posicion;

        protected boolean isVisible;
        
        protected Tablero tablero;
	/**
	 * Vuelve visible el objeto
	 * 
	 */
	public void makeVisible(){
            this.isVisible = true;
        }

	/**
	 * Vuelev invisible el objeto
	 */
	public void makeInvisible(){
            this.isVisible = false;
        }

	/**
	 * Indica al objeto el color
         * @param color1 - Color del objeto
	 */
	public void setColor(Color color1) {
            this.color=color1;
	}
        public Color getColor(){return this.color;}

	/**
	 * Mueve el objeto en la direccion indicada
	 * @param direccion - Direccion hacia donde se mueve la serpiente, esta puede ser Arriba, Abajo, Izquierda, Derecha.
         *@throws SnOOpExcepcion 
         *   COMER_ALIMENTO -Si se encuentra un alimento en la posicion destino del objeto
         *   COMER_SORPRESA - Si se encuentra una sorpresa en la posicion destino del objeto
         *   DESTRUIR - Si el objeto es un proyectil y en el destino se encuentra una parte de una serpiente
	 */
	public  void mover(String direccion) throws SnOOpExcepcion{
            try{
                tablero.mover(direccion,posicion);
            }
            catch(SnOOpExcepcion e){throw e;}
        }
        
        /**
         * Asigna al objeto su posicion
         * @param posicion 
         */
	public void setPosicion(Posicion posicion){
            this.posicion=posicion;
        }
        
        /**
         * Asigna al objeto su posicion en la direccion dada
         * @param direccion Direccion hacia donde se posicionara el objeto
         * @param posicion Posicion en donde se ubicara el objeto
         */
        public void setPosicion(String direccion, Posicion posicion){
            Posicion local = null;
            int x = posicion.getPosicion()[0];
            int y = posicion.getPosicion()[1];
            switch(direccion){
            case "Arriba":
                if(x-1>=0){local = new Posicion(x-1,y);}
                break;
            case "Abajo":
                if(x+1<tablero.getFila()){local = new Posicion(x-1,y);}
                break;
            case "Derecha":
                if(y+1<tablero.getColumna()){local = new Posicion(x-1,y);}
                break;
            case "Izquierda":
                if(y-1>=0){local = new Posicion(x-1,y);}
                break;
            }
        }

	public String getIcono() {
            return this.icono;
	}
        
        public Posicion getPosicion(){return posicion;}

        public void setTablero(Tablero tablero){this.tablero=tablero;}
        public Tablero getTablero(){return this.tablero;}
        
        public void setIcon(String icon){this.icono = icon;}
        public String getIcon(){return this.icono;}
        
        /**
         * Elimina el objeto del tablero del juego
         */
        public void desaparecer(){
            makeInvisible();
            int[] pos = posicion.getPosicion();
            tablero.setObjeto(null, pos[0], pos[1]);
        }
}
