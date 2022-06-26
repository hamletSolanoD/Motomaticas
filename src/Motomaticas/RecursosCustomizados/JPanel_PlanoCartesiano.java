package Motomaticas.RecursosCustomizados;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


import java.awt.BasicStroke;

public class JPanel_PlanoCartesiano extends JPanel {
private int SeparacionX;
private int SeparacionY;
private int Divisiones;
private double Angulo = 90;
	public JPanel_PlanoCartesiano(int Divisiones) {
this.Divisiones = Divisiones;
setBounds(0,0,928,794);

	}
	public static double redondeo(double Numero) {
		   double scale = Math.pow(10, 2);
		    return Math.round(Numero * scale) / scale;
	}
	private void PintarRecta(Graphics g) {
		g.setFont(new Font("",1,SeparacionX/3));
		
		//Eje X
		for(int i = 1; i < Divisiones; i++) {
			g.fillRect((SeparacionX*i), (getHeight()/2)-SeparacionY/2,SeparacionX/6,SeparacionY);
		}
		g.setColor(Color.black);
		for(double e =  Divisiones/2 ; e > 1 ; e--) {
			String Etiqueta = Double.toString( redondeo((((double)Divisiones/((double)Divisiones/2))*e)/(double)Divisiones));
			g.drawString("-"+Etiqueta, SeparacionX+(SeparacionX*((Divisiones/2)-((int)e))), (getHeight()/2)+SeparacionY);			
			}
		for(double e = 2 ; e <=  Divisiones/2 ; e++) {
			String Etiqueta = Double.toString( redondeo((((double)Divisiones/((double)Divisiones/2))*e)/(double)Divisiones));
			g.drawString(Etiqueta, (SeparacionX*((Divisiones/2)+((int)e)))-SeparacionX, (getHeight()/2)+SeparacionY);			
			}
		
		// Eje Y
		
		for(int i = 1; i < Divisiones; i++) {
			g.setColor(Color.blue);
			g.fillRect((getWidth()/2)-SeparacionX/2 ,SeparacionY*i,SeparacionX,SeparacionY/6);
		}
		g.setColor(Color.black);

		for(double e =  Divisiones/2 ; e > 1 ; e--) {
			String Etiqueta = Double.toString( redondeo((((double)Divisiones/((double)Divisiones/2))*e)/(double)Divisiones));
			g.drawString(Etiqueta,  (getWidth()/2)+SeparacionX/2  ,SeparacionY/4+SeparacionY+(SeparacionY*  ((Divisiones/2)-((int)e))  ));			
			}
		
		for(double e = 2 ; e <=  Divisiones/2 ; e++) {
			String Etiqueta = Double.toString( redondeo((((double)Divisiones/((double)Divisiones/2))*e)/(double)Divisiones));
			g.drawString("-"+Etiqueta,  (getWidth()/2)+SeparacionX/2,SeparacionY/4+SeparacionY+(SeparacionY*((Divisiones/2)+((int)e)))-SeparacionY);			
			}
	}
	
	
	private void DibujarVector(double Grados, Graphics g) {
		double MagnitudX = Math.cos(Math.toRadians(Grados));
		double MagnitudY = Math.sin(Math.toRadians(Grados));
		
		
		int DireccionX = (getWidth()/2) + (int)((getWidth()/2)*MagnitudX);
		int DireccionY = (getHeight()/2) - (int)((getHeight()/2)*MagnitudY);
		
		g.setColor(Color.ORANGE);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(10));
		g.drawLine(getWidth()/2, getHeight()/2,DireccionX,DireccionY);
		

	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		SeparacionX = getWidth()/Divisiones;
		SeparacionY = getHeight()/Divisiones;
		 g.setColor (Color.blue);
		 g.fillRect(0,getHeight()/2-SeparacionY/20,getWidth(),SeparacionY/10);
		 
		 g.setColor (Color.red);
		 g.fillRect(getWidth()/2-SeparacionX/20,0,SeparacionX/10,getHeight());
		 PintarRecta(g);	
		 
		 if(Angulo != 90) {
		 DibujarVector(Angulo, g);
		 }
	} 
	
	public void setAngulo(double Angulo) {
		this.Angulo = Angulo;
				
	}

}
