package presentacion;
import javax.swing.JFrame;
import java.awt.*;
import aplicacion.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JComboBox;
import java.util.Hashtable;
import javax.swing.JOptionPane;
public class Pausa extends JFrame{
    //DIMENSIONES INICIALES DE LA PANTALLA
    Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
    private final Dimension DIMENSION_PREFERIDA = new Dimension(800,600);

    private JPanel panelIzquierdo, panelDerecho, panelAbajo, panelArriba;

    private JButton boton1,boton2,boton3,boton4;

    private JComboBox<ImageIcon> comboArray = new JComboBox<ImageIcon>();

    private Hashtable<Object, ImageIcon> icono = null;

    private String[] items = new String[2];

    private String seguirDireccion;

    private SnOOpe seguir;


    //CONSTRUCTOR DE LA PANTALLA DE CONFIGURACION
    public Pausa(SnOOpe juego, String direccion){
        //PARA QUE LA PANTALLA SIEMPRE APAREZCA EN LA MITAD
        this.setLocationRelativeTo(null);
        this.seguir = juego;
        this.seguirDireccion = direccion;
        //this.pantallaAnterior = pantalla;
        prepareElementos();
        prepareAcciones();
    }

    /**
     * Se encarga de preparar toda la parte gráfica del frame de configuracion, tales como
     * las dimensiones del frame, preparación de botones y margenes
     */
    private void prepareElementos(){
        //TITULO Y DIMENSIONES DE LA PANTALLA
        setTitle("Snake");
        setSize(DIMENSION_PREFERIDA);
        setLayout(new BorderLayout());

        //Preparacion de la imagen principal
        this.setLocationRelativeTo(null);
        setSize(DIMENSION_PREFERIDA);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        //PREPARACION DE BOTONES
        prepararBotones();

        //PREPARACION DE MARGENES
        prepareMargenes();
    }

    /**
     * Se encarga de preparar los 4 botones que son las opciones de
     * la configuracion
     */
    private void prepararBotones(){
        JPanel panel_botones = new JPanel(new GridLayout(4,1,20,20));
        panel_botones.setBackground(Color.CYAN.darker());
        boton1 = new JButton("GUARDAR JUEGO");
        boton2 = new JButton("CARGAR JUEGO");
        boton3 = new JButton("MENU PRINCIPAL");
        boton4 = new JButton("VOLVER");
        panel_botones.add(boton1);
        panel_botones.add(boton2);
        panel_botones.add(boton3);
        panel_botones.add(boton4);
        add(panel_botones, BorderLayout.CENTER);
    }

    /**
     * Se encarga de generar margenes a los botones para que no 
     * queden tan grandes y tengan un espacio entre ellos.
     * Se manejan las 4 margenes
     */
    private void prepareMargenes(){
        //PANEL PARA LA MARGEN IZQUIERDA
        panelIzquierdo = new JPanel();
        panelIzquierdo.setPreferredSize(new Dimension(120,120));
        panelIzquierdo.setBackground(Color.CYAN.darker());
        add(panelIzquierdo, BorderLayout.WEST);

        //PANEL PARA LA MARGEN DERECHA
        panelDerecho = new JPanel();
        panelDerecho.setPreferredSize(new Dimension(120,120));
        panelDerecho.setBackground(Color.CYAN.darker());
        add(panelDerecho, BorderLayout.EAST);

        //CREACION DEL MARGEN INFERIOR
        panelAbajo = new JPanel();
        panelAbajo.setPreferredSize(new Dimension(50,50));
        panelAbajo.setBackground(Color.CYAN.darker());
        add(panelAbajo, BorderLayout.SOUTH);

        //CREACION DEL MARGEN SUPERIOR
        panelArriba = new JPanel();
        panelArriba.setPreferredSize(new Dimension(50,50));
        panelArriba.setBackground(Color.CYAN.darker());
        add(panelArriba, BorderLayout.NORTH);
    }

    /**
     * Se encarga de programar las acciones de los botones
     * ya definidos
     */
    private void prepareAcciones(){
        //Vuelve al juego o a la pantalla
        boton4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                volver();
            }
        });
    }

    /**
     * Se encarga de volver a la pantalla anterior, se tienen
     * los casos donde vuelve al inicio y donde vuelve al juego
     */
    private void volver(){
        solitario seguirJuego = new solitario();
        seguirJuego.setGame(this.seguir);
        seguirJuego.direccion = this.seguirDireccion;
        this.setVisible(false);
        seguirJuego.setVisible(true);
    }

    /**
     * Se encarga de revisar cual fue el ícono seleccionao
     * @param itemSeleccionado
     * @return
     */
    private String revision(ImageIcon itemSeleccionado){
        if(itemSeleccionado.equals(icono.get("uno"))){
            return "uno";
        }
        else if(itemSeleccionado.equals(icono.get("dos"))){
            return "dos";
        }
        else if(itemSeleccionado.equals(icono.get("tres"))){
            return "tres";
        }
        else if(itemSeleccionado.equals(icono.get("cuatro"))){
            return "cuatro";
        }
        return null;
    }

    /**
     * Se encarga de meter las opciones del box en un string
     * @param item
     */
    private void meterString(String item){
        if(items[0] == null && items[1] == null){
            System.out.print("se va a meter en la primer posicion" + item);
            items[0] = item;
        }
        else if(items[0] != null && items[1] == null){
            System.out.print("Se va a meter en la segunda posicion" + item);
            items[1] = item;
        }
        else{
            System.out.print("no se puede meter");
        }
    }
}
