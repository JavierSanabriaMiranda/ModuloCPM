package uo.cpm.l7.model.Juego;

public enum Personaje {
	
	FANTASMA_LIDER("fanLider.png"), CAZAFANTASMAS("cazaFan.png"), FANTASMA_PIRATA("fanPirata.png"), FANTASMA_BASICO("fanDefault.png"), 
	FANTASMA_ZOMBIE("fanZombie.png"), FANTASMA_MOMIA("fanMomia.png"), FANTASMA_CALABAZA("fanCalabaza.png"), BORDE(""), VACIO(""); 
	
	public final String imagen;
	
	private Personaje(String img) {
		this.imagen = img;
	}
	
	/**
	 * @return un String con el nombre de la imagen que representa al personaje
	 */
	public String getImagen() {
		return this.imagen;
	}
	
}
