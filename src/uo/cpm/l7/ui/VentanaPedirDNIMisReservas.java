package uo.cpm.l7.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import uo.cpm.l7.service.Horrotel;

import java.awt.Font;
import java.util.ResourceBundle;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPedirDNIMisReservas extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextPane txInfo;
	private JLabel lbDNI;
	private JTextField txDNI;
	private Horrotel app;
	private VentanaPrincipal vp;
	private JButton btCancelar;
	private JButton btAcceder;
	private PermitirAcceder pA = new PermitirAcceder();
	// Configuramos los atributos que vamos a poner al texto del JTextPane (Texto
	// centrado)
	private SimpleAttributeSet centrarTexto = new SimpleAttributeSet();

	/**
	 * Create the dialog.
	 */
	public VentanaPedirDNIMisReservas(VentanaPrincipal vp, Horrotel app) {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(VentanaPedirDNIMisReservas.class.getResource("/img/logo.png")));
		this.vp = vp;
		this.app = app;
		// Configuramos los atributos que vamos a poner al texto del JTextPane (Texto
		// centrado)
		StyleConstants.setAlignment(centrarTexto, StyleConstants.ALIGN_CENTER);

		setModal(true);
		getContentPane().setBackground(Color.DARK_GRAY);
		setBackground(Color.DARK_GRAY);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getTxInfo());
		contentPanel.add(getLbDNI());
		contentPanel.add(getTxDNI());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.DARK_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.add(getBtCancelar());
			buttonPane.add(getBtAcceder());
		}

		localizar();
	}

	private JTextPane getTxInfo() {
		if (txInfo == null) {
			txInfo = new JTextPane();
			txInfo.setForeground(Color.WHITE);
			txInfo.setEditable(false);
			txInfo.setFont(new Font("Arial", Font.PLAIN, 22));
			txInfo.setBackground(Color.DARK_GRAY);
			txInfo.setBounds(36, 11, 369, 130);
		}
		return txInfo;
	}

	private JLabel getLbDNI() {
		if (lbDNI == null) {
			lbDNI = new JLabel("");
			lbDNI.setLabelFor(getTxDNI());
			lbDNI.setForeground(Color.WHITE);
			lbDNI.setFont(new Font("Arial", Font.PLAIN, 20));
			lbDNI.setBounds(36, 144, 146, 31);
		}
		return lbDNI;
	}

	private JTextField getTxDNI() {
		if (txDNI == null) {
			txDNI = new JTextField();
			txDNI.setFont(new Font("Arial", Font.PLAIN, 15));
			txDNI.setBounds(192, 144, 212, 31);
			txDNI.setColumns(10);
			txDNI.getDocument().addDocumentListener(pA);
		}
		return txDNI;
	}

	private JButton getBtCancelar() {
		if (btCancelar == null) {
			btCancelar = new JButton("");
			btCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btCancelar.setFont(new Font("Arial", Font.PLAIN, 20));
		}
		return btCancelar;
	}

	private JButton getBtAcceder() {
		if (btAcceder == null) {
			btAcceder = new JButton("");
			btAcceder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					comprobarSiHayDNIGuardado();
				}
			});
			btAcceder.setEnabled(false);
			btAcceder.setFont(new Font("Arial", Font.PLAIN, 20));
		}
		return btAcceder;
	}


	private void localizar() {
		ResourceBundle textos = ResourceBundle.getBundle("rcs/textos", vp.getUbicacion());

		setTitle(textos.getString("tituloPedirDNI"));

		getLbDNI().setText(textos.getString("pedirDNI"));
		getLbDNI().setDisplayedMnemonic(textos.getString("mnemonicPedirDNI").charAt(0));
		getBtCancelar().setText(textos.getString("cancelar"));
		getBtCancelar().setMnemonic(textos.getString("mnemonicCancelar").charAt(0));
		getBtAcceder().setText(textos.getString("acceder"));
		getBtAcceder().setMnemonic(textos.getString("mnemonicAcceder").charAt(0));

		// Cogemos el documento de estilo del JTextPane
		StyledDocument doc = getTxInfo().getStyledDocument();

		// Aplicamos los atributos al documento
		doc.setParagraphAttributes(0, doc.getLength(), centrarTexto, false);
		
		getTxInfo().setText(textos.getString("infoPedirDNI"));

	}
	
	/**
	 * Habilita el botón de acceder siempre y cuando este rellenado el JTextField del DNI
	 */
	private class PermitirAcceder implements DocumentListener {

		@Override
		public void insertUpdate(DocumentEvent e) {
			comprobarSiHayDNI();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			comprobarSiHayDNI();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			comprobarSiHayDNI();
		}

	}
	
	/**
	 * Comprueba si esta rellenado el campo del DNI
	 */
	private void comprobarSiHayDNI() {
		if (!getTxDNI().getText().isBlank())
			getBtAcceder().setEnabled(true);
		else
			getBtAcceder().setEnabled(false);
	}
	
	private void comprobarSiHayDNIGuardado() {
		String DNI = getTxDNI().getText();
		
		if (app.isReservaGuardada(DNI)) {
			VentanaMisReservas vMR = new VentanaMisReservas(this.vp, this.app, DNI);
			vMR.setLocationRelativeTo(this);
			vMR.setVisible(true);
			dispose();
		}
		else {
			ResourceBundle textos = ResourceBundle.getBundle("rcs/textos", vp.getUbicacion());

			String[] opciones = { textos.getString("aceptar")};

			// JOptionPane internacionalizado
			int seleccion = JOptionPane.showOptionDialog(this, textos.getString("noHayReserva"),
					textos.getString("tituloNoHayReserva"), 2, JOptionPane.INFORMATION_MESSAGE, null, opciones,
					textos.getString("opcionNo"));
			if (seleccion == JOptionPane.YES_OPTION) {
				dispose();
			}
		}
		
	}

}
