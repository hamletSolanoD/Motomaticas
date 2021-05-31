package RecursosCustomizados;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.PaintContext;

import javax.swing.JPanel;

public class JPanel_Lienzo extends JPanel {

	private int PinzelGrosor;
	private int PosicionX;
	private int PosicionY;
	private Color Color;

	public  JPanel_Lienzo() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
		g.setColor(Color);
		g.fillOval(PosicionX, PosicionY, PinzelGrosor, PinzelGrosor);
		
	}

public void Actualizar(int PinzelGrosor, int PosicionX,int PosicionY, Color Color) {
	this.Color = Color;
	this.PinzelGrosor = PinzelGrosor;
	this.PosicionX = PosicionX;
	this.PosicionY = PosicionY;
}

}
