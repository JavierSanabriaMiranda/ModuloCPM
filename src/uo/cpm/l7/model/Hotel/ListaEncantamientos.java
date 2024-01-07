package uo.cpm.l7.model.Hotel;

/**
 * Clase que contiene una lista con todos los encantamientos que un hotel puede tener
 */
public class ListaEncantamientos {
	
	public static final int NUMERO_ENCANTAMIENTOS = 6;
	
	private static Encantamiento[] encantamientos = {Encantamiento.APARICIONES, Encantamiento.DESCENSOS_TEMPERATURA,
						Encantamiento.ENCENDIDO_APAGADO_LUCES, Encantamiento.OBJETOS_CAMBIAN_LUGAR, Encantamiento.OLORES_NAUSEABUNDOS,
						Encantamiento.RUIDOS_EXTRANOS};
	
	public static Encantamiento getEncantamiento(int pos) {
		return encantamientos[pos];
	}
}
