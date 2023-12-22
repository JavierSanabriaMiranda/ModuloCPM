package uo.cpm.l7.model.Juego;

import java.util.Random;

public class Dado {

	private int valor;
	
	/**
	 * @return valor aleatorio entre 0 y 1
	 */
	public final int lanzar() {
		Random random = new Random();
		return random.nextInt(0, 2);
	}
	
	/**
	 * @return valor almancenado en el dado
	 */
	public final int getValor() {
		return valor;
	}
}
