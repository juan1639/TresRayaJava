package packMain;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tablero extends JFrame {
	
	public static final double resX = Settings.tileX * 3.07;
	public static final double resY = Settings.tileY * 3.2;
	
	public static final double anchoCasillaBoton = Settings.tileX;
	public static final double altoCasillaBoton = Settings.tileY;
	
	public static final int nro_casillas9 = 9;
	public static final int filas = Settings.filas;
	public static final int columnas = Settings.columnas;
	
	public static final String fichaX = Settings.fichaX;
	public static final String fichaO = Settings.fichaO;
	
	public static CasillaXO arrayCasillas[] = new CasillaXO[nro_casillas9];
	public static JPanel panel;
	
	private static boolean save_quien_comienza;
	private static boolean enJuego = true;
	private static boolean turno;
	
	public Tablero(boolean _turno, boolean _quien_comienza) {
		
		turno = _turno;
		save_quien_comienza = _quien_comienza;
		
		settingsJFrame();
		crearPanel();
		iniciarComponentes();
		
		if (!turno) {
			cpuJugada();
		}
	}

	public void settingsJFrame() {
		
		setSize((int) resX, (int) resY);
		setTitle(" TRES en RAYA  By Juan Eguia ");
		setLocationRelativeTo(null);
		setResizable(true);
		setMinimumSize(new Dimension(300, 300));
		
		//this.getContentPane().setBackground(Color.BLUE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void crearPanel() {
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.DARK_GRAY);
		this.getContentPane().add(panel);
	}
	
	public static void iniciarComponentes() {
		
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
		
		if (arrayCasillas[indice].getValor() != null) return;
		
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
			GameOver gameover = new GameOver(empate, tresRaya, true);
			//GameOver.game_over(empate, tresRaya);
		}
	}
	
	public static void cpuJugada() {
		
		if (!enJuego) return;
		
		// CPU checkea SI tiene la posibilidad de Ganar directo ----------
		boolean posiblidadGanar = false;
		int i;
		
		for (i = 0; i < nro_casillas9; i ++) {
			
			if (arrayCasillas[i].getValor() == null) {
				
				arrayCasillas[i].setValor(fichaO);
				
				posiblidadGanar = CheckGanador.check_tresRaya(fichaO);
				
				if (posiblidadGanar) {
					break;
				}
				
				arrayCasillas[i].setValor(null);
			}
		}
		
		// Asignamos el indice como TIRADA (si posibilidadGanar = True)---
		int numero_rnd = i;
		
		// SI NO hay posibilidadGanar directo, checkeamos SI defender ----
		boolean defender = false;
		
		if (!posiblidadGanar) {
			
			for (i = 0; i < nro_casillas9; i ++) {
				
				if (arrayCasillas[i].getValor() == null) {
					
					arrayCasillas[i].setValor(fichaX);
					
					defender = CheckGanador.check_tresRaya(fichaX);
					
					if (defender) {
						arrayCasillas[i].setValor(null);
						break;
					}
					
					arrayCasillas[i].setValor(null);
				}
			}
			
			numero_rnd = i;
			
			// Finalmente, SI no hay que defender, se tira Aleatorio -----
			if (!defender) {
				
				do {
					numero_rnd = (int) (Math.random() * Settings.nro_casillas9);
					
				} while (arrayCasillas[numero_rnd].getValor() != null);
			}
		}
		
		// ---------------------------------------------------------------
		// LLegados hasta aqui, ya tenemos RESUELTO "numero_rnd"
		// ---------------------------------------------------------------
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
			GameOver gameover = new GameOver(empate, tresRaya, false);
			//GameOver.game_over(empate, tresRaya);
		}
	}
	
	// Getters & Setters
	public static int getFila(int indice) {
		
		int fila = (int) (indice / filas);
		return fila;
	}
	
	public static int getColumna(int indice) {
		
		int columna = (int) (indice / filas);
		columna = indice - (columna * columnas);
		return columna;
	}
	
	public static boolean isEnJuego() {
		return enJuego;
	}

	public static void setEnJuego(boolean enJuego) {
		Tablero.enJuego = enJuego;
	}

	public static boolean isTurno() {
		return turno;
	}

	public static void setTurno(boolean turno) {
		Tablero.turno = turno;
	}

	public static boolean isSave_quien_comienza() {
		return save_quien_comienza;
	}

	public static void setSave_quien_comienza(boolean save_quien_comienza) {
		Tablero.save_quien_comienza = save_quien_comienza;
	}
}
