package Motomaticas.VentanasProyecto;

import java.io.Serializable;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextPane;

import Motomaticas.ValoresDefault.Constantes;

public class JPanelNotas extends JPanel implements Serializable {

	/**
	 * Create the panel.
	 */
	public JPanelNotas() {
		setLayout(new BorderLayout(0, 0));
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(Constantes.PrincipalColor);
		textPane.setFont(Constantes.textoNormal);
		textPane.setForeground(Constantes.DetallesSegundoColor);

		add(textPane, BorderLayout.CENTER);

	}

}
