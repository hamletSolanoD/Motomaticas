package Principal;

import ModulosImportados.NumerosReales.UnidadNumerosRacionales;
import ModulosImportados.Vector3D.Vector3D;
import Motomaticas.ObjetosLogicos.motorMatematico.operaciones.OperacionMatematica;

import Motomaticas.VentanasProyecto.Portada;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OperacionMatematica.inyectarBases();
		new Vector3D("");
		new UnidadNumerosRacionales();

		new Portada();
	}

}
