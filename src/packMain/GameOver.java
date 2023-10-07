package packMain;

public class GameOver {
	
	public static void game_over(boolean empate, boolean tresRaya) {
		
		System.out.print(" Game Over \n");
		
		if (empate && tresRaya) {
			empate = false;
		}
		
		System.out.print(" Empate: " + empate + " TresRaya: " + tresRaya);
	}

}
