package VentanasProyecto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;

import javax.swing.JPanel;

import RecursosCustomizados.JPanel_Lienzo;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JSlider;
import javax.swing.SwingConstants;


public class JPanelPizarron extends JPanel implements Serializable, MouseMotionListener {

	private JPanel_Lienzo Pizarron = new JPanel_Lienzo();
	
	
	private JSlider Slider_PinzelGrueso;
	private Color ColorPinzel = new Color(0,0,0);
	
	public JPanelPizarron() {
		setLayout(new BorderLayout(0, 0));
		setBackground(new Color(0,0,0,0));
		
		add(Pizarron, BorderLayout.CENTER);
		Pizarron.addMouseMotionListener(this);
		Pizarron.setBackground(Color.white);
		
		JToolBar toolBar = new JToolBar(SwingConstants.VERTICAL);
		toolBar.setFloatable(false);
		add(toolBar, BorderLayout.WEST);
		
		JButton JButton_ColorPicker = new JButton("Color");
		JButton_ColorPicker.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				ColorPinzel = JColorChooser.showDialog(null, "Color del Pinzel",Color.BLACK);
			}
		});
		toolBar.add(JButton_ColorPicker);
		
		JPanel panel = new JPanel();
		toolBar.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		Slider_PinzelGrueso = new JSlider();
		Slider_PinzelGrueso.setPaintLabels(true);
		panel.add(Slider_PinzelGrueso, BorderLayout.CENTER);
		Slider_PinzelGrueso.setMinimum(1);
		Slider_PinzelGrueso.setToolTipText("Grosor del pinzel");
		Slider_PinzelGrueso.setPaintTicks(true);
		Slider_PinzelGrueso.setOrientation(SwingConstants.VERTICAL);
		
		JButton btn_Borrar = new JButton("Borrar");
		btn_Borrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ColorPinzel = Pizarron.getBackground();
			}
		});
		toolBar.add(btn_Borrar);
		
		
		
		setVisible(true);
	}


	@Override
	public void mouseDragged(MouseEvent mouse) {
		// TODO Auto-generated method stub
		Pizarron.Actualizar(Slider_PinzelGrueso.getValue(), mouse.getX(), mouse.getY(),ColorPinzel);
		Pizarron.paintComponents(Pizarron.getGraphics());
		
	}


	@Override
	public void mouseMoved(MouseEvent mouse) {
	}

}
