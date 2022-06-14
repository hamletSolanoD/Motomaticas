package ModulosImportados.NumerosReales;

import javax.swing.JFrame;

import ObjetosLogicos.motorMatematico.ObjetoAlgebraico;
import ObjetosLogicos.motorMatematico.operaciones.OperacionMatematica;
import ObjetosLogicos.motorMatematico.variables.UnidadMatematica;
import ValoresDefault.Constantes.Apunte;

public class UnidadNumerosReales extends UnidadMatematica {
    private double Valor;

    static class Multiplicacion extends OperacionMatematica {

        public Multiplicacion() {
            super(false,"X",false, "Multiplicar Numeros Reales",
                    "Operacion para multiplicar 2 numeros reales y retornar su producto", 2);
            // TODO Auto-generated constructor stub
        }

        @Override
        public ObjetoAlgebraico calcularOperacion(ObjetoAlgebraico... args) {
            UnidadNumerosReales A = (UnidadNumerosReales) args[0];
            UnidadNumerosReales B = (UnidadNumerosReales) args[1];

            Apunte nuevoApunte_MultiplicarAritmetico = new Apunte(
                    "Multiplicar " + A.getValor() + " x " + B.getValor() + " = " + (A.getValor() * B.getValor()));
            MostrarInformacion(nuevoApunte_MultiplicarAritmetico);

            return new UnidadNumerosReales(A.getValor() * B.getValor());
        }

        @Override
        protected String[] definirTipoDeOperandoscorrectos() {
            String[] operandos = { (new UnidadNumerosReales()).getNombre(),
                (new UnidadNumerosReales()).getNombre() };
            return operandos;
        }

      
    }

    static class Division extends OperacionMatematica {
        @Override
        protected String[] definirTipoDeOperandoscorrectos() {
            String[] operandos = { (new UnidadNumerosReales()).getNombre(),
                (new UnidadNumerosReales()).getNombre() };
            return operandos;
        }
        public Division() {
            super(false,"/",false, "Dividir Numeros Reales", "Operacion para dividir 2 numeros reales y retornar su producto", 2);
            // TODO Auto-generated constructor stub
        }

        @Override
        public ObjetoAlgebraico calcularOperacion(ObjetoAlgebraico... args) {
            UnidadNumerosReales A = (UnidadNumerosReales) args[0];
            UnidadNumerosReales B = (UnidadNumerosReales) args[1];

            Apunte nuevoApunte_MultiplicarAritmetico = new Apunte(
                    "Dividir " + A.getValor() + " / " + B.getValor() + " = " + (A.getValor() / B.getValor()));
            MostrarInformacion(nuevoApunte_MultiplicarAritmetico);

            return new UnidadNumerosReales(A.getValor() / B.getValor());
        }

       
    }

    static class Suma extends OperacionMatematica {
        @Override
        protected String[] definirTipoDeOperandoscorrectos() {
            String[] operandos = { (new UnidadNumerosReales()).getNombre(),
                (new UnidadNumerosReales()).getNombre() };
            return operandos;
        }
        public Suma() {
            super(false,"+",false, "Suma Numeros Reales", "Operacion para sumar 2 numeros reales y retornar su producto", 1);
        }

        @Override
        public ObjetoAlgebraico calcularOperacion(ObjetoAlgebraico... args) {
            UnidadNumerosReales A = (UnidadNumerosReales) args[0];
            UnidadNumerosReales B = (UnidadNumerosReales) args[1];

            Apunte nuevoApunte_SumaAritmetica = new Apunte(
                    "Suma " + A.getValor() + " + " + B.getValor() + " = " + (A.getValor() + B.getValor()));

            MostrarInformacion(nuevoApunte_SumaAritmetica);
            return new UnidadNumerosReales(A.getValor() + B.getValor());
        }

      
    }

    static class Resta extends OperacionMatematica {
        @Override
        protected String[] definirTipoDeOperandoscorrectos() {
            String[] operandos = { (new UnidadNumerosReales()).getNombre(),
                (new UnidadNumerosReales()).getNombre() };
            return operandos;
        }
        public Resta() {
            super(false,"-",false, "Resta Numeros Reales", "Operacion para restar 2 numeros reales y retornar su producto", 1);
            // TODO Auto-generated constructor stub
        }

        @Override
        public ObjetoAlgebraico calcularOperacion(ObjetoAlgebraico... args) {
            UnidadNumerosReales A = (UnidadNumerosReales) args[0];
            UnidadNumerosReales B = (UnidadNumerosReales) args[1];

            Apunte nuevoApunte_SumaAritmetica = new Apunte(
                    "Resta " + A.getValor() + " - " + B.getValor() + " = " + (A.getValor() - B.getValor()));

            MostrarInformacion(nuevoApunte_SumaAritmetica);
            return new UnidadNumerosReales(A.getValor() - B.getValor());
        }

      

    }


    public UnidadNumerosReales() {
        super("U",new Suma(), new Resta(), new Multiplicacion(), new Division());
    }

    public UnidadNumerosReales(double Valor) {
        super("U",new Suma(), new Resta(), new Multiplicacion(), new Division());
        this.Valor = Valor;
    }

    public void setValor(double valor) {
        Valor = valor;
    }

    public double getValor() {
        return Valor;
    }

    @Override
    public String toString() {
        return "Unidad: " + Double.toString(Valor);
    }

    @Override
    public String toStringReducido() {
        return Double.toString(Valor);
    }

    @Override
    public String getNombre() {
        // TODO Auto-generated method stub
        return "Numero Real";
    }

    @Override
    public UnidadMatematica crearUnidad(JFrame Padre) {
        crearUnidadNumerosReales CrearUnidadJDialog = new crearUnidadNumerosReales(Padre);
        return CrearUnidadJDialog.getUnidadNueva();
        
    }

}
