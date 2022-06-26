package ModulosImportados.NumerosReales;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


import Motomaticas.ObjetosLogicos.motorMatematico.variables.UnidadMatematica;
import Motomaticas.RecursosCustomizados.MensajeConfirmar;
import Motomaticas.ValoresDefault.Constantes;

import javax.swing.JLabel;

import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Dialog;

import javax.swing.Box;

public class crearUnidadNumerosRacionales extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	UnidadNumerosRacionales UnidadNueva = new UnidadNumerosRacionales();
	private JTextField JtextField_NuevaUnidad;
	private JFrame FramePrincipal;
	
	public crearUnidadNumerosRacionales(JFrame FramePrincipal) {
		super(FramePrincipal,Dialog.ModalityType.TOOLKIT_MODAL);
		this.FramePrincipal = FramePrincipal;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Constantes.PrincipalColor);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Agregar Nueva Constante:");
		lblNewLabel_1.setForeground(Constantes.DetallesColor);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(Constantes.textoNormal.deriveFont(Font.BOLD));
		contentPanel.add(lblNewLabel_1, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setBackground(Constantes.PrincipalColor);
		contentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JtextField_NuevaUnidad = new JTextField();
		JtextField_NuevaUnidad.setBackground(Constantes.SecundarioColor);
		JtextField_NuevaUnidad.setText("0.0");
		JtextField_NuevaUnidad.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				UnidadNueva = (new UnidadNumerosRacionales(Constantes.ComprobarEntradas(e,FramePrincipal)));
				
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		panel.add(JtextField_NuevaUnidad, BorderLayout.CENTER);
		JtextField_NuevaUnidad.setColumns(10);
		
		Component verticalStrut = Box.createVerticalStrut(50);
		panel.add(verticalStrut, BorderLayout.NORTH);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut, BorderLayout.EAST);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_1, BorderLayout.WEST);
		
		Component verticalStrut_1 = Box.createVerticalStrut(50);
		panel.add(verticalStrut_1, BorderLayout.SOUTH);
		
	
		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setBackground(Constantes.PrincipalColor);
				JButton okButton = Constantes.BotonRedondeado("OK", new Color(0,0,0,0), Constantes.TerciarioColor);
				okButton.addActionListener(this);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			
				JButton cancelButton = Constantes.BotonRedondeado("Cancelar", new Color(0,0,0,0), Constantes.DetallesSegundoColor);
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			setVisible(true);
		
	}

	public UnidadNumerosRacionales getUnidadNueva() {
		return UnidadNueva;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getActionCommand()) {
	case "OK":
		this.dispose();
		break;
	case "Cancel": 
		MensajeConfirmar confirmarCancelar = new MensajeConfirmar(FramePrincipal, "Desea salir sin agregar unidad?");
		
		if(confirmarCancelar.Respuesta() == 0) {this.dispose(); UnidadNueva = null;} break;
		
		
		
		
		}} }
