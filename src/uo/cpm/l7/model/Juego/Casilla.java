package uo.cpm.l7.model.Juego;

public class Casilla {

	/**
	 * Personaje que contiene la casilla y que puede ser un tipo de fantasma
	 * o un cazafantasmas
	 */
	private Personaje valor;

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
	
	public void setValor(Personaje personaje) {
		this.valor = personaje;
	}
	
	/**
	 * @return Un String con el nombre de la imagen que representa al personaje contenido en la casilla
	 */
	public String getImagen() {
		return getValor().getImagen();
	}
	
	
}
