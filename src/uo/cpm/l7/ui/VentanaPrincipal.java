package uo.cpm.l7.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.event.MouseEvent;

import uo.cpm.l7.service.Horrotel;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.Image;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.Box;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.JTextArea;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private Horrotel app;
	private JPanel contentPane;
	private JPanel panelVentanas;
	private JPanel pnInicio;
	private JLabel lbTitulo;
	private JPanel pnJuego;
	private JPanel pnCentroJuego;
	private JButton btEmpezar;
	private JPanel pnMenuInicio;
	private JTextPane txPanePresentacion;
	private PasarPantalla pasaPantalla = new PasarPantalla();
	private CambiaIdioma cI = new CambiaIdioma();
	private MuestraInfoDiminutivos mID = new MuestraInfoDiminutivos();
	private JPanel pnCentralInicio;
	private JPanel pnTituloEIdiomas;
	private JPanel pnCentralMenuInicio;
	private JButton btIrAReservarMenu;
	private JButton btIrAJugarMenu;
	private Component verticalStrutMenuInicio;
	private JPanel pnBtAtras;
	private JButton btAtras;
	private Component horizontalGlueMenuInicio;
	private PanelJuego panelJuego;
	private JPanel pnMenuJuego;
	private JPanel pnTituloJuego;
	private JPanel pnCentralMenuJuego;
	private JLabel lbTituloJuego;
	private JTextPane txPaneInfoDescuentoJuego;
	private JTextPane txReglasJuego;
	private JButton btJugar;;
	// Configuramos los atributos que vamos a poner al texto del JTextPane (Texto
	// centrado)
	private SimpleAttributeSet centrarTexto = new SimpleAttributeSet();

	private Component verticalGlue;
	private Component verticalStrutMenuJuego1;
	private Component verticalStrutMenuJuego2;
	private JPanel pnDadoYValor;
	private JButton btDado;
	private JLabel lbValorDado;
	private JLabel lbValor;
	private JPanel pnValorDado;
	private JPanel pnDado;
	private JLabel lbNumeroTiradas;
	private JPanel pnNumTiradas;
	private JLabel lbTiradas;
	private Component horizontalGlue;
	private Locale ubicacion = Locale.getDefault(Locale.Category.FORMAT);
	private JPanel pnSuperiorJuego;
	private JLabel lbEliminaciones;
	private JPanel pnEliminaciones;
	private JPanel pnIdiomas;
	private JButton btEspanol;
	private JButton btIngles;
	private JPanel pnTitulo;
	private JPanel pnTituloJuegoEIdiomas;
	private JPanel pnDadoDisponible;
	private JLabel lbDadoDisponible;
	private JPanel pnCatalogoHoteles;
	private JPanel pnTituloYBotones;
	private JPanel pnTituloCatalogoHoteles;
	private JPanel pnBotonesCatalogo;
	private JPanel pnBarraBusqueda;
	private JScrollPane scpHoteles;
	private JPanel pnHoteles;
	private JToggleButton btAbrirFiltros;
	private JButton btMisReservas;
	private JTextField txBarraBusqueda;
	private JButton btBuscar;
	private JPanel pnFiltros;
	private JComboBox<String> cbUbicaciones;
	private JSlider slPrecio;
	private JPanel pnEncantamientos;
	private JPanel pnSliderPrecio;
	private JLabel lbPrecio;
	private JPanel pnBotonesYBusqueda;
	private Component horizontalGlueBtCatalogo;
	private Component horizontalStrutBtCatalogo;
	private JButton btAplicarFiltro;
	private JPanel pnComboUbicaciones;
	private JLabel lbUbicacion;
	private JPanel pnLbPrecio;
	private JLabel lbValorPrecio;
	private Component horizontalGlue_1;
	private Component horizontalGlue_2;
	private JPanel pnAplicarYQuitarFiltro;
	private JButton btQuitarFiltro;
	private Component verticalStrut;
	private JPanel pnEncantamientosConLb;
	private JLabel lbEncantamientos;
	private JPanel pnLbEncantamientos;
	private JLabel lbInfoDiminutivos;
	private JTextArea txInfoDiminutivos;
	private JPanel pnInfoHotel;

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal(Horrotel app) {
		this.app = app;
		panelJuego = new PanelJuego(this.app, this);

		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/logo.png")));
		setTitle("Horrotel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1103, 656);
		setMinimumSize(new Dimension(1100, 600));

		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelVentanas());
		localizar();
	}

	////////////////////// GETTERS DE COMPONENTES //////////////////////

	private JPanel getPanelVentanas() {
		if (panelVentanas == null) {
			panelVentanas = new JPanel();
			panelVentanas.setBackground(Color.DARK_GRAY);
			panelVentanas.setLayout(new CardLayout(0, 0));
			panelVentanas.add(getPnInicio(), "inicio");
			panelVentanas.add(getPnJuego(), "juego");
			panelVentanas.add(getPnMenuInicio(), "menuInicio");
			panelVentanas.add(getPnMenuJuego(), "menuJuego");
			panelVentanas.add(getPnCatalogoHoteles(), "catalogoHoteles");
			panelVentanas.add(getPnInfoHotel(), "infoHotel");
		}
		return panelVentanas;
	}

	private JPanel getPnInicio() {
		if (pnInicio == null) {
			pnInicio = new JPanel();
			pnInicio.setBackground(Color.DARK_GRAY);
			pnInicio.setLayout(new BorderLayout(0, 0));
			pnInicio.add(getPnCentralInicio());
			pnInicio.add(getPnTituloEIdiomas(), BorderLayout.NORTH);
		}
		return pnInicio;
	}

	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel("");
			lbTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
			setImagenAdaptadaTitulo(lbTitulo, "/img/titulo.png", 674, 294);
		}
		return lbTitulo;
	}

	private JPanel getPnJuego() {
		if (pnJuego == null) {
			pnJuego = new JPanel();
			pnJuego.setBackground(Color.DARK_GRAY);
			pnJuego.setLayout(new BorderLayout(0, 0));
			pnJuego.add(getPnCentroJuego(), BorderLayout.CENTER);
			pnJuego.add(getPnDadoYValor(), BorderLayout.SOUTH);
			pnJuego.add(getPnSuperiorJuego(), BorderLayout.NORTH);
		}
		return pnJuego;
	}

	private JPanel getPnCentroJuego() {
		if (pnCentroJuego == null) {
			pnCentroJuego = new JPanel();
			pnCentroJuego.setBackground(Color.DARK_GRAY);
			pnCentroJuego.setLayout(new GridLayout(1, 1, 0, 0));
			// pnCentroJuego.addComponentListener(rIT);
			pnCentroJuego.add(panelJuego);
		}
		return pnCentroJuego;
	}

	private JButton getBtEmpezar() {
		if (btEmpezar == null) {
			btEmpezar = new JButton("");
			btEmpezar.setMaximumSize(new Dimension(155, 82));
			btEmpezar.setMinimumSize(new Dimension(155, 89));
			btEmpezar.setAlignmentX(Component.CENTER_ALIGNMENT);
			btEmpezar.setFont(new Font("Arial", Font.PLAIN, 12));
			btEmpezar.setActionCommand("menuInicio");
			btEmpezar.addActionListener(pasaPantalla);
		}
		return btEmpezar;
	}

	private JPanel getPnMenuInicio() {
		if (pnMenuInicio == null) {
			pnMenuInicio = new JPanel();
			pnMenuInicio.setBackground(Color.DARK_GRAY);
			pnMenuInicio.setLayout(new BorderLayout(0, 0));
			pnMenuInicio.add(getPnCentralMenuInicio(), BorderLayout.CENTER);
			pnMenuInicio.add(getPnBtAtras(), BorderLayout.SOUTH);
		}
		return pnMenuInicio;
	}

	private JTextPane getTxPanePresentacion() {
		if (txPanePresentacion == null) {
			txPanePresentacion = new JTextPane();
			txPanePresentacion.setPreferredSize(new Dimension(58, 48));
			txPanePresentacion.setMaximumSize(new Dimension(600, 150));
			txPanePresentacion.setEditable(false);
			txPanePresentacion.setForeground(Color.WHITE);
			txPanePresentacion.setFont(new Font("Arial", Font.PLAIN, 14));
			txPanePresentacion.setBackground(Color.DARK_GRAY);
		}
		return txPanePresentacion;
	}

	private JPanel getPnCentralInicio() {
		if (pnCentralInicio == null) {
			pnCentralInicio = new JPanel();
			pnCentralInicio.setBackground(Color.DARK_GRAY);
			pnCentralInicio.setLayout(new BoxLayout(pnCentralInicio, BoxLayout.Y_AXIS));
			pnCentralInicio.add(getTxPanePresentacion());
			pnCentralInicio.add(getBtEmpezar());
		}
		return pnCentralInicio;
	}

	private JPanel getPnTituloEIdiomas() {
		if (pnTituloEIdiomas == null) {
			pnTituloEIdiomas = new JPanel();
			pnTituloEIdiomas.setBackground(Color.DARK_GRAY);
			pnTituloEIdiomas.setLayout(new BorderLayout(0, 0));
			pnTituloEIdiomas.add(getPnIdiomas(), BorderLayout.EAST);
			pnTituloEIdiomas.add(getPnTitulo(), BorderLayout.CENTER);
		}
		return pnTituloEIdiomas;
	}

	private JPanel getPnCentralMenuInicio() {
		if (pnCentralMenuInicio == null) {
			pnCentralMenuInicio = new JPanel();
			pnCentralMenuInicio.setBackground(Color.DARK_GRAY);
			pnCentralMenuInicio.setLayout(new BoxLayout(pnCentralMenuInicio, BoxLayout.Y_AXIS));
			pnCentralMenuInicio.add(getBtIrAReservarMenu());
			pnCentralMenuInicio.add(getVerticalStrutMenuInicio());
			pnCentralMenuInicio.add(getBtIrAJugarMenu());
		}
		return pnCentralMenuInicio;
	}

	private JButton getBtIrAReservarMenu() {
		if (btIrAReservarMenu == null) {
			btIrAReservarMenu = new JButton();
			btIrAReservarMenu.setMaximumSize(new Dimension(200, 100));
			btIrAReservarMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
			btIrAReservarMenu.setActionCommand("catalogoHoteles");
			btIrAReservarMenu.addActionListener(pasaPantalla);
		}
		return btIrAReservarMenu;
	}

	private JButton getBtIrAJugarMenu() {
		if (btIrAJugarMenu == null) {
			btIrAJugarMenu = new JButton();
			btIrAJugarMenu.setActionCommand("menuJugar");
			btIrAJugarMenu.setMaximumSize(new Dimension(200, 100));
			btIrAJugarMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
			btIrAJugarMenu.addActionListener(pasaPantalla);
		}
		return btIrAJugarMenu;
	}

	private Component getVerticalStrutMenuInicio() {
		if (verticalStrutMenuInicio == null) {
			verticalStrutMenuInicio = Box.createVerticalStrut(20);
		}
		return verticalStrutMenuInicio;
	}

	private JPanel getPnBtAtras() {
		if (pnBtAtras == null) {
			pnBtAtras = new JPanel();
			pnBtAtras.setBackground(Color.DARK_GRAY);
			pnBtAtras.setLayout(new BoxLayout(pnBtAtras, BoxLayout.X_AXIS));
			pnBtAtras.add(getHorizontalGlueMenuInicio());
			pnBtAtras.add(getBtAtras());
		}
		return pnBtAtras;
	}

	private JButton getBtAtras() {
		if (btAtras == null) {
			btAtras = new JButton("New button");
			btAtras.setMaximumSize(new Dimension(104, 71));
			btAtras.setAlignmentX(Component.CENTER_ALIGNMENT);
			btAtras.setActionCommand("inicio");
			btAtras.addActionListener(pasaPantalla);
		}
		return btAtras;
	}

	private Component getHorizontalGlueMenuInicio() {
		if (horizontalGlueMenuInicio == null) {
			horizontalGlueMenuInicio = Box.createHorizontalGlue();
		}
		return horizontalGlueMenuInicio;
	}

	private JPanel getPnMenuJuego() {
		if (pnMenuJuego == null) {
			pnMenuJuego = new JPanel();
			pnMenuJuego.setBackground(Color.DARK_GRAY);
			pnMenuJuego.setLayout(new BorderLayout(0, 0));
			pnMenuJuego.add(getPnCentralMenuJuego(), BorderLayout.CENTER);
			pnMenuJuego.add(getPnTituloJuegoEIdiomas(), BorderLayout.NORTH);
		}
		return pnMenuJuego;
	}

	private JPanel getPnTituloJuegoEIdiomas() {
		if (pnTituloJuegoEIdiomas == null) {
			pnTituloJuegoEIdiomas = new JPanel();
			pnTituloJuegoEIdiomas.setBackground(Color.DARK_GRAY);
			pnTituloJuegoEIdiomas.setLayout(new BorderLayout(0, 0));
			pnTituloJuegoEIdiomas.add(getPnTituloJuego());

		}
		return pnTituloJuegoEIdiomas;
	}

	private JPanel getPnTituloJuego() {
		if (pnTituloJuego == null) {
			pnTituloJuego = new JPanel();
			pnTituloJuego.setBackground(Color.DARK_GRAY);
			pnTituloJuego.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnTituloJuego.add(getLbTituloJuego());
		}
		return pnTituloJuego;
	}

	private JPanel getPnCentralMenuJuego() {
		if (pnCentralMenuJuego == null) {
			pnCentralMenuJuego = new JPanel();
			pnCentralMenuJuego.setBackground(Color.DARK_GRAY);
			pnCentralMenuJuego.setLayout(new BoxLayout(pnCentralMenuJuego, BoxLayout.Y_AXIS));
			pnCentralMenuJuego.add(getTxPaneInfoDescuentoJuego());
			pnCentralMenuJuego.add(getVerticalStrutMenuJuego2());
			pnCentralMenuJuego.add(getTxReglasJuego());
			pnCentralMenuJuego.add(getVerticalStrutMenuJuego1());
			pnCentralMenuJuego.add(getBtJugar());
			pnCentralMenuJuego.add(getVerticalGlue());
		}
		return pnCentralMenuJuego;
	}

	private JLabel getLbTituloJuego() {
		if (lbTituloJuego == null) {
			lbTituloJuego = new JLabel("");
		}
		return lbTituloJuego;
	}

	private JTextPane getTxPaneInfoDescuentoJuego() {
		if (txPaneInfoDescuentoJuego == null) {
			txPaneInfoDescuentoJuego = new JTextPane();
			txPaneInfoDescuentoJuego.setMinimumSize(new Dimension(700, 100));
			txPaneInfoDescuentoJuego.setMaximumSize(new Dimension(700, 50));
			txPaneInfoDescuentoJuego.setForeground(Color.WHITE);
			txPaneInfoDescuentoJuego.setFont(new Font("Arial", Font.PLAIN, 15));
			txPaneInfoDescuentoJuego.setEditable(false);
			txPaneInfoDescuentoJuego.setBackground(Color.DARK_GRAY);
			txPaneInfoDescuentoJuego.setText("");
		}
		return txPaneInfoDescuentoJuego;
	}

	private JTextPane getTxReglasJuego() {
		if (txReglasJuego == null) {
			txReglasJuego = new JTextPane();
			txReglasJuego.setMaximumSize(new Dimension(700, 400));
			txReglasJuego.setForeground(Color.WHITE);
			txReglasJuego.setFont(new Font("Arial", Font.PLAIN, 15));
			txReglasJuego.setEditable(false);
			txReglasJuego.setBackground(Color.DARK_GRAY);
		}
		return txReglasJuego;
	}

	private JButton getBtJugar() {
		if (btJugar == null) {
			btJugar = new JButton("");
			btJugar.setActionCommand("juego");
			btJugar.setMinimumSize(new Dimension(200, 130));
			btJugar.setMaximumSize(new Dimension(200, 130));
			btJugar.setAlignmentX(Component.CENTER_ALIGNMENT);
			btJugar.setBackground(Color.RED);
			btJugar.addActionListener(pasaPantalla);
		}
		return btJugar;
	}

	private Component getVerticalGlue() {
		if (verticalGlue == null) {
			verticalGlue = Box.createVerticalGlue();
		}
		return verticalGlue;
	}

	private Component getVerticalStrutMenuJuego1() {
		if (verticalStrutMenuJuego1 == null) {
			verticalStrutMenuJuego1 = Box.createVerticalStrut(20);
		}
		return verticalStrutMenuJuego1;
	}

	private Component getVerticalStrutMenuJuego2() {
		if (verticalStrutMenuJuego2 == null) {
			verticalStrutMenuJuego2 = Box.createVerticalStrut(20);
		}
		return verticalStrutMenuJuego2;
	}

	private JPanel getPnDadoYValor() {
		if (pnDadoYValor == null) {
			pnDadoYValor = new JPanel();
			pnDadoYValor.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent e) {
					setImagenAdaptada(getBtDado(), "/img/dado.png", 100, 100);
				}
			});
			pnDadoYValor.setBackground(Color.DARK_GRAY);
			pnDadoYValor.setLayout(new BoxLayout(pnDadoYValor, BoxLayout.X_AXIS));
			pnDadoYValor.add(getPnValorDado());
			pnDadoYValor.add(getHorizontalGlue());
			pnDadoYValor.add(getPnDadoDisponible());
			pnDadoYValor.add(getPnDado());
		}
		return pnDadoYValor;
	}

	protected JButton getBtDado() {
		if (btDado == null) {
			btDado = new JButton("");
			btDado.setMaximumSize(new Dimension(200, 200));
			btDado.setMinimumSize(new Dimension(90, 90));
			btDado.setAlignmentX(Component.CENTER_ALIGNMENT);
			btDado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tirarDado();
					actualizarTooltipsTablero();
				}
			});
			btDado.setBackground(Color.DARK_GRAY);
		}
		return btDado;
	}

	private JLabel getLbValorDado() {
		if (lbValorDado == null) {
			lbValorDado = new JLabel("");
			lbValorDado.setForeground(Color.RED);
			lbValorDado.setFont(new Font("Arial", Font.PLAIN, 40));
			lbValorDado.setBackground(Color.DARK_GRAY);
		}
		return lbValorDado;
	}

	private JLabel getLbValor() {
		if (lbValor == null) {
			lbValor = new JLabel("");
			lbValor.setForeground(Color.RED);
			lbValor.setFont(new Font("Arial", Font.PLAIN, 40));
			lbValor.setBackground(Color.DARK_GRAY);
		}
		return lbValor;
	}

	private JPanel getPnValorDado() {
		if (pnValorDado == null) {
			pnValorDado = new JPanel();
			pnValorDado.setBackground(Color.DARK_GRAY);
			pnValorDado.add(getLbValorDado());
			pnValorDado.add(getLbValor());
		}
		return pnValorDado;
	}

	private JPanel getPnDado() {
		if (pnDado == null) {
			pnDado = new JPanel();
			pnDado.setBackground(Color.DARK_GRAY);
			pnDado.setLayout(new BoxLayout(pnDado, BoxLayout.Y_AXIS));
			pnDado.add(getPnNumTiradas());
			pnDado.add(getBtDado());
		}
		return pnDado;
	}

	private JPanel getPnDadoDisponible() {
		if (pnDadoDisponible == null) {
			pnDadoDisponible = new JPanel();
			pnDadoDisponible.setBackground(Color.DARK_GRAY);
			pnDadoDisponible.setMaximumSize(new Dimension(200, 40));
			pnDadoDisponible.add(getLbDadoDisponible());
		}
		return pnDadoDisponible;
	}

	protected JLabel getLbDadoDisponible() {
		if (lbDadoDisponible == null) {
			lbDadoDisponible = new JLabel("");
			lbDadoDisponible.setLabelFor(getBtDado());
			lbDadoDisponible.setFont(new Font("Arial", Font.PLAIN, 36));
			lbDadoDisponible.setForeground(Color.GREEN);
			lbDadoDisponible.setBackground(Color.DARK_GRAY);
		}
		return lbDadoDisponible;
	}

	private JLabel getLbNumeroTiradas() {
		if (lbNumeroTiradas == null) {
			lbNumeroTiradas = new JLabel("");
			lbNumeroTiradas.setForeground(Color.WHITE);
			lbNumeroTiradas.setFont(new Font("Arial", Font.PLAIN, 15));
			lbNumeroTiradas.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		return lbNumeroTiradas;
	}

	private JPanel getPnNumTiradas() {
		if (pnNumTiradas == null) {
			pnNumTiradas = new JPanel();
			pnNumTiradas.setMaximumSize(new Dimension(150, 20));
			pnNumTiradas.setBackground(Color.DARK_GRAY);
			pnNumTiradas.add(getLbNumeroTiradas());
			pnNumTiradas.add(getLbTiradas());
		}
		return pnNumTiradas;
	}

	private JLabel getLbTiradas() {
		if (lbTiradas == null) {
			lbTiradas = new JLabel(app.getTiradasRestantes() + "");
			lbTiradas.setForeground(Color.WHITE);
			lbTiradas.setFont(new Font("Arial", Font.PLAIN, 15));
		}
		return lbTiradas;
	}

	private Component getHorizontalGlue() {
		if (horizontalGlue == null) {
			horizontalGlue = Box.createHorizontalGlue();
		}
		return horizontalGlue;
	}

	private JPanel getPnSuperiorJuego() {
		if (pnSuperiorJuego == null) {
			pnSuperiorJuego = new JPanel();
			pnSuperiorJuego.setBackground(Color.DARK_GRAY);
			pnSuperiorJuego.add(getLbEliminaciones());
			pnSuperiorJuego.add(getPnEliminaciones());
		}
		return pnSuperiorJuego;
	}

	private JLabel getLbEliminaciones() {
		if (lbEliminaciones == null) {
			lbEliminaciones = new JLabel("");
			lbEliminaciones.setForeground(Color.WHITE);
			lbEliminaciones.setFont(new Font("Arial", Font.PLAIN, 44));
			lbEliminaciones.setBackground(new Color(240, 240, 240));
			lbEliminaciones.setVisible(false);
		}
		return lbEliminaciones;
	}

	private JPanel getPnEliminaciones() {
		if (pnEliminaciones == null) {
			pnEliminaciones = new JPanel();
			pnEliminaciones.setBackground(Color.DARK_GRAY);
		}
		return pnEliminaciones;
	}

	private JPanel getPnIdiomas() {
		if (pnIdiomas == null) {
			pnIdiomas = new JPanel();
			pnIdiomas.setBackground(Color.DARK_GRAY);
			pnIdiomas.setMinimumSize(new Dimension(200, 10));
			pnIdiomas.setMaximumSize(new Dimension(2000, 32767));
			pnIdiomas.setLayout(new BoxLayout(pnIdiomas, BoxLayout.Y_AXIS));
			pnIdiomas.add(getBtEspanol());
			pnIdiomas.add(getBtIngles());
		}
		return pnIdiomas;
	}

	private JButton getBtEspanol() {
		if (btEspanol == null) {
			btEspanol = new JButton("");
			btEspanol.setBackground(Color.DARK_GRAY);
			btEspanol.setActionCommand("es");
			setImagenAdaptada(btEspanol, "/img/ES.png", 20, 20);
			btEspanol.addActionListener(cI);
		}
		return btEspanol;
	}

	private JButton getBtIngles() {
		if (btIngles == null) {
			btIngles = new JButton("");
			btIngles.setBackground(Color.DARK_GRAY);
			btIngles.setActionCommand("en");
			setImagenAdaptada(btIngles, "/img/EN.png", 20, 20);
			btIngles.addActionListener(cI);
		}
		return btIngles;
	}

	private JPanel getPnTitulo() {
		if (pnTitulo == null) {
			pnTitulo = new JPanel();
			pnTitulo.setBackground(Color.DARK_GRAY);
			pnTitulo.add(getLbTitulo());
		}
		return pnTitulo;
	}

	////////////////////// GETTERS DE ATRIBUTOS //////////////////////

	/**
	 * @return ubicacion actual en la que se localiza la interfaz
	 */
	protected Locale getUbicacion() {
		return this.ubicacion;
	}

	private JPanel getPnCatalogoHoteles() {
		if (pnCatalogoHoteles == null) {
			pnCatalogoHoteles = new JPanel();
			pnCatalogoHoteles.setBackground(Color.DARK_GRAY);
			pnCatalogoHoteles.setLayout(new BorderLayout(0, 0));
			pnCatalogoHoteles.add(getPnTituloYBotones(), BorderLayout.NORTH);
			pnCatalogoHoteles.add(getScpHoteles());
		}
		return pnCatalogoHoteles;
	}

	private JPanel getPnTituloYBotones() {
		if (pnTituloYBotones == null) {
			pnTituloYBotones = new JPanel();
			pnTituloYBotones.setBackground(Color.DARK_GRAY);
			pnTituloYBotones.setLayout(new BorderLayout(0, 0));
			pnTituloYBotones.add(getPnTituloCatalogoHoteles(), BorderLayout.NORTH);
			pnTituloYBotones.add(getPnBotonesCatalogo());
		}
		return pnTituloYBotones;
	}

	private JPanel getPnTituloCatalogoHoteles() {
		if (pnTituloCatalogoHoteles == null) {
			pnTituloCatalogoHoteles = new JPanel();
			pnTituloCatalogoHoteles.setBackground(Color.DARK_GRAY);
			pnTituloCatalogoHoteles.setLayout(new BorderLayout(0, 0));
		}
		return pnTituloCatalogoHoteles;
	}

	private JPanel getPnBotonesCatalogo() {
		if (pnBotonesCatalogo == null) {
			pnBotonesCatalogo = new JPanel();
			pnBotonesCatalogo.setBackground(Color.DARK_GRAY);
			pnBotonesCatalogo.setLayout(new BorderLayout(0, 0));
			pnBotonesCatalogo.add(getPnFiltros(), BorderLayout.SOUTH);
			pnBotonesCatalogo.add(getPnBotonesYBusqueda());
		}
		return pnBotonesCatalogo;
	}

	private JPanel getPnBarraBusqueda() {
		if (pnBarraBusqueda == null) {
			pnBarraBusqueda = new JPanel();
			pnBarraBusqueda.setMaximumSize(new Dimension(600, 200));
			pnBarraBusqueda.setBackground(Color.DARK_GRAY);
			pnBarraBusqueda.setLayout(new BorderLayout(0, 0));
			pnBarraBusqueda.add(getTxBarraBusqueda(), BorderLayout.CENTER);
			pnBarraBusqueda.add(getBtBuscar(), BorderLayout.EAST);
		}
		return pnBarraBusqueda;
	}

	private JScrollPane getScpHoteles() {
		if (scpHoteles == null) {
			scpHoteles = new JScrollPane();
			scpHoteles.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scpHoteles.setBackground(Color.DARK_GRAY);
			scpHoteles.setViewportView(getPnHoteles());
		}
		return scpHoteles;
	}

	private JPanel getPnHoteles() {
		if (pnHoteles == null) {
			pnHoteles = new JPanel();
			pnHoteles.setBackground(Color.DARK_GRAY);
			pnHoteles.setLayout(new GridLayout(0, 2, 30, 30));
		}
		return pnHoteles;
	}

	private JToggleButton getBtAbrirFiltros() {
		if (btAbrirFiltros == null) {
			btAbrirFiltros = new JToggleButton("");
			btAbrirFiltros.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					mostrarUOcultarFiltros();
				}
			});
			btAbrirFiltros.setFont(new Font("Arial", Font.PLAIN, 14));
		}
		return btAbrirFiltros;
	}

	private JButton getBtMisReservas() {
		if (btMisReservas == null) {
			btMisReservas = new JButton("");
			btMisReservas.setFont(new Font("Arial", Font.PLAIN, 14));
		}
		return btMisReservas;
	}

	private JButton getBtBuscar() {
		if (btBuscar == null) {
			btBuscar = new JButton("");
			btBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscarHotelPorNombre();
				}
			});
			btBuscar.setBackground(Color.WHITE);
			btBuscar.setMinimumSize(new Dimension(50, 50));
			btBuscar.setMaximumSize(new Dimension(50, 50));
			setImagenAdaptada(btBuscar, "/img/lupa.png", 20, 20);
		}
		return btBuscar;
	}

	private JTextField getTxBarraBusqueda() {
		if (txBarraBusqueda == null) {
			txBarraBusqueda = new JTextField();
			txBarraBusqueda.setColumns(10);
		}
		return txBarraBusqueda;
	}

	private JPanel getPnFiltros() {
		if (pnFiltros == null) {
			pnFiltros = new JPanel();
			pnFiltros.setBorder(new LineBorder(Color.WHITE));
			pnFiltros.setBackground(Color.DARK_GRAY);
			pnFiltros.setLayout(new BoxLayout(pnFiltros, BoxLayout.X_AXIS));
			pnFiltros.add(getPnComboUbicaciones());
			pnFiltros.add(getHorizontalGlue_2());
			pnFiltros.add(getPnSliderPrecio());
			pnFiltros.add(getHorizontalGlue_1());
			pnFiltros.add(getPnEncantamientosConLb());
			pnFiltros.add(getTxInfoDiminutivos());
			pnFiltros.add(getPnAplicarYQuitarFiltro());
		}
		return pnFiltros;
	}

	private JComboBox<String> getCbUbicaciones() {
		if (cbUbicaciones == null) {
			cbUbicaciones = new JComboBox<String>();
			cbUbicaciones.setForeground(Color.WHITE);
			cbUbicaciones.setFont(new Font("Arial", Font.PLAIN, 14));
			cbUbicaciones.setAlignmentX(Component.LEFT_ALIGNMENT);
			cbUbicaciones.setMinimumSize(new Dimension(400, 22));
			cbUbicaciones.setMaximumSize(new Dimension(400, 32767));
			cbUbicaciones.setBackground(Color.DARK_GRAY);
		}
		return cbUbicaciones;
	}

	private JSlider getSlPrecio() {
		if (slPrecio == null) {
			slPrecio = new JSlider();
			slPrecio.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					actualizarPrecio();
				}
			});
			slPrecio.setBackground(Color.DARK_GRAY);
			slPrecio.setFont(new Font("Arial", Font.PLAIN, 11));
			slPrecio.setMajorTickSpacing(30);
			slPrecio.setForeground(Color.WHITE);
			slPrecio.setMinimum((int) app.getPrecioMasBajo());
			slPrecio.setMaximum((int) app.getPrecioMasAlto());
			slPrecio.setPaintTicks(true);
			slPrecio.setPaintLabels(true);
			slPrecio.setValue((int) app.getPrecioMasAlto());
		}
		return slPrecio;
	}

	private JPanel getPnEncantamientos() {
		if (pnEncantamientos == null) {
			pnEncantamientos = new JPanel();
			pnEncantamientos.setMaximumSize(new Dimension(500, 32767));
			pnEncantamientos.setBackground(Color.DARK_GRAY);
			pnEncantamientos.setLayout(new GridLayout(2, 0, 0, 0));
			generarFiltrosParaEncantamientos();
		}
		return pnEncantamientos;
	}

	private JPanel getPnSliderPrecio() {
		if (pnSliderPrecio == null) {
			pnSliderPrecio = new JPanel();
			pnSliderPrecio.setMaximumSize(new Dimension(300, 32767));
			pnSliderPrecio.setBackground(Color.DARK_GRAY);
			pnSliderPrecio.setLayout(new BoxLayout(pnSliderPrecio, BoxLayout.Y_AXIS));
			pnSliderPrecio.add(getPnLbPrecio());
			pnSliderPrecio.add(getSlPrecio());
		}
		return pnSliderPrecio;
	}

	private JLabel getLbPrecio() {
		if (lbPrecio == null) {
			lbPrecio = new JLabel("");
			lbPrecio.setLabelFor(getSlPrecio());
			lbPrecio.setAlignmentX(Component.CENTER_ALIGNMENT);
			lbPrecio.setForeground(Color.WHITE);
			lbPrecio.setFont(new Font("Arial", Font.PLAIN, 16));
		}
		return lbPrecio;
	}

	private JPanel getPnBotonesYBusqueda() {
		if (pnBotonesYBusqueda == null) {
			pnBotonesYBusqueda = new JPanel();
			pnBotonesYBusqueda.setBackground(Color.DARK_GRAY);
			pnBotonesYBusqueda.setLayout(new BoxLayout(pnBotonesYBusqueda, BoxLayout.X_AXIS));
			pnBotonesYBusqueda.add(getBtAbrirFiltros());
			pnBotonesYBusqueda.add(getHorizontalGlueBtCatalogo());
			pnBotonesYBusqueda.add(getPnBarraBusqueda());
			pnBotonesYBusqueda.add(getHorizontalStrutBtCatalogo());
			pnBotonesYBusqueda.add(getBtMisReservas());
		}
		return pnBotonesYBusqueda;
	}

	private Component getHorizontalGlueBtCatalogo() {
		if (horizontalGlueBtCatalogo == null) {
			horizontalGlueBtCatalogo = Box.createHorizontalGlue();
			horizontalGlueBtCatalogo.setMaximumSize(new Dimension(900, 0));
		}
		return horizontalGlueBtCatalogo;
	}

	private Component getHorizontalStrutBtCatalogo() {
		if (horizontalStrutBtCatalogo == null) {
			horizontalStrutBtCatalogo = Box.createHorizontalStrut(20);
		}
		return horizontalStrutBtCatalogo;
	}

	private JButton getBtAplicarFiltro() {
		if (btAplicarFiltro == null) {
			btAplicarFiltro = new JButton("");
			btAplicarFiltro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					aplicarFiltro();
				}
			});
			btAplicarFiltro.setMaximumSize(new Dimension(150, 40));
			btAplicarFiltro.setAlignmentX(Component.CENTER_ALIGNMENT);
			btAplicarFiltro.setFont(new Font("Arial", Font.PLAIN, 14));
		}
		return btAplicarFiltro;
	}

	private JPanel getPnComboUbicaciones() {
		if (pnComboUbicaciones == null) {
			pnComboUbicaciones = new JPanel();
			pnComboUbicaciones.setBackground(Color.DARK_GRAY);
			pnComboUbicaciones.setLayout(new BoxLayout(pnComboUbicaciones, BoxLayout.Y_AXIS));
			pnComboUbicaciones.add(getLbUbicacion());
			pnComboUbicaciones.add(getCbUbicaciones());
		}
		return pnComboUbicaciones;
	}

	private JLabel getLbUbicacion() {
		if (lbUbicacion == null) {
			lbUbicacion = new JLabel("");
			lbUbicacion.setLabelFor(getCbUbicaciones());
			lbUbicacion.setForeground(Color.WHITE);
			lbUbicacion.setFont(new Font("Arial", Font.PLAIN, 15));
		}
		return lbUbicacion;
	}

	private JPanel getPnLbPrecio() {
		if (pnLbPrecio == null) {
			pnLbPrecio = new JPanel();
			pnLbPrecio.setBackground(Color.DARK_GRAY);
			pnLbPrecio.add(getLbPrecio());
			pnLbPrecio.add(getLbValorPrecio());
		}
		return pnLbPrecio;
	}

	private JLabel getLbValorPrecio() {
		if (lbValorPrecio == null) {
			lbValorPrecio = new JLabel("");
			lbValorPrecio.setForeground(Color.WHITE);
			lbValorPrecio.setFont(new Font("Arial", Font.PLAIN, 16));
		}
		return lbValorPrecio;
	}

	private Component getHorizontalGlue_1() {
		if (horizontalGlue_1 == null) {
			horizontalGlue_1 = Box.createHorizontalGlue();
			horizontalGlue_1.setMaximumSize(new Dimension(400, 0));
		}
		return horizontalGlue_1;
	}

	private Component getHorizontalGlue_2() {
		if (horizontalGlue_2 == null) {
			horizontalGlue_2 = Box.createHorizontalGlue();
			horizontalGlue_2.setMaximumSize(new Dimension(500, 0));
		}
		return horizontalGlue_2;
	}

	private JPanel getPnAplicarYQuitarFiltro() {
		if (pnAplicarYQuitarFiltro == null) {
			pnAplicarYQuitarFiltro = new JPanel();
			pnAplicarYQuitarFiltro.setBackground(Color.DARK_GRAY);
			pnAplicarYQuitarFiltro.setMaximumSize(new Dimension(300, 32767));
			pnAplicarYQuitarFiltro.setLayout(new BoxLayout(pnAplicarYQuitarFiltro, BoxLayout.Y_AXIS));
			pnAplicarYQuitarFiltro.add(getVerticalStrut());
			pnAplicarYQuitarFiltro.add(getBtAplicarFiltro());
			pnAplicarYQuitarFiltro.add(getBtQuitarFiltro());
		}
		return pnAplicarYQuitarFiltro;
	}

	private JButton getBtQuitarFiltro() {
		if (btQuitarFiltro == null) {
			btQuitarFiltro = new JButton("");
			btQuitarFiltro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inicializarCatalogo();
				}
			});
			btQuitarFiltro.setMaximumSize(new Dimension(150, 40));
			btQuitarFiltro.setAlignmentX(Component.CENTER_ALIGNMENT);
			btQuitarFiltro.setFont(new Font("Arial", Font.PLAIN, 14));
		}
		return btQuitarFiltro;
	}

	private Component getVerticalStrut() {
		if (verticalStrut == null) {
			verticalStrut = Box.createVerticalStrut(20);
		}
		return verticalStrut;
	}

	private JPanel getPnEncantamientosConLb() {
		if (pnEncantamientosConLb == null) {
			pnEncantamientosConLb = new JPanel();
			pnEncantamientosConLb.setBackground(Color.DARK_GRAY);
			pnEncantamientosConLb.setLayout(new BoxLayout(pnEncantamientosConLb, BoxLayout.Y_AXIS));
			pnEncantamientosConLb.add(getPnLbEncantamientos());
			pnEncantamientosConLb.add(getPnEncantamientos());
		}
		return pnEncantamientosConLb;
	}

	private JLabel getLbEncantamientos() {
		if (lbEncantamientos == null) {
			lbEncantamientos = new JLabel("");
			lbEncantamientos.setForeground(Color.WHITE);
			lbEncantamientos.setFont(new Font("Arial", Font.PLAIN, 16));
			lbEncantamientos.setBackground(Color.DARK_GRAY);
			lbEncantamientos.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		return lbEncantamientos;
	}
	
	private JLabel getLbInfoDiminutivos() {
		if (lbInfoDiminutivos == null) {
			lbInfoDiminutivos = new JLabel("");
			setImagenAdaptadaTitulo(lbInfoDiminutivos, "/img/info.png", 30, 30);
			lbInfoDiminutivos.addMouseListener(mID);
		}
		return lbInfoDiminutivos;
	}
	
	private JPanel getPnLbEncantamientos() {
		if (pnLbEncantamientos == null) {
			pnLbEncantamientos = new JPanel();
			pnLbEncantamientos.setBackground(Color.DARK_GRAY);
			pnLbEncantamientos.setMaximumSize(new Dimension(500, 100));
			pnLbEncantamientos.add(getLbEncantamientos());
			pnLbEncantamientos.add(getLbInfoDiminutivos());
		}
		return pnLbEncantamientos;
	}
	
	private JTextArea getTxInfoDiminutivos() {
		if (txInfoDiminutivos == null) {
			txInfoDiminutivos = new JTextArea();
			txInfoDiminutivos.setMaximumSize(new Dimension(300, 2147483647));
			txInfoDiminutivos.setForeground(Color.WHITE);
			txInfoDiminutivos.setFont(new Font("Arial", Font.PLAIN, 14));
			txInfoDiminutivos.setBackground(Color.DARK_GRAY);
			txInfoDiminutivos.setEditable(false);
			txInfoDiminutivos.setVisible(false);
		}
		return txInfoDiminutivos;
	}

	////////////////////// INTERNACIONALIZACION //////////////////////

	public class CambiaIdioma implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton fuente = (JButton) e.getSource();
			ubicacion = new Locale(fuente.getActionCommand());
			localizar();
			panelJuego.localizarTablero();
		}

	}

	/**
	 * Método principal para realizar la internacionalización de aplicación
	 */
	private void localizar() {
		// Configuramos los atributos que vamos a poner al texto del JTextPane (Texto
		// centrado)
		StyleConstants.setAlignment(centrarTexto, StyleConstants.ALIGN_CENTER);

		ResourceBundle textos = ResourceBundle.getBundle("rcs/textos", this.ubicacion);

		localizarPantallaInicio(textos);
		localizarMenuInicio(textos);
		localizarMenuJuego(textos);
		localizarJuego(textos);
		localizarCatalogo(textos);
	}

	/**
	 * Muestra todos los textos e imagenes de la pantalla de inicio según la
	 * localización del usuario
	 * 
	 * @param documento que contiene los textos en cada idioma
	 */
	private void localizarPantallaInicio(ResourceBundle textos) {
		getBtEspanol().setToolTipText(textos.getString("mnemonicBtEspanol"));
		getBtIngles().setToolTipText(textos.getString("mnemonicBtIngles"));

		// Cogemos el documento de estilo del JTextPane
		StyledDocument doc = getTxPanePresentacion().getStyledDocument();

		// Aplicamos los atributos al documento
		doc.setParagraphAttributes(0, doc.getLength(), centrarTexto, false);

		getTxPanePresentacion().setText(textos.getString("presentacion0"));

		getBtEmpezar().setText(textos.getString("botonEmpezar"));
		// Establece el mnemonic del botón a la primera letra de su texto interno
		getBtEmpezar().setMnemonic(getBtEmpezar().getText().charAt(0));
	}

	/**
	 * Muestra todos los textos e imagenes del menú de inicio de la aplicación según
	 * la localización del usuario
	 * 
	 * @param documento que contiene los textos en cada idioma
	 */
	private void localizarMenuInicio(ResourceBundle textos) {
		// Establece Textos
		getBtIrAReservarMenu().setText(textos.getString("botonReservarMenu"));
		getBtIrAJugarMenu().setText(textos.getString("botonJugarMenu"));
		;
		getBtAtras().setText(textos.getString("botonAtras"));

		// Establece Mnemónicos
		getBtIrAReservarMenu().setMnemonic(getBtIrAReservarMenu().getText().charAt(0));
		getBtIrAJugarMenu().setMnemonic(getBtIrAJugarMenu().getText().charAt(0));
		getBtAtras().setMnemonic(textos.getString("botonAtrasMnemonic").charAt(0));
	}

	/**
	 * Muestra todos los textos e imagenes del menú del juego según la localización
	 * del usuario
	 * 
	 * @param documento que contiene los textos en cada idioma
	 */
	private void localizarMenuJuego(ResourceBundle textos) {
		setImagenAdaptadaTitulo(getLbTituloJuego(), textos.getString("rutaTituloJuego"), 600, 300);

		// Cogemos el documento de estilo del JTextPane
		StyledDocument doc1 = getTxPaneInfoDescuentoJuego().getStyledDocument();
		StyledDocument doc2 = getTxReglasJuego().getStyledDocument();

		// Aplicamos los atributos al documento
		doc1.setParagraphAttributes(0, doc1.getLength(), centrarTexto, false);
		doc2.setParagraphAttributes(0, doc2.getLength(), centrarTexto, false);

		// Establecemos el texto en el JTextPane
		getTxPaneInfoDescuentoJuego().setText(textos.getString("infoDescuentoJuego"));
		getTxReglasJuego().setText(textos.getString("reglasJuego"));

		getBtJugar().setText(textos.getString("jugar"));
		getBtJugar().setMnemonic(textos.getString("jugar").charAt(0));
	}

	private void localizarJuego(ResourceBundle textos) {
		getLbValorDado().setText(textos.getString("valorDado"));
		getLbNumeroTiradas().setText(textos.getString("numTiradas"));
		getBtDado().setToolTipText(textos.getString("tooltipDado"));
		getLbEliminaciones().setText(textos.getString("eliminaciones"));
		getLbDadoDisponible().setText(textos.getString("tirarDado"));
		getLbDadoDisponible().setDisplayedMnemonic(getLbDadoDisponible().getText().charAt(0));
		panelJuego.localizarTablero();
	}

	private void localizarCatalogo(ResourceBundle textos) {
		colocarTextoEnLabelsCatalogo(textos);

		// Cambiar texto del combo de ubicaciones
		if (ubicacion.getLanguage().equals("es")) {
			getCbUbicaciones().setModel(new DefaultComboBoxModel<>(app.getUbicacionesES()));
		} else {
			getCbUbicaciones().setModel(new DefaultComboBoxModel<>(app.getUbicacionesEN()));
		}
		
		// Se establece el texto del panel de información de diminutivos
		getTxInfoDiminutivos().setText(textos.getString("infoDiminutivos"));
		
		// Se establece el tooltip para el icono de información de diminutivos
		getLbInfoDiminutivos().setToolTipText(textos.getString("tooltipInfoDiminutivos"));

		inicializarCatalogo();
	}

	private void colocarTextoEnLabelsCatalogo(ResourceBundle textos) {
		getBtAbrirFiltros().setText(textos.getString("btFiltros"));
		getBtAbrirFiltros().setMnemonic(textos.getString("mnemonicBtFiltros").charAt(0));
		getBtBuscar().setToolTipText(textos.getString("tooltipBarraBusqueda"));
		getBtMisReservas().setText(textos.getString("btMisReservas"));
		getBtMisReservas().setMnemonic(textos.getString("mnemonicBtReservas").charAt(0));
		getLbPrecio().setText(textos.getString("precio"));
		getLbPrecio().setDisplayedMnemonic(getLbPrecio().getText().charAt(0));
		getBtAplicarFiltro().setText(textos.getString("btAplicarFiltro"));
		getBtAplicarFiltro().setMnemonic(textos.getString("mnemonicAplicarFiltro").charAt(0));
		getLbUbicacion().setText(textos.getString("ubicacion"));
		getLbUbicacion().setDisplayedMnemonic(textos.getString("mnemonicUbicacion").charAt(0));
		getBtQuitarFiltro().setText(textos.getString("btQuitarFiltro"));
		getBtQuitarFiltro().setMnemonic(textos.getString("mnemonicBtQuitarFiltro").charAt(0));
		getLbEncantamientos().setText(textos.getString("encantamientos"));
	}

	////////////////////// PASAR VENTANAS CON EL CARDLAYOUT //////////////////////

	/**
	 * Clase manejadora del evento de ciertos botones de movimiento entre pantallas
	 * cuya función es coger el actionCommand del botón que informa de la ventana a
	 * la que moverse y mostrar dicha ventana
	 */
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
		case "inicio":
			mostrarInicio();
			break;
		case "menuInicio":
			mostrarMenuInicio();
			break;
		case "menuJugar":
			mostrarMenuJuego();
			break;
		case "juego":
			mostrarJuego();
			break;
		case "catalogoHoteles":
			mostrarCatalogo();
			break;
		default:
			throw new IllegalArgumentException("El valor " + pantalla + " no es un valor válido");
		}
	}

	/**
	 * Muestra la ventana de inicio que es una presentación de la aplicación
	 */
	private void mostrarInicio() {
		getPnInicio().add(getPnTituloEIdiomas(), BorderLayout.NORTH);
		((CardLayout) getPanelVentanas().getLayout()).show(getPanelVentanas(), "inicio");
	}

	/**
	 * Muestra la ventana del menú de inicio donde el usuario decidirá si quiere
	 * jugar o ir a reservar un hotel
	 */
	protected void mostrarMenuInicio() {
		setImagenAdaptadaTitulo(getLbTitulo(), "/img/titulo.png", 674, 294);
		getPnTituloEIdiomas().add(getPnIdiomas(), BorderLayout.EAST);
		getPnMenuInicio().add(getPnTituloEIdiomas(), BorderLayout.NORTH);
		getPnMenuInicio().add(getPnBtAtras(), BorderLayout.SOUTH);
		getBtAtras().setActionCommand("inicio");
		((CardLayout) getPanelVentanas().getLayout()).show(getPanelVentanas(), "menuInicio");
	}

	/**
	 * Muestra la ventana de menú del juego para que el usuario lea las reglas y
	 * decida si jugar o no
	 */
	private void mostrarMenuJuego() {
		getPnTituloJuegoEIdiomas().add(getPnIdiomas(), BorderLayout.EAST);
		getPnMenuJuego().add(getPnBtAtras(), BorderLayout.SOUTH);
		getBtAtras().setActionCommand("menuInicio");
		((CardLayout) getPanelVentanas().getLayout()).show(getPanelVentanas(), "menuJuego");
	}

	/**
	 * Muestra la ventana del juego para que el usuario interactue
	 */
	private void mostrarJuego() {
		// Lógica
		// Se empieza el juego de 0
		app.inicializarJuego();

		// Interfaz
		inicializarPnJuego();
		((CardLayout) getPanelVentanas().getLayout()).show(getPanelVentanas(), "juego");
	}

	/**
	 * Muestra la ventana del catalogo de hoteles en el que el usuario podrá buscar
	 * un hotel que le interese para informarse sobre él y reservarlo
	 */
	private void mostrarCatalogo() {
		// Muestra el titulo
		setImagenAdaptadaTitulo(getLbTitulo(), "/img/titulo.png", 210, 96);
		getPnTituloCatalogoHoteles().add(getPnTituloEIdiomas(), BorderLayout.CENTER);

		// Añade el boton para ir a la ventana anterior
		getPnCatalogoHoteles().add(getPnBtAtras(), BorderLayout.SOUTH);
		getBtAtras().setActionCommand("menuInicio");

		inicializarCatalogo();

		// Muestra la ventana
		((CardLayout) getPanelVentanas().getLayout()).show(getPanelVentanas(), "catalogoHoteles");
	}
	
	/**
	 * Muestra una ventana con la info de un hotel concreto en el que ha hecho click el usuario
	 */
	protected void mostrarInfoHotel() {
		getPnInfoHotel().add(getPnBtAtras(), BorderLayout.SOUTH);
		getBtAtras().setActionCommand("catalogoHoteles");
		
		// Muestra la ventana
		((CardLayout) getPanelVentanas().getLayout()).show(getPanelVentanas(), "infoHotel");
	}

	////////////////////// OTROS MÉTODOS //////////////////////

	/**
	 * Adapta el tamaño del titulo a la label
	 * 
	 * @param label      de titulo cuya imagen se quiere redimensionar
	 * @param rutaImagen nombre de la imagen
	 */
	private void setImagenAdaptadaTitulo(JLabel label, String rutaImagen, int width, int height) {
		Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
		Image imgEscalada = imgOriginal.getScaledInstance(width, height, Image.SCALE_FAST);
		ImageIcon icon = new ImageIcon(imgEscalada);
		label.setIcon(icon);
	}

	/**
	 * Adapta el tamaño de las imagenes en función del tamaño del componente que las
	 * contiene, en este caso un JButton
	 * 
	 * @param boton      cuya imagen se quiere redimensionar
	 * @param rutaImagen nombre de la imagen
	 */
	private void setImagenAdaptada(JButton boton, String rutaImagen, int ancho, int alto) {
		Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
		Image imgEscalada = imgOriginal.getScaledInstance(ancho, alto, Image.SCALE_FAST);
		ImageIcon icon = new ImageIcon(imgEscalada);
		boton.setIcon(icon);
		boton.setDisabledIcon(icon);
	}

	/**
	 * Inicializa el juego para comenzar una partida nueva desde 0
	 */
	private void inicializarPnJuego() {
		// Reinicia el panel de juego
		panelJuego.inicializar();
		// Reinicia las labels de las tiradas del dado
		getLbTiradas().setText(app.getTiradasRestantes() + "");
		getLbValor().setText("");
		// Oculta el panel de arriba de las eliminaciones
		mostrarEliminacion();
		getLbEliminaciones().setVisible(false);
	}

	/**
	 * Método usado por las clase VentanaFinalJuego para volver al menú en caso de
	 * que el usuario quiera
	 */
	protected void jugarDeNuevo() {
		mostrarMenuJuego();
	}

	private void tirarDado() {
		// Lógica
		app.tirarDado();
		actualizarValorDadoYTiradas();

		// Interfaz
		getBtDado().setEnabled(false);
		getLbDadoDisponible().setEnabled(false);
		getLbDadoDisponible().setVisible(false);
		panelJuego.habilitarBotones(true);
	}

	/**
	 * Actualiza los tooltips del tablero para que los muestren solo los botones que
	 * están habilitados en cada momento
	 */
	private void actualizarTooltipsTablero() {

		this.panelJuego.localizarTablero();
	}

	/**
	 * Actualiza la label que muestra el valor así como el número de tiradas
	 * restantes
	 */
	private void actualizarValorDadoYTiradas() {
		getLbValor().setText(app.getValorDado() + "");
		getLbTiradas().setText(app.getTiradasRestantes() + "");
	}

	protected void mostrarEliminacion() {
		getPnEliminaciones().removeAll();
		for (int i = 0; i < app.getNumeroEliminaciones(); i++)
			getPnEliminaciones().add(crearLbEnemigo(i));

		getLbEliminaciones().setVisible(true);
	}

	private JLabel crearLbEnemigo(int pos) {
		JLabel lb = new JLabel();
		lb.setSize(70, 70);
		setImagenAdaptadaTitulo(lb, "/img/" + app.getEnemigoEliminado(pos).getImagen(), 70, 70);
		return lb;
	}

	/**
	 * Muestra u oculta el panel de filtros en función del estado del JToggleButton
	 * btFiltros
	 */
	private void mostrarUOcultarFiltros() {
		getPnFiltros().setVisible(!getPnFiltros().isVisible());
	}

	/**
	 * Inicializa el catalogo para que se encuentre de la misma forma cada vez que
	 * se abre su ventana
	 */
	private void inicializarCatalogo() {
		// Crea los hoteles
		getPnHoteles().removeAll();
		crearPanelesHoteles();

		// Oculta el panel de filtros
		getBtAbrirFiltros().setSelected(false);
		getPnFiltros().setVisible(false);

		// Deselecciona todos los filtros
		for (int i = 0; i < getPnEncantamientos().getComponentCount(); i++)
			((JCheckBox) getPnEncantamientos().getComponent(i)).setSelected(false);
		// Vacía el buscador
		getTxBarraBusqueda().setText("");

		// Establecer el precio al inicial
		getSlPrecio().setValue(slPrecio.getMaximum());

		// Establece el combo de ubicaciones a "Todos"
		getCbUbicaciones().setSelectedIndex(0);
	}

	/**
	 * Genera los checkboxes para los filtros de encantamientos
	 */
	private void generarFiltrosParaEncantamientos() {
		for (int i = 0; i < app.getNumeroEncantamientosTotales(); i++)
			getPnEncantamientos().add(generarFiltroEncantamiento(i));
	}

	private JCheckBox generarFiltroEncantamiento(int pos) {
		JCheckBox encantamiento = new JCheckBox();
		encantamiento.setText(app.getEncantamientoEnLista(pos).getDiminutivo());
		encantamiento.setBackground(Color.DARK_GRAY);
		encantamiento.setForeground(Color.WHITE);
		return encantamiento;
	}

	/**
	 * Actualiza la label que informa al usuario del precio que ha introducido como
	 * filtro
	 */
	private void actualizarPrecio() {
		getLbValorPrecio().setText(getSlPrecio().getValue() + "");
	}

	private void crearPanelesHoteles() {
		// Si la ubicacion es española crea los hoteles en español
		if (ubicacion.getLanguage().equals("es"))
			for (int i = 0; i < app.getNumHoteles(); i++) {
				PanelHotel pH = new PanelHotel(this.app, this, app.getHotelES(i));
				getPnHoteles().add(pH);
			}

		// Si la ubicacion NO es española crea los hoteles en inglés
		else
			for (int i = 0; i < app.getNumHoteles(); i++) {
				PanelHotel pH = new PanelHotel(this.app, this, app.getHotelEN(i));
				getPnHoteles().add(pH);
			}
	}

	private void buscarHotelPorNombre() {
		getPnHoteles().removeAll();
		crearPanelesHoteles();
		String busqueda = getTxBarraBusqueda().getText();

		if (busqueda.equals("")) {
			validate();
			return;
		}

		else {
			String resultado = app.buscarCastilloPorNombre(busqueda);
			if (resultado == null)
				getPnHoteles().removeAll();
			else {
				int numHoteles = getPnHoteles().getComponentCount();
				for (int i = 0; i < numHoteles; i++) {
					// Los hoteles que no coincidan con la busqueda introducida en la barra de
					// busqueda se borran
					if (resultado.equals(((PanelHotel) getPnHoteles().getComponent(i)).getHotel().getCodigo())) {
						PanelHotel hotelEncontrado = (PanelHotel) getPnHoteles().getComponent(i);
						getPnHoteles().removeAll();
						getPnHoteles().add(hotelEncontrado);
						break;
					}

				}

			}
		}
		repaint();
		validate();
	}

	private void aplicarFiltro() {
		getPnHoteles().removeAll();
		crearPanelesHoteles();

		String ubicacion = (String) getCbUbicaciones().getSelectedItem();
		double precio = getSlPrecio().getValue();
		List<String> encantamientos = getEncantamientosSeleccionados();

		List<String> codigosCastillos = app.establecerFiltro(ubicacion, precio, encantamientos);
		List<PanelHotel> castillosPermitidos = new ArrayList<>();

		// Si no hay castillos con los criterios introducidos
		if (codigosCastillos.isEmpty()) {
			getPnHoteles().removeAll();
			repaint();
		}
		
		// Si hay mínimo un castillo que cumple los filtros
		else {
			for (int i = 0; i < getPnHoteles().getComponentCount(); i++) {
				PanelHotel hotel = (PanelHotel) getPnHoteles().getComponent(i);
				// Si el código del castillo está en la lista de códigos es porque cumple los
				// filtros
				if (codigosCastillos.contains(hotel.getHotel().getCodigo()))
					castillosPermitidos.add(hotel);
			}
			getPnHoteles().removeAll();
			for (PanelHotel hotel : castillosPermitidos)
				getPnHoteles().add(hotel);
		}
		validate();
	}

	/**
	 * @return lista que almacena los textos que representan cada uno de los
	 *         encantamientos seleccionados en el panel de filtro
	 */
	private List<String> getEncantamientosSeleccionados() {
		List<String> encantamientosSeleccionados = new ArrayList<>();
		for (int i = 0; i < getPnEncantamientos().getComponentCount(); i++) {
			JCheckBox encantamiento = (JCheckBox) getPnEncantamientos().getComponent(i);
			if (encantamiento.isSelected())
				encantamientosSeleccionados.add(encantamiento.getText());
		}

		return encantamientosSeleccionados;
	}

	
	private class MuestraInfoDiminutivos extends MouseAdapter {
		
		@Override
		public void mousePressed(MouseEvent e) {
			getTxInfoDiminutivos().setVisible(true);
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			getTxInfoDiminutivos().setVisible(false);
		}

	}


	protected JPanel getPnInfoHotel() {
		if (pnInfoHotel == null) {
			pnInfoHotel = new JPanel();
			pnInfoHotel.setBackground(Color.DARK_GRAY);
			pnInfoHotel.setLayout(new BorderLayout(0, 0));
		}
		return pnInfoHotel;
	}
}
