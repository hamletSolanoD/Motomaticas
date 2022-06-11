package ObjetosLogicos.motorMatematico.operaciones;
import EjemploDeInyeccion.Vector3;
import ObjetosLogicos.motorMatematico.ObjetoAlgebraico;
import ObjetosLogicos.motorMatematico.variables.UnidadAritmetica;
import RecursosCustomizados.BotonApuntes;
import ValoresDefault.Constantes;
import ValoresDefault.Constantes.Apunte;
import ValoresDefault.Constantes.Parrafo;
import ValoresDefault.Constantes.TipoOperacion;
import VentanasProyecto.MainApunteFrame;

public class Operacion extends ObjetoAlgebraico {

    private TipoOperacion Operacion;
    
	//constructor por defecto, usando operaciones basicas ya incluidas
	public Operacion(TipoOperacion Operacion) {
		super(Constantes.TipoObjetoAlgebraico.Operacion);	//declara que el objeto que vamos a usar es un objeto tipo operacion, no un objeto tipo variable
		this.Operacion = Operacion;	// le pasamos por parametro la operacion que se usa
	}



	/*
	 * agrega al panel general de algebra la informacion de esta operacion en lenguaje humano en un objeto tipo boton de apuntes
	 */
    private static void MostrarInformacion(Apunte Apunte) {
    MainApunteFrame.panel_Algebra.AgregarBotonDeApuntes(new BotonApuntes(Apunte));
    }

	
	
	
	///////////// VECTOR UNITARIO  \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	public static Vector3 VecUnitario(Vector3 Vector) {
		Vector3 Resultado = new Vector3("Vector unitario de "+Vector.getNombre(),Vector.getThetaX(),Vector.getThetaY(),Vector.getThetaZ(),1);
		
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
	
	///////////// VECTOR MAGNITUD  \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	public static double VecMagnitud(Vector3 Vector) {
		Apunte NuevoApunte_Magnitud = new Apunte("Magnitud del vector \""+ Vector.getNombre()+"\"");
		
		NuevoApunte_Magnitud.AgregarParrafo(new Parrafo("\nDefinicion: La magnitud de un vector respresenta la unidad de amplitud o cantidad que en union con la direccion representa un vector."));
	
		NuevoApunte_Magnitud.AgregarParrafo(new Parrafo("\nFormula: Magnitud² = magnitudX² + magnitudY² + magnitudZ²"));
		
		NuevoApunte_Magnitud.AgregarParrafo(new Parrafo("\nProcedimiento:"));
		NuevoApunte_Magnitud.AgregarParrafo(new Parrafo("1.- Magnitud² = "+Vector.getMagnitudX()+"² + "+Vector.getMagnitudY()+"² + "+Vector.getMagnitudZ()+"²"));
		NuevoApunte_Magnitud.AgregarParrafo(new Parrafo("2.- Magnitud² = "+Math.pow(Vector.getMagnitudX(),2)+" + "+Math.pow(Vector.getMagnitudY(),2)+" + "+Math.pow(Vector.getMagnitudZ(),2)+""));
		NuevoApunte_Magnitud.AgregarParrafo(new Parrafo("3.- Magnitud² = "+(Math.pow(Vector.getMagnitudX(),2)+Math.pow(Vector.getMagnitudY(),2)+Math.pow(Vector.getMagnitudZ(),2))));
		NuevoApunte_Magnitud.AgregarParrafo(new Parrafo("4.- Magnitud  = "+Vector.getMagnitud()));

		MostrarInformacion(NuevoApunte_Magnitud);
		return  Vector.getMagnitud();
	}
	
	///////////// PRODUCTO ESCALAR  \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public static UnidadAritmetica VecProductoEscalar(Vector3 Vector,Vector3 Vector2) {
		UnidadAritmetica Resultado =  new UnidadAritmetica((Vector.getMagnitudX()*Vector2.getMagnitudX())+(Vector.getMagnitudY()*Vector2.getMagnitudY())+(Vector.getMagnitudZ()*Vector2.getMagnitudZ()));
		
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
	public static UnidadAritmetica VecProductoEscalar(double MagnitudA, double MagnitudB, double Angulo) {
		return new UnidadAritmetica( MagnitudA*MagnitudB*Math.cos(Angulo));
	}
	
	///////////// PRODUCTO CRUZ  \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public static Vector3 VecCruz(Vector3 VectorA, Vector3 VectorB) {
		double MagnitudX = (VectorA.getMagnitudY()*VectorB.getMagnitudZ())-(VectorA.getMagnitudZ()*VectorB.getMagnitudY()); 
		double MagnitudY = (-1)*((VectorA.getMagnitudX()*VectorB.getMagnitudZ())-(VectorA.getMagnitudZ()*VectorB.getMagnitudX())); 
		double MagnitudZ = (VectorA.getMagnitudX()*VectorB.getMagnitudY())-(VectorA.getMagnitudY()*VectorB.getMagnitudX()); 
		Vector3 Resultado = new Vector3("Vector producto cruz de "+VectorA.getNombre()+" y "+VectorB.getNombre(),MagnitudX,MagnitudY,MagnitudZ);
		
		Apunte nuevoApunte_VectorCruz = new Apunte("Vector Cruz de \""+ VectorA.getNombre()+"\" x \""+ VectorB.getNombre()+"\"");
		
		nuevoApunte_VectorCruz.AgregarParrafo(new Parrafo("\nDefinicion: El producto cruz representa la operacion entre 2 vectores multiplicandose en el mismo plano resultando en un nuevo vector producto de ambos."));
		
		nuevoApunte_VectorCruz.AgregarParrafo(new Parrafo("\nFormula: vecA x vecB = Vector( ((Ay)(Bz) - (Az)(By)) î , ((Az)(Bx) - (Ax)(Bz)) ĵ , ((Ax)(By) - (Ay)(Bx)) ž)"));
		
		nuevoApunte_VectorCruz.AgregarParrafo(new Parrafo("\nProcedimiento:"));
		nuevoApunte_VectorCruz.AgregarParrafo(new Parrafo("1.- vecA x vecB = Vector( (("+VectorA.getMagnitudY()+")("+VectorB.getMagnitudZ()+") - ("+VectorA.getMagnitudZ()+")("+VectorB.getMagnitudY()+")) î , (("+VectorA.getMagnitudZ()+")("+VectorB.getMagnitudX()+") - ("+VectorA.getMagnitudX()+")("+VectorB.getMagnitudZ()+")) ĵ , (("+VectorA.getMagnitudX()+")("+VectorB.getMagnitudY()+") - ("+VectorA.getMagnitudY()+")("+VectorB.getMagnitudX()+")) ž)"));
		nuevoApunte_VectorCruz.AgregarParrafo(new Parrafo("2.- vecA x vecB = Vector( "+MagnitudX+" î , "+MagnitudY+" ĵ , "+MagnitudZ+" ž)"));
		nuevoApunte_VectorCruz.AgregarParrafo(new Parrafo("3.- vecA x vecB = "+Resultado.toStringReducido()));

		MostrarInformacion(nuevoApunte_VectorCruz);
		return Resultado;
	}
	
	/////////////    OPERACIONES ARITMETICAS                                  \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	public static UnidadAritmetica Sumar(UnidadAritmetica A, UnidadAritmetica B) {
		
		Apunte nuevoApunte_SumaAritmetica = new Apunte("Suma "+A.getValor()+" + "+B.getValor()+" = "+ (A.getValor()+B.getValor()));
		
		MostrarInformacion(nuevoApunte_SumaAritmetica);
		return new UnidadAritmetica(A.getValor()+B.getValor());
	}
   public static Vector3 Sumar(Vector3 VectorA, Vector3 VectorB) {
	   double MagnitudX = VectorA.getMagnitudX() + VectorB.getMagnitudX();
	   double MagnitudY = VectorA.getMagnitudY() + VectorB.getMagnitudY();
	   double MagnitudZ = VectorA.getMagnitudZ() + VectorB.getMagnitudZ();
	   Vector3 Resultado = new Vector3("Vector suma de "+VectorA.getNombre()+" y "+VectorB.getNombre(),MagnitudX,MagnitudY,MagnitudZ); 
	   
	   Apunte nuevoApunte_SumaVectores = new Apunte("Suma de los vectores \""+ VectorA.getNombre()+"\" + \""+VectorB.getNombre()+"\"");
	   
	   nuevoApunte_SumaVectores.AgregarParrafo(new Parrafo("\nDefinicion: La suma de vectores representa sumar individulmente las componentes de cada vector para dar resultado con ellas a uno nuevo."));
	  
	   nuevoApunte_SumaVectores.AgregarParrafo(new Parrafo("\nFormula: vecA + vecB = Vector( (Ax + Bx) î, (Ay + By) ĵ, (Az + Bz) ž)"));
	 
	   nuevoApunte_SumaVectores.AgregarParrafo(new Parrafo("\nProcedimiento:"));
	   nuevoApunte_SumaVectores.AgregarParrafo(new Parrafo("1.- vecA + vecB = Vector( ("+VectorA.getMagnitudX()+" + "+VectorB.getMagnitudX()+") î, ("+VectorA.getMagnitudY()+" + "+VectorB.getMagnitudY()+") ĵ, ("+VectorA.getMagnitudZ()+" + "+VectorB.getMagnitudZ()+") ž)"));
	   nuevoApunte_SumaVectores.AgregarParrafo(new Parrafo("2.- vecA + vecB = "+Resultado.toStringReducido()));
	   MostrarInformacion(nuevoApunte_SumaVectores);
	 return Resultado;
	}

	public static UnidadAritmetica Restar(UnidadAritmetica A, UnidadAritmetica B) {
		Apunte nuevoApunte_RestaAritmetica = new Apunte("Resta "+A.getValor()+" - "+B.getValor()+" = "+(A.getValor()-B.getValor()));
		MostrarInformacion(nuevoApunte_RestaAritmetica);
		return new UnidadAritmetica(A.getValor()-B.getValor());
		
	}
  public static Vector3 Restar(Vector3 VectorA, Vector3 VectorB) {
	   double MagnitudX = VectorA.getMagnitudX() - VectorB.getMagnitudX();
	   double MagnitudY = VectorA.getMagnitudY() - VectorB.getMagnitudY();
	   double MagnitudZ = VectorA.getMagnitudZ() - VectorB.getMagnitudZ();
	   Vector3 Resultado = new Vector3("Vector resta de "+VectorA.getNombre()+" y "+VectorB.getNombre(),MagnitudX,MagnitudY,MagnitudZ);
	   
	   Apunte nuevoApunte_RestaVectores = new Apunte("Resta de los vectores \""+ VectorA.getNombre()+"\" - \""+VectorB.getNombre()+"\"");
	  
	   nuevoApunte_RestaVectores.AgregarParrafo(new Parrafo("\nDefinicion: La resta de vectores representa restar individulmente las componentes de cada vector para dar resultado con ellas a uno nuevo."));
	   
	   nuevoApunte_RestaVectores.AgregarParrafo(new Parrafo("\nFormula: vecA - vecB = Vector( (Ax - Bx) î, (Ay - By) ĵ, (Az - Bz) ž)"));
	  
	   nuevoApunte_RestaVectores.AgregarParrafo(new Parrafo("\nProcedimiento:"));
	   nuevoApunte_RestaVectores.AgregarParrafo(new Parrafo("1.- vecA - vecB = Vector( ("+VectorA.getMagnitudX()+" - "+VectorB.getMagnitudX()+") î, ("+VectorA.getMagnitudY()+" - "+VectorB.getMagnitudY()+") ĵ, ("+VectorA.getMagnitudZ()+" - "+VectorB.getMagnitudZ()+") ž)"));
	   nuevoApunte_RestaVectores.AgregarParrafo(new Parrafo("2.- vecA - vecB = "+Resultado.toStringReducido()));
	 
	 MostrarInformacion(nuevoApunte_RestaVectores);
	 return Resultado;
	}
  
  public static Vector3 Dividir(Vector3 Vector, UnidadAritmetica Unidad) {
	  Vector3 Resultado =  new Vector3("Vector Dividir vector "+Vector.getNombre()+" / "+Unidad,Vector.getMagnitudX()/Unidad.getValor(),Vector.getMagnitudY()/Unidad.getValor(),Vector.getMagnitudZ()/Unidad.getValor());
	  
	  Apunte nuevoApunte_DividirVector = new Apunte("Dividir vector \""+ Vector.toStringReducido()+"\" / \""+Unidad.getValor()+"\"");
	 
	  nuevoApunte_DividirVector.AgregarParrafo(new Parrafo("\nDefinicion: Dividir un vector representa dividir su magnitud total entre alguna unidad o dividir las magnitudes de cada eje entre la unidad para dar como resultado un nuevo vector. "));
	 
	  nuevoApunte_DividirVector.AgregarParrafo(new Parrafo("\nFormula: Vector / Unidad = (Vector Magnitud / Unidad) x ( Â(Vector) )"));
	 
	  nuevoApunte_DividirVector.AgregarParrafo(new Parrafo("\nProcedimiento:"));
	  nuevoApunte_DividirVector.AgregarParrafo(new Parrafo("1.- Magnitud X = "+ Vector.getMagnitudX()+" / "+Unidad.getValor()+" = "+ Resultado.getMagnitudX()));
	  nuevoApunte_DividirVector.AgregarParrafo(new Parrafo("2.- Magnitud Y = "+ Vector.getMagnitudY()+" / "+Unidad.getValor()+" = "+ Resultado.getMagnitudY()));
	  nuevoApunte_DividirVector.AgregarParrafo(new Parrafo("3.- Magnitud Z = "+ Vector.getMagnitudZ()+" / "+Unidad.getValor()+" = "+ Resultado.getMagnitudZ()));
	  nuevoApunte_DividirVector.AgregarParrafo(new Parrafo("4.- "+Vector.toStringReducido()+" / "+Unidad.getValor()+" = "+Resultado.toStringReducido()));
	 
	  nuevoApunte_DividirVector.AgregarParrafo(new Parrafo("\nFormula: Vector / Unidad = Vector( MagnitudX / Unidad, MagnitudY / Unidad, MagnitudZ / Unidad)"));

	  nuevoApunte_DividirVector.AgregarParrafo(new Parrafo("\nProcedimiento 2:"));
	  nuevoApunte_DividirVector.AgregarParrafo(new Parrafo("1.- Magnitud / Unidad = "+Vector.getMagnitud()+" / "+ Unidad.getValor()));
	  nuevoApunte_DividirVector.AgregarParrafo(new Parrafo("2.- Magnitud / Unidad = "+(Vector.getMagnitud()/Unidad.getValor())));
	  nuevoApunte_DividirVector.AgregarParrafo(new Parrafo("3.- "+Vector.toStringReducido()+" / "+Unidad.getValor()+" = "+(Vector.getMagnitud()/Unidad.getValor())+""+VecUnitario(Vector).toStringReducido()));
	  nuevoApunte_DividirVector.AgregarParrafo(new Parrafo("4.- "+Vector.toStringReducido()+" / "+Unidad.getValor()+" = "+Resultado.toStringReducido()));


	  MostrarInformacion(nuevoApunte_DividirVector);
	  return Resultado;
  }
  public static UnidadAritmetica Dividir(UnidadAritmetica A, UnidadAritmetica B) {
	  Apunte nuevoApunte_DividirAritmetico = new Apunte("Dividir "+A.getValor()+" / "+B.getValor()+" = "+(A.getValor()/B.getValor()));
		
	  MostrarInformacion(nuevoApunte_DividirAritmetico);
	  return new UnidadAritmetica(A.getValor()/B.getValor());
  }

  
  
  public static UnidadAritmetica Multiplicar(UnidadAritmetica A, UnidadAritmetica B) { 
	  Apunte nuevoApunte_MultiplicarAritmetico = new Apunte("Multiplicar "+A.getValor()+" x "+B.getValor()+" = "+(A.getValor()*B.getValor()));

	  return new UnidadAritmetica(A.getValor()*B.getValor());
	  }
  
  public static Vector3 Multiplicar(Vector3 Vector, UnidadAritmetica Unidad) {
	  Vector3 Resultado = new Vector3("Vector Multiplicar Magnitud de "+Vector.getNombre()+" y "+Unidad,Vector.getMagnitudX()*Unidad.getValor(),Vector.getMagnitudY()*Unidad.getValor(),Vector.getMagnitudZ()*Unidad.getValor());
	  
	  
	  Apunte nuevoApunte_Multiplicar = new Apunte("Multiplicar Magnitud del vector \""+ Vector.getMagnitud()+"\" x \""+Unidad.getValor()+"\"");
	 
	  nuevoApunte_Multiplicar.AgregarParrafo(new Parrafo("\nDefinicion: Multiplicar un vector por una magnitud representa multiplicar su magnitud total por alguna unidad o multiplicar las magnitudes de cada eje por la unidad para dar como resultado un nuevo vector. "));
	  
	  nuevoApunte_Multiplicar.AgregarParrafo(new Parrafo("\nFormula: Vector x Unidad = (Vector Magnitud x Unidad) x ( Â(Vector) )"));
	
	  nuevoApunte_Multiplicar.AgregarParrafo(new Parrafo("\nProcedimiento:"));
	  nuevoApunte_Multiplicar.AgregarParrafo(new Parrafo("1.- Magnitud X = "+ Vector.getMagnitudX()+" x "+Unidad.getValor()+" = "+ Resultado.getMagnitudX()));
	  nuevoApunte_Multiplicar.AgregarParrafo(new Parrafo("2.- Magnitud Y = "+ Vector.getMagnitudY()+" x "+Unidad.getValor()+" = "+ Resultado.getMagnitudY()));
	  nuevoApunte_Multiplicar.AgregarParrafo(new Parrafo("3.- Magnitud Z = "+ Vector.getMagnitudZ()+" x "+Unidad.getValor()+" = "+ Resultado.getMagnitudZ()));
	  nuevoApunte_Multiplicar.AgregarParrafo(new Parrafo("4.- "+Vector.toStringReducido()+" x "+Unidad.getValor()+" = "+Resultado.toStringReducido()));
	
	  nuevoApunte_Multiplicar.AgregarParrafo(new Parrafo("\nFormula: Vector x Unidad = Vector( MagnitudX x Unidad, MagnitudY x Unidad, MagnitudZ x Unidad)"));

	  nuevoApunte_Multiplicar.AgregarParrafo(new Parrafo("\nProcedimiento 2:"));
	  nuevoApunte_Multiplicar.AgregarParrafo(new Parrafo("1.- Magnitud x Unidad = "+Vector.getMagnitud()+" x "+ Unidad.getValor()));
	  nuevoApunte_Multiplicar.AgregarParrafo(new Parrafo("2.- Magnitud x Unidad = "+(Vector.getMagnitud()*Unidad.getValor())));
	  nuevoApunte_Multiplicar.AgregarParrafo(new Parrafo("3.- "+Vector.toStringReducido()+" x "+Unidad.getValor()+" = "+(Vector.getMagnitud()*Unidad.getValor())+""+VecUnitario(Vector).toStringReducido()));
	  nuevoApunte_Multiplicar.AgregarParrafo(new Parrafo("4.- "+Vector.toStringReducido()+" x "+Unidad.getValor()+" = "+Resultado.toStringReducido()));


	  MostrarInformacion(nuevoApunte_Multiplicar);
	  return Resultado;
  }


  /*
   * retorna su tipo de operacion
   */
  public TipoOperacion getOperacion() {
	  return Operacion;
  } 

	/*retorna la descripcion de la operacion */
  public String toString(){
	  return Operacion.toString();
  }
	
}
