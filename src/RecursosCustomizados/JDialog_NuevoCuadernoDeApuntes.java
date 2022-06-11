package RecursosCustomizados;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import ObjetosLogicos.interfazGrafica.CuadernoDeApuntesBaseLogica;
import ValoresDefault.Constantes;

import java.awt.Component;
import java.awt.Dialog;

import javax.swing.Box;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class JDialog_NuevoCuadernoDeApuntes extends JDialog implements ActionListener {

	private  JPanel contentPanel = new JPanel();

	CuadernoDeApuntesBaseLogica nuevoCuadernoApuntes = null;
	private JTextField Titulo;
	private JTextField Autor;
	private JFrame Padre;
	
	public JDialog_NuevoCuadernoDeApuntes(JFrame Padre) {
		
		super(Padre,Dialog.ModalityType.TOOLKIT_MODAL);
		this.Padre = Padre;
		contentPanel.setBackground(Constantes.SecundarioColor);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			Component horizontalStrut = Box.createHorizontalStrut(20);
			contentPanel.add(horizontalStrut, BorderLayout.WEST);
		}
		{
			Component verticalStrut = Box.createVerticalStrut(20);
			contentPanel.add(verticalStrut, BorderLayout.NORTH);
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(Constantes.SecundarioColor);
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(3, 2, 0, 0));
			{
				JLabel lblNewLabel = new JLabel("Titulo:");
				lblNewLabel.setFont(Constantes.textoNormal);
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblNewLabel);
			}
			{
				Titulo = new JTextField();
				Titulo.setFont(Constantes.textoNormal);
				panel.add(Titulo);
				Titulo.setColumns(10);
			}
			{
				Component verticalStrut = Box.createVerticalStrut(20);
				panel.add(verticalStrut);
			}
			{
				Component verticalStrut = Box.createVerticalStrut(20);
				panel.add(verticalStrut);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Autor:");
				lblNewLabel_1.setFont(Constantes.textoNormal);
				lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblNewLabel_1);
			}
			{
				Autor = new JTextField();
				Autor.setFont(Constantes.textoNormal);
				panel.add(Autor);
				Autor.setColumns(10);
			}
		}
		{
			Component horizontalStrut = Box.createHorizontalStrut(20);
			contentPanel.add(horizontalStrut, BorderLayout.EAST);
		}
		{
			Component verticalStrut = Box.createVerticalStrut(20);
			contentPanel.add(verticalStrut, BorderLayout.SOUTH);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Constantes.SecundarioColor);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setFont(Constantes.botones);
				okButton.setBackground(Constantes.TerciarioColor);
				okButton.setForeground(Constantes.TerciarioColor);
				okButton.setOpaque(false);
				okButton.setBorder(new LineBorder(Constantes.SecundarioColor, 20, true));
				okButton.setActionCommand("OK");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setFont(Constantes.botones);
				cancelButton.setBackground(Constantes.DetallesColor);
				cancelButton.setForeground(Constantes.DetallesColor);
				cancelButton.setOpaque(false);
				cancelButton.setBorder(new LineBorder(Constantes.SecundarioColor, 20, true));
				
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
		setVisible(true);
	}
	
	public CuadernoDeApuntesBaseLogica getCuadernoDeApuntes() {
		
		return nuevoCuadernoApuntes;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getActionCommand()) {
		case "OK": 	
			if(Titulo.getText().isEmpty() || Autor.getText().isEmpty()) {
			new MensajeError(Padre, "Campos incompletos");
			}
		else {
			nuevoCuadernoApuntes = new CuadernoDeApuntesBaseLogica(Titulo.getText(),Autor.getText());
			this.dispose();
			}
	
		break;
		case "Cancel": 
			MensajeConfirmar confirmar = new MensajeConfirmar(Padre, "�Desea abandonar sin crear?");
			if(confirmar.Respuesta() == 0) 
			{ this.dispose();}
			break;
		}
	}

}
