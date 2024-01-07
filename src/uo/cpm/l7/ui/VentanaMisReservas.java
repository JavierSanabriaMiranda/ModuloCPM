package uo.cpm.l7.ui;

import java.util.ResourceBundle;

import javax.swing.JDialog;

import uo.cpm.l7.service.Horrotel;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMisReservas extends JDialog {

	private static final long serialVersionUID = 1L;
	private VentanaPrincipal vp;
	private Horrotel app;
	private String DNI;
	private JLabel lbMisReservas;
	private JPanel pnTitulo;
	private JPanel pnSalir;
	private JButton btSalir;
	private JScrollPane scReservas;
	private JPanel pnReservas;

	/**
	 * Create the dialog.
	 */
	public VentanaMisReservas(VentanaPrincipal vp, Horrotel app, String DNI) {
		this.vp = vp;
		this.app = app;
		this.DNI = DNI;

		setModal(true);
		getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(getPnTitulo(), BorderLayout.NORTH);
		getContentPane().add(getPnSalir(), BorderLayout.SOUTH);
		getContentPane().add(getScReservas());
		setBackground(Color.DARK_GRAY);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaMisReservas.class.getResource("/img/logo.png")));
		setBounds(100, 100, 798, 709);

		crearPanelesReserva();
		localizar();

	}

	private JLabel getLbMisReservas() {
		if (lbMisReservas == null) {
			lbMisReservas = new JLabel("New label");
			lbMisReservas.setHorizontalAlignment(SwingConstants.CENTER);
			lbMisReservas.setForeground(Color.WHITE);
			lbMisReservas.setFont(new Font("Arial", Font.PLAIN, 45));
		}
		return lbMisReservas;
	}

	private JPanel getPnTitulo() {
		if (pnTitulo == null) {
			pnTitulo = new JPanel();
			pnTitulo.setBackground(Color.DARK_GRAY);
			pnTitulo.add(getLbMisReservas());
		}
		return pnTitulo;
	}

	private JPanel getPnSalir() {
		if (pnSalir == null) {
			pnSalir = new JPanel();
			pnSalir.setBackground(Color.DARK_GRAY);
			pnSalir.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			pnSalir.add(getBtSalir());
		}
		return pnSalir;
	}

	private JButton getBtSalir() {
		if (btSalir == null) {
			btSalir = new JButton("");
			btSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btSalir.setFont(new Font("Arial", Font.PLAIN, 15));
		}
		return btSalir;
	}

	private JScrollPane getScReservas() {
		if (scReservas == null) {
			scReservas = new JScrollPane();
			scReservas.setBackground(Color.DARK_GRAY);
			scReservas.setViewportView(getPnReservas());
		}
		return scReservas;
	}

	protected JPanel getPnReservas() {
		if (pnReservas == null) {
			pnReservas = new JPanel();
			pnReservas.setBackground(Color.DARK_GRAY);
			pnReservas.setLayout(new GridLayout(0, 1, 0, 0));
		}
		return pnReservas;
	}

	private void localizar() {
		ResourceBundle textos = ResourceBundle.getBundle("rcs/textos", vp.getUbicacion());

		setTitle(textos.getString("tituloMisReservas"));
		getLbMisReservas().setText(textos.getString("misReservas"));
		getBtSalir().setText(textos.getString("salir"));
		getBtSalir().setMnemonic(textos.getString("mnemonicSalir").charAt(0));
	}
	
	/**
	 * Crea los paneles con las reservas correspondientes al DNI introducido
	 */
	protected void crearPanelesReserva() {
		getPnReservas().removeAll();
		repaint();
		for (int i = 0; i < app.getNumReservas(DNI); i++) {
			PanelReserva pR = new PanelReserva(this.vp, this.app, app.getReserva(DNI, i), this);
			getPnReservas().add(pR);
		}
		if (getPnReservas().getComponentCount() < 3)
			ponerPanelesInvisibles();
		repaint();
		validate();
	}
	
	/**
	 * Cuando el número de reservas es menor que 3, se crean paneles invisibles hasta que hayan 3 paneles en el panel de reservas con
	 * la intención de mantener la proporcion de los paneles en el gridLayout
	 */
	private void ponerPanelesInvisibles() {
		int numComponentesReales = getPnReservas().getComponentCount();
		for (int i = numComponentesReales; i < 3; i++) {
			JPanel pn = new JPanel();
			pn.setVisible(false);
			getPnReservas().add(pn);
		}
			
	}
}
