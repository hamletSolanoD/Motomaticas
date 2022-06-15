package Motomaticas.ObjetosLogicos.motorMatematico.variables;

import java.util.ArrayList;

import javax.swing.JFrame;

import  Motomaticas.ObjetoMatematico;
import  Motomaticas.ObjetosLogicos.motorMatematico.operaciones.OperacionMatematica;
import  Motomaticas.ValoresDefault.Constantes;

public abstract class UnidadMatematica extends ObjetoMatematico {
	
	public OperacionMatematica Suma;
	public OperacionMatematica Resta;
	public OperacionMatematica Multiplicacion;
	public OperacionMatematica Division;
	public static ArrayList<UnidadMatematica> TotalUnidadesMatematicas;


	public UnidadMatematica(String nombreObjetoMatematico,String SimboloIdentificador,OperacionMatematica Suma , OperacionMatematica Resta,OperacionMatematica Multiplicacion,OperacionMatematica Division) {
		super(nombreObjetoMatematico,SimboloIdentificador,Constantes.TipoObjetoMatematico.Unidad);
		this.Suma = Suma;
		this.Resta = Resta;
		this.Multiplicacion = Multiplicacion;
		this.Division = Division;
		if(TotalUnidadesMatematicas == null) TotalUnidadesMatematicas = new ArrayList<>();
		boolean repetido = false;
		for (UnidadMatematica unidadMatematica : TotalUnidadesMatematicas) {
			if(unidadMatematica.getNombreObjetoMatematico() == this.getNombreObjetoMatematico()){
				repetido = true;
				break;
			}
		}
		if(!repetido) TotalUnidadesMatematicas.add(this);
			
	}
	
	public abstract String toString();

	public abstract String toStringReducido();

	public abstract UnidadMatematica crearUnidad(JFrame padre);// metodo para que se sobrescriba y cree una jdialog de unidad aritmetica

}
