import java.util.*;
import java.io.*;
import java.util.ArrayList;
/**
 * Clase que contiene la informacion del sistema de Transmilenio
 * @author Cristian Castellanos-Diego Gonzalez
 */

public class Sistema{
    private HashMap<String,Ruta> rutas;
    private HashSet<Troncal> troncales;
    private TreeMap<String,Estacion> estaciones;

    public int tiempoEspera(String nombreEstacion){
        return estaciones.get(nombreEstacion).getTiempoEspera();
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
