package presentacion;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

/**
 * Clase que representa la segunda pantalla del juego
 * Pantalla de cargar o empezar juego
 * @author Cristian Castellanos- Diego Gonzalez
 * @version 1.0
 */

public class SegundaPantalla extends JFrame{
    //DIMENSIONES INICIALES DE LA PANTALLA
    Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
    private final Dimension DIMENSION_PREFERIDA = new Dimension(800,600);

    //PANELES
    private JPanel botones, panelIzquierdo, panelDerecho;

    //BOTONES
    private JButton boton1,boton2,boton3;

   /**
    * Constructor de la clase SegundaPantalla
    */
   public SegundaPantalla(){
       setLocationRelativeTo(null);
       prepareElementos();
       prepareAcciones();
   }

   /**
    * Se encarga de preparar los elementos graficos de la pantalla, tales como los
    * botones y paneles, así como la imagen. También le da tamaño a la pantalla y deja
    * que esta siempre aparezca en el centro de la pantalla
    */
   private void prepareElementos(){
       //TITULO Y DIMENSIONES DE LA PANTALLA
       setTitle("Snake");
       setSize(DIMENSION_PREFERIDA);

       //Preparacion de la imagen principal
       setSize(DIMENSION_PREFERIDA);
       setLocationRelativeTo(null);
       setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

       //PREPARACION DE LA IMAGEN 
       PrepareImagen();

       //PREPARACION DE LOS BOTONES
       prepareBotones();

       //PREPARACION DE MARGENES
       prepareMargenes();

       //CAMBIO DE COLOR DE LA PANTALLA
       cambiarColor();
   }

   /**
    * Se encarga de la preparacion de la imagen de la serpiente que va en la pantalla
    */
   private void PrepareImagen(){
       JLabel imagen_izquierda = new JLabel(new ImageIcon("imagenes/imagen_serpiente (1).png"));
       add(imagen_izquierda, BorderLayout.NORTH);
   }

   /**
    * Se encarga de la preparacion de los tres botones ("NUEVA PARTIDA" - "CARGAR PARTIDA" - "VOLVER")
    */
   private void prepareBotones(){
       //PREPARACION DE BOTONES
       botones = new JPanel();
       botones.setLayout(new GridLayout(3,1,10,22));
       insertarBotones();
       botones.setBackground(Color.white);
       add(botones, BorderLayout.CENTER);
   }

   /**
    * Inserta los tres botones al panel gridLayout
    */
   private void insertarBotones(){
       boton1 = new JButton("NUEVA PARTIDA");
       botones.add(boton1);
       boton2 = new JButton("CARGAR PARTIDA");
       botones.add(boton2);
       boton3 = new JButton("VOLVER");
       botones.add(boton3);
   }

   /** 
    * Se encarga de programar las acciones de los botones y hacia donde van dirigidas
   */
   private void prepareAcciones(){
        /* PASAR A PANTALLA DE MODO DE JUEGO */
        boton1.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            nuevaPartida();
        }
    });

    /* PASAR A PANTALLA DE CARGAR PARTIDA */
    boton2.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            cargarPartida();
        }
    });

    /* VOLVER A PANTALLA PRINCIPAL */
    boton3.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            volver();
        }
    });
   }

   /**
    * Método que se usa cuando se quiere crear una nueva partida, tiene 3 modos de juego
    * que el usuario puede escoger, por lo que se pasa a la pantalla de modos de juego. 
    */
   private void nuevaPartida(){
       ModosJuego modos = new ModosJuego();
       modos.setVisible(true);
       this.setVisible(false);
   }

   /**
    * Método usado por el boton "CARGAR PARTIDA", este le muestra las partidas que han sido
    * guardadas. Para hacer esto se creó otra pantalla para crear un nuevo frame.
    */
   private void cargarPartida(){
        cargarPartida cargar = new cargarPartida();
        cargar.setVisible(true);
        this.setVisible(false);
   }

   /**
    * Se encarga de volver a la pantalla de inicio
    */
   private void volver(){
        PantallaInicio anterior = new PantallaInicio();
        anterior.setVisible(true);
        this.setVisible(false);
   }

   /**
    * Se encarga de dar margen al panel del gridLayout para que los botones no cojan
    * todo el espacio disponible, pues se veria desproporcionado
    */
   private void prepareMargenes(){
       //PANEL PARA LA MARGEN IZQUIERDA
       panelIzquierdo = new JPanel();
       panelIzquierdo.setPreferredSize(new Dimension(40,40));
       add(panelIzquierdo, BorderLayout.WEST);

       //PANEL PARA LA MARGEN DERECHA
       panelDerecho = new JPanel();
       panelDerecho.setPreferredSize(new Dimension(40,40));
       add(panelDerecho, BorderLayout.EAST);
   }

   /**
    * Se encarga de darle color a la pantalla o de cambiarla a cualquiera
    */
   private void cambiarColor(){
       //EL COLOR DE FONDO DE LA PANTALLA PRINCIPAL SERA BLANCO 
       this.getContentPane().setBackground(Color.CYAN.darker());
       botones.setBackground(Color.CYAN.darker());
       panelIzquierdo.setBackground(Color.CYAN.darker());
       panelDerecho.setBackground(Color.CYAN.darker());
   }

}