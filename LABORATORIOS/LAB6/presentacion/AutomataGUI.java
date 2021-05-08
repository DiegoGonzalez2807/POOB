package presentacion;
import dominio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.filechooser.*;

public class AutomataGUI extends JFrame implements Serializable{  
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

    /**
     * Funcion encargada de la preparación del menu de opciones del simulador
     */
    private void prepareElementosMenu(){
        //CREADOR DEL MENU
        menu();
        //INSERTAR OPCIONES DEL MENU
        opcionesMenu();
    }

    /**
     * Se encarga de meter la barra de menú que hayamos iniciado
     */
    private void menu(){
        setJMenuBar(menu_bar);
    }

    /**
     * Funcion que se encarga de meter los botones en la barra de menu que hayamos creado
     */
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
            AutomataCelular nuevo = new AutomataCelular();
            this.setVisible(false);
            this.automata = nuevo;
            this.setVisible(true);

    }

    private void opcionAbrir(){
        File archivo = new File("");
        try{
            JFileChooser fileChooser = new JFileChooser();
            //FILTRA TODOS LOS ARCHIVOS Y SOLO DEJA LOS VISIBLES
            //LOS QUE TENGAN EXTENSION .DAT.
            fileChooser.setFileFilter(new FileNameExtensionFilter("Archivo con extensión .DAT","DAT"));
            int seleccion = fileChooser.showOpenDialog(this);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                archivo = fileChooser.getSelectedFile();
            }
            AutomataCelular automata_ = automata.abrir(archivo);
            this.setVisible(false);
            this.automata = automata_;
            this.setVisible(true);}
            catch(Exception e){
                
        }

    }

    private void opcionGuardar(){
        try{
            JFileChooser fileChooser = new JFileChooser();
            //FILTRA TODOS LOS ARCHIVOS Y SOLO DEJA LOS VISIBLES
            //LOS QUE TENGAN EXTENSION .DAT.    
            fileChooser.setFileFilter(new FileNameExtensionFilter("Archivo con extensión .DAT","DAT"));
            int seleccion = fileChooser.showSaveDialog(this);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                this.automata.guardar(this.automata,fileChooser.getSelectedFile());
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void opcionImportar(){
        File archivo = new File("");
        try{
            JFileChooser fileChooser = new JFileChooser();
            //FILTRA TODOS LOS ARCHIVOS Y SOLO DEJA LOS VISIBLES
            //LOS QUE TENGAN EXTENSION .DAT.
            fileChooser.setFileFilter(new FileNameExtensionFilter("Archivo con extensión .TXT","TXT"));
            int seleccion = fileChooser.showOpenDialog(this);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                archivo = fileChooser.getSelectedFile();
            }
            AutomataCelular automata_ = automata.importe03(archivo);
            this.setVisible(false);
            this.automata = automata_;
            this.setVisible(true);}
            catch(Exception e){
                JOptionPane.showMessageDialog(this, e.getMessage()+" "+archivo.getName());
                
        }
    }

    private void opcionExportar(){
        try{
            JFileChooser fileChooser = new JFileChooser();
            //FILTRA TODOS LOS ARCHIVOS Y SOLO DEJA LOS VISIBLES
            //LOS QUE TENGAN EXTENSION .DAT.
            fileChooser.setFileFilter(new FileNameExtensionFilter("Archivo con extensión .TXT","TXT"));
            int seleccion = fileChooser.showSaveDialog(this);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                this.automata.exportar(fileChooser.getSelectedFile());
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
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
