package RecursosCustomizados;

import java.io.Serializable;

import javax.swing.JButton;

import ObjetosLogicos.motorMatematico.ObjetoAlgebraico;
import ValoresDefault.Constantes;

public class BotonAritmetico extends JButton implements Serializable {
	private String Etiqueta;
    private ObjetoAlgebraico ObjetoAlgebraico;
	private String Informacion;
	private boolean Seleccionado;
	
	public BotonAritmetico(ObjetoAlgebraico objetoAlgebraico) {
		super(objetoAlgebraico.toStringReducido());
		super.setFont(Constantes.botones);
		setBackground(Constantes.SecundarioColor);
		setForeground(Constantes.DetallesSegundoColor);
		this.ObjetoAlgebraico = objetoAlgebraico;
		this.Etiqueta = objetoAlgebraico.toStringReducido();
		this.Informacion = objetoAlgebraico.toString();
		this.setToolTipText(objetoAlgebraico.toString());
		
	}

	public String getEtiqueta() {
		return Etiqueta;
	}

	public ObjetoAlgebraico getObjetoAlgebraico() {
		return ObjetoAlgebraico;
	}

	public String getInformacion() {
		return Informacion;
	}

	public void setSeleccionado(boolean seleccionado) {
		Seleccionado = seleccionado;
	}

	public boolean isSeleccionado() {
		return Seleccionado;
	}
	
	

	
}
