package Motomaticas.RecursosCustomizados;

import java.io.Serializable;

import javax.swing.JButton;

import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.views.AbstractView;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import Motomaticas.ObjetosLogicos.motorMatematico.ObjetoMatematico;
import Motomaticas.ValoresDefault.Constantes;

public class BotonAritmetico extends JButton implements Serializable, MouseListener {
	private String Etiqueta;
    private ObjetoMatematico ObjetoMatematico;
	private String Informacion;
	private boolean Seleccionado;
	private boolean Adentro;
	private boolean Arrastrado;

	
	public BotonAritmetico(ObjetoMatematico objetoMatematico) {
		super(objetoMatematico.toStringReducido());
		super.setFont(Constantes.botones);
		setBackground(Constantes.SecundarioColor);
		setForeground(Constantes.DetallesSegundoColor);
		this.ObjetoMatematico = objetoMatematico;
		this.Etiqueta = objetoMatematico.toStringReducido();
		this.Informacion = objetoMatematico.toString();
		this.setToolTipText(objetoMatematico.toString());
		this.addMouseListener(this);
		
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
	public boolean isAdentro(){
		return Adentro;
	}




	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {

		if(Adentro){
		Seleccionado = !Seleccionado;
		if (this.isSeleccionado()) {
			this.setBackground(Constantes.DetallesColor);
			this.setForeground(Constantes.PrincipalColor);
			this.setFont(Constantes.botones);
		} else {// si ya estaba seleccionado y volvemos a presionarlo se deselecciona 
			this.setBackground(Constantes.SecundarioColor);
			this.setForeground(Constantes.DetallesSegundoColor);
			this.setFont(Constantes.botones);
		}
	}


	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		if(Adentro) Arrastrado = true;
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		Arrastrado = false;
		
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		Adentro = true;
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		Adentro = false;
		
	}




	
	

	
}
