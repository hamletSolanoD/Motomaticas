package ObjetosLogicos.motorMatematico.operaciones;

import ObjetosLogicos.motorMatematico.ObjetoAlgebraico;
public interface operacionesMatematicasGenericasInterface{
    public class llaveDerecha extends OperacionMatematica{

        public llaveDerecha() {
            super(true, "{", false,null, "llave derecha", "Establece la jerarquia de operaciones con encapsulamiento de llaves",
                    4);
            //TODO Auto-generated constructor stub
        }

        @Override
        public ObjetoAlgebraico calcularOperacion(ObjetoAlgebraico... args) {
            // TODO Auto-generated method stub
            return null;
        }

    }
    public class llaveIzquierda extends OperacionMatematica{

        public llaveIzquierda() {
            super(true, "}", false,null, "llave izquierda", "Establece la jerarquia de operaciones con encapsulamiento de llaves",
                    4);
            //TODO Auto-generated constructor stub
        }

        @Override
        public ObjetoAlgebraico calcularOperacion(ObjetoAlgebraico... args) {
            // TODO Auto-generated method stub
            return args[0];
        }

    }


}
