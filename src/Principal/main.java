package Principal;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.plaf.synth.SynthStyle;

import ModulosImportados.NumerosReales.UnidadNumerosReales;
import ModulosImportados.Vector3D.OperacionesVector;
import ModulosImportados.Vector3D.Vector3D;
import ObjetosLogicos.motorMatematico.*;
import ObjetosLogicos.motorMatematico.operaciones.OperacionMatematica;
import RecursosCustomizados.JDialog_CrearOperacionMatematica;
import RecursosCustomizados.JDialog_CrearUnidadMatematica;
import VentanasProyecto.Portada;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Vector3D("prueba");
		new UnidadNumerosReales(34);
		OperacionesVector.operacionVecCruz vecCruz = new 	OperacionesVector.operacionVecCruz();
		new JDialog_CrearOperacionMatematica(new JFrame(), "Prueba");
		
		

		//new Portada();
		// new MainApunteFrame();
		// System.out.println(Operacion.VecProductoEscalar(new Vector3("VA", 3, 5, 1), new Vector3("VB", 6, 2, 8)));
		// OperacionesVector.operacionVecUnitario d = new OperacionesVector.operacionVecUnitario();
	}

}
