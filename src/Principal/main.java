package Principal;

import java.util.ArrayList;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import ModulosImportados.NumerosReales.UnidadNumerosRacionales;
import ModulosImportados.Vector3D.Vector3D;
import Motomaticas.DefaultCrearPlantillas.EstandarNparamsJDialog;
import Motomaticas.ObjetosLogicos.motorMatematico.operaciones.OperacionMatematica;
import Motomaticas.RecursosCustomizados.BotonAritmetico;
import Motomaticas.RecursosCustomizados.JPanelDragAndDropBotonesAritmeticos;
import Motomaticas.RecursosCustomizados.JPanelInspector.JPanel_CrearUnidadMatematica;
import Motomaticas.VentanasProyecto.JInternalFrameOperacion;
import Motomaticas.VentanasProyecto.JpanelOperaciones;
import Motomaticas.VentanasProyecto.Portada;

public class main {

	public static void main(String[] args) {
		OperacionMatematica.inyectarBases();
		new Vector3D("");
		new UnidadNumerosRacionales();

		new Portada();
		// new JInternalFrameOperacion("Prueba");
		// JFrame padre = new JFrame();
		// padre.setVisible(true);
		// padre.setBounds(0, 0, 800, 500);
		// padre.add(new JpanelOperaciones(),BorderLayout.CENTER);
		// padre.add(new JPanel_CrearUnidadMatematica(padre),BorderLayout.CENTER);


		// padre.add( jpbttn, BorderLayout.CENTER);

	}

}
