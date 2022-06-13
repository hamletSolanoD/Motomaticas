package ModulosImportados.Vector3D;




import ObjetosLogicos.motorMatematico.ObjetoAlgebraico;
import ObjetosLogicos.motorMatematico.operaciones.OperacionMatematica;
import ObjetosLogicos.motorMatematico.variables.UnidadNumerosReales;
import ValoresDefault.Constantes.*;

public interface OperacionesVector {
    
public static class operacionVecUnitario extends OperacionMatematica{

    public operacionVecUnitario() {
        super((new UnidadNumerosReales()).getNombre(),(new UnidadNumerosReales()).getNombre(),"Vector Unitario"," Operacion para calcular el vector unitario de un vector.",4);
        //TODO Auto-generated constructor stub
    }

    /*
     * @Vector3D
     */
    @Override
    public ObjetoAlgebraico calcularOperacion(ObjetoAlgebraico... args) {
        Vector3D Vector  = (Vector3D)args[0];
   
        Vector3D Resultado = new Vector3D("Vector unitario de "+Vector.getNombre(),Vector.getThetaX(),Vector.getThetaY(),Vector.getThetaZ(),1);
		
		Apunte NuevoApunte_VectorUnitario = new Apunte("Vector Unitario de \""+ Vector.getNombre()+"\"");
		
		NuevoApunte_VectorUnitario.AgregarParrafo(new Parrafo("\nDefinicion: El vector unitario representa un nuevo vector unicamente con la direccion del original y dejando su magnitud en 1"));
	
		NuevoApunte_VectorUnitario.AgregarParrafo(new Parrafo("\nFormula:  Â = Vector / Magnitud del Vector"));
	
		NuevoApunte_VectorUnitario.AgregarParrafo(new Parrafo("\nProcedimiento:"));
		NuevoApunte_VectorUnitario.AgregarParrafo(new Parrafo("1.-  Â = "+ Vector.toStringReducido()+"/"+Vector.getMagnitud()));
		NuevoApunte_VectorUnitario.AgregarParrafo(new Parrafo("2.-  Componente del vector en X: "+Vector.getMagnitudX()+"/"+Vector.getMagnitud()+" = "+Resultado.getMagnitudX()));
		NuevoApunte_VectorUnitario.AgregarParrafo(new Parrafo("3.-  Componente del vector en Y: "+Vector.getMagnitudY()+"/"+Vector.getMagnitud()+" = "+Resultado.getMagnitudY()));
		NuevoApunte_VectorUnitario.AgregarParrafo(new Parrafo("4.-  Componente del vector en Z: "+Vector.getMagnitudZ()+"/"+Vector.getMagnitud()+" = "+Resultado.getMagnitudZ()));
		
		NuevoApunte_VectorUnitario.AgregarParrafo(new Parrafo("\nVector Original: "+ Vector.getMagnitud()+Resultado.toStringReducido()));
		NuevoApunte_VectorUnitario.AgregarParrafo(new Parrafo("Vector Unitario: "+ Resultado.toStringReducido()));

		MostrarInformacion(NuevoApunte_VectorUnitario);
		return Resultado;
    }
    
}
public static class operacionVecMagnitud extends OperacionMatematica{

    public operacionVecMagnitud() {
        super((new Vector3D("")).getNombre(),(new Vector3D("")).getNombre(),"Vector Magnitud","Operacion para calcular la magnitud de un vector.",4);
        //TODO Auto-generated constructor stub
    }

    @Override
    public ObjetoAlgebraico calcularOperacion(ObjetoAlgebraico... args) {
        Vector3D Vector  = (Vector3D)args[0];

        Apunte NuevoApunte_Magnitud = new Apunte("Magnitud del vector \""+ Vector.getNombre()+"\"");
		
		NuevoApunte_Magnitud.AgregarParrafo(new Parrafo("\nDefinicion: La magnitud de un vector respresenta la unidad de amplitud o cantidad que en union con la direccion representa un vector."));
	
		NuevoApunte_Magnitud.AgregarParrafo(new Parrafo("\nFormula: Magnitud² = magnitudX² + magnitudY² + magnitudZ²"));
		
		NuevoApunte_Magnitud.AgregarParrafo(new Parrafo("\nProcedimiento:"));
		NuevoApunte_Magnitud.AgregarParrafo(new Parrafo("1.- Magnitud² = "+Vector.getMagnitudX()+"² + "+Vector.getMagnitudY()+"² + "+Vector.getMagnitudZ()+"²"));
		NuevoApunte_Magnitud.AgregarParrafo(new Parrafo("2.- Magnitud² = "+Math.pow(Vector.getMagnitudX(),2)+" + "+Math.pow(Vector.getMagnitudY(),2)+" + "+Math.pow(Vector.getMagnitudZ(),2)+""));
		NuevoApunte_Magnitud.AgregarParrafo(new Parrafo("3.- Magnitud² = "+(Math.pow(Vector.getMagnitudX(),2)+Math.pow(Vector.getMagnitudY(),2)+Math.pow(Vector.getMagnitudZ(),2))));
		NuevoApunte_Magnitud.AgregarParrafo(new Parrafo("4.- Magnitud  = "+Vector.getMagnitud()));

		MostrarInformacion(NuevoApunte_Magnitud);
		return new UnidadNumerosReales(Vector.getMagnitud());
    }


}
public static class operacionVecProductoEscalar extends OperacionMatematica{

    public operacionVecProductoEscalar(String tituloOperacion, String descripcionDeOperacion, int prioridadDeOperacion) {
        super((new Vector3D("")).getNombre(),(new Vector3D("")).getNombre()"Producto Escalar", "Operacion para calcular el producto escalar entre 2 vectores.", 4);
        //TODO Auto-generated constructor stub
    }

    @Override
    public ObjetoAlgebraico calcularOperacion(ObjetoAlgebraico... args) {

        Vector3D Vector = (Vector3D) args[0];
        Vector3D Vector2 = (Vector3D) args[1];
        UnidadNumerosReales Resultado =  new UnidadNumerosReales((Vector.getMagnitudX()*Vector2.getMagnitudX())+(Vector.getMagnitudY()*Vector2.getMagnitudY())+(Vector.getMagnitudZ()*Vector2.getMagnitudZ()));
		
		Apunte nuevoApunte_ProductoEscalar = new Apunte("Producto Escalar de \""+Vector.getNombre()+"\" y \""+ Vector2.getNombre()+"\"");
		
		nuevoApunte_ProductoEscalar.AgregarParrafo(new Parrafo("\nDefinicion: El producto escalar representa la proyeccion del vector A sobre el vector B y equivalentemente B sobre A. "));
		
		nuevoApunte_ProductoEscalar.AgregarParrafo(new Parrafo("\nFormula 1: vecA • vecB = |A||B| cos(θ)"));
		nuevoApunte_ProductoEscalar.AgregarParrafo(new Parrafo("Formula 2: vecA • vecB = AxBx + AyBy + AzBz "));
		
		nuevoApunte_ProductoEscalar.AgregarParrafo(new Parrafo("\nProcedimiento:"));
		nuevoApunte_ProductoEscalar.AgregarParrafo(new Parrafo("1.- vecA • vecB = ("+Vector.getMagnitudX()+")"+"("+Vector2.getMagnitudX()+")"+" + ("+Vector.getMagnitudY()+")"+"("+Vector2.getMagnitudY()+")"+" + ("+Vector.getMagnitudZ()+")"+"("+Vector2.getMagnitudZ()+")"));
		nuevoApunte_ProductoEscalar.AgregarParrafo(new Parrafo("2.- vecA • vecB = "+Vector.getMagnitudX()*Vector2.getMagnitudX()+"+"+Vector.getMagnitudY()*Vector2.getMagnitudY()+"+"+Vector.getMagnitudZ()*Vector2.getMagnitudZ()));
		nuevoApunte_ProductoEscalar.AgregarParrafo(new Parrafo("3.- vecA • vecB = "+Resultado.getValor()));

		MostrarInformacion(nuevoApunte_ProductoEscalar);
		return Resultado;
    }


}
public static class operacionVecProductoEscalar_v2 extends OperacionMatematica{

    public operacionVecProductoEscalar_v2(String tituloOperacion, String descripcionDeOperacion, int prioridadDeOperacion) {
        super((new Vector3D("")).getNombre(),(new Vector3D("")).getNombre(),"Producto Escalar", "Operacion para calcular el producto escalar entre 2 vectores.", 4);
        //TODO Auto-generated constructor stub
    }

    @Override
    public ObjetoAlgebraico calcularOperacion(ObjetoAlgebraico... args) {

        Vector3D Vector = (Vector3D) args[0];
        Vector3D Vector2 = (Vector3D) args[1];
        UnidadNumerosReales Resultado =  new UnidadNumerosReales((Vector.getMagnitudX()*Vector2.getMagnitudX())+(Vector.getMagnitudY()*Vector2.getMagnitudY())+(Vector.getMagnitudZ()*Vector2.getMagnitudZ()));
		
		Apunte nuevoApunte_ProductoEscalar = new Apunte("Producto Escalar de \""+Vector.getNombre()+"\" y \""+ Vector2.getNombre()+"\"");
		
		nuevoApunte_ProductoEscalar.AgregarParrafo(new Parrafo("\nDefinicion: El producto escalar representa la proyeccion del vector A sobre el vector B y equivalentemente B sobre A. "));
		
		nuevoApunte_ProductoEscalar.AgregarParrafo(new Parrafo("\nFormula 1: vecA • vecB = |A||B| cos(θ)"));
		nuevoApunte_ProductoEscalar.AgregarParrafo(new Parrafo("Formula 2: vecA • vecB = AxBx + AyBy + AzBz "));
		
		nuevoApunte_ProductoEscalar.AgregarParrafo(new Parrafo("\nProcedimiento:"));
		nuevoApunte_ProductoEscalar.AgregarParrafo(new Parrafo("1.- vecA • vecB = ("+Vector.getMagnitudX()+")"+"("+Vector2.getMagnitudX()+")"+" + ("+Vector.getMagnitudY()+")"+"("+Vector2.getMagnitudY()+")"+" + ("+Vector.getMagnitudZ()+")"+"("+Vector2.getMagnitudZ()+")"));
		nuevoApunte_ProductoEscalar.AgregarParrafo(new Parrafo("2.- vecA • vecB = "+Vector.getMagnitudX()*Vector2.getMagnitudX()+"+"+Vector.getMagnitudY()*Vector2.getMagnitudY()+"+"+Vector.getMagnitudZ()*Vector2.getMagnitudZ()));
		nuevoApunte_ProductoEscalar.AgregarParrafo(new Parrafo("3.- vecA • vecB = "+Resultado.getValor()));

		MostrarInformacion(nuevoApunte_ProductoEscalar);
		return Resultado;
    }


}

    
}
