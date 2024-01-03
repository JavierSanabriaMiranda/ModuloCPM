package uo.cpm.l7.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import uo.cpm.l7.service.Horrotel;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.Image;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextPane;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.Box;

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



}
