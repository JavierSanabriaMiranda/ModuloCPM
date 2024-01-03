package uo.cpm.l7.ui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import uo.cpm.l7.service.Horrotel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ResourceBundle;

public class PanelJuego extends JPanel {


	private static final long serialVersionUID = 1L;
	private Horrotel app;
	private RedimensionarImagenesTablero rIT = new RedimensionarImagenesTablero();
	private MoverCazafantasmas mC = new MoverCazafantasmas();
	private VentanaPrincipal vp;
	
	/**
	 * Create the panel.
	 */
	public PanelJuego(Horrotel app, VentanaPrincipal vp) {
		this.app = app;
		this.vp = vp;
		setBackground(Color.DARK_GRAY);
		setLayout(new GridLayout(app.getFilasTablero(), app.getColumnasTablero(), 0, 0));
		crearBotonesTablero();
		addComponentListener(rIT);
	}
	
	/**
	 * Inicializa el juego, es decir, habilita el dado, inicializa de nuevo las imagenes del tablero y deshabilita todos los 
	 * botones del mismo
	 */
	protected void inicializar() {
		vp.getBtDado().setEnabled(true);
		vp.getLbDadoDisponible().setEnabled(true);
		habilitarBotones(false);
		for (int i = 0; i < getComponentCount(); i++) {
			JButton boton = (JButton) getComponent(i);
			String[] coordsBoton = boton.getActionCommand().split(",");
			// Recoge la fila
			int fila = Integer.parseInt(coordsBoton[0]);
			// Recoge la columna
			int columna = Integer.parseInt(coordsBoton[1]);
			setImagenAdaptada(boton, "/img/" + app.getCasilla(fila, columna).getImagen());
		}	
	}

	/**
	 * Crea todos los botones del tablero estableciendoles como actionCommand su
	 * fila y su columna separadas por una coma, los crea deshabilitados y con fondo
	 * negro así como con icono en caso de coincidir con una casilla de personaje o
	 * invisibles si coinciden con un fondo
	 */
	private void crearBotonesTablero() {
		// Se crean los botones añadiendoles de action command su coordenada en el
		// tablero separada por una coma
		for (int i = 0; i < app.getFilasTablero(); i++)
			for (int j = 0; j < app.getColumnasTablero(); j++) {
				JButton boton = new JButton();
				boton.setActionCommand(String.format("%d,%d", i, j));
				// Cambia el color de fondo del botón
				boton.setBackground(Color.BLACK);

				// Se inhabilitan los botones inicialmente
				boton.setEnabled(false);

				// Se le añade al boton la accion al pulsarse
				boton.addActionListener(mC);

				// Si es borde se pone invisible
				if (app.isBorde(app.getCasilla(i, j)))
					boton.setVisible(false);
				add(boton);
			}
	}

	/**
	 * Establece para el botón introducido como parametro, una imagen que se ajusta al tamaño del mismo, aun cuando este se
	 * redimensiona 
	 * 
	 * @param boton al que aplicar la imagen como icono
	 * @param rutaImagen a aplicar
	 */
	private void setImagenAdaptada(JButton boton, String rutaImagen) {
		Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
		// Se establece el mismo tamaño para altura y anchura con el fin de mantener las proporciones de la imagen
		// ya que las imagenes son cuadradas
		Image imgEscalada = imgOriginal.getScaledInstance(boton.getHeight(), boton.getHeight(), Image.SCALE_FAST);
		ImageIcon icon = new ImageIcon(imgEscalada);
		boton.setIcon(icon);
		boton.setDisabledIcon(icon);
	}

	/**
	 * Habilita o deshabilita todos los botones del panel que sean interactuables
	 * (cazafantasmas) en función del parámetro introducido
	 * 
	 * @param booleano que identifica si se habilitan o se deshabilitan los botones.
	 *                 Si está a true se habilitan, si está a false se deshabilitan
	 */
	protected void habilitarBotones(boolean booleano) {
		for (int i = 0; i < getComponentCount(); i++) {
			JButton boton = (JButton) getComponent(i);

			String[] coordsBoton = boton.getActionCommand().split(",");
			// Recoge la fila
			int fila = Integer.parseInt(coordsBoton[0]);
			// Recoge la columna
			int columna = Integer.parseInt(coordsBoton[1]);

			// Si el valor es falso se ponen todos a falso
			if (!booleano) 
				boton.setEnabled(booleano);
			// Si el valor es verdadero se ponen enabled solo los cazafantasmas
			else
				// Si el boton es un cazafantasmas
				if (app.isCazafantasmas(app.getCasilla(fila, columna)))
					boton.setEnabled(booleano);

		}

	}

	/**
	 * Establece el tooltip localizado de los botones que estan habilitados (Solo los cazafantasmas)
	 */
	protected void localizarTablero() {
		ResourceBundle textos = ResourceBundle.getBundle("rcs/textos", vp.getUbicacion());
		
		for (int i = 0; i < getComponentCount(); i++) {
			JButton boton = (JButton) getComponent(i);
			if (boton.isEnabled())
				boton.setToolTipText(textos.getString("tooltipCasilla"));
		}
	}
	
	private void quitarTooltips() {
		for (int i = 0; i < getComponentCount(); i++) {
			JButton boton = (JButton) getComponent(i);
			if (boton.isEnabled())
				boton.setToolTipText(null);
		}
	}

	/**
	 * Clase cuya función es redimensionar las imagenes del tablero una vez se aumenta el tamaño del panel que los contiene
	 */
	 class RedimensionarImagenesTablero extends ComponentAdapter {

		@Override
		public void componentResized(ComponentEvent e) {
			JPanel fuente = (JPanel) e.getSource();
			// Añade las fotos a los botones
			for (int i = 0; i < fuente.getComponentCount(); i++) {
				JButton boton = (JButton) fuente.getComponent(i);
				// Recoge las coords del actionCommand del boton
				String[] coordsBoton = boton.getActionCommand().split(",");
				// Recoge la fila
				int fila = Integer.parseInt(coordsBoton[0]);
				// Recoge la columna
				int columna = Integer.parseInt(coordsBoton[1]);
				// Si es una casilla que tenga foto se la pone
				if (!app.isBorde(app.getCasilla(fila, columna)) && !app.isVacio(app.getCasilla(fila, columna)))
					setImagenAdaptada(boton, "/img/" + app.getCasilla(fila, columna).getImagen());
			}

		}

	}

	 /**
	  * Clase que sirve para el funcionamiento de las casillas del tablero, ejecuta el movimiento de los personajes tanto en la
	  * lógica como en la interfaz
	  */
	class MoverCazafantasmas implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton boton = (JButton) e.getSource();

			// Recoge las coords del actionCommand del boton
			String[] coordsBoton = boton.getActionCommand().split(",");
			// Recoge la fila
			int fila = Integer.parseInt(coordsBoton[0]);
			// Recoge la columna
			int columna = Integer.parseInt(coordsBoton[1]);

			// Lógica e Interfaz
			// Si el movimiento a realizar es válido se actualizan las imagenes de los
			// botones (Se borra la del botón pulsado y se pasa
			// la imagen a la nueva posición)
			if (app.moverACasilla(fila, columna)) {
				// Borra el icono del boton pulsado

				// Actualiza el icono del botón destino del cazafantasmas
				actualizarImagenBotones();
				
				// Muestra en el panel de eliminaciones el enemigo eliminado
				vp.mostrarEliminacion();
				
				quitarTooltips();
				habilitarBotones(false);
				// Se habilita el dado y se muestra la label que informa de que se puede lanzar
				vp.getBtDado().setEnabled(true);
				vp.getLbDadoDisponible().setEnabled(true);
				vp.getLbDadoDisponible().setVisible(true);
				
				
				// Si el movimiento acaba la partida
				if (app.isJuegoFinalizado()) {
					crearPanelFinalDeJuego();
				}

			}
		}

	}
	
	/**
	 * Crea un panel cuando el juego finaliza, ofreciendo al usuario la opción de guardar su descuento o de jugar
	 * de nuevo
	 */
	private void crearPanelFinalDeJuego() {
		VentanaFinalJuego finalJuego = new VentanaFinalJuego(app, vp, vp.getUbicacion());
		finalJuego.setLocationRelativeTo(null);
		finalJuego.setVisible(true);
	}

	/**
	 * Actualiza la imagen del botón cuyo ActionCommand coincide con la fila y la
	 * columna introducida como parámetro
	 * 
	 * @param fila    del botón
	 * @param columna del botón
	 */
	private void actualizarImagenBotones() {
		for (int i = 0; i < getComponentCount(); i++) {
			JButton boton = (JButton) getComponent(i);
			// Recoge las coords del actionCommand del boton
			String[] coordsBoton = boton.getActionCommand().split(",");
			// Recoge la fila
			int fila = Integer.parseInt(coordsBoton[0]);
			// Recoge la columna
			int columna = Integer.parseInt(coordsBoton[1]);
			
			if (!app.isBorde(app.getCasilla(fila, columna)))
				if (app.isVacio(app.getCasilla(fila, columna)))
					boton.setIcon(null);
				else 
					setImagenAdaptada(boton, "/img/" + app.getCasilla(fila, columna).getImagen());
		}
	}

}
