package uo.cpm.l7.model.Juego;

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
	 * Constructor por defecto de la clase Juego, recibe como par�metro el tama�o del tablero de juego y el n�mero
	 * de lanzamientos al dado que tendr� el jugador
	 * 
	 * @param tam Tama�o del tablero de juego
	 * @param numLanzamientos N�mero de lanzamientos que tendr� el jugador
	 */
	public Juego() {
		this.dado = new Dado();
		this.tablero = new Tablero(FILAS, COLUMNAS);
	}
	
	/**
	 * Inicializa el juego creando una nueva partida desde cero
	 */
	public void inicializarJuego() {
		this.dado = new Dado();
		this.tablero.vaciarTablero();
		this.tablero.inicializarTablero();
	}
	
	/**
	 * Lanza una vez el dado, reduce por tanto el n�mero de lanzamientos restantes
	 */
	public void lanzarDado() {
		if (numLanzamientos > 0) {
			dado.lanzar();
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
	 * Comprueba si la partida ha finalizado (si no hay m�s disparos restantes o se han eliminado a un fantasma de cada tipo
	 * as� como al fantasma lider
	 */
	public void comprobarFinalDeJuego() {
		if (numLanzamientos == 0)
			finalizarJuego();
	}
	
	public int getNumeroLanzamientos() {
		return this.numLanzamientos;
	}
	
	private void finalizarJuego() {
		//TODO 
	}
}
