package RecursosCustomizados;

import java.awt.Dimension;
import java.io.Serializable;

import javax.swing.JButton;

import ObjetosLogicos.motorMatematico.ObjetoAlgebraico;
import ValoresDefault.Constantes;

public class BotonAritmetico extends JButton implements Serializable {
	private String Etiqueta;
    private ObjetoAlgebraico ObjetoAlgebraico;
	private String Informacion;
	private boolean Seleccionado;
	
	public BotonAritmetico(ObjetoAlgebraico ObjetoAlgebraico, String Etiqueta, String Informacion) {
		super(Etiqueta);
		super.setFont(Constantes.botones);
		this.Etiqueta = Etiqueta;
		setBackground(Constantes.SecundarioColor);
		setForeground(Constantes.DetallesSegundoColor);
		this.ObjetoAlgebraico = ObjetoAlgebraico;
		this.Informacion = Informacion;
		
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
