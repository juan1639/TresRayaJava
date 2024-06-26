package juan1639.entidades;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import juan1639.logicajuego.Tablero;
import juan1639.packmain.Settings;

public class CasillaXO {
	
	private String valor;
	private int indice;
	private int fila;
	private int columna;
	private JButton casillaBoton;
	
	public CasillaXO(String valor, int indice, int fila, int columna) {
		this.valor = valor;
		this.indice = indice;
		this.fila = fila;
		this.columna = columna;
		
		this.casillaBoton = crearCasillaBoton();
		this.casillaBoton.addMouseListener(eventoRaton());
	}
	
	public CasillaXO(int indice, int fila, int columna) {
		this.valor = null;
		this.indice = indice;
		this.fila = fila;
		this.columna = columna;
		
		this.casillaBoton = crearCasillaBoton();
		this.casillaBoton.addMouseListener(eventoRaton());
	}
	
	// Cada casilla es un JButton para que sea "clickable"
	public JButton crearCasillaBoton() {
		
		int ancho = (int) Tablero.ANCHO_CASILLA_BOTON;
		int alto = (int) Tablero.ALTO_CASILLA_BOTON;
		int fontSize = (int) (alto / 1.1);
		//Color colorFicha = valor == Tablero.fichaX ? new Color(5, 80, 220) : new Color(25, 190, 5);
		Color colorFicha = valor == Tablero.FICHAX ? Settings.azulX : Settings.verdeO;
		
		casillaBoton = new JButton();
		//casillaBoton.setOpaque(true);
		//casillaBoton.setText(String.valueOf(this.indice));
		casillaBoton.setText(this.valor);
		casillaBoton.setFont(new Font("arial", Font.BOLD, fontSize));
		casillaBoton.setEnabled(true);
		casillaBoton.setFocusable(false);
		casillaBoton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1, true));
		casillaBoton.setBounds(this.columna * ancho, this.fila * alto, ancho, alto);
		casillaBoton.setForeground(colorFicha);
		//casillaBoton.setBackground(Color.BLUE);
		//panel.add(etiqueta);
		
		return casillaBoton;
	}
	
	public MouseListener eventoRaton() {
		
		MouseListener oyenteRaton = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
				
				Tablero.realizarJugada(indice, fila, columna);
			}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
		};
		
		return oyenteRaton;
	}
	
	// Getters & Setters
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public JButton getCasillaBoton() {
		return casillaBoton;
	}

	public void setCasillaBoton(JButton casillaBoton) {
		this.casillaBoton = casillaBoton;
	}
}
