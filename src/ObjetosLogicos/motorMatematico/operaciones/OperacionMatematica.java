package ObjetosLogicos.motorMatematico.operaciones;


import ObjetosLogicos.motorMatematico.ObjetoAlgebraico;
import RecursosCustomizados.BotonApuntes;
import ValoresDefault.Constantes;
import ValoresDefault.Constantes.Apunte;
import VentanasProyecto.MainApunteFrame;

public abstract class OperacionMatematica extends ObjetoAlgebraico {

	private String descripcionDeOperacion;
	private int prioridadDeOperacion;
	private String tituloOperacion;
	private String[] TipoOperandosCorrecto;
	private boolean Funcion;// booleano para saber si una operacion es una funcion con mas de un parametro de entrada o solo una operacion que utiliza 2 tipos de entrada y ya

	// constructor por defecto, usando operaciones basicas ya incluidas
	public OperacionMatematica( boolean Funcion, String[] TipoOperandosCorrecto, String tituloOperacion, String descripcionDeOperacion, int prioridadDeOperacion)
	 {
		super(Constantes.TipoObjetoAlgebraico.Operacion);
		this.tituloOperacion = tituloOperacion;
		this.prioridadDeOperacion = prioridadDeOperacion;
		this.descripcionDeOperacion = descripcionDeOperacion;
		this.TipoOperandosCorrecto = TipoOperandosCorrecto;
		this.Funcion = Funcion;
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
		return this.getTituloDeOperacion();
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
}
