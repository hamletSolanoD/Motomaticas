package RecursosCustomizados;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.JPanel;


import ValoresDefault.Constantes;

public class MyJPanel_ConFondo extends JPanel {
	private Image imagen;
	
	public MyJPanel_ConFondo(Image Imagen, int width, int height) {
		setBounds(0,0,width,height);
		setBackground(Constantes.Fondo);
		setOpaque(false);
		this.imagen = Imagen;
	}	
	public MyJPanel_ConFondo() {
		super();
		setBackground(Constantes.Fondo);	
		setOpaque(false);

		}
	
	public MyJPanel_ConFondo(LayoutManager layout) {
		super(layout);
		setBackground(Constantes.Fondo);	
		setOpaque(false);	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(imagen,0,0,this.getWidth(),this.getHeight(),null);
		super.paintComponent(g);
	}
	
	public void CambiarImagen(Image img) {
		this.imagen = img;
		super.repaint();
	}
	

}
