package RecursosCustomizados;

import java.awt.Dialog;

import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import javax.swing.ScrollPaneConstants;

import ObjetosLogicos.motorMatematico.operaciones.OperacionMatematica;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import ValoresDefault.Constantes;

import java.awt.event.ActionEvent;

public class JDialog_CrearOperacionMatematica extends JDialog {
    private final JPanel contentPanel = new JPanel();
    private OperacionMatematica operacionMatematicaCreada;

    private void inicializarOpcionesOperacionMatematica() {

        for (OperacionMatematica operacionMatematica : OperacionMatematica.TotalOperacionesMatematicas) {
            JPanel OperacionesMatematicasJP = new JPanel();
            OperacionesMatematicasJP.setBackground(Constantes.PrincipalColor);
            OperacionesMatematicasJP.setLayout(new BorderLayout());

            BotonAritmetico nuevaOperacion = new BotonAritmetico(operacionMatematica);
            nuevaOperacion.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    operacionMatematicaCreada = operacionMatematica.clonarNuevaInstanciaOperacionMatematica();
                    System.out.println(operacionMatematicaCreada);
                    dispose();
                }
            });

            OperacionesMatematicasJP.add(nuevaOperacion, BorderLayout.CENTER);
            OperacionesMatematicasJP.add(Box.createVerticalStrut(5), BorderLayout.SOUTH);
            OperacionesMatematicasJP.add(Box.createVerticalStrut(5), BorderLayout.NORTH);
            OperacionesMatematicasJP.add(Box.createHorizontalStrut(5), BorderLayout.EAST);
            OperacionesMatematicasJP.add(Box.createHorizontalStrut(5), BorderLayout.WEST);

            contentPanel.add(OperacionesMatematicasJP);

        }

    }
    

    public JDialog_CrearOperacionMatematica(JFrame Padre, String Mensaje) {
        super(Padre, "Confirmar", Dialog.ModalityType.TOOLKIT_MODAL);
        setBounds(100, 100, Constantes.PantallaOrdenadorX / 3, Constantes.PantallaOrdenadorY / 3);
        setTitle("Insertar operacion Matematica");
        setResizable(false);

        contentPanel.setBackground(Constantes.PrincipalColor);
        contentPanel.setLayout(new GridLayout(0, 2));

        JScrollPane Operaciones = new JScrollPane();
        Operaciones.setBackground(Constantes.PrincipalColor);
        Operaciones.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        Operaciones.setViewportView(contentPanel);
        inicializarOpcionesOperacionMatematica();

        getContentPane().add(Operaciones, BorderLayout.CENTER);

        setVisible(true);

    }

    public OperacionMatematica getOperacionMatematicaCreada() {
        return operacionMatematicaCreada;

    }

}
