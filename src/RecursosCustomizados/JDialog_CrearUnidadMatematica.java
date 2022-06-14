package RecursosCustomizados;

import java.awt.Dialog;

import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import javax.swing.ScrollPaneConstants;

import ObjetosLogicos.motorMatematico.variables.UnidadMatematica;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import ValoresDefault.Constantes;

import java.awt.event.ActionEvent;

public class JDialog_CrearUnidadMatematica extends JDialog {
    private final JPanel contentPanel = new JPanel();
    private JFrame Padre;
    private UnidadMatematica unidadMatematicaCreada;

    private void inicializarOpcionesUnidadesMatematicas() {

        for (UnidadMatematica unidadMatematicaInyectada : UnidadMatematica.TotalUnidadesMatematicas) {

            JPanel newUnidadMatematicaButton = new JPanel();
            newUnidadMatematicaButton.setBackground(Constantes.PrincipalColor);
            newUnidadMatematicaButton.setLayout(new BorderLayout());

            BotonAritmetico NuevaUnidad = new BotonAritmetico(unidadMatematicaInyectada);
            NuevaUnidad.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    unidadMatematicaCreada = unidadMatematicaInyectada.crearUnidad(Padre);
                    dispose();
                }
            });

            newUnidadMatematicaButton.add(NuevaUnidad, BorderLayout.CENTER);
            newUnidadMatematicaButton.add(Box.createVerticalStrut(5), BorderLayout.SOUTH);
            newUnidadMatematicaButton.add(Box.createVerticalStrut(5), BorderLayout.NORTH);
            newUnidadMatematicaButton.add(Box.createHorizontalStrut(5), BorderLayout.EAST);
            newUnidadMatematicaButton.add(Box.createHorizontalStrut(5), BorderLayout.WEST);

            contentPanel.add(newUnidadMatematicaButton);

        }

    }

    public JDialog_CrearUnidadMatematica(JFrame Padre, String Mensaje) {
        super(Padre, "Confirmar", Dialog.ModalityType.TOOLKIT_MODAL);
        this.Padre = Padre;
        setBounds(100, 100, Constantes.PantallaOrdenadorX / 3, Constantes.PantallaOrdenadorY / 3);
        setTitle("Crear Unidad Matematica");
        setResizable(false);

        contentPanel.setBackground(Constantes.PrincipalColor);
        contentPanel.setLayout(new GridLayout(0, 2));

        JScrollPane Operaciones = new JScrollPane();
        Operaciones.setBackground(Constantes.PrincipalColor);
        Operaciones.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        Operaciones.setViewportView(contentPanel);
        inicializarOpcionesUnidadesMatematicas();

        getContentPane().add(Operaciones, BorderLayout.CENTER);

        setVisible(true);

    }

    public UnidadMatematica getUnidadMatematicaCreada() {
        return unidadMatematicaCreada;

    }

}
