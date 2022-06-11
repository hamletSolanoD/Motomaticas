package ObjetosLogicos.motorMatematico.variables;

import ObjetosLogicos.motorMatematico.ObjetoAlgebraico;
import ValoresDefault.Constantes;

public class UnidadAritmetica extends ObjetoAlgebraico {
	
	private double Valor;
	public UnidadAritmetica() {
		super(Constantes.TipoObjetoAlgebraico.Unidad);
	}
	public UnidadAritmetica(double valor) {
		super(Constantes.TipoObjetoAlgebraico.Unidad);
		this.Valor = valor;
	}
	public void setValor(double valor) {
		Valor = valor;
	}
	public double getValor() {
		return Valor;
	}
	
	public String toString() {
		
		return "Unidad: "+Double.toString(Valor);
	}

	public String toStringReducido() {
		
		return Double.toString(Valor);
	}


}
