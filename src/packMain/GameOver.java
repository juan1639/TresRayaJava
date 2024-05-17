package packMain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameOver extends JFrame {
	
	public static final double goX = Tablero.resX / 1.3;
	public static final double goY = Tablero.resX / 4;
	
	private static JPanel panel;
	private static JLabel etiquetaGO;
	private static JButton botonNG;
	
	public GameOver(boolean empate, boolean tresRaya, boolean ganador) {
		
		settingsGameOver();
		
		panel = new JPanel();
		this.getContentPane().add(panel);
		
		etiquetaMsgGO(game_over(empate, tresRaya, ganador));
		botonNewGame();
		botonNG.addMouseListener(eventoRatonNewGame());
	}

	public void settingsGameOver() {
		
		setSize((int) goX, (int) goY);
		setVisible(true);
		setTitle(" Game Over ");
		setLocationRelativeTo(null);
		setResizable(false);
		setMinimumSize(new Dimension(200, 100));
		
		//this.getContentPane().setBackground(Color.BLUE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void etiquetaMsgGO(String txt) {
		
		etiquetaGO = new JLabel();
		etiquetaGO.setFont(new Font("arial", Font.BOLD, (int) (goY / 4)));
		etiquetaGO.setText(txt);
		etiquetaGO.setFocusable(false);
		etiquetaGO.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaGO.setVerticalAlignment(SwingConstants.TOP);
		//etiquetaGO.setOpaque(true);
		etiquetaGO.setForeground(Color.ORANGE);
		//etiquetaGO.setBackground(Color.BLUE);
		panel.add(etiquetaGO);
	}
	
	public static void botonNewGame() {
		
		botonNG = new JButton();
		botonNG.setFont(new Font("arial", Font.BOLD, (int) (goY / 6)));
		botonNG.setText(" Jugar otra vez ");
		//botonNG.setSize((int) (goX / 1.3), (int) (goY / 8));
		botonNG.setFocusable(false);
		//botonNG.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1, true));
		botonNG.setHorizontalAlignment(SwingConstants.CENTER);
		botonNG.setVerticalAlignment(SwingConstants.BOTTOM);
		//botonNG.setOpaque(true);
		botonNG.setForeground(Color.BLUE);
		//botonNG.setBackground(Color.RED);
		panel.add(botonNG);
	}
	
	public static String game_over(boolean empate, boolean tresRaya, boolean ganador) {
		
		System.out.print(" Game Over \n");
		
		if (empate && tresRaya) {
			empate = false;
		}
		
		System.out.print(" Empate: " + empate + " TresRaya: " + tresRaya);
		String txt = empate ? " Partida empatada! " : " Tres en Raya! ";
		
		if (!empate) {
			String txtGanador = ganador ? " Ganaste! " : " Perdiste! ";
			txt += txtGanador;
		}
		
		return txt;
	}
	
	public MouseListener eventoRatonNewGame() {
		
		MouseListener newGame = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
				dispose();
				
				Tablero.panel.removeAll();
				Tablero.iniciarComponentes();
				Tablero.panel.repaint();
				
				Tablero.setTurno(Tablero.isSave_quien_comienza());
				Tablero.setEnJuego(true);
				
				if (!Tablero.isSave_quien_comienza()) {
					Tablero.cpuJugada();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				botonNG.setForeground(Color.CYAN);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				botonNG.setForeground(Color.BLUE);
			}
		};
		
		return newGame;
	}
}
