package presentacion;
import aplicacion.*;
import aplicacion.alimentos.Alimento;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import javax.swing.border.Border;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class prueba extends JFrame{
    //DIMENSIONES INICIALES DE LA PANTALLA
    Toolkit t = Toolkit.getDefaultToolkit ();
    Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
    private final Dimension DIMENSION_PREFERIDA = new Dimension(625,727);

    private JPanel panelSuperior, panelTitular;

    private JButton botonPausa;

    private SnOOpe snake;

    private FotoSnake snakePintada;

    private long frecuencia = 60;

    String direccion = "DERECHO";

    public prueba(){
        snake = new SnOOpe("Diego", new String[] {"Division"}, new String[] {"Dulce"}, new Color[] {Color.RED, Color.GREEN});
        try{
            snake.mover(snake.getJugadores().get(0), "Derecha");
            snake.getJugadores().get(0).getSnake().crecer(5);
        }
        catch(Exception e){

        }
        programacionTeclas eventos = new programacionTeclas();
        addKeyListener(eventos);
        prepareElementos();
        prepareAcciones();
    }

    private SnOOpe getGame(){
        return this.snake;
    }

    private void prepareElementos(){
        setTitle("Snake");
        setSize(DIMENSION_PREFERIDA);
        setLocationRelativeTo(null);
        snakePintada = new FotoSnake(this);
        snakePintada.repaint();
        correr corre1 = new correr();
        preparePanelSuperior();
        prepareJuego();
    }

    private void preparePanelSuperior(){
        panelSuperior = new JPanel();
        panelSuperior.setLayout(new BorderLayout());
        panelSuperior.setBackground(Color.GREEN);
        Border line = BorderFactory.createLineBorder(Color.GRAY, 4);
        panelSuperior.setBorder(line);

        //PREPARANDO LA MARGEN IZQUIERDA
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setPreferredSize(new Dimension(20,20));
        panelIzquierdo.setBackground(Color.GREEN);
        panelSuperior.add(panelIzquierdo, BorderLayout.WEST);
        add(panelSuperior,BorderLayout.NORTH);

        //PREPARANDO EL SCORE
        prepareScore();
    }

    private void prepareScore(){
        panelTitular = new JPanel();
        panelTitular.setLayout(new BoxLayout(panelTitular, BoxLayout.Y_AXIS));
        JLabel titulo1 = new JLabel("SCORE");
        JLabel titulo2 = new JLabel("5000");
        panelTitular.add(titulo1);
        panelTitular.add(titulo2);
        panelTitular.setBackground(Color.GREEN);
        titulo1.setFont(new Font("Serif", Font.PLAIN, 20));
        titulo2.setFont(new Font("Serif", Font.PLAIN, 20));
        panelSuperior.add(panelTitular, BorderLayout.CENTER);
    }

    private void prepareAcciones(){

    }

    private void pausa(){
        //Pausa pantallaPausa = new Pausa();
        //pantallaPausa.setVisible(true);
        //this.setVisible(false);
    }

    private void prepareJuego(){
        add(snakePintada, BorderLayout.CENTER);
    }


    public class programacionTeclas implements KeyListener{

        private correr corre1 = new correr();

        private long ultimo = 20;
        private int velocidad = 0;

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {   
            if(e.getKeyCode() == KeyEvent.VK_LEFT){
                if(direccion != "DERECHO"){
                    direccion = "IZQUIERDO";
                    corre1.correr1();
                }
            }   
            else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                if(direccion != "IZQUIERDO"){
                    direccion = "DERECHO";
                    corre1.correr1();
                }
            }
            else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                if(direccion != "ARRIBA"){
                    direccion = "ABAJO";
                    corre1.correr1();
                }  
            }
            else if(e.getKeyCode() == KeyEvent.VK_UP){
                if(direccion != "ABAJO"){
                    direccion = "ARRIBA";
                    corre1.correr1();
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    public class correr {
        public void correr1(){
            if(direccion.equals("DERECHO")){
                try{
                    snake.mover(snake.getJugadores().get(0), "Derecha");
                    snakePintada.repaint();
                }
                catch(Exception e){
                }
            }
            else if(direccion.equals("IZQUIERDO")){
                try{
                    snake.mover(snake.getJugadores().get(0), "Izquierda");
                    snakePintada.repaint();
                }
                catch(Exception e){

                }
            }
            else if(direccion.equals("ARRIBA")){
                try{
                    snake.mover(snake.getJugadores().get(0), "Arriba");
                    snakePintada.repaint();
                }
                catch(Exception e){

                }
            }
            else if(direccion.equals("ABAJO")){
                try{
                    snake.mover(snake.getJugadores().get(0), "Abajo");
                    snakePintada.repaint();
                }
                catch(Exception e){
                }
            }
        }
    }

    public  class FotoSnake extends JPanel {
        private prueba juego;

        public FotoSnake(prueba juego){
            this.juego = juego;
            setBackground(Color.yellow);
            setPreferredSize(new Dimension(100,100));
        }

        public void paintComponent(Graphics g){
            snake = juego.getGame();
            super.paintComponent(g);

            for(int i = 0; i<= snake.getFilas(); i++){
                g.drawLine(i*19,0,i*19,snake.getFilas()*19);
            }

            for(int j = 0; j<= snake.getColumnas(); j++){
                g.drawLine(0,j*19,snake.getColumnas()*19,j*19);
            }

            for(int i = 0; i< snake.getFilas();i++){
                for(int j = 0; j< snake.getColumnas(); j++){
                    Objeto objeto = snake.getTablero().getObjeto(i,j);
                    if(objeto != null){
                        if(objeto instanceof Alimento){
                            Image imagen = t.getImage (objeto.getIcono());
                            g.drawImage(imagen,j*19+1,i*19+1,this);
                        }
                        Color nuevo = snake.getTablero().getObjeto(i,j).getColor();
                        g.setColor(nuevo);
                        g.fillOval(j*19+1,i*19+1, 19-2,19-2);
                    }
                }
            }
        }
    }
}
    


