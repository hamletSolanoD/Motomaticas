package ObjetosLogicos.motorMatematico;
import java.io.Serializable;
import java.util.UUID;
import ValoresDefault.Constantes.TipoObjetoAlgebraico;

public abstract class ObjetoAlgebraico  implements Serializable{
	protected String ID;
	protected TipoObjetoAlgebraico TipoDeObjetoAlgebraico;
	public ObjetoAlgebraico(TipoObjetoAlgebraico TipoDeObjetoAlgebraio){
		this.ID = UUID.randomUUID().toString();
		this.TipoDeObjetoAlgebraico = TipoDeObjetoAlgebraio;
	}
	public String getID() {
		return ID;
	}
	public TipoObjetoAlgebraico getTipoDeObjetoAlgebraico() {
		return TipoDeObjetoAlgebraico;
	}
	
	protected void setTipoDeObjetoAlgebraico(TipoObjetoAlgebraico TipoDeObjetoAlgebraico) {
		this.TipoDeObjetoAlgebraico =  TipoDeObjetoAlgebraico;
	}
}
