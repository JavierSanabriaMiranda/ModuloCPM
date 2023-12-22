package uo.cpm.l7.model.Juego;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tablero {
	
	/**
	 * Número de fantasmas por cuadrilla, es decir, número de fantasmas no lider que hay de cada tipo
	 * repartidos por el tablero
	 */
	private static final int NUM_FANTASMAS_POR_CUADRILLA = 3;
	
	
	/**
	 * Tamaño del tablero (número de casillas)
	 */
	private int size;
	
	/**
	 * Tablero sobre el que se realizan los movimientos y cuyos valores son los fantasmas o los cazafantasmas
	 */
	private Casilla[] tablero;
	
	/**
	 * Lista de las casillas que han sido descubiertas
	 */
	private List<Casilla> casillasDescubiertas;
	
	/**
	 * Constructor por defecto de la clase Tablero, recibe el tamaño del tablero a crear
	 * @param tam tamaño del tablero a crear
	 */
	public Tablero(int tam) {
		this.size = tam;
		this.tablero = new Casilla[size];
		this.casillasDescubiertas = new ArrayList<>();
		inicializarTablero();
	}
	
	/**
	 * Reparte los distintos personajes del juego en sus posiciones correspondientes, es decir,
	 * el fantasma lider en la primera posición del tablero (parte alta del tablero), los cazafantasmas en
	 * posiciones de la 15 a la 21 (parte baja del tablero) y el resto de los fantasmas se reparten por el resto del
	 * tablero de forma aleatoria
	 */
	public void inicializarTablero() {
		// Se ubica al fantasma lider en lo alto del tablero
		tablero[0] = new Casilla(Personaje.FANTASMA_LIDER);
		
		// Se ubica el resto de fantasmas de forma aleatoria
		distribuirFantasmas(Personaje.FANTASMA_BASICO);
		distribuirFantasmas(Personaje.FANTASMA_CALABAZA);
		distribuirFantasmas(Personaje.FANTASMA_MOMIA);
		distribuirFantasmas(Personaje.FANTASMA_ZOMBIE);
		distribuirFantasmas(Personaje.FANTASMA_PIRATA);
		
		// Se setablece a los cazafantasmas en la fila de abajo del tablero
		for (int i = 15; i < size; i++)
			tablero[i] = new Casilla(Personaje.CAZAFANTASMAS);
	}
	
	/**
	 * Distribuye en casillas aleatorias de la 1 a la 15, 3 fantasmas del tipo introducido como parámetro
	 * @param tipoFantasma
	 */
	private void distribuirFantasmas(Personaje tipoFantasma) {
		int numFantasmas = NUM_FANTASMAS_POR_CUADRILLA;
		Random random = new Random();
		int pos;
		
		while (numFantasmas != 0) {
			pos = random.nextInt(1,15);
			// Si ha encontrado una casilla vacía se establece el fantasma y se reduce el numFantasmas
			if (tablero[pos] == null) {
				tablero[pos] = new Casilla(tipoFantasma);
				numFantasmas--;
			}
		}
	}

	/**
	 * @return tamaño del tablero
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @return tablero
	 */
	public Casilla[] getTablero() {
		return tablero;
	}

	/**
	 * @return casillas que ya han sido descubiertas en la partida
	 */
	public List<Casilla> getCasillasDescubiertas() {
		return casillasDescubiertas;
	}
	
	
}
