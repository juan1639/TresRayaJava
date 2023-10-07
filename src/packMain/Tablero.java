package packMain;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// ----------------------------------------------------------------------
public class Tablero extends JFrame {
	
	public static final double resX = 540;
	public static final double resY = 540;
	
	public static final double anchoCasillaBoton = resX / 3.1;
	public static final double altoCasillaBoton = resY / 3.2;
	
	public static final int nro_casillas9 = 9;
	public static final int filas = 3;
	public static final int columnas = 3;
	
	public static final String fichaX = "x";
	public static final String fichaO = "o";
	
	public static CasillaXO arrayCasillas[] = new CasillaXO[nro_casillas9];
	private static JPanel panel;
	private static boolean enJuego = true;
	private static boolean turno = true;
	
	// ------------------------------------------------------------------
	public Tablero() {
		
		setSize((int) resX, (int) resY);
		setTitle(" TRES en RAYA  By Juan Eguia ");
		setLocationRelativeTo(null);
		setResizable(true);
		setMinimumSize(new Dimension(300, 300));
		
		//this.getContentPane().setBackground(Color.BLUE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// ------------------------------------------------
		iniciarComponentes();
	}
	
	public void iniciarComponentes() {
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.DARK_GRAY);
		this.getContentPane().add(panel);
		
		int i = 0;
		
		for (int fila = 0; fila < filas; fila ++) {
			for (int col = 0; col < columnas; col ++) {
				
				CasillaXO casilla = new CasillaXO(i, fila, col);
				arrayCasillas[i] = casilla;
				JButton casillaBoton = casilla.getCasillaBoton();
				panel.add(casillaBoton);
				i ++;
			}
		}
	}
	
	public static void realizarJugada(int indice, int fila, int columna) {
		
		if (!turno || !enJuego) return;
		
		panel.remove(arrayCasillas[indice].getCasillaBoton());
		
		arrayCasillas[indice] = new CasillaXO(fichaX, indice, fila, columna);
		panel.add(arrayCasillas[indice].getCasillaBoton());
		
		panel.repaint();
		
		System.out.print(indice + " jugada realizada... \n");
		
		boolean empate = CheckGanador.check_empate();
		boolean tresRaya = CheckGanador.check_tresRaya(fichaX);
		
		if (!empate && !tresRaya) {
			turno = false;
			cpuJugada();
			
		} else {
			enJuego = false;
			GameOver.game_over(empate, tresRaya);
		}
	}
	
	public static void cpuJugada() {
		
		if (!enJuego) return;
		
		int numero_rnd;
		
		do {
			numero_rnd = (int) (Math.random() * 9);
			
		} while (arrayCasillas[numero_rnd].getValor() != null);
		
		CasillaXO tiradaCpu = arrayCasillas[numero_rnd];
		panel.remove(tiradaCpu.getCasillaBoton());
		
		arrayCasillas[numero_rnd] = new CasillaXO(fichaO, numero_rnd, getFila(numero_rnd), getColumna(numero_rnd));
		
		panel.add(arrayCasillas[numero_rnd].getCasillaBoton());
		panel.repaint();
		
		System.out.print(numero_rnd + " jugada realizada... \n");
		
		boolean empate = CheckGanador.check_empate();
		boolean tresRaya = CheckGanador.check_tresRaya(fichaO);
		
		if (!empate && !tresRaya) {
			turno = true;
			
		} else {
			enJuego = false;
			GameOver.game_over(empate, tresRaya);
		}
	}
	
	public static int getFila(int indice) {
		
		int fila = (int) (indice / filas);
		return fila;
	}
	
	public static int getColumna(int indice) {
		
		int columna = (int) (indice / filas);
		columna = indice - (columna * columnas);
		return columna;
	}
}
