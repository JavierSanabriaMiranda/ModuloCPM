package uo.cpm.l7.ui;

import javax.swing.JPanel;

import uo.cpm.l7.model.Hotel.Hotel;
import uo.cpm.l7.service.Horrotel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.border.LineBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.ResourceBundle;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JComboBox;

public class PanelInfoHotel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Horrotel app;
	private VentanaPrincipal vp;
	private Hotel hotel;
	private JPanel pnSuperior;
	private JPanel pnDescripci�nHotel;
	private JPanel pnFotoYPrecio;
	private JLabel lbFoto;
	private JLabel lbPrecioPorNoche;
	private JLabel lbNombreHotel;
	private JLabel lbInfoMaxPersonasHabitacion;
	private JTextPane txPnDescripcion;
	private JPanel pnInferior;
	private JPanel pnPaisEncantamientosYHabitaciones;
	private JTextPane txEncantamientosYHabitaciones;
	private JPanel pnFotosHabitaciones;
	private JPanel pnReserva;
	private JLabel lbReserva;
	private JPanel pnHabitaciones;
	private JPanel pnPersonas;
	private JPanel pnFechaEntrada;
	private JPanel pnFechaSalida;
	private JPanel pnPrecioFinal;
	private JButton btReservar;
	private JPanel pnPrecioPorNoche;
	private JLabel lbValorPrecioPorNoche;
	// Configuramos los atributos que vamos a poner al texto del JTextPane (Texto
	// centrado)
	private SimpleAttributeSet centrarTexto = new SimpleAttributeSet();
	private JLabel lbHabitaciones;
	private JLabel lbPersonas;
	private JLabel lbEntrada;
	private JLabel lbSalida;
	private JLabel lbPrecioFinal;
	private JSpinner spHabitaciones;
	private JSpinner spPersonas;
	private JTextField txPrecioFinal;
	private Component horizontalStrut;
	private Component horizontalStrut_1;
	private Component horizontalGlue;
	private Component horizontalGlue_1;
	private Component horizontalStrut_2;
	private Component horizontalStrut_2_1;
	private Component horizontalStrut_3;
	private Component horizontalStrut_5;
	private Component horizontalStrut_6;
	private Component horizontalStrut_7;
	private ActualizarPrecio aP = new ActualizarPrecio();
	private ActualizarPrecioParaCb aPC = new ActualizarPrecioParaCb();
	private ActualizarCbSalida aCS = new ActualizarCbSalida();
	private JComboBox<String> cbFechaEntrada;
	private JComboBox<String> cbFechaSalida;
	private Component horizontalGlue_2;
	private Component horizontalGlue_3;
	private Component horizontalStrut_2_2;
	private Component horizontalStrut_2_3;
	private Component horizontalStrut_5_1;
	private Component horizontalStrut_5_2;
	private Component horizontalGlue_4;
	// Valores importantes para la reserva
	private int numeroHabitaciones;
	private int numeroDias;
	private JPanel pnLbReserva;

	/**
	 * Create the panel.
	 */
	public PanelInfoHotel(Horrotel app, VentanaPrincipal vp, Hotel hotel) {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				// Reescala la imagen del hotel
				setImagenAdaptada(getLbFoto(), "/img/" + hotel.getCodigo() + ".png");

				for (int i = 0; i < getPnFotosHabitaciones().getComponentCount(); i++) {
					JLabel lb = (JLabel) getPnFotosHabitaciones().getComponent(i);
					setImagenAdaptada(lb, "/img/habitacion" + i + ".jpg");
				}
			}
		});
		setBackground(Color.DARK_GRAY);
		this.app = app;
		this.vp = vp;
		this.hotel = hotel;

		// Configuramos los atributos que vamos a poner al texto del JTextPane (Texto
		// centrado)
		StyleConstants.setAlignment(centrarTexto, StyleConstants.ALIGN_CENTER);
		setLayout(new GridLayout(0, 1, 0, 0));
		add(getPnSuperior());
		add(getPnInferior());

		localizar();
	}

	private JPanel getPnSuperior() {
		if (pnSuperior == null) {
			pnSuperior = new JPanel();
			pnSuperior.setPreferredSize(new Dimension(32767, 1500));
			pnSuperior.setBackground(Color.DARK_GRAY);
			pnSuperior.setLayout(new GridLayout(0, 2, 0, 0));
			pnSuperior.add(getPnFotoYPrecio());
			pnSuperior.add(getPnDescripci�nHotel());
		}
		return pnSuperior;
	}

	private JPanel getPnDescripci�nHotel() {
		if (pnDescripci�nHotel == null) {
			pnDescripci�nHotel = new JPanel();
			pnDescripci�nHotel.setMaximumSize(new Dimension(1500, 1500));
			pnDescripci�nHotel.setBackground(Color.DARK_GRAY);
			pnDescripci�nHotel.setLayout(new BoxLayout(pnDescripci�nHotel, BoxLayout.Y_AXIS));
			pnDescripci�nHotel.add(getLbNombreHotel());
			pnDescripci�nHotel.add(getLbInfoMaxPersonasHabitacion());
			pnDescripci�nHotel.add(getTxPnDescripcion());
		}
		return pnDescripci�nHotel;
	}

	private JPanel getPnFotoYPrecio() {
		if (pnFotoYPrecio == null) {
			pnFotoYPrecio = new JPanel();
			pnFotoYPrecio.setMaximumSize(new Dimension(1500, 1500));
			pnFotoYPrecio.setPreferredSize(new Dimension(1500, 32767));
			pnFotoYPrecio.setBorder(new LineBorder(Color.WHITE));
			pnFotoYPrecio.setBackground(Color.DARK_GRAY);
			pnFotoYPrecio.setLayout(new BorderLayout(0, 0));
			pnFotoYPrecio.add(getLbFoto(), BorderLayout.CENTER);
			pnFotoYPrecio.add(getPnPrecioPorNoche(), BorderLayout.SOUTH);
		}
		return pnFotoYPrecio;
	}

	private JLabel getLbFoto() {
		if (lbFoto == null) {
			lbFoto = new JLabel("");
		}
		return lbFoto;
	}

	private JLabel getLbPrecioPorNoche() {
		if (lbPrecioPorNoche == null) {
			lbPrecioPorNoche = new JLabel("");
			lbPrecioPorNoche.setForeground(Color.WHITE);
			lbPrecioPorNoche.setFont(new Font("Arial", Font.PLAIN, 20));
		}
		return lbPrecioPorNoche;
	}

	private JLabel getLbNombreHotel() {
		if (lbNombreHotel == null) {
			lbNombreHotel = new JLabel("");
			lbNombreHotel.setAlignmentX(Component.CENTER_ALIGNMENT);
			lbNombreHotel.setForeground(Color.WHITE);
			lbNombreHotel.setFont(new Font("Arial", Font.PLAIN, 45));
			lbNombreHotel.setText(hotel.getDenominacion());
		}
		return lbNombreHotel;
	}

	private JLabel getLbInfoMaxPersonasHabitacion() {
		if (lbInfoMaxPersonasHabitacion == null) {
			lbInfoMaxPersonasHabitacion = new JLabel("");
			lbInfoMaxPersonasHabitacion.setAlignmentX(Component.CENTER_ALIGNMENT);
			lbInfoMaxPersonasHabitacion.setForeground(Color.WHITE);
			lbInfoMaxPersonasHabitacion.setFont(new Font("Arial", Font.PLAIN, 20));
		}
		return lbInfoMaxPersonasHabitacion;
	}

	private JTextPane getTxPnDescripcion() {
		if (txPnDescripcion == null) {
			txPnDescripcion = new JTextPane();
			txPnDescripcion.setPreferredSize(new Dimension(1500, 32767));
			txPnDescripcion.setBackground(Color.DARK_GRAY);
			txPnDescripcion.setForeground(Color.WHITE);
			txPnDescripcion.setEditable(false);
			txPnDescripcion.setFont(new Font("Arial", Font.PLAIN, 20));

			// Cogemos el documento de estilo del JTextPane
			StyledDocument doc = txPnDescripcion.getStyledDocument();

			// Aplicamos los atributos al documento
			doc.setParagraphAttributes(0, doc.getLength(), centrarTexto, false);

			txPnDescripcion.setText(hotel.getDescripcion());
		}
		return txPnDescripcion;
	}

	private JPanel getPnInferior() {
		if (pnInferior == null) {
			pnInferior = new JPanel();
			pnInferior.setPreferredSize(new Dimension(32767, 1500));
			pnInferior.setBackground(Color.DARK_GRAY);
			pnInferior.setLayout(new GridLayout(0, 4, 0, 0));
			pnInferior.add(getPnPaisEncantamientosYHabitaciones());
			pnInferior.add(getHorizontalStrut_1());
			pnInferior.add(getHorizontalStrut_1_1());
			pnInferior.add(getPnReserva());
		}
		return pnInferior;
	}

	private JPanel getPnPaisEncantamientosYHabitaciones() {
		if (pnPaisEncantamientosYHabitaciones == null) {
			pnPaisEncantamientosYHabitaciones = new JPanel();
			pnPaisEncantamientosYHabitaciones.setMaximumSize(new Dimension(300, 32767));
			pnPaisEncantamientosYHabitaciones.setPreferredSize(new Dimension(1500, 32767));
			pnPaisEncantamientosYHabitaciones.setBackground(Color.DARK_GRAY);
			pnPaisEncantamientosYHabitaciones.setLayout(new BorderLayout(0, 0));
			pnPaisEncantamientosYHabitaciones.add(getTxEncantamientosYHabitaciones(), BorderLayout.NORTH);
			pnPaisEncantamientosYHabitaciones.add(getPnFotosHabitaciones());
		}
		return pnPaisEncantamientosYHabitaciones;
	}

	private JTextPane getTxEncantamientosYHabitaciones() {
		if (txEncantamientosYHabitaciones == null) {
			txEncantamientosYHabitaciones = new JTextPane();
			txEncantamientosYHabitaciones.setFont(new Font("Arial", Font.PLAIN, 15));
			txEncantamientosYHabitaciones.setForeground(Color.WHITE);
			txEncantamientosYHabitaciones.setEditable(false);
			txEncantamientosYHabitaciones.setBackground(Color.DARK_GRAY);
		}
		return txEncantamientosYHabitaciones;
	}

	private JPanel getPnFotosHabitaciones() {
		if (pnFotosHabitaciones == null) {
			pnFotosHabitaciones = new JPanel();
			pnFotosHabitaciones.setMaximumSize(new Dimension(500, 500));
			pnFotosHabitaciones.setBackground(Color.DARK_GRAY);
			pnFotosHabitaciones.setLayout(new GridLayout(0, 2, 0, 0));
			generarFotoHabitaciones();
		}
		return pnFotosHabitaciones;
	}

	private JPanel getPnReserva() {
		if (pnReserva == null) {
			pnReserva = new JPanel();
			pnReserva.setPreferredSize(new Dimension(50, 32767));
			pnReserva.setBorder(new LineBorder(Color.WHITE));
			pnReserva.setBackground(Color.DARK_GRAY);
			pnReserva.setLayout(new GridLayout(0, 1, 0, 0));
			pnReserva.add(getPnLbReserva());
			pnReserva.add(getPnPersonas());
			pnReserva.add(getPnHabitaciones());
			pnReserva.add(getPnFechaEntrada());
			pnReserva.add(getPnFechaSalida());
			pnReserva.add(getPnPrecioFinal());
			pnReserva.add(getBtReservar());
		}
		return pnReserva;
	}

	private JLabel getLbReserva() {
		if (lbReserva == null) {
			lbReserva = new JLabel("");
			lbReserva.setAlignmentX(Component.CENTER_ALIGNMENT);
			lbReserva.setForeground(Color.WHITE);
			lbReserva.setFont(new Font("Arial", Font.PLAIN, 20));
		}
		return lbReserva;
	}

	private JPanel getPnHabitaciones() {
		if (pnHabitaciones == null) {
			pnHabitaciones = new JPanel();
			pnHabitaciones.setMaximumSize(new Dimension(999999, 50));
			pnHabitaciones.setBackground(Color.DARK_GRAY);
			pnHabitaciones.setLayout(new BoxLayout(pnHabitaciones, BoxLayout.X_AXIS));
			pnHabitaciones.add(getHorizontalStrut_2());
			pnHabitaciones.add(getLbHabitaciones());
			pnHabitaciones.add(getHorizontalGlue());
			pnHabitaciones.add(getSpHabitaciones());
			pnHabitaciones.add(getHorizontalStrut_5());
		}
		return pnHabitaciones;
	}

	private JPanel getPnPersonas() {
		if (pnPersonas == null) {
			pnPersonas = new JPanel();
			pnPersonas.setMaximumSize(new Dimension(999999, 50));
			pnPersonas.setBackground(Color.DARK_GRAY);
			pnPersonas.setLayout(new BoxLayout(pnPersonas, BoxLayout.X_AXIS));
			pnPersonas.add(getHorizontalStrut_2_1());
			pnPersonas.add(getLbPersonas());
			pnPersonas.add(getHorizontalGlue_1());
			pnPersonas.add(getSpPersonas());
			pnPersonas.add(getHorizontalStrut_6());
		}
		return pnPersonas;
	}

	private JPanel getPnFechaEntrada() {
		if (pnFechaEntrada == null) {
			pnFechaEntrada = new JPanel();
			pnFechaEntrada.setMaximumSize(new Dimension(999999, 50));
			pnFechaEntrada.setBackground(Color.DARK_GRAY);
			pnFechaEntrada.setLayout(new BoxLayout(pnFechaEntrada, BoxLayout.X_AXIS));
			pnFechaEntrada.add(getHorizontalStrut_2_2());
			pnFechaEntrada.add(getLbEntrada());
			pnFechaEntrada.add(getHorizontalGlue_2());
			pnFechaEntrada.add(getCbFechaEntrada());
			pnFechaEntrada.add(getHorizontalStrut_5_1());
		}
		return pnFechaEntrada;
	}

	private JPanel getPnFechaSalida() {
		if (pnFechaSalida == null) {
			pnFechaSalida = new JPanel();
			pnFechaSalida.setMaximumSize(new Dimension(999999, 50));
			pnFechaSalida.setBackground(Color.DARK_GRAY);
			pnFechaSalida.setLayout(new BoxLayout(pnFechaSalida, BoxLayout.X_AXIS));
			pnFechaSalida.add(getHorizontalStrut_2_3());
			pnFechaSalida.add(getLbSalida());
			pnFechaSalida.add(getHorizontalGlue_3());
			pnFechaSalida.add(getCbFechaSalida());
			pnFechaSalida.add(getHorizontalStrut_5_2());
		}
		return pnFechaSalida;
	}

	private JPanel getPnPrecioFinal() {
		if (pnPrecioFinal == null) {
			pnPrecioFinal = new JPanel();
			pnPrecioFinal.setMaximumSize(new Dimension(999999, 50));
			pnPrecioFinal.setBackground(Color.DARK_GRAY);
			pnPrecioFinal.setLayout(new BoxLayout(pnPrecioFinal, BoxLayout.X_AXIS));
			pnPrecioFinal.add(getHorizontalStrut_3());
			pnPrecioFinal.add(getLbPrecioFinal());
			pnPrecioFinal.add(getHorizontalGlue_4());
			pnPrecioFinal.add(getTxPrecioFinal());
			pnPrecioFinal.add(getHorizontalStrut_7());
		}
		return pnPrecioFinal;
	}

	private JButton getBtReservar() {
		if (btReservar == null) {
			btReservar = new JButton("");
			btReservar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarVentanaParaReservar();
				}
			});
			btReservar.setAlignmentX(Component.CENTER_ALIGNMENT);
			btReservar.setFont(new Font("Arial", Font.PLAIN, 15));
		}
		return btReservar;
	}

	private JPanel getPnPrecioPorNoche() {
		if (pnPrecioPorNoche == null) {
			pnPrecioPorNoche = new JPanel();
			pnPrecioPorNoche.setBorder(new LineBorder(Color.WHITE));
			pnPrecioPorNoche.setBackground(Color.DARK_GRAY);
			pnPrecioPorNoche.add(getLbPrecioPorNoche());
			pnPrecioPorNoche.add(getLbValorPrecioPorNoche());
		}
		return pnPrecioPorNoche;
	}

	private JLabel getLbValorPrecioPorNoche() {
		if (lbValorPrecioPorNoche == null) {
			lbValorPrecioPorNoche = new JLabel("");
			lbValorPrecioPorNoche.setForeground(Color.WHITE);
			lbValorPrecioPorNoche.setFont(new Font("Arial", Font.PLAIN, 20));
			lbValorPrecioPorNoche.setText(String.format("%.2f", hotel.getPrecioHabitacion()));
		}
		return lbValorPrecioPorNoche;
	}

	private JLabel getLbHabitaciones() {
		if (lbHabitaciones == null) {
			lbHabitaciones = new JLabel("");
			lbHabitaciones.setLabelFor(getSpHabitaciones());
			lbHabitaciones.setForeground(Color.WHITE);
			lbHabitaciones.setFont(new Font("Arial", Font.PLAIN, 20));
		}
		return lbHabitaciones;
	}

	private JLabel getLbPersonas() {
		if (lbPersonas == null) {
			lbPersonas = new JLabel("");
			lbPersonas.setLabelFor(getSpPersonas());
			lbPersonas.setForeground(Color.WHITE);
			lbPersonas.setFont(new Font("Arial", Font.PLAIN, 20));
		}
		return lbPersonas;
	}

	private JLabel getLbEntrada() {
		if (lbEntrada == null) {
			lbEntrada = new JLabel("");
			lbEntrada.setLabelFor(getCbFechaEntrada());
			lbEntrada.setForeground(Color.WHITE);
			lbEntrada.setFont(new Font("Arial", Font.PLAIN, 20));
		}
		return lbEntrada;
	}

	private JLabel getLbSalida() {
		if (lbSalida == null) {
			lbSalida = new JLabel("");
			lbSalida.setLabelFor(getCbFechaSalida());
			lbSalida.setForeground(Color.WHITE);
			lbSalida.setFont(new Font("Arial", Font.PLAIN, 20));
		}
		return lbSalida;
	}

	private JLabel getLbPrecioFinal() {
		if (lbPrecioFinal == null) {
			lbPrecioFinal = new JLabel("");
			lbPrecioFinal.setForeground(Color.WHITE);
			lbPrecioFinal.setFont(new Font("Arial", Font.PLAIN, 20));
		}
		return lbPrecioFinal;
	}

	private JSpinner getSpHabitaciones() {
		if (spHabitaciones == null) {
			spHabitaciones = new JSpinner();
			spHabitaciones.setPreferredSize(new Dimension(50, 20));
			spHabitaciones.setMaximumSize(new Dimension(200, 30));
			spHabitaciones.addChangeListener(aP);
			spHabitaciones
					.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
			spHabitaciones.setFont(new Font("Arial", Font.PLAIN, 14));
		}
		return spHabitaciones;
	}

	private JSpinner getSpPersonas() {
		if (spPersonas == null) {
			spPersonas = new JSpinner();
			spPersonas.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					ponerMinimoHabitaciones();
				}
			});
			spPersonas.addChangeListener(aP);
			spPersonas.setPreferredSize(new Dimension(50, 20));
			spPersonas.setMaximumSize(new Dimension(50, 30));
			spPersonas
					.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
			spPersonas.setFont(new Font("Arial", Font.PLAIN, 14));
		}
		return spPersonas;
	}

	private JTextField getTxPrecioFinal() {
		if (txPrecioFinal == null) {
			txPrecioFinal = new JTextField();
			txPrecioFinal.setPreferredSize(new Dimension(100, 20));
			txPrecioFinal.setMaximumSize(new Dimension(150, 30));
			txPrecioFinal.setBackground(Color.DARK_GRAY);
			txPrecioFinal.setForeground(Color.WHITE);
			txPrecioFinal.setFont(new Font("Arial", Font.PLAIN, 15));
			txPrecioFinal.setEditable(false);
			txPrecioFinal.setColumns(10);
			actualizarPrecioFinal();
		}
		return txPrecioFinal;
	}

	private Component getHorizontalStrut_1() {
		if (horizontalStrut == null) {
			horizontalStrut = Box.createHorizontalStrut(20);
		}
		return horizontalStrut;
	}

	private Component getHorizontalStrut_1_1() {
		if (horizontalStrut_1 == null) {
			horizontalStrut_1 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_1;
	}

	private Component getHorizontalGlue() {
		if (horizontalGlue == null) {
			horizontalGlue = Box.createHorizontalGlue();
		}
		return horizontalGlue;
	}

	private Component getHorizontalGlue_1() {
		if (horizontalGlue_1 == null) {
			horizontalGlue_1 = Box.createHorizontalGlue();
		}
		return horizontalGlue_1;
	}

	private Component getHorizontalStrut_2() {
		if (horizontalStrut_2 == null) {
			horizontalStrut_2 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_2;
	}

	private Component getHorizontalStrut_2_1() {
		if (horizontalStrut_2_1 == null) {
			horizontalStrut_2_1 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_2_1;
	}

	private Component getHorizontalStrut_3() {
		if (horizontalStrut_3 == null) {
			horizontalStrut_3 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_3;
	}

	private Component getHorizontalStrut_5() {
		if (horizontalStrut_5 == null) {
			horizontalStrut_5 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_5;
	}

	private Component getHorizontalStrut_6() {
		if (horizontalStrut_6 == null) {
			horizontalStrut_6 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_6;
	}

	private Component getHorizontalStrut_7() {
		if (horizontalStrut_7 == null) {
			horizontalStrut_7 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_7;
	}

	private JComboBox<String> getCbFechaEntrada() {
		if (cbFechaEntrada == null) {
			cbFechaEntrada = new JComboBox<String>();
			cbFechaEntrada.setPreferredSize(new Dimension(80, 22));
			cbFechaEntrada.setMaximumSize(new Dimension(150, 30));
			cbFechaEntrada.addItemListener(aPC);
			cbFechaEntrada.addItemListener(aCS);
		}
		return cbFechaEntrada;
	}

	private JComboBox<String> getCbFechaSalida() {
		if (cbFechaSalida == null) {
			cbFechaSalida = new JComboBox<String>();
			cbFechaSalida.setPreferredSize(new Dimension(80, 22));
			cbFechaSalida.setMaximumSize(new Dimension(150, 30));
			cbFechaSalida.addItemListener(aPC);
		}
		return cbFechaSalida;
	}

	private Component getHorizontalGlue_2() {
		if (horizontalGlue_2 == null) {
			horizontalGlue_2 = Box.createHorizontalGlue();
		}
		return horizontalGlue_2;
	}

	private Component getHorizontalGlue_3() {
		if (horizontalGlue_3 == null) {
			horizontalGlue_3 = Box.createHorizontalGlue();
		}
		return horizontalGlue_3;
	}

	private Component getHorizontalStrut_2_2() {
		if (horizontalStrut_2_2 == null) {
			horizontalStrut_2_2 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_2_2;
	}

	private Component getHorizontalStrut_2_3() {
		if (horizontalStrut_2_3 == null) {
			horizontalStrut_2_3 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_2_3;
	}
	
	private Component getHorizontalStrut_5_1() {
		if (horizontalStrut_5_1 == null) {
			horizontalStrut_5_1 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_5_1;
	}
	private Component getHorizontalStrut_5_2() {
		if (horizontalStrut_5_2 == null) {
			horizontalStrut_5_2 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_5_2;
	}
	private Component getHorizontalGlue_4() {
		if (horizontalGlue_4 == null) {
			horizontalGlue_4 = Box.createHorizontalGlue();
		}
		return horizontalGlue_4;
	}

	/**
	 * Adapta el tama�o de la imagen del hotel al tama�o del panel que lo contiene
	 * 
	 * @param label      en la que se ubicar� foto del hotel que se quiere
	 *                   redimensionar
	 * @param rutaImagen nombre de la imagen
	 */
	private void setImagenAdaptada(JLabel label, String rutaImagen) {
		Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
		Image imgEscalada = imgOriginal.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_FAST);
		ImageIcon icon = new ImageIcon(imgEscalada);
		label.setIcon(icon);
	}

	/**
	 * Adapta el tama�o de la imagen del hotel al tama�o del panel que lo contiene
	 * 
	 * @param label      en la que se ubicar� foto del hotel que se quiere
	 *                   redimensionar
	 * @param rutaImagen nombre de la imagen
	 */
	private void setImagenAdaptada(JLabel label, String rutaImagen, int width, int height) {
		Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
		Image imgEscalada = imgOriginal.getScaledInstance(width, height, Image.SCALE_FAST);
		ImageIcon icon = new ImageIcon(imgEscalada);
		label.setIcon(icon);
	}

	private void localizar() {

		ResourceBundle textos = ResourceBundle.getBundle("rcs/textos", vp.getUbicacion());

		ponerTextosLocalizados(textos);
		rellenarTxEncantamientosYHabitaciones(textos);
		rellenarCheckBoxes(textos);

	}

	private void ponerTextosLocalizados(ResourceBundle textos) {
		getLbInfoMaxPersonasHabitacion().setText(textos.getString("maxPersonasHabitacion"));
		getBtReservar().setText(textos.getString("reservar"));
		getBtReservar().setMnemonic(textos.getString("mnemonicReservar").charAt(0));
		getLbReserva().setText(textos.getString("reserva"));
		getLbPrecioPorNoche().setText(textos.getString("precio"));
		getLbHabitaciones().setText(textos.getString("habitaciones"));
		getLbHabitaciones().setDisplayedMnemonic(textos.getString("mnemonicHabitaciones").charAt(0));
		getLbPersonas().setText(textos.getString("personas"));
		getLbPersonas().setDisplayedMnemonic(textos.getString("mnemonicPersonas").charAt(0));
		getLbEntrada().setText(textos.getString("entrada"));
		getLbEntrada().setDisplayedMnemonic(textos.getString("mnemonicEntrada").charAt(0));
		getLbSalida().setText(textos.getString("salida"));
		getLbSalida().setDisplayedMnemonic(textos.getString("mnemonicSalida").charAt(0));
		getLbPrecioFinal().setText(textos.getString("precio"));
	}

	private void rellenarTxEncantamientosYHabitaciones(ResourceBundle textos) {
		StringBuilder sb = new StringBuilder();
		// Ponemos el pa�s
		sb.append(textos.getString("pais"));
		sb.append(" " + hotel.getPais());

		sb.append("\n");

		// Ponemos los encantamientos
		sb.append(textos.getString("encantamientos2"));
		for (int i = 0; i < hotel.getNumeroEncantamientos(); i++) {
			if (i == hotel.getNumeroEncantamientos() - 1)
				sb.append(" " + textos.getString(hotel.getEncantamiento(i).getDiminutivo()));

			sb.append(" " + textos.getString(hotel.getEncantamiento(i).getDiminutivo()) + ",");
		}

		getTxEncantamientosYHabitaciones().setText(sb.toString());
	}

	/**
	 * Rellena las checkboxes de fechas de entrada y salida con los formatos correctos segun la localizaci�n
	 * @param textos
	 */
	private void rellenarCheckBoxes(ResourceBundle textos) {
		DateFormat formatoFecha = DateFormat.getDateInstance(DateFormat.SHORT, vp.getUbicacion());
		String[] fechasEntrada = new String[365];
		String[] fechasSalida = new String[365];
		Date fechaActual = new Date();
		
		// Creamos el modelo para el combo de salida empezando un d�a despues que el de entrada
		for (int i = 0; i < 365; i++) {
			fechasEntrada[i] = formatoFecha.format(fechaActual);
			fechasSalida[i] = formatoFecha.format(fechaActual.getTime() + 24 * 3600 * 1000);
			// A�adimos a la fecha actual un d�a en milisegundos
			fechaActual.setTime(fechaActual.getTime() + 24 * 3600 * 1000);
		}
		
		
		getCbFechaEntrada().setModel(new DefaultComboBoxModel<>(fechasEntrada));
		getCbFechaSalida().setModel(new DefaultComboBoxModel<>(fechasSalida));
	}
	
	/**
	 * Actualiza el combo de fechas de salida para que la primera fecha disponible que muestre sea la 
	 * del d�a siguiente a la seleccionada por el de entrada
	 */
	private void actualizarCbFechaSalida() {
		DateFormat formatoFecha = DateFormat.getDateInstance(DateFormat.SHORT, vp.getUbicacion());
		String fechaSeleccionadaString = (String) getCbFechaEntrada().getSelectedItem();
		
		if (calcularDiasSeleccionados() > 0)
			return;
		
		try {
			Date fechaSeleccionadaEntrada = formatoFecha.parse(fechaSeleccionadaString);
			Date fechaSalida = new Date();
			fechaSalida.setTime(fechaSeleccionadaEntrada.getTime() + 24 * 3600 * 1000);
			
			String[] fechas = new String[365];
			// A�adimos 100 d�as m�s (M�ximo tiempo de reserva)
			for (int i = 0; i < 100; i++) {
				fechas[i] = formatoFecha.format(fechaSalida);
				// A�adimos a la fecha actual un d�a en milisegundos
				fechaSalida.setTime(fechaSalida.getTime() + 24 * 3600 * 1000);
			}
			getCbFechaSalida().setModel(new DefaultComboBoxModel<>(fechas));
			
		} catch (ParseException e) {
			throw new IllegalArgumentException("El formato de fecha recibido era incorrecto, se han formateado mal las fechas");
		}
		
	}
	
	private int calcularDiasSeleccionados() {
		DateFormat formatoFecha = DateFormat.getDateInstance(DateFormat.SHORT, vp.getUbicacion());
		String fechaEntradaString = (String) getCbFechaEntrada().getSelectedItem();
		String fechaSalidaString = (String) getCbFechaSalida().getSelectedItem();
		int diasSeleccionados = 0;
		
		// Si aun no se ha rellenado el combo
		if (fechaEntradaString == null)
			return 1;
		
		try {
			Date fechaSeleccionadaEntrada = formatoFecha.parse(fechaEntradaString);
			Date fechaSeleccionadaSalida = formatoFecha.parse(fechaSalidaString);
			int diaSalida = (int) (fechaSeleccionadaSalida.getTime()/(24*3600*1000));
			int diaEntrada = (int) (fechaSeleccionadaEntrada.getTime()/(24*3600*1000));
			
			diasSeleccionados = diaSalida - diaEntrada;
			
		} catch (ParseException e) {
			throw new IllegalArgumentException("El formato de fecha recibido era incorrecto, se han formateado mal las fechas");
		}
		
		return diasSeleccionados;
	}

	/**
	 * Genera las 4 fotos de las habitaciones del hotel
	 */
	private void generarFotoHabitaciones() {
		for (int i = 0; i < 4; i++) {
			JLabel lb = new JLabel();
			lb.setSize(new Dimension(500, 500));
			lb.setMaximumSize(new Dimension(500, 500));
			setImagenAdaptada(lb, "/img/habitacion" + i + ".jpg", 200, 200);

			getPnFotosHabitaciones().add(lb);
		}
	}
	
	/**
	 * Modifica el spinner de habitaciones en funci�n del spinner de personas haciendo que para cada habitaci�n hayan un m�ximo de 2 personas
	 */
	private void ponerMinimoHabitaciones() {
		int valorMinimoHabitaciones = app.getNumeroMinimoDeHabitaciones((int) getSpPersonas().getValue());
		int valorActualSp = (int) getSpHabitaciones().getValue();

		if (valorActualSp < valorMinimoHabitaciones) {
			getSpHabitaciones().setValue(valorMinimoHabitaciones);
		}
		getSpHabitaciones().setModel(new SpinnerNumberModel(Integer.valueOf((int) getSpHabitaciones().getValue()),
				Integer.valueOf(valorMinimoHabitaciones), null, Integer.valueOf(1)));
	}

	/**
	 * Actualiza el precio final que se le muestra al usuario
	 */
	private void actualizarPrecioFinal() {
		int habitaciones = (int) getSpHabitaciones().getValue();
		int numDias = calcularDiasSeleccionados();
		
		this.numeroHabitaciones = habitaciones;
		this.numeroDias = numDias;

		double precioFinal = app.calcularPrecioReserva(habitaciones, numDias, hotel);
		getTxPrecioFinal().setText(String.format("%.2f�", precioFinal));

	}

	private class ActualizarPrecio implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			actualizarPrecioFinal();
		}
	}
	
	private class ActualizarPrecioParaCb implements ItemListener {


		@Override
		public void itemStateChanged(ItemEvent e) {
			actualizarPrecioFinal();
		}
	}
	
	private class ActualizarCbSalida implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			actualizarCbFechaSalida();
		}
		
	}
	
	private void mostrarVentanaParaReservar() {
		//TODO Generar un JDialog 
	}

	private JPanel getPnLbReserva() {
		if (pnLbReserva == null) {
			pnLbReserva = new JPanel();
			pnLbReserva.setBackground(Color.DARK_GRAY);
			pnLbReserva.add(getLbReserva());
		}
		return pnLbReserva;
	}
}
