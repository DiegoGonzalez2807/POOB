package presentacion;
import java.awt.*;
import java.lang.module.Configuration;

import javax.swing.*;
import presentacion.PantallaInicio;

public class SnakeGUI extends JFrame {

	private String snake;

	private boolean wall;
	//DIMENSIONES INICIALES DE LA PANTALLA
	Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private final Dimension DIMENSION_PREFERIDA = new Dimension(800,600);

	public  SnakeGUI() {
		//PARA QUE LA PANTALLA SIEMPRE APAREZCA EN LA MITAD
		this.setLocationRelativeTo(null);

		//SE LLAMA A LA PANTALLA DE INICIO
		solitario pantalla1 = new solitario();
		pantalla1.setVisible(true);
		prepareElementos();
	}

	private void prepareElementos() {
		setSize(DIMENSION_PREFERIDA);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	public static void main(String[] args) {
		SnakeGUI gui = new SnakeGUI();
		//gui.setVisible(true);
	}
}
