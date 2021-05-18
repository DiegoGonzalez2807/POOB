package aplicacion;

/**
 * Contiene los objetos que pertenecen a la cabeza de la serpiente
 * @author Cristian Castellanos - Diego Gonzalez
*/
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

public class Cabeza extends Objeto implements Serializable {

//    private ArrayList<Circle> ojos;
    private Circle cabeza;
    private Serpiente serpiente;
    private String sentido;

    /**
     * Genera la cabeza de la serpiente, en esta version unicamente la cabeza, no ojos
     * @param color - Color de la cabeza
     */
    public Cabeza (Color color, Serpiente serpiente,String sentido, Posicion posicion) {
        //Inicializa los atributos
//        ojos = new ArrayList<Circle>();
        this.serpiente = serpiente;
        //Se crea la cabeza, se compone de la cabeza, los ojos y las pupilas
        cabeza = new Circle(color);
        setSentido(sentido);
        setPosicion(posicion);
        setColor(color);
//        Circle ojoDerecho  = new Circle(10,Color.WHITE);
//        Circle ojoIzquierdo = new Circle(10,Color.WHITE);
//        Circle pupilaDerecha = new Circle(8,Color.BLACK);
//        Circle pupulaIzquierda = new Circle(8,Color.BLACK);
//        //Se agregan los miembros a la cabeza
//        ojos.add(ojoDerecho);
//        ojos.add(ojoIzquierdo);
//        ojos.add(pupilaDerecha);
//        ojos.add(pupulaIzquierda);
    }
    

    @Override
    public void makeVisible() {
//        for(Circle miembro:ojos){
//            miembro.makeVisible();
//        }
        cabeza.makeVisible();
        isVisible=true;
    }

    @Override
    public void makeInvisible() {
        isVisible=false;
//        for(Circle miembro:ojos){
//            miembro.makeVisible();
//        }
        cabeza.makeInvisible();
    }

    @Override
    public void mover(String direccion) throws SnOOpExcepcion{
        try{
            tablero.mover(direccion, posicion);
        }
        catch(SnOOpExcepcion e){throw e;}
    }

    @Override
    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
        cabeza.setPosicion(posicion);
//        for(Circle miembro:ojos){
//            miembro.setPosicion(posicion);
//        }
    }
    
    public Serpiente getSerpiente(){
        return this.serpiente;
    }
    
    public void setSentido(String sentido){
        this.sentido=sentido;
    }
    
    public String getSentido(){return sentido;}
    
    @Override
    public void setTablero(Tablero tablero){
        super.setTablero(tablero);
        cabeza.setTablero(tablero);
//        for(Circle parte:ojos){
//            parte.setTablero(tablero);
//        }
        cabeza.setSentido(sentido);
    }
}
