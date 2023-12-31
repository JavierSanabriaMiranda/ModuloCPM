package uo.cpm.l7.model.Juego;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tablero {

	/**
	 * Número de fantasmas por cuadrilla, es decir, número de fantasmas no lider que
	 * hay de cada tipo repartidos por el tablero
	 */
	private static final int NUM_FANTASMAS_POR_CUADRILLA = 3;

	/**
	 * Tablero sobre el que se realizan los movimientos y cuyos valores son los
	 * fantasmas o los cazafantasmas
	 */
	private Casilla[][] tablero;

	/**
	 * Lista de las casillas hacia las que se ha movido algun cazafantasmas
	 */
	private List<Personaje> enemigosEliminados;

	/**
	 * Número de filas del tablero
	 */
	private int filas;

	/**
	 * Número de columnas del tablero
	 */
	private int columnas;

	/**
	 * Constructor por defecto de la clase Tablero, recibe el tamaño del tablero a
	 * crear
	 * 
	 * @param tam tamaño del tablero a crear
	 */
	public Tablero(int filas, int columnas) {
		this.filas = filas;
		this.columnas = columnas;
		this.tablero = new Casilla[filas][columnas];
		this.enemigosEliminados = new ArrayList<>();
		inicializarTablero();
	}

	public void vaciarTablero() {
		for (int i = 0; i < filas; i++)
			for (int j = 0; j < columnas; j++) {
				tablero[i][j] = null;
			}
	}
	
	
	/**
	 * Reparte los distintos personajes del juego en sus posiciones
	 * correspondientes, es decir, el fantasma lider en la primera posición del
	 * tablero (parte alta del tablero), los cazafantasmas en posiciones de la 15 a
	 * la 21 (parte baja del tablero) y el resto de los fantasmas se reparten por el
	 * resto del tablero de forma aleatoria
	 */
	public void inicializarTablero() {
		enemigosEliminados.clear();
		// Se colocan en los bordes del tablero (Haciendo una escalera por ambos lados
		// desde la primera fila)
		// Casillas del tipo BORDE
		colocarBordes();

		// Se ubica el resto de fantasmas de forma aleatoria
		distribuirFantasmas(Personaje.FANTASMA_BASICO);
		distribuirFantasmas(Personaje.FANTASMA_CALABAZA);
		distribuirFantasmas(Personaje.FANTASMA_MOMIA);
		distribuirFantasmas(Personaje.FANTASMA_ZOMBIE);
		distribuirFantasmas(Personaje.FANTASMA_PIRATA);

		// Se setablece a los cazafantasmas en la fila de abajo del tablero
		for (int i = 0; i < tablero[filas - 1].length; i++)
			tablero[filas - 1][i] = new Casilla(Personaje.CAZAFANTASMAS);
		
		// Se ubica al fantasma lider en lo alto del tablero
		tablero[0][columnas/2] = new Casilla(Personaje.FANTASMA_LIDER);
	}

	/**
	 * Asigna las casillas BORDE del tablero, que se ubican haciendo una escalera
	 * por izquierda y derecha desde la fila 0, dejando en el centro de esta un
	 * hueco para el fantasma lider.
	 */
	private void colocarBordes() {
		for (int i = 0; i < filas-1; i++) {
			for (int j = 0; j < (columnas/2)-i; j++) {
				tablero[i][j] = new Casilla(Personaje.BORDE);
				tablero[i][columnas - 1 - j] = new Casilla(Personaje.BORDE);
			}
		}
	}

	/**
	 * Distribuye en casillas aleatorias de la 1 a la 15, 3 fantasmas del tipo
	 * introducido como parámetro
	 * 
	 * @param tipoFantasma
	 */
	private void distribuirFantasmas(Personaje tipoFantasma) {
		int numFantasmas = NUM_FANTASMAS_POR_CUADRILLA;
		Random random = new Random();
		int fila;
		int columna;

		while (numFantasmas != 0) {
			fila = random.nextInt(1, filas - 1);
			columna = random.nextInt(0, columnas);
			// Si ha encontrado una casilla vacía se establece el fantasma y se reduce el
			// numFantasmas
			if (tablero[fila][columna] == null) {
				tablero[fila][columna] = new Casilla(tipoFantasma);
				numFantasmas--;
			}
		}
	}

	/**
	 * @return tablero actual, que es un array de casillas
	 */
	public Casilla[][] getTablero() {
		return tablero;
	}

	/**
	 * @return casillas sobre las que ya se ha realizado un movimiento de un
	 *         cazafantasmas, guarda por tanto los fantasmas que han sido eliminados
	 *         en la partida
	 */
	public List<Personaje> getEnemigosEliminados() {
		return enemigosEliminados;
	}

	/**
	 * @return número de filas del tablero
	 */
	public int getFilas() {
		return filas;
	}

	/**
	 * @return número de columnas del tablero
	 */
	public int getColumnas() {
		return columnas;
	}

	/**
	 * Mueve al cazafantasmas ubicado en la posición (filaInicial, columnaInicial)
	 * hacia arriba un número de posiciones equivalente al valor que se introduce
	 * como parámetro eliminando así al fantasma ubicado en dicha posición destino.
	 * Se añade por tanto dicho fantasma a la lista de enemigos eliminados.
	 * 
	 * Si la casilla sobre la que se intenta realizar el movimiento no es un
	 * cazafantasmas o la posición destino es una posición fuera del tablero o un
	 * borde, el método no hace nada y retorna false. En cualquier otro caso, se
	 * realiza el movimiento con normalidad y retorna true
	 * 
	 * @param filaInicial    en la que se ubica el cazafantasmas
	 * @param columnaInicial en la que se ubica el cazafantasmas
	 * @param valor          número de posiciones que se mueve en vertical el
	 *                       cazafantasmas
	 * @return true si se realiza movimiento, false en caso contrario (movimiento
	 *         inválido)
	 */
	public boolean moverACasilla(int filaInicial, int columnaInicial, int valor) {
		// Si la casilla sobre la que se pretende iniciar el movimiento no es un
		// cazafantasmas se retorna false
		if (!(tablero[filaInicial][columnaInicial].getValor().equals(Personaje.CAZAFANTASMAS)))
			return false;
		// Si el movimiento se sale del tablero o se encuentra con un borde, no se
		// permite el movimiento y se retorna false
		if (filaInicial - valor < 0 || tablero[filaInicial - valor][columnaInicial].getValor().equals(Personaje.BORDE))
			return false;
		
		// Cuando el movimiento se realiza hacia un fantasma cualquiera y es válido se
		// mueve el cazafantasmas y se borra al fantasma.	Antes de borrarse, se añade a la lista de enemigos eliminados
		enemigosEliminados.add(tablero[filaInicial - valor][columnaInicial].getValor());
		tablero[filaInicial - valor][columnaInicial].setValor(Personaje.CAZAFANTASMAS);
		tablero[filaInicial][columnaInicial].setValor(Personaje.VACIO);
		return true;
	}
	
	/**
	 * Comprueba si se ha eliminado a un enemigo de cada tipo y además si se ha eliminado al fantasma lider. En función de eso da una 
	 * recompensa u otra. Si no se ha obtenido premio, devuelve null
	 *  
	 * @return código del premio obtenido o null si no se ha obtenido ningun premio
	 */
	public String comprobarPremio() {
		if (hayPremio())
			if (enemigosEliminados.contains(Personaje.FANTASMA_LIDER))
				return Juego.DESCUENTO_25;
			else
				return Juego.DESCUENTO_10;
		else
			return null;		
	}
	
	/**
	 * Comprueba si la partida ha terminado por completo con el máximo premio. Su finalidad es comprobar si hay que terminar
	 * la partida aun cuando quedan todavía lanzamientos al dado
	 * 
	 * @return true si se ha completado el objetivo del juego (eliminar a un fantasma de cada tipo más el fantasma lider)
	 */
	public boolean juegoTerminaConPremio() {
		return comprobarPremio().equals(Juego.DESCUENTO_25);
	}
	
	/**
	 * @return true si se ha eliminado a un fantasma de cada tipo
	 */
	private boolean hayPremio() {
		return enemigosEliminados.contains(Personaje.FANTASMA_BASICO) && enemigosEliminados.contains(Personaje.FANTASMA_CALABAZA) 
				&& enemigosEliminados.contains(Personaje.FANTASMA_MOMIA) && enemigosEliminados.contains(Personaje.FANTASMA_PIRATA)
				&& enemigosEliminados.contains(Personaje.FANTASMA_ZOMBIE);
	}

}
