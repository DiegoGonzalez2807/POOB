import java.util.ArrayList;

public class Ruta {
    
    //NOMBRE DE LA RUTA
    protected String nombre_ruta;

    //PARADAS DE LA RUTA
    protected ArrayList<Estacion> paradas;


    public Ruta(String nombre, ArrayList<Estacion> paradas){
        this.nombre_ruta = nombre;
        this.paradas = paradas;
    }

    public ArrayList<Estacion> ruta_funcional_1(String estacion_1, String estacion_2){
        return null;
    }

    /**
     * Función que revisa si las dos estaciones están en la ruta
     * @param estacion_1
     * @param estacion_2
     * @return
     */
    private boolean contiene_estaciones(String estacion_1, String estacion_2){
        boolean safe = false;
        /**for(estacion estacion:paradas){
            if(estacion_1.equals(estacion.getName()) || estacion_2.equals(estacion.getName())){
                safe = safe && true;
            }
        }*/
        return safe;
    }
}
