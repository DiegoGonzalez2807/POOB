package presentacion;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Clase que representa la pantalla de inicio del juego
 * @author Cristian Castellanos- Diego Gonzalez
 * @version 1.0
 */

public class PantallaInicio extends JFrame{
    //DIMENSIONES INICIALES DE LA PANTALLA
    Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
    private final Dimension DIMENSION_PREFERIDA = new Dimension(800,600);

    //PANELES
    private JPanel botones, panelIzquierdo, panelDerecho;

    //BOTONES
    private JButton boton1,boton2,boton3;

   /**
    * Constructor de la clase que contiene la pantalla de inicio del juego
    */
   public PantallaInicio(){
       //PARA QUE LA PANTALLA SIEMPRE APAREZCA EN LA MITAD
       this.setLocationRelativeTo(null);
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
       this.setLocationRelativeTo(null);
       setSize(DIMENSION_PREFERIDA);
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
    * Se encarga de la preparacion de los tres botones ("EMPEZAR" - "CONFIGURACION" - "SALIR")
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
       boton1 = new JButton("EMPEZAR");
       botones.add(boton1);
       boton2 = new JButton("CONFIGURACION");
       botones.add(boton2);
       boton3 = new JButton("SALIR");
       botones.add(boton3);
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

   /** 
    * Se encarga de programar las acciones de los botones y hacia donde van dirigidas
   */
   private void prepareAcciones(){
       /* PASE A LA SEGUNDA PANTALLA */
       boton1.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               cambiarPantalla();
           }
       });

       /* SALGA DEL JUEGO*/
       boton3.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               System.exit(0);
           }
       });

       /* VAYA A CONFIGURACION*/
       boton2.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               configuracion("PantallaInicio");
           }
       });
   }

   /**
    * Este metodo es exclusivo de cuando se empieza la partida, pues cuando se le
    * da al boton de empezar, se va a cambiar a la segunda pantalla que es donde 
    * nos preguntan si queremos cargar o empezar una partida. La primer pantalla se
    * hace invisible
    */
   private void cambiarPantalla(){
       SegundaPantalla pantalla2 = new SegundaPantalla();
       pantalla2.setVisible(true);
       this.setVisible(false);
   }

   /**
    * Este método se encarga de ir a la pantalla de configuración. Este es de uso exclusivo 
    * para el boton de "CONFIGURACION"
    * @param pantalla --> Pantalla donde se llama la configuración
    */
   private void configuracion(String pantalla){
       configuracion pantalla_configuracion = new configuracion("PantallaInicio");
       pantalla_configuracion.setVisible(true);
       this.setVisible(false);
   }

}