package VentanasProyecto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.Utilities;

import ObjetosLogicos.CuadernoDeApuntesBaseLogica;
import RecursosCustomizados.JDialog_NuevoCuadernoDeApuntes;
import ValoresDefault.Constantes;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;

import java.awt.Component;

import javax.jws.soap.SOAPBinding.Style;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.border.LineBorder;

public class Portada extends JFrame implements ActionListener {
	public Portada() {
		setBounds(Constantes.PantallaOrdenadorX/5,Constantes.PantallaOrdenadorY/9,(int)(Constantes.PantallaOrdenadorX/5)*3,(int)(Constantes.PantallaOrdenadorY/2));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		splitPane.setResizeWeight(0.10);
		splitPane.setDividerLocation(0.47);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(Constantes.DetallesColor);
		splitPane.setLeftComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(60);
		panel.add(horizontalStrut_2, BorderLayout.WEST);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(60);
		panel.add(horizontalStrut_3, BorderLayout.EAST);
		
		Component verticalStrut_2 = Box.createVerticalStrut(60);
		panel.add(verticalStrut_2, BorderLayout.SOUTH);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Constantes.DetallesColor);
		panel.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new GridLayout(3, 1, 0, 0));
		
		JButton btn_NuevoApunte = new JButton("Nuevo Apunte");
		btn_NuevoApunte.addActionListener(this);
		btn_NuevoApunte.setActionCommand("NuevoApunte");
		btn_NuevoApunte.setFont(Constantes.CrearFuente(Constantes.PantallaOrdenadorX/100, 3));
		btn_NuevoApunte.setBackground(Constantes.SecundarioColor);
		btn_NuevoApunte.setForeground(Constantes.PrincipalColor);
		btn_NuevoApunte.setOpaque(false);
		btn_NuevoApunte.setBorder(new LineBorder(Constantes.SecundarioColor, 20, true));
		panel_3.add(btn_NuevoApunte);
		
		Component verticalStrut_4 = Box.createVerticalStrut(20);
		panel_3.add(verticalStrut_4);
		
		JButton btn_CargarApunte = new JButton("Cargar Apunte");
		btn_CargarApunte.addActionListener(this);
		btn_CargarApunte.setBackground(Constantes.SecundarioColor);
		btn_CargarApunte.setForeground(Constantes.PrincipalColor);
		btn_CargarApunte.setActionCommand("CargarApunte");
		btn_CargarApunte.setFont(Constantes.CrearFuente(Constantes.PantallaOrdenadorX/100, 3));
		btn_CargarApunte.setOpaque(false);
		btn_CargarApunte.setBorder(new LineBorder(Constantes.SecundarioColor, 20, true));
		panel_3.add(btn_CargarApunte);
		
		Component verticalStrut_3 = Box.createVerticalStrut(60);
		panel.add(verticalStrut_3, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Constantes.PrincipalColor);
		splitPane.setRightComponent(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel Titulo = new JPanel();
		panel_1.add(Titulo);
		Titulo.setBackground(Constantes.PrincipalColor);
		Titulo.setLayout(new BorderLayout(0, 0));
		
		JTextPane lbl_Titulo = new JTextPane();
		lbl_Titulo.setForeground(Constantes.DetallesSegundoColor);
		lbl_Titulo.setEditable(false);
		lbl_Titulo.setFont(Constantes.Titulos);
		lbl_Titulo.setText("Mi Cuaderno de Aritmetica con Vectores");
		lbl_Titulo.setBackground(Constantes.PrincipalColor);
		StyledDocument doc = lbl_Titulo.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		Titulo.add(lbl_Titulo);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panel_1.add(verticalStrut, BorderLayout.NORTH);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panel_1.add(verticalStrut_1, BorderLayout.SOUTH);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel_1.add(horizontalStrut, BorderLayout.WEST);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel_1.add(horizontalStrut_1, BorderLayout.EAST);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getActionCommand()) {
		case "NuevoApunte":
			
		JDialog_NuevoCuadernoDeApuntes NuevoApunte = new JDialog_NuevoCuadernoDeApuntes(this);
		CuadernoDeApuntesBaseLogica CuadernoDeApuntesNuevo= NuevoApunte.getCuadernoDeApuntes();
		if(CuadernoDeApuntesNuevo != null) {
			MainApunteFrame MainApunteFrame = new MainApunteFrame(CuadernoDeApuntesNuevo);
			dispose();
		}
		break;
		case "CargarApunte" : 
			JFileChooser file = new JFileChooser();
			file.showOpenDialog(this);
			File FileAbierto = file.getSelectedFile();
			
			try {
				FileInputStream FIS = new FileInputStream(FileAbierto);
				ObjectInputStream OIS = new ObjectInputStream(FIS);
			    CuadernoDeApuntesBaseLogica  CuadernoDeApuntesCargado = (CuadernoDeApuntesBaseLogica)OIS.readObject();
				if(CuadernoDeApuntesCargado != null) {
				 
					
					
					MainApunteFrame MainApunteFrame = new MainApunteFrame(CuadernoDeApuntesCargado);
					MainApunteFrame.panel_Vectores.JIFActivo = (JInternalFrameOperacion) OIS.readObject();
					dispose();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			

			break;
		
		}
	}
}
