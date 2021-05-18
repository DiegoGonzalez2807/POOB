/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import aplicacion.alimentos.*;
import aplicacion.sorpresas.*;
import java.io.Serializable;


/**
 * Contiene la informacion del tablero del juego de SnOOpe, en esta se guardan las serpientes, y los distintos objetos
 * @author Cristian Castellanos- Diego Gonzalez
 * @version 1.0
 */
public class Tablero implements Serializable {
    
    private int fila;
    private int columna;
    private Objeto[][] tablero;
    private SnOOpe snake;
    
    /**
     * Crea un tablero con los parametros dados
     * @param fila - Numero de filas del tablero
     * @param columna - Numero de columnas del tablero
     */
    public Tablero(int fila,int columna,SnOOpe snake){
        this.fila=fila;
        this.columna=columna;
        tablero = new Objeto[fila][columna];
        this.snake = snake;
    }
    /**
     * Adsigna a la casilla asignada un objeto, y al objeto le asigna el tablero
     * @param objeto - OBjeto a asignar en la casilla
     * @param fila - Fila de la casilla a incluir el objeto
     * @param columna - Columna de la casilla a incluir el objeto
     */
    public void setObjeto(Objeto objeto,int fila, int columna){
        tablero[fila][columna] = objeto;
        if(objeto!=null){
            objeto.setTablero(this);
        }
    }
    
    /**
     * Asigna a la casilla posterior en la direccion indicada el objeto dado
     * @param objeto- Objeto a asignar en la casiila
     * @param direccion - Direccion que indica donde se pondra el objeto
     * @param fila - Fila que indica la casilla guia
     * @param columna - Columna que indica la casilla guia
     */
    public void setObjeto(Objeto objeto,String direccion,int fila,int columna){
        switch(direccion){
            case "Arriba":
                if(fila-1>=0){setObjeto(objeto,fila-1,columna);}
                break;
            case "Abajo":
                if(fila+1<this.fila){setObjeto(objeto,fila+1,columna);}
                break;
            case "Derecha":
                if(columna+1<this.columna){setObjeto(objeto,fila,columna+1);}
                break;
            case "Izquierda":
                if(columna-1>=0){setObjeto(objeto,fila, columna-1);}
                break;
        }
    }
    
    /**
     * Funcion creada para retornar el objeto en la casilla especificada
     * @param fila - Fila de la casilla donde esta el objeto pedido
     * @param columna - Columna de la casilla en dond esta el objeto pedido
     * @return - Objeto pedido en la posicion indicada
     */
    public Objeto getObjeto(int fila, int columna){
        return tablero[fila][columna];
    }
    
    /**
     * Funcion creada para retornar el objeto siguiente en la direccion indicada
     * @param fila- Fila de la casilla guia
     * @param columna- Columna de la casilla guia
     * @param direccion - Direccion en donde se encuentra el objeto
     * @return Objeto siguiente al guia en la direccion dada
     */
    public Objeto getObjeto(String direccion,int fila, int columna){
        Objeto objeto = null;
        switch(direccion){
            case "Arriba":
                if(fila-1>=0){objeto = getObjeto(fila-1,columna);}
                break;
            case "Abajo":
                if(fila+1<this.fila){objeto = getObjeto(fila+1,columna);}
                break;
            case "Derecha":
                if(columna+1<this.columna){objeto = getObjeto(fila,columna+1);}
                break;
            case "Izquierda":
                if(columna-1<this.columna){objeto = getObjeto(fila, columna-1);}
                break;
        }
        return objeto;
    }

    
   /**Mueve el objeto indicado en la direccion indicada
    *@param direccion - Direccion en donde se mueve el objeto esta puede ser "Arriba", "Abajo", "Izquierda", "Derecha"
    *@param fila - Fila en donde se encuentra el objeto a mover
    *@param columna - Columna en donde se encuentra el objeto a mover
    *@throws SnOOpExcepcion 
    *   COMER_ALIMENTO -Si se encuentra un alimento en la posicion destino del objeto
    *   COMER_SORPRESA - Si se encuentra una sorpresa en la posicion destino del objeto
    *   DESTRUIR - Si el objeto es un proyectil y en el destino se encuentra una parte de una serpiente
    */
    public void mover(String direccion,int fila,int columna) throws SnOOpExcepcion{
        Objeto objeto = tablero[fila][columna]; 
        Objeto destino = null;
        int[] to = null;
        //Se valida que la posicion no este vacia
        if(objeto!=null){
            try{
                to = destino(fila,columna,direccion);
                destino = tablero[to[0]][to[1]];}
            catch(SnOOpExcepcion e){throw e;}
            //Se valida si el objeto es parte de la serpiente de la serpiente
            if(objeto instanceof Cabeza){
                    //Si la posicion a donde se mueve el objeto es una sorpresa
                if(destino instanceof Sorpresa){
                    throw new SnOOpExcepcion(SnOOpExcepcion.COMER_SORPRESA);}
                    //Si la posicion a donde se mueve el objeto es un alimento
                else if(destino instanceof Alimento){throw new SnOOpExcepcion((SnOOpExcepcion.COMER_ALIMENTO));} 
                    //Se valida si en el destino se encuentra un bloque
                else if(destino instanceof BloqueTrampa){throw new SnOOpExcepcion(SnOOpExcepcion.CHOQUE_BLOQUE);}
                    //Se valida si el destino es parte de la serpiente
                else if(destino instanceof Circle){throw new SnOOpExcepcion(SnOOpExcepcion.CHOQUE_PROPIO);}
            }
            //Si el objeto a mover es un proyectil, y en destino hay una parte de serpiente
            else if(objeto instanceof Proyectil && destino instanceof Circle){throw new SnOOpExcepcion(SnOOpExcepcion.DESTRUIR);}
            //Se actualiza la posicion del objeto
            objeto.setPosicion(new Posicion(to));
            setObjeto(objeto,to[0],to[1]);
            setObjeto(null, fila, columna);
        }
    }
    public void mover(String direccion, Posicion posicion) throws SnOOpExcepcion{
        try{
            int[] local = posicion.getPosicion();
            mover(direccion,local[0],local[1]);
        }
        catch(SnOOpExcepcion e){throw e;}
    }
    
        /**
     * Revisa la posicion siguiente de la casilla en la posicion dada,para saber si es posible el movimiento
     * @param fila Fila en donde se encuentra la casilla dentro del tablero
     * @param columna Columna en donde sencuentra la casilla dentro del tablero
     * @param direccion Direccion en donde se encuentra la casilla a revisar
     * @return Retorna la posicion de la casilla en donde se coloca el objeto
     * @throws SnOOpExcepcion
     *      CHOQUE_FRONTERA - Si se estrella contra la frontera la serpiete,e s decir se sale de rango en el movimiento
     */
    private int[] destino(int fila, int columna, String direccion) throws SnOOpExcepcion{
        int[] to = new int[]{fila,columna};
        Objeto destino = null;
        switch(direccion){
            case "Arriba" -> {
                if(fila == 0){throw new SnOOpExcepcion((SnOOpExcepcion.CHOQUE_FRONTERA));}
                destino = tablero[fila-1][columna];
                to = new int[]{fila-1,columna};
            }
            case "Abajo" -> {
                if(fila == this.fila-1){throw new SnOOpExcepcion(SnOOpExcepcion.CHOQUE_FRONTERA);}
                destino = tablero[fila+1][columna];
                to = new int[]{fila+1,columna};
            }
            case "Derecha" -> {
                if(columna == this.columna-1){throw new SnOOpExcepcion(SnOOpExcepcion.CHOQUE_FRONTERA);}
                destino = tablero[fila][columna+1];
                to = new int[]{fila,columna+1};
            }
            case "Izquierda" -> {
                if(columna == 0){throw new SnOOpExcepcion(SnOOpExcepcion.CHOQUE_FRONTERA);}
                destino = tablero[fila][columna-1];
                to = new int[]{fila,columna-1};
            }
        }
        return to;
    }
    
    public int getFila(){return fila;}
    public int getColumna(){return columna;}
    
    public SnOOpe getSnOOpe(){return this.snake;}

    public static void main(String[] args) {
        Tablero tablero =  new Tablero(15,15,null);
        tablero.setObjeto(null,1,2);
    }
}
