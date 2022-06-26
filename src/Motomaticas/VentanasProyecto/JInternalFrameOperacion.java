package Motomaticas.VentanasProyecto;

import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.JToolBar;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import Motomaticas.ObjetosLogicos.motorMatematico.operaciones.OperacionMatematica;
import Motomaticas.ObjetosLogicos.motorMatematico.operaciones.operacionesMatematicasGenericasInterface;
import ModulosImportados.NumerosReales.UnidadNumerosRacionales;
import ModulosImportados.Vector3D.*;
import Motomaticas.ObjetosLogicos.motorMatematico.*;
import Motomaticas.ObjetosLogicos.motorMatematico.funciones.FuncionMatematica;
import Motomaticas.ObjetosLogicos.motorMatematico.variables.UnidadMatematica;
import Motomaticas.RecursosCustomizados.BotonAritmetico;
import Motomaticas.RecursosCustomizados.JPanelDragAndDropBotonesAritmeticos;
import Motomaticas.RecursosCustomizados.JPanelInspector.JPanel_CrearFuncionMatematica;
import Motomaticas.RecursosCustomizados.JPanelInspector.JPanel_CrearOperacionMatematica;
import Motomaticas.RecursosCustomizados.JPanelInspector.JPanel_CrearUnidadMatematica;
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
	private JTextPane ConsolaOutput;
	private JTextPane PropiedadesDisplay;
	private JPanelDragAndDropBotonesAritmeticos JPanelOperacionesAreaDeTrabajo;

	private JTabbedPane InspectorPanel;

	private void crearPanelIzquierdo(JPanel panel) {
		panel.setBackground(Constantes.PrincipalColor);
		panel.setLayout(new BorderLayout());

		JPanel OperacionesYDetalles = new JPanel(new GridLayout(0, 1));

		// Area de Trabajo
		JPanelOperacionesAreaDeTrabajo = new JPanelDragAndDropBotonesAritmeticos(18, this);

		// VIsualizacion
		JSplitPane TerminalJPanel = new JSplitPane();

		JScrollPane JScrollEditorPropiedadesDisplay = new JScrollPane();
		JScrollEditorPropiedadesDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		JScrollEditorPropiedadesDisplay
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		PropiedadesDisplay = new JTextPane();
		PropiedadesDisplay.setBackground(Constantes.PrincipalColor);
		PropiedadesDisplay.setFont(Constantes.textoNormal);
		PropiedadesDisplay.setEditable(false);
		JScrollEditorPropiedadesDisplay.setViewportView(PropiedadesDisplay);

		JScrollPane JScroll_Consola = new JScrollPane();
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

		// Agregar al panel tipo consola

		TerminalJPanel.setLeftComponent(JScroll_Consola);
		TerminalJPanel.setRightComponent(JScrollEditorPropiedadesDisplay);
		TerminalJPanel.setResizeWeight(0.5);

		// Agregar al panel general izquierdo
		JScrollPane scrollForAreaDeOperaciones = new JScrollPane();
		scrollForAreaDeOperaciones.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollForAreaDeOperaciones.setViewportView(JPanelOperacionesAreaDeTrabajo);
		OperacionesYDetalles.add(scrollForAreaDeOperaciones);
		OperacionesYDetalles.add(TerminalJPanel);

		panel.add(OperacionesYDetalles, BorderLayout.CENTER);

	}

	public void setDetallesDisplayText(String s) {

		if (s == null)
			s = (OperacionGeneral.getError() == null) ? "Resultado: \n"+OperacionGeneral.getResultado().toString() : OperacionGeneral.getError().toString();
		PropiedadesDisplay.setText(s);

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

		InspectorPanel = new JTabbedPane(JTabbedPane.NORTH);
		InspectorPanel.setBorder(null);
		InspectorPanel.addTab("Unidades", null, new JPanel_CrearUnidadMatematica(this), null);
		InspectorPanel.addTab("Operaciones", null, new JPanel_CrearOperacionMatematica(this), null);
		InspectorPanel.addTab("Funciones", null, new JPanel_CrearFuncionMatematica(this), null);

		JPanel GeneralOperacionPanelIzquierdo = new JPanel();
		crearPanelIzquierdo(GeneralOperacionPanelIzquierdo);

		JGeneralSplitPanel.setLeftComponent(GeneralOperacionPanelIzquierdo);
		JGeneralSplitPanel.setRightComponent(InspectorPanel);
		JGeneralSplitPanel.setResizeWeight(0.7);

		setVisible(true);
	}

	// Calcular la salida general de la operacion para mostrarla en el cuadro de
	// salida
	public void CalcularConsolaOutput() {
		ConsolaOutput.setText(""); /// reset output
		OperacionGeneral.HardReset();
		MainApunteFrame.panel_Matematico.HardReset_JPanelMatematico();

		for (BotonAritmetico i : JPanelOperacionesAreaDeTrabajo.getListaBotonesAritmeticos()) {
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
			case "CreadoDesdeInspector":
				ObjetoMatematico nueObjetoMatematico = ((BotonAritmetico) arg0.getSource()).getObjetoMatematico();
				ObjetoMatematico ObjetoAJButton = null;

				switch (nueObjetoMatematico.getTipoDeObjetoMatematico()) {
					case Unidad:
						ObjetoAJButton = ((UnidadMatematica) nueObjetoMatematico)
								.crearUnidad((JFrame) SwingUtilities.getRoot(this));
						break;
					case Operacion:
						ObjetoAJButton = ((OperacionMatematica)nueObjetoMatematico).clonarNuevaInstanciaOperacionMatematica();
						break;
					case Funcion:
						ObjetoAJButton = ((FuncionMatematica) nueObjetoMatematico)
								.llamarFuncionMatematica((JFrame) SwingUtilities.getRoot(this));
						break;

				}

				JPanelOperacionesAreaDeTrabajo.AgregarBotonAritmetico(new BotonAritmetico(ObjetoAJButton));

				CalcularConsolaOutput();

				break;

		}

	}

}
