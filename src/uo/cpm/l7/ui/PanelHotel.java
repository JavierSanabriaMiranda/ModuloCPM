package uo.cpm.l7.ui;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import uo.cpm.l7.model.Hotel.Hotel;
import uo.cpm.l7.service.Horrotel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelHotel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Horrotel app;
	private VentanaPrincipal vp;
	private Hotel hotel;
	private JPanel pnFotoYEncantamientos;
	private JPanel pnNombreYPrecio;
	private JPanel pnEncantamientos;
	private JPanel pnFoto;
	private JPanel pnPrecio;
	private JLabel lbValorPrecio;
	private JLabel lbPrecio;
	private JLabel lbNombreHotel;
	private JLabel lbFotoHotel;
	private Component horizontalGlue;

	/**
	 * Create the panel.
	 */
	public PanelHotel(Horrotel app, VentanaPrincipal vp, Hotel hotel) {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelInfoHotel pIH = new PanelInfoHotel(app, vp, hotel);
				vp.getPnInfoHotel().removeAll();
				vp.getPnInfoHotel().add(pIH);
				vp.mostrarInfoHotel();
			}
		});
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				setImagenAdaptada(lbFotoHotel, "/img/" + hotel.getCodigo() + ".png");
			}
		});
		this.app = app;
		this.vp = vp;
		this.hotel = hotel;
		setBackground(Color.DARK_GRAY);
		setBorder(new LineBorder(Color.WHITE));
		setLayout(new BorderLayout(0, 0));
		add(getPnFotoYEncantamientos(), BorderLayout.CENTER);
		add(getPnNombreYPrecio(), BorderLayout.SOUTH);
		setPreferredSize(new Dimension(100, 300));
		
		crearLabelsEncantamientos();

		localizar();
	}

	protected Hotel getHotel() {
		return this.hotel;
	}

	private JPanel getPnFotoYEncantamientos() {
		if (pnFotoYEncantamientos == null) {
			pnFotoYEncantamientos = new JPanel();
			pnFotoYEncantamientos.setBackground(Color.DARK_GRAY);
			pnFotoYEncantamientos.setLayout(new BorderLayout(0, 0));
			pnFotoYEncantamientos.add(getPnEncantamientos(), BorderLayout.NORTH);
			pnFotoYEncantamientos.add(getPnFoto());
		}
		return pnFotoYEncantamientos;
	}

	private JPanel getPnNombreYPrecio() {
		if (pnNombreYPrecio == null) {
			pnNombreYPrecio = new JPanel();
			pnNombreYPrecio.setBackground(Color.DARK_GRAY);
			pnNombreYPrecio.setLayout(new BoxLayout(pnNombreYPrecio, BoxLayout.X_AXIS));
			pnNombreYPrecio.add(getLbNombreHotel());
			pnNombreYPrecio.add(getHorizontalGlue());
			pnNombreYPrecio.add(getPnPrecio());
		}
		return pnNombreYPrecio;
	}

	private JPanel getPnEncantamientos() {
		if (pnEncantamientos == null) {
			pnEncantamientos = new JPanel();
			pnEncantamientos.setBackground(Color.DARK_GRAY);
			pnEncantamientos.setLayout(new BoxLayout(pnEncantamientos, BoxLayout.X_AXIS));
		}
		return pnEncantamientos;
	}

	private JPanel getPnFoto() {
		if (pnFoto == null) {
			pnFoto = new JPanel();
			pnFoto.setBackground(Color.DARK_GRAY);
			pnFoto.setLayout(new BorderLayout(0, 0));
			pnFoto.add(getLbFotoHotel());
		}
		return pnFoto;
	}

	private JPanel getPnPrecio() {
		if (pnPrecio == null) {
			pnPrecio = new JPanel();
			pnPrecio.setMaximumSize(new Dimension(200, 32767));
			pnPrecio.setBackground(Color.DARK_GRAY);
			pnPrecio.setLayout(new BoxLayout(pnPrecio, BoxLayout.X_AXIS));
			pnPrecio.add(getLbValorPrecio());
			pnPrecio.add(getLbPrecio());
		}
		return pnPrecio;
	}

	private JLabel getLbValorPrecio() {
		if (lbValorPrecio == null) {
			lbValorPrecio = new JLabel("");
			lbValorPrecio.setForeground(Color.WHITE);
			lbValorPrecio.setFont(new Font("Arial", Font.PLAIN, 18));
		}
		return lbValorPrecio;
	}

	private JLabel getLbPrecio() {
		if (lbPrecio == null) {
			lbPrecio = new JLabel("");
			lbPrecio.setForeground(Color.WHITE);
			lbPrecio.setFont(new Font("Arial", Font.PLAIN, 18));
		}
		return lbPrecio;
	}

	protected void localizar() {
		ResourceBundle textos = ResourceBundle.getBundle("rcs/textos", vp.getUbicacion());

		getLbPrecio().setText(textos.getString("precioNoche"));
		getLbValorPrecio().setText(String.format("%.2f", hotel.getPrecioHabitacion()));

		getLbNombreHotel().setText(hotel.getDenominacion());
		getPnFoto().setToolTipText(textos.getString("tooltipPulsarHotel"));

	}

	private JLabel getLbNombreHotel() {
		if (lbNombreHotel == null) {
			lbNombreHotel = new JLabel("");
			lbNombreHotel.setMaximumSize(new Dimension(400, 2147483647));
			lbNombreHotel.setAlignmentX(Component.CENTER_ALIGNMENT);
			lbNombreHotel.setForeground(Color.WHITE);
			lbNombreHotel.setFont(new Font("Arial", Font.PLAIN, 20));
		}
		return lbNombreHotel;
	}

	private JLabel getLbFotoHotel() {
		if (lbFotoHotel == null) {
			lbFotoHotel = new JLabel("");
		}
		return lbFotoHotel;
	}

	/**
	 * Adapta el tamaño de la imagen del hotel al tamaño del panel que lo contiene
	 * 
	 * @param label      en la que se ubicará foto del hotel que se quiere
	 *                   redimensionar
	 * @param rutaImagen nombre de la imagen
	 */
	private void setImagenAdaptada(JLabel label, String rutaImagen) {
		Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
		Image imgEscalada = imgOriginal.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_FAST);
		ImageIcon icon = new ImageIcon(imgEscalada);
		label.setIcon(icon);
	}

	private Component getHorizontalGlue() {
		if (horizontalGlue == null) {
			horizontalGlue = Box.createHorizontalGlue();
			horizontalGlue.setMaximumSize(new Dimension(100, 0));
		}
		return horizontalGlue;
	}
	
	private void crearLabelsEncantamientos() {
		ResourceBundle textos = ResourceBundle.getBundle("rcs/textos", vp.getUbicacion());
		
		for (int i = 0; i < getHotel().getNumeroEncantamientos(); i++) {
			// Creamos el panel que contiene al componente
			JPanel pn = new JPanel();
			pn.setSize(new Dimension(40,40));
			pn.setBackground(Color.DARK_GRAY);
			pn.setBorder(new LineBorder(Color.WHITE));
			pn.setLayout(new FlowLayout());
			
			// Creamos el label que informa del encantamiento
			JLabel lb = new JLabel(hotel.getEncantamiento(i).getDiminutivo());
			lb.setBackground(Color.BLUE);
			lb.setForeground(Color.WHITE);
			lb.setFont(new Font("Arial", Font.PLAIN, 15));
			// Establece un tooltip que aclara el tipo de encantamiento al que hace referencia el diminutivo
			pn.setToolTipText(textos.getString(hotel.getEncantamiento(i).getDiminutivo()));
			
			// Añadimos el label al panel
			pn.add(lb);
			
			// Añadimos el panel al otro panel
			getPnEncantamientos().add(pn);
		}
	}

}
