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
import VentanasProyecto.MainApunteFrame;
import VentanasProyecto.Portada;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OperacionMatematica.inyectarBases();
		new Vector3D("");
		new UnidadNumerosReales();

		new Portada();
	}

}
