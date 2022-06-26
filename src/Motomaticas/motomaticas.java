package Motomaticas;

import java.util.ArrayList;

import Motomaticas.ObjetosLogicos.motorMatematico.ObjetoMatematico;
import Motomaticas.ObjetosLogicos.motorMatematico.funciones.FuncionMatematica;
import Motomaticas.ObjetosLogicos.motorMatematico.operaciones.OperacionMatematica;
import Motomaticas.ObjetosLogicos.motorMatematico.operaciones.operacionesMatematicasGenericasInterface;
import Motomaticas.ObjetosLogicos.motorMatematico.variables.UnidadMatematica;
import Motomaticas.VentanasProyecto.Portada;

public class motomaticas {

    private ArrayList<UnidadMatematica> unidadesMatematicasUsadas = new  ArrayList<UnidadMatematica>();
    private ArrayList<OperacionMatematica> operacionesMatematicasUsadas = new  ArrayList<OperacionMatematica>();
    private ArrayList<FuncionMatematica> funcionesMatematicasUsadas = new  ArrayList<FuncionMatematica>();


    public motomaticas() {
        importarOperacionesMatematicas(new operacionesMatematicasGenericasInterface.llaveIzquierda());
        importarOperacionesMatematicas(new operacionesMatematicasGenericasInterface.llaveDerecha());
        new Portada();

    }




    public void importarUnidadesMatematicas(UnidadMatematica ... UnidadesMatematicas){
        for(UnidadMatematica newUnidad : UnidadesMatematicas){
            unidadesMatematicasUsadas.add(newUnidad);
        }
    }
    public void importarFuncionesMatematicas(FuncionMatematica ... FuncionesMatematicas){
        for(FuncionMatematica newFuncion : FuncionesMatematicas){
            funcionesMatematicasUsadas.add(newFuncion);
        }
    }    public void importarOperacionesMatematicas(OperacionMatematica ... OperacionesMatematicas){
        for(OperacionMatematica newOperacion : OperacionesMatematicas){
            operacionesMatematicasUsadas.add(newOperacion);
        }
    }
}
