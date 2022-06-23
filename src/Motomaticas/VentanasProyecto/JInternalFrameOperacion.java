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
	private ArrayList<BotonAritmetico> BotonesAritmeticos = new ArrayList<BotonAritmetico>();
	private JPanel JPanelOperaciones;
	private JTextPane ConsolaOutput;
	private JTextPane PropiedadesDisplay;

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

		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(Constantes.SecundarioColor);
		getContentPane().add(toolBar, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Constantes.SecundarioColor);
		toolBar.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Constantes.SecundarioColor);
		panel_8.setLayout(new GridLayout(1, 0, 0, 0));
		scrollPane.setViewportView(panel_8);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Constantes.SecundarioColor);
		panel_8.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		panel_2.add(horizontalStrut_2, BorderLayout.WEST);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel_2.add(horizontalStrut, BorderLayout.EAST);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Constantes.SecundarioColor);
		panel_2.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));

		JButton AgregarUnidades = Constantes.BotonCuadrado("+U", Constantes.DetallesColor, Constantes.PrincipalColor);
		panel_3.add(AgregarUnidades);
		AgregarUnidades.setActionCommand("+U");
		AgregarUnidades.addActionListener(this);
		AgregarUnidades.setFont(Constantes.textoNormal);

		JButton AgregarFuncionCompleja = Constantes.BotonCuadrado("F", Constantes.DetallesColor, Constantes.PrincipalColor);
		panel_3.add(AgregarFuncionCompleja);
		AgregarFuncionCompleja.setActionCommand("F");
		AgregarFuncionCompleja.addActionListener(this);
		AgregarFuncionCompleja.setFont(Constantes.textoNormal);

		JButton AgregarOperacion = Constantes.BotonCuadrado("...", Constantes.DetallesColor, Constantes.PrincipalColor);
		panel_3.add(AgregarOperacion);
		AgregarOperacion.setActionCommand("...");
		AgregarOperacion.addActionListener(this);
		AgregarOperacion.setFont(Constantes.textoNormal);
		
		JButton JerarquiaDer = Constantes.BotonCuadrado("{", Constantes.DetallesColor, Constantes.PrincipalColor);
		panel_3.add(JerarquiaDer);
		JerarquiaDer.setActionCommand("{");
		JerarquiaDer.addActionListener(this);
		JerarquiaDer.setFont(Constantes.textoNormal);

		JButton JerarquiaIzq = Constantes.BotonCuadrado("}", Constantes.DetallesColor, Constantes.PrincipalColor);
		panel_3.add(JerarquiaIzq);
		JerarquiaIzq.setActionCommand("}");
		JerarquiaIzq.addActionListener(this);
		JerarquiaIzq.setFont(Constantes.textoNormal);

		JButton EliminarOperacion = Constantes.BotonCuadrado("--", Constantes.DetallesColor, Constantes.PrincipalColor);
		panel_3.add(EliminarOperacion);
		EliminarOperacion.setActionCommand("--");
		EliminarOperacion.addActionListener(this);
		EliminarOperacion.setFont(Constantes.textoNormal);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.50);
		splitPane.setDividerLocation(0.50);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		JScrollPane Operaciones = new JScrollPane();
		Operaciones.setBackground(Constantes.PrincipalColor);
		Operaciones.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Operaciones.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		splitPane.setLeftComponent(Operaciones);

		JPanelOperaciones = new JPanel();
		JPanelOperaciones.setBackground(Constantes.PrincipalColor);
		Operaciones.setViewportView(JPanelOperaciones);
		GridBagLayout gbl_JPanelOperaciones = new GridBagLayout();
		JPanelOperaciones.setLayout(gbl_JPanelOperaciones);

		JPanel JPanel_Output = new JPanel();
		splitPane.setRightComponent(JPanel_Output);
		JPanel_Output.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane JScroll_Editor = new JScrollPane();
		JScroll_Editor.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		JScroll_Editor.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JPanel_Output.add(JScroll_Editor);

		PropiedadesDisplay = new JTextPane();
		PropiedadesDisplay.setBackground(Constantes.PrincipalColor);

		PropiedadesDisplay.setFont(Constantes.textoNormal);
		PropiedadesDisplay.setEditable(false);
		JScroll_Editor.setViewportView(PropiedadesDisplay);

		JScrollPane JScroll_Consola = new JScrollPane();
		JPanel_Output.add(JScroll_Consola);
		JScroll_Consola.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		JScroll_Consola.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		JPanel JPanel_Consola = new JPanel();
		JScroll_Consola.setViewportView(JPanel_Consola);
		JPanel_Consola.setLayout(new BorderLayout(0, 0));

		ConsolaOutput = new JTextPane();
		ConsolaOutput.setBackground(Constantes.PrincipalColor);
		ConsolaOutput.setEditable(false);
		ConsolaOutput.setFont(Constantes.textoNormal);
		ConsolaOutput.setPreferredSize(JPanel_Consola.getSize());
		JPanel_Consola.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent componentEvent) {
				ConsolaOutput.setPreferredSize(JPanel_Consola.getSize());
			}
		});
		JPanel_Consola.add(ConsolaOutput, BorderLayout.CENTER);
		// test();
		setVisible(true);
	}

	// Agregar boton aritmetico a la seccion de operaciones registradas o historial
	// de operaciones
	private void AgregarBotonAritmetico(BotonAritmetico botonAritmetico) {
		botonAritmetico.setFont(Constantes.botones);
		botonAritmetico.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (!botonAritmetico.isSeleccionado()) {
					for (BotonAritmetico f : BotonesAritmeticos) {
						f.setSeleccionado(false);
						f.setBackground(Constantes.SecundarioColor);
						f.setForeground(Constantes.DetallesSegundoColor);
						f.setFont(Constantes.botones);
					}
					botonAritmetico.setSeleccionado(true);
					botonAritmetico.setBackground(Constantes.DetallesColor);
					botonAritmetico.setForeground(Constantes.PrincipalColor);
					botonAritmetico.setFont(Constantes.botones);
					BotonMatematicoEnfocado = botonAritmetico;
					PropiedadesDisplay.setText(BotonMatematicoEnfocado.getObjetoMatematico().toString());
				} else {
					BotonMatematicoEnfocado = null;
					botonAritmetico.setSeleccionado(false);
					botonAritmetico.setBackground(Constantes.SecundarioColor);
					botonAritmetico.setForeground(Constantes.DetallesSegundoColor);
					botonAritmetico.setFont(Constantes.botones);
					if (OperacionGeneral.getError() == null) {
						PropiedadesDisplay.setText(OperacionGeneral.getResultado().toString());
					} else {
						PropiedadesDisplay.setText("Sin resultado");
					}
				}

			}
		});

		BotonesAritmeticos.add(botonAritmetico);

		GridBagConstraints Constrains = new GridBagConstraints();
		Constrains.fill = GridBagConstraints.HORIZONTAL;
		Constrains.gridx = 0;
		Constrains.weightx = 1;
		Constrains.weighty = 1;
		Constrains.insets = new Insets(5, 5, 5, 5);
		JPanelOperaciones.add(botonAritmetico, Constrains);
		getContentPane().paintAll(getContentPane().getGraphics());
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
				UnidadMatematica  resultadoFuncion = creadorFunciones.getUnidadMatematicaResultadoFuncion();
				if (resultadoFuncion != null) {
					AgregarBotonAritmetico(new BotonAritmetico(resultadoFuncion));
					CalcularConsolaOutput();
				}
				break;
		}

	}

}
