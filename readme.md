# Sencillo proyecto del juego TRES EN RAYA (TicTacToe) en Java

<img src="snapShot-tresRayaJava.png" alt="img game"/>

# Estructura del proyecto:

<ul>
	<li>packmain -> Package principal del proyecto</li>
	<ul>
		<li>Principal.java (método main)</li>
		<li>MenuPrincipal.java (swing con el menu principal)</li>
		<li>GameOver.java (swing con el game over)</li>
		<li>Settings.java (clase con las principales variables y constantes)</li>
	</ul>
	<li>checkwinner -> Package con la clase donde de checkea un posible ganador</li>
	<ul>
		<li>CheckGanador.java</li>
	</ul>
	<li>entidades -> Package con la clase donde se crea la Casilla X/O, incluido el JButton y eventos raton</li>
	<ul>
		<li>CasillaXO.java</li>
	</ul>
	<li>logicajuego -> Package con las clases Tablero donde se gestionan las jugadas (jugador/CPU)</li>
	<ul>
		<li>Tablero.java</li>
		<li>RepaintNuevaXO.java</li>
	</ul>
</ul>
