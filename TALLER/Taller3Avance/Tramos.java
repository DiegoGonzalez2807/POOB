/**
 * Clase que contiene la información de los tramos que componen
 * dos estaciones
 * @author Cristian Castellanos - Diego Gonzalez
 */

public class Tramos {
    
    private String estacion1;
    private String estacion2;
    private int distancia;

    /**
     * Constructor de la clase tramos
     * @param estacion1
     * @param estacion2
     * @param distancia
     */
    public Tramos(String estacion1, String estacion2, int distancia){
        this.estacion1 = estacion1;
        this.estacion2 = estacion2;
        this.distancia = distancia;
    }

    public int getDistancia(){
        return this.distancia;
    }

    public String getEstacion1(){
        return this.estacion1;
    }

    public String getEstacion2(){
        return this.estacion2;
    }
}
