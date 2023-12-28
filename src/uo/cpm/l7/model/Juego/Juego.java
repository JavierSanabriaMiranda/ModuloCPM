package uo.cpm.l7.model.Juego;

public class Juego {
	
	/**
	 * Número de filas del tablero
	 */
	private static final int FILAS = 5;
	
	/**
	 * Número de columnas del tablero
	 */
	private static final int COLUMNAS = 7;
	
	/**
	 * Número de lanzamientos máximo que se puede hacer al dado
	 */
	private static final int NUMERO_LANZAMIENTOS = 7;
	
	/**
	 * Código del descuento del 25%
	 */
	public static final String DESCUENTO_25 = "EXTRA25";
	
	/**
	 * Código del descuento del 10%
	 */
	public static final String DESCUENTO_10 = "EXTRA10";
	
	/**
	 * Número de lanzamientos que le quedan al jugador
	 */
	private int numLanzamientos = NUMERO_LANZAMIENTOS;
	
	/**
	 * Tablero del juego sobre el que se realizan los movimientos
	 */
	private Tablero tablero;
	
	/**
	 * Dado del juego con el cual se obtendrán valores entre 0 y 1
	 */
	private Dado dado;
	
	/**
	 * Atributo que marca si el juego ha finalizado o no. Si está a true el juego ha finalizado
	 */
	private boolean finalizado;
	
	/**
	 * Guarda el premio en caso de que el jugador halla ganado alguno al finalizar la partida, en caso contrario
	 * es null
	 */
	private String premio;
	
	/**
	 * Constructor por defecto de la clase Juego, recibe como parámetro el tamaño del tablero de juego y el número
	 * de lanzamientos al dado que tendrá el jugador
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
		this.finalizado = false;
		this.premio = null;
}
	
	/**
	 * Lanza una vez el dado, reduce por tanto el número de lanzamientos restantes
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
	 * Se mueve el cazafantasmas de la posición seleccionada en vertical un número de posiciones igual al valor obtenido por el dado
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
	 * Comprueba si la partida ha finalizado, es decir, si no hay más disparos restantes o si se ha eliminado a un 
	 * fantasma de cada tipo así como al fantasma lider aún habiendo disparos restantes
	 */
	public void comprobarFinalDeJuego() {
		if (numLanzamientos == 0 || tablero.juegoTerminaConPremio())
			this.finalizado = true;
		this.premio = tablero.comprobarPremio();
	}
	
	/**
	 * @return Número de lanzamientos restantes del dado
	 */
	public int getNumeroLanzamientos() {
		return this.numLanzamientos;
	}
	
	/**
	 * @return true si la partida ha finalizado, false en caso contrario
	 */
	public boolean isFinalizado() {
		return this.finalizado;
	}

}
