package ObjetosLogicos.motorMatematico;
import java.io.Serializable;
import java.util.UUID;
import ValoresDefault.Constantes.TipoObjetoAlgebraico;

public abstract class ObjetoAlgebraico  implements Serializable{
	protected String ID;
	protected TipoObjetoAlgebraico TipoDeObjetoAlgebraico;
	protected String SimboloIdentificador;
	


	public ObjetoAlgebraico(String SimboloIdentificador,TipoObjetoAlgebraico TipoDeObjetoAlgebraio){
		this.ID = UUID.randomUUID().toString();
		this.SimboloIdentificador = SimboloIdentificador;
		this.TipoDeObjetoAlgebraico = TipoDeObjetoAlgebraio;
	}
	public String getID() {
		return ID;
	}
	public TipoObjetoAlgebraico getTipoDeObjetoAlgebraico() {
		return TipoDeObjetoAlgebraico;
	}
	
	public String getSimboloIdentificador(){
		return SimboloIdentificador;
	}
	public abstract String toStringReducido();
	public abstract String toString();
	public abstract String getNombre();

	
	protected void setTipoDeObjetoAlgebraico(TipoObjetoAlgebraico TipoDeObjetoAlgebraico) {
		this.TipoDeObjetoAlgebraico =  TipoDeObjetoAlgebraico;
	}
}
