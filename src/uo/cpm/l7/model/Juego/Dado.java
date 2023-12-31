package uo.cpm.l7.model.Juego;

import java.util.Random;

public class Dado {

	private int valor;
	
	/**
	 * @return valor aleatorio entre 1 y 2
	 */
	public final void lanzar() {
		Random random = new Random();
		this.valor = random.nextInt(1, 3);
	}
	
	/**
	 * @return valor almancenado en el dado
	 */
	public final int getValor() {
		return valor;
	}
	
	/**
	 * @param valor a establecer en el dado
	 */
	public void setValor(int valor) {
		this.valor = valor;
	}
}
