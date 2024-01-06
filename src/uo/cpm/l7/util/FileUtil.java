package uo.cpm.l7.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uo.cpm.l7.model.Hotel.Encantamiento;
import uo.cpm.l7.model.Hotel.Hotel;

public abstract class FileUtil {

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
			new RuntimeException("Error de entrada/salida");
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
			BufferedReader fichero = new BufferedReader(new FileReader("files/descuentos.dat"));
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
		String linea;
		String[] partes;
		
		File inputFile = new File("files/descuentos.dat");
//		File tempFile = new File("files/descuentosTEMP.dat");
		
		try {
			BufferedReader fichero = new BufferedReader(new FileReader(inputFile));
			
			// Read all lines into an array / list
			// Filter the line with the specified dni
			// Store the lines again in the same file
			
//			BufferedWriter ficheroTemp = new BufferedWriter(new FileWriter(tempFile, true));
			while (fichero.ready()) {
				linea = fichero.readLine();
				partes = linea.split(";");
				// Si la linea que se recorre no es la que se busca eliminar, se escribe en el archivo temporal
				if (!partes[0].toLowerCase().equals(DNI.toLowerCase()))
					ficheroTemp.append(linea);
			}
			fichero.close();
			ficheroTemp.close();
			// Se borra el archivo anterior
			//TODO Buscar solución
			boolean borrado = inputFile.delete();
			if (!borrado)
				System.out.println("Error al borrar");
			
			// Se renombra el archivo temporal para así tener de nuevo el archivo anterior ahora sin la línea a borrar
			File nombre = new File("files/descuentos.dat");
			
			boolean renombrado = tempFile.renameTo(nombre);
			if (!renombrado)
				System.out.println("Error al renombrar");
		} catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha encontrado.");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
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

