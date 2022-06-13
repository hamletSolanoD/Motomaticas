package ObjetosLogicos.motorMatematico.variables;

import java.util.ArrayList;

import ObjetosLogicos.motorMatematico.ObjetoAlgebraico;
import ObjetosLogicos.motorMatematico.operaciones.OperacionMatematica;
import ValoresDefault.Constantes.Apunte;

public class UnidadNumerosReales extends UnidadMatematica {
    private double Valor;
    

    static class Multiplicacion extends OperacionMatematica {
     
        static String[] operandos =    {(new UnidadNumerosReales()).getNombre(),
            (new UnidadNumerosReales()).getNombre()};

        public Multiplicacion() {         
            super(false,operandos,"Multiplicar", " Operacion para multiplicar 2 numeros reales y retornar su producto", 2);
            // TODO Auto-generated constructor stub
        }

        @Override
        public ObjetoAlgebraico calcularOperacion(ObjetoAlgebraico... args) {
            UnidadNumerosReales A = (UnidadNumerosReales) args[0];
            UnidadNumerosReales B = (UnidadNumerosReales) args[1];

            Apunte nuevoApunte_MultiplicarAritmetico =  new Apunte(
                    "Multiplicar " + A.getValor() + " x " + B.getValor() + " = " + (A.getValor() * B.getValor()));
            MostrarInformacion(nuevoApunte_MultiplicarAritmetico);

            return new UnidadNumerosReales(A.getValor() * B.getValor());
        }

    }

    static class Division extends OperacionMatematica {
        static String[] operandos =    {(new UnidadNumerosReales()).getNombre(),
            (new UnidadNumerosReales()).getNombre()};
        public Division() {
            super(false,operandos,"Dividir", " Operacion para dividir 2 numeros reales y retornar su producto", 2);
            // TODO Auto-generated constructor stub
        }


        @Override
        public ObjetoAlgebraico calcularOperacion(ObjetoAlgebraico... args) {
            UnidadNumerosReales A = (UnidadNumerosReales) args[0];
            UnidadNumerosReales B = (UnidadNumerosReales) args[1];

            Apunte nuevoApunte_MultiplicarAritmetico = new Apunte(
                    "Dividir " + A.getValor() + " / " + B.getValor() + " = " + (A.getValor() * B.getValor()));
            MostrarInformacion(nuevoApunte_MultiplicarAritmetico);

            return new UnidadNumerosReales(A.getValor() / B.getValor());
        }

    }

    static class Suma extends OperacionMatematica {
        static String[] operandos =    {(new UnidadNumerosReales()).getNombre(),
            (new UnidadNumerosReales()).getNombre()};
        public Suma() {
            super(false,operandos,"Suma", " Operacion para sumar 2 numeros reales y retornar su producto", 2);
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
        static String[] operandos =    {(new UnidadNumerosReales()).getNombre(),
            (new UnidadNumerosReales()).getNombre()};
        public Resta() {
            super(false,operandos,"Resta", " Operacion para restar 2 numeros reales y retornar su producto", 2);
            // TODO Auto-generated constructor stub
        }

        @Override
        public ObjetoAlgebraico calcularOperacion(ObjetoAlgebraico... args) {
            UnidadNumerosReales A = (UnidadNumerosReales) args[0];
            UnidadNumerosReales B = (UnidadNumerosReales) args[1];

            Apunte nuevoApunte_SumaAritmetica = new Apunte(
                    "Resta " + A.getValor() + " - " + B.getValor() + " = " + (A.getValor() + B.getValor()));

            MostrarInformacion(nuevoApunte_SumaAritmetica);
            return new UnidadNumerosReales(A.getValor() - B.getValor());
        }

    }

    OperacionMatematica suma = new Suma();

    public UnidadNumerosReales() {
        super(new Suma(),  new Resta(), new Multiplicacion(), new Division());
    }

    public UnidadNumerosReales(double Valor) {
        super(new Suma(),  new Resta(), new Multiplicacion(), new Division());
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

}
