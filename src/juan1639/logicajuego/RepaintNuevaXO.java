package juan1639.logicajuego;

import javax.swing.JPanel;

import juan1639.entidades.CasillaXO;

public class RepaintNuevaXO {
	
	public static void clickNuevaXO(String fichaXO, JPanel panel, CasillaXO[] arrayCasillas,
			int indice, int fila, int columna) {
		
		panel.remove(arrayCasillas[indice].getCasillaBoton());
		
		arrayCasillas[indice] = new CasillaXO(fichaXO, indice, fila, columna);
		panel.add(arrayCasillas[indice].getCasillaBoton());
		
		panel.repaint();
	}
}
