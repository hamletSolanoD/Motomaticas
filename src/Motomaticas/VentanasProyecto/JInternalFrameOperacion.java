package Motomaticas.VentanasProyecto;

import java.awt.GridBagConstraints;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;

import javax.swing.JToolBar;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import Motomaticas.ObjetosLogicos.motorMatematico.operaciones.OperacionMatematica;
import Motomaticas.ObjetosLogicos.motorMatematico.operaciones.operacionesMatematicasGenericasInterface;
import ModulosImportados.Vector3D.*;
import Motomaticas.ObjetosLogicos.motorMatematico.*;
import Motomaticas.ObjetosLogicos.motorMatematico.variables.UnidadMatematica;
import Motomaticas.RecursosCustomizados.BotonAritmetico;
import Motomaticas.RecursosCustomizados.JDialog_CrearFuncionMatematica;
import Motomaticas.RecursosCustomizados.JDialog_CrearOperacionMatematica;
import Motomaticas.RecursosCustomizados.JDialog_CrearUnidadMatematica;
import Motomaticas.ValoresDefault.Constantes;
import Motomaticas.ValoresDefault.Constantes.TipoDeErrorMatematico;
import Motomaticas.ValoresDefault.Constantes.TipoObjetoMatematico;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextPane;

import javax.swing.JOptionPane;

import java.awt.Component;

import javax.swing.Box;
import java.awt.GridBagLayout;

//suboperacion JFrame
public class JInternalFrameOperacion extends JInternalFrame implements ActionListener, Serializable {

	////////////////////// Diseño //////////////////

	////////////////// LOGICA /////////////
	private String Titulo;
	private OperacionGeneral OperacionGeneral = new OperacionGeneral();
	private BotonAritmetico BotonMatematicoEnfocado;

	////////// GRAFICA ///////////
	private JPanel JPanelOperaciones;
	private JTextPane ConsolaOutput;
	private JTextPane PropiedadesDisplay;

	private JPanel OperacionesMatematicasAreaDeTrabajo;

	private void crearPanelIzquierdo(JPanel panel) {
		panel.setBackground(Constantes.PrincipalColor);
		panel.setLayout(new BorderLayout());



		//Seccion de Botones para definir la vista de detalles lateral derecha 
		JPanel Titular = new JPanel(new BorderLayout());
		Titular.setBackground(Constantes.PrincipalColor);
		JPanel SeccionesBotones = new JPanel(new GridLayout());
		JButton Unidades = Constantes.BotonCuadrado("Unidades", Constantes.SecundarioColor, Constantes.TerciarioColor);
		JButton Operaciones = Constantes.BotonCuadrado("Operaciones", Constantes.SecundarioColor,
				Constantes.TerciarioColor);
		JButton Funciones = Constantes.BotonCuadrado("Funciones", Constantes.SecundarioColor,
				Constantes.TerciarioColor);
		Unidades.setActionCommand("Unidadeds");
		Operaciones.setActionCommand("Operaciones");
		Funciones.setActionCommand("Funciones");
		Unidades.addActionListener(this);
		Operaciones.addActionListener(this);
		Funciones.addActionListener(this);
		SeccionesBotones.add(Unidades);
		SeccionesBotones.add(Operaciones);
		SeccionesBotones.add(Funciones);
		Titular.add(SeccionesBotones,BorderLayout.EAST);
		



		//seccion de operaciones y de resultados

		JPanel OperacionesYDetalles = new JPanel(new GridLayout(0,1));

		OperacionesMatematicasAreaDeTrabajo = new JPanel();
		

		





		JPanel TerminalJPanel = new JPanel(new GridLayout(1,0));
		JPanel DetallesOperacion = new JPanel();
		JPanel resultadoOperacion = new JPanel();
		

		TerminalJPanel.add(DetallesOperacion);
		TerminalJPanel.add(resultadoOperacion);






		OperacionesYDetalles.add(OperacionesMatematicasAreaDeTrabajo);
		OperacionesYDetalles.add(TerminalJPanel);
		









		panel.add(Titular, BorderLayout.NORTH);
		panel.add(OperacionesYDetalles, BorderLayout.CENTER);

	}

	private void crearPanelDerecho(JPanel panel) {
		panel.setBackground(Constantes.PrincipalColor);

	}

	// crear el jframe panel de la sub operacion con todos sus componentes
	public JInternalFrameOperacion(String Titulo) {
		this.Titulo = Titulo;

		setBounds(0, 0, getParent().WIDTH / 6, getParent().HEIGHT / 4);
		setTitle(Titulo);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JSplitPane JGeneralSplitPanel = new JSplitPane();
		getContentPane().add(JGeneralSplitPanel, BorderLayout.CENTER);

		JPanel GeneralDetallesPanelDerecho = new JPanel();
		crearPanelDerecho(GeneralDetallesPanelDerecho);
		JPanel GeneralOperacionPanelIzquierdo = new JPanel();
		crearPanelIzquierdo(GeneralOperacionPanelIzquierdo);

		JGeneralSplitPanel.setLeftComponent(GeneralOperacionPanelIzquierdo);
		JGeneralSplitPanel.setRightComponent(GeneralDetallesPanelDerecho);
		JGeneralSplitPanel.setResizeWeight(0.78);

		setVisible(true);
	}



	// Quitar boton aritmetico a la seccion de operaciones registradas o historial
	// de operaciones
	private void QuitarBotonAritmetico(BotonAritmetico botonAritmetico) {
		BotonesAritmeticos.remove(botonAritmetico);
		JPanelOperaciones.remove(botonAritmetico);
		getContentPane().paintAll(getContentPane().getGraphics());
	}

	// Calcular la salida general de la operacion para mostrarla en el cuadro de
	// salida
	public void CalcularConsolaOutput() {
		ConsolaOutput.setText(""); /// reset output
		OperacionGeneral.HardReset();
		MainApunteFrame.panel_Matematico.HardReset_JPanelMatematico();

		for (BotonAritmetico i : BotonesAritmeticos) {
			ConsolaOutput.setText(ConsolaOutput.getText() + i.getEtiqueta());
			OperacionGeneral.getObjetosMatematicos().add(i.getObjetoMatematico());
		} //// mostrar los botones en el lado de operaciones

		TipoDeErrorMatematico PosibleError = OperacionGeneral.CalcularOperacion();
		if (PosibleError == null) { // si hay un resultado que mostrar
			if (OperacionGeneral.getResultado().getTipoDeObjetoMatematico() == TipoObjetoMatematico.Unidad) {
				ConsolaOutput.setForeground(Constantes.DetallesSegundoColor);
				ConsolaOutput.setText(ConsolaOutput.getText() + " = "
						+ ((UnidadMatematica) OperacionGeneral.getResultado()).toStringReducido());

				// MainApunteFrame.panel_Visualizar.MostrarVectores(new Vector3D("", 90, 90, 90,
				// 0));
			}
		} else { // si no hay un resultado que mostrar
			ConsolaOutput.setForeground(Constantes.DetallesColor);
			ConsolaOutput.setText("Error en la operacion: " + PosibleError.toString());
			// MainApunteFrame.panel_Visualizar.MostrarVectores(new Vector3D("", 90, 90, 90,
			// 0));

		}
	}

	/*
	 * define las acciones a realizar por cada boton de operacion
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getActionCommand()) {
			case "--":
				if (BotonMatematicoEnfocado != null) {
					OperacionGeneral.getObjetosMatematicos().remove(BotonMatematicoEnfocado.getObjetoMatematico());
					QuitarBotonAritmetico(BotonMatematicoEnfocado);
					BotonMatematicoEnfocado = null;
					CalcularConsolaOutput();

					if (OperacionGeneral.getError() == null) {
						PropiedadesDisplay.setText(OperacionGeneral.getResultado().toString());
						// System.out.println("Borrado y con resultado");
					} else {
						PropiedadesDisplay.setText("Sin resultado");
						// System.out.println("Borrado y sin resultado");
					}

				} else {
					JOptionPane.showMessageDialog(this, "Operacion no seleccionada", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

				break;
			case "{":
				AgregarBotonAritmetico(
						new BotonAritmetico(new operacionesMatematicasGenericasInterface.llaveIzquierda()));
				CalcularConsolaOutput();
				break;
			case "}":
				AgregarBotonAritmetico(
						new BotonAritmetico(new operacionesMatematicasGenericasInterface.llaveDerecha()));
				CalcularConsolaOutput();
				break;
			case "+U":
				JDialog_CrearUnidadMatematica creadorUnidad = new JDialog_CrearUnidadMatematica(
						(JFrame) SwingUtilities.getRoot(this), "crear unidad matematica");
				UnidadMatematica nuevaUnidad = creadorUnidad.getUnidadMatematicaCreada();
				if (nuevaUnidad != null) {
					AgregarBotonAritmetico(new BotonAritmetico(nuevaUnidad));
					CalcularConsolaOutput();
				}
				break;
			case "...":
				JDialog_CrearOperacionMatematica creadorOperacion = new JDialog_CrearOperacionMatematica(
						(JFrame) SwingUtilities.getRoot(this), "crear operacion matematica");
				OperacionMatematica operacionNueva = creadorOperacion.getOperacionMatematicaCreada();
				if (operacionNueva != null) {
					AgregarBotonAritmetico(new BotonAritmetico(operacionNueva));
					CalcularConsolaOutput();
				}
				break;
			case "F":
				JDialog_CrearFuncionMatematica creadorFunciones = new JDialog_CrearFuncionMatematica(
						(JFrame) SwingUtilities.getRoot(this), "crear operacion matematica");
				UnidadMatematica resultadoFuncion = creadorFunciones.getUnidadMatematicaResultadoFuncion();
				if (resultadoFuncion != null) {
					AgregarBotonAritmetico(new BotonAritmetico(resultadoFuncion));
					CalcularConsolaOutput();
				}
				break;
		}

	}

}
