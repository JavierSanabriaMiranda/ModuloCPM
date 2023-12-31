package uo.cpm.l7.model.Juego;

import java.util.List;

public class Juego {
	
	/**
	 * N�mero de filas del tablero
	 */
	private static final int FILAS = 5;
	
	/**
	 * N�mero de columnas del tablero
	 */
	private static final int COLUMNAS = 7;
	
	/**
	 * N�mero de lanzamientos m�ximo que se puede hacer al dado
	 */
	private static final int NUMERO_LANZAMIENTOS = 7;
	
	/**
	 * C�digo del descuento del 25%
	 */
	public static final String DESCUENTO_25 = "EXTRA25";
	
	/**
	 * C�digo del descuento del 10%
	 */
	public static final String DESCUENTO_10 = "EXTRA10";
	
	/**
	 * N�mero de lanzamientos que le quedan al jugador
	 */
	private int numLanzamientos = NUMERO_LANZAMIENTOS;
	
	/**
	 * Tablero del juego sobre el que se realizan los movimientos
	 */
	private Tablero tablero;
	
	/**
	 * Dado del juego con el cual se obtendr�n valores entre 0 y 1
	 */
	private Dado dado;
	
	/**
	 * Atributo que marca si el juego ha finalizado o no. Si est� a true el juego ha finalizado
	 */
	private boolean finalizado;
	
	/**
	 * Guarda el premio en caso de que el jugador halla ganado alguno al finalizar la partida, en caso contrario
	 * es null
	 */
	private String premio;
	
	/**
	 * Constructor por defecto de la clase Juego, recibe como par�metro el tama�o del tablero de juego y el n�mero
	 * de lanzamientos al dado que tendr� el jugador
	 */
	public Juego() {
		this.dado = new Dado();
		this.tablero = new Tablero(FILAS, COLUMNAS);
		this.finalizado = false;
	}
	
	/**
	 * Inicializa el juego creando una nueva partida desde cero
	 */
	public void inicializarJuego() {
		this.dado = new Dado();
		this.tablero.vaciarTablero();
		this.tablero.inicializarTablero();
		this.numLanzamientos = NUMERO_LANZAMIENTOS;
		this.finalizado = false;
		this.premio = null;
}
	
	/**
	 * Lanza una vez el dado, reduce por tanto el n�mero de lanzamientos restantes
	 */
	public void lanzarDado() {
		if (numLanzamientos > 0) {
			// Si algun cazafantasmas se puede mover 2 casillas en vertical se randomiza el valor
			if (comprobarMovimientoDe2Casillas())
				dado.lanzar();
			// Si ningun cazafantasmas se puede mover 2 casillas en vertical se fija el valor del dado a 1
			else 
				dado.setValor(1);
			numLanzamientos--;
		}
	}
	
	/**
	 * @return valor obtenido anteriormente por el dado
	 */
	public int getValor() {
		return dado.getValor();
	}

	/**
	 * Se mueve el cazafantasmas de la posici�n seleccionada en vertical un n�mero de posiciones igual al valor obtenido por el dado
	 * 
	 * @param filaInicial desde la que se realiza el movimiento
	 * @param columnaInicial desde la que se realiza el movimiento
	 * 
	 * @return true si se puede mover a la casilla, false si no
	 */
	public boolean moverACasilla(int filaInicial, int columnaInicial) {
		return tablero.moverACasilla(filaInicial, columnaInicial, dado.getValor());
	}
	
	/**
	 * @return tablero asociado al juego actual
	 */
	public Tablero getTablero() {
		return this.tablero;
	}
	
	/**
	 * @return N�mero de lanzamientos restantes del dado
	 */
	public int getNumeroLanzamientos() {
		return this.numLanzamientos;
	}
	
	/**
	 * Comprueba si la partida ha finalizado, es decir, si no hay m�s disparos restantes o si se ha eliminado a un 
	 * fantasma de cada tipo as� como al fantasma lider a�n habiendo disparos restantes
	 */
	private void comprobarFinalDeJuego() {
		if (numLanzamientos == 0 || tablero.juegoTerminaConPremio())
			this.finalizado = true;
		this.premio = tablero.comprobarPremio();
	}
	
	/**
	 * Comprueba si al menos un cazafantasmas se puede desplazar en vertical 2 posiciones, en caso de que ninguno pueda, se fuerza 
	 * que el valor del dado sea 1 para permitir seguir el juego
	 * 
	 * @return true si alguno de los cazafantasmas se puede mover 2 casillas, false en caso contrario
	 */
	private boolean comprobarMovimientoDe2Casillas() {
		return tablero.comprobarMovimientoDe2Casillas();
	}
	
	/**
	 * @return true si la partida ha finalizado, false en caso contrario
	 */
	public boolean isFinalizado() {
		comprobarFinalDeJuego();
		return this.finalizado;
	}
	
	public String getPremio() {
		return this.premio;
	}
	
	public List<Personaje> getEnemigosEliminados() {
		return tablero.getEnemigosEliminados();
	}

}
