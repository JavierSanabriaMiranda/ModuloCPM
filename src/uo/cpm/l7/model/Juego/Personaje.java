package uo.cpm.l7.model.Juego;

public enum Personaje {
	
	FANTASMA_LIDER("fanLider"), CAZAFANTASMAS("cazaFan"), FANTASMA_PIRATA("fanPirata"), FANTASMA_BASICO("fanDefault"), 
	FANTASMA_ZOMBIE("fanZombie"), FANTASMA_MOMIA("fanMomia"), FANTASMA_CALABAZA("fanCalabaza"); 
	
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
