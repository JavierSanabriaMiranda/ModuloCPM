package uo.cpm.l7.model.Hotel;

import java.util.List;

public class Hotel {
	
	public static final int MAX_PERSONAS_POR_HABITACION = 2;
	
	
	/**
	 * Codigo del Hotel
	 */
	private String codigo;
	
	/**
	 * Nombre del Hotel
	 */
	private String denominacion;
	
	/**
	 * Descripción completa del hotel
	 */
	private String descripcion;
	
	/**
	 * Pais en el que se ubica el hotel
	 */
	private String pais;
	
	/**
	 * Precio de cada una de las habitaciones a reservar en el hotel
	 */
	private double precioHabitacion;
	
	/**
	 * Lista de encantamientos que contiene el hotel
	 */
	private List<Encantamiento> encantamientos;
	
	
	/**
	 * Constructor por defecto de la clase Hotel, recibe como parámetro todas aquellas características del hotel
	 * 
	 * @param codigo del hotel
	 * @param denominacion del hotel
	 * @param descripcion del hotel
	 * @param pais en el que se ubica el hotel
	 * @param precio de cada habitación
	 * @param encantamientos que tiene el hotel
	 */
	public Hotel(String codigo, String denom, String desc, String pais, double precio, List<Encantamiento> encantamientos) {
		this.codigo = codigo;
		this.denominacion = denom;
		this.descripcion = desc;
		this.pais = pais;
		this.precioHabitacion = precio;
		this.encantamientos = encantamientos;
	}


	public String getCodigo() {
		return codigo;
	}


	public String getDenominacion() {
		return denominacion;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public String getPais() {
		return pais;
	}


	public double getPrecioHabitacion() {
		return precioHabitacion;
	}


	public List<Encantamiento> getEncantamientos() {
		return encantamientos;
	}
	
	public int getNumeroEncantamientos() {
		return encantamientos.size();
	}
	
	/**
	 * @param pos posición del encantamiento a devolver
	 * @return encantamiento de la lista de encantamientos que está en la posición "pos"
	 */
	public Encantamiento getEncantamiento(int pos) {
		return encantamientos.get(pos);
	}

	
	
}
