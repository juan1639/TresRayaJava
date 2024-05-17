package packMain;

public class CheckGanador {
	
	public static boolean check_empate() {
		
		for (CasillaXO casilla: Tablero.arrayCasillas) {
			
			if (casilla.getValor() == null) {
				return false;
			}
		}
		
		System.out.print("Empate \n");
		return true;
	}
	
	public static boolean check_tresRaya(String xo) {
		
		int[][] combiGanadoras = {
				{0, 1, 2},
				{3, 4, 5},
				{6, 7, 8},
				{0, 3, 6},
				{1, 4, 7},
				{2, 5, 8},
				{0, 4, 8},
				{2, 4, 6}
		};
		
		CasillaXO[] casilla = Tablero.arrayCasillas;
		
		for (int[] combi: combiGanadoras) {
			
			int uno = combi[0];
			int dos = combi[1];
			int tres = combi[2];
			
			if (casilla[uno].getValor() == xo && casilla[dos].getValor() == xo
					&& casilla[tres].getValor() == xo) {
				
				System.out.print(" Tres en Raya!! \n");
				return true;
			}
		}
		
		return false;
	}
}
