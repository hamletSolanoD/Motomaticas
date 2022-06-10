package VentanasProyecto;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import java.awt.Color;

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
import java.util.HashMap;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import ObjetosLogicos.ObjetoAlgebraico;
import ObjetosLogicos.Operacion;
import ObjetosLogicos.OperacionGeneral;
import ObjetosLogicos.UnidadAritmetica;
import ObjetosLogicos.Vector3;
import RecursosCustomizados.BotonAritmetico;
import RecursosCustomizados.CrearUnidadAritmetica;
import RecursosCustomizados.CrearVector3;
import RecursosCustomizados.Mensaje_EntradaDeDatos;
import ValoresDefault.Constantes;
import ValoresDefault.Constantes.Apunte;
import ValoresDefault.Constantes.TipoDeErrorAlgebraico;
import ValoresDefault.Constantes.TipoObjetoAlgebraico;
import ValoresDefault.Constantes.TipoOperacion;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.Container;

import javax.swing.Box;
import java.awt.GridBagLayout;
import java.awt.Dimension;

public class JInternalFrameOperacion extends JInternalFrame implements ActionListener, Serializable {

	////////////////////// Diseño //////////////////

	////////////////// LOGICA /////////////
	private String Titulo;
	private OperacionGeneral OperacionGeneral = new OperacionGeneral();
	private BotonAritmetico BotonAlgebraicoEnfocado;

	////////// GRAFICA ///////////
	private ArrayList<BotonAritmetico> BotonesAritmeticos = new ArrayList<BotonAritmetico>();
	private JPanel JPanelOperaciones;
	private JTextPane ConsolaOutput;
	private JTextPane PropiedadesDisplay;

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

		JButton AgregarVector = Constantes.BotonCuadrado("Ā", Constantes.DetallesColor, Constantes.PrincipalColor);
		AgregarVector.setActionCommand("++");
		panel_3.add(AgregarVector);
		AgregarVector.addActionListener(this);
		AgregarVector.setFont(Constantes.textoNormal);

		JButton EliminarVector = Constantes.BotonCuadrado("--", Constantes.DetallesColor, Constantes.PrincipalColor);
		panel_3.add(EliminarVector);
		EliminarVector.setActionCommand("--");
		EliminarVector.addActionListener(this);
		EliminarVector.setFont(Constantes.textoNormal);

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

		JButton AgregarUnidad = Constantes.BotonCuadrado("+U", Constantes.DetallesColor, Constantes.PrincipalColor);
		panel_3.add(AgregarUnidad);
		AgregarUnidad.setActionCommand("U");
		AgregarUnidad.addActionListener(this);
		AgregarUnidad.setFont(Constantes.textoNormal);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Constantes.SecundarioColor);
		panel_8.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel_4.add(horizontalStrut_1, BorderLayout.EAST);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Constantes.SecundarioColor);
		panel_4.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new GridLayout(1, 0, 0, 0));

		JButton VectUnitario = Constantes.BotonCuadrado("Â", Constantes.DetallesColor, Constantes.PrincipalColor);
		VectUnitario.setActionCommand("Â");
		panel_5.add(VectUnitario);
		VectUnitario.addActionListener(this);
		VectUnitario.setFont(Constantes.textoNormal);

		JButton VecMagnitud = Constantes.BotonCuadrado("|Ā|", Constantes.DetallesColor, Constantes.PrincipalColor);
		VecMagnitud.setActionCommand("|A|");
		panel_5.add(VecMagnitud);
		VecMagnitud.addActionListener(this);
		VecMagnitud.setFont(Constantes.textoNormal);

		JButton VecProductoPunto = Constantes.BotonCuadrado("•", Constantes.DetallesColor, Constantes.PrincipalColor);
		panel_5.add(VecProductoPunto);
		VecProductoPunto.setActionCommand("•");
		VecProductoPunto.addActionListener(this);
		VecProductoPunto.setFont(Constantes.textoNormal);

		JButton VecProductoCruz = Constantes.BotonCuadrado("X", Constantes.DetallesColor, Constantes.PrincipalColor);
		panel_5.add(VecProductoCruz);
		VecProductoCruz.setActionCommand("xx");
		VecProductoCruz.addActionListener(this);
		VecProductoCruz.setFont(Constantes.textoNormal);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Constantes.SecundarioColor);
		panel_8.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		panel_6.add(horizontalStrut_3, BorderLayout.EAST);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Constantes.SecundarioColor);
		panel_6.add(panel_7);
		panel_7.setLayout(new GridLayout(1, 0, 0, 0));

		JButton Sumar = Constantes.BotonCuadrado("+", Constantes.DetallesColor, Constantes.PrincipalColor);
		panel_7.add(Sumar);
		Sumar.setActionCommand("+");
		Sumar.addActionListener(this);
		Sumar.setFont(Constantes.textoNormal);

		JButton Restar = Constantes.BotonCuadrado("-", Constantes.DetallesColor, Constantes.PrincipalColor);
		panel_7.add(Restar);
		Restar.setActionCommand("-");
		Restar.addActionListener(this);
		Restar.setFont(Constantes.textoNormal);

		JButton Multiplicar = Constantes.BotonCuadrado("x", Constantes.DetallesColor, Constantes.PrincipalColor);
		panel_7.add(Multiplicar);
		Multiplicar.setActionCommand("x");
		Multiplicar.addActionListener(this);
		Multiplicar.setFont(Constantes.textoNormal);

		JButton Dividir = Constantes.BotonCuadrado("÷", Constantes.DetallesColor, Constantes.PrincipalColor);
		panel_7.add(Dividir);
		Dividir.setActionCommand("/");
		Dividir.addActionListener(this);
		Dividir.setFont(Constantes.textoNormal);

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
					BotonAlgebraicoEnfocado = botonAritmetico;
					PropiedadesDisplay.setText(BotonAlgebraicoEnfocado.getObjetoAlgebraico().toString());
				} else {
					BotonAlgebraicoEnfocado = null;
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

	private void QuitarBotonAritmetico(BotonAritmetico botonAritmetico) {
		BotonesAritmeticos.remove(botonAritmetico);
		JPanelOperaciones.remove(botonAritmetico);
		getContentPane().paintAll(getContentPane().getGraphics());
	}

	public void CalcularConsolaOutput() {
		ConsolaOutput.setText(""); /// reset output
		OperacionGeneral.HardReset();
		MainApunteFrame.panel_Algebra.HardReset_JPanelAlgebra();

		for (BotonAritmetico i : BotonesAritmeticos) {
			ConsolaOutput.setText(ConsolaOutput.getText() + i.getEtiqueta());
			OperacionGeneral.getObjetosAlgebraicos().add(i.getObjetoAlgebraico());
		} //// mostrar los botones en el lado de operaciones

		TipoDeErrorAlgebraico PosibleError = OperacionGeneral.CalcularOperacion();
		if (PosibleError == null) { // si hay un resultado que mostrar
			if (OperacionGeneral.getResultado().getTipoDeObjetoAlgebraico() == TipoObjetoAlgebraico.Unidad) {
				ConsolaOutput.setForeground(Constantes.DetallesSegundoColor);
				ConsolaOutput.setText(ConsolaOutput.getText() + " = "
						+ ((UnidadAritmetica) OperacionGeneral.getResultado()).toStringReducido());
				MainApunteFrame.panel_Visualizar.MostrarVectores(new Vector3("", 90, 90, 90, 0));

			} else if (OperacionGeneral.getResultado().getTipoDeObjetoAlgebraico() == TipoObjetoAlgebraico.Vector) {
				ConsolaOutput.setForeground(Constantes.DetallesSegundoColor);
				ConsolaOutput.setText(ConsolaOutput.getText() + " = "
						+ ((Vector3) OperacionGeneral.getResultado()).toStringReducido());
				MainApunteFrame.panel_Visualizar.MostrarVectores(((Vector3) OperacionGeneral.getResultado()));

			}
		} else { // si no hay un resultado que mostrar
			ConsolaOutput.setForeground(Constantes.DetallesColor);
			ConsolaOutput.setText("Error en la operacion: " + PosibleError.toString());
			MainApunteFrame.panel_Visualizar.MostrarVectores(new Vector3("", 90, 90, 90, 0));

		}
	}

	private void test() {
		AgregarBotonAritmetico(new BotonAritmetico(new Vector3("Fff", 34, 54, 23), "algo chido", ""));
	}

	@Override

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getActionCommand()) {
			case "++":
				CrearVector3 creadorPanelVector = new CrearVector3((JFrame) SwingUtilities.getRoot(this));
				Vector3 NuevoVector = creadorPanelVector.GetVector();
				if (NuevoVector != null) {
					NuevoVector.setNombre(new Mensaje_EntradaDeDatos((JFrame) SwingUtilities.getRoot(this),
							"Ingrese etiqueta del Vector").Respuesta());
					AgregarBotonAritmetico(
							new BotonAritmetico(NuevoVector, NuevoVector.toStringReducido(), NuevoVector.getNombre()));
					CalcularConsolaOutput();
				}
				break;
			case "--":
				if (BotonAlgebraicoEnfocado != null) {
					OperacionGeneral.getObjetosAlgebraicos().remove(BotonAlgebraicoEnfocado.getObjetoAlgebraico());
					QuitarBotonAritmetico(BotonAlgebraicoEnfocado);
					BotonAlgebraicoEnfocado = null;
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
				ObjetoAlgebraico parentesisIzquierdo = new Operacion(TipoOperacion.Parentesis_Izquierdo);
				AgregarBotonAritmetico(new BotonAritmetico(parentesisIzquierdo, "{", ""));
				CalcularConsolaOutput();
				break;
			case "}":
				ObjetoAlgebraico parentesisDerecho = new Operacion(TipoOperacion.Parentesis_Derecho);
				AgregarBotonAritmetico(new BotonAritmetico(parentesisDerecho, "}", ""));
				CalcularConsolaOutput();
				break;
			case "U":
				CrearUnidadAritmetica creadorPanelUnidad = new CrearUnidadAritmetica(
						(JFrame) SwingUtilities.getRoot(this));
				UnidadAritmetica NuevaUnidad = creadorPanelUnidad.getUnidadNueva();
				if (NuevaUnidad != null) {
					AgregarBotonAritmetico(new BotonAritmetico(NuevaUnidad, NuevaUnidad.toStringReducido(), ""));
					CalcularConsolaOutput();
				}
				break;
			case "Â":
				ObjetoAlgebraico VectorUnitario = new Operacion(TipoOperacion.Vector_Unitario);
				AgregarBotonAritmetico(new BotonAritmetico(VectorUnitario, "Â{", ""));
				CalcularConsolaOutput();
				break;
			case "|A|":
				ObjetoAlgebraico VectorMagnitud = new Operacion(TipoOperacion.Vector_Magnitud);
				AgregarBotonAritmetico(new BotonAritmetico(VectorMagnitud, "|Ā|{", ""));
				CalcularConsolaOutput();
				break;

			case "•":
				ObjetoAlgebraico productoPunto = new Operacion(TipoOperacion.Producto_Escalar);
				AgregarBotonAritmetico(new BotonAritmetico(productoPunto, "•", ""));
				CalcularConsolaOutput();
				break;

			case "xx":
				ObjetoAlgebraico productoCruz = new Operacion(TipoOperacion.Producto_Cruz);
				AgregarBotonAritmetico(new BotonAritmetico(productoCruz, "X", ""));
				CalcularConsolaOutput();
				break;

			case "+":
				ObjetoAlgebraico Suma = new Operacion(TipoOperacion.Sumar);
				AgregarBotonAritmetico(new BotonAritmetico(Suma, "+", ""));
				CalcularConsolaOutput();
				break;

			case "-":
				ObjetoAlgebraico resta = new Operacion(TipoOperacion.Restar);
				AgregarBotonAritmetico(new BotonAritmetico(resta, "-", ""));
				CalcularConsolaOutput();
				break;

			case "x":
				ObjetoAlgebraico Multiplicar = new Operacion(TipoOperacion.Multiplicar);
				AgregarBotonAritmetico(new BotonAritmetico(Multiplicar, "x", ""));
				CalcularConsolaOutput();
				break;

			case "/":
				ObjetoAlgebraico dividir = new Operacion(TipoOperacion.Dividir);
				AgregarBotonAritmetico(new BotonAritmetico(dividir, "/", ""));
				CalcularConsolaOutput();
				break;

		}

	}

}
