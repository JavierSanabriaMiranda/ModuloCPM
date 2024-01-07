package uo.cpm.l7.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import uo.cpm.l7.model.Hotel.Encantamiento;
import uo.cpm.l7.model.Hotel.Hotel;
import uo.cpm.l7.model.Hotel.Reserva;

public abstract class FileUtil {
	
	private static final String ARCHIVO_DESCUENTOS = "files/descuentos.dat";
	private static final String ARCHIVOS_RESERVAS = "files/reservas.dat";

	public static void loadFile(String nombreFicheroEntrada, List<Hotel> catalogoHoteles) {

		String linea;
		String[] datosHotel = null;
		List<Encantamiento> encantamientos = new ArrayList<>();

		try {
			BufferedReader fichero = new BufferedReader(new FileReader(nombreFicheroEntrada));
			while (fichero.ready()) {
				linea = fichero.readLine();
				datosHotel = linea.split(";");
				encantamientos = getEncantamientos(datosHotel[5].split("-"));
				
				catalogoHoteles.add(new Hotel(datosHotel[0], datosHotel[1], datosHotel[2],
						datosHotel[3], Float.parseFloat(datosHotel[4]), encantamientos));
			}
			fichero.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha encontrado.");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
		}
	}

	public static void saveToFile(String nombreFicheroSalida, String txInfo) {
		try {
			// Creamos un FileWriter con la opción append activada (true en el segundo parámetro)
			BufferedWriter fichero = new BufferedWriter(new FileWriter("files/" + nombreFicheroSalida + ".dat", true));
			fichero.append(txInfo);
			fichero.close();
		}

		catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha podido guardar");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida", ioe);
		}
	}
	
	/**
	 * Comprueba si el fichero cuyo nombre es introducido como parámetro, contiene en alguna linea el contenido introducido como parámetro
	 * 
	 * @param nombreFichero a abrir
	 * @param contenido a comprobar si está en el fichero
	 * @return true si el contenido está en alguna línea del fichero, false en caso contrario
	 */
	public static boolean isInFile(String nombreFichero, String contenido) {
		String linea;
		String[] partes;
		
		try {
			BufferedReader fichero = new BufferedReader(new FileReader(nombreFichero));
			while (fichero.ready()) {
				linea = fichero.readLine();
				partes = linea.split(";");
				if (partes[0].toLowerCase().equals(contenido.toLowerCase())) {
					fichero.close();
					return true;
				}
			}
			fichero.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha encontrado.");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
		}
		return false;
	}
	
	/**
	 * Abre el fichero de descuentos y retorna el descuento asociado al DNI introducido como parámetro
	 * 
	 * @param DNI cuyo descuento se quiere retornar
	 * @return descuento asociado al DNI
	 */
	public static int getDescuento(String DNI) {
		String linea;
		String[] partes;
		
		try {
			BufferedReader fichero = new BufferedReader(new FileReader(ARCHIVO_DESCUENTOS));
			while (fichero.ready()) {
				linea = fichero.readLine();
				partes = linea.split(";");
				if (partes[0].toLowerCase().equals(DNI.toLowerCase())) {
					switch (partes[1]) {
					case "EXTRA10":
						return 10;
					case "EXTRA25":
						return 25;
					default:
						throw new IllegalArgumentException("Unexpected value: " + partes[1]);
					}
				}
			}
			fichero.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha encontrado.");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
		}
		throw new IllegalArgumentException("El DNI introducido no se encuentra en el fichero");
	}
	
	/**
	 * Borra el descuento asociado al DNI introducido como parámetro. Para borrar el descuento, crea un archivo temporal
	 * que copia línea a línea el archivo original, saltandose la línea a borrar. Posteriormente renombra el archivo temporal
	 * para sustituir al original
	 * 
	 * @param DNI cuyo descuento se quiere borrar
	 */
	public static void borrarDescuento(String DNI) {
		Path filePath = Paths.get(ARCHIVO_DESCUENTOS);
		try {
			// Pasa todas las líneas del fichero cuyo path es filePath a una lista
			List<String> lineas = Files.readAllLines(filePath);
			// Filtra la línea que tiene el DNI pasado como parámetro
			List<String> lineasSinDni = new ArrayList<>();
			for (String linea : lineas) {
				String[] partes = linea.split(";");
				// Si la linea que se recorre no es la que se busca eliminar, se escribe en la nueva lista
				if (!partes[0].toLowerCase().equals(DNI.toLowerCase()))
					lineasSinDni.add(linea);
			}
			
			// Guarda todas las líneas menos la filtrada en el mismo archivo original
			File fichero = new File(ARCHIVO_DESCUENTOS);
			BufferedWriter writer = new BufferedWriter(new FileWriter(fichero));
			
			for (String linea : lineasSinDni) {
				writer.write(linea + "\n");
			}
			writer.close();
			
			
		} catch (IOException e) {
			throw new RuntimeException("Error de Entrada/Salida", e);
		}
	}
	
	/**
	 * Devuelve una lista con todas las reservas asociadas al DNI introducido como parámetro
	 * 
	 * @param DNI asociado a las reservas a buscar
	 * @return lista de Strings que son las reservas asociadas al DNI introducido como parámetro
	 */
	public static List<Reserva> getReservas(String DNI) {
		String linea;
		String[] partes;
		List<Reserva> reservas = new ArrayList<>();
		
		try {
			BufferedReader fichero = new BufferedReader(new FileReader(ARCHIVOS_RESERVAS));
			while (fichero.ready()) {
				linea = fichero.readLine();
				partes = linea.split(";");
				if (partes[0].toLowerCase().equals(DNI.toLowerCase())) {
					reservas.add(new Reserva(partes[0],partes[1],partes[2],partes[3],partes[4],Integer.parseInt(partes[5]),
							Integer.parseInt(partes[6]),Double.parseDouble(partes[7]), "")); // Omitimos los comentarios porque no los mostramos
				}
			}
			fichero.close();
			return reservas;
		} catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha encontrado.");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
		}
		throw new IllegalArgumentException("El DNI introducido no se encuentra en el fichero");
	}
	
	/**
	 * Borra del archivo "reservas.dat" la reserva cuya linea esta formateada como lineaReserva
	 * @param lineaReserva linea a borrar formateada
	 */
	public static void borrarReserva(String lineaReserva) {
		Path filePath = Paths.get(ARCHIVOS_RESERVAS);
		try {
			// Pasa todas las líneas del fichero cuyo path es filePath a una lista
			List<String> lineas = Files.readAllLines(filePath);
			// Filtra la línea que tiene el formato pasado como parámetro
			List<String> lineasNoCoincidentes = new ArrayList<>();
			for (String linea : lineas) {
				// Si la linea que se recorre no es la que se busca eliminar, se escribe en la nueva lista
				if (!(linea + "\n").equals(lineaReserva)) // Le añadimos el \n que el readAllLines le quita
					lineasNoCoincidentes.add(linea);
			}
			
			// Guarda todas las líneas menos la filtrada en el mismo archivo original
			File fichero = new File(ARCHIVOS_RESERVAS);
			BufferedWriter writer = new BufferedWriter(new FileWriter(fichero));
			
			for (String linea : lineasNoCoincidentes) {
				writer.write(linea + "\n");
			}
			writer.close();
			
			
		} catch (IOException e) {
			throw new RuntimeException("Error de Entrada/Salida", e);
		}
	}
	
	
	/**
	 * Devuelve los encantamientos en función de sus diminutivos
	 * 
	 * @param diminutivos que identifican los encantamientos
	 * @return lista de objetos encantamiento
	 */
	private static List<Encantamiento> getEncantamientos(String[] diminutivos) {
		List<Encantamiento> encantamientos = new ArrayList<>();
		
		for (int i = 0; i < diminutivos.length; i++) {
			switch (diminutivos[i]) {
				case "Ap":
					encantamientos.add(Encantamiento.APARICIONES);
					break;
				case "De":
					encantamientos.add(Encantamiento.DESCENSOS_TEMPERATURA);
					break;
				case "En":
					encantamientos.add(Encantamiento.ENCENDIDO_APAGADO_LUCES);
					break;
				case "Ob":
					encantamientos.add(Encantamiento.OBJETOS_CAMBIAN_LUGAR);
					break;
				case "Ol":
					encantamientos.add(Encantamiento.OLORES_NAUSEABUNDOS);
					break;
				case "Ru":
					encantamientos.add(Encantamiento.RUIDOS_EXTRANOS);
					break;
				default:
					throw new IllegalArgumentException("La lista introducida tiene algun String con formato incorrecto");
			}
		}
		return encantamientos;
	}
}

