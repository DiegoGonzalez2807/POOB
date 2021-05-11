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
           String line2 = entrada.readLine();
           while(line2 != null){
               Estacion nuevaEstacion = new Estacion(line2);
               paradas.add(nuevaEstacion);
               //LECTURA DEL NOMBRE DE LA ESTACION
               line2 = entrada.readLine();
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

    public void guardarTroncal(Troncal troncal){
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Troncal"+troncal.getName()+".out"));
            out.writeObject("Troncal"+"troncal.getName");
            out.writeObject(troncal);
            out.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Archivo no encontrado");
        }
        catch(IOException e){
            System.out.println("IOException");;
        }
    }    
}
