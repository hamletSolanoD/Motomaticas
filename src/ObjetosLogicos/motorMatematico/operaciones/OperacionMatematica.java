package ObjetosLogicos.motorMatematico.operaciones;

import ObjetosLogicos.motorMatematico.ObjetoAlgebraico;
import ObjetosLogicos.motorMatematico.variables.UnidadMatematica;
import RecursosCustomizados.BotonApuntes;
import ValoresDefault.Constantes;
import ValoresDefault.Constantes.Apunte;
import VentanasProyecto.MainApunteFrame;

public abstract class OperacionMatematica extends ObjetoAlgebraico {

	private String descripcionDeOperacion;
	private int prioridadDeOperacion;
	private String tituloOperacion;
	private String OperandoIzquierdoTipo;
	private String OperandoDerechoTipo;

	// constructor por defecto, usando operaciones basicas ya incluidas
	public OperacionMatematica(String OperandoIzquierdoTipo,String OperandoDerechoTipo, String tituloOperacion, String descripcionDeOperacion, int prioridadDeOperacion)
	 {
		super(Constantes.TipoObjetoAlgebraico.Operacion);
		this.tituloOperacion = tituloOperacion;
		this.prioridadDeOperacion = prioridadDeOperacion;
		this.descripcionDeOperacion = descripcionDeOperacion;
		this.OperandoDerechoTipo = OperandoDerechoTipo;
		this.OperandoIzquierdoTipo = OperandoIzquierdoTipo;
	}

	/*
	 * agrega al panel general de algebra la informacion de esta operacion en
	 * lenguaje humano en un objeto tipo boton de apuntes
	 */
	protected void MostrarInformacion(Apunte Apunte) {
		MainApunteFrame.panel_Algebra.AgregarBotonDeApuntes(new BotonApuntes(Apunte));
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

	public String getTituloDeOperacion() {
		return tituloOperacion;
	}
	public String getOperandoIzquierdoTipo() {
		return OperandoIzquierdoTipo;
	}
	public String getOperandoDerechoTipo() {
		return OperandoDerechoTipo;
	}

	public boolean comprobarOperandos(UnidadMatematica obj1, UnidadMatematica obj2){

		return obj1.getNombre() == OperandoDerechoTipo && obj2.getNombre() == OperandoIzquierdoTipo;
	
	}

	public abstract ObjetoAlgebraico calcularOperacion(ObjetoAlgebraico... args);
}
