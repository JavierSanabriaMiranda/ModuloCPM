package uo.cpm.l7.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
						datosHotel[3], Float.parseFloat(datosHotel[3]), encantamientos));
			}
			fichero.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha encontrado.");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
		}
	}

	public static void saveToFile(String nombreFicheroSalida, String txPedido) {
		try {
			BufferedWriter fichero = new BufferedWriter(new FileWriter("files/" + nombreFicheroSalida + ".dat"));
			fichero.write(txPedido);
			fichero.close();
		}

		catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha podido guardar");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida");
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

