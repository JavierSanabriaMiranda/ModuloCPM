package uo.cpm.l7.ui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import uo.cpm.l7.model.Hotel.Hotel;
import uo.cpm.l7.service.Horrotel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.net.URL;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.BoxLayout;
import java.awt.Toolkit;

public class VentanaReserva extends JDialog {

	private static final long serialVersionUID = 1L;
	private Locale ubicacion;
	private int habitaciones;
	private int personas;
	private String fechaEntrada;
	private String fechaSalida;
	private int numDias;
	private double precio;
	private double precioConDescuento;
	private Hotel hotel;
	private Horrotel app;
	private VentanaPrincipal vp;
	private JPanel pnDatosReserva;
	private JLabel lbDatosReserva;
	private JTextPane txDatosReserva;
	private JLabel lbDNI;
	private JLabel lbNombre;
	private JLabel lbCorreo;
	private JLabel lbComentarios;
	private JLabel lbPrecio;
	private JTextField txPrecio;
	private JCheckBox chBxAplicarDescuento;
	private JButton btAtras;
	private JButton btConfirmarReserva;
	private JTextField txDNI;
	private JTextField txNombre;
	private JTextField txCorreo;
	private JTextPane txComentarios;
	private JPanel pnDescuento;
	private JLabel lbValorDescuento;
	// Configuramos los atributos que vamos a poner al texto del JTextPane (Texto
	// centrado)
	private SimpleAttributeSet centrarTexto = new SimpleAttributeSet();
	private AplicarDescuento aD = new AplicarDescuento();
	private PermitirReservar pR = new PermitirReservar();
	private RealizarReserva rR = new RealizarReserva();
	private ComprobarDescuento cD = new ComprobarDescuento();

	/**
	 * Create the dialog.
	 */
	public VentanaReserva(Locale ubicacion, int habitaciones, int personas, String fechaEntrada, String fechaSalida,
			int numDias, double precioFinal, Hotel hotel, Horrotel app, VentanaPrincipal vp) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaReserva.class.getResource("/img/logo.png")));
		this.ubicacion = ubicacion;
		this.habitaciones = habitaciones;
		this.personas = personas;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.numDias = numDias;
		this.precio = precioFinal;
		this.hotel = hotel;
		this.app = app;
		this.vp = vp;
		
		// Configuramos los atributos que vamos a poner al texto del JTextPane (Texto
		// centrado)
		StyleConstants.setAlignment(centrarTexto, StyleConstants.ALIGN_CENTER);

		getContentPane().setBackground(Color.DARK_GRAY);
		setBackground(Color.DARK_GRAY);
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 719, 719);
		getContentPane().setLayout(null);
		getContentPane().add(getPnDatosReserva());
		getContentPane().add(getLbDNI());
		getContentPane().add(getLbNombre());
		getContentPane().add(getLbCorreo());
		getContentPane().add(getLbComentarios());
		getContentPane().add(getLbPrecio());
		getContentPane().add(getTxPrecio());
		getContentPane().add(getBtAtras());
		getContentPane().add(getBtConfirmarReserva());
		getContentPane().add(getTxDNI());
		getContentPane().add(getTxNombre());
		getContentPane().add(getTxCorreo());
		getContentPane().add(getTxComentarios());
		getContentPane().add(getPnDescuento());

		localizar();
		cargaAyuda();
	}

	private JPanel getPnDatosReserva() {
		if (pnDatosReserva == null) {
			pnDatosReserva = new JPanel();
			pnDatosReserva.setBackground(Color.DARK_GRAY);
			pnDatosReserva.setBounds(184, 11, 334, 172);
			pnDatosReserva.setLayout(new BorderLayout(0, 0));
			pnDatosReserva.add(getLbDatosReserva(), BorderLayout.NORTH);
			pnDatosReserva.add(getTxDatosReserva());
		}
		return pnDatosReserva;
	}

	private JLabel getLbDatosReserva() {
		if (lbDatosReserva == null) {
			lbDatosReserva = new JLabel("");
			lbDatosReserva.setHorizontalAlignment(SwingConstants.CENTER);
			lbDatosReserva.setForeground(Color.WHITE);
			lbDatosReserva.setFont(new Font("Arial", Font.BOLD, 33));
		}
		return lbDatosReserva;
	}

	private JTextPane getTxDatosReserva() {
		if (txDatosReserva == null) {
			txDatosReserva = new JTextPane();
			txDatosReserva.setEditable(false);
			txDatosReserva.setFont(new Font("Arial", Font.PLAIN, 20));
			txDatosReserva.setBackground(Color.DARK_GRAY);
			txDatosReserva.setForeground(Color.WHITE);

			// Cogemos el documento de estilo del JTextPane
			StyledDocument doc = txDatosReserva.getStyledDocument();

			// Aplicamos los atributos al documento
			doc.setParagraphAttributes(0, doc.getLength(), centrarTexto, false);
		}
		return txDatosReserva;
	}

	private JLabel getLbDNI() {
		if (lbDNI == null) {
			lbDNI = new JLabel("");
			lbDNI.setLabelFor(getTxDNI());
			lbDNI.setForeground(Color.WHITE);
			lbDNI.setFont(new Font("Arial", Font.PLAIN, 23));
			lbDNI.setBounds(52, 212, 277, 49);
		}
		return lbDNI;
	}

	private JLabel getLbNombre() {
		if (lbNombre == null) {
			lbNombre = new JLabel("");
			lbNombre.setLabelFor(getTxNombre());
			lbNombre.setForeground(Color.WHITE);
			lbNombre.setFont(new Font("Arial", Font.PLAIN, 23));
			lbNombre.setBounds(52, 282, 277, 49);
		}
		return lbNombre;
	}

	private JLabel getLbCorreo() {
		if (lbCorreo == null) {
			lbCorreo = new JLabel("");
			lbCorreo.setLabelFor(getTxCorreo());
			lbCorreo.setForeground(Color.WHITE);
			lbCorreo.setFont(new Font("Arial", Font.PLAIN, 23));
			lbCorreo.setBounds(52, 354, 277, 49);
		}
		return lbCorreo;
	}

	private JLabel getLbComentarios() {
		if (lbComentarios == null) {
			lbComentarios = new JLabel("");
			lbComentarios.setLabelFor(getTxComentarios());
			lbComentarios.setForeground(Color.WHITE);
			lbComentarios.setFont(new Font("Arial", Font.PLAIN, 23));
			lbComentarios.setBounds(52, 430, 277, 49);
		}
		return lbComentarios;
	}

	private JLabel getLbPrecio() {
		if (lbPrecio == null) {
			lbPrecio = new JLabel("");
			lbPrecio.setForeground(Color.WHITE);
			lbPrecio.setFont(new Font("Arial", Font.PLAIN, 23));
			lbPrecio.setBounds(52, 530, 122, 49);
		}
		return lbPrecio;
	}

	private JTextField getTxPrecio() {
		if (txPrecio == null) {
			txPrecio = new JTextField();
			txPrecio.setEditable(false);
			txPrecio.setFont(new Font("Arial", Font.PLAIN, 15));
			txPrecio.setBounds(168, 532, 154, 49);
			txPrecio.setColumns(10);
			txPrecio.setText(String.format("%.2f€", this.precio));
		}
		return txPrecio;
	}

	private JCheckBox getChBxAplicarDescuento() {
		if (chBxAplicarDescuento == null) {
			chBxAplicarDescuento = new JCheckBox("");
			chBxAplicarDescuento.setBackground(Color.DARK_GRAY);
			chBxAplicarDescuento.setForeground(Color.WHITE);
			chBxAplicarDescuento.setFont(new Font("Arial", Font.PLAIN, 20));
			chBxAplicarDescuento.setVisible(false);
			chBxAplicarDescuento.addActionListener(aD);
		}
		return chBxAplicarDescuento;
	}

	private JButton getBtAtras() {
		if (btAtras == null) {
			btAtras = new JButton("");
			btAtras.setFont(new Font("Arial", Font.PLAIN, 15));
			btAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btAtras.setBounds(323, 608, 132, 49);
		}
		return btAtras;
	}

	private JButton getBtConfirmarReserva() {
		if (btConfirmarReserva == null) {
			btConfirmarReserva = new JButton("");
			btConfirmarReserva.setEnabled(false);
			btConfirmarReserva.setFont(new Font("Arial", Font.PLAIN, 15));
			btConfirmarReserva.setBounds(481, 608, 196, 49);
			btConfirmarReserva.addActionListener(rR);
		}
		return btConfirmarReserva;
	}

	private JTextField getTxDNI() {
		if (txDNI == null) {
			txDNI = new JTextField();
			txDNI.setFont(new Font("Arial", Font.PLAIN, 15));
			txDNI.setColumns(10);
			txDNI.setBounds(323, 216, 293, 40);
			// Detecta modificaciones en el documento del JTextField (Modificaciones en el
			// texto del JTextField)
			txDNI.getDocument().addDocumentListener(pR);
			txDNI.getDocument().addDocumentListener(cD);
		}
		return txDNI;
	}

	private JTextField getTxNombre() {
		if (txNombre == null) {
			txNombre = new JTextField();
			txNombre.setFont(new Font("Arial", Font.PLAIN, 15));
			txNombre.setColumns(10);
			txNombre.setBounds(323, 286, 293, 40);
			// Detecta modificaciones en el documento del JTextField (Modificaciones en el
			// texto del JTextField)
			txNombre.getDocument().addDocumentListener(pR);
		}
		return txNombre;
	}

	private JTextField getTxCorreo() {
		if (txCorreo == null) {
			txCorreo = new JTextField();
			txCorreo.setFont(new Font("Arial", Font.PLAIN, 15));
			txCorreo.setColumns(10);
			txCorreo.setBounds(323, 358, 293, 40);
			// Detecta modificaciones en el documento del JTextField (Modificaciones en el
			// texto del JTextField)
			txCorreo.getDocument().addDocumentListener(pR);
		}
		return txCorreo;
	}

	private JTextPane getTxComentarios() {
		if (txComentarios == null) {
			txComentarios = new JTextPane();
			txComentarios.setFont(new Font("Arial", Font.PLAIN, 15));
			txComentarios.setBounds(323, 414, 293, 81);
		}
		return txComentarios;
	}

	private JPanel getPnDescuento() {
		if (pnDescuento == null) {
			pnDescuento = new JPanel();
			pnDescuento.setBackground(Color.DARK_GRAY);
			pnDescuento.setBounds(344, 519, 272, 70);
			pnDescuento.setLayout(new BoxLayout(pnDescuento, BoxLayout.X_AXIS));
			pnDescuento.add(getChBxAplicarDescuento());
			pnDescuento.add(getLbValorDescuento());
		}
		return pnDescuento;
	}

	private JLabel getLbValorDescuento() {
		if (lbValorDescuento == null) {
			lbValorDescuento = new JLabel();
			lbValorDescuento.setForeground(Color.WHITE);
			lbValorDescuento.setFont(new Font("Arial", Font.PLAIN, 20));
			lbValorDescuento.setVisible(false);
		}
		return lbValorDescuento;
	}

	private void localizar() {
		ResourceBundle textos = ResourceBundle.getBundle("rcs/textos", this.ubicacion);

		setTitle(textos.getString("tituloReserva"));
		
		establecerTextosYMnemonics(textos);
		establecerTextoDatosReserva(textos);
	}

	private void establecerTextosYMnemonics(ResourceBundle textos) {
		getLbDatosReserva().setText(textos.getString("datosReserva"));
		getLbDNI().setText(textos.getString("introDNI"));
		getLbDNI().setDisplayedMnemonic(textos.getString("mnemonicIntroDNI").charAt(0));
		getLbNombre().setText(textos.getString("nombreYApellidos"));
		getLbNombre().setDisplayedMnemonic(textos.getString("mnemonicNombreYApellidos").charAt(0));
		getLbCorreo().setText(textos.getString("correo"));
		getLbCorreo().setDisplayedMnemonic(textos.getString("mnemonicCorreo").charAt(0));
		getLbComentarios().setText(textos.getString("comentarios"));
		getLbComentarios().setDisplayedMnemonic(textos.getString("mnemonicComentarios").charAt(0));
		getLbPrecio().setText(textos.getString("precio"));
		getBtAtras().setText(textos.getString("botonAtras"));
		getBtAtras().setMnemonic(textos.getString("botonAtrasMnemonic").charAt(0));
		getBtConfirmarReserva().setText(textos.getString("confirmarReserva"));
		getBtConfirmarReserva().setMnemonic(textos.getString("mnemonicConfirmarReserva").charAt(0));
		getChBxAplicarDescuento().setText(textos.getString("aplicarDescuento"));
		getChBxAplicarDescuento().setMnemonic(textos.getString("mnemonicAplicarDescuento").charAt(0));
	}

	private void establecerTextoDatosReserva(ResourceBundle textos) {
		StringBuilder sb = new StringBuilder();

		sb.append(textos.getString("castillo"));
		sb.append(hotel.getDenominacion() + "\n");
		sb.append(textos.getString("nHabitaciones"));
		sb.append(this.habitaciones + "\n");
		sb.append(textos.getString("nPersonas"));
		sb.append(this.personas + "\n");
		sb.append(textos.getString("fEntrada"));
		sb.append(this.fechaEntrada + "\n");
		sb.append(textos.getString("fSalida"));
		sb.append(this.fechaSalida + "\n");

		getTxDatosReserva().setText(sb.toString());
	}

	private void comprobarDescuentoDNI() {
		String dniIntroducido = getTxDNI().getText();

		// Si el DNI tiene descuento
		if (app.isDNIGuardado(dniIntroducido)) {
			getChBxAplicarDescuento().setVisible(true);
			getLbValorDescuento().setText(app.getDescuento(dniIntroducido) + "%");
			getLbValorDescuento().setVisible(true);
		}
		// Si el DNI no tiene descuento
		else {
			// Se oculta y desmarca el checkBox
			getChBxAplicarDescuento().setVisible(false);
			getChBxAplicarDescuento().setSelected(false);
			// Se resetea el precio
			this.precioConDescuento = this.precio;
			getTxPrecio().setText(String.format("%.2f€", this.precio));
			// Se oculta el porcentaje de descuento
			getLbValorDescuento().setVisible(false);
		}
	}

	private class AplicarDescuento implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JCheckBox fuente = (JCheckBox) e.getSource();

			if (fuente.isSelected())
				aplicarDescuento();
			else {
				getTxPrecio().setText(String.format("%.2f€", precio));
				precioConDescuento = precio;
			}
		}

	}

	private void aplicarDescuento() {
		String dniIntroducido = getTxDNI().getText();
		int descuento = app.getDescuento(dniIntroducido);

		double precioSinDescuento = precio;
		double precioConDescuento = precioSinDescuento * (1 - descuento / 100.0);

		getTxPrecio().setText(String.format("%.2f€", precioConDescuento));

		this.precioConDescuento = precioConDescuento;
	}

	private class PermitirReservar implements DocumentListener {

		@Override
		public void insertUpdate(DocumentEvent e) {
			comprobarSiSePuedePermitirReserva();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			comprobarSiSePuedePermitirReserva();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			comprobarSiSePuedePermitirReserva();
		}

	}
	
	private class ComprobarDescuento implements DocumentListener {

		@Override
		public void insertUpdate(DocumentEvent e) {
			comprobarDescuentoDNI();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			comprobarDescuentoDNI();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			comprobarDescuentoDNI();
		}

	}
	
	private class RealizarReserva implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			realizarReserva();
		}
		
	}

	private void comprobarSiSePuedePermitirReserva() {
		// Si todos contienen texto
		if (!getTxDNI().getText().isBlank() && !getTxNombre().getText().isBlank() && !getTxCorreo().getText().isBlank())
			getBtConfirmarReserva().setEnabled(true);
		else
			getBtConfirmarReserva().setEnabled(false);
	}
	
	/**
	 * Realiza la reserva y la almacena con todos los datos de la misma
	 */
	private void realizarReserva() {
		// Lógica
		String DNI = getTxDNI().getText();
		String nomYApell = getTxNombre().getText();
		String correo = getTxCorreo().getText();
		String codigo = hotel.getCodigo();
		String comentarios = getTxComentarios().getText();
		
		app.generarReserva(DNI, nomYApell, correo, codigo, this.fechaEntrada, this.numDias, this.habitaciones, this.precioConDescuento, comentarios);
		
		if (this.precioConDescuento < this.precio)
			app.borrarDescuento(DNI);
		
		
		// Interfaz
		ResourceBundle textos = ResourceBundle.getBundle("rcs/textos", this.ubicacion);

		String[] opciones = { textos.getString("aceptar")};

		// JOptionPane internacionalizado
		int seleccion = JOptionPane.showOptionDialog(this, textos.getString("panelConfirmarReserva"),
				textos.getString("tituloPanelConfirmarReserva"), 2, JOptionPane.INFORMATION_MESSAGE, null, opciones,
				textos.getString("opcionNo"));
		if (seleccion == JOptionPane.YES_OPTION) {
			vp.mostrarMenuInicio();
			vp.mostrarInicio();
			dispose();
		}
	}
	
	private void cargaAyuda() {

		URL hsURL;
		HelpSet hs;

		try {
			File fichero = new File("help/ayuda.hs");
			hsURL = fichero.toURI().toURL();
			hs = new HelpSet(null, hsURL);
		}

		catch (Exception e) {
			System.out.println("Ayuda no encontrada");
			return;
		}

		HelpBroker hb = hs.createHelpBroker();
		hb.initPresentation();
		
		// Activa la tecla F1, es decir hace que funcione la ayuda pulsando F1
		hb.enableHelpKey(getRootPane(), "intro", hs);

	}
}
