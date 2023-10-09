package packMain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

// -------------------------------------------------------------------
public class MenuPrincipal extends JFrame {
	
	private static final double resX = 540;
	private static final double resY = 540;
	
	private boolean quien_comienza = true;
	private JPanel panel;
	private JLabel titulo;
	private JRadioButton radioBoton1;
	private JRadioButton radioBoton2;
	private JButton botonJugar;
	
	public MenuPrincipal() {
		
		setSize((int) resX, (int) resY);
		setTitle(" TRES en RAYA  Menu Principal");
		setLocationRelativeTo(null);
		setResizable(true);
		setMinimumSize(new Dimension(300, 300));
		
		//this.getContentPane().setBackground(Color.BLUE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// ------------------------------------------------
		crearPanel();
		crearRadioBoton();
		crearEtiquetas();
		crearBotonJugar();
		botonJugar.addMouseListener(eventoBotonJugar());
	}
	
	public void crearPanel() {
		
		panel = new JPanel();
		panel.setLayout(null);
		//panel.setBackground(Color.DARK_GRAY);
		this.getContentPane().add(panel);
	}
	
	public void crearRadioBoton() {
		
		int x = (int) (resX / 8);
		int y = (int) (resY / 3);
		int ancho = (int) (resX / 1.5);
		int alto = (int) (resY / 10);
		
		radioBoton1 = new JRadioButton(" Comienza el Jugador ... X ");
		radioBoton1.setSelected(true);
		radioBoton1.setFont(new Font("arial", 0, 25));
		radioBoton1.setForeground(Color.GRAY);
		radioBoton1.setBounds(x, y, ancho, alto);
		panel.add(radioBoton1);	
		
		y += (int) (alto * 1.5);
		
		radioBoton2 = new JRadioButton(" Comienza la CPU ........ O ");
		radioBoton2.setSelected(false);
		radioBoton2.setFont(new Font("arial", 0, 25));
		radioBoton2.setForeground(Color.GRAY);
		radioBoton2.setBounds(x, y, ancho, alto);
		panel.add(radioBoton2);
		
		ButtonGroup grupoRadioBotones = new ButtonGroup();
		grupoRadioBotones.add(radioBoton1);
		grupoRadioBotones.add(radioBoton2);
	}
	
	public void crearEtiquetas() {
		
		int x = (int) (resX / 10);
		
		titulo = new JLabel(" Elige quién comienza");
		titulo.setBounds(x, (int) (resY / 18), (int) (resX / 1.3), (int) (resY / 7));
		titulo.setForeground(Color.BLUE);
		titulo.setFont(new Font("arial", 1, 40));
		panel.add(titulo);
	}
	
	public void crearBotonJugar() {
		
		botonJugar = new JButton();
		botonJugar.setFont(new Font("arial", Font.BOLD, 30));
		botonJugar.setText(" Jugar ");
		//botonNG.setSize((int) (goX / 1.3), (int) (goY / 8));
		botonJugar.setFocusable(false);
		//botonNG.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1, true));
		botonJugar.setBounds((int) (resX / 3), (int) (resY / 1.3), 180, 50);
		//botonNG.setOpaque(true);
		botonJugar.setForeground(Color.BLUE);
		//botonNG.setBackground(Color.RED);
		panel.add(botonJugar);
	}
	
	public MouseListener eventoBotonJugar() {
		
		MouseListener jugar = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
				dispose();
				
				if (radioBoton1.isSelected()) {
					quien_comienza = true;
				} else {
					quien_comienza = false;
				}
				
				Tablero tablero = new Tablero(quien_comienza, quien_comienza);
				tablero.setVisible(true);
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
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		
		return jugar;
	}
}
