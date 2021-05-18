package presentacion;
import aplicacion.*;
import aplicacion.sorpresas.*;
import aplicacion.alimentos.Alimento;


import java.awt.image.BufferedImage;
import java.sql.Time;
import java.util.TimerTask;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

import java.util.Timer;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import javax.swing.border.Border;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class solitario extends JFrame{
    //DIMENSIONES INICIALES DE LA PANTALLA
    Toolkit t = Toolkit.getDefaultToolkit ();
    Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
    private final Dimension DIMENSION_PREFERIDA = new Dimension(625,710);

    //PANELES
    private JPanel panelSuperior, panelTitular;

    //CONEXION CAPA DE APLICACION
    private SnOOpe snake;

    //GRAFICAS DEL JUEGO
    private FotoSnake snakePintada;

    //DIRECCION DE LA SERPIENTE
    public String direccion = "DERECHO";

    //VELOCIDAD DE LA SERPIENTE
    private int velocidad = 70;

    //DIRECCION QUE ESTABA ANTES DE LA PAUSA
    public String direccionAnterior;

    /**
     * Constructor del juego en solitario
     */
    public solitario(){
        //SE PIDE UNA CONEXION CON LA CAPA DE DOMINIO, ES DECIR QUE SE VA A HACER UN JUEGO A 
        //PARTIR DE LAS ESPECIFICACIONES QUE DIO EL USUARIO EN PANTALLAS ANTERIORES
        snake = new SnOOpe("Diego", new String[] {"Division"}, new String[] {"Dulce"}, new Color[] {Color.RED, Color.GREEN});
        try{
            snake.empezar();
        }
        catch(Exception e){

        }
        //PROGRAMACION DE EVENTOS DEL TECLADO PARA MOVER LA SERPIENTE
        programacionTeclas eventos = new programacionTeclas(this);
        addKeyListener(eventos);
        prepareElementos();
        prepareAcciones();
        //SE LE PIDE AL HILO QUE YA PUEDE EMPEZAR A EJECUTAR PERO CON UN TIEMPO DE DIFERENCIA
        //ESE TIEMPO LO DA EL USUARIO Y SE VA CAMBIANDO RESPECTO A LO QUE COMA LA SERPIENTE
        correr corre1 = new correr(this,velocidad);
        Thread trid1 = new Thread(corre1);
        trid1.start();
        nuevoAlimento alimentar = new nuevoAlimento(this);
        Timer temporizador = new Timer();
        int segundos = 10;
        temporizador.scheduleAtFixedRate(alimentar,0,1000*segundos);
    }

    /**
     * Retorna el juego SnOOPE con el cual estamos haciendo la conexion
     * @return
     */
    public SnOOpe getGame(){
        return this.snake;
    }

    public Tablero getActualTablero(){
        return this.snake.getTablero();
    }

    public void setGame(SnOOpe nuevoJuego){
        this.snake = nuevoJuego;
    }

    /**
     * Prepara todos los elementos graficos del frame, tales como el panel donde se va a mostrar el movimiento
     * de la serpiente, sorpresas y demas, asi como el panel de score. Se encarga tambien de que el frame
     * aparezca en la mitad de la pantalla
     */
    private void prepareElementos(){
        setTitle("Snake");
        setSize(DIMENSION_PREFERIDA);
        setLocationRelativeTo(null);
        snakePintada = new FotoSnake(this);
        snakePintada.repaint();
        preparePanelSuperior();
        prepareJuego();
    }

    /**
     * Se encarga de preparar el panel superior del frame, el cual corresponde al score. Se preparan
     * margenes para que no quede tan pegado al borde el score, si no un poco a la derecha
     */
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

    /**
     * Se encarga de preparar el score, lo cual son dos cosas, la primera es la palabra "SCORE" y la segunda es
     * el puntaje que lleva la serpiente, el cual se va actualizando a medida que va comiendo alimentos. Este
     * puntaje va debajo de la palabra, por lo que se usa un BoxLayout para crearlo
     */
    private void prepareScore(){
        panelTitular = new JPanel();
        panelTitular.setLayout(new BoxLayout(panelTitular, BoxLayout.Y_AXIS));
        JLabel titulo1 = new JLabel("SCORE");
        JLabel titulo2 = new JLabel(Integer.toString(getGame().getJugadores().get(0).getPuntaje()));
        panelTitular.add(titulo1);
        panelTitular.add(titulo2);
        panelTitular.setBackground(Color.GREEN);
        titulo1.setFont(new Font("Serif", Font.PLAIN, 20));
        titulo2.setFont(new Font("Serif", Font.PLAIN, 20));
        panelSuperior.add(panelTitular, BorderLayout.CENTER);
    }

    /**
     * Se encarga de la programación de botones
     */
    private void prepareAcciones(){
    }

    /**
     * Se encarga de insertar el panel donde se va a mover la serpiente y se van a insertar los demás
     * elementos como alimentos y sorpresas
     */
    private void prepareJuego(){
        add(snakePintada, BorderLayout.CENTER);
    }

    /**
     * Se encarga de revisar la longitud de la serpiente, retorna un booleano debido a que si la longitud
     * es multiplo de 5, entonces se debe disminuir la velocidad
     * @return
     */
    private int longitudSerpiente(){
        int count = 0;
        for(int i = 0;i<getGame().getFilas();i++){
            for(int j = 0;j<getGame().getColumnas();j++){
                if(getGame().getTablero().getObjeto(i,j) instanceof Circle){
                    count += 1;
                }
            }
        }
        return count;
    }

    /**
     * Clase encargada de escuchar todos los eventos del teclado
     */
    public class programacionTeclas implements KeyListener{

        private solitario juego;

        /**
         * Constructor de la clase programacionTeclas
         * @param juego
         */
        public programacionTeclas(solitario juego){
            this.juego = juego;
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        /**
         * Se encarga de cambiar la direccion de la serpiente de acuerdo
         */
        @Override
        public void keyPressed(KeyEvent e) {   
            //DIRECCION IZQUIERDA
            if(e.getKeyCode() == KeyEvent.VK_LEFT){
                if(direccion != "DERECHO"){
                    direccion = "IZQUIERDO";
                }
            }   
            //DIRECCION DERECHA
            else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                if(direccion != "IZQUIERDO"){
                    direccion = "DERECHO";
                }
            }
            //DIRECCION ABAJO
            else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                if(direccion != "ARRIBA"){
                    direccion = "ABAJO";
                }  
            }
            //DIRECCION ARRIBA
            else if(e.getKeyCode() == KeyEvent.VK_UP){
                if(direccion != "ABAJO"){
                    direccion = "ARRIBA";
                }
            }

            //BOTON DE PAUSA
            else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                direccion = "PAUSA";     
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    /**
     * Clase encargada de actualizar la comida que no fue consumida por la serpiente 
     * cada 10 segundos
     */
    public class nuevoAlimento extends TimerTask{
        private int count;
        private solitario juego;

        /**
         * Constructor de la clase nuevoAlimento
         * @param juego
         */
        public nuevoAlimento(solitario juego){
            this.count = 0;
            this.juego = juego;
        }

        /**
         * Se encarga de revisar el tablero donde estamos jugando, en caso que haya comida, la elimina
         * y genera en otras posiciones comida nueva
         */
        public void run(){
            SnOOpe serpiente = juego.getGame();
            if(serpiente.cantidadAlimentos() > 0){
                for(int i = 0; i< serpiente.getFilas();i++){
                    for(int j = 0; j < serpiente.getColumnas();j++){
                        if(serpiente.getTablero().getObjeto(i,j) instanceof Alimento){
                            serpiente.getTablero().setObjeto(null,i,j);
                        }
                    }
                }
            }
            try{
                serpiente.generarAlimento();
                serpiente.generarAlimento();
            } 
            catch(Exception e){}
        }
    }

    /**
     * Clase encargada de correr a la serpiente en el tablero de acuerdo a 
     * la direccion que hayamos dado en el teclado
     */
    public class correr extends Thread{
        private solitario juego;
        private int tiempo;

        /**
         * Constructor de la clase correr
         * @param juego
         * @param sleep
         */
        public correr(solitario juego, int sleep){
            this.juego = juego;
            this.tiempo = sleep;
        }

        /**
         * Se encarga de revisar la direccion que tiene la serpiente para ir actualizando
         * el movimiento de esta. Este movimiento se genera hasta que la serpiente muera
         */
        public void run(){
            while(true){
                try{
                    sleep(tiempo);
                }catch(Exception e){
                }
                //ACTUALIZACION DIRECCION DERECHA
                if(direccion.equals("DERECHO")){
                    try{
                        direccionAnterior = "DERECHO";
                        snake.mover(snake.getJugadores().get(0), "Derecha");
                        snakePintada.repaint();
                        
                    }
                    catch(Exception e){
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, e.getMessage()+"\n SCORE: 5000", "Has perdido :c", JOptionPane.WARNING_MESSAGE);
                    }
                }
                //ACTUALIZACION DIRECCION IZQUERDA
                else if(direccion.equals("IZQUIERDO")){
                    try{
                        direccionAnterior = "IZQUIERDO";
                        snake.mover(snake.getJugadores().get(0), "Izquierda");
                        snakePintada.repaint();
                    }
                    catch(Exception e){
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null,  e.getMessage()+"\n SCORE: 5000", "Has perdido :c", JOptionPane.WARNING_MESSAGE);
                  
                    }
                }
                //ACTUALIZACION DIRECCION ARRIBA
                else if(direccion.equals("ARRIBA")){
                    try{
                        direccionAnterior = "ARRIBA";
                        snake.mover(snake.getJugadores().get(0), "Arriba");
                        snakePintada.repaint();
                    }
                    catch(Exception e){ 
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, e.getMessage()+"\n SCORE: 5000", "Has perdido :c", JOptionPane.WARNING_MESSAGE);
                    }
                }
                //ACTUALIZACION DIRECCION ABAJO
                else if(direccion.equals("ABAJO")){
                    try{
                        direccionAnterior = "ABAJO";
                        snake.mover(snake.getJugadores().get(0), "Abajo");
                        snakePintada.repaint();
                    }
                    catch(Exception e){
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, e.getMessage()+"\n SCORE: 5000", "Has perdido :c", JOptionPane.WARNING_MESSAGE);
                    }
                }
                //ACTUALIZACION BOTON DE PAUSA
                else if(direccion.equals("PAUSA")){
                    Pausa configura = new Pausa(getGame(), direccionAnterior);
                    configura.setVisible(true);
                    juego.setVisible(false);
                    break;
                    
                }
            }
        }
    }

    /**
     * Clase encargada de pintar el tablero de juego, así como de ir repintando a medida
     * que se cambian objetos o cosas en el tablero.
     */
    public  class FotoSnake extends JPanel {
        private solitario juego;

        /**
         * Constructor de la clase FotoSnake
         * @param juego
         */
        public FotoSnake(solitario juego){
            this.juego = juego;
            setBackground(Color.yellow);
            setPreferredSize(new Dimension(100,100));
        }

        /**
         * Se encarga de pintar la cuadricula del juego y de los elementos que tenga
         * el tablero de juego
         */
        public void paintComponent(Graphics g){
            snake = juego.getGame();
           /**  if(!snake.reviewAlimento()){
                try{
                    snake.generarAlimento();
                }catch(Exception e){}
            }*/
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
                            //g.setColor(Color.BLUE);
                            //g.fillRect(j*19+1,i*19+1,19-2,19-2);
                            g.drawImage(imagen,j*19+1,i*19+1,this);
                        }
                        else if(objeto instanceof Sorpresa){
                            g.fillRect(j*19+1,i*19+1, 19-2,19-2);
                        }
                        else{
                            Color nuevo = snake.getTablero().getObjeto(i,j).getColor();
                            g.setColor(nuevo);
                            g.fillOval(j*19+1,i*19+1, 19-2,19-2);
                        }
                    }
                }
            }
        }
    }
}
    


