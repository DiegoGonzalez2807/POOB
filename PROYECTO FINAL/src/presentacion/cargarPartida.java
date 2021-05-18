package presentacion;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
//import aplicacion.*;
import javax.swing.border.Border;
import javax.swing.JComboBox;
import java.util.Hashtable;
import javax.swing.JTextField;

public class cargarPartida extends JFrame{

    //DIMENSIONES INICIALES DE LA PANTALLA
    Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
    private final Dimension DIMENSION_PREFERIDA = new Dimension(800,600);

    //BOTONES 
    private JButton boton1,boton2;

    //PANELES DE MARGENES Y DEL FRAME
    private JPanel panelBotones, panelIzquierdo, panelDerecho, panelAbajo, botones;
    private JPanel panelSuperior, panelArriba, panelCentral;

    //CONSTRUCTOR DE LA PANTALLA DE CARGA
    public cargarPartida(){
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        prepareElementos();
        prepareAcciones();
    }

    /**
     * Se encarga de preparar toda la parte gráfica del frame de CargarPartida, tales como
     * las dimensiones del frame, preparación del panel inferior, central y superior
     */
    private void prepareElementos(){
        //TITULO Y DIMENSIONES DE LA PANTALLA
        setTitle("Snake");

        //Preparacion de la imagen principal
        setSize(DIMENSION_PREFERIDA);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        //INSERTAR BOTONES DE ABAJO
        prepararInferior();

        //PREPARACION DEL PANEL SUPERIOR
        prepararSuperior();

        //PREPARACION DEL PANEL CENTRAL
        prepararCentral();
    }

    /**
     * Se encarga de insertar los botones y ponerles margenes para que estos queden alineados
     * y bien presentados
     */
    private void prepararInferior(){
        panelBotones = new JPanel();
        panelBotones.setLayout(new BorderLayout());
        prepareMargenInferior(panelBotones, new Dimension(50,50));

        //INSERTAR BOTONES
        insertarBotones();
        add(panelBotones, BorderLayout.SOUTH);
    }

    /**
     * Se encarga de dar margenes al panel que se desee, para esta función solo se ponen
     * margen en parte izquierda, derecha e inferior
     * @param panelEnvio
     * @param nuevaDimension
     */
    private void prepareMargenInferior(JPanel panelEnvio, Dimension nuevaDimension){
        //CREACION DEL MARGEN IZQUIERDO
        panelIzquierdo = new JPanel();
        panelIzquierdo.setPreferredSize(nuevaDimension);
        panelIzquierdo.setBackground(Color.DARK_GRAY.brighter());
        panelEnvio.add(panelIzquierdo, BorderLayout.WEST);

        //CREACION DEL MARGEN DERECHO
        panelDerecho = new JPanel();
        panelDerecho.setPreferredSize(nuevaDimension);
        panelDerecho.setBackground(Color.DARK_GRAY.brighter());
        panelEnvio.add(panelDerecho, BorderLayout.EAST);

        //CREACION DEL MARGEN INFERIOR
        panelAbajo = new JPanel();
        panelAbajo.setPreferredSize(nuevaDimension);
        panelAbajo.setBackground(Color.DARK_GRAY.brighter());
        panelEnvio.add(panelAbajo, BorderLayout.SOUTH);
    }

    /**
     * Se encarga de insertar los botones en un panel que es de tipo GridLayout
     */
    private void insertarBotones(){
        boton1 = new JButton("VOLVER");
        boton2 = new JButton("CARGAR PARTIDA");
        botones = new JPanel(new GridLayout(1,2,10,10));
        botones.add(boton1);
        botones.add(boton2);
        panelBotones.add(botones, BorderLayout.CENTER);

    }

    /**
     * Función encargada de preparar y meter al frame principal la parte grafica superior
     */
    private void prepararSuperior(){
        panelSuperior = new JPanel(new BorderLayout());
        //PREPARACION DE MARGENES
        prepareMargenes();
        //PREPARACION DE IMAGENES
        prepareImagen();
        add(panelSuperior, BorderLayout.NORTH);
    }

    /**
     * Se encarga de dar margenes al panel que se desee, para esta función solo se ponen
     * margen en parte izquierda, derecha y superior
     */
    private void prepareMargenes(){
        //CREACION DEL MARGEN IZQUIERDO
        panelIzquierdo = new JPanel();
        panelIzquierdo.setPreferredSize(new Dimension(170,170));
        panelIzquierdo.setBackground(Color.DARK_GRAY.brighter());
        panelSuperior.add(panelIzquierdo, BorderLayout.WEST);

        //CREACION DEL MARGEN DERECHO
        panelDerecho = new JPanel();
        panelDerecho.setPreferredSize(new Dimension(170,170));
        panelDerecho.setBackground(Color.DARK_GRAY.brighter());
        panelSuperior.add(panelDerecho, BorderLayout.EAST);

        //CREACION DEL MARGEN ARRIBA
        panelArriba = new JPanel();
        panelArriba.setPreferredSize(new Dimension(90,30));
        panelArriba.setBackground(Color.DARK_GRAY.brighter());
        panelSuperior.add(panelArriba, BorderLayout.NORTH);
    }

    /**
     * Se encarga de preparar la imagen de arriba, la cual muestra una simulación de 
     * una partida. Se espera que en caso que sea una partida de dos jugadores, aparezca
     * otra pantalla con simulación donde sean dos jugadores en una partida
     */
    private void prepareImagen(){
        JPanel panelImagen = new JPanel(new BorderLayout());
        panelImagen.setPreferredSize(new Dimension(90,250));
        Border line = BorderFactory.createLineBorder(Color.BLUE, 2);
        panelImagen.setBorder(line);
        ImageIcon imagen = new ImageIcon("imagenes/solo.png");
        JLabel label = new JLabel(imagen);
        panelImagen.add(label, BorderLayout.CENTER);
        panelSuperior.add(panelImagen,BorderLayout.CENTER);
    }

    /**
     * Se encarga de presentar correctamente la parte central de la pantalla. Esta corresponde
     * a los juegos guardados. Estos se encuentran en un gridLayout para que el usuario los pueda escoger
     */
    private void prepararCentral(){
        panelCentral = new JPanel(new BorderLayout());
        prepararBotones();
        add(panelCentral, BorderLayout.CENTER);
        prepareMargenInferior(panelCentral, new Dimension(70,70));
        prepareMargenArriba();
    }

    /**
     * Se encarga de preparar los botones del panel central.
     */
    private void prepararBotones(){
        JPanel panelGuardado = new JPanel(new GridLayout(3,1,10,10));
        panelGuardado.setPreferredSize(new Dimension(300,300));
        JButton boton1 = new JButton("1");
       // boton1.setText("<html><p>Boton</p><p>uno</p></html>");
        JButton boton2 = new JButton("2");
        JButton boton3 = new JButton("3");
        panelGuardado.setBackground(Color.GRAY.darker());
        panelGuardado.add(boton1);
        panelGuardado.add(boton2);
        panelGuardado.add(boton3);
        panelCentral.add(panelGuardado, BorderLayout.CENTER);
    }

    /**
     * Se encarga de dar margen de arriba a los botones que contienen los juegos guardados
     */
    private void prepareMargenArriba(){
        //CREACION DEL MARGEN ARRIBA
        panelArriba = new JPanel();
        panelArriba.setPreferredSize(new Dimension(30,30));
        panelArriba.setBackground(Color.DARK_GRAY.brighter());
        panelCentral.add(panelArriba, BorderLayout.NORTH);
    }

    /**
     * Se encarga de programar las acciones de los botones que se hayan 
     * definido
     */
    private void prepareAcciones(){
        /* VUELVA A LA SEGUNDA PANTALLA */
       boton1.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            volver();
        }
    });

    /* CARGAR PARTIDA*/
    boton2.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            JOptionPane.showMessageDialog(null, "Cargar se hace dentro de la segunda entrega", "ATENCION", JOptionPane.DEFAULT_OPTION, null);
        }
    });
    }

    /**
     * Se encarga de volver a la segunda pantalla
     */
    private void volver(){
        SegundaPantalla pantalla2 = new SegundaPantalla();
        pantalla2.setVisible(true);
        this.setVisible(false);
    }
}
