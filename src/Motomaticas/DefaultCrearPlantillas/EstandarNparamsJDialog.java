package Motomaticas.DefaultCrearPlantillas;

import java.awt.Dialog;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Motomaticas.RecursosCustomizados.MensajeError;
import Motomaticas.ValoresDefault.Constantes;
import Motomaticas.VentanasProyecto.JPanelNotas;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Motomaticas.ValoresDefault.Constantes;
import javax.swing.JTextField;

public class EstandarNparamsJDialog extends JDialog implements DocumentListener {
    private ArrayList<JTextField> entradas = new ArrayList<JTextField>();
    private GridBagConstraints constrains = new GridBagConstraints();
    private String[] Respuestas;
    private JFrame padre;
    private boolean Numerico;

    private void rellenarJPanel(JPanel PanelEntradasGeneral, String[] varInputs) {

        for (String entrada : varInputs) {
            JPanel nuevaEntrada = new JPanel(new BorderLayout());
            nuevaEntrada.setBackground(Constantes.PrincipalColor);

            JPanel TituloCategoria = new JPanel(new BorderLayout());
            TituloCategoria.setBackground(Constantes.PrincipalColor);
            JLabel JLabelTitulo = new JLabel(entrada);
            JLabelTitulo.setFont(Constantes.botones);
            JLabelTitulo.setForeground(Constantes.TerciarioColor);
            TituloCategoria.add(JLabelTitulo, BorderLayout.CENTER);
            TituloCategoria.add(Box.createVerticalStrut(10), BorderLayout.NORTH);
            TituloCategoria.add(Box.createHorizontalStrut(30), BorderLayout.EAST);
            TituloCategoria.add(Box.createHorizontalStrut(30), BorderLayout.WEST);
            nuevaEntrada.add(TituloCategoria, BorderLayout.NORTH);

            JTextField nuevaEntradaJTextField = new JTextField();
            nuevaEntradaJTextField.getDocument().addDocumentListener(this);
        
            entradas.add(nuevaEntradaJTextField);

            nuevaEntrada.add(nuevaEntradaJTextField, BorderLayout.CENTER);
            nuevaEntrada.add(Box.createVerticalStrut(10), BorderLayout.SOUTH);
            nuevaEntrada.add(Box.createHorizontalStrut(30), BorderLayout.EAST);
            nuevaEntrada.add(Box.createHorizontalStrut(30), BorderLayout.WEST);

            PanelEntradasGeneral.add(nuevaEntrada, constrains);
        }

    }

    private void CalcularValoresRespuesta() {
        ArrayList<String> retorno = new ArrayList<String>();
        boolean error = false;
        for (JTextField textField : entradas) {

            if (!textField.getText().equals(""))
                retorno.add(textField.getText());
            else {
                error = true;
            }
        }
        this.Respuestas = retorno.toArray(new String[0]);
        if (error) {
            new MensajeError(padre, "Error hay algun campo incompleto");
            this.Respuestas = null;
        }

    }

    public String[] getValoresRespuesta() {
        return Respuestas;
    }

    public Float[] getValoresRespuestaNumerica() {
        if (Numerico) {
try{
            ArrayList<Float> retornoNumerico = new ArrayList<Float>();
            for (String res : getValoresRespuesta()) {
                retornoNumerico.add(Float.parseFloat(new String(res)));
            }
            return retornoNumerico.toArray(new Float[0]);

        }catch(Throwable e){
System.err.println(e.getMessage());
            new MensajeError(padre, "Parametros de entrada incorrectos");
            return null;

        }
        } else {
            System.err.println("pide retorno de valores numericos siendo solicitados todo tipo de entrad");

            new MensajeError(padre, "Parametros de entrada incorrectos");
            return null;
        }

    }

    public EstandarNparamsJDialog(JFrame padre, String Titulo, String[] EntradasStrings, boolean Numerico) {
        super(padre, Titulo, Dialog.ModalityType.TOOLKIT_MODAL);
        constrains.gridx = 0;
        constrains.gridy = GridBagConstraints.RELATIVE;
        constrains.fill = GridBagConstraints.HORIZONTAL;
        constrains.weightx = 1;

        setBounds(100, 100, Constantes.PantallaOrdenadorX / 4, Constantes.PantallaOrdenadorY / 3);
        getContentPane().setBackground(Constantes.PrincipalColor);
        this.padre = padre;
        this.Numerico = Numerico;

        JPanel JPanelEntradas = new JPanel(new GridBagLayout());
        JPanelEntradas.setBackground(Constantes.PrincipalColor);

        rellenarJPanel(JPanelEntradas, EntradasStrings);

        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBackground(Constantes.PrincipalColor);
            buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);

            {
                JButton okButton = Constantes.BotonRedondeado("OK", new Color(0, 0, 0, 0), Constantes.DetallesColor);
                buttonPane.add(okButton);
                okButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // TODO Auto-generated method stub
                        CalcularValoresRespuesta();
                        if (Respuestas[0] != null)
                            dispose();
                    }
                });
                getRootPane().setDefaultButton(okButton);

                JButton Cancel = Constantes.BotonRedondeado("Cancel", new Color(0, 0, 0, 0), Constantes.TerciarioColor);
                Cancel.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // TODO Auto-generated method stub
                        dispose();
                    }
                });
                buttonPane.add(Cancel);
            }
        }

        JScrollPane Operaciones = new JScrollPane();
        Operaciones.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Operaciones.setBackground(Constantes.PrincipalColor);
        Operaciones.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        Operaciones.setViewportView(JPanelEntradas);

        getContentPane().add(Operaciones, BorderLayout.CENTER);

        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    
    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub
       Constantes.ComprobarEntradas(e, padre);

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }

}
