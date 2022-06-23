package Motomaticas.ObjetosLogicos.motorMatematico.operaciones;

import java.util.ArrayList;

import Motomaticas.ObjetosLogicos.motorMatematico.ObjetoMatematico;
import Motomaticas.ObjetosLogicos.motorMatematico.variables.UnidadMatematica;
import Motomaticas.RecursosCustomizados.BotonApuntes;
import Motomaticas.ValoresDefault.Constantes;
import Motomaticas.ValoresDefault.Constantes.Apunte;
import Motomaticas.VentanasProyecto.MainApunteFrame;

public abstract class OperacionMatematica extends ObjetoMatematico implements Cloneable {

	public static ArrayList<OperacionMatematica> TotalOperacionesMatematicas;
	private String descripcionDeOperacion;
	private int prioridadDeOperacion;
	private boolean conmutativa;


	public String getDescripcionDeOperacion() {
		return this.descripcionDeOperacion;
	}

	public int getPrioridadDeOperacion() {
		return this.prioridadDeOperacion;
	}


	public OperacionMatematica clonarNuevaInstanciaOperacionMatematica() {
		try {
			return (OperacionMatematica) this.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public boolean getIsConmutativa(){return conmutativa;}
	public boolean getConLlave() {
		return this.conLlave;
	}

	// de entrada o solo una operacion que utiliza 2 tipos de entrada y ya
	// constructor por defecto, usando operaciones basicas ya incluidas
	private boolean conLlave;// si el operador hace uso de apertura y cierre de
	private static boolean primeraEjecucion = true;

	public OperacionMatematica(boolean conLlave, String SimboloIdentificador, String nombre,
			String descripcionDeOperacion, int prioridadDeOperacion, boolean conmutativa) {
		super(nombre, SimboloIdentificador, Constantes.TipoObjetoMatematico.Operacion);
		this.conLlave = conLlave;
		this.conmutativa = conmutativa;
		this.prioridadDeOperacion = ((prioridadDeOperacion >= 4 && conLlave) || conLlave) ? 4
				: ((prioridadDeOperacion >= 4) ? 3 : prioridadDeOperacion);
		if (this.prioridadDeOperacion != prioridadDeOperacion)
			(new Exception(
					"Error en asigniacion de prioridad de operacion para la operacion " + getNombreObjetoMatematico()))
					.printStackTrace();
		this.descripcionDeOperacion = descripcionDeOperacion;
		inyectarPorCreacion();
	}


	private void inyectarPorCreacion() {
		if (TotalOperacionesMatematicas == null)
			TotalOperacionesMatematicas = new ArrayList<>();
		boolean repetido = false;
		for (OperacionMatematica operacionMatematica : TotalOperacionesMatematicas) {
			if (operacionMatematica.getNombreObjetoMatematico() == this.getNombreObjetoMatematico()) {
				repetido = true;
				break;
			}
		}
		if (!repetido)
			TotalOperacionesMatematicas.add(this);
	}

	public static void inyectarBases() {
		if (primeraEjecucion) {
			inyectarOperacionMatematica(new operacionesMatematicasGenericasInterface.llaveIzquierda());
			inyectarOperacionMatematica(new operacionesMatematicasGenericasInterface.llaveDerecha());
			primeraEjecucion = false;
		}

	}

	/*
	 * agrega al panel general de Matematico la informacion de esta operacion en
	 * lenguaje humano en un objeto tipo boton de apuntes
	 */
	protected void MostrarInformacion(Apunte Apunte) {
		MainApunteFrame.panel_Matematico.AgregarBotonDeApuntes(new BotonApuntes(Apunte));
	}

	@Override
	public String toStringReducido() {
		if (conLlave)
			return SimboloIdentificador + "{";
		return this.SimboloIdentificador;
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

	public boolean comprobarOperandosIguales(String[] ArrayTipos) {
		String[] TipoOperandosCorrecto  = definirTipoDeOperandoscorrectos();
		try {
			for (int e = 0; e < TipoOperandosCorrecto.length; e++) {
				if (ArrayTipos[e] != TipoOperandosCorrecto[e])
					return false;

			}
		} catch (Throwable e) {
			return false;
		}
		return true;

	}

	public abstract UnidadMatematica calcularOperacion(UnidadMatematica... args);

	public static void inyectarOperacionMatematica(OperacionMatematica OperacionMatematica) {
		if (TotalOperacionesMatematicas == null)
			TotalOperacionesMatematicas = new ArrayList<>();
		boolean repetido = false;
		for (OperacionMatematica opMatematica : TotalOperacionesMatematicas) {
			if (opMatematica.getNombreObjetoMatematico() == OperacionMatematica.getNombreObjetoMatematico()) {
				repetido = true;
				break;
			}
		}
		if (!repetido)
			TotalOperacionesMatematicas.add(OperacionMatematica);

	}

	protected abstract String[] definirTipoDeOperandoscorrectos();

}
