package domain;
import java.util.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.*;


/*No olviden adicionar la documentacion*/
public class AutomataCelular implements Serializable{
    static private int LONGITUD=30;
    private Elemento[][] automata;
    private String sensor = "yellow";
    
    public AutomataCelular() {
        automata=new Elemento[LONGITUD][LONGITUD];
        for (int f=0;f<LONGITUD;f++){
            for (int c=0;c<LONGITUD;c++){
                automata[f][c]=null;
            }
        }
        algunosElementos();
    }

    public int  getLongitud(){
        return LONGITUD;
    }

    public Elemento getElemento(int f,int c){
        return automata[f][c];
    }
    
    public String getSensor(){
        return this.sensor;
    }

    public void setElemento(int f, int c, Elemento nueva){
        automata[f][c]=nueva;
    }

    public static AutomataCelular nuevo(File file) throws AutomataExcepcion{
        throw new AutomataExcepcion(AutomataExcepcion.OPCION_CONSTRUCCION);
    }

    /**
     * Toma del archivo dado su informacion (automata,sensor y longitud)
     * @param file Archivo en donde se encuentra la informacion
     * @return Retorna el automata con la informacion del archivo
     */
    public static AutomataCelular abrir(File file) throws ClassNotFoundException,java.io.IOException,AutomataExcepcion{
        AutomataCelular automata=null;
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            automata = (AutomataCelular) in.readObject();
            in.close();
            return automata;
        }
        catch(Exception e){
            throw e;
        }
    }
    
    public static AutomataCelular abra00(File file) throws AutomataExcepcion{
        throw new AutomataExcepcion(AutomataExcepcion.OPCION_CONSTRUCCION);       
    }

    /**
     * Toma la informacion del automata,elementos, longitud y demas, y la guarda.
     * @param file Archivo en donde se guarda la informacion
     */
    public void guardar(AutomataCelular automata, File file) throws java.io.IOException,java.io.FileNotFoundException,AutomataExcepcion{
        try{
            ObjectOutputStream archivoGuardado = new ObjectOutputStream(new FileOutputStream(file));
            archivoGuardado.writeObject(automata);
            archivoGuardado.close();
        }
        catch(Exception e){
            throw e;
        }

    }

    /**
     * Toma la informacion del automata,elementos, longitud y demas, y la guarda.
     * @param file Archivo en donde se guarda la informacion
     */
    public static void guarde00(File file) throws AutomataExcepcion{
        //throw new AutomataExcepcion(AutomataExcepcion.OPCION_CONSTRUCCION);
        if(!file.getName().endsWith(".dat")){
            throw new AutomataExcepcion(AutomataExcepcion.OPCION_CONSTRUCCION);
        }
        try{
            ObjectOutputStream archivoGuardado = new ObjectOutputStream(new FileOutputStream(file));
        }
        catch(IOException e){

        }
    }


    /**
     * Toma del archivo dado la informacion dada linea por linea
     * @param file Archivo en donde se encuentra la informacion
     * @return Retorna el automata con la informacion del archivo
     */
    public static AutomataCelular importar(File file) throws AutomataExcepcion{
        throw new AutomataExcepcion(AutomataExcepcion.OPCION_CONSTRUCCION);
        
    }

     /**
     * Toma la informacion del automata y la guarda  en forma de texto, el formato designado es componente posicion
     * @param file Archivo en donde se guarda la informacion
     */
    public static void exportar(File file) throws AutomataExcepcion{
        throw new AutomataExcepcion(AutomataExcepcion.OPCION_CONSTRUCCION);
    }

    public void algunosElementos(){
        SensorVida sensor_1 = new SensorVida(this,15,29);
        SensorVida sensor_2 = new SensorVida(this,16,29);
        SensorVida sensor_3 = new SensorVida(this,15,28);
        SensorVida sensor_4 = new SensorVida(this,16,28);
        Celula muerta = new Celula(this, 5,6);
        setElemento(5,4,muerta);
        setElemento(4,5,muerta);
        ticTac();
        ticTac();
        ticTac();
        Celula indiana = new Celula(this, 1,1);
        Celula celula_007 = new Celula(this,2,2);
        Celula agamenon = new CelulaEspecial(this,5,5);
        Celula venus = new CelulaEspecial(this,10,10);
        Calefactor suroeste = new Calefactor(this,0,29);
        Calefactor noreste = new Calefactor(this,29,29);
        Celula diego = new CelulaGenesis(this,20,20);
        Celula cristian = new CelulaGenesis(this,6,23);
        setElemento(0,29,suroeste);
        setElemento(29,29,noreste);
        CelulaConway con1 = new CelulaConway(this,14,15);
        CelulaConway con2 = new CelulaConway(this,14,16);
        prueba1();
        prueba2();
        prueba3();
    }
    
    private void prueba1(){
        CelulaConway john = new CelulaConway(this,5,27);
        CelulaConway horton = new CelulaConway(this,5,28);
    }
    
    private void prueba2(){
        CelulaConway con5 = new CelulaConway(this,29,0);
        CelulaConway con6 = new CelulaConway(this,29,1);
        CelulaConway con7 = new CelulaConway(this,28,1);
        CelulaConway con8 = new CelulaConway(this,28,0);
    }
    
    private void prueba3(){
        CelulaConway con9 = new CelulaConway(this,29,15);
        CelulaConway con10 = new CelulaConway(this,29,16);
        CelulaConway con11 = new CelulaConway(this,29,17);
    }
    
    public void ticTac(){
        for(Elemento[] array:automata){
            for(Elemento elemento:array){
                if(elemento != null){
                    elemento.decida();
                    elemento.cambie();
                }
            }
        }
    }

}
