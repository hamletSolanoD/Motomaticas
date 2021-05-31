package VentanasProyecto;

import javax.swing.JPanel;

import RecursosCustomizados.JPanel_PlanoCartesiano;
import ValoresDefault.Constantes;
import ValoresDefault.Constantes.TipoObjetoAlgebraico;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import ObjetosLogicos.ObjetoAlgebraico;
import ObjetosLogicos.OperacionGeneral;
import ObjetosLogicos.Vector3;

import java.awt.Color;

public class JPanelVisualizar extends JPanel {

	/**
	 * Create the panel.
	 */
	private JPanel_PlanoCartesiano planoCartesianoXY;
	private JPanel_PlanoCartesiano planoCartesianoXZ;
	private JPanel_PlanoCartesiano planoCartesianoYZ;
	private JPanel Planos;

	public JPanelVisualizar() {
		setBackground(Constantes.PrincipalColor);
		setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane = new JScrollPane();
	    Planos = new JPanel(new GridLayout(0,1,0,0));
		Planos.setPreferredSize(new Dimension(928,794*6));
		Planos.setBackground(Constantes.PrincipalColor);
		scrollPane.setViewportView(Planos);
		
		
		
		
		planoCartesianoXY = new JPanel_PlanoCartesiano(42);
		planoCartesianoXY.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		planoCartesianoXZ = new JPanel_PlanoCartesiano(42);
		planoCartesianoXZ.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		planoCartesianoYZ = new JPanel_PlanoCartesiano(42);
		planoCartesianoYZ.setBorder(new LineBorder(new Color(0, 0, 0), 2));

		
		JLabel JLabel_XY = new JLabel("Plano X-Y");
		JLabel_XY.setFont(Constantes.Titulos);
		JLabel_XY.setForeground(Constantes.DetallesSegundoColor);
		JLabel_XY.setHorizontalAlignment(SwingConstants.CENTER);
		Planos.add(JLabel_XY);
		Planos.add(planoCartesianoXY);
		
		JLabel JLabel_XY_1 = new JLabel("Plano X-Z");
		JLabel_XY_1.setFont(Constantes.Titulos);
		JLabel_XY_1.setForeground(Constantes.DetallesSegundoColor);
		JLabel_XY_1.setHorizontalAlignment(SwingConstants.CENTER);
		Planos.add(JLabel_XY_1);
		Planos.add(planoCartesianoXZ);
		
		JLabel JLabel_XY_1_1 = new JLabel("Plano Y-Z");
		JLabel_XY_1_1.setForeground(Constantes.DetallesSegundoColor);
		JLabel_XY_1_1.setFont(Constantes.Titulos);
		JLabel_XY_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		Planos.add(JLabel_XY_1_1);
		Planos.add(planoCartesianoYZ);

		
		
		
		
		add(scrollPane, BorderLayout.CENTER);
	}
	public void MostrarVectores(Vector3 Vector) {

		planoCartesianoYZ.setAngulo(90);
		planoCartesianoXZ.setAngulo(90);
		planoCartesianoXY.setAngulo(90);

	if(Vector.getMagnitudX() == 0) {
		planoCartesianoYZ.setAngulo(Vector.getThetaZ());
	}
	else if(Vector.getMagnitudY() == 0) {
		  planoCartesianoXZ.setAngulo(Vector.getThetaX());
	}
	else if(Vector.getMagnitudZ() == 0) {
		  planoCartesianoXY.setAngulo(Vector.getThetaX());
	}
	else {
		  planoCartesianoYZ.setAngulo(Vector.getThetaY());
		  planoCartesianoXZ.setAngulo(Vector.getThetaZ());
		  planoCartesianoXY.setAngulo(Vector.getThetaX());
	}
	}
	}
