package Motomaticas.RecursosCustomizados.JPanelInspector;

import java.awt.Dialog;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.ScrollPaneConstants;

import Motomaticas.ObjetosLogicos.motorMatematico.ObjetoMatematico;
import Motomaticas.ObjetosLogicos.motorMatematico.operaciones.OperacionMatematica;
import Motomaticas.RecursosCustomizados.BotonAritmetico;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import Motomaticas.ValoresDefault.Constantes;

import java.awt.event.ActionEvent;

public class JPanel_CrearOperacionMatematica extends JPanel  {
    private final JPanel contentPanel = new JPanel();
    private ActionListener actionListenerEscucha;
    private HashMap<String, JPanel> categoriasContenido = new HashMap<String, JPanel>();// Contenidos
    private HashMap<String, JPanel> categoriasSeccionCompleta = new HashMap<String, JPanel>();// panel con contenido y
                                                                                              // titulos y bordes
    private GridBagConstraints gridBagLayourPanelCategorias = new GridBagConstraints();

    private void inicializarOpcionesOperacionMatematica() {

        for (OperacionMatematica operacionMatematica : OperacionMatematica.TotalOperacionesMatematicas) {
            JPanel OperacionesMatematicasJP = new JPanel();
            OperacionesMatematicasJP.setBackground(Constantes.PrincipalColor);
            OperacionesMatematicasJP.setLayout(new BorderLayout());

            BotonAritmetico nuevaOperacion = new BotonAritmetico(operacionMatematica);
            nuevaOperacion.setPreferredSize(new Dimension(nuevaOperacion.getMaximumSize().width, 100));
            nuevaOperacion.setActionCommand("CreadoDesdeInspector");
            nuevaOperacion.addActionListener(actionListenerEscucha);

            OperacionesMatematicasJP.add(nuevaOperacion, BorderLayout.CENTER);
            OperacionesMatematicasJP.add(Box.createVerticalStrut(5), BorderLayout.SOUTH);
            OperacionesMatematicasJP.add(Box.createVerticalStrut(5), BorderLayout.NORTH);
            OperacionesMatematicasJP.add(Box.createHorizontalStrut(5), BorderLayout.EAST);
            OperacionesMatematicasJP.add(Box.createHorizontalStrut(5), BorderLayout.WEST);

            if (!categoriasSeccionCompleta.containsKey(operacionMatematica.getCategoriaMatematica())) {
                // Crea panel de categoria con sus borders
                JPanel nuevaCategoria = new JPanel(new BorderLayout());

                nuevaCategoria.setBackground(Constantes.PrincipalColor);

                // panel de titulo de categoria con sus borders
                JPanel TituloCategoria = new JPanel(new BorderLayout());
                TituloCategoria.setBackground(Constantes.PrincipalColor);
                JLabel JLabelTitulo = new JLabel(operacionMatematica.getCategoriaMatematica());
                JLabelTitulo.setFont(Constantes.botones);
                JLabelTitulo.setForeground(Constantes.TerciarioColor);
                TituloCategoria.add(JLabelTitulo, BorderLayout.CENTER);
                TituloCategoria.add(Box.createVerticalStrut(3), BorderLayout.SOUTH);
                TituloCategoria.add(Box.createVerticalStrut(3), BorderLayout.NORTH);
                TituloCategoria.add(Box.createHorizontalStrut(3), BorderLayout.EAST);
                TituloCategoria.add(Box.createHorizontalStrut(3), BorderLayout.WEST);

                // panel de los elementos el cual sera insertado en el hashmap

                JPanel CategoriaElementos = new JPanel(new GridLayout(0, 3));

                CategoriaElementos.setBackground(Constantes.PrincipalColor);
                categoriasContenido.put(operacionMatematica.getCategoriaMatematica(), CategoriaElementos);

                // añadir todo al panel general
                nuevaCategoria.add(TituloCategoria, BorderLayout.NORTH);
                nuevaCategoria.add(CategoriaElementos, BorderLayout.CENTER);
                nuevaCategoria.add(Box.createVerticalStrut(5), BorderLayout.SOUTH);
                nuevaCategoria.add(Box.createHorizontalStrut(5), BorderLayout.EAST);
                nuevaCategoria.add(Box.createHorizontalStrut(5), BorderLayout.WEST);
                categoriasSeccionCompleta.put(operacionMatematica.getCategoriaMatematica(), nuevaCategoria);
            }

            ((JPanel) categoriasContenido.get(operacionMatematica.getCategoriaMatematica()))
                    .add(OperacionesMatematicasJP);

            contentPanel.removeAll();
            for (Object categoria : categoriasSeccionCompleta.values().toArray()) {
                contentPanel.add((JPanel) categoria, gridBagLayourPanelCategorias);
            }

        }

    }

    public JPanel_CrearOperacionMatematica(ActionListener actionListenerEscucha) {
        gridBagLayourPanelCategorias.gridx = 0;
        gridBagLayourPanelCategorias.gridy = GridBagConstraints.RELATIVE;
        gridBagLayourPanelCategorias.fill = GridBagConstraints.HORIZONTAL;
        gridBagLayourPanelCategorias.weightx = 1;
        gridBagLayourPanelCategorias.weighty = 1;
        gridBagLayourPanelCategorias.anchor = GridBagConstraints.FIRST_LINE_START;

        this.actionListenerEscucha = actionListenerEscucha;


        contentPanel.setBackground(Constantes.PrincipalColor);
        contentPanel.setLayout(new GridBagLayout());

        JScrollPane Operaciones = new JScrollPane();
        Operaciones.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Operaciones.setBackground(Constantes.PrincipalColor);
        Operaciones.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        Operaciones.setViewportView(contentPanel);
        inicializarOpcionesOperacionMatematica();
        this.setLayout(new BorderLayout());
        this.add(Operaciones, BorderLayout.CENTER);

    }



}
