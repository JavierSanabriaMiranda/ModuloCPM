package uo.cpm.l7.ui;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import uo.cpm.l7.service.Horrotel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.CardLayout;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaFinalJuego extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel pnPrincipal;
	private Horrotel app;
	private VentanaPrincipal vp;
	private Locale ubicacion;
	private JPanel pnInicial;
	private JLabel lbFinDeJuego;
	private JTextPane txPnDescuentoObtenido;
	private JButton btJugarDeNuevo;
	private JButton btObtenerDescuento;
	// Configuramos los atributos que vamos a poner al texto del JTextPane (Texto
	// centrado)
	private SimpleAttributeSet centrarTexto = new SimpleAttributeSet();
	private JPanel pnResultadoFinDeJuego;
	private JPanel pnObtenerDescuento;
	private JTextPane txInfoObtenerDescuento;
	private JTextField txDNI;
	private JLabel lbIntroDNI;
	private JButton btCancelar;
	private JButton btGuardar;
	private PasarPantalla pP = new PasarPantalla();

	/**
	 * Create the frame.
	 */
	public VentanaFinalJuego(Horrotel app, VentanaPrincipal vp, Locale ubicacion) {
		this.ubicacion = ubicacion;
		this.app = app;
		this.vp = vp;

		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 726, 450);
		pnPrincipal = new JPanel();
		pnPrincipal.setBackground(Color.DARK_GRAY);
		pnPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnPrincipal);
		pnPrincipal.setLayout(new CardLayout(0, 0));
		pnPrincipal.add(getPnInicial(), "panelInicial");
		pnPrincipal.add(getPnObtenerDescuento(), "obtDescuento");
		localizar();
	}

	////////////////////// GETTERS DE COMPONENTES //////////////////////

	private JPanel getPnInicial() {
		if (pnInicial == null) {
			pnInicial = new JPanel();
			pnInicial.setBorder(null);
			pnInicial.setBackground(Color.DARK_GRAY);
			pnInicial.setLayout(null);
			pnInicial.add(getTxPnDescuentoObtenido());
			pnInicial.add(getBtJugarDeNuevo());
			pnInicial.add(getBtObtenerDescuento());
			pnInicial.add(getPnResultadoFinDeJuego());
		}
		return pnInicial;
	}

	private JLabel getLbFinDeJuego() {
		if (lbFinDeJuego == null) {
			lbFinDeJuego = new JLabel();
			lbFinDeJuego.setForeground(Color.WHITE);
			lbFinDeJuego.setBackground(Color.DARK_GRAY);
			lbFinDeJuego.setFont(new Font("Arial", Font.PLAIN, 70));
		}
		return lbFinDeJuego;
	}

	private JTextPane getTxPnDescuentoObtenido() {
		if (txPnDescuentoObtenido == null) {
			txPnDescuentoObtenido = new JTextPane();
			txPnDescuentoObtenido.setForeground(Color.WHITE);
			txPnDescuentoObtenido.setEditable(false);
			txPnDescuentoObtenido.setBackground(Color.DARK_GRAY);
			txPnDescuentoObtenido.setFont(new Font("Arial", Font.PLAIN, 23));
			txPnDescuentoObtenido.setBounds(166, 155, 367, 126);
		}
		return txPnDescuentoObtenido;
	}

	private JButton getBtJugarDeNuevo() {
		if (btJugarDeNuevo == null) {
			btJugarDeNuevo = new JButton();
			btJugarDeNuevo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Si se ha obtenido descuento pide un mensaje de confirmación extra
					if (app.hayPremio())
						pedirConfirmacionDeJugarDeNuevo();
					else
						volverAJugar();
				}
			});
			btJugarDeNuevo.setFont(new Font("Arial", Font.PLAIN, 14));
			btJugarDeNuevo.setBackground(new Color(240, 240, 240));
			btJugarDeNuevo.setBounds(142, 300, 182, 81);
		}
		return btJugarDeNuevo;
	}

	private JButton getBtObtenerDescuento() {
		if (btObtenerDescuento == null) {
			btObtenerDescuento = new JButton("");
			btObtenerDescuento.setFont(new Font("Arial", Font.PLAIN, 14));
			btObtenerDescuento.setBackground(SystemColor.menu);
			btObtenerDescuento.setBounds(374, 300, 182, 81);
			btObtenerDescuento.setActionCommand("obtDescuento");
			btObtenerDescuento.addActionListener(pP);
			// Si no se ha obtenido descuento se deshabilita el botón
			if (!app.hayPremio())
				btObtenerDescuento.setEnabled(false);
		}
		return btObtenerDescuento;
	}

	private JPanel getPnResultadoFinDeJuego() {
		if (pnResultadoFinDeJuego == null) {
			pnResultadoFinDeJuego = new JPanel();
			pnResultadoFinDeJuego.setBackground(Color.DARK_GRAY);
			pnResultadoFinDeJuego.setBounds(87, 11, 537, 140);
			pnResultadoFinDeJuego.add(getLbFinDeJuego());
		}
		return pnResultadoFinDeJuego;
	}

	private JPanel getPnObtenerDescuento() {
		if (pnObtenerDescuento == null) {
			pnObtenerDescuento = new JPanel();
			pnObtenerDescuento.setBackground(Color.DARK_GRAY);
			pnObtenerDescuento.setLayout(null);
			pnObtenerDescuento.add(getTxInfoObtenerDescuento());
			pnObtenerDescuento.add(getTxDNI());
			pnObtenerDescuento.add(getLbIntroDNI());
			pnObtenerDescuento.add(getBtCancelar());
			pnObtenerDescuento.add(getBtGuardar());
		}
		return pnObtenerDescuento;
	}

	private JTextPane getTxInfoObtenerDescuento() {
		if (txInfoObtenerDescuento == null) {
			txInfoObtenerDescuento = new JTextPane();
			txInfoObtenerDescuento.setForeground(Color.WHITE);
			txInfoObtenerDescuento.setFont(new Font("Arial", Font.PLAIN, 23));
			txInfoObtenerDescuento.setEditable(false);
			txInfoObtenerDescuento.setBackground(Color.DARK_GRAY);
			txInfoObtenerDescuento.setBounds(95, 32, 534, 126);
		}
		return txInfoObtenerDescuento;
	}

	private JTextField getTxDNI() {
		if (txDNI == null) {
			txDNI = new JTextField();
			txDNI.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					comprobarQueHayDNI();
				}
			});
			txDNI.setFont(new Font("Arial", Font.PLAIN, 15));
			txDNI.setBounds(259, 204, 369, 42);
			txDNI.setColumns(10);
		}
		return txDNI;
	}

	private JLabel getLbIntroDNI() {
		if (lbIntroDNI == null) {
			lbIntroDNI = new JLabel("");
			lbIntroDNI.setLabelFor(getTxDNI());
			lbIntroDNI.setForeground(Color.WHITE);
			lbIntroDNI.setFont(new Font("Arial", Font.PLAIN, 20));
			lbIntroDNI.setBounds(98, 202, 151, 42);
		}
		return lbIntroDNI;
	}

	private JButton getBtCancelar() {
		if (btCancelar == null) {
			btCancelar = new JButton("");
			btCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pedirConfirmacionCancelar();
				}
			});
			btCancelar.setFont(new Font("Arial", Font.PLAIN, 13));
			btCancelar.setBounds(311, 311, 151, 61);
		}
		return btCancelar;
	}

	private JButton getBtGuardar() {
		if (btGuardar == null) {
			btGuardar = new JButton("");
			btGuardar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!comprobarDNIGuardado()) {
						guardarDescuento();
						informarGuardadoDescuento();
					} else
						informarDNIYaUsado();

				}
			});
			btGuardar.setFont(new Font("Arial", Font.PLAIN, 13));
			btGuardar.setEnabled(false);
			btGuardar.setBounds(477, 311, 151, 61);
		}
		return btGuardar;
	}

	////////////////////// INTERNACIONALIZACION //////////////////////

	/**
	 * Localiza los textos del JDialog de final de juego
	 */
	private void localizar() {
		// Configuramos los atributos que vamos a poner al texto del JTextPane (Texto
		// centrado)
		StyleConstants.setAlignment(centrarTexto, StyleConstants.ALIGN_CENTER);

		ResourceBundle textos = ResourceBundle.getBundle("rcs/textos", this.ubicacion);

		localizarPanelInicial(textos);
		localizarPanelObtenerDescuento(textos);
	}

	private void localizarPanelInicial(ResourceBundle textos) {
		// Cogemos el documento de estilo del JTextPane
		StyledDocument doc = getTxPnDescuentoObtenido().getStyledDocument();

		// Aplicamos los atributos al documento
		doc.setParagraphAttributes(0, doc.getLength(), centrarTexto, false);

		// Establecemos el texto en el JTextPane
		getTxPnDescuentoObtenido().setText(textos.getString("descuento") + app.getValorDescuento() + "%");

		getBtJugarDeNuevo().setText(textos.getString("botonJugarDeNuevo"));
		getBtJugarDeNuevo().setMnemonic(textos.getString("mnemonicJugarDeNuevo").charAt(0));
		getBtObtenerDescuento().setText(textos.getString("botonObtenerDescuento"));
		getBtObtenerDescuento().setMnemonic(textos.getString("mnemonicObtenerDescuento").charAt(0));

		if (app.hayPremio())
			getLbFinDeJuego().setText(textos.getString("victoria"));
		else
			getLbFinDeJuego().setText(textos.getString("derrota"));
	}

	private void localizarPanelObtenerDescuento(ResourceBundle textos) {
		// Cogemos el documento de estilo del JTextPane
		StyledDocument doc = getTxInfoObtenerDescuento().getStyledDocument();

		// Aplicamos los atributos al documento
		doc.setParagraphAttributes(0, doc.getLength(), centrarTexto, false);

		// Establecemos el texto en el JTextPane
		getTxInfoObtenerDescuento().setText(textos.getString("comoObtenerDescuento"));

		getLbIntroDNI().setText(textos.getString("introDNI"));
		getLbIntroDNI().setDisplayedMnemonic(textos.getString("mnemonicIntroDNI").charAt(0));
		getBtCancelar().setText(textos.getString("cancelar"));
		getBtCancelar().setMnemonic('c');
		getBtGuardar().setText(textos.getString("guardarDescuento"));
		getBtGuardar().setMnemonic(textos.getString("mnemonicGuardarDescuento").charAt(0));
	}

	////////////////////// OTROS MÉTODOS //////////////////////

	/**
	 * Despliega para el usuario un JOptionPane que le pide confirmación para volver
	 * a jugar
	 */
	private void pedirConfirmacionDeJugarDeNuevo() {
		ResourceBundle textos = ResourceBundle.getBundle("rcs/textos", this.ubicacion);

		String[] opciones = { textos.getString("opcionSi"), textos.getString("opcionNo") };

		// JOptionPane internacionalizado
		int seleccion = JOptionPane.showOptionDialog(this, textos.getString("confirmarJugarDeNuevo"),
				textos.getString("tituloPnConfirmarJugarDeNuevo"), 2, JOptionPane.QUESTION_MESSAGE, null, opciones,
				textos.getString("opcionNo"));
		if (seleccion == JOptionPane.YES_OPTION) {
			volverAJugar();
		}
	}

	/**
	 * Manda al usuario al menú de inicio del juego y cierra la ventana de final de
	 * juego
	 */
	private void volverAJugar() {
		vp.jugarDeNuevo();
		this.dispose();
	}

	/**
	 * Comprueba si hay texto introducido en el txDNI, en ese caso, habilita el
	 * botón de guardar descuento
	 */
	private void comprobarQueHayDNI() {
		if (getTxDNI().getText().length() > 0)
			getBtGuardar().setEnabled(true);
		else
			getBtGuardar().setEnabled(false);
	}

	private void pedirConfirmacionCancelar() {
		ResourceBundle textos = ResourceBundle.getBundle("rcs/textos", this.ubicacion);

		String[] opciones = { textos.getString("opcionSi"), textos.getString("opcionNo") };

		// JOptionPane internacionalizado
		int seleccion = JOptionPane.showOptionDialog(this, textos.getString("confirmarCancelar"),
				textos.getString("tituloCancelar"), 2, JOptionPane.QUESTION_MESSAGE, null, opciones,
				textos.getString("opcionNo"));
		if (seleccion == JOptionPane.YES_OPTION) {
			vp.mostrarMenuInicio();
			this.dispose();
		}
	}

	/**
	 * @return true si el DNI está ya guardado, false en caso contrario
	 */
	private boolean comprobarDNIGuardado() {
		return app.isDNIGuardado(getTxDNI().getText());
	}

	/**
	 * Guarda el descuento del cliente con el DNI que introdujo en el txDNI
	 */
	private void guardarDescuento() {
		app.guardarDescuento(getTxDNI().getText());
	}

	private void informarGuardadoDescuento() {
		ResourceBundle textos = ResourceBundle.getBundle("rcs/textos", this.ubicacion);

		String[] opciones = { textos.getString("aceptar") };

		// JOptionPane internacionalizado
		int seleccion = JOptionPane.showOptionDialog(this, textos.getString("descuentoGuardado"),
				textos.getString("tituloDescuentoGuardado"), 2, JOptionPane.INFORMATION_MESSAGE, null, opciones,
				textos.getString("aceptar"));
		if (seleccion == JOptionPane.YES_OPTION) {
			vp.mostrarMenuInicio();
			this.dispose();
		}
	}

	private void informarDNIYaUsado() {
		ResourceBundle textos = ResourceBundle.getBundle("rcs/textos", this.ubicacion);

		String[] opciones = { textos.getString("aceptar") };

		// JOptionPane internacionalizado
		JOptionPane.showOptionDialog(this, textos.getString("dniYaUsado"),
				textos.getString("tituloDNIYaUsado"), 2, JOptionPane.INFORMATION_MESSAGE, null, opciones,
				textos.getString("aceptar"));
	}

	class PasarPantalla implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton fuente = (JButton) e.getSource();
			mostrarPantalla(fuente.getActionCommand());
		}

	}

	/**
	 * Elige la pantalla a mostrar en función del String introducido como parámetro
	 * 
	 * @param pantalla Nombre de la pantalla que se desea mostrar
	 */
	private void mostrarPantalla(String pantalla) {
		switch (pantalla) {
		case "obtDescuento":
			((CardLayout) pnPrincipal.getLayout()).show(pnPrincipal, "obtDescuento");
			break;
		default:
			throw new IllegalArgumentException("El valor " + pantalla + " no es un valor válido");
		}
	}
}
