package aplicacion;

import java.awt.Color;
import java.util.*;
import aplicacion.alimentos.*;
import aplicacion.sorpresas.*;
import java.io.Serializable;

/**
 * Simula la serpiente a manejar en el jeugo, esta puede ser circular o cuadrada.
 * @author Cristian Castellanos- Diego Gonzalez
 * @version 1.0
 * 
 */

public class Serpiente implements Serializable {

    private Color[] colores;
    private int longitud;
    private int velocidad;//100
    private Posicion posicion;
    private Tablero tablero;
    private Stack<Circle> cuerpo;
    private Cabeza cabeza;
    private String sentido;//Representa hacia donde se dirige la serpiente
    private boolean isVisible;
    private Sorpresa sorpresa; 

    /**
     * Genera una nueva serpiente con los parametros dados y con una longitud inicial de 3.
     * @param color1 - Color de la cabeza de la serpiente
     * @param color2 - Color de el cuerpo de la serpiente
     * @param posicion - Posicion inicial de la serpiente
     * 
     */
    public Serpiente (Color color1, Color color2, Posicion posicion,String sentido,Tablero tablero) {
        colores = new Color[2];
        cuerpo = new Stack<Circle>();
        //Se asgnan los colores
        colores[0]=color1;
        colores[1]=color2;
        //Se asigna la posicion;
        this.posicion = posicion;
        this.cabeza = new Cabeza(color1,this, sentido, posicion);
        setSentido(sentido);
        setTablero(tablero);
        int[] local = posicion.getPosicion();
        tablero.setObjeto(cabeza,local[0],local[1]);
        //Inicializa la longitud de la serpiente
        longitud = 1;//La cabeza
        //Genera el cuerpo de la serpiente
        crecer(2);
        makeVisible();
    }

    public Serpiente (Color[] colores,Posicion posicion,String sentido, Tablero tablero){
        this(colores[0],colores[1],posicion,sentido,tablero);
    }

    /**
     * Hace crecer la serpiente en las unidades especificadas, entiendase hacerla crecer en cuanto a longitud
     * @param unidades - Cantidad en la que crece la serpiente
     */
    public final void crecer(int unidades) {
        for(int i=0;i<unidades;i++){
            Circle miembro = new Circle(colores[1]);
            miembro.setTablero(tablero);
            if(cuerpo.isEmpty() ){
                int[] local = validarSentido(sentido, posicion.getPosicion());
                tablero.setObjeto(miembro, local[0], local[1]);
                miembro.setPosicion(new Posicion(local));
                miembro.setSentido(sentido);
            }
            else{
                Circle cola = cuerpo.get(longitud-2);
                int[] local = validarSentido(sentido,cola.getPosicion().getPosicion());
                tablero.setObjeto(miembro,local[0],local[1]);
                miembro.setSentido(sentido);
                miembro.setPosicion(new Posicion(local[0],local[1]));
            }
            cuerpo.push(miembro);
            longitud+=1;
        }
    }   
    
    
    /**
     * Mira si es posible generar la cola en alguna posicion alrededor de la cola actual.
     * @param sentido - Sentido de la cola actual
     * @param posicion - Posicion de la cola actual
     * @return Retorna un arreglo que tiene la posicion en donde se puede poner la cola
     */
    private int[] validarSentido(String sentido, int[] posicion){
        //Mira en la posicion posterior dependiendo del sentido opuesto, si es posible poner una cola(es nula la posicion),en caso que no se pueda, mira otro
        //lado, si en este tampoco se puede, si o si debe ser en el otro lado
        int[] posicionFinal = null;
        switch(sentido){
            case "Arriba":
                if(posicion[0]+1<tablero.getFila() && tablero.getObjeto(posicion[0]+1,posicion[1])==null){
                   posicionFinal = new int[]{posicion[0]+1,posicion[1]};setSentido("Arriba");}
                else if(posicion[1]-1>=0 && tablero.getObjeto(posicion[0], posicion[1]-1)==null){
                    posicionFinal = new int[]{posicion[0],posicion[1]-1};setSentido("Derecha");}
                else{posicionFinal = new int[]{posicion[0],posicion[1]+1};setSentido("Izquierda");}
               break;
            case "Abajo":
                if(posicion[0]-1>=0 && tablero.getObjeto(posicion[0]-1, posicion[1])== null){
                    posicionFinal = new int[]{posicion[0]-1,posicion[1]};setSentido("Abajo");
                }
                else if(posicion[1]-1>=0 && tablero.getObjeto(posicion[0], posicion[1]-1)==null){
                    posicionFinal = new int[]{posicion[0],posicion[1]-1};setSentido("Derecha");}
                else{posicionFinal = new int[]{posicion[0],posicion[1]+1};setSentido("Izquierda");}
                break;
            case "Derecha":
                if(posicion[1]-1>=0 && tablero.getObjeto(posicion[0], posicion[1]-1)==null){
                    posicionFinal = new int[]{posicion[0],posicion[1]-1};setSentido("Derecha");
                }
                else if(posicion[0]+1<tablero.getFila() && tablero.getObjeto(posicion[0]+1,posicion[1])==null){
                    posicionFinal = new int[]{posicion[0]+1,posicion[1]};setSentido("Arriba");}
                else{posicionFinal = new int[]{posicion[0]-1,posicion[1]};setSentido("Abajo");}
                break;
            case "Izquierda":
                if(posicion[1]+1<tablero.getColumna() && tablero.getObjeto(posicion[0], posicion[1]+1)==null){
                    posicionFinal = new int[]{posicion[0],posicion[1]+1};setSentido("Izquierda");}
                else if(posicion[0]+1<tablero.getFila() && tablero.getObjeto(posicion[0]+1,posicion[1])==null){
                    posicionFinal = new int[]{posicion[0]+1,posicion[1]};setSentido("Arriba");}
                else{posicionFinal = new int[]{posicion[0]-1,posicion[1]};setSentido("Abajo");}                    
        }
        return posicionFinal;
    }
    /**
     * Mueve la serpiente en la direccion indicada
     * @param direccion - Direccion hacia donde se mueve la serpiente, esta puede ser Arriba, Abajo, Izquierda, Derecha.
     * 
     */
    public void mover(String direccion) throws SnOOpExcepcion {
        //Mueve inicialmente la cabeza
        if(!opuesto(direccion,cabeza.getSentido())){
            try{
                //La cola toma la direccion de la parte siguiente, asi hasta que la primera parte tenga el sentido de la cabeza
                cabeza.mover(direccion);
            }
            //Si se da un error lo atrapa
            catch(SnOOpExcepcion e){
                try{
                    String mensaje = e.getMessage();
                    //Si el error es comer un alimento o sorpresa lo atiende
                    if(mensaje.equals(SnOOpExcepcion.COMER_ALIMENTO) || mensaje.equals(SnOOpExcepcion.COMER_SORPRESA)){
                        if(mensaje.equals(SnOOpExcepcion.COMER_ALIMENTO)){comerAlimento();}
                        else{comerSorpresa();}      
                    }
                    else{throw e;}
                }
                catch(SnOOpExcepcion er){
                    throw er;
                }
            }
            finally{
                for(int i=0;i<cuerpo.size();i++){
                    Circle parte = cuerpo.get(i);
                    String sentido = parte.getSentido();
                    parte.mover(sentido);
                }
                for(int i=cuerpo.size()-1;i>0;i--){
                    String sentidoAnterior = cuerpo.get(i-1).getSentido();
                    cuerpo.get(i).setSentido(sentidoAnterior);
                }
                cabeza.setSentido(direccion);
                cuerpo.get(0).setSentido(cabeza.getSentido());
            }
        }
    }

    /**
     * Asigna a la serpiente dos colores
     * @param color1 - Color de la cabeza
     * @param color2 - Color del cuerpo
     */
    public void setColor(Color color1, Color color2) {
           colores[0]=color1;
           colores[1]=color2;
    }
    
    public Color[] getColor(){return this.colores;}

    /**
     * Hace la serpiente invisible
     * 
     */
    public void makeInvisible() {
        cabeza.makeInvisible();
        for(Circle miembro:cuerpo){
            miembro.makeInvisible();
        }
    }

    /**
     * Hace la serpiente visible
     * 
     */
    public void makeVisible() {
        cabeza.makeVisible();
        for(Circle miembro:cuerpo){
            miembro.makeVisible();
        }
    }

    /**
     * Disminuye o aumenta la velocidad de la serpiente en la unidad de tiempo dada.
     * @param c - Reppresenta si se desea aumentar la velocidad("+") o disminuir ("-") la velocidad
     * @param - Unidades a cambiar la velocidad
     */
    public void relentizar(char c, int unidades) {
        switch (c){
            case '+':
                velocidad += unidades;
                break;
            case '-':
                velocidad -= unidades;
                break;
        }
    }

    /**
     * Realiza la accion de la serpiente de comer una sorpresa
     */
    private void comerSorpresa() throws SnOOpExcepcion {
        try{
            int[] posCabeza = cabeza.getPosicion().getPosicion();
            String dirCabeza = cabeza.getSentido();
            Sorpresa sorpresa = (Sorpresa)tablero.getObjeto(dirCabeza, posCabeza[0], posCabeza[1]);
            tablero.setObjeto(null, dirCabeza, posCabeza[0], posCabeza[1]);
            cabeza.mover(dirCabeza);
            this.sorpresa = sorpresa;
        }
        catch(SnOOpExcepcion e){throw e;}
    }
    
    /**
     * Acciona el efecto de la sorpresa que tenga en el momento la serpiente
     * @param jugador Jugador que acciona la sorpresa
     */
    public void usarSorpresa(Usuario jugador){
        sorpresa.efecto(jugador);
    }

    /**
     * Realiza la accion de la serpiente de comer un alimento
     * @throws SnOOpExcepcion En caso que el alimento sea un veneno
     */
    private void comerAlimento() throws SnOOpExcepcion{
        try{
            int[] posCabeza = cabeza.getPosicion().getPosicion();
            String dirCabeza = cabeza.getSentido();
            Alimento alimento = (Alimento)tablero.getObjeto(dirCabeza, posCabeza[0], posCabeza[1]);
            tablero.setObjeto(null, dirCabeza, posCabeza[0], posCabeza[1]);
            cabeza.mover(dirCabeza);
            alimento.accion(this);
        }
        catch(SnOOpExcepcion e){throw e;}
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }   

    public void setPosicion(Posicion posicion){
        this.posicion = posicion; 
    }
    public int[] getPosicion(){return this.posicion.getPosicion();}
    /**
     * Divide la serpiente en la posicion indicada
     * @param fila - Fila de la posicion indicada
     * @param columna - Columna de la posicion indicada
     */
    public void dividir(int fila,int columna){
        Circle pos =(Circle) tablero.getObjeto(fila, columna);
        int last = cuerpo.indexOf(pos);
        disminuir(longitud-last);
    }
    /**
     * Divide la serpiente en la posicion indicada
     * @param posicion - Posicion dada
     */
    public void dividir(Posicion posicion){
        int[] pos = posicion.getPosicion();
        dividir(pos[0],pos[1]);
    }    
    
    /**
     * Disminuye la serpiente en las unidades designadas
     * @param unidades 
     */
    public void disminuir(int unidades){
        for(int i=longitud-1;i>=longitud-unidades;i--){
            Circle miembro = cuerpo.pop();
            miembro.desaparecer();
        }
    }
    
    public void setSentido(String sentido){
        this.sentido =  sentido;
    }
    
    public int getLongitud(){return cuerpo.size()+1;}
    
    public Cabeza getCabeza(){return this.cabeza;}
    public Stack<Circle> getCuerpo(){return this.cuerpo;}

    /**
     * Mira si las direcciones dadas son opuestas
     * @param direccion1 
     * @param direccion2
     * @return true si las direcciones son opuestas, else de lo contrario
     */
    private static boolean opuesto(String direccion1, String direccion2) {
        boolean safe = false;
        switch(direccion1){
            case "Derecha":
                if(direccion2.equals("Izquierda")){safe =true;}
                break;
            case "Izquierda":
                if(direccion2.equals("Derecha")){safe =true;}
                break;
            case "Arriba":
                if(direccion2.equals("Abajo")){safe =true;}
                break;
            case "Abajo":
                if(direccion2.equals("Arriba")){safe =true;}                
        }
        return safe;
    }
}
