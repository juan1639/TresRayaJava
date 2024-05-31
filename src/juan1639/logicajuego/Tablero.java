package juan1639.logicajuego;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import juan1639.checkwinner.CheckGanador;
import juan1639.entidades.CasillaXO;
import juan1639.packmain.GameOver;
import juan1639.packmain.Settings;

public class Tablero extends JFrame {
	
	public static final double RES_X = Settings.tileX * 3.07;
	public static final double RES_Y = Settings.tileY * 3.2;
	
	public static final double ANCHO_CASILLA_BOTON = Settings.tileX;
	public static final double ALTO_CASILLA_BOTON = Settings.tileY;
	
	public static final int NUM_CASILLAS_9 = 9;
	public static final int FILAS = Settings.filas;
	public static final int COLUMNAS = Settings.columnas;
	
	public static final String FICHAX = Settings.FICHA_X;
	public static final String FICHAO = Settings.FICHA_O;
	
	public static CasillaXO arrayCasillas[] = new CasillaXO[NUM_CASILLAS_9];
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
		
		setSize((int) RES_X, (int) RES_Y);
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
		// Dibujar el Tablero de Juego / Draw GameBoard
		int i = 0;
		
		for (int fila = 0; fila < FILAS; fila ++) {
			for (int col = 0; col < COLUMNAS; col ++) {
				
				CasillaXO casilla = new CasillaXO(i, fila, col);
				arrayCasillas[i] = casilla;
				JButton casillaBoton = casilla.getCasillaBoton();
				panel.add(casillaBoton);
				i ++;
			}
		}
	}
	
	public static void realizarJugada(int indice, int fila, int columna) {
		
		// Si No es Nuestro Turno o Casilla No vacia... return
		if (!turno || !enJuego) return;
		if (arrayCasillas[indice].getValor() != null) return;
		
		// Repintar Casilla y poner nuestra X
		RepaintNuevaXO.clickNuevaXO(FICHAX, panel, arrayCasillas, indice, fila, columna);
		
		System.out.print(indice + " jugada realizada... \n");
		
		// Tras realizar jugada... inmediatamente se Checkea un posible Ganador o Empate
		boolean empate = CheckGanador.check_empate();
		boolean tresRaya = CheckGanador.check_tresRaya(FICHAX);
		
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
		
		for (i = 0; i < NUM_CASILLAS_9; i ++) {
			
			if (arrayCasillas[i].getValor() == null) {
				
				arrayCasillas[i].setValor(FICHAO);
				
				posiblidadGanar = CheckGanador.check_tresRaya(FICHAO);
				
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
			
			for (i = 0; i < NUM_CASILLAS_9; i ++) {
				
				if (arrayCasillas[i].getValor() == null) {
					
					arrayCasillas[i].setValor(FICHAX);
					
					defender = CheckGanador.check_tresRaya(FICHAX);
					
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
		// 	LLegados hasta aqui, ya tenemos RESUELTO "numero_rnd"
		// ---------------------------------------------------------------
		CasillaXO tiradaCpu = arrayCasillas[numero_rnd];
		
		// Repintar Casilla y poner la CPU (ficha O)
		RepaintNuevaXO.clickNuevaXO(FICHAO, panel, arrayCasillas, numero_rnd,
				getFila(numero_rnd), getColumna(numero_rnd));
		
		System.out.print(numero_rnd + " jugada realizada... \n");
		
		boolean empate = CheckGanador.check_empate();
		boolean tresRaya = CheckGanador.check_tresRaya(FICHAO);
		
		if (!empate && !tresRaya) {
			turno = true;
			
		} else {
			enJuego = false;
			GameOver gameover = new GameOver(empate, tresRaya, false);
			gameover.setVisible(true);
			//GameOver.game_over(empate, tresRaya);
		}
	}
	
	// Getters & Setters
	public static int getFila(int indice) {
		
		int fila = (int) (indice / FILAS);
		return fila;
	}
	
	public static int getColumna(int indice) {
		
		int columna = (int) (indice / FILAS);
		columna = indice - (columna * COLUMNAS);
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
