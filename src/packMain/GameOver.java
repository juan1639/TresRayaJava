package packMain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class GameOver extends JFrame {
	
	public static final double goX = Tablero.resX / 1.3;
	public static final double goY= Tablero.resX / 4;
	
	private static JLabel etiquetaGO;
	
	// ------------------------------------------------------------------
	public GameOver(boolean empate, boolean tresRaya) {
		
		setSize((int) goX, (int) goY);
		setVisible(true);
		setTitle(" Game Over ");
		setLocationRelativeTo(null);
		setResizable(false);
		setMinimumSize(new Dimension(200, 100));
		
		//this.getContentPane().setBackground(Color.BLUE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		etiquetaGO = new JLabel();
		etiquetaGO.setFont(new Font("arial", Font.BOLD, (int) (goY / 4)));
		etiquetaGO.setFocusable(false);
		//etiquetaGO.setOpaque(true);
		etiquetaGO.setForeground(Color.ORANGE);
		//etiquetaGO.setBackground(Color.BLUE);
		this.getContentPane().add(etiquetaGO);
		
		// ------------------------------------------------
		game_over(empate, tresRaya);
	}
	
	public static void game_over(boolean empate, boolean tresRaya) {
		
		System.out.print(" Game Over \n");
		
		if (empate && tresRaya) {
			empate = false;
		}
		
		System.out.print(" Empate: " + empate + " TresRaya: " + tresRaya);
		
		String txt = empate ? " Empate! " : " Tres en Raya! ";
		etiquetaGO.setText(txt);
		
	}

}
