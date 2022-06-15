package ModulosImportados.NumerosReales;

import Motomaticas.ObjetosLogicos.motorMatematico.ObjetoMatematico;
import Motomaticas.ObjetosLogicos.motorMatematico.operaciones.OperacionMatematica;
import Motomaticas.ObjetosLogicos.motorMatematico.variables.UnidadMatematica;
import Motomaticas.ValoresDefault.Constantes.Apunte;
import Motomaticas.ValoresDefault.Constantes.Parrafo;;


public interface OperacionesNumerosReales {
    public class potencias extends OperacionMatematica{

        public potencias() {
            super(false, "^", "Potencia", "Eleva un numero N a la M potencia", 3);
            //TODO Auto-generated constructor stub
        }
        @Override
        public String getCategoriaMatematica(){
                return "prueba de categoria";
        } 

        @Override
        protected String[] definirTipoDeOperandoscorrectos() {
            String[] operandos = {
                (new UnidadNumerosReales()).getNombreObjetoMatematico(),
                (new UnidadNumerosReales()).getNombreObjetoMatematico()
            };
            return operandos;
        }

        @Override
        public UnidadMatematica calcularOperacion(UnidadMatematica... args) {
            UnidadNumerosReales Numero = (UnidadNumerosReales)args[0];
            UnidadNumerosReales Potencia = (UnidadNumerosReales)args[1];
            Apunte apunte = new Apunte("Potencia de "+Numero.getValor()+" a la "+ Potencia.getValor());
            apunte.AgregarParrafo(new Parrafo("La potencia es la multiplicacion del numero N, M veces por si mismo"));
            apunte.AgregarParrafo(new Parrafo("Ejemplo: 4^6 = 4*4*4*4*4*4"));
            MostrarInformacion(apunte);
            return new UnidadNumerosReales(Math.pow(Numero.getValor(), Potencia.getValor()));
        }

        
    }
    
    public class raices extends OperacionMatematica{

        public raices() {
            super(true, "√", "Raices", "Retorna el numero que cabe N veces si mismo dentro del numero dado", 3);
            //TODO Auto-generated constructor stub
        }

        @Override
        protected String[] definirTipoDeOperandoscorrectos() {
            String[] operandos = {
                (new UnidadNumerosReales()).getNombreObjetoMatematico(),
            };
            return operandos;
        }

        @Override
        public UnidadMatematica calcularOperacion(UnidadMatematica... args) {
            UnidadNumerosReales Numero = (UnidadNumerosReales)args[0];
        
            return new UnidadNumerosReales(Math.sqrt(Numero.getValor()));
        }

  

        
    }
}
