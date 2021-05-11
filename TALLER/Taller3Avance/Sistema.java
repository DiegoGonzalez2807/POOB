import java.util.*;
import java.io.*;
import java.nio.Buffer;
import java.util.Collections;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Set;
/**
 * Clase que contiene la informacion del sistema de Transmilenio
 * @author Cristian Castellanos-Diego Gonzalez
 */

public class Sistema{
    private HashMap<String,Ruta> rutas;
    private HashSet<Troncal> troncales;
    private TreeMap<String,Estacion> estaciones;

    /**
     * Función que retorna el tiempo de espera de una estación (en minutos)
     * de acuerdo al nombre de esta
     * @param nombreEstacion
     * @return
     */
    public int tiempoEspera(String nombreEstacion){
        return estaciones.get(nombreEstacion).getTiempoEspera();
    }

    /**
     * Función que se encarga de retornar una lista con las estaciones
     * ordenadas de manera alfabética
     * @return
     */
    public ArrayList<String> estacionesAlfabetica(){
        ArrayList<String> listaEstaciones = new ArrayList<String>();
        for(String nombre: estaciones.keySet()){
            //AÑADE LOS NOMBRES DE LAS ESTACIONES (LAS LLAVES)
            //AL ARRAYLIST
            listaEstaciones.add(nombre);
        }
        //ORDENA DE MANERA ALFABETICA EL ARREGLO DE NOMBRES
        Collections.sort(listaEstaciones);
        return listaEstaciones;

    }
    
    /**
     * Función encargada de retornar un arreglo de rutas que contengan
     * las dos estaciones requeridas y que en el camino no se tengan que hacer
     * transbordos
     * @param estacion1
     * @param estacion2
     * @return --> Lista de rutas funcionales
     */
    public ArrayList<String> noTransbordos(String estacion1, String estacion2){
        //ARRAYLIST QUE VA A RETORNAR LOS NOMBRES DE LAS RUTAS
        ArrayList<String> listaRutas = new ArrayList<String>();

        //TREEMAP QUE SE VA A ENCARGAR DE ORDENAR LAS RUTAS POR 
        //NUMERO DE PARADAS
        TreeMap<Integer,String> rutas1 = new TreeMap<Integer,String>();

        for(String key: rutas.keySet()){
            Ruta ruta = rutas.get(key);

            //REVISA QUE LAS ESTACIONES SI ESTÉN EN LA RUTA
            if(ruta.sinTransbordo(estacion1, estacion2)){
                rutas1.put(ruta.getParadasSize(), ruta.getName());
            }
        }
        for(int key: rutas1.keySet()){
            //METE LOS NOMBRES ORDENADOS DE ACUERDO A LA CANTIDAD DE PARADAS
            listaRutas.add(rutas1.get(key));

            //SE ORDENAN ALFABETICAMENTE
            Collections.sort(listaRutas);
        }
        return listaRutas;
    }

    /**
     * Función que se encarga de retornar la ruta con el mejor plan de
     * recorrido entre una estación y otra
     * @param estacion1
     * @param estacion2
     * @return
     */
    public Ruta mejorPlan(String estacion1, String estacion2){
        Ruta mejorRuta = null;
        //PRIMER CASO PARA PLAN SIN TRANSBORDO
        TreeMap<Integer,Ruta> rutas1 = new TreeMap<Integer,Ruta>();

        for(String key: rutas.keySet()){
            Ruta ruta = rutas.get(key);

            //REVISA QUE LAS ESTACIONES SI ESTÉN EN LA RUTA
            if(ruta.sinTransbordo(estacion1, estacion2)){
                //METE LA RUTA QUE SIRVE
                rutas1.put(ruta.getParadasSize(), ruta);
            }
        }
        int menorRuta = rutas1.firstKey();

        return mejorRuta;
    }
    
    /**
     * Funcion que retorna el tiempo que se demora un plan de ruta
     * @param planRuta -(La entrada es {{nombreEstación1,nombreRuta1}...{nombreEstacion,null}}
     * @return - Tiempo de recorrido
     */
    public tiempoRuta(String[][] planRuta){
        int tiempo = 0;
        Ruta ruta_ = null;
        String estacion = null;
        ArrayList<Estacion> estaciones= null;
        //Mira en cada una de las rutas del plan dado, las esataciones por donde pasa y la estacion en donde se transborda o el destino de la ruta
        for(int i=0;i<planRuta.length-1;i++){
            ruta_ =  rutas.get(parada[i][1]);
            estacion = parada[i][0];
            index = ruta_.index(estacion);
            estaciones = ruta_.getParadas();
            //Mira en la ruta el tiempo de espera de las estaciones hasta llegar a la estacion de transbordo o de destino
            while(!estaciones.get(index).getName.equals(parada[i+1][0])){
                tiempo += estaciones.get(index).getTiempoEspera();
                index += 1;
            }
        }
        return tiempo;
    }
 

    /**
     * Importar una nueva ruta desde un archivo de texto. El archivo contiene el nombre de la
     * ruta y el nombre de las estaciones por las que pasa.
     * @param file
     * @return --> nueva ruta
     */
    public Ruta importarRuta(File file) throws IOException,java.io.FileNotFoundException{
        Ruta nuevaRuta = null;
        ArrayList<Estacion> paradas = new ArrayList<Estacion>();

        try{
           BufferedReader entrada = new BufferedReader(new FileReader(file));
           //LECTURA DEL NOMBRE DE LA RUTA
           String nombre = entrada.readLine();
           //LECTURA DE LA PRIMER ESTACION
           //SE TOMA LA ENTRADA DE ESTACION COMO:
            //          {nombreEstacion,Troncal}
           String[] line2 = entrada.readLine().split("");
           while(line2 != null){
               Estacion nuevaEstacion = new Estacion(line2[0], line2[1]);
               paradas.add(nuevaEstacion);
               //LECTURA DEL NOMBRE DE LA ESTACION
               line2 = entrada.readLine().split("");
           }
           nuevaRuta = new Ruta(nombre,paradas);
           //SE INSERTA LA NUEVA RUTA A LAS RUTAS DEL SISTEMA
           rutas.put(nombre, nuevaRuta);
           entrada.close();
        }
        //EXCEPCIONES JAVA Y JAVAIO
        catch(Exception e){
            throw e;
        }
        return nuevaRuta;

    }
    public void exportarRecorrido(File file)throws IOException{
        try{
            PrintWriter printer = new PrintWriter(new FileOutputStream(file));

        }
        catch(Exception e){
            throw e;
        }
    }

    /**
     * Función que guarda en un archivo toda la información actual de
     * la troncal que uno desee
     * @param troncal
     * @param file
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     */
    public void guardarTroncal(Troncal troncal, File file) throws java.io.IOException,java.io.FileNotFoundException {
        try{
            ObjectOutputStream archivoGuardar = new ObjectOutputStream(new FileOutputStream(file));
            archivoGuardar.writeObject(troncal);
            archivoGuardar.close();
        }
        catch(Exception e){
            throw e;
        }
    }    
}
