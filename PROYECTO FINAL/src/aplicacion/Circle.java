package aplicacion;

import java.awt.Color;
import java.io.Serializable;

public class Circle extends Objeto implements Serializable {

    private int diametro;
    private String sentido;
      
    /**
     * Crea un circula con los parametros dados
     * @param diametro
     * @param color 
     */
    public Circle(int diametro,Color color){
        this.diametro=diametro;
        setColor(color);
        }
    /**
     * Crea un circulo con el color dado, el diametro por defecto es 20
     * @param color 
     */
    public Circle(Color color){
        this(20,color);
    }

    public Circle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void setSentido(String sentido){
        this.sentido=sentido;
    }
    
    public String getSentido(){return sentido;}



}
