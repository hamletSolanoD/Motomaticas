package VentanasProyecto;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import RecursosCustomizados.BotonApuntes;
import ValoresDefault.Constantes;

import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.SwingConstants;

public  class JPanelAlgebra extends JPanel implements Serializable {
	public   JPanel SuperPanel_JPaneAlgebra = new JPanel(new BorderLayout());

	
	public   ArrayList<BotonApuntes> BotonesDeApuntes = new ArrayList<BotonApuntes>();
	private  BotonApuntes BotonApuntesEnfocado = null;

	private  JTextPane JTextPane_PropiedadesApunteSeleccionado = new JTextPane();
	private  JPanel JPanel_Apuntes = new JPanel(new GridBagLayout());
	private  JPanel panel = new JPanel();
	private  JLabel JLabel_OperacionActiva = new JLabel("");
	
	
	
	private final Component horizontalStrut = Box.createHorizontalStrut(20);
	private final Component verticalStrut = Box.createVerticalStrut(20);
	private final Component verticalStrut_1 = Box.createVerticalStrut(20);
	private final Component horizontalStrut_1 = Box.createHorizontalStrut(20);

	
	
	public JPanelAlgebra() {
		setLayout(new BorderLayout(0, 0));
		this.add(SuperPanel_JPaneAlgebra,BorderLayout.CENTER);
		
		JLabel_OperacionActiva.setForeground(Constantes.PrincipalColor);
		panel.setBackground(Constantes.DetallesColor);
		SuperPanel_JPaneAlgebra.add(panel, BorderLayout.NORTH);
		
		JPanel_Apuntes.setBackground(Constantes.PrincipalColor);
		
		panel.setLayout(new BorderLayout(0, 0));
		JLabel_OperacionActiva.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel_OperacionActiva.setFont(Constantes.Titulos);
		panel.add(JLabel_OperacionActiva,BorderLayout.CENTER);
		
		panel.add(horizontalStrut, BorderLayout.WEST);
		
		panel.add(verticalStrut, BorderLayout.NORTH);
		
		panel.add(verticalStrut_1, BorderLayout.SOUTH);
		panel.add(horizontalStrut_1, BorderLayout.EAST);
		JTextPane_PropiedadesApunteSeleccionado.setBackground(Constantes.PrincipalColor);
		JTextPane_PropiedadesApunteSeleccionado.setFont(Constantes.textoNormal);
		//JTextPane_PropiedadesApunteSeleccionado.setEnabled(false);
		JTextPane_PropiedadesApunteSeleccionado.setEditable(false);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.50);
		splitPane.setDividerLocation(0.50);
		SuperPanel_JPaneAlgebra.add(splitPane);
		
		JScrollPane Operaciones = new JScrollPane();
		Operaciones.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		splitPane.setLeftComponent(Operaciones);
		Operaciones.setViewportView(JPanel_Apuntes);
		
		
		
		
		JScrollPane Informacion = new JScrollPane();
		Informacion.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Informacion.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		splitPane.setRightComponent(Informacion);
		Informacion.setViewportView(JTextPane_PropiedadesApunteSeleccionado);
		
		
	}
	public void AgregarBotonDeApuntes(BotonApuntes BotonApuntes) {
		BotonApuntes.setFont(Constantes.botones);
		if(JLabel_OperacionActiva.getText().isEmpty()) {
			JLabel_OperacionActiva.setText(
					JpanelOperaciones.JIFActivo.getTitle()
					);
			}
		BotonApuntes.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!BotonApuntes.isSeleccionado()) {
					for(BotonApuntes f : BotonesDeApuntes) {
						f.setSeleccionado(false);
						f.setBackground(Constantes.SecundarioColor);
						f.setForeground(Constantes.DetallesSegundoColor);

						f.setFont(Constantes.botones);				
					}
					BotonApuntes.setSeleccionado(true);
					BotonApuntes.setBackground(Constantes.DetallesColor);
					BotonApuntes.setForeground(Constantes.PrincipalColor);
					BotonApuntes.setFont(Constantes.botones.deriveFont(Font.BOLD));
				BotonApuntesEnfocado = BotonApuntes;
				JTextPane_PropiedadesApunteSeleccionado.setText(BotonApuntesEnfocado.getApunte().toString());// Sin mostrar imagenes
				}
				else {
					BotonApuntesEnfocado = null;
					BotonApuntes.setSeleccionado(false);
					BotonApuntes.setBackground(Constantes.SecundarioColor);
					BotonApuntes.setForeground(Constantes.DetallesSegundoColor);
					BotonApuntes.setFont(Constantes.botones);}
			}
		});
		
		BotonesDeApuntes.add(BotonApuntes);
	
		
		GridBagConstraints Constrains = new GridBagConstraints();
		Constrains.fill = GridBagConstraints.HORIZONTAL;
		Constrains.gridx = 0;
		Constrains.weightx = 1;
		Constrains.weighty = 1;
		Constrains.insets = new Insets(5, 5, 5, 5);
		JPanel_Apuntes.add(BotonApuntes,Constrains);
		SuperPanel_JPaneAlgebra.paintAll(SuperPanel_JPaneAlgebra.getGraphics());
	}

	public  void HardReset_JPanelAlgebra() {
		BotonesDeApuntes.clear();
		BotonApuntesEnfocado = null;
		JTextPane_PropiedadesApunteSeleccionado.setText("Sin Especificaciones");
		JPanel_Apuntes.removeAll();
		JLabel_OperacionActiva.setText("");
		
		
	}
	
	
	
	

}
