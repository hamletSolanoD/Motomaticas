package ObjetosLogicos.motorMatematico;

import java.io.Serializable;
import java.util.UUID;
import ValoresDefault.Constantes.TipoObjetoAlgebraico;

public abstract class ObjetoAlgebraico implements Serializable {
	protected String ID;
	protected TipoObjetoAlgebraico TipoDeObjetoAlgebraico;
	private String NombreObjetoMatematico;
	protected String SimboloIdentificador;

	public ObjetoAlgebraico(String nombreObjetoMatematico,String SimboloIdentificador ,TipoObjetoAlgebraico TipoDeObjetoAlgebraio) {
		this.ID = UUID.randomUUID().toString();
		this.SimboloIdentificador = SimboloIdentificador;
		this.NombreObjetoMatematico = nombreObjetoMatematico;
		this.TipoDeObjetoAlgebraico = TipoDeObjetoAlgebraio;
	}

	public String getID() {
		return ID;
	}

	public  TipoObjetoAlgebraico getTipoDeObjetoAlgebraico() {
		return TipoDeObjetoAlgebraico;
	}

	public String getSimboloIdentificador() {
		return SimboloIdentificador;
	}

	
	public String getCategoriaMatematica() {
		return "General";
	}


	public abstract String toStringReducido();

	public abstract String toString();


	public final String getNombreObjetoMatematico(){
		return NombreObjetoMatematico;
	}

	protected void setTipoDeObjetoAlgebraico(TipoObjetoAlgebraico TipoDeObjetoAlgebraico) {
		this.TipoDeObjetoAlgebraico = TipoDeObjetoAlgebraico;
	}
}
