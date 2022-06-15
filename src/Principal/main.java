package Principal;

import java.util.ArrayList;

import javax.swing.JFrame;

import ModulosImportados.NumerosReales.UnidadNumerosRacionales;
import ModulosImportados.Vector3D.Vector3D;
import Motomaticas.DefaultCrearPlantillas.EstandarNparamsJDialog;
import Motomaticas.ObjetosLogicos.motorMatematico.operaciones.OperacionMatematica;

import Motomaticas.VentanasProyecto.Portada;

public class main {

	public static void main(String[] args) {
		OperacionMatematica.inyectarBases();
		new Vector3D("");
		new UnidadNumerosRacionales();

		new Portada();

	}

}
