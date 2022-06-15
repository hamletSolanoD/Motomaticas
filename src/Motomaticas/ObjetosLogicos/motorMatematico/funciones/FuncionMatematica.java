package Motomaticas.ObjetosLogicos.motorMatematico.funciones;

import java.util.ArrayList;

import javax.swing.JFrame;

import Motomaticas.ObjetosLogicos.motorMatematico.ObjetoMatematico;
import Motomaticas.ObjetosLogicos.motorMatematico.variables.UnidadMatematica;
import Motomaticas.ValoresDefault.Constantes;

public abstract class FuncionMatematica extends ObjetoMatematico {

    protected String descripcionDeOperacion;
    public static ArrayList<FuncionMatematica> TotalFuncionesMatematicas;

    public FuncionMatematica(String nombreObjetoMatematico, String SimboloIdentificador,
            String descripcionDeOperacion) {
        super(nombreObjetoMatematico, SimboloIdentificador, Constantes.TipoObjetoMatematico.Funcion);
        this.descripcionDeOperacion = descripcionDeOperacion;
        inyectarPorCreacion();
    }

    private void inyectarPorCreacion() {
        if (TotalFuncionesMatematicas == null)
            TotalFuncionesMatematicas = new ArrayList<>();
        boolean repetido = false;
        for (FuncionMatematica funcionMatematica : TotalFuncionesMatematicas) {
            if (funcionMatematica.getNombreObjetoMatematico() == this.getNombreObjetoMatematico()) {
                repetido = true;
                break;
            }
        }
        if (!repetido)
            TotalFuncionesMatematicas.add(this);
    }

    @Override
    public String toStringReducido() {
        return getNombreObjetoMatematico();
    }

    @Override
    public String toString() {
        return toStringReducido() + ":" + descripcionDeOperacion;
    }



    public abstract UnidadMatematica calcularOperacion(UnidadMatematica... args);

    public abstract UnidadMatematica llamarFuncionMatematica(JFrame padre);// metodo para que se sobrescriba y cree una
                                                                           // jdialog de unidad aritmetica
    public static void inyectarFuncionMatematica(FuncionMatematica OperacionMatematica) {
        if (TotalFuncionesMatematicas == null)
            TotalFuncionesMatematicas = new ArrayList<>();
        boolean repetido = false;
        for (FuncionMatematica opMatematica : TotalFuncionesMatematicas) {
            if (opMatematica.getNombreObjetoMatematico() == OperacionMatematica.getNombreObjetoMatematico()) {
                repetido = true;
                break;
            }
        }
        if (!repetido)
            TotalFuncionesMatematicas.add(OperacionMatematica);

    }
}
