package uo.cpm.l7;

import uo.cpm.l7.service.Horrotel;
import uo.cpm.l7.ui.VentanaPrincipal;

public class Main {

	public static void main(String[] args) {
		Horrotel app = new Horrotel();
		try {
			VentanaPrincipal frame = new VentanaPrincipal(app);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
