
public class Estacion {

    //Se empieza la estación sin tiempo de espera y sin nivel de ocupación
    protected int tiempo_espera = 0;
    protected String nivel_ocupacion = null;

    //NOMBRE 
    protected String nombre;

    /**
     * Constructor de la estación respecto a su nombre y su nivel de ocupación
     * @param nombre
     * @param nivel_ocupacion
     */
    public Estacion(String nombre){
        this.nombre = nombre;
        this.nivel_ocupacion = "lleno";
        this.tiempo_espera = setTiempoEspera(nivel_ocupacion);
    }

    /**
     * Función que nos retorna el tiempo de espera con respecto al nivel de ocupación de la estación
     * @param nivel_ocupacion
     * @return
     */
    private int setTiempoEspera(String nivel_ocupacion){
        int answer = 0;
        switch(nivel_ocupacion){
            case "Bajo":
                answer = 5;
            case "Medio":
                answer = 20;
            case "Alto":
                answer = 45;
        }
        return answer;  
    }

    public int getTiempoEspera(){
        return this.tiempo_espera;
    }

    public String getName(){
        return this.nombre;
    }
}
