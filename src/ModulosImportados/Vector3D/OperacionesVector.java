package ModulosImportados.Vector3D;

import ModulosImportados.NumerosReales.UnidadNumerosReales;
import ObjetosLogicos.motorMatematico.ObjetoAlgebraico;
import ObjetosLogicos.motorMatematico.operaciones.OperacionMatematica;
import ValoresDefault.Constantes.*;

public interface OperacionesVector {
    


    public static class operacionVecUnitario extends OperacionMatematica {
        static String[] operandos = { (new Vector3D("")).getNombre(), (new Vector3D("")).getNombre() };

        public operacionVecUnitario() {
            super(true,"Â",false, operandos, "Vector Unitario", " Operacion para calcular el vector unitario de un vector.", 4);
            // TODO Auto-generated constructor stub
        }

        /*
         * @Vector3D
         */
        @Override
        public ObjetoAlgebraico calcularOperacion(ObjetoAlgebraico... args) {
            Vector3D Vector = (Vector3D) args[0];

            Vector3D Resultado = new Vector3D("Vector unitario de " + Vector.getNombre(), Vector.getThetaX(),
                    Vector.getThetaY(), Vector.getThetaZ(), 1);

            Apunte NuevoApunte_VectorUnitario = new Apunte("Vector Unitario de \"" + Vector.getNombre() + "\"");

            NuevoApunte_VectorUnitario.AgregarParrafo(new Parrafo(
                    "\nDefinicion: El vector unitario representa un nuevo vector unicamente con la direccion del original y dejando su magnitud en 1"));

            NuevoApunte_VectorUnitario.AgregarParrafo(new Parrafo("\nFormula:  Â = Vector / Magnitud del Vector"));

            NuevoApunte_VectorUnitario.AgregarParrafo(new Parrafo("\nProcedimiento:"));
            NuevoApunte_VectorUnitario
                    .AgregarParrafo(new Parrafo("1.-  Â = " + Vector.toStringReducido() + "/" + Vector.getMagnitud()));
            NuevoApunte_VectorUnitario.AgregarParrafo(new Parrafo("2.-  Componente del vector en X: "
                    + Vector.getMagnitudX() + "/" + Vector.getMagnitud() + " = " + Resultado.getMagnitudX()));
            NuevoApunte_VectorUnitario.AgregarParrafo(new Parrafo("3.-  Componente del vector en Y: "
                    + Vector.getMagnitudY() + "/" + Vector.getMagnitud() + " = " + Resultado.getMagnitudY()));
            NuevoApunte_VectorUnitario.AgregarParrafo(new Parrafo("4.-  Componente del vector en Z: "
                    + Vector.getMagnitudZ() + "/" + Vector.getMagnitud() + " = " + Resultado.getMagnitudZ()));

            NuevoApunte_VectorUnitario.AgregarParrafo(
                    new Parrafo("\nVector Original: " + Vector.getMagnitud() + Resultado.toStringReducido()));
            NuevoApunte_VectorUnitario.AgregarParrafo(new Parrafo("Vector Unitario: " + Resultado.toStringReducido()));

            MostrarInformacion(NuevoApunte_VectorUnitario);
            return Resultado;
        }

    }

    public static class operacionVecMagnitud extends OperacionMatematica {
        static String[] operandos = { (new Vector3D("")).getNombre(), (new Vector3D("")).getNombre() };

        public operacionVecMagnitud() {
            super(true,"|A|",false, operandos, "Vector Magnitud", "Operacion para calcular la magnitud de un vector.", 4);
            // TODO Auto-generated constructor stub
        }

        @Override
        public ObjetoAlgebraico calcularOperacion(ObjetoAlgebraico... args) {
            Vector3D Vector = (Vector3D) args[0];

            Apunte NuevoApunte_Magnitud = new Apunte("Magnitud del vector \"" + Vector.getNombre() + "\"");

            NuevoApunte_Magnitud.AgregarParrafo(new Parrafo(
                    "\nDefinicion: La magnitud de un vector respresenta la unidad de amplitud o cantidad que en union con la direccion representa un vector."));

            NuevoApunte_Magnitud
                    .AgregarParrafo(new Parrafo("\nFormula: Magnitud² = magnitudX² + magnitudY² + magnitudZ²"));

            NuevoApunte_Magnitud.AgregarParrafo(new Parrafo("\nProcedimiento:"));
            NuevoApunte_Magnitud.AgregarParrafo(new Parrafo("1.- Magnitud² = " + Vector.getMagnitudX() + "² + "
                    + Vector.getMagnitudY() + "² + " + Vector.getMagnitudZ() + "²"));
            NuevoApunte_Magnitud.AgregarParrafo(new Parrafo("2.- Magnitud² = " + Math.pow(Vector.getMagnitudX(), 2)
                    + " + " + Math.pow(Vector.getMagnitudY(), 2) + " + " + Math.pow(Vector.getMagnitudZ(), 2) + ""));
            NuevoApunte_Magnitud.AgregarParrafo(new Parrafo("3.- Magnitud² = " + (Math.pow(Vector.getMagnitudX(), 2)
                    + Math.pow(Vector.getMagnitudY(), 2) + Math.pow(Vector.getMagnitudZ(), 2))));
            NuevoApunte_Magnitud.AgregarParrafo(new Parrafo("4.- Magnitud  = " + Vector.getMagnitud()));

            MostrarInformacion(NuevoApunte_Magnitud);
            return new UnidadNumerosReales(Vector.getMagnitud());
        }

    }

    public static class operacionVecProductoEscalar extends OperacionMatematica {
        static String[] operandos = { (new Vector3D("")).getNombre(), (new Vector3D("")).getNombre() };

        public operacionVecProductoEscalar() {
            super(false,"•",false, operandos, "Producto Escalar", "Operacion para calcular el producto escalar entre 2 vectores.",
                    4);
            // TODO Auto-generated constructor stub
        }

        @Override
        public ObjetoAlgebraico calcularOperacion(ObjetoAlgebraico... args) {

            Vector3D Vector = (Vector3D) args[0];
            Vector3D Vector2 = (Vector3D) args[1];
            UnidadNumerosReales Resultado = new UnidadNumerosReales(
                    (Vector.getMagnitudX() * Vector2.getMagnitudX()) + (Vector.getMagnitudY() * Vector2.getMagnitudY())
                            + (Vector.getMagnitudZ() * Vector2.getMagnitudZ()));

            Apunte nuevoApunte_ProductoEscalar = new Apunte(
                    "Producto Escalar de \"" + Vector.getNombre() + "\" y \"" + Vector2.getNombre() + "\"");

            nuevoApunte_ProductoEscalar.AgregarParrafo(new Parrafo(
                    "\nDefinicion: El producto escalar representa la proyeccion del vector A sobre el vector B y equivalentemente B sobre A. "));

            nuevoApunte_ProductoEscalar.AgregarParrafo(new Parrafo("\nFormula 1: vecA • vecB = |A||B| cos(θ)"));
            nuevoApunte_ProductoEscalar.AgregarParrafo(new Parrafo("Formula 2: vecA • vecB = AxBx + AyBy + AzBz "));

            nuevoApunte_ProductoEscalar.AgregarParrafo(new Parrafo("\nProcedimiento:"));
            nuevoApunte_ProductoEscalar.AgregarParrafo(
                    new Parrafo("1.- vecA • vecB = (" + Vector.getMagnitudX() + ")" + "(" + Vector2.getMagnitudX() + ")"
                            + " + (" + Vector.getMagnitudY() + ")" + "(" + Vector2.getMagnitudY() + ")" + " + ("
                            + Vector.getMagnitudZ() + ")" + "(" + Vector2.getMagnitudZ() + ")"));
            nuevoApunte_ProductoEscalar
                    .AgregarParrafo(new Parrafo("2.- vecA • vecB = " + Vector.getMagnitudX() * Vector2.getMagnitudX()
                            + "+" + Vector.getMagnitudY() * Vector2.getMagnitudY() + "+"
                            + Vector.getMagnitudZ() * Vector2.getMagnitudZ()));
            nuevoApunte_ProductoEscalar.AgregarParrafo(new Parrafo("3.- vecA • vecB = " + Resultado.getValor()));

            MostrarInformacion(nuevoApunte_ProductoEscalar);
            return Resultado;
        }

    }

    public static class operacionVecProductoEscalar_v2 extends OperacionMatematica {
        static String[] operandos = { "double", "double", "double" };

        public operacionVecProductoEscalar_v2() {
            /*
             * tiene declarado una funcion por tanto obligatoriamente debe llevar un jdialog
             * de creacion
             */
            super(false,"•",true, operandos, "Producto Escalar", "Operacion para calcular el producto escalar entre 2 vectores.",
                    4);
            // TODO Auto-generated constructor stub
        }

        @Override
        public ObjetoAlgebraico calcularOperacion(ObjetoAlgebraico... args) {
            UnidadNumerosReales MagnitudA = (UnidadNumerosReales) args[0];
            UnidadNumerosReales MagnitudB = (UnidadNumerosReales) args[0];
            UnidadNumerosReales Angulo = (UnidadNumerosReales) args[0];
            return new UnidadNumerosReales(MagnitudA.getValor() * MagnitudB.getValor() * Math.cos(Angulo.getValor()));
        }
    }

    public static class operacionVecCruz extends OperacionMatematica {

        static String[] operandos = { (new Vector3D("")).getNombre(), (new Vector3D("")).getNombre() };

        public operacionVecCruz() {
            super(false,"X",false, operandos, "Producto Cruz", "Operacion para calcular el producto cruz entre 2 vectores", 4);
        }

        @Override
        public ObjetoAlgebraico calcularOperacion(ObjetoAlgebraico... args) {
            Vector3D VectorA = (Vector3D) args[0];
            Vector3D VectorB = (Vector3D) args[0];

            double MagnitudX = (VectorA.getMagnitudY() * VectorB.getMagnitudZ())
                    - (VectorA.getMagnitudZ() * VectorB.getMagnitudY());
            double MagnitudY = (-1) * ((VectorA.getMagnitudX() * VectorB.getMagnitudZ())
                    - (VectorA.getMagnitudZ() * VectorB.getMagnitudX()));
            double MagnitudZ = (VectorA.getMagnitudX() * VectorB.getMagnitudY())
                    - (VectorA.getMagnitudY() * VectorB.getMagnitudX());
            Vector3D Resultado = new Vector3D(
                    "Vector producto cruz de " + VectorA.getNombre() + " y " + VectorB.getNombre(), MagnitudX,
                    MagnitudY, MagnitudZ);

            Apunte nuevoApunte_VectorCruz = new Apunte(
                    "Vector Cruz de \"" + VectorA.getNombre() + "\" x \"" + VectorB.getNombre() + "\"");

            nuevoApunte_VectorCruz.AgregarParrafo(new Parrafo(
                    "\nDefinicion: El producto cruz representa la operacion entre 2 vectores multiplicandose en el mismo plano resultando en un nuevo vector producto de ambos."));

            nuevoApunte_VectorCruz.AgregarParrafo(new Parrafo(
                    "\nFormula: vecA x vecB = Vector( ((Ay)(Bz) - (Az)(By)) î , ((Az)(Bx) - (Ax)(Bz)) ĵ , ((Ax)(By) - (Ay)(Bx)) ž)"));

            nuevoApunte_VectorCruz.AgregarParrafo(new Parrafo("\nProcedimiento:"));
            nuevoApunte_VectorCruz.AgregarParrafo(new Parrafo("1.- vecA x vecB = Vector( ((" + VectorA.getMagnitudY()
                    + ")(" + VectorB.getMagnitudZ() + ") - (" + VectorA.getMagnitudZ() + ")(" + VectorB.getMagnitudY()
                    + ")) î , ((" + VectorA.getMagnitudZ() + ")(" + VectorB.getMagnitudX() + ") - ("
                    + VectorA.getMagnitudX() + ")(" + VectorB.getMagnitudZ() + ")) ĵ , ((" + VectorA.getMagnitudX()
                    + ")(" + VectorB.getMagnitudY() + ") - (" + VectorA.getMagnitudY() + ")(" + VectorB.getMagnitudX()
                    + ")) ž)"));
            nuevoApunte_VectorCruz.AgregarParrafo(new Parrafo(
                    "2.- vecA x vecB = Vector( " + MagnitudX + " î , " + MagnitudY + " ĵ , " + MagnitudZ + " ž)"));
            nuevoApunte_VectorCruz.AgregarParrafo(new Parrafo("3.- vecA x vecB = " + Resultado.toStringReducido()));

            MostrarInformacion(nuevoApunte_VectorCruz);
            return Resultado;
        }
    }

}
