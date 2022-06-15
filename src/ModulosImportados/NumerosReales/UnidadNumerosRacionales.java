package ModulosImportados.NumerosReales;

import javax.swing.JFrame;

import Motomaticas.ObjetosLogicos.motorMatematico.operaciones.OperacionMatematica;
import Motomaticas.ObjetosLogicos.motorMatematico.variables.UnidadMatematica;
import Motomaticas.ValoresDefault.Constantes.Apunte;

public class UnidadNumerosRacionales extends UnidadMatematica implements OperacionesNumerosRacionales {
    private double Valor;

    static class Multiplicacion extends OperacionMatematica {

        public Multiplicacion() {
            super(false, "X", "Multiplicar Numeros Reales",
                    "Operacion para multiplicar 2 numeros reales y retornar su producto", 2);
            // TODO Auto-generated constructor stub
        }

        @Override
        public UnidadMatematica calcularOperacion(UnidadMatematica... args) {
            UnidadNumerosRacionales A = (UnidadNumerosRacionales) args[0];
            UnidadNumerosRacionales B = (UnidadNumerosRacionales) args[1];

            Apunte nuevoApunte_MultiplicarAritmetico = new Apunte(
                    "Multiplicar " + A.getValor() + " x " + B.getValor() + " = " + (A.getValor() * B.getValor()));
            MostrarInformacion(nuevoApunte_MultiplicarAritmetico);

            return new UnidadNumerosRacionales(A.getValor() * B.getValor());
        }

        @Override
        protected String[] definirTipoDeOperandoscorrectos() {
            String[] operandos = { (new UnidadNumerosRacionales()).getNombreObjetoMatematico(),
                    (new UnidadNumerosRacionales()).getNombreObjetoMatematico() };
            return operandos;
        }

    }

    static class Division extends OperacionMatematica {
        @Override
        protected String[] definirTipoDeOperandoscorrectos() {
            String[] operandos = { (new UnidadNumerosRacionales()).getNombreObjetoMatematico(),
                    (new UnidadNumerosRacionales()).getNombreObjetoMatematico() };
            return operandos;
        }

        public Division() {
            super(false, "/", "Dividir Numeros Reales",
                    "Operacion para dividir 2 numeros reales y retornar su producto", 2);
            // TODO Auto-generated constructor stub
        }

        @Override
        public UnidadMatematica calcularOperacion(UnidadMatematica... args) {
            UnidadNumerosRacionales A = (UnidadNumerosRacionales) args[0];
            UnidadNumerosRacionales B = (UnidadNumerosRacionales) args[1];

            Apunte nuevoApunte_MultiplicarAritmetico = new Apunte(
                    "Dividir " + A.getValor() + " / " + B.getValor() + " = " + (A.getValor() / B.getValor()));
            MostrarInformacion(nuevoApunte_MultiplicarAritmetico);

            return new UnidadNumerosRacionales(A.getValor() / B.getValor());
        }

    }

    static class Suma extends OperacionMatematica {
        @Override
        protected String[] definirTipoDeOperandoscorrectos() {
            String[] operandos = { (new UnidadNumerosRacionales()).getNombreObjetoMatematico(),
                    (new UnidadNumerosRacionales()).getNombreObjetoMatematico() };
            return operandos;
        }

        public Suma() {
            super(false, "+", "Suma Numeros Reales", "Operacion para sumar 2 numeros reales y retornar su producto", 1);
        }

        @Override
        public UnidadMatematica calcularOperacion(UnidadMatematica... args) {
            UnidadNumerosRacionales A = (UnidadNumerosRacionales) args[0];
            UnidadNumerosRacionales B = (UnidadNumerosRacionales) args[1];

            Apunte nuevoApunte_SumaAritmetica = new Apunte(
                    "Suma " + A.getValor() + " + " + B.getValor() + " = " + (A.getValor() + B.getValor()));

            MostrarInformacion(nuevoApunte_SumaAritmetica);
            return new UnidadNumerosRacionales(A.getValor() + B.getValor());
        }

    }

    static class Resta extends OperacionMatematica {
        @Override
        protected String[] definirTipoDeOperandoscorrectos() {
            String[] operandos = { (new UnidadNumerosRacionales()).getNombreObjetoMatematico(),
                    (new UnidadNumerosRacionales()).getNombreObjetoMatematico() };
            return operandos;
        }

        public Resta() {
            super(false, "-", "Resta Numeros Reales", "Operacion para restar 2 numeros reales y retornar su producto",
                    1);
            // TODO Auto-generated constructor stub
        }

        @Override
        public UnidadMatematica calcularOperacion(UnidadMatematica... args) {
            UnidadNumerosRacionales A = (UnidadNumerosRacionales) args[0];
            UnidadNumerosRacionales B = (UnidadNumerosRacionales) args[1];

            Apunte nuevoApunte_SumaAritmetica = new Apunte(
                    "Resta " + A.getValor() + " - " + B.getValor() + " = " + (A.getValor() - B.getValor()));

            MostrarInformacion(nuevoApunte_SumaAritmetica);
            return new UnidadNumerosRacionales(A.getValor() - B.getValor());
        }

    }

    public UnidadNumerosRacionales() {
        super("Numero Real", "U", new Suma(), new Resta(), new Multiplicacion(), new Division());
        InyectarFunciones();
    }

    public UnidadNumerosRacionales(double Valor) {
        super("Numero Real", "U", new Suma(), new Resta(), new Multiplicacion(), new Division());
        InyectarFunciones();
        this.Valor = Valor;
    }

    private void InyectarFunciones() {
        OperacionMatematica.inyectarOperacionMatematica(new potencias());
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
    public UnidadMatematica crearUnidad(JFrame Padre) {
        crearUnidadNumerosRacionales CrearUnidadJDialog = new crearUnidadNumerosRacionales(Padre);
        return CrearUnidadJDialog.getUnidadNueva();

    }

    @Override
    public String getCategoriaMatematica() {
        return "Numeros";
    }

}
