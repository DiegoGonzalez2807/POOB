package presentacion;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.JOptionPane;

public class ModosJuego extends JFrame{
    //DIMENSIONES INICIALES DE LA PANTALLA
    Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
    private final Dimension DIMENSION_PREFERIDA = new Dimension(800,600);

    private JButton boton1,boton2,boton3;

    /**
     * Metodo encargado de la construcción de la pantalla
     */
    public ModosJuego(){
           setLocationRelativeTo(null);
        setLayout(new GridLayout(1,3));
           prepareElementos();
           prepareAcciones();
    }

    /**
     * Metodo encargado de preparar el Frame para que se vea como en el 
     * boceto
     */
    private void prepareElementos(){
        //TITULO Y DIMENSIONES DE LA PANTALLA
        setTitle("Snake");

        //Preparacion de la imagen principal
        setSize(DIMENSION_PREFERIDA);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        //INSERTAR BOTONES
        insertarBotones();
    }

    /**
     * Funcin encargada de programar e insertar los botones. En este caso
     * los botones hacen referencia a cada uno de los tipos de juego
     */
    private void insertarBotones(){
        //CREACION DEL PRIMER TIPO DE JUEGO
        ImageIcon imagen = new ImageIcon("imagenes/solitario.png");
        boton1 = new JButton(imagen);
        boton1.setBackground(Color.CYAN);
        add(boton1);

        //CREACION DEL SEGUNDO TIPO DE JUEGO
        boton2 = new JButton(new ImageIcon("imagenes/dos_jugadores.png"));
        boton2.setBackground(Color.YELLOW.brighter());
        add(boton2);

        //CREACION DEL TERCER TIPO DE JUEGO
        boton3 = new JButton(new ImageIcon("imagenes/maquina.png"));
        boton3.setBackground(Color.GREEN);
        add(boton3);
    }

    /**
     * Funcion encargada de programar los botones antes estipulados
     */
    private void prepareAcciones(){
        /* PASAR A PANTALLA DE JUGADOR SOLITARIO */
        boton1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String nombre1 = JOptionPane.showInputDialog("Deme su nombre", "Nombre del Jugador");
                cambiarSolitario();
            }
        });

        /* PASAR A PANTALLA CON DOS JUGADORES (SIN MAQUINA) */
        boton2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String nombre1 = JOptionPane.showInputDialog("Deme su nombre", "Nombre del Jugador");
                String nombre2 = JOptionPane.showInputDialog("Deme el nombre del segundo jugador", "Nombre del Jugador");
                cambiarDoble();
            }
        });

        /* PASAR A PANTALLA CON DOS JUGADORES (CON MAQUINA) */
        boton3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String nombre1 = JOptionPane.showInputDialog("Deme su nombre", "Nombre del Jugador");
                String nombre2 = JOptionPane.showInputDialog("Deme el nombre que desea ponerle a la maquina", "Nombre de la Maquina");
                cambiarDoble();
            }
        });

        
    }

    /**Funcion encargada de cerrar una ventana, pero antes
     * pregunta si quiere otro metodo de juego, para asi remitirlo
     * de nuevo a la pantalla de modos de juego
     */
    private void cerrar1(){
        Object[] opciones = { "Aceptar", "Cancelar" };
        int eleccion = JOptionPane.showOptionDialog(rootPane, "Desea escoger otro método de juego","Mensaje de Confirmacion", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                        opciones, "Aceptar");
        if(eleccion == JOptionPane.YES_OPTION){
            
        }
    }

    /**
     * Funcion encargada de dirigirnos al juego cuando
     * solo se tiene una persona
     */
    private void cambiarSolitario(){
        solitario pantalla_solitario = new solitario();
        pantalla_solitario.setVisible(true);
        this.setVisible(false);
    }

    /**
     * Funcion que se encarga de dirigirnos al juego cuando
     * tenemos dos personas, ya sea jugador normal o maquina
     */
    private void cambiarDoble(){
        doble pantalla_solitario = new doble();
        pantalla_solitario.setVisible(true);
        this.setVisible(false);
    }
}
