package Motomaticas.RecursosCustomizados;

import java.io.Serializable;

import javax.swing.JButton;

import Motomaticas.ObjetosLogicos.motorMatematico.ObjetoMatematico;
import Motomaticas.ValoresDefault.Constantes;

public class BotonAritmetico extends JButton implements Serializable {
	private String Etiqueta;
    private ObjetoMatematico ObjetoMatematico;
	private String Informacion;
	private boolean Seleccionado;
	
	public BotonAritmetico(ObjetoMatematico objetoMatematico) {
		super(objetoMatematico.toStringReducido());
		super.setFont(Constantes.botones);
		setBackground(Constantes.SecundarioColor);
		setForeground(Constantes.DetallesSegundoColor);
		this.ObjetoMatematico = objetoMatematico;
		this.Etiqueta = objetoMatematico.toStringReducido();
		this.Informacion = objetoMatematico.toString();
		this.setToolTipText(objetoMatematico.toString());
		
	}

	public String getEtiqueta() {
		return Etiqueta;
	}

	public ObjetoMatematico getObjetoMatematico() {
		return ObjetoMatematico;
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
