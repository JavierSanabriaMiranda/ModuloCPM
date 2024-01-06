package uo.cpm.l7.service;

import java.awt.font.GlyphJustificationInfo;
import java.util.List;

import uo.cpm.l7.model.Hotel.CatalogoHoteles;
import uo.cpm.l7.model.Hotel.Encantamiento;
import uo.cpm.l7.model.Hotel.Hotel;
import uo.cpm.l7.model.Hotel.ListaEncantamientos;
import uo.cpm.l7.model.Juego.Casilla;
import uo.cpm.l7.model.Juego.Juego;
import uo.cpm.l7.model.Juego.Personaje;
import uo.cpm.l7.util.FileUtil;

/**
 *
 * @author uo293758
 * 
 * Clase de servicio para el interfaz de usuario, proporciona a esta todos los m�todos necesarios para el correcto funcionamiento
 * de la aplicaci�n, tanto en la parte del juego como en la de reserva de hoteles
 *
 */
public class Horrotel {
	
	/**
	 * Juego que permite obtener descuento a los clientes
	 */
	private Juego juego = new Juego();
	
	/**
	 * Cat�logo que contiene la lista de todos los hoteles que proporciona la aplicaci�n
	 */
	private CatalogoHoteles catalogo = new CatalogoHoteles();
	
	/**
	 * @return filas del tablero del juego
	 */
	public int getFilasTablero() {
		return juego.getTablero().getFilas();
	}
	
	/**
	 * @return columnas del tablero del juego
	 */
	public int getColumnasTablero() {
		return juego.getTablero().getColumnas();
	}
	
	/**
	 * Devuelve la casilla cuya posici�n es determinada por la fila y columna introducidas como par�metro
	 * 
	 * @param fila de la casilla
	 * @param columna de la casilla
	 * @return casilla cuya posici�n se corresponde con la fila y la columna
	 */
	public Casilla getCasilla(int fila, int columna) {
		return juego.getTablero().getTablero()[fila][columna];
	}
	
	/**
	 * @param casilla a comprobar si es borde
	 * @return true si la casilla que se introdujo como par�metro es de tipo Personaje.BORDE o false en caso contrario
	 */
	public boolean isBorde(Casilla casilla) {
		return casilla.getValor().equals(Personaje.BORDE);
	}
	
	/**
	 * @param casilla a comprobar si es cazafantasmas
	 * @return true si la casilla que se introdujo como par�metro es de tipo Personaje.CAZAFANTASMAS o false en caso contrario
	 */
	public boolean isCazafantasmas(Casilla casilla) {
		return casilla.getValor().equals(Personaje.CAZAFANTASMAS);
	}
	
	/**
	 * @param casilla a comprobar si es vacio
	 * @return true si la casilla que se introdujo como par�metro es de tipo Personaje.VACIO o false en caso contrario
	 */
	public boolean isVacio(Casilla casilla) {
		return casilla.getValor().equals(Personaje.VACIO);
	}
	
	/**
	 * Inicializa el juego para iniciar una nueva partida
	 */
	public void inicializarJuego() {
		juego.inicializarJuego();
	}
	
	/**
	 * Lanza el dado para generar en �l un valor entre 1 y 2
	 */
	public void tirarDado() {
		juego.lanzarDado();
	}
	
	/**
	 * @return valor previamente obtenido por el lanzamiento del dado
	 */
	public int getValorDado() {
		return juego.getValor();
	}
	
	/**
	 * @return n�mero de tiradas retantes que quedan en el dado
	 */
	public int getTiradasRestantes() {
		return juego.getNumeroLanzamientos();
	}
	
	/**
	 * Mueve el personaje (unicamente CAZAFANTASMAS) ubicado en la posici�n filaInicial columnaInicial en vertical 
	 * seg�n el valor obtenido anteriormente por el dado. Si la posici�n introducida no permite el movimiento retorna false, si lo permite
	 * retorna true
	 * 
	 * @param filaInicial en la que se ubica el CAZAFANTASMAS
	 * @param columnaInicial en la que se ubica el CAZAFANTASMAS
	 * @return true si el movimiento es posible, false en caso contrario
	 */
	public boolean moverACasilla(int filaInicial, int columnaInicial) {
		return juego.moverACasilla(filaInicial, columnaInicial);
	}
	
	/**
	 * @return true si el juego ha finalizado, false en caso contrario
	 */
	public boolean isJuegoFinalizado() {
		return juego.isFinalizado();
	}
	
	/**
	 * @return valor n�merico que representa el porcentaje de descuento obtenido al final de una partida
	 */
	public int getValorDescuento() {
		if (juego.getPremio() == null)
			return 0;
		String codDescuento = juego.getPremio();
		
		switch (codDescuento) {
		case Juego.DESCUENTO_10:
			return 10;
		case Juego.DESCUENTO_25:
			return 25;
		default:
			throw new IllegalArgumentException("Unexpected value: " + codDescuento);
		}
		
	}
	
	/**
	 * @return n�mero de enemigos eliminados en la partida actual o la �ltima jugada
	 */
	public int getNumeroEliminaciones() {
		return juego.getEnemigosEliminados().size();
	}
	
	/**
	 * Devuelve el enemigo eliminado que se ubica en la posici�n de la lista de enemigos eliminados pos
	 * 
	 * @param pos posicion del enemigo eliminado que queremos obtener
	 * @return enemigo eliminado que se encontraba en la posici�n pos de la lista de enemigos eliminados
	 */
	public Personaje getEnemigoEliminado(int pos) {
		return juego.getEnemigosEliminados().get(pos);
	}
	
	/**
	 * @return true si la ultima partida jugada ha finalizado con premio
	 */
	public boolean hayPremio() {
		return juego.hayPremio();
	}
	
	/**
	 * Guarda el descuento asociado al DNI introducido en el documento "descuentos.dat"
	 * @param DNI del cliente que guarda el descuento
	 */
	public void guardarDescuento(String DNI) {
		String linea = DNI + ";" + juego.getPremio() + "\n";
		FileUtil.saveToFile("descuentos", linea);
	}
	
	/**
	 * @return true si el DNI est� guardado en el documento de descuentos
	 */
	public boolean isDNIGuardado(String DNI) {
		return FileUtil.isInFile("files/descuentos.dat", DNI);
	}
	
	/**
	 * @return n�mero de tipos de encantamientos que puede tener un hotel
	 */
	public int getNumeroEncantamientosTotales() {
		return ListaEncantamientos.NUMERO_ENCANTAMIENTOS;
	}
	
	/**
	 * Devuelve el encantamiento de la lista de encantamientos que se encuentra en la posici�n pos
	 * 
	 * @param pos del encantamiento a obtener
	 * @return encantamiento de la lista de encantamientos que se encuentra en la posici�n pos
	 */
	public Encantamiento getEncantamientoEnLista(int pos) {
		return ListaEncantamientos.getEncantamiento(pos);
	}
	
	/**
	 * @return precio m�s bajo de entre todos los hoteles del catalogo
	 */
	public double getPrecioMasBajo() {
		return catalogo.getPrecioMasBajo();
	}
	
	/**
	 * @return precio m�s alto de entre todos los hoteles del catalogo
	 */
	public double getPrecioMasAlto() {
		return catalogo.getPrecioMasAlto();
	}
	
	/**
	 * Devuelve el hotel de la lista de hoteles en espa�ol que ocupa la posici�n pos
	 * 
	 * @param pos del hotel en al lista
	 * @return hotel de la lista de hoteles en espa�ol que ocupa la posici�n pos
	 */
	public Hotel getHotelES(int pos) {
		return catalogo.getHotelesES().get(pos);
	}
	
	/**
	 * Devuelve el hotel de la lista de hoteles en ingl�s que ocupa la posici�n pos
	 * 
	 * @param pos del hotel en al lista
	 * @return hotel de la lista de hoteles en ingl�s que ocupa la posici�n pos
	 */
	public Hotel getHotelEN(int pos) {
		return catalogo.getHotelesEN().get(pos);
	}
	
	/**
	 * @return n�mero de hoteles que hay en el cat�logo
	 */
	public int getNumHoteles() {
		return catalogo.getNumHoteles();
	}
	
	/**
	 * @return array de Strings con los nombres de todas las ubicaciones en Espa�ol en las que hay castillos
	 */
	public String[] getUbicacionesES() {
		return catalogo.getUbicacionesES();
	}
	
	/**
	 * @return array de Strings con los nombres de todas las ubicaciones en Ingles en las que hay castillos
	 */
	public String[] getUbicacionesEN() {
		return catalogo.getUbicacionesEN();
	}
	
	/**
	 * Devuelve el c�digo del castillo que coinciden con la busqueda introducida como par�metro
	 * 
	 * @param nombre del castillo a encontrar
	 * @return c�digo del castillo que coinciden con la busqueda introducida como par�metro
	 */
	public String buscarCastilloPorNombre(String nombre) {
		return catalogo.buscarCastilloPorNombre(nombre);
	}
	
	/**
	 * Recibe como par�metros de entrada el filtro que el usuario ha querido aplicar a su busqueda de castillos y retorna
	 * una lista con todos los c�digos de los castillos que se corresponden con el filtro aplicado
	 * 
	 * @param ubicacion de los castillos filtrados
	 * @param precio de los castillos filtrados
	 * @param encantamientos de los castillos filtrados (Deben tener m�nimo estos encantamientos)
	 * @return lista con todos los c�digos de los castillos que se corresponden con el filtro aplicado
	 */
	public List<String> establecerFiltro(String ubicacion, double precio, List<String> encantamientos) {
		if (encantamientos.isEmpty())
			return catalogo.establecerFiltro(ubicacion, precio);
		else
			return catalogo.establecerFiltro(ubicacion, precio, encantamientos);
	}
	
	/**
	 * Devuelve el n�mero m�nimo de habitaciones que se deben seleccionar para el n�mero de personas introducido como par�metro
	 * 
	 * @param personas que figuran en la reserva
	 * @return n�mero m�nimo de habitaciones
	 */
	public int getNumeroMinimoDeHabitaciones(int personas) {
		return (int) Math.ceil(personas/2.0);
	}
	
	public double calcularPrecioReserva(int habitaciones, int dias, Hotel hotel) {
		return hotel.getPrecioHabitacion() * habitaciones * dias;
	}
}
