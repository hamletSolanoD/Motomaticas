package Motomaticas.VentanasProyecto;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Motomaticas.ObjetosLogicos.interfazGrafica.CuadernoDeApuntesBaseLogica;
import Motomaticas.RecursosCustomizados.MensajeConfirmar;
import Motomaticas.ValoresDefault.Constantes;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;


import java.awt.Component;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public  class ApunteMotomaticas extends JFrame implements WindowListener, Serializable, ActionListener{

	private CuadernoDeApuntesBaseLogica MainApunte;
	
	public static JPanelProcedimientoMatematico panel_Matematico;
	public static JPanel panel_Grafico;
	public static JPanelPizarron panel_Pizarra;
	public static JPanelNotas panel_Notas;
	public static JpanelOperaciones panel_Operaciones;
	public static JPanelVisualizar panel_Visualizar;

	

	/*crear o leer un apunte ya guardado, crear toda la parte visual desde la parte logica del apunte*/
	public ApunteMotomaticas(CuadernoDeApuntesBaseLogica Apunte) {
		MainApunte = Apunte;
		setBounds(Constantes.PantallaOrdenadorX/25,Constantes.PantallaOrdenadorY/20,(Constantes.PantallaOrdenadorX/10)*9,(Constantes.PantallaOrdenadorY/10)*9);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		    panel_Operaciones = Apunte.getOperaciones();
		    panel_Matematico = Apunte.getMatematico();
		    panel_Notas = Apunte.getNotas();
		    panel_Pizarra = Apunte.getPizarra();
		    panel_Visualizar = Apunte.getVisualizar();
			
			
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Constantes.SecundarioColor);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.NORTH);	
		tabbedPane.setBorder(null);
		panel.add(tabbedPane, BorderLayout.CENTER);
	
		//tabbedPane.setOpaque(false);
		tabbedPane.setForeground(Constantes.DetallesSegundoColor);
		tabbedPane.setBorder(null);
		tabbedPane.setBackground(Constantes.DetallesColor);
		tabbedPane.setFont(Constantes.textoNormal);


		if(panel_Operaciones != null){
			tabbedPane.addTab("Operaciones", null, panel_Operaciones, null);
		}
		if(panel_Matematico != null){
			tabbedPane.addTab("Matematico", null, panel_Matematico, null);
		}
		if(panel_Visualizar != null){
			tabbedPane.addTab("Vizualizar", null, panel_Visualizar, null);
		}
		if(panel_Notas != null){
			tabbedPane.addTab("Notas", null, panel_Notas, null);
		}
		if(panel_Pizarra != null){
			tabbedPane.addTab("Pizarra", null, panel_Pizarra, null);
		}




		
	   
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Constantes.SecundarioColor);
		getContentPane().add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JLabel lbl_Titulo = new JLabel();
		lbl_Titulo.setForeground(Constantes.DetallesSegundoColor);
		lbl_Titulo.setText(MainApunte.getNombreDelApunte());
		lbl_Titulo.setFont(Constantes.Titulos);
		lbl_Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lbl_Titulo);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel_5.add(horizontalStrut, BorderLayout.EAST);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panel_5.add(verticalStrut, BorderLayout.NORTH);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panel_5.add(verticalStrut_1, BorderLayout.SOUTH);
		
		JButton btnNewButton;
		try {
			btnNewButton = new JButton(new ImageIcon((ImageIO.read(this.getClass().getResource("/save.png")).getScaledInstance(getWidth()/33, getWidth()/33, Image.SCALE_SMOOTH))));
			btnNewButton.setBorder(null);
			btnNewButton.setBackground(Constantes.SecundarioColor);
			btnNewButton.addActionListener(this);
			btnNewButton.setActionCommand("Guardar");
			panel_5.add(btnNewButton, BorderLayout.WEST);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		setVisible(true);
	}
	
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		 if(new MensajeConfirmar(this, "Desea salir de la aplicacion?").Respuesta() == 0) { this.dispose();}		

	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getActionCommand()){
			case "Guardar": 

				MainApunte.setOperaciones(panel_Operaciones);
				MainApunte.setPizarra(panel_Pizarra);
				MainApunte.setMatematico(panel_Matematico);
				MainApunte.setNotas(panel_Notas);
				MainApunte.setVisualizar(panel_Visualizar);

				try {
				    JFileChooser jFileChooser = new JFileChooser();
					jFileChooser.showSaveDialog(this);
					File FileGuardado = jFileChooser.getSelectedFile();
					
					FileOutputStream FW = new FileOutputStream(FileGuardado+".ApunteFisica");
					ObjectOutputStream OOS = new ObjectOutputStream(FW);
					OOS.writeObject(MainApunte);
					OOS.writeObject(JpanelOperaciones.JIFActivo);
				}
				catch(IOException e) {}
					
			
			break;
			
		}
	}

}
