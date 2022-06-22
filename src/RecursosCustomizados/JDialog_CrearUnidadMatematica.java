package RecursosCustomizados;

import java.awt.Dialog;

import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.Dimension;
import javax.swing.ScrollPaneConstants;

import ObjetosLogicos.motorMatematico.variables.UnidadMatematica;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import ValoresDefault.Constantes;

import java.awt.event.ActionEvent;

public class JDialog_CrearUnidadMatematica extends JDialog {
    private final JPanel contentPanel = new JPanel();
    private JFrame padre;
    private UnidadMatematica unidadMatematicaCreada;
    private HashMap<String, JPanel> categoriasContenido = new HashMap<String, JPanel>();// Contenidos
    private HashMap<String, JPanel> categoriasSeccionCompleta = new HashMap<String, JPanel>();// panel con contenido y
                                                                                              // titulos y bordes
    private GridBagConstraints gridBagLayourPanelCategorias = new GridBagConstraints();

    private void inicializarOpcionesUnidadesMatematicas() {

        for (UnidadMatematica unidadMatematica : UnidadMatematica.TotalUnidadesMatematicas) {
            JPanel UnidadesMatematicasJP = new JPanel();
            UnidadesMatematicasJP.setBackground(Constantes.PrincipalColor);
            UnidadesMatematicasJP.setLayout(new BorderLayout());

            BotonAritmetico nuevaUnidad = new BotonAritmetico(unidadMatematica);
            nuevaUnidad.setPreferredSize(new Dimension(nuevaUnidad.getMaximumSize().width, 100));

            nuevaUnidad.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    unidadMatematicaCreada = unidadMatematica.crearUnidad(padre);
                    dispose();
                }
            });

            UnidadesMatematicasJP.add(nuevaUnidad, BorderLayout.CENTER);
            UnidadesMatematicasJP.add(Box.createVerticalStrut(5), BorderLayout.SOUTH);
            UnidadesMatematicasJP.add(Box.createVerticalStrut(5), BorderLayout.NORTH);
            UnidadesMatematicasJP.add(Box.createHorizontalStrut(5), BorderLayout.EAST);
            UnidadesMatematicasJP.add(Box.createHorizontalStrut(5), BorderLayout.WEST);

            if (!categoriasSeccionCompleta.containsKey(unidadMatematica.getCategoriaMatematica())) {
                // Crea panel de categoria con sus borders
                JPanel nuevaCategoria = new JPanel(new BorderLayout());

                nuevaCategoria.setBackground(Constantes.PrincipalColor);

                // panel de titulo de categoria con sus borders
                JPanel TituloCategoria = new JPanel(new BorderLayout());
                TituloCategoria.setBackground(Constantes.PrincipalColor);
                JLabel JLabelTitulo = new JLabel(unidadMatematica.getCategoriaMatematica());
                JLabelTitulo.setFont(Constantes.botones);
                JLabelTitulo.setForeground(Constantes.TerciarioColor);
                TituloCategoria.add(JLabelTitulo, BorderLayout.CENTER);
                TituloCategoria.add(Box.createVerticalStrut(3), BorderLayout.SOUTH);
                TituloCategoria.add(Box.createVerticalStrut(3), BorderLayout.NORTH);
                TituloCategoria.add(Box.createHorizontalStrut(3), BorderLayout.EAST);
                TituloCategoria.add(Box.createHorizontalStrut(3), BorderLayout.WEST);

                // panel de los elementos el cual sera insertado en el hashmap

                JPanel CategoriaElementos = new JPanel(new GridLayout(0, 1));

                CategoriaElementos.setBackground(Constantes.PrincipalColor);
                categoriasContenido.put(unidadMatematica.getCategoriaMatematica(), CategoriaElementos);

                // añadir todo al panel general
                nuevaCategoria.add(TituloCategoria, BorderLayout.NORTH);
                nuevaCategoria.add(CategoriaElementos, BorderLayout.CENTER);
                nuevaCategoria.add(Box.createVerticalStrut(5), BorderLayout.SOUTH);
                nuevaCategoria.add(Box.createHorizontalStrut(5), BorderLayout.EAST);
                nuevaCategoria.add(Box.createHorizontalStrut(5), BorderLayout.WEST);
                categoriasSeccionCompleta.put(unidadMatematica.getCategoriaMatematica(), nuevaCategoria);
            }

            ((JPanel) categoriasContenido.get(unidadMatematica.getCategoriaMatematica()))
                    .add(UnidadesMatematicasJP);

            contentPanel.removeAll();
            for (Object categoria : categoriasSeccionCompleta.values().toArray()) {
                contentPanel.add((JPanel) categoria, gridBagLayourPanelCategorias);
            }

        }

    }

    public JDialog_CrearUnidadMatematica(JFrame Padre, String Mensaje) {
        super(Padre, "Confirmar", Dialog.ModalityType.TOOLKIT_MODAL);
        this.padre = Padre;
        gridBagLayourPanelCategorias.gridx = 0;
        gridBagLayourPanelCategorias.gridy = GridBagConstraints.RELATIVE;
        gridBagLayourPanelCategorias.fill = GridBagConstraints.HORIZONTAL;
        gridBagLayourPanelCategorias.weightx = 1;
     
        setBounds(100, 100, Constantes.PantallaOrdenadorX / 3, Constantes.PantallaOrdenadorY / 3);
        setTitle("Crear Unidad Matematica");
        setResizable(false);

        contentPanel.setBackground(Constantes.PrincipalColor);
        contentPanel.setLayout(new GridBagLayout());

        JScrollPane Operaciones = new JScrollPane();
        Operaciones.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Operaciones.setBackground(Constantes.PrincipalColor);
        Operaciones.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        Operaciones.setViewportView(contentPanel);
        inicializarOpcionesUnidadesMatematicas();

        getContentPane().add(Operaciones, BorderLayout.CENTER);

        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    public UnidadMatematica getUnidadMatematicaCreada() {
        return unidadMatematicaCreada;

    }

}
