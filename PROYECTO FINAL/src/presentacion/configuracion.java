package presentacion;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import javax.swing.border.Border;
import javax.swing.JComboBox;
import java.util.Hashtable;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class configuracion extends JFrame{
    //DIMENSIONES INICIALES DE LA PANTALLA
    Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
    private final Dimension DIMENSION_PREFERIDA = new Dimension(800,600);

    private JPanel panelIzquierdo, panelDerecho, panelAbajo, panelArriba;

    private JButton boton1,boton2,boton3,boton4;

    private JComboBox<ImageIcon> comboArray = new JComboBox<ImageIcon>();

    private Hashtable<Object, ImageIcon> icono = null;

    private String[] items = new String[2];

    private String pantallaAnterior;

    //CONSTRUCTOR DE LA PANTALLA DE CONFIGURACION
    public configuracion(String pantalla){
        //PARA QUE LA PANTALLA SIEMPRE APAREZCA EN LA MITAD
        this.setLocationRelativeTo(null);
        this.pantallaAnterior = pantalla;
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
        boton1 = new JButton("CAMBIAR SERPIENTE");
        boton2 = new JButton("SELECCIONAR SORPRESA");
        boton3 = new JButton("SELECCIONAR ALIMENTO");
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
     * Se encarga de la primer caja que da a escoger al usuario 
     * las diferentes serpientes que puede tener.
     */
    private void box1(){
        ImageIcon[] imagenes = {
            new ImageIcon("imagenes/serpiente2.png"),
            new ImageIcon("imagenes/serpiente3.png"),
        };
        ImageIcon nueva_serpiente = (ImageIcon) JOptionPane.showInputDialog(null, "Seleccione la serpiente deseada", "Serpiente", JOptionPane.DEFAULT_OPTION, null, imagenes, imagenes[0]);
    }

    /**
     * Se encarga de dar a escoger al usuario
     * las diferentes comidas que hay en el juego. Tiene
     * que escoger dos
     */
    private void box3(){
        ImageIcon[] imagenes = {
            new ImageIcon("imagenes/manzana.png"),
            new ImageIcon("imagenes/tricolor.png"),
            new ImageIcon("imagenes/dulce.png"),
            new ImageIcon("imagenes/veneno.png"),
        };
        
        ImageIcon icono1 = (ImageIcon) JOptionPane.showInputDialog(null, "Seleccione la primer fruta", "Frutas", JOptionPane.DEFAULT_OPTION, null, imagenes, imagenes[0]);
        ImageIcon icono2 = (ImageIcon) JOptionPane.showInputDialog(null, "Seleccione la segunda fruta", "Frutas", JOptionPane.DEFAULT_OPTION, null, imagenes, imagenes[0]);
    }

    /**
     * Se encarga de programar las acciones de los botones
     * ya definidos
     */
    private void prepareAcciones(){
        //Se va a las opciones de comida
        boton3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                box3();
                prepareAccionCombo();
            }
        });
        //Se va a las opciones de serpiente
        boton1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                box1();
                prepareAccionCombo();
            }
        });
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
        if(this.pantallaAnterior.equals("PantallaInicio")){
            PantallaInicio anterior = new PantallaInicio();
            anterior.setVisible(true);
            this.setVisible(false);
        }
        else if(this.pantallaAnterior.equals("Solitario")){
            solitario anterior = new solitario();
            anterior.setVisible(true);
            this.setVisible(false);
        }
           
    }

    /**
     * Se encarga de preparar las opciones escogidas en 
     * cada box tanto de comidas como de serpiente
     */
    private void prepareAccionCombo(){
        comboArray.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ImageIcon itemSeleccionado = (ImageIcon) comboArray.getSelectedItem();
                String item = revision(itemSeleccionado);
                if(item != null){
                    meterString(item);
                }
            }
        });
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
