package ObjetosLogicos.motorMatematico.operaciones;


import java.util.ArrayList;

import ObjetosLogicos.motorMatematico.ObjetoAlgebraico;
import RecursosCustomizados.BotonApuntes;
import ValoresDefault.Constantes;
import ValoresDefault.Constantes.Apunte;
import VentanasProyecto.MainApunteFrame;

public abstract class OperacionMatematica extends ObjetoAlgebraico {

	public static ArrayList<OperacionMatematica> TotalOperacionesMatematicas;
	private String descripcionDeOperacion;
	private int prioridadDeOperacion;
	private String nombre;
	private String[] TipoOperandosCorrecto;
	private boolean Funcion;// booleano para saber si una operacion es una funcion con mas de un parametro de entrada o solo una operacion que utiliza 2 tipos de entrada y ya
	// constructor por defecto, usando operaciones basicas ya incluidas
	private boolean conLlave;//si el operador hace uso de apertura y cierre de llaves
	
	public OperacionMatematica(boolean conLlave,String SimboloIdentificador,boolean Funcion, String[] TipoOperandosCorrecto, String nombre, String descripcionDeOperacion, int prioridadDeOperacion)
	 {
		super(SimboloIdentificador,Constantes.TipoObjetoAlgebraico.Operacion);
		this.conLlave = conLlave;
		this.nombre = nombre;
		this.prioridadDeOperacion = prioridadDeOperacion;
		this.descripcionDeOperacion = descripcionDeOperacion;
		this.TipoOperandosCorrecto = TipoOperandosCorrecto;
		this.Funcion = Funcion;
		if(TotalOperacionesMatematicas == null) TotalOperacionesMatematicas = new ArrayList<>();
		boolean repetido = false;
		for (OperacionMatematica operacionMatematica : TotalOperacionesMatematicas) {
			if(operacionMatematica.getNombre() == this.getNombre()){
				repetido = true;
				break;
			}
		}
		if(!repetido) TotalOperacionesMatematicas.add(this);
	}

	/*
	 * agrega al panel general de algebra la informacion de esta operacion en
	 * lenguaje humano en un objeto tipo boton de apuntes
	 */
	protected void MostrarInformacion(Apunte Apunte) {
		MainApunteFrame.panel_Algebra.AgregarBotonDeApuntes(new BotonApuntes(Apunte));
	}

	@Override
	public String toStringReducido() {
		return this.nombre;
	}
	/*
	 * retorna su tipo de operacion
	 */
	public int getprioridadDeOperacion() {
		return prioridadDeOperacion;
	}

	/* retorna la descripcion de la operacion */
	@Override
	public String toString() {
		return descripcionDeOperacion;
	}

	@Override
	public String getNombre() {
		return nombre;
	}


	public boolean comprobarOperandosIguales(String[] ArrayTipos){
		try{
		for(int e = 0; e < TipoOperandosCorrecto.length; e++){
			if(ArrayTipos[e] != TipoOperandosCorrecto[e]) return false; 
		
		}
	}catch(IndexOutOfBoundsException e){
		return false;
	}
		return true;

	}

	public abstract ObjetoAlgebraico calcularOperacion(ObjetoAlgebraico... args);

	public static void inyectarFuncionesMatematicas(OperacionMatematica OperacionMatematica){
		if(TotalOperacionesMatematicas == null) TotalOperacionesMatematicas = new ArrayList<>();
		boolean repetido = false;
		for (OperacionMatematica opMatematica : TotalOperacionesMatematicas) {
			if(opMatematica.getNombre() == OperacionMatematica.getNombre()){
				repetido = true;
				break;
			}
		}
		if(!repetido) TotalOperacionesMatematicas.add(OperacionMatematica);

	}
}
