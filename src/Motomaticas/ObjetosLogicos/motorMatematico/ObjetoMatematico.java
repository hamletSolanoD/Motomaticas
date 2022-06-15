package Motomaticas.ObjetosLogicos.motorMatematico;

import java.io.Serializable;
import java.util.UUID;

import Motomaticas.ValoresDefault.Constantes.TipoObjetoMatematico;

 public abstract class ObjetoMatematico implements Serializable {
	protected String ID;
	public TipoObjetoMatematico TipoDeObjetoMatematico;
	private String NombreObjetoMatematico;
	protected String SimboloIdentificador;

	public ObjetoMatematico(String nombreObjetoMatematico,String SimboloIdentificador ,TipoObjetoMatematico TipoDeObjetoMatematicoio) {
		this.ID = UUID.randomUUID().toString();
		this.SimboloIdentificador = SimboloIdentificador;
		this.NombreObjetoMatematico = nombreObjetoMatematico;
		this.TipoDeObjetoMatematico = TipoDeObjetoMatematicoio;

	}

	public String getID() {
		return ID;
	}

	public  TipoObjetoMatematico getTipoDeObjetoMatematico() {
		return TipoDeObjetoMatematico;
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

	protected void setTipoDeObjetoMatematico(TipoObjetoMatematico TipoDeObjetoMatematico) {
		this.TipoDeObjetoMatematico = TipoDeObjetoMatematico;
	}
}
