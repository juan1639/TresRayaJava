package juan1639.packmain;

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

import juan1639.logicajuego.Tablero;

public class MenuPrincipal extends JFrame {
	
	private static boolean quien_comienza = true;
	private JPanel panel;
	private JLabel titulo;
	private JRadioButton radioBoton[] = new JRadioButton[2];
	private JButton botonJugar;
	
	public MenuPrincipal() {
		
		settingsJFrame();
		crearPanel();
		crearRadioBoton();
		crearEtiquetas();
		crearBotonJugar();
		botonJugar.addMouseListener(eventoBotonJugar());
	}

	public void settingsJFrame() {
		
		setSize(Settings.tileX * 3, Settings.tileY * 3);
		setTitle(" TRES en RAYA  Menu Principal");
		setLocationRelativeTo(null);
		setResizable(true);
		setMinimumSize(new Dimension(300, 300));
		
		//this.getContentPane().setBackground(Color.BLUE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void crearPanel() {
		
		panel = new JPanel();
		panel.setLayout(null);
		//panel.setBackground(Color.DARK_GRAY);
		this.getContentPane().add(panel);
	}
	
	public void crearRadioBoton() {
		
		int x = (int) ((Settings.tileX * 3) / 8);
		int y = (int) ((Settings.tileY * 3) / 3);
		int ancho = (int) ((Settings.tileX * 3) / 1.5);
		int alto = (int) ((Settings.tileY * 3) / 10);

		crearRadioBoton(" Comienza el Jugador (ficha X) ", true, x, y, ancho, alto, 0, Settings.azulX);
		y += (int) (alto * 1.5);
		crearRadioBoton(" Comienza la CPU  (ficha O) ", false, x, y, ancho, alto, 1, Settings.verdeO);
		
		ButtonGroup grupoRadioBotones = new ButtonGroup();
		grupoRadioBotones.add(radioBoton[0]);
		grupoRadioBotones.add(radioBoton[1]);
	}

	public void crearRadioBoton(String info, Boolean bool,
			int x, int y, int ancho, int alto, int index, Color col) {
		
		radioBoton[index] = new JRadioButton(info);
		radioBoton[index].setSelected(bool);
		radioBoton[index].setFont(new Font("arial", 0, 22));
		radioBoton[index].setForeground(col);
		radioBoton[index].setBounds(x, y, ancho, alto);
		panel.add(radioBoton[index]);
	}
	
	public void crearEtiquetas() {
		
		int x = (int) ((Settings.tileX * 3) / 10);
		
		titulo = new JLabel("Elige quién comienza");
		titulo.setBounds(x, (int) ((Settings.tileY * 3) / 18),
				(int) ((Settings.tileX * 3) / 1.3), (int) ((Settings.tileY * 3) / 7));
		
		titulo.setForeground(Color.GRAY);
		titulo.setFont(new Font("arial", 1, 30));
		panel.add(titulo);
	}
	
	public void crearBotonJugar() {
		
		botonJugar = new JButton();
		botonJugar.setFont(new Font("arial", Font.BOLD, 30));
		botonJugar.setText(" Jugar ");
		//botonNG.setSize((int) (goX / 1.3), (int) (goY / 8));
		botonJugar.setFocusable(true);
		//botonJugar.requestFocus();
		//botonNG.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1, true));
		botonJugar.setBounds((int) ((Settings.tileX * 3) / 3), (int) ((Settings.tileY * 3) / 1.3), 180, 50);
		//botonNG.setOpaque(true);
		botonJugar.setForeground(Color.BLUE);
		botonJugar.setBackground(Color.CYAN);
		//botonNG.setBackground(Color.RED);
		panel.add(botonJugar);
	}
	
	public MouseListener eventoBotonJugar() {
		
		MouseListener jugar = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
				dispose();
				
				if (radioBoton[0].isSelected()) {
					
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
				//botonJugar.setForeground(Color.GREEN);
				botonJugar.setForeground(Settings.amarillo);
				botonJugar.setBackground(Color.BLUE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				botonJugar.setForeground(Color.BLUE);
				botonJugar.setBackground(Color.CYAN);
			}
		};
		
		return jugar;
	}
}
