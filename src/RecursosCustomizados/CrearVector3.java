package RecursosCustomizados;

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
import javax.swing.text.BadLocationException;

import EjemploDeInyeccion.Vector3;
import ValoresDefault.Constantes;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Component;
import java.awt.Dialog;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JCheckBox;

public class CrearVector3 extends JDialog implements ActionListener {
	private final JPanel contentPanel = new JPanel();
	private JFrame FramePrincipal;
	
	private Color ColorApagado =  Constantes.DetallesSegundoColor;
	private Color ColorPrendido = Constantes.DetallesColor;

	private JTextField txtField_MagnitudX;
	private JTextField txtField_MagnitudY;
	private JTextField txtField_MagnitudZ;
	private JTextField txtField_ThetaX;
	private JTextField txtField_ThetaY;
	private JTextField txtField_ThetaZ;
	private JTextField txtField_Magnitud;
	private JCheckBox box_ThetaX;
	private JCheckBox box_ThetaY;
	private JCheckBox box_ThetaZ;
	
	private JPanel JPanel_ThetaX;
	private JPanel JPanel_ThetaY;
	private JPanel JPanel_ThetaZ;
	private JPanel JPanel_MagnitudY;
	private JPanel JPanel_MagnitudZ;
	private JPanel JPanel_MagnitudX;
	
	
	private ButtonGroup  DimensionMagnitudes;
	private JRadioButton DimensionMangitud_rbtn_3D;
	private JRadioButton DimensionMagnitud_rbtn_XY;
	private JRadioButton DimensionMagnitud_rbtn_XZ;
	private JRadioButton DimensionMagnitud_rbtn_YZ;
	
	
	private ButtonGroup  DimensionesAngulo;
	private JRadioButton DimensionAngulo_rbtn_3D;
	private JRadioButton DimensionAngulo_rbtn_XY;
	private JRadioButton DimensionAngulo_rbtn_XZ;
	private JRadioButton DimensionAngulo_rbtn_YZ;
	
	private int Dimension;	
	
	
	
	/* Dimension:
	* 0 = XYZ
	* 1 = XY
	* 2 = XZ
	* 3 = YZ
	*/
	
	Vector3 VectorNuevo = new Vector3("Vector Nuevo");
    


	private void ActualizarVector() {
		txtField_MagnitudX.setText(String.valueOf(VectorNuevo.getMagnitudX()));
		txtField_MagnitudY.setText(String.valueOf(VectorNuevo.getMagnitudY()));
		txtField_MagnitudZ.setText(String.valueOf(VectorNuevo.getMagnitudZ()));
		txtField_Magnitud.setText(String.valueOf(VectorNuevo.getMagnitud()));
		txtField_ThetaX.setText(String.valueOf(VectorNuevo.getThetaX()));
		txtField_ThetaY.setText(String.valueOf(VectorNuevo.getThetaY()));
		txtField_ThetaZ.setText(String.valueOf(VectorNuevo.getThetaZ()));
	}

	private void DesactivarEje(char panel) {
		/* Dimension:
		* 0 = XYZ
		* 1 = XY
		* 2 = XZ
		* 3 = YZ
		*/
		switch(panel) {
		case 'X':  
		case 'x': 
			Dimension = 3;
			txtField_MagnitudX.setEnabled(false);
			txtField_MagnitudY.setEnabled(true);
			txtField_MagnitudZ.setEnabled(true);
			
			
			JPanel_ThetaX.setBackground(ColorApagado);
			JPanel_ThetaY.setBackground(ColorPrendido);
			JPanel_ThetaZ.setBackground(ColorPrendido);
			JPanel_MagnitudX.setBackground(ColorApagado);
			JPanel_MagnitudY.setBackground(ColorPrendido);
			JPanel_MagnitudZ.setBackground(ColorPrendido);
			
			box_ThetaX.setSelected(false);
			box_ThetaY.setSelected(false);
			box_ThetaZ.setSelected(true);
			SeleccionarAnguloEnDimension('Z');

			txtField_ThetaX.setEnabled(false);
			txtField_ThetaY.setEnabled(true);
			txtField_ThetaZ.setEnabled(true);

			box_ThetaX.setEnabled(false);
			box_ThetaY.setEnabled(true);
			box_ThetaZ.setEnabled(true);
			
			break;

		case 'Y':  
			Dimension = 2;
			txtField_MagnitudX.setEnabled(true);
			txtField_MagnitudY.setEnabled(false);
			txtField_MagnitudZ.setEnabled(true);
	
			box_ThetaX.setSelected(true);
			box_ThetaY.setSelected(false);
			box_ThetaZ.setSelected(false);
			SeleccionarAnguloEnDimension('X');

			
			JPanel_ThetaX.setBackground(ColorPrendido);
			JPanel_ThetaY.setBackground(ColorApagado);
			JPanel_ThetaZ.setBackground(ColorPrendido);
			JPanel_MagnitudX.setBackground(ColorPrendido);
			JPanel_MagnitudY.setBackground(ColorApagado);
			JPanel_MagnitudZ.setBackground(ColorPrendido);
			
			txtField_ThetaX.setEnabled(true);
			txtField_ThetaY.setEnabled(false);
			txtField_ThetaZ.setEnabled(true);

			box_ThetaX.setEnabled(true);
			box_ThetaY.setEnabled(false);
			box_ThetaZ.setEnabled(true);
		case 'y':  break;

		case 'Z':  
		case 'z': 
			Dimension = 1;
			txtField_MagnitudX.setEnabled(true);
			txtField_MagnitudY.setEnabled(true);
			txtField_MagnitudZ.setEnabled(false);
			
			box_ThetaX.setSelected(true);
			box_ThetaY.setSelected(false);
			box_ThetaZ.setSelected(false);
			SeleccionarAnguloEnDimension('X');
			
			JPanel_ThetaX.setBackground(ColorPrendido);
			JPanel_ThetaY.setBackground(ColorPrendido);
			JPanel_ThetaZ.setBackground(ColorApagado);
			JPanel_MagnitudX.setBackground(ColorPrendido);
			JPanel_MagnitudY.setBackground(ColorPrendido);
			JPanel_MagnitudZ.setBackground(ColorApagado);
			
			txtField_ThetaX.setEnabled(true);
			txtField_ThetaY.setEnabled(true);
			txtField_ThetaZ.setEnabled(false);

			box_ThetaX.setEnabled(true);
			box_ThetaY.setEnabled(true);
			box_ThetaZ.setEnabled(false);
			break;

		case 'N': 
		case 'n': 
			Dimension = 0;
			txtField_MagnitudX.setEnabled(true);
			txtField_MagnitudY.setEnabled(true);
			txtField_MagnitudZ.setEnabled(true);
			
			JPanel_ThetaX.setBackground(ColorPrendido);
			JPanel_ThetaY.setBackground(ColorPrendido);
			JPanel_ThetaZ.setBackground(ColorPrendido);
			JPanel_MagnitudX.setBackground(ColorPrendido);
			JPanel_MagnitudY.setBackground(ColorPrendido);
			JPanel_MagnitudZ.setBackground(ColorPrendido);
			
			box_ThetaX.setSelected(true);
			box_ThetaY.setSelected(true);
			box_ThetaZ.setSelected(true);
	

			txtField_ThetaX.setEnabled(true);
			txtField_ThetaY.setEnabled(true);
			txtField_ThetaZ.setEnabled(true);

			box_ThetaX.setEnabled(true);
			box_ThetaY.setEnabled(true);
			box_ThetaZ.setEnabled(true);
			
			UnsetEditableAxis('N');
			

			break;

		
		}
	}

	
	private void UnsetEditableAxis(char eje) {
		switch(eje) {
		case 'X': txtField_ThetaX.setEditable(false); txtField_ThetaY.setEditable(true); txtField_ThetaZ.setEditable(true); break;
		case 'Y': txtField_ThetaX.setEditable(true); txtField_ThetaY.setEditable(false); txtField_ThetaZ.setEditable(true); break;
		case 'Z':txtField_ThetaX.setEditable(true); txtField_ThetaY.setEditable(true); txtField_ThetaZ.setEditable(false); break;
		case 'N': txtField_ThetaX.setEditable(true); txtField_ThetaY.setEditable(true); txtField_ThetaZ.setEditable(true); break;
		
		}
		
	}
	private void SeleccionarAnguloEnDimension(char Angulo) {
		switch(Angulo) {
		case 'X':
			
			if(box_ThetaX.isSelected()) {
			switch(Dimension) {
			case 0: 	
				// UnsetEditableAxis('Z'); box_ThetaZ.setSelected(false);
			break;
			case 1: UnsetEditableAxis('Y'); box_ThetaY.setSelected(false); break;
			case 2: UnsetEditableAxis('Z');  box_ThetaZ.setSelected(false);break;
		      }
			}
			
			else {
				switch(Dimension) {
			case 0: 
				box_ThetaX.setSelected(true);
				 
			break;
			case 1: UnsetEditableAxis('X'); box_ThetaY.setSelected(true); break;
			case 2: UnsetEditableAxis('X');  box_ThetaZ.setSelected(true);break;
		      }
				
				}
			break;
		case 'Y':
			if(box_ThetaY.isSelected()) {
			switch(Dimension) {
			case 0: 
			//	UnsetEditableAxis('X');  box_ThetaX.setSelected(false);
				break;
			case 1:  UnsetEditableAxis('X');  box_ThetaX.setSelected(false); break;
			case 3:  UnsetEditableAxis('Z'); box_ThetaZ.setSelected(false); break;
			}
			}
			else {
				switch(Dimension) {
				case 0: 
					box_ThetaY.setSelected(true);

				//	UnsetEditableAxis('Y');  
				//	if( box_ThetaX.isSelected()) box_ThetaZ.setSelected(true);
				//	else box_ThetaX.setSelected(true);
					
					break;
				case 1:  UnsetEditableAxis('Y');  box_ThetaX.setSelected(true); break;
				case 3:  UnsetEditableAxis('Y'); box_ThetaZ.setSelected(true); break;
				}
				
			}
			
			break;
		case 'Z':
			if(box_ThetaZ.isSelected()) {
			switch(Dimension) {
			case 0:	
			//	UnsetEditableAxis('Y'); box_ThetaY.setSelected(false); 
				break;
			case 2:  UnsetEditableAxis('X');  box_ThetaX.setSelected(false); break;
			case 3:  UnsetEditableAxis('Y'); box_ThetaY.setSelected(false); break;
			}}
			else {
				switch(Dimension) {
				case 0:
					box_ThetaZ.setSelected(true);

					//UnsetEditableAxis('Z');
					///if( box_ThetaY.isSelected()) box_ThetaX.setSelected(true);
					//else box_ThetaY.setSelected(true); 
					break;
				case 2:  UnsetEditableAxis('Z');  box_ThetaX.setSelected(true); break;
				case 3:  UnsetEditableAxis('Z'); box_ThetaY.setSelected(true); break;
				}
				
				
			}
			break;
		}
		
		VectorNuevo.ResetTheta();
		VectorNuevo.ResetMagnitudes();

	}
	
	public CrearVector3(JFrame FramePrincipal) {
		super(FramePrincipal,Dialog.ModalityType.TOOLKIT_MODAL);
		this.FramePrincipal = FramePrincipal;
		contentPanel.setBackground(Constantes.PrincipalColor);
		setBounds(100, 100, (Constantes.PantallaOrdenadorX/10)*8, (int)(Constantes.PantallaOrdenadorY/10)*4);
		getContentPane().setLayout(new BorderLayout());
				{
					JPanel Principal = new JPanel();
					getContentPane().add(Principal, BorderLayout.CENTER);
						Principal.setLayout(new GridLayout(1, 2, 0, 0));
					
					
						JPanel Contenido = new JPanel();
						Contenido.setBackground(Constantes.PrincipalColor);
						Principal.add(Contenido);
						Contenido.setLayout(new BorderLayout(0, 0));
						{
							JPanel buttonPane = new JPanel();
							buttonPane.setBackground(Constantes.PrincipalColor);
							Contenido.add(buttonPane, BorderLayout.SOUTH);
							buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
							{
								JButton okButton =Constantes.BotonRedondeado("OK", new Color(0,0,0,0),Constantes.DetallesColor);
								okButton.setActionCommand("OK");
								okButton.addActionListener(this);
								buttonPane.add(okButton);
								
								getRootPane().setDefaultButton(okButton);
							}
							{
								JButton cancelButton = Constantes.BotonRedondeado("Cancelar", new Color(0,0,0,0), Constantes.DetallesSegundoColor);
								cancelButton.setActionCommand("Cancel");
								cancelButton.addActionListener(this);
								buttonPane.add(cancelButton);
							}
						}
						Contenido.add(contentPanel, BorderLayout.CENTER);
						contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
						contentPanel.setLayout(new BorderLayout(0, 0));
						{
							JPanel panel_1_1 = new JPanel();
							panel_1_1.setBackground(Constantes.PrincipalColor);
							contentPanel.add(panel_1_1, BorderLayout.NORTH);
							panel_1_1.setLayout(new BorderLayout(0, 0));
							{
								JLabel lblNewLabel = new JLabel("Nuevo Vector");
								lblNewLabel.setForeground(Constantes.DetallesColor);
								lblNewLabel.setFont(Constantes.Titulos);
								panel_1_1.add(lblNewLabel, BorderLayout.CENTER);
								lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
							}
							{
								Component horizontalStrut = Box.createHorizontalStrut(20);
								panel_1_1.add(horizontalStrut, BorderLayout.EAST);
							}
							{
								Component verticalStrut = Box.createVerticalStrut(20);
								panel_1_1.add(verticalStrut, BorderLayout.SOUTH);
							}
							{
								Component verticalStrut = Box.createVerticalStrut(20);
								panel_1_1.add(verticalStrut, BorderLayout.NORTH);
							}
							{
								Component horizontalStrut = Box.createHorizontalStrut(20);
								panel_1_1.add(horizontalStrut, BorderLayout.WEST);
							}
						}
						{
							JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
							tabbedPane.setOpaque(false);
							
							contentPanel.add(tabbedPane, BorderLayout.CENTER);
							{
								JPanel panel_1_2 = new JPanel();
								panel_1_2.setBackground(Constantes.PrincipalColor);
								tabbedPane.addTab("X Y Z", null, panel_1_2, null);
								panel_1_2.setLayout(new BorderLayout(0, 0));
								{
									JPanel panel = new JPanel();
									panel.setBackground(Constantes.PrincipalColor);
									panel_1_2.add(panel, BorderLayout.NORTH);
									panel.setLayout(new GridLayout(1, 0, 0, 0));
								    DimensionMagnitudes = new ButtonGroup();
									
									
								    DimensionMangitud_rbtn_3D = new JRadioButton("plano 3D");
								    DimensionMangitud_rbtn_3D.setFont(Constantes.textoNormal);
								    DimensionMangitud_rbtn_3D.setForeground(Constantes.DetallesSegundoColor);
								    DimensionMangitud_rbtn_3D.setBackground(Constantes.PrincipalColor);
										panel.add(DimensionMangitud_rbtn_3D);
										DimensionMangitud_rbtn_3D.setActionCommand("Dimension3DMangitud");
										DimensionMangitud_rbtn_3D.addActionListener(this);
										DimensionMagnitudes.add(DimensionMangitud_rbtn_3D);
								
										DimensionMagnitud_rbtn_XY = new JRadioButton("2D: plano X Y");
										DimensionMagnitud_rbtn_XY.setFont(Constantes.textoNormal);
										DimensionMagnitud_rbtn_XY.setForeground(Constantes.DetallesSegundoColor);
										DimensionMagnitud_rbtn_XY.setBackground(Constantes.PrincipalColor);
										panel.add(DimensionMagnitud_rbtn_XY);
										DimensionMagnitud_rbtn_XY.setActionCommand("DimensionMagnitud_rbtn_XY");
										DimensionMagnitud_rbtn_XY.addActionListener(this);
										DimensionMagnitudes.add(DimensionMagnitud_rbtn_XY);

							
										DimensionMagnitud_rbtn_XZ = new JRadioButton("2D: plano X Z");
										DimensionMagnitud_rbtn_XZ.setFont(Constantes.textoNormal);
										DimensionMagnitud_rbtn_XZ.setForeground(Constantes.DetallesSegundoColor);
										DimensionMagnitud_rbtn_XZ.setBackground(Constantes.PrincipalColor);
										panel.add(DimensionMagnitud_rbtn_XZ);
										DimensionMagnitud_rbtn_XZ.setActionCommand("DimensionMagnitud_rbtn_XZ");
										DimensionMagnitud_rbtn_XZ.addActionListener(this);
										DimensionMagnitudes.add(DimensionMagnitud_rbtn_XZ);

								
									   DimensionMagnitud_rbtn_YZ = new JRadioButton("2D: plano Y Z");
									   DimensionMagnitud_rbtn_YZ.setFont(Constantes.textoNormal);
									   DimensionMagnitud_rbtn_YZ.setForeground(Constantes.DetallesSegundoColor);
									   DimensionMagnitud_rbtn_YZ.setBackground(Constantes.PrincipalColor);
										panel.add(DimensionMagnitud_rbtn_YZ);
										DimensionMagnitud_rbtn_YZ.setActionCommand("DimensionMagnitud_rbtn_YZ");
										DimensionMagnitud_rbtn_YZ.addActionListener(this);
										DimensionMagnitudes.add(DimensionMagnitud_rbtn_YZ);

									
								}
								{
									JButton btn_MagnitudesGenerar = Constantes.BotonRedondeado("Generar", new Color(0,0,0,0), Constantes.DetallesSegundoColor);
									panel_1_2.add(btn_MagnitudesGenerar, BorderLayout.SOUTH);
									btn_MagnitudesGenerar.setActionCommand("btn_MagnitudesGenerar");
									btn_MagnitudesGenerar.addActionListener(this);
								}
								{
									JPanel panel_1_1 = new JPanel();
									panel_1_1.setBackground(Constantes.PrincipalColor);
									panel_1_2.add(panel_1_1);
									panel_1_1.setLayout(new GridLayout(1, 0, 0, 0));
									{
									    JPanel_MagnitudX = new JPanel();
										panel_1_1.add(JPanel_MagnitudX);
										JPanel_MagnitudX.setLayout(new GridLayout(0, 1, 0, 0));
										{
											JLabel lblNewLabel_1 = new JLabel("X:");
											JPanel_MagnitudX.add(lblNewLabel_1);
											lblNewLabel_1.setForeground(Constantes.SecundarioColor);
											lblNewLabel_1.setFont(Constantes.textoNormal);
											lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
										}
										{
											txtField_MagnitudX = new JTextField();
											txtField_MagnitudX.setBackground(Constantes.SecundarioColor);
											txtField_MagnitudX.setForeground(Constantes.DetallesSegundoColor);
											JPanel_MagnitudX.add(txtField_MagnitudX);
											txtField_MagnitudX.setEnabled(false);
											txtField_MagnitudX.getDocument().addDocumentListener(new DocumentListener() {
												
												@Override
												public void removeUpdate(DocumentEvent e) {
												
												}
												
												@Override
												public void insertUpdate(DocumentEvent e) {
													// TODO Auto-generated method stub
													
													
													VectorNuevo.setMagnitudX(Constantes.ComprobarEntradas(e,FramePrincipal));
												
												}
												
												@Override
												public void changedUpdate(DocumentEvent e) {
												
												}
											});
											txtField_MagnitudX.setColumns(10);
										}
									}
									{
										Component horizontalStrut = Box.createHorizontalStrut(20);
										panel_1_1.add(horizontalStrut);
									}
									{
									    JPanel_MagnitudY = new JPanel();
										panel_1_1.add(JPanel_MagnitudY);
										JPanel_MagnitudY.setLayout(new GridLayout(0, 1, 0, 0));
										{
											JLabel lblNewLabel_2 = new JLabel("Y:");
											lblNewLabel_2.setForeground(Constantes.SecundarioColor);
											JPanel_MagnitudY.add(lblNewLabel_2);
											lblNewLabel_2.setFont(Constantes.botones);
											lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
										}
										{
											txtField_MagnitudY = new JTextField();
											txtField_MagnitudY.setBackground(Constantes.SecundarioColor);
											txtField_MagnitudY.setForeground(Constantes.DetallesSegundoColor);
											JPanel_MagnitudY.add(txtField_MagnitudY);
											txtField_MagnitudY.setEnabled(false);
											txtField_MagnitudY.getDocument().addDocumentListener(new DocumentListener() {
												
												@Override
												public void removeUpdate(DocumentEvent e) {
												
												}
												@Override
												public void insertUpdate(DocumentEvent e) {
													// TODO Auto-generated method stub
													VectorNuevo.setMagnitudY(Constantes.ComprobarEntradas(e,FramePrincipal));
													
												}
												
												@Override
												public void changedUpdate(DocumentEvent e) {
												
												}
											});
											txtField_MagnitudY.setColumns(10);
										}
									}
									{
										Component horizontalStrut = Box.createHorizontalStrut(20);
										panel_1_1.add(horizontalStrut);
									}
									{
									    JPanel_MagnitudZ = new JPanel();
										panel_1_1.add(JPanel_MagnitudZ);
										JPanel_MagnitudZ.setLayout(new GridLayout(0, 1, 0, 0));
										{
											JLabel lblNewLabel_3 = new JLabel("Z:");
											lblNewLabel_3.setForeground(Constantes.SecundarioColor);
											JPanel_MagnitudZ.add(lblNewLabel_3);
											lblNewLabel_3.setFont(Constantes.textoNormal);
											lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
										}
										{
											txtField_MagnitudZ = new JTextField();
											txtField_MagnitudZ.setBackground(Constantes.SecundarioColor);
											txtField_MagnitudZ.setForeground(Constantes.DetallesSegundoColor);
											JPanel_MagnitudZ.add(txtField_MagnitudZ);
											txtField_MagnitudZ.setEnabled(false);
											txtField_MagnitudZ.getDocument().addDocumentListener(new DocumentListener() {
												
												@Override
												public void removeUpdate(DocumentEvent e) {
												
												}
												
												@Override
												public void insertUpdate(DocumentEvent e) {
													// TODO Auto-generated method stub
													VectorNuevo.setMagnitudZ(Constantes.ComprobarEntradas(e,FramePrincipal));

												}
												
												@Override
												public void changedUpdate(DocumentEvent e) {
												
												}
											});
											txtField_MagnitudZ.setColumns(10);
										}
									}
								}
							}
							{
								JPanel panel_1_1 = new JPanel();
								panel_1_1.setBackground(Constantes.PrincipalColor);
								tabbedPane.addTab("\u03B1 \u03B3 \u00DF |A|", null, panel_1_1, null);
								panel_1_1.setLayout(new BorderLayout(0, 0));
								
									JPanel panel = new JPanel();
									panel.setBackground(Constantes.PrincipalColor);
									panel_1_1.add(panel, BorderLayout.NORTH);
									panel.setLayout(new GridLayout(1, 0, 0, 0));
									
								    DimensionesAngulo = new ButtonGroup();
									
									
								    DimensionAngulo_rbtn_3D = new JRadioButton("plano 3D");
								    DimensionAngulo_rbtn_3D.setForeground(Constantes.DetallesSegundoColor);
								    DimensionAngulo_rbtn_3D.setFont(Constantes.textoNormal);
								    DimensionAngulo_rbtn_3D.setBackground(Constantes.PrincipalColor);
									panel.add(DimensionAngulo_rbtn_3D);
									DimensionAngulo_rbtn_3D.setActionCommand("Dimension3DAngulo");
									DimensionAngulo_rbtn_3D.addActionListener(this);
									DimensionesAngulo.add(DimensionAngulo_rbtn_3D);
									
									    DimensionAngulo_rbtn_XY = new JRadioButton("2D: plano X Y");
									    DimensionAngulo_rbtn_XY.setForeground(Constantes.DetallesSegundoColor);
									    DimensionAngulo_rbtn_XY.setFont(Constantes.textoNormal);
									    DimensionAngulo_rbtn_XY.setBackground(Constantes.PrincipalColor);
										panel.add(DimensionAngulo_rbtn_XY);
										DimensionAngulo_rbtn_XY.setActionCommand("DimensionAngulo_rbtn_XY");
										DimensionAngulo_rbtn_XY.addActionListener(this);
										DimensionesAngulo.add(DimensionAngulo_rbtn_XY);
										

									
									
									    DimensionAngulo_rbtn_XZ = new JRadioButton("2D: plano X Z");
									    DimensionAngulo_rbtn_XZ.setForeground(Constantes.DetallesSegundoColor);
									    DimensionAngulo_rbtn_XZ.setFont(Constantes.textoNormal);
									    DimensionAngulo_rbtn_XZ.setBackground(Constantes.PrincipalColor);
										panel.add(DimensionAngulo_rbtn_XZ);
										DimensionAngulo_rbtn_XZ.setActionCommand("DimensionAngulo_rbtn_XZ");
										DimensionAngulo_rbtn_XZ.addActionListener(this);
										DimensionesAngulo.add(DimensionAngulo_rbtn_XZ);

									
									
									    DimensionAngulo_rbtn_YZ = new JRadioButton("2D: plano Y Z");
									    DimensionAngulo_rbtn_YZ.setForeground(Constantes.DetallesSegundoColor);
									    DimensionAngulo_rbtn_YZ.setFont(Constantes.textoNormal);
									    DimensionAngulo_rbtn_YZ.setBackground(Constantes.PrincipalColor);
										panel.add(DimensionAngulo_rbtn_YZ);
										DimensionAngulo_rbtn_YZ.setActionCommand("DimensionAngulo_rbtn_YZ");
										DimensionAngulo_rbtn_YZ.addActionListener(this);
										DimensionesAngulo.add(DimensionAngulo_rbtn_YZ);

									
								
								{
									JButton btn_AngulosGenerar = Constantes.BotonRedondeado("Generar", new Color(0,0,0,0),Constantes.DetallesSegundoColor);
									panel_1_1.add(btn_AngulosGenerar, BorderLayout.SOUTH);
									btn_AngulosGenerar.setActionCommand("btn_AngulosGenerar");
									btn_AngulosGenerar.addActionListener(this);
								}
								{
									JPanel panel_2_1 = new JPanel();
									panel_1_1.add(panel_2_1, BorderLayout.CENTER);
									panel_2_1.setLayout(new GridLayout(1, 0, 0, 0));
									{
									    JPanel_ThetaX = new JPanel();
									    
										panel_2_1.add(JPanel_ThetaX);
										JPanel_ThetaX.setLayout(new GridLayout(0, 1, 0, 0));
										{
											JLabel lblNewLabel_1 = new JLabel("\u03B8\r\nX");
											JPanel_ThetaX.add(lblNewLabel_1);
											lblNewLabel_1.setForeground(Constantes.SecundarioColor);
											lblNewLabel_1.setFont(new Font("", 0, Constantes.PantallaOrdenadorX/80));
											lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
										}
										{
											txtField_ThetaX = new JTextField();
											txtField_ThetaX.setBackground(Constantes.SecundarioColor);
											txtField_ThetaX.setForeground(Constantes.DetallesSegundoColor);
											txtField_ThetaX.setEnabled(false);
											JPanel_ThetaX.add(txtField_ThetaX);
											txtField_ThetaX.getDocument().addDocumentListener(new DocumentListener() {
												
												@Override
												public void removeUpdate(DocumentEvent e) {
												
												}
												
												@Override
												public void insertUpdate(DocumentEvent e) {
													VectorNuevo.setThetaX(Constantes.ComprobarEntradas(e,FramePrincipal));

												}
												
												@Override
												public void changedUpdate(DocumentEvent e) {
												
												}
											});
											txtField_ThetaX.setColumns(10);
										}
										{
										    box_ThetaX = new JCheckBox("");
										    box_ThetaX.setBorder(null);
										    box_ThetaX.setBackground(Constantes.PrincipalColor);
										    box_ThetaX.setEnabled(false);
										    box_ThetaX.setActionCommand("box_ThetaX");
										    box_ThetaX.addActionListener(this);
											box_ThetaX.setHorizontalAlignment(SwingConstants.CENTER);
											JPanel_ThetaX.add(box_ThetaX);
										}
									}
									{
									    JPanel_ThetaY = new JPanel();
										panel_2_1.add(JPanel_ThetaY);
										JPanel_ThetaY.setLayout(new GridLayout(0, 1, 0, 0));
										{
											JLabel lblNewLabel_2 = new JLabel("\u03B8Y");
											lblNewLabel_2.setForeground(Constantes.SecundarioColor);
											//lblNewLabel_2.setFont(new Font("", 0, Constantes.PantallaOrdenadorX/80));
											JPanel_ThetaY.add(lblNewLabel_2);
											lblNewLabel_2.setFont(new Font("",0,Constantes.PantallaOrdenadorX/80));
											lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
										}
										{
											txtField_ThetaY = new JTextField();
											txtField_ThetaY.setBackground(Constantes.SecundarioColor);
											txtField_ThetaY.setForeground(Constantes.DetallesSegundoColor);
											txtField_ThetaY.setEnabled(false);
											JPanel_ThetaY.add(txtField_ThetaY);
											txtField_ThetaY.getDocument().addDocumentListener(new DocumentListener() {
												
												@Override
												public void removeUpdate(DocumentEvent e) {
													// TODO Auto-generated method stub
													
												}
												
												@Override
												public void insertUpdate(DocumentEvent e) {
													// TODO Auto-generated method stub
													VectorNuevo.setThetaY(Constantes.ComprobarEntradas(e,FramePrincipal));

												}
												
												@Override
												public void changedUpdate(DocumentEvent e) {
													// TODO Auto-generated method stub
													
												}
											});
											txtField_ThetaY.setColumns(10);
										}
										{
										    box_ThetaY = new JCheckBox("");
										    box_ThetaY.setBorder(null);
										    box_ThetaY.setBackground(Constantes.PrincipalColor);
										    box_ThetaY.setEnabled(false);
										    box_ThetaY.setActionCommand("box_ThetaY");
										    box_ThetaY.addActionListener(this);
											box_ThetaY.setHorizontalAlignment(SwingConstants.CENTER);
											JPanel_ThetaY.add(box_ThetaY);
										}
									}
									{
									    JPanel_ThetaZ = new JPanel();
										panel_2_1.add(JPanel_ThetaZ);
										JPanel_ThetaZ.setLayout(new GridLayout(0, 1, 0, 0));
										{
											JLabel lblNewLabel_3 = new JLabel("\u03B8Z");
											lblNewLabel_3.setForeground(Constantes.SecundarioColor);
											lblNewLabel_3.setFont(new Font("", 0, Constantes.PantallaOrdenadorX/80));
											JPanel_ThetaZ.add(lblNewLabel_3);
											lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
										}
										{
											txtField_ThetaZ = new JTextField();
											txtField_ThetaZ.setBackground(Constantes.SecundarioColor);
											txtField_ThetaZ.setForeground(Constantes.DetallesSegundoColor);
											txtField_ThetaZ.setEnabled(false);
											JPanel_ThetaZ.add(txtField_ThetaZ);
											txtField_ThetaZ.getDocument().addDocumentListener(new DocumentListener() {
												
												@Override
												public void removeUpdate(DocumentEvent e) {
													// TODO Auto-generated method stub
													
												}
												
												@Override
												public void insertUpdate(DocumentEvent e) {
													// TODO Auto-generated method stub
													VectorNuevo.setThetaZ(Constantes.ComprobarEntradas(e,FramePrincipal));

												}
												
												@Override
												public void changedUpdate(DocumentEvent e) {
													// TODO Auto-generated method stub
													
												}
											});
											txtField_ThetaZ.setColumns(10);
										}
										{
										    box_ThetaZ = new JCheckBox("");
										    box_ThetaZ.setBorder(null);
										    box_ThetaZ.setBackground(Constantes.PrincipalColor);
										    box_ThetaZ.setEnabled(false);
										    box_ThetaZ.setActionCommand("box_ThetaZ");
										    box_ThetaZ.addActionListener(this);
											box_ThetaZ.setHorizontalAlignment(SwingConstants.CENTER);
											JPanel_ThetaZ.add(box_ThetaZ);
										}
									}
									{
										JPanel JPanelMagnitudThethas = new JPanel();
										JPanelMagnitudThethas.setBackground(ColorPrendido);
										panel_2_1.add(JPanelMagnitudThethas);
										JPanelMagnitudThethas.setLayout(new GridLayout(0, 1, 0, 0));
										{
											JLabel lblNewLabel_4 = new JLabel("|A|");
											lblNewLabel_4.setForeground(Constantes.SecundarioColor);
											lblNewLabel_4.setFont(new Font("", 0, Constantes.PantallaOrdenadorX/80));
											JPanelMagnitudThethas.add(lblNewLabel_4);
											lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
										}
										{
											txtField_Magnitud = new JTextField();
											txtField_Magnitud.setBackground(Constantes.SecundarioColor);
											txtField_Magnitud.setForeground(Constantes.DetallesSegundoColor);
											JPanelMagnitudThethas.add(txtField_Magnitud);
											txtField_Magnitud.getDocument().addDocumentListener(new DocumentListener() {
												
												@Override
												public void removeUpdate(DocumentEvent e) {
													// TODO Auto-generated method stub
													
												}
												
												@Override
												public void insertUpdate(DocumentEvent e) {
													// TODO Auto-generated method stub
												    VectorNuevo.setMagnitud(Constantes.ComprobarEntradas(e,FramePrincipal));

												}
												
												@Override
												public void changedUpdate(DocumentEvent e) {
													// TODO Auto-generated method stub
													
												}
											});
											txtField_Magnitud.setColumns(10);
										}
									}
								}
							}
						}
						MyJPanel_ConFondo Imagen = new MyJPanel_ConFondo();
						Principal.add(Imagen);
						try {
							Imagen.CambiarImagen(ImageIO.read(getClass().getResource("/vectores.jpg")));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Imagen.setLayout(new GridLayout(0, 1, 0, 0));
				}
				Inicializar();

			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			setVisible(true);
	}
	private void Inicializar() {
	    DimensionAngulo_rbtn_XY.setSelected(true);
	    DesactivarEje('Z');
		VectorNuevo.setThetaZ(0);
		DimensionMagnitudes.setSelected(DimensionMagnitud_rbtn_XY.getModel(),true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		switch(arg0.getActionCommand()) {
		case "OK": 	
			if(VectorNuevo.IsEmpty()) {
		 new MensajeError(FramePrincipal, "Vector incompleto");
	}
		
		else {
			if(VectorNuevo.getMagnitud() == 0){ VectorNuevo.CalcularDatosTheta();}
			else {CalcularMagnitudes();}   
			this.dispose();
			}
	
		break;
		case "Cancel":
			if(new MensajeConfirmar(FramePrincipal, "�Desea salir sin agregar vector?").Respuesta() == 0) 
			{ this.dispose(); VectorNuevo = null;}break;
		
		//////////////////////////////////////////// ANGULOS  ////////////////////////////////////////////////
		case "DimensionAngulo_rbtn_YZ": 	
			DesactivarEje('X');
			VectorNuevo.setThetaX(0);
			DimensionMagnitudes.setSelected(DimensionMagnitud_rbtn_YZ.getModel(),true);
			// ("Dimension de Angulo Y-Z");
			break;
		case "DimensionAngulo_rbtn_XZ": 
			DesactivarEje('Y');
			VectorNuevo.setThetaY(0);
			DimensionMagnitudes.setSelected(DimensionMagnitud_rbtn_XZ.getModel(),true);
			// ("Dimension de Angulo X-Z"); 
			break;
		case "DimensionAngulo_rbtn_XY": 
			DesactivarEje('Z');
			VectorNuevo.setThetaZ(0);
			DimensionMagnitudes.setSelected(DimensionMagnitud_rbtn_XY.getModel(),true);
			// ("Dimension de Angulo X-Y"); 
			break;
		case "Dimension3DAngulo": 
			DesactivarEje('N');
			DimensionMagnitudes.setSelected(DimensionMangitud_rbtn_3D.getModel(),true);
			// ("Dimension3DAngulo"); 
			break;	
			
	/////////////////////////////////////////// MAGNITUDEDS ///////////////////////////////////////////
		case "DimensionMagnitud_rbtn_XY":
			DesactivarEje('Z');
			VectorNuevo.setMagnitudZ(0);
			DimensionesAngulo.setSelected(DimensionAngulo_rbtn_XY.getModel(),true);
			// ("Dimension de Magnitud X-Y"); 
			break;
		case "DimensionMagnitud_rbtn_XZ":
			DesactivarEje('Y');
			VectorNuevo.setMagnitudY(0);
			DimensionesAngulo.setSelected(DimensionAngulo_rbtn_XZ.getModel(),true);
			// ("Dimension de Magnitud X-Z"); 
			break;
		case "DimensionMagnitud_rbtn_YZ": 
			DesactivarEje('X');
			VectorNuevo.setMagnitudX(0);
			DimensionesAngulo.setSelected(DimensionAngulo_rbtn_YZ.getModel(),true);
			// ("Dimension de Magnitud Y-Z"); 
			break;
		case "Dimension3DMangitud":
			DesactivarEje('N');
			DimensionesAngulo.setSelected(DimensionAngulo_rbtn_3D.getModel(),true);
			// ("Dimension3DMagnitud");
			break;
		
   ////////////////////////////// SELECCIONAR ANGULOS A USAR ////////////////////////////////////////
		case "box_ThetaX":  SeleccionarAnguloEnDimension('X'); break;
		case "box_ThetaY":  SeleccionarAnguloEnDimension('Y'); break;
		case "box_ThetaZ":  SeleccionarAnguloEnDimension('Z'); break;
//////////////////////////////////// GENERAR ANGULOS /////////////////////////////////////////////////
		case "btn_MagnitudesGenerar":   VectorNuevo.CalcularDatosTheta();              break;
		case "btn_AngulosGenerar":     		CalcularMagnitudes();                      break;
		}
		
		if(VectorNuevo!= null) {
		ActualizarVector();
		}
	}
	
	private void CalcularMagnitudes() {
			if(!(VectorNuevo.getMagnitud() != 0 && (VectorNuevo.getThetaX() != 0 || VectorNuevo.getThetaY() != 0 || VectorNuevo.getThetaZ() != 0))) {
				new MensajeError(FramePrincipal,"Angulos o magnitud vacia");
			}
			else {
				if(VectorNuevo.ComprobarAngulos()) {
				VectorNuevo.CalcularDatosMagnitud(Dimension);}
				else {
					new MensajeError(FramePrincipal,"Angulos no complementarios");
				}
		}
	}
	 public Vector3 GetVector() {
	      return VectorNuevo;
	   }


}
