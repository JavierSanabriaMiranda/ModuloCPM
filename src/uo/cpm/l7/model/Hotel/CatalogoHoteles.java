package uo.cpm.l7.model.Hotel;

import java.util.ArrayList;
import java.util.List;

import uo.cpm.l7.util.FileUtil;

public class CatalogoHoteles {
	
	private static final String FICHERO_HOTELES = "castillos.dat";
	
	private List<Hotel> hoteles;;

	/**
	 * Constructor por defecto de la clase CatalogoHoteles. Rellena el catálogo con los datos del fichero catillos.dat
	 */
	public CatalogoHoteles() {
		hoteles = new ArrayList<>();
		rellenarCatalogo();
	}
	
	/**
	 * Rellena el catálogo con los datos del fichero catillos.dat
	 */
	private void rellenarCatalogo() {
		FileUtil.loadFile(FICHERO_HOTELES, hoteles);
	}

	/**
	 * @return lista de hoteles
	 */
	public List<Hotel> getHoteles() {
		return hoteles;
	}
	
	
}
