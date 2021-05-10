import java.util.*;
import java.io.*;
import java.util.Collections;
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

    public Ruta importarRuta(String urlRuta){
        Ruta ruta = null;
        try{
            BufferedReader bIn = new BufferedReader(new FileReader(urlRuta));
            //Lee la primera linea del archivo
            String line = bIn.readLine();
            ArrayList<Estacion> estaciones = new ArrayList<Estacion>();
            String nameRuta = "";
            if(line!=null){nameRuta = line;}
            while(line != null){
                Estacion estacion = new Estacion(line);
                estaciones.add(estacion);
                line = bIn.readLine();
            }
            ruta = new Ruta(nameRuta,estaciones);
            bIn.close();
        }
        catch(IOException e){
            System.out.println("Archivo no encontrado");
        }
        return ruta;
    }

    public void exportarRecorrido(ArrayList<Estacion> recorrido){
        try{
            PrintWriter pw = new PrintWriter(new FileOutputStream("rutaDeseada.txt"));
            //Se ingresa en el archivo el nombre de las estaciones de mejor recorrido
            for(Estacion estacion:recorrido){
                pw.println(estacion.getName());
            }
            //Se guardan los cambios en el archivo
            pw.flush();
            //Se cierra el archivo
            pw.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Rayos");
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
