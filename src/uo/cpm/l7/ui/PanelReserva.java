package uo.cpm.l7.ui;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;

import uo.cpm.l7.model.Hotel.Reserva;
import uo.cpm.l7.service.Horrotel;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelReserva extends JPanel {


	private static final long serialVersionUID = 1L;
	private VentanaPrincipal vp;
	private Horrotel app;
	private Reserva reserva;
	private VentanaMisReservas vMR;
	private JPanel pnNombreCastillo;
	private JLabel lbNombreCastillo;
	private JPanel pnInfoReserva;
	private JPanel pnHabitaciones;
	private JPanel pnFechaEntrada;
	private JPanel pnNombreYApellidos;
	private JPanel pnNumDias;
	private JLabel lbNumHabitaciones;
	private JLabel lbValorHabitaciones;
	private JLabel lbFechaEntrada;
	private JLabel lbValorFechaEntrada;
	private JLabel lbNombrePersona;
	private JLabel lbValorNombre;
	private JLabel lbNumDias;
	private JLabel lbValorNumDias;
	private JPanel pnInferior;
	private JPanel pnPrecio;
	private JLabel lbPrecio;
	private JLabel lbValorPrecio;
	private JButton btCancelarReserva;
	private Component separadorEntrePrecioYBtCancelar;

	/**
	 * Create the panel.
	 */
	public PanelReserva(VentanaPrincipal vp, Horrotel app, Reserva reserva, VentanaMisReservas vMR) {
		this.vp = vp;
		this.app = app;
		this.reserva = reserva;
		this.vMR = vMR;
		setBorder(new LineBorder(Color.WHITE));
		setBackground(Color.DARK_GRAY);
		setLayout(new BorderLayout(0, 0));
		add(getPnNombreCastillo(), BorderLayout.NORTH);
		add(getPnInfoReserva(), BorderLayout.CENTER);
		add(getPnInferior(), BorderLayout.SOUTH);
		
		
		localizar();
	}

	private JPanel getPnNombreCastillo() {
		if (pnNombreCastillo == null) {
			pnNombreCastillo = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnNombreCastillo.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnNombreCastillo.setBackground(Color.DARK_GRAY);
			pnNombreCastillo.add(getLbNombreCastillo());
		}
		return pnNombreCastillo;
	}
	private JLabel getLbNombreCastillo() {
		if (lbNombreCastillo == null) {
			lbNombreCastillo = new JLabel("");
			lbNombreCastillo.setForeground(Color.WHITE);
			lbNombreCastillo.setFont(new Font("Arial", Font.PLAIN, 30));
			if (vp.getUbicacion().getLanguage().equals("es"))
				lbNombreCastillo.setText(app.getCastilloPorCodigoES(reserva.getCodigo()));
			else
				lbNombreCastillo.setText(app.getCastilloPorCodigoEN(reserva.getCodigo()));
		}
		return lbNombreCastillo;
	}
	private JPanel getPnInfoReserva() {
		if (pnInfoReserva == null) {
			pnInfoReserva = new JPanel();
			pnInfoReserva.setBackground(Color.DARK_GRAY);
			pnInfoReserva.setLayout(new GridLayout(2, 2, 0, 0));
			pnInfoReserva.add(getPnHabitaciones());
			pnInfoReserva.add(getPnFechaEntrada());
			pnInfoReserva.add(getPnNombreYApellidos());
			pnInfoReserva.add(getPnNumDias());
		}
		return pnInfoReserva;
	}
	private JPanel getPnHabitaciones() {
		if (pnHabitaciones == null) {
			pnHabitaciones = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnHabitaciones.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnHabitaciones.setBackground(Color.DARK_GRAY);
			pnHabitaciones.add(getLbNumHabitaciones());
			pnHabitaciones.add(getLbValorHabitaciones());
		}
		return pnHabitaciones;
	}
	private JPanel getPnFechaEntrada() {
		if (pnFechaEntrada == null) {
			pnFechaEntrada = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnFechaEntrada.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnFechaEntrada.setBackground(Color.DARK_GRAY);
			pnFechaEntrada.add(getLbFechaEntrada());
			pnFechaEntrada.add(getLbValorFechaEntrada());
		}
		return pnFechaEntrada;
	}
	private JPanel getPnNombreYApellidos() {
		if (pnNombreYApellidos == null) {
			pnNombreYApellidos = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnNombreYApellidos.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnNombreYApellidos.setBackground(Color.DARK_GRAY);
			pnNombreYApellidos.add(getLbNombrePersona());
			pnNombreYApellidos.add(getLbValorNombre());
		}
		return pnNombreYApellidos;
	}
	private JPanel getPnNumDias() {
		if (pnNumDias == null) {
			pnNumDias = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnNumDias.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnNumDias.setBackground(Color.DARK_GRAY);
			pnNumDias.add(getLbNumDias());
			pnNumDias.add(getLbValorNumDias());
		}
		return pnNumDias;
	}
	private JLabel getLbNumHabitaciones() {
		if (lbNumHabitaciones == null) {
			lbNumHabitaciones = new JLabel("");
			lbNumHabitaciones.setForeground(Color.WHITE);
			lbNumHabitaciones.setFont(new Font("Arial", Font.PLAIN, 20));
		}
		return lbNumHabitaciones;
	}
	private JLabel getLbValorHabitaciones() {
		if (lbValorHabitaciones == null) {
			lbValorHabitaciones = new JLabel("");
			lbValorHabitaciones.setForeground(Color.WHITE);
			lbValorHabitaciones.setFont(new Font("Arial", Font.PLAIN, 20));
			lbValorHabitaciones.setText(reserva.getNumHabitaciones() + "");
		}
		return lbValorHabitaciones;
	}
	private JLabel getLbFechaEntrada() {
		if (lbFechaEntrada == null) {
			lbFechaEntrada = new JLabel("");
			lbFechaEntrada.setForeground(Color.WHITE);
			lbFechaEntrada.setFont(new Font("Arial", Font.PLAIN, 20));
		}
		return lbFechaEntrada;
	}
	private JLabel getLbValorFechaEntrada() {
		if (lbValorFechaEntrada == null) {
			lbValorFechaEntrada = new JLabel("");
			lbValorFechaEntrada.setForeground(Color.WHITE);
			lbValorFechaEntrada.setFont(new Font("Arial", Font.PLAIN, 20));
			lbValorFechaEntrada.setText(reserva.getFechaReserva());
		}
		return lbValorFechaEntrada;
	}
	private JLabel getLbNombrePersona() {
		if (lbNombrePersona == null) {
			lbNombrePersona = new JLabel("");
			lbNombrePersona.setForeground(Color.WHITE);
			lbNombrePersona.setFont(new Font("Arial", Font.PLAIN, 20));
		}
		return lbNombrePersona;
	}
	private JLabel getLbValorNombre() {
		if (lbValorNombre == null) {
			lbValorNombre = new JLabel("");
			lbValorNombre.setForeground(Color.WHITE);
			lbValorNombre.setFont(new Font("Arial", Font.PLAIN, 20));
			lbValorNombre.setText(reserva.getNombreYApellidos());
		}
		return lbValorNombre;
	}
	private JLabel getLbNumDias() {
		if (lbNumDias == null) {
			lbNumDias = new JLabel("");
			lbNumDias.setForeground(Color.WHITE);
			lbNumDias.setFont(new Font("Arial", Font.PLAIN, 20));
		}
		return lbNumDias;
	}
	private JLabel getLbValorNumDias() {
		if (lbValorNumDias == null) {
			lbValorNumDias = new JLabel("");
			lbValorNumDias.setForeground(Color.WHITE);
			lbValorNumDias.setFont(new Font("Arial", Font.PLAIN, 20));
			lbValorNumDias.setText(reserva.getDias() + "");
		}
		return lbValorNumDias;
	}
	private JPanel getPnInferior() {
		if (pnInferior == null) {
			pnInferior = new JPanel();
			pnInferior.setBackground(Color.DARK_GRAY);
			pnInferior.setLayout(new BoxLayout(pnInferior, BoxLayout.X_AXIS));
			pnInferior.add(getPnPrecio());
			pnInferior.add(getSeparadorEntrePrecioYBtCancelar());
			pnInferior.add(getBtCancelarReserva());
		}
		return pnInferior;
	}
	private JPanel getPnPrecio() {
		if (pnPrecio == null) {
			pnPrecio = new JPanel();
			pnPrecio.setMaximumSize(new Dimension(400, 32767));
			pnPrecio.setBackground(Color.DARK_GRAY);
			pnPrecio.add(getLbPrecio());
			pnPrecio.add(getLbValorPrecio());
		}
		return pnPrecio;
	}
	private JLabel getLbPrecio() {
		if (lbPrecio == null) {
			lbPrecio = new JLabel("");
			lbPrecio.setForeground(Color.WHITE);
			lbPrecio.setFont(new Font("Arial", Font.PLAIN, 25));
		}
		return lbPrecio;
	}
	private JLabel getLbValorPrecio() {
		if (lbValorPrecio == null) {
			lbValorPrecio = new JLabel("");
			lbValorPrecio.setForeground(Color.WHITE);
			lbValorPrecio.setFont(new Font("Arial", Font.PLAIN, 25));
			lbValorPrecio.setText(String.format("%.2f€", reserva.getPrecioFinal()));
		}
		return lbValorPrecio;
	}
	private JButton getBtCancelarReserva() {
		if (btCancelarReserva == null) {
			btCancelarReserva = new JButton("");
			btCancelarReserva.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarCartelAviso();
				}
			});
			btCancelarReserva.setFont(new Font("Arial", Font.PLAIN, 20));
		}
		return btCancelarReserva;
	}
	
	private Component getSeparadorEntrePrecioYBtCancelar() {
		if (separadorEntrePrecioYBtCancelar == null) {
			separadorEntrePrecioYBtCancelar = Box.createHorizontalGlue();
		}
		return separadorEntrePrecioYBtCancelar;
	}
	
	private void localizar() {
		ResourceBundle textos = ResourceBundle.getBundle("rcs/textos", vp.getUbicacion());
		
		getLbNumHabitaciones().setText(textos.getString("nHabitaciones"));
		getLbNombrePersona().setText(textos.getString("nombreYApellidos"));
		getLbFechaEntrada().setText(textos.getString("fEntrada"));
		getLbNumDias().setText(textos.getString("dias"));
		getLbPrecio().setText(textos.getString("precio"));
		getBtCancelarReserva().setText(textos.getString("cancelarReserva"));
		getBtCancelarReserva().setMnemonic(textos.getString("mnemonicCancelar").charAt(0));
	}

	
	private void mostrarCartelAviso() {
		ResourceBundle textos = ResourceBundle.getBundle("rcs/textos", vp.getUbicacion());

		String[] opciones = { textos.getString("opcionSi"), textos.getString("opcionNo") };

		// JOptionPane internacionalizado
		int seleccion = JOptionPane.showOptionDialog(this, textos.getString("textoCancelarReserva"),
				textos.getString("cancelarReserva"), 2, JOptionPane.QUESTION_MESSAGE, null, opciones,
				textos.getString("opcionNo"));
		if (seleccion == JOptionPane.YES_OPTION) {
			app.cancelarReserva(this.reserva);
			vMR.crearPanelesReserva();
		}
	}
}
