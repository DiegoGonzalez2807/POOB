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
     * Recordar que se debe mirar que no tenga transbordos, eso quiere decir que ambas
     * estaciones estén en la misma ruta y no se tenga que coger otro bus
     * @param estacion_1
     * @param estacion_2
     * @return --> ambas estaciones están en la ruta
     */
    private boolean sinTransbordo(String estacion_1, String estacion_2){
        boolean answer = false;
        //CASO DONDE SOLO SE TIENE QUE ENCONTRAR LA ESTACION 1 Y 2
        if(checkerEstacion(estacion_1) && checkerEstacion(estacion_2)){
            answer = true;
        }
        return answer;
    }

    /**
     * Función encargada de revisar si la estación que se está revisando está en
     * las paradas de esta ruta
     * @param estacionRevision
     * @return --> si está en la ruta
     */
    private boolean checkerEstacion(String estacionRevision){
        boolean answer = false;
        for(Estacion estacion: paradas){
            if(estacion.getName().equals(estacionRevision)){
                answer = true;
                break;
            }
        }
        return answer;
    }
}
