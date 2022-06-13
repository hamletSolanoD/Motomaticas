package RecursosCustomizados;

import java.io.Serializable;

import javax.swing.JButton;

import ValoresDefault.Constantes;
import ValoresDefault.Constantes.Apunte;

public class BotonApuntes extends JButton implements Serializable {

    private Apunte Apunte;
	private boolean Seleccionado;
	
	public BotonApuntes(Apunte Apunte) {
		super(Apunte.getTituloAdaptado());
		super.setFont(Constantes.botones);
		setBackground(Constantes.SecundarioColor);
		setForeground(Constantes.DetallesSegundoColor);
		this.Apunte = Apunte;
		
	}

	

	public Apunte getApunte() {
		return Apunte;
	}

	public void setSeleccionado(boolean seleccionado) {
		Seleccionado = seleccionado;
	}

	public boolean isSeleccionado() {
		return Seleccionado;
	}
}
