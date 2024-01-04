package uo.cpm.l7.model.Hotel;

public enum Encantamiento {
	
	APARICIONES("Apariciones de fantasmas", "Ap"), DESCENSOS_TEMPERATURA("Descenso de temperatura", "De"), 
	ENCENDIDO_APAGADO_LUCES("Encendido y apagado de luces", "En"), 
	OBJETOS_CAMBIAN_LUGAR("Objetos que cambian de lugar" ,"Ob"), OLORES_NAUSEABUNDOS("Olores nauseabundos", "Ol"), 
	RUIDOS_EXTRANOS("Ruidos extraños", "Ru");
	
	public final String nombreCompleto;
	public final String diminutivo;
	
	private Encantamiento(String nombreCompleto ,String diminutivo) {
		this.nombreCompleto = nombreCompleto;
		this.diminutivo = diminutivo;
	}
	
	/**
	 * @return un String con el diminutivo del encantamiento
	 */
	public String getDiminutivo() {
		return this.diminutivo;
	}
	
	/**
	 * @return un String con el nombre completo del encantamiento
	 */
	public String getNombre() {
		return this.nombreCompleto;
	}
}
