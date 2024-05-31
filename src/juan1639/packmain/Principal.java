package juan1639.packmain;

import java.awt.EventQueue;

public class Principal {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				MenuPrincipal menuP = new MenuPrincipal();
				menuP.setVisible(true);
			}
		});
	}
}
