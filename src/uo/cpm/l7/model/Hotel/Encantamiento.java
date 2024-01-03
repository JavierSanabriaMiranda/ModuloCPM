package uo.cpm.l7.model.Hotel;

public enum Encantamiento {
	
	APARICIONES("Ap"), DESCENSOS_TEMPERATURA("De"), ENCENDIDO_APAGADO_LUCES("En"), 
	OBJETOS_CAMBIAN_LUGAR("Ob"), OLORES_NAUSEABUNDOS("Ol"), RUIDOS_EXTRANOS("Ru");
	
	public final String diminutivo;
	
	private Encantamiento(String diminutivo) {
		this.diminutivo = diminutivo;
	}
	
	/**
	 * @return un String con el nombre de la imagen que representa al personaje
	 */
	public String getDiminutivo() {
		return this.diminutivo;
	}
}
