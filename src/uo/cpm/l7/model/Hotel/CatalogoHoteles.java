package uo.cpm.l7.model.Hotel;

import java.util.ArrayList;
import java.util.List;

import uo.cpm.l7.util.FileUtil;

public class CatalogoHoteles {
	
	private static final String FICHERO_HOTELES_ES = "files/castillos.dat";
	private static final String FICHERO_HOTELES_EN = "files/castles.dat";
	
	private List<Hotel> hotelesES;
	private List<Hotel> hotelesEN;

	/**
	 * Constructor por defecto de la clase CatalogoHoteles. Rellena el cat�logo con los datos del fichero catillos.dat
	 */
	public CatalogoHoteles() {
		hotelesES = new ArrayList<>();
		hotelesEN = new ArrayList<>();
		rellenarCatalogoES();
		rellenarCatalogoEN();
	}
	
	/**
	 * Rellena el cat�logo espa�ol con los datos del fichero catillos.dat
	 */
	private void rellenarCatalogoES() {
		FileUtil.loadFile(FICHERO_HOTELES_ES, hotelesES);
	}
	
	/**
	 * Rellena el cat�logo ingl�s con los datos del fichero castles.dat
	 */
	private void rellenarCatalogoEN() {
		FileUtil.loadFile(FICHERO_HOTELES_EN, hotelesEN);
	}

	/**
	 * @return lista de hoteles en espa�ol
	 */
	public List<Hotel> getHotelesES() {
		return hotelesES;
	}
	
	/**
	 * @return lista de hoteles en ingl�s
	 */
	public List<Hotel> getHotelesEN() {
		return hotelesEN;
	}
	
	/**
	 * @return n�mero de hoteles totales que tiene el cat�logo. Se usa el cat�logo en espa�ol aunque el ingl�s tambi�n sirve
	 * ya que tienen el mismo n�mero de hoteles
	 */
	public int getNumHoteles() {
		return hotelesES.size();
	}
	
	/**
	 * @return precio m�s bajo de entre todos los hoteles
	 */
	public double getPrecioMasBajo() {
		double precioMin = Double.POSITIVE_INFINITY;
		for (Hotel hotel : hotelesES) {
			if (hotel.getPrecioHabitacion() < precioMin)
				precioMin = hotel.getPrecioHabitacion();
		}
		return precioMin;
	}
	
	/**
	 * @return precio m�s alto de entre todos los hoteles
	 */
	public double getPrecioMasAlto() {
		double precioMax = Double.NEGATIVE_INFINITY;
		for (Hotel hotel : hotelesES) {
			if (hotel.getPrecioHabitacion() > precioMax)
				precioMax = hotel.getPrecioHabitacion();
		}
		return precioMax;
	}
	
	/**
	 * @return n�mero de paises distintos en los que hay hoteles
	 */
	public int getNumeroPaises() {
		List<String> ubicaciones = new ArrayList<>();
		for (Hotel hotel : hotelesES) {
			// Si no se ha guardado aun esta ubicacion
			if (!ubicaciones.contains(hotel.getPais()))
				ubicaciones.add(hotel.getPais());
		}
		return ubicaciones.size();
	}
	
	/**
	 * @return array de Strings con los nombres de todas las ubicaciones en Espa�ol en las que hay castillos
	 */
	public String[] getUbicacionesES() {
		List<String> ubicaciones = new ArrayList<>();
		ubicaciones.add("Todas");
		for (Hotel hotel : hotelesES) {
			// Si no se ha guardado aun esta ubicacion
			if (!ubicaciones.contains(hotel.getPais()))
				ubicaciones.add(hotel.getPais());
		}
		String[] ubicacionesArray = new String[ubicaciones.size()];
		for (int i = 0; i < ubicaciones.size(); i++)
			ubicacionesArray[i] = ubicaciones.get(i);
		return ubicacionesArray;
	}
	
	/**
	 * @return array de Strings con los nombres de todas las ubicaciones en Ingl�s las que hay castillos
	 */
	public String[] getUbicacionesEN() {
		List<String> ubicaciones = new ArrayList<>();
		ubicaciones.add("All");
		for (Hotel hotel : hotelesEN) {
			// Si no se ha guardado aun esta ubicacion
			if (!ubicaciones.contains(hotel.getPais()))
				ubicaciones.add(hotel.getPais());
		}
		String[] ubicacionesArray = new String[ubicaciones.size()];
		for (int i = 0; i < ubicaciones.size(); i++)
			ubicacionesArray[i] = ubicaciones.get(i);
		return ubicacionesArray;
	}
	
	/**
	 * Devuelve el c�digo del castillo que coinciden con la busqueda introducida como par�metro
	 * 
	 * @param nombre del castillo a encontrar
	 * @return c�digo del castillo que coinciden con la busqueda introducida como par�metro
	 */
	public String buscarCastilloPorNombre(String nombre) {
		
		for (Hotel hotel : hotelesES) 
			if (hotel.getDenominacion().toLowerCase().equals(nombre.toLowerCase()))
				return hotel.getCodigo();
		for (Hotel hotel : hotelesEN)
			if (hotel.getDenominacion().toLowerCase().equals(nombre.toLowerCase()))
				return hotel.getCodigo();
		return null;
	}
	
}
