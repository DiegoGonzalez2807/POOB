package aplicacion;

import aplicacion.alimentos.*;
import aplicacion.sorpresas.*;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;



/**
 * Clase que representa el juego en general, contiene jugadores, cada uno de estos con su respectiva serpiente
 */

//javac -d bin src/presentacion/*.java -sourcepath src src/aplicacion/*.java src/aplicacion/maquinas/*.java src/aplicacion/sorpresas/*.java src/aplicacion/alimentos/*.java
//java -cp bin presentacion.SnakeGUI
public class SnOOpe implements Serializable  {

	private static int[] campoJuego = new int[]{32,32};

	private String[] sorpresas;

	private String[] alimentos;

	private boolean isVisible;

	public ArrayList<Usuario> jugadores = new ArrayList<Usuario>();
        
        private Tablero tablero;


	/**
	 * Crea una nueva partida del juego Snoop, con dos jugadores, y las adecuaciones dadas por los jugadores (alimentos,sorpresas).
	 * @param nombreJugador1 - Nombre del primer usuario del juego
         * @param colores1 - Colores elegidos por el jugador1 para personalizar su serpiente
	 * @param nombreJugador2 - Nombre del segundo usuario del juego, si el usuario es maquina se pone "Pc"
         * @param colores2 - Colores elegidos por el jugador2 para personalizar su serpiente
	 * @param sorpresas - Las sorpresas que el jugador quiere que aparezcan en el juego
	 * @param alimentos - Alimentos que el jugador quiere que aparezcan en el juego
	 */
	public SnOOpe(String nombreJugador1,Color[] colores1, String nombreJugador2,Color[] colores2,String[] sorpresas, String[] alimentos){
            //Se asignan los atributos del juego
            this.sorpresas=sorpresas;
            this.alimentos=alimentos;
            //Se inicializa el tablero 
            tablero = new Tablero(campoJuego[0], campoJuego[1],this);
            //Se crea el usuario 1 y se le asigna una posicion y sentido a la serpiente
            Usuario jugador1  = new Persona(nombreJugador1,colores1,new Posicion(0,2),"Derecha",tablero);
            Usuario jugador2 = null;
            Posicion pos = new Posicion(campoJuego[0]-1,campoJuego[1]-3);
            //Se crea el jugador 2 el cual depende del nombre que se le de al jugador, si este es un tipo de maquina crea dicha maquina
            try{
                String maquina = "dominio.maquinas."+nombreJugador2;
//                Class clase = Class.forName(maquina);
                jugador2 = (Usuario)Class.forName(maquina).getDeclaredConstructor().newInstance("Pc",colores2,pos,"Izquierda",tablero);
                jugador2.setColor(colores2);
            }
            
            //En caso que la clase no exista, es decir se de el nombre del jugador se crea una persona
            catch(ClassNotFoundException e){
                jugador2 = new Persona(nombreJugador2,colores2,pos,"Izquierda",tablero);
            }
            catch(Exception e){
                e.printStackTrace();
            }
            //Despues de crear el segundo jugador se le asigna una posicion y sentido
            //jugador2.setPosicion(new Posicion(14,12));
            //Se agregan los jugadores a el arreglo de jugadores
            jugadores.add(jugador1);
            jugadores.add(jugador2);
	}

	/**
	 * Genera un nuevo juego en donde se juega en solitario, con las especificaciones dadas por el jugador.(sorpresas, alimentos).
	 * @param jugador - Unico jugador de la partida
	 * @param sorpresas - Las sorpresas que el jugador quiere que aparezcan en el juego
	 * @param alimentos - Alimentos que el jugador quiere que aparezcan en el juego
         * @param colores - Colores elegidos por le jugador par apersonalizar la serpiente
	 * 
	 */
	public SnOOpe(String jugador, String[] sorpresas, String[] alimentos,Color[] colores){
            //Se asignan los atributos del juego
            this.sorpresas=sorpresas;
            this.alimentos=alimentos;
            //Se crea el usuario 1 y se le asigna una posicion y sentido a la serpiente
            tablero = new Tablero(campoJuego[0], campoJuego[1],this);
            Usuario jugador1  = new Persona(jugador,colores,new Posicion(0,2),"Derecha",tablero);
            jugadores.add(jugador1);
	}

	/**
	 * Guarda la partida jugada actualmente en un archivo, en la ruta especificada, si solo se da nombre se guarda en una carpeta especial para las partidas guardadas
	 * @param nombre - Direccion o nombre de la partida
	 */
        public void guardarJuego(SnOOpe automata, File file) throws java.io.IOException,java.io.FileNotFoundException,SnOOpExcepcion{
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
	 * Carga la partida especificada
	 * @param archivo - Ruta donde se encuentra la partida a cargar
	 */
        public static SnOOpe cargarJuego(File file) throws ClassNotFoundException,java.io.IOException,SnOOpExcepcion{
            SnOOpe automata=null;
            try{
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                automata = (SnOOpe) in.readObject();
                in.close();
                return automata;
            }
            catch(Exception e){
                throw e;
            }
        }

	/**
	 * Indica al juego que sorpresas y alimentos se pueden generar en medio del juego.
	 * @param sorpresas - Sorpresas elegidas por los jugadores
	 * @param alimentos - Alimentos elegidos porlos jugadores
	 * 
	 */
	private void seleccionarObjetos(String[] sorpresas, String[] alimentos) {
            this.alimentos=alimentos;
            this.sorpresas=sorpresas;
	}
    
    /**
     * Se encarga de revisar que la cantidad de alimentos que están en el tablero
     * son dos, pues no pueden haber mas
     * @return --> True si solo hay dos alimentos
     */
    public boolean reviewAlimento(){
        int count2 = 0;
        for(int i = 0; i< getTablero().getFila();i++){
            for(int j = 0; j < getTablero().getColumna();j++){
                if(getTablero().getObjeto(i,j) instanceof Alimento){
                    count2 += 1;
                }
            }
        }
        if(count2 == 2){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Se encarga de retornar la cantidad de alimentos que hay en el tablero actualmente
     * @return --> Cantidad de alimentos
     */
    public int cantidadAlimentos(){
        int count1 = 0;
        for(int i = 0; i< getTablero().getFila();i++){
            for(int j = 0; j < getTablero().getColumna();j++){
                if(getTablero().getObjeto(i,j) instanceof Alimento){
                    count1 += 1;
                }
            }
        }
        return count1;
    }

    public void setTablero(Tablero nuevo){
        this.tablero = nuevo;
    }

	public void mejorRecord() {

	}

	/**
	 * Hace visible los objetos de la partida
	 * 
	 */
	public void makeVisible() {

	}

	/**
	 * Hace invisible los objetos de la partida jugada
	 * 
	 * 
	 */
	public void makeInvisible() {

	}

	/**
	 * Pausa las acciones que se esten dando en la partida
	 * 
	 */
	public void pausarJuego() {

	}

	/**
	 * Finaliza el juego, indicando su causa
	 * 
	 */
	public void fin() {

	}

	/**
	 * Mira si se genero un nuevo record dentro de el juego, teniendo en cuenta otras partidas
	 * 
	 */
	private boolean isRecord() {
		return false;
	}

	/**
	 * Empieza la partida creada por el jugador.Las serpientes se inician a mover en sus direcciones respectivas
	 * 
	 */
	public void empezar() throws SnOOpExcepcion, Exception{
            try{
                if(jugadores.size()==1){
                    mover(jugadores.get(0),"Derecha");
                }
                else{
                    mover(jugadores.get(0),"Derecha");
                    mover(jugadores.get(1),"Izquierda");
                }
                generarAlimento();
                generarAlimento();
                generarSorpresa();  
            }
            
            catch(Exception e){throw e;}
	}

        /**
         * Crea un alimento en una posicion aleatoria, en donde no haya nada en el tablero
         * @return El alimento generado
         */
        public Alimento generarAlimento() throws Exception{
            Alimento alimento = null;
            File carpeta = new File("src/aplicacion/alimentos");
            int number = 0;
            int x = 0;
            int y = 0;
            Random rand = new Random();
            float r = rand.nextFloat(); 
            float g = rand.nextFloat(); 
            float b = rand.nextFloat();
            Color randomColor = new Color(r, g, b);
            String[] archivos = carpeta.list();
            for(int i=0;i<archivos.length;i++){
                String clase = archivos[i];
                archivos[i] = clase.replace(" ", "").replace(".java","");
            }
            number = (int)Math.random()*(archivos.length);
            while(archivos[number].equals("Alimento")){
                number = (int)(Math.random()*archivos.length);
                }
            String clase = "aplicacion.alimentos."+archivos[number];
            while(tablero.getObjeto(x,y)!=null){
                x = (int)(Math.random()*campoJuego[0]);
                y = (int)(Math.random()*campoJuego[1]);
            }
            try{
            alimento = (Alimento)Class.forName(clase).getDeclaredConstructor(Tablero.class,Posicion.class).newInstance(tablero,new Posicion(x,y));
            alimento.setColor(randomColor);
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return alimento;
        }
        
        
        /**
         * Crea una sorpresa en una posicion aleatoria, en donde no haya nada en el tablero
         * @return La sorpresa generada
         */
        public Sorpresa generarSorpresa() throws Exception{
            Sorpresa sorpresa = null;
            File carpeta = new File("src/aplicacion/sorpresas");
            int number = 0;
            int x = 0;
            int y = 0;
            String[] archivos = carpeta.list();
            for(int i=0;i<archivos.length;i++){
                String clase = archivos[i];
                archivos[i] = clase.replace(" ", "").replace(".java","");
            }
            number = (int)Math.random()*(archivos.length);
            while(archivos[number].equals("Sorpresa")){
                number = (int)(Math.random()*archivos.length);
                }
            String clase = "aplicacion.sorpresas."+archivos[number];
            while(tablero.getObjeto(x,y)!=null){
                x = (int)(Math.random()*campoJuego[0]);
                y = (int)(Math.random()*campoJuego[1]);
            }
            try{
                sorpresa = (Sorpresa)Class.forName(clase).getDeclaredConstructor(Tablero.class,Posicion.class).newInstance(tablero,new Posicion(x,y));
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return sorpresa;
        }
        
	/**
	 * Reanuda la partida pausada por el jugador.
	 * 
	 */
	public void reanudar() {

	}
        
        public ArrayList<Usuario> getJugadores(){return jugadores;}
        
        /**
         * Mueve la serpiente del jugador en la direccion indicada
         * @param jugador Jugador que juega
         * @param direccion Direccion a mover la serpiente
         * @throws SnOOpExcepcion
         */
        public void mover(Usuario jugador, String direccion) throws SnOOpExcepcion{
            try{jugador.mover(direccion);}
            catch(SnOOpExcepcion e){throw e;}
        }
        
        public Tablero getTablero(){return this.tablero;}
        
        public String[] getAlimentos(){return this.alimentos;}
        
        public String[] getSorpresas(){return this.sorpresas;}
        
        public static void main(String[] args) {
            String jugador = "prueba";
            Color[] colores = new Color[]{Color.RED,Color.ORANGE};
            String[] alimentos = new String[]{"Fruta","Dulce","Veneno"};
            String[] sorpresas = new String[]{"Bloque","Division","Lupa"};
            SnOOpe snake = new SnOOpe(jugador, sorpresas, alimentos, colores);
            try{
            snake.empezar();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
    }
    public int getFilas(){return this.campoJuego[0];}

    public int getColumnas(){return this.campoJuego[1];}
       
}
