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

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import Motomaticas.ObjetosLogicos.motorMatematico.ObjetoMatematico;
import Motomaticas.ObjetosLogicos.motorMatematico.funciones.FuncionMatematica;
import Motomaticas.ObjetosLogicos.motorMatematico.variables.UnidadMatematica;
import Motomaticas.RecursosCustomizados.BotonAritmetico;
import Motomaticas.ValoresDefault.Constantes;

import java.awt.event.ActionEvent;

public class JPanel_CrearFuncionMatematica extends JPanel {
    private final JPanel contentPanel = new JPanel();
    private ActionListener actionListenerEscucha;
    private HashMap<String, JPanel> categoriasContenido = new HashMap<String, JPanel>();// Contenidos
    private HashMap<String, JPanel> categoriasSeccionCompleta = new HashMap<String, JPanel>();// panel con contenido y
                                                                                              // titulos y bordes
    private GridBagConstraints gridBagLayourPanelCategorias = new GridBagConstraints();

    private void inicializarOpcionesUnidadesMatematicas() {

        for (FuncionMatematica funcionMatematica : FuncionMatematica.TotalFuncionesMatematicas) {
            JPanel FuncionMatematicaJP = new JPanel();
            FuncionMatematicaJP.setBackground(Constantes.PrincipalColor);
            FuncionMatematicaJP.setLayout(new BorderLayout());

            BotonAritmetico nuevaFuncion = new BotonAritmetico(funcionMatematica);
            nuevaFuncion.setPreferredSize(new Dimension(nuevaFuncion.getMaximumSize().width, 100));
            nuevaFuncion.setActionCommand("CreadoDesdeInspector");
            nuevaFuncion.addActionListener(actionListenerEscucha);

            FuncionMatematicaJP.add(nuevaFuncion, BorderLayout.CENTER);
            FuncionMatematicaJP.add(Box.createVerticalStrut(5), BorderLayout.SOUTH);
            FuncionMatematicaJP.add(Box.createVerticalStrut(5), BorderLayout.NORTH);
            FuncionMatematicaJP.add(Box.createHorizontalStrut(5), BorderLayout.EAST);
            FuncionMatematicaJP.add(Box.createHorizontalStrut(5), BorderLayout.WEST);

            if (!categoriasSeccionCompleta.containsKey(funcionMatematica.getCategoriaMatematica())) {
                // Crea panel de categoria con sus borders
                JPanel nuevaCategoria = new JPanel(new BorderLayout());

                nuevaCategoria.setBackground(Constantes.PrincipalColor);

                // panel de titulo de categoria con sus borders
                JPanel TituloCategoria = new JPanel(new BorderLayout());
                TituloCategoria.setBackground(Constantes.PrincipalColor);
                JLabel JLabelTitulo = new JLabel(funcionMatematica.getCategoriaMatematica());
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
                categoriasContenido.put(funcionMatematica.getCategoriaMatematica(), CategoriaElementos);

                // añadir todo al panel general
                nuevaCategoria.add(TituloCategoria, BorderLayout.NORTH);
                nuevaCategoria.add(CategoriaElementos, BorderLayout.CENTER);
                nuevaCategoria.add(Box.createVerticalStrut(5), BorderLayout.SOUTH);
                nuevaCategoria.add(Box.createHorizontalStrut(5), BorderLayout.EAST);
                nuevaCategoria.add(Box.createHorizontalStrut(5), BorderLayout.WEST);
                categoriasSeccionCompleta.put(funcionMatematica.getCategoriaMatematica(), nuevaCategoria);
            }

            ((JPanel) categoriasContenido.get(funcionMatematica.getCategoriaMatematica()))
                    .add(FuncionMatematicaJP);

            contentPanel.removeAll();
            for (Object categoria : categoriasSeccionCompleta.values().toArray()) {
                contentPanel.add((JPanel) categoria, gridBagLayourPanelCategorias);
            }

        }

    }

    public JPanel_CrearFuncionMatematica(ActionListener actionListenerEscucha) {
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
