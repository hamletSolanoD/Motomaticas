package Motomaticas.VentanasProyecto;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import Motomaticas.RecursosCustomizados.MensajeConfirmar;
import Motomaticas.RecursosCustomizados.MensajeError;
import Motomaticas.RecursosCustomizados.Mensaje_EntradaDeDatos;
import Motomaticas.ValoresDefault.Constantes;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import java.awt.Component;
import javax.swing.Box;

public class JpanelOperaciones extends JPanel implements Serializable, ActionListener, InternalFrameListener{

	
	private ArrayList<JInternalFrameOperacion> JIFOperaciones = new ArrayList<JInternalFrameOperacion>();
	public static JInternalFrameOperacion JIFActivo;
	private JDesktopPane desktopPane;
	
	public JpanelOperaciones() {
		setLayout(new BorderLayout(0, 0));
		
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(Constantes.SecundarioColor);
		toolBar.setBorderPainted(false);		
	
		toolBar.setFloatable(false);
		add(toolBar, BorderLayout.NORTH);
	
		JButton AgregarOperacion = Constantes.BotonRedondeado("Agregra", Constantes.PrincipalColor, Constantes.DetallesColor);
		AgregarOperacion.setFont(Constantes. textoNormal);//// POR AHORA
		AgregarOperacion.setActionCommand("+");
		AgregarOperacion.addActionListener(this);
		toolBar.add(AgregarOperacion);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		toolBar.add(horizontalStrut);
		
		
		JButton QuitarOperacion = Constantes.BotonRedondeado("Eliminar", Constantes.PrincipalColor, Constantes.DetallesColor);
		QuitarOperacion.setActionCommand("-");
		QuitarOperacion.setFont(Constantes.textoNormal);/////////POR AHORA 
		QuitarOperacion.addActionListener(this);
		toolBar.add(QuitarOperacion);
		
		
	    desktopPane = new JDesktopPane();
	    desktopPane.setBackground(Constantes.PrincipalColor);
		add(desktopPane, BorderLayout.CENTER);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()) {
		case "+": 
		String TitulOperacionInput = new Mensaje_EntradaDeDatos((JFrame)SwingUtilities.getRoot(this), "Nombre de la operacion:").Respuesta();

		if(!(TitulOperacionInput == null)) {
			if(TitulOperacionInput.length() > 3) {
			JInternalFrameOperacion nuevaOperacion = new JInternalFrameOperacion(TitulOperacionInput);
			nuevaOperacion.addInternalFrameListener(this);
			JIFOperaciones.add(nuevaOperacion); 	
			desktopPane.add(nuevaOperacion);
			}
			else { new	MensajeError((JFrame)SwingUtilities.getRoot(this),"Nombre muy corto, minimo 4 letras");
}
		}
		else {
			new MensajeError((JFrame)SwingUtilities.getRoot(this), "Nombre vacio");

		}
		break;
		
		case "-": 
			if(!(JIFActivo == null)) {
				MensajeConfirmar EliminarOperacion = new MensajeConfirmar((JFrame)SwingUtilities.getRoot(this), "Seguro que quieres eliminar esta operacion?");
				if(EliminarOperacion.Respuesta() == 0) {
			    JIFOperaciones.remove(JIFActivo);
			    desktopPane.remove(JIFActivo);
			    desktopPane.repaint();
				JIFActivo = null;
				}
			}
			else {
				new MensajeError((JFrame)SwingUtilities.getRoot(this), "Operacion no seleccionada");

			}
			
			
			break;
		
		
		}
		
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		JIFActivo = (JInternalFrameOperacion)arg0.getInternalFrame();
		JIFActivo.CalcularConsolaOutput();
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
