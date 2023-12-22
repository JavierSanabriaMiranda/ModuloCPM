package uo.cpm.l7.model.Juego;

public class Casilla {

	/**
	 * Personaje que contiene la casilla y que puede ser un tipo de fantasma
	 * o un cazafantasmas
	 */
	private Personaje valor;
	
	/**
	 * Valor que define si la casilla ha sido descubierta o no.
	 * Si esta a true, la casilla ha sido descubierta, si esta a false no
	 */
	private boolean descubierto;

	/**
	 * Constructor por defecto de la clase casilla, recibe el personaje oculto dentro de la casilla
	 * 
	 * @param personaje oculto dentro de la casilla
	 */
	public Casilla(Personaje personaje) {
		this.valor = personaje;
	}
	
	/**
	 * @return personaje contenido en la casilla
	 */
	public Personaje getValor() {
		return valor;
	}
	
	/**
	 * @return Un String con el nombre de la imagen que representa al personaje contenido en la casilla
	 */
	public String getImagen() {
		return getValor().getImagen();
	}

	public boolean isDescubierta() {
		return descubierto;
	}
	
	public void setDescubierta(boolean estado) {
		this.descubierto = estado;
	}
	
	
	
}
