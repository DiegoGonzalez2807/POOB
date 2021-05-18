/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion ;

/**
 * Contiene la informacion de un proyectil.
 * Este permite al jugador destruir obstacuulos o partir al contrincante pór el punto de impacto
 */
import aplicacion.sorpresas.*;
import java.io.Serializable;

/**
 * Guarda l ainformacion de un proyectil
 * @author Cristian
 */
public class Proyectil extends Objeto implements Serializable  {
    
    public Proyectil(String direccion, Posicion posicion){
        setPosicion(direccion, posicion);
        int[] local = this.getPosicion().getPosicion();
        //Si el proyectil es disparado a la frontera, este no se posiciona en el tablero
        if(0<=local[0] && local[0]<tablero.getFila() && 0<=local[1] && local[1]<tablero.getColumna()){
            //Si el proyectil es lanzado directamente sobre un objeto, es decir justo al lado de este, se generan distintas acciones
                //Si el objeto impactado es un muro, este desaparece
            if(tablero.getObjeto(local[0],local[1]) instanceof BloqueTrampa){
                impactarBloque(local);
            }
                //Si el objeto impactado es parte de una serpiente
            else if(tablero.getObjeto(local[0],local[1]) instanceof Circle || tablero.getObjeto(local[0],local[1]) instanceof Cabeza){
                impactarSerpiente(local);
            }
            else if(tablero.getObjeto(local[0],local[1]) == null);
                tablero.setObjeto(this,local[0],local[1]);
                try{
                    while(true){
                        this.mover(direccion);
                    }
                }
                catch(SnOOpExcepcion e){
                    local = this.getPosicion().getPosicion();
                    //Si al mover el proyectil choca con algo, se genran distintas acciones
                    if(e.getMessage().equals(SnOOpExcepcion.CHOQUE_BLOQUE)){
                        impactarBloque(local);
                    }
                    else if(e.getMessage().equals(SnOOpExcepcion.CHOQUE_FRONTERA)){
                        this.desaparecer();
                    }
                    else if(e.getMessage().equals(SnOOpExcepcion.DESTRUIR)){
                        impactarSerpiente(local);
                    }


                }
        }
    }
    
    /**
     * Genera la accion cuando el proyectil impacta una serpiente
     * @param local Posicion en donde impacta el proyectil
     */
    private void impactarSerpiente(int[] local){
        //Se busca la serpiente que contiene el circulo impactado
        for(Usuario jugador:tablero.getSnOOpe().getJugadores()){
            Serpiente python = jugador.getSnake();
            //Si el objeto es parte del cuerpo de la serpiente, la serpiente se divide en ese punto
            if(tablero.getObjeto(local[0],local[1]) instanceof Circle){
                for(Circle circle:python.getCuerpo()){
                    if(python.getCuerpo().indexOf(tablero.getObjeto(local[0],local[1])) != -1){
                        python.dividir(posicion);
                    }
                }
            }
            //Si el objeto impactado es la cabeza, la serpiente pierde su cuerpo
            else{
                if(tablero.getObjeto(local[0],local[1]).equals(python.getCabeza())){
                    python.disminuir(python.getLongitud());
                }
            }
        }       
    }
    
    /**
     * Genera la accion cuando el proyectil impacta un bloque
     * @param local Posicion en donde ompacta el proyectil
     */
    private void impactarBloque(int[] local){
        tablero.getObjeto(local[0], local[1]).desaparecer();
    }
    
}
