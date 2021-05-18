/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Deberia moverse la serpiente
 * Deberia comer un alimento
 * Deberia morir envenenado
 * Deberia crecer
 * Deberia Disminuir
 * Deberia crear una serpiente con el color dado
 */
package tests;
import aplicacion.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import jdk.jfr.Timestamp;

//javac -d bin -cp junit-4.12.jar src/aplicacion/*.java src/aplicacion/sorpresas/*.java src/aplicacion/maquinas/*.java src/aplicacion/alimentos/*.java src/tests/SnOOpeTest.java
//java -cp junit-4.12.jar;hamcrest-core-1.3.jar;bin org.junit.runner.JUnitCore tests.SnOOpeTest
/**
 * Contiene las preubas de unidad de el juego
 * @author Cristian Castellanos - Diego Gonzalez
 * @version 1.0
 */


public class SnOOpeTest {
    String jugador = "prueba";
    Color[] colores = new Color[]{Color.RED,Color.ORANGE};
    String[] alimentos = new String[]{"Fruta","Dulce","Veneno"};
    String[] sorpresas = new String[]{"Bloque","Division","Lupa"};
    SnOOpe snake = new SnOOpe(jugador, sorpresas, alimentos, colores);
    
    @Before
    public void setUp(){
    }
    
    @Test
    public void deberiaCrearUnJuegoSnake(){
        //Deberia crear un usuario con nombre prueba
        ArrayList<Usuario> usuarios = snake.getJugadores();
        Usuario player = usuarios.get(0);
        assertTrue(jugador.equals(usuarios.get(0).getNombre()));
        //Deberia crear un tablero de 15X15
        Tablero tablero = snake.getTablero();//Se pide el tablero para posteriormente pedir sus medidas
        int fila = tablero.getFila();
        int columna = tablero.getColumna();
        assertEquals(fila,15);
        assertEquals(columna,15);
        //Deberia crear una serpiente con colores rojo y naranja
        Serpiente python = player.getSnake();
        Color[] coloresPrueba = python.getColor();
        assertEquals(coloresPrueba[0],colores[0]);
        assertEquals(coloresPrueba[1],colores[1]);
        //Deberia crear una serpiente con posicion 0,2
        int[] posicion = python.getPosicion();
        assertEquals(posicion[0],0);
        assertEquals(posicion[1], 2);
        //Deberia tener como sorpresas las dadas
        String[] sorpresasPrueba = snake.getSorpresas();
        assertEquals(Arrays.toString(sorpresas),Arrays.toString(sorpresasPrueba));
        //Deberia tener como alimentos los dados
        String[] alimentosPrueba = snake.getAlimentos();
        assertEquals(Arrays.toString(alimentos),Arrays.toString(alimentosPrueba));
        //Deberia tener una longitud de 3, y las partes del cuerpo en la posicion (0,1),(0,0)
        assertEquals(python.getLongitud(),3);
        assertTrue(tablero.getObjeto(0, 1) instanceof Circle);
        assertTrue(tablero.getObjeto(0, 0) instanceof Circle);
        assertTrue(tablero.getObjeto(0, 2) instanceof Cabeza);
        //Los elementos que conforman la serpiente deben tener el tablero asignado
        assertTrue(tablero.getObjeto(0, 1).getTablero() instanceof Tablero);
        assertTrue(tablero.getObjeto(0, 0).getTablero()instanceof Tablero);
        assertTrue(tablero.getObjeto(0, 2).getTablero()instanceof Tablero);
 
    }
    
    
    @Test
    public void deberiaMoverseLaSerpiente(){
        //Se mueve la serpiente a la derecha, se espera que la cabeza se meueva a la posicion 0,3
        ArrayList<Usuario> jugadores = snake.getJugadores();
        Tablero tablero = snake.getTablero();
        Persona jugador1 = (Persona)jugadores.get(0);
        try{
            //Se verifica que cada parte de la serpiente haya sido movida correctamente, como la serpiente se movio la posicion 0,0 debe quedar vacia
            snake.mover(jugador1, "Derecha");
            assertTrue(tablero.getObjeto(0, 3) instanceof Cabeza);
            assertTrue(tablero.getObjeto(0, 1) instanceof Circle);
            assertTrue(tablero.getObjeto(0, 2) instanceof Circle);
            assertTrue(tablero.getObjeto(0, 0)==null);
            //Se mueve la serpiente hacia abajo
            snake.mover(jugador1, "Abajo");
//            assertTrue(tablero.getObjeto(1,3) instanceof Cabeza);
//            assertTrue(tablero.getObjeto(0, 3) instanceof Circle);
//            assertTrue(tablero.getObjeto(0, 2) instanceof Circle);
//            assertTrue(tablero.getObjeto(0, 1)==null);
//            //Se mueve la serpiente a la izquierda 2 veces
//            snake.mover(jugador1, "Izquierda");
//            assertTrue(tablero.getObjeto(1, 2) instanceof Cabeza);
//            assertTrue(tablero.getObjeto(1, 3) instanceof Circle);
//            assertTrue(tablero.getObjeto(0, 3) instanceof Circle);
//            assertTrue(tablero.getObjeto(0, 2)==null);
//            snake.mover(jugador1, "Izquierda");
//            assertTrue(tablero.getObjeto(1, 1) instanceof Cabeza);
//            assertTrue(tablero.getObjeto(1, 2) instanceof Circle);
//            assertTrue(tablero.getObjeto(1, 3) instanceof Circle);
//            assertTrue(tablero.getObjeto(0, 3)==null);
//            //Se mueve la serpiente hacia arriba
//            snake.mover(jugador1,"Arriba");
//            assertTrue(tablero.getObjeto(0, 1) instanceof Cabeza);
//            assertTrue(tablero.getObjeto(1, 1) instanceof Circle);
//            assertTrue(tablero.getObjeto(1, 2) instanceof Circle);
//            assertTrue(tablero.getObjeto(1, 3)==null);   
////            //Si la serpiente se mueve contrario a donde esta mirando no deberia hacer nada
//            snake.mover(jugador1, "Abajo");
//            assertTrue(tablero.getObjeto(0, 1) instanceof Cabeza);
//            assertTrue(tablero.getObjeto(1, 1) instanceof Circle);
//            assertTrue(tablero.getObjeto(1, 2) instanceof Circle);
//            assertTrue(tablero.getObjeto(1, 3)==null); 
        }     
        catch(Exception e){
            fail("No debio lanzar error");
        }
    }
    
//    @Test
    public void deberiaEstrellarseLaSerpienteContraElMuro(){
        ArrayList<Usuario> jugadores = snake.getJugadores();
        Tablero tablero = snake.getTablero();
        Persona jugador1 = (Persona)jugadores.get(0);
        try{
            //Se verifica que cada parte de la serpiente haya sido movida correctamente, como la serpiente se movio la posicion 0,0 debe quedar vacia
            snake.mover(jugador1, "Arriba");
        }     
        catch(SnOOpExcepcion e){
            assertTrue(true);
        }
        catch(Exception e){
            fail("Debia sacar un error");
        }
    }
    
//    @Test
    public void deberiaEstrellarseConSuPropioCuerpo(){
        ArrayList<Usuario> jugadores = snake.getJugadores();
        Tablero tablero = snake.getTablero();
        Persona jugador1 = (Persona)jugadores.get(0);
        Serpiente python = jugador1.getSnake();
        python.crecer(1);
        //La serpiente deberia crecer con la cola en 1,0
        assertTrue(tablero.getObjeto(0, 2) instanceof Cabeza);
        assertTrue(tablero.getObjeto(0, 1) instanceof Circle);
        assertTrue(tablero.getObjeto(0, 0) instanceof Circle);
        assertTrue(tablero.getObjeto(1, 0) instanceof Circle);
        Circle A = (Circle)tablero.getObjeto(1,0);
        System.out.println(A.getSentido());
        //La serpiente deberia tener una longitud de 4
        assertEquals(4,python.getLongitud());
        //La serpiente deberia estrellarse despues de 3 movimientos
        try{
            snake.mover(jugador1, "Abajo");
            assertTrue(tablero.getObjeto(1, 2) instanceof Cabeza);
            assertTrue(tablero.getObjeto(0, 2) instanceof Circle);
            assertTrue(tablero.getObjeto(0, 1) instanceof Circle);
            assertTrue(tablero.getObjeto(0, 0) instanceof Circle);
//            snake.mover(jugador1, "Izquierda");
//            assertTrue(tablero.getObjeto(0, 2) instanceof Cabeza);
//            assertTrue(tablero.getObjeto(0, 1) instanceof Circle);
//            assertTrue(tablero.getObjeto(0, 0) instanceof Circle);
//            assertTrue(tablero.getObjeto(1, 0) instanceof Circle);            
//            snake.mover(jugador1, "Arriba");
//            assertTrue(tablero.getObjeto(0, 2) instanceof Cabeza);
//            assertTrue(tablero.getObjeto(0, 1) instanceof Circle);
//            assertTrue(tablero.getObjeto(0, 0) instanceof Circle);
//            assertTrue(tablero.getObjeto(1, 0) instanceof Circle);            
            //fail("Deberia lanzar error");
        }
        catch(SnOOpExcepcion e){
            System.out.println(e.getMessage());
            assertTrue(true);
        }
    }
            
            

    @After
    public void tearDown()
    {

    }
}
