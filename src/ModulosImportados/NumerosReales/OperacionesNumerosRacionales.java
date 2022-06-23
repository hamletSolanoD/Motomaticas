package ModulosImportados.NumerosReales;

import Motomaticas.ObjetosLogicos.motorMatematico.ObjetoMatematico;
import Motomaticas.ObjetosLogicos.motorMatematico.operaciones.OperacionMatematica;
import Motomaticas.ObjetosLogicos.motorMatematico.variables.UnidadMatematica;
import Motomaticas.ValoresDefault.Constantes.Apunte;
import Motomaticas.ValoresDefault.Constantes.Parrafo;;


public interface OperacionesNumerosRacionales {
    public class potencias extends OperacionMatematica{

        public potencias() {
            super(false, "^", "Potencia", "Eleva un numero N a la M potencia", 3,false);
            //TODO Auto-generated constructor stub
        }
        @Override
        public String getCategoriaMatematica(){
                return "prueba de categoria";
        } 

        @Override
        protected String[] definirTipoDeOperandoscorrectos() {
            String[] operandos = {
                (new UnidadNumerosRacionales()).getNombreObjetoMatematico(),
                (new UnidadNumerosRacionales()).getNombreObjetoMatematico()
            };
            return operandos;
        }

        @Override
        public UnidadMatematica calcularOperacion(UnidadMatematica... args) {
            UnidadNumerosRacionales Numero = (UnidadNumerosRacionales)args[0];
            UnidadNumerosRacionales Potencia = (UnidadNumerosRacionales)args[1];
            Apunte apunte = new Apunte("Potencia de "+Numero.getValor()+" a la "+ Potencia.getValor());
            apunte.AgregarParrafo(new Parrafo("La potencia es la multiplicacion del numero N, M veces por si mismo"));
            apunte.AgregarParrafo(new Parrafo("Ejemplo: 4^6 = 4*4*4*4*4*4"));
            MostrarInformacion(apunte);
            return new UnidadNumerosRacionales(Math.pow(Numero.getValor(), Potencia.getValor()));
        }

        
    }
    
    public class raices extends OperacionMatematica{

        public raices() {
            super(true, "√", "Raices", "Retorna el numero que cabe N veces si mismo dentro del numero dado", 3,false);
            //TODO Auto-generated constructor stub
        }

        @Override
        protected String[] definirTipoDeOperandoscorrectos() {
            String[] operandos = {
                (new UnidadNumerosRacionales()).getNombreObjetoMatematico(),
            };
            return operandos;
        }

        @Override
        public UnidadMatematica calcularOperacion(UnidadMatematica... args) {
            UnidadNumerosRacionales Numero = (UnidadNumerosRacionales)args[0];
        
            return new UnidadNumerosRacionales(Math.sqrt(Numero.getValor()));
        }

  

        
    }
}
