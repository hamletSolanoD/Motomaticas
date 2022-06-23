package Motomaticas.RecursosCustomizados;

import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;


import Motomaticas.ValoresDefault.Constantes;

public class JPanelDragAndDropBotonesAritmeticos extends JPanel {


    ArrayList<BotonAritmetico> botonesAritmeticos = new ArrayList<BotonAritmetico>() ;
    BotonAritmetico BotonMatematicoEnfocado;
	GridBagConstraints constrainForNewButtons;
	private int X,Y;//posicion de insercion en la consola


    

    public JPanelDragAndDropBotonesAritmeticos(){
		this.setBackground(Constantes.PrincipalColor);
		this.setLayout(new GridBagLayout());





    }


	private BotonAritmetico BotonAritmeticoSobreDe(){
		return null;
	}

	private BotonAritmetico BotonAritmeticoArrastrando(){
		return null;
	}



    // Agregar boton aritmetico a la seccion de operaciones registradas o historial
	// de operaciones
	private void AgregarBotonAritmetico(BotonAritmetico botonAritmetico) {
		botonAritmetico.setFont(Constantes.botones);
		botonesAritmeticos.add(botonAritmetico);
		for (BotonAritmetico btnAritmetico : botonesAritmeticos) {
			this.add(btnAritmetico,constrainForNewButtons);
		}
		this.paintAll(this.getGraphics());
	}






    
}
