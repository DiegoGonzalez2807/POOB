package presentacion;
import domain.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.JOptionPane;

public class AutomataGUI extends JFrame{  
    public static final int CELDA=21;
    public static final int DIMENSION=CELDA*31;
    
    private JButton botonTicTac;
    private JLabel lFila;
    private JLabel lColumna;
    private JTextField tFila;
    private JTextField tColumna;
    private JPanel panelControl;
    private JPanel panelNueva;
    private JPanel panelBNueva;
    private JButton botonViva;
    private JButton botonLatente;
    private FotoAutomata foto;
    private JMenuBar menu_bar = new JMenuBar();
    private JMenu menu1;
    private JMenuItem opcion1,opcion2,opcion3,opcion4,opcion5,opcion6;
    
    private AutomataCelular automata;

    public AutomataGUI() {
        automata=new AutomataCelular();
        prepareElementos();
        prepareAcciones();
    }
    
    private void prepareElementos() {
        setTitle("Automata celular");
        foto=new FotoAutomata(this);
        botonTicTac=new JButton("Tic-tac");
        setLayout(new BorderLayout());
        add(foto,BorderLayout.NORTH);
        add(botonTicTac,BorderLayout.SOUTH);
        setSize(new Dimension(DIMENSION,DIMENSION+50)); 
        setResizable(false);
        foto.repaint();

        //PREPARACION DEL MENU DE ARRIBA
        prepareElementosMenu();
    }

    private void prepareElementosMenu(){
        //CREADOR DEL MENU
        menu();
        //INSERTAR OPCIONES DEL MENU
        opcionesMenu();
    }

    private void menu(){
        setJMenuBar(menu_bar);
    }

    private void opcionesMenu(){
        menu1 = new JMenu("Menu");
        menu_bar.add(menu1);
        opcion1 = new JMenuItem("Nuevo");
        menu1.add(opcion1);
        opcion2 = new JMenuItem("Abrir");
        menu1.add(opcion2);
        opcion3 = new JMenuItem("Guardar Como");
        menu1.add(opcion3);
        opcion4 = new JMenuItem("Importar");
        menu1.add(opcion4);
        opcion5 = new JMenuItem("Exportar Como");
        menu1.add(opcion5);
        opcion6 = new JMenuItem("Salir");
        menu1.add(opcion6);
    }

    private void prepareAcciones(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);       
        botonTicTac.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    botonTicTacAccion();
                }
            });
        
        //PREPARACION DE LOS BOTONES DEL MENU
        prepareAccionesMenu();
    }

    private void botonTicTacAccion() {
        automata.ticTac();
        foto.repaint();
    }

    /**
     * Se ecarga de programar las funcionalidades de los botones del menu de opciones.
     * Tener en cuenta de que cada a boton, cuando se quiera ejecutar va a saltar un error
     * diciendo que los metodos siguen en desarrollo
     * 
     */
    private void prepareAccionesMenu(){
        /* PREPARA UN NUEVO ARCHIVO */
        opcion1.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    opcionNuevo();
                }
            });

        /* ABRE UN NUEVO ARCHIVO */
        opcion2.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    opcionAbrir();
                }
            });
            
        /* GUARDA UN ARCHIVO */
        opcion3.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    opcionGuardar();
                }
            });

        /* IMPORTA UN ARCHIVO */
        opcion4.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    opcionImportar();
                }
            });

        /* EXPORTA UN ARCHIVO */
        opcion5.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    opcionExportar();
                }
            });

        /* SALE DEL SIMULADOR */
        opcion6.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    opcionSalir();
                }
            });
            
    }

    private void opcionNuevo(){
        try{
            File archivo = new File("");
            automata.nuevo(archivo);
        }catch(AutomataExcepcion e){
            JOptionPane.showMessageDialog(null, "Metodo en construccion, aun no se puede crear un nuevo archivo");
        }
    }

    private void opcionAbrir(){
        try{
            File archivo = new File("");
            automata.abrir(archivo);
        }catch(AutomataExcepcion e){
            JOptionPane.showMessageDialog(null, "Metodo en construccion, aun no se puede abrir archivos externos");
        }
    }

    private void opcionGuardar(){
        try{
            File archivo = new File("");
            automata.guardar(archivo);
        }catch(AutomataExcepcion e){
            JOptionPane.showMessageDialog(null, "Metodo en construccion, aun no se pueden guardar archivos");
        }
    }

    private void opcionImportar(){
        try{
            File archivo = new File("");
            automata.importar(archivo);
        }catch(AutomataExcepcion e){
            JOptionPane.showMessageDialog(null, "Metodo en construccion, aun no se puede importar archivos");
        }
    }

    private void opcionExportar(){
        try{
            File archivo = new File("");
            automata.exportar(archivo);
        }catch(AutomataExcepcion e){
            JOptionPane.showMessageDialog(null, "Metodo en construccion, aun no se puede exportar archivos");
        }
    }

    private void opcionSalir(){
        System.exit(0);
    }

    public AutomataCelular getAutomata(){
        return automata;
    }
    
    public static void main(String[] args) {
        AutomataGUI ca=new AutomataGUI();
        ca.setVisible(true);
    }  
}

class FotoAutomata extends JPanel{
    private AutomataGUI gui;

    public FotoAutomata(AutomataGUI gui) {
        this.gui=gui;
        setBackground(Color.white);
        setPreferredSize(new Dimension(gui.DIMENSION, gui.DIMENSION));         
    }


    public void paintComponent(Graphics g){
        AutomataCelular automata=gui.getAutomata();
        super.paintComponent(g);
         
        for (int f=0;f<=automata.getLongitud();f++){
            g.drawLine(f*gui.CELDA,0,f*gui.CELDA,automata.getLongitud()*gui.CELDA);
        }
        for (int c=0;c<=automata.getLongitud();c++){
            g.drawLine(0,c*gui.CELDA,automata.getLongitud()*gui.CELDA,c*gui.CELDA);
        }       
        for (int f=0;f<automata.getLongitud();f++){
            for(int c=0;c<automata.getLongitud();c++){
                if (automata.getElemento(f,c)!=null){
                    g.setColor(automata.getElemento(f,c).getColor());
                    if (automata.getElemento(f,c).forma()==Elemento.CUADRADA){                  
                        if (automata.getElemento(f,c).isVivo()){
                            g.fillRoundRect(gui.CELDA*c+1,gui.CELDA*f+1,gui.CELDA-2,gui.CELDA-2,2,2);
                        }else{
                            g.drawRoundRect(gui.CELDA*c+1,gui.CELDA*f+1,gui.CELDA-2,gui.CELDA-2,2,2);    

                        }
                    }else {
                        if (automata.getElemento(f,c).isVivo()){
                            g.fillOval(gui.CELDA*c+1,gui.CELDA*f+1,gui.CELDA-2,gui.CELDA-2);
                        } else {
                            g.drawOval(gui.CELDA*c+1,gui.CELDA*f+1,gui.CELDA-2,gui.CELDA-2);
                        }
                    }
                }
            }
        }
    }
}
