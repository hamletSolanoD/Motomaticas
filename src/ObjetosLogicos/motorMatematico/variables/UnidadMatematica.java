package ObjetosLogicos.motorMatematico.variables;

import ObjetosLogicos.motorMatematico.ObjetoAlgebraico;
import ObjetosLogicos.motorMatematico.operaciones.OperacionMatematica;
import ValoresDefault.Constantes;

public abstract class UnidadMatematica extends ObjetoAlgebraico {
	
	public OperacionMatematica Suma;
	public OperacionMatematica Resta;
	public OperacionMatematica Multiplicacion;
	public OperacionMatematica Division;


	public UnidadMatematica(OperacionMatematica Suma , OperacionMatematica Resta,OperacionMatematica Multiplicacion,OperacionMatematica Division) {
		super(Constantes.TipoObjetoAlgebraico.Unidad);
		this.Suma = Suma;
		this.Resta = Resta;
		this.Multiplicacion = Multiplicacion;
		this.Division = Division;
	}
	
	public abstract String toString();

	public abstract String getNombre();

	public abstract String toStringReducido();

}
