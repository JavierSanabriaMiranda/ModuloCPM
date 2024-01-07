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
	 * Constructor por defecto de la clase CatalogoHoteles. Rellena el cat�logo con
	 * los datos del fichero catillos.dat
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
	 * @return n�mero de hoteles totales que tiene el cat�logo. Se usa el cat�logo
	 *         en espa�ol aunque el ingl�s tambi�n sirve ya que tienen el mismo
	 *         n�mero de hoteles
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
	 * @return array de Strings con los nombres de todas las ubicaciones en Espa�ol
	 *         en las que hay castillos
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
	 * @return array de Strings con los nombres de todas las ubicaciones en Ingl�s
	 *         las que hay castillos
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
	 * Devuelve el c�digo del castillo que coinciden con la busqueda introducida
	 * como par�metro
	 * 
	 * @param nombre del castillo a encontrar
	 * @return c�digo del castillo que coinciden con la busqueda introducida como
	 *         par�metro
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

	/**
	 * Recibe como par�metros de entrada el filtro que el usuario ha querido aplicar
	 * a su busqueda de castillos y retorna una lista con todos los c�digos de los
	 * castillos que se corresponden con el filtro aplicado
	 * 
	 * @param ubicacion      de los castillos filtrados
	 * @param precio         de los castillos filtrados
	 * @param encantamientos de los castillos filtrados (Deben tener m�nimo estos
	 *                       encantamientos)
	 * @return lista con todos los c�digos de los castillos que se corresponden con
	 *         el filtro aplicado
	 */
	public List<String> establecerFiltro(String ubicacion, double precio) {
		List<String> hotelesPermitidos = new ArrayList<>();

		for (Hotel hotel : hotelesES)
			// Si la ubicaci�n es Todas solo se observa el precio
			if (ubicacion == "Todas") {
				if (hotel.getPrecioHabitacion() <= precio)
					hotelesPermitidos.add(hotel.getCodigo());
			} else {
				if (hotel.getPais().equals(ubicacion) && hotel.getPrecioHabitacion() <= precio)
					hotelesPermitidos.add(hotel.getCodigo());
			}

		for (Hotel hotel : hotelesEN)
			// Si la ubicaci�n es Todas solo se observa el precio
			if (ubicacion == "All") {
				if (hotel.getPrecioHabitacion() <= precio)
					hotelesPermitidos.add(hotel.getCodigo());
			} else {
				if (hotel.getPais().equals(ubicacion) && hotel.getPrecioHabitacion() <= precio)
					hotelesPermitidos.add(hotel.getCodigo());
			}

		return hotelesPermitidos;
	}

	/**
	 * Recibe como par�metros de entrada el filtro que el usuario ha querido aplicar
	 * a su busqueda de castillos y retorna una lista con todos los c�digos de los
	 * castillos que se corresponden con el filtro aplicado
	 * 
	 * @param ubicacion      de los castillos filtrados
	 * @param precio         de los castillos filtrados
	 * @param encantamientos de los castillos filtrados (Deben tener m�nimo estos
	 *                       encantamientos)
	 * @return lista con todos los c�digos de los castillos que se corresponden con
	 *         el filtro aplicado
	 */
	public List<String> establecerFiltro(String ubicacion, double precio, List<String> encantamientos) {
		List<String> hotelesPermitidos = new ArrayList<>();

		for (Hotel hotel : hotelesES)
			if (ubicacion == "Todas") {
				if (hotel.getPrecioHabitacion() <= precio && contieneEncantamientos(encantamientos, hotel))
					hotelesPermitidos.add(hotel.getCodigo());
			} else {
				if (hotel.getPais().equals(ubicacion) && hotel.getPrecioHabitacion() <= precio
						&& contieneEncantamientos(encantamientos, hotel))
					hotelesPermitidos.add(hotel.getCodigo());
			}

		for (Hotel hotel : hotelesEN)
			if (ubicacion == "All") {
				if (hotel.getPrecioHabitacion() <= precio && contieneEncantamientos(encantamientos, hotel))
					hotelesPermitidos.add(hotel.getCodigo());
			} else {
				if (hotel.getPais().equals(ubicacion) && hotel.getPrecioHabitacion() <= precio
						&& contieneEncantamientos(encantamientos, hotel))
					hotelesPermitidos.add(hotel.getCodigo());
			}

		return hotelesPermitidos;
	}

	/**
	 * @param encantamientos a comprobar si los contiene el hotel
	 * @param hotel          a comprobar si contiene los encantamientos
	 * @return true si el hotel contiene todos los encantamientos introducidos como
	 *         par�metro, false en caso contrario
	 */
	private boolean contieneEncantamientos(List<String> encantamientos, Hotel hotel) {
		List<String> diminutivos = getDiminutivosEncantamientos(hotel.getEncantamientos());

		for (String encantamiento : encantamientos)
			// Si el hotel no contiene alguno de los encantamientos
			if (!diminutivos.contains(encantamiento))
				return false;
		// Si el hotel contiene todos los encantamientos
		return true;
	}

	/**
	 * @return lista con los diminutivos de los encantamientos introducidos como
	 *         par�metro
	 */
	private List<String> getDiminutivosEncantamientos(List<Encantamiento> encantamientos) {
		List<String> diminutivos = new ArrayList<>();
		for (Encantamiento encantamiento : encantamientos)
			diminutivos.add(encantamiento.getDiminutivo());
		return diminutivos;
	}
	
	/**
	 * Devuelve el nombre del hotel en espa�ol seg�n su c�digo o null si el c�digo no corresponde con ning�n castillo
	 * 
	 * @param codigo del hotel a encontrar
	 * @return nombre en espa�ol del hotel
	 */
	public String getHotelPorCodigoES(String codigo) {
		for (Hotel hotel : hotelesES)
			if (hotel.getCodigo().equals(codigo))
				return hotel.getDenominacion();
		return null;
	}
	
	/**
	 * Devuelve el nombre del hotel en ingl�s seg�n su c�digo o null si el c�digo no corresponde con ning�n castillo
	 * 
	 * @param codigo del hotel a encontrar
	 * @return nombre en ingl�s del hotel
	 */
	public String getHotelPorCodigoEN(String codigo) {
		for (Hotel hotel : hotelesEN)
			if (hotel.getCodigo().equals(codigo))
				return hotel.getDenominacion();
		return null;
	}
	

}
