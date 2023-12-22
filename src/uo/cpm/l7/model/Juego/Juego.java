package uo.cpm.l7.model.Juego;

public class Juego {
	
	/**
	 * Número de lanzamientos que le quedan al jugador
	 */
	private int numLanzamientos;
	
	/**
	 * Tablero del juego sobre el que se realizan los movimientos
	 */
	private Tablero tablero;
	
	/**
	 * Dado del juego con el cual se obtendrán valores entre 0 y 1
	 */
	private Dado dado = new Dado();
	
	/**
	 * Constructor por defecto de la clase Juego, recibe como parámetro el tamaño del tablero de juego y el número
	 * de lanzamientos al dado que tendrá el jugador
	 * 
	 * @param tam Tamaño del tablero de juego
	 * @param numLanzamientos Número de lanzamientos que tendrá el jugador
	 */
	public Juego(int tam, int numLanzamientos) {
		this.tablero = new Tablero(tam);
		this.numLanzamientos = numLanzamientos;
	}
	
	/**
	 * Lanza una vez el dado y retorna el resultado obtenido, reduce por tanto el número de lanzamientos restantes
	 * 
	 * @return resultado obtenido por el dado (valor entre 0 y 1)
	 */
	public int lanzarDado() {
		dado.lanzar();
		numLanzamientos--;
		return dado.getValor();
	}
	
	/**
	 * @return valor obtenido anteriormente por el dado
	 */
	public int getValor() {
		return dado.getValor();
	}

	/**
	 * Descubre la casilla cuya posición ha sido seleccionada y retorna true en caso de que se haya podido descubrir,
	 * false en otro caso
	 * 
	 * @param pos a descubrir
	 * @param valor obtenido por el dado
	 * 
	 * @return true si descubre la casilla, false si no
	 */
	public boolean descubrirCasilla(int posADescubrir,int posInicial , int valor) {
		//TODO Comprobar como hacer esta mecanica
		return false;
	}
	
}
