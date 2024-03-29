package Motomaticas.RecursosCustomizados.JPanelInspector;

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

import Motomaticas.ObjetosLogicos.motorMatematico.ObjetoMatematico;
import Motomaticas.ObjetosLogicos.motorMatematico.variables.UnidadMatematica;
import Motomaticas.RecursosCustomizados.BotonAritmetico;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import Motomaticas.ValoresDefault.Constantes;

import java.awt.event.ActionEvent;

public class JPanel_CrearUnidadMatematica extends JPanel  {
    private final JPanel contentPanel = new JPanel();
    private ActionListener actionListenerEscucha;
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
            nuevaUnidad.setActionCommand("CreadoDesdeInspector");
            nuevaUnidad.addActionListener(actionListenerEscucha);

          

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

    public JPanel_CrearUnidadMatematica( ActionListener actionListenerEscucha) {
        this.actionListenerEscucha = actionListenerEscucha;
        gridBagLayourPanelCategorias.gridx = 0;
        gridBagLayourPanelCategorias.gridy = GridBagConstraints.RELATIVE;
        gridBagLayourPanelCategorias.fill = GridBagConstraints.HORIZONTAL;
        gridBagLayourPanelCategorias.weightx = 1;
        gridBagLayourPanelCategorias.weighty = 1;
        gridBagLayourPanelCategorias.anchor = GridBagConstraints.FIRST_LINE_START;

     

        contentPanel.setBackground(Constantes.PrincipalColor);
        contentPanel.setLayout(new GridBagLayout());

        JScrollPane Operaciones = new JScrollPane();
        Operaciones.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Operaciones.setBackground(Constantes.PrincipalColor);
        Operaciones.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        Operaciones.setViewportView(contentPanel);
        inicializarOpcionesUnidadesMatematicas();
        this.setLayout(new BorderLayout());
        this.add(Operaciones, BorderLayout.CENTER);


    }


}
