package uo.cpm.l7.model.Hotel;

import java.util.Date;

public class Reserva {

	/**
	 * DNI del cliente que realiza la reserva, ha de ser mayor de edad
	 */
	private String DNICliente;
	
	/**
	 * Nombre y apellidos del cliente que realiza la reserva
	 */
	private String nombreYApellidos;
	
	/**
	 * Correo electrónico de la persona que realiza la reserva
	 */
	private String email;
	
	/**
	 * Código del castillo que se reserva
	 */
	private String codigo;
	
	/**
	 * Fecha para la que se reserva el hotel
	 */
	private Date fechaReserva;
	
	/**
	 * Número de días que se realiza la reserva
	 */
	private int dias;
	
	/**
	 * Número de habitaciones que se reservan
	 */
	private int numHabitaciones;
	
	/**
	 * Precio final de la reserva, aplicado o no descuento
	 */
	private double precioFinal;
	
	/**
	 * Comentarios realizados por el cliente acerca de la reserva
	 */
	private String comentarios;
	
	/**
	 * Constructor por defecto de la clase reserva, recibe como parámetros los datos de la reserva
	 * 
	 * @param DNI del cliente
	 * @param nomYApell Nombre y apellidos del cliente
	 * @param email del cliente
	 * @param cod Código del hotel a reservar
	 * @param fecha Fecha para la reserva
	 * @param dias de estancia
	 * @param numHab Numero de habitaciones reservadas
	 * @param precio final de la reserva
	 * @param coment Comentarios del cliente
	 */
	public Reserva(String DNI, String nomYApell, String email, String cod, Date fecha, int dias, int numHab, double precio, String coment) {
		this.DNICliente = DNI;
		this.nombreYApellidos = nomYApell;
		this.email = email;
		this.codigo = cod;
		this.fechaReserva = fecha;
		this.dias = dias;
		this.numHabitaciones = numHab;
		this.precioFinal = precio;
		this.comentarios = coment;
	}
}
