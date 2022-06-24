package Motomaticas.RecursosCustomizados;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;

import javax.imageio.ImageIO;
import javax.management.ConstructorParameters;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout.Constraints;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseListener;
import java.io.IOException;

import Motomaticas.ValoresDefault.Constantes;

public class JPanelDragAndDropBotonesAritmeticos extends JPanel implements MouseListener {

	ArrayList<BotonAritmetico> botonesAritmeticos = new ArrayList<BotonAritmetico>();
	BotonAritmetico BotonMatematicoEnfocado;
	GridBagConstraints constrainForNewButtons = new GridBagConstraints();
	BotonAritmetico BotonAritmeticoArrastrado, BotonAritmeticoEnPosicionActual;
	private int X, Y;// posicion de insercion en la consola
	private int CeldasDeAncho = 10;

	public JPanelDragAndDropBotonesAritmeticos(int CeldasDeAncho) {
		this.setBackground(Constantes.PrincipalColor);
		this.addMouseListener(this);
		this.setLayout(new GridBagLayout());
		this.CeldasDeAncho = CeldasDeAncho;
	}

	// Agregar boton aritmetico a la seccion de operaciones registradas o historial
	// de operaciones
	public void AgregarBotonAritmetico(BotonAritmetico botonAritmetico) {

		botonAritmetico.setFont(Constantes.botones);
		botonAritmetico.addMouseListener(this);
		botonesAritmeticos.add(botonAritmetico);
		refrescarPanel();

	}

	public void QuitarBotonAritmetico(BotonAritmetico botonAritmetico) {

		botonesAritmeticos.remove(botonAritmetico);
		refrescarPanel();

	}

	public ArrayList<BotonAritmetico> getListaBotonesAritmeticos() {
		return botonesAritmeticos;
	}

	private void refrescarPanel() {
		X = Y = 0;
		this.removeAll();
		for (BotonAritmetico btnAritmetico : botonesAritmeticos) {
			JPanel JbuttonConDeleteOption = new JPanel(new BorderLayout());

			

			constrainForNewButtons.gridheight = 1;
			constrainForNewButtons.insets = new Insets(2, 2, 2, 2);
			constrainForNewButtons.fill = GridBagConstraints.HORIZONTAL;
			double ancho = Math.ceil(btnAritmetico.getEtiqueta().length() / 4.0f);
			if ((X + ancho) >= CeldasDeAncho) {
				X = 0;
				Y++;

			}
			constrainForNewButtons.gridwidth = (int) ancho;
			constrainForNewButtons.gridx = X;
			constrainForNewButtons.gridy = Y;
			// constrainForNewButtons.anchor = GridBagConstraints.CENTER;

			JbuttonConDeleteOption.add(btnAritmetico,BorderLayout.CENTER);
			JButton btnCerrar = null;
			try {
				btnCerrar = new JButton(
					new ImageIcon(
						(ImageIO.read
						(this.getClass().getResource("/borrar.png")).
						getScaledInstance(getWidth()/50, getWidth()/50, 
						Image.SCALE_SMOOTH))));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			JbuttonConDeleteOption.add(btnCerrar,BorderLayout.NORTH);




			this.add(JbuttonConDeleteOption, constrainForNewButtons);
			X += ancho;
			if (X >= CeldasDeAncho) {
				X = 0;
				Y++;
			}

		}
		this.paintAll(this.getGraphics());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getComponent().getClass() == BotonAritmetico.class) {

		}

	}

	@Override
	public void mousePressed(MouseEvent e) {

		if (e.getComponent().getClass() == BotonAritmetico.class) {
			// System.out.println("Mouse pressed btn");
			BotonAritmeticoArrastrado = (BotonAritmetico) e.getSource();

			for (BotonAritmetico btnAritmetico : botonesAritmeticos) {
				btnAritmetico.setBackground(Constantes.SecundarioColor);
				btnAritmetico.setForeground(Constantes.DetallesSegundoColor);
			}
			BotonAritmeticoArrastrado.setBackground(Constantes.DetallesColor);
			BotonAritmeticoArrastrado.setForeground(Constantes.PrincipalColor);

		} else {
			BotonAritmeticoArrastrado = null;
			// System.out.println("Mouse pressed panel");
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		// System.out.println("Mouse relesed");
		if (BotonAritmeticoEnPosicionActual != null && BotonAritmeticoArrastrado != null) {

			botonesAritmeticos.remove(BotonAritmeticoArrastrado);
			int desface = (botonesAritmeticos.indexOf(BotonAritmeticoEnPosicionActual) > botonesAritmeticos
					.indexOf(BotonAritmeticoArrastrado)) ? 1 : 0;

			botonesAritmeticos.add(botonesAritmeticos.indexOf(BotonAritmeticoEnPosicionActual) + desface,
					BotonAritmeticoArrastrado);

			refrescarPanel();
		}

		BotonAritmeticoArrastrado = null;
		BotonAritmeticoEnPosicionActual = null;

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

		// System.out.println(e.getComponent().getClass());
		if (e.getComponent().getClass() == BotonAritmetico.class) {
			BotonAritmeticoEnPosicionActual = (BotonAritmetico) e.getSource();
			// System.out.println("Mouse entered in bttn");

		} else {
			// System.out.println("Mouse entered in Panel");

			BotonAritmeticoEnPosicionActual = null;
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

		if (e.getComponent().getClass() == BotonAritmetico.class) {

		}

	}

}
