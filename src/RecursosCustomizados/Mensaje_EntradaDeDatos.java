package RecursosCustomizados;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import ValoresDefault.Constantes;
import javax.swing.JTextField;

public class Mensaje_EntradaDeDatos extends JDialog {
	private String Respuesta;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	
	public Mensaje_EntradaDeDatos(JFrame Padre,String Mensaje) {
		super(Padre, "Confirmar", Dialog.ModalityType.TOOLKIT_MODAL);
		
		setBounds(100, 100, Constantes.PantallaOrdenadorX/6, Constantes.PantallaOrdenadorY/5);
		getContentPane().setLayout(new BorderLayout());
		
		JTextPane lbl_Titulo = new JTextPane();
		lbl_Titulo.setForeground(Constantes.PrincipalColor.darker());
		lbl_Titulo.setEditable(false);
		lbl_Titulo.setFont(Constantes.botones);
		lbl_Titulo.setText(Mensaje);
		lbl_Titulo.setBackground(Constantes.SecundarioColor);
		StyledDocument doc = lbl_Titulo.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.add(lbl_Titulo);
		contentPanel.setBackground(Constantes.SecundarioColor);
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		textField = new JTextField();
		textField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				Respuesta = textField.getText();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		textField.setFont(Constantes.textoNormal);
		textField.setForeground(Constantes.DetallesColor);
		textField.setBackground(Constantes.PrincipalColor);
		contentPanel.add(textField, BorderLayout.SOUTH);
		textField.setColumns(10);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Constantes.SecundarioColor);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = Constantes.BotonRedondeado("OK",new Color(0,0,0,0),Constantes.DetallesColor);
				buttonPane.add(okButton);
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						dispose();
					}
				});
				getRootPane().setDefaultButton(okButton);
				
				JButton Cancel = Constantes.BotonRedondeado("Cancel",new Color(0,0,0,0),Constantes.TerciarioColor);
				Cancel.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Respuesta = null;
						dispose();
					}
				});
				buttonPane.add(Cancel);
			}
		}
		setVisible(true);
	}
	public String Respuesta() {
		return Respuesta;
	}

}
