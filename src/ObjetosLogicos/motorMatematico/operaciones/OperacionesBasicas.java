package ObjetosLogicos.motorMatematico.operaciones;
import ModulosImportados.Vector3D.Vector3D;
import ObjetosLogicos.motorMatematico.ObjetoAlgebraico;
import ObjetosLogicos.motorMatematico.variables.UnidadAritmetica;
import RecursosCustomizados.BotonApuntes;
import ValoresDefault.Constantes;
import ValoresDefault.Constantes.Apunte;
import ValoresDefault.Constantes.Parrafo;
import ValoresDefault.Constantes.TipoOperacion;
import VentanasProyecto.MainApunteFrame;



	public static UnidadAritmetica VecProductoEscalar(double MagnitudA, double MagnitudB, double Angulo) {
		return new UnidadAritmetica( MagnitudA*MagnitudB*Math.cos(Angulo));
	}
	
	///////////// PRODUCTO CRUZ  \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public static Vector3D VecCruz(Vector3D VectorA, Vector3D VectorB) {
		double MagnitudX = (VectorA.getMagnitudY()*VectorB.getMagnitudZ())-(VectorA.getMagnitudZ()*VectorB.getMagnitudY()); 
		double MagnitudY = (-1)*((VectorA.getMagnitudX()*VectorB.getMagnitudZ())-(VectorA.getMagnitudZ()*VectorB.getMagnitudX())); 
		double MagnitudZ = (VectorA.getMagnitudX()*VectorB.getMagnitudY())-(VectorA.getMagnitudY()*VectorB.getMagnitudX()); 
		Vector3D Resultado = new Vector3D("Vector producto cruz de "+VectorA.getNombre()+" y "+VectorB.getNombre(),MagnitudX,MagnitudY,MagnitudZ);
		
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


   public static Vector3D Sumar(Vector3D VectorA, Vector3D VectorB) {
	   double MagnitudX = VectorA.getMagnitudX() + VectorB.getMagnitudX();
	   double MagnitudY = VectorA.getMagnitudY() + VectorB.getMagnitudY();
	   double MagnitudZ = VectorA.getMagnitudZ() + VectorB.getMagnitudZ();
	   Vector3D Resultado = new Vector3D("Vector suma de "+VectorA.getNombre()+" y "+VectorB.getNombre(),MagnitudX,MagnitudY,MagnitudZ); 
	   
	   Apunte nuevoApunte_SumaVectores = new Apunte("Suma de los vectores \""+ VectorA.getNombre()+"\" + \""+VectorB.getNombre()+"\"");
	   
	   nuevoApunte_SumaVectores.AgregarParrafo(new Parrafo("\nDefinicion: La suma de vectores representa sumar individulmente las componentes de cada vector para dar resultado con ellas a uno nuevo."));
	  
	   nuevoApunte_SumaVectores.AgregarParrafo(new Parrafo("\nFormula: vecA + vecB = Vector( (Ax + Bx) î, (Ay + By) ĵ, (Az + Bz) ž)"));
	 
	   nuevoApunte_SumaVectores.AgregarParrafo(new Parrafo("\nProcedimiento:"));
	   nuevoApunte_SumaVectores.AgregarParrafo(new Parrafo("1.- vecA + vecB = Vector( ("+VectorA.getMagnitudX()+" + "+VectorB.getMagnitudX()+") î, ("+VectorA.getMagnitudY()+" + "+VectorB.getMagnitudY()+") ĵ, ("+VectorA.getMagnitudZ()+" + "+VectorB.getMagnitudZ()+") ž)"));
	   nuevoApunte_SumaVectores.AgregarParrafo(new Parrafo("2.- vecA + vecB = "+Resultado.toStringReducido()));
	   MostrarInformacion(nuevoApunte_SumaVectores);
	 return Resultado;
	}


  public static Vector3D Restar(Vector3D VectorA, Vector3D VectorB) {
	   double MagnitudX = VectorA.getMagnitudX() - VectorB.getMagnitudX();
	   double MagnitudY = VectorA.getMagnitudY() - VectorB.getMagnitudY();
	   double MagnitudZ = VectorA.getMagnitudZ() - VectorB.getMagnitudZ();
	   Vector3D Resultado = new Vector3D("Vector resta de "+VectorA.getNombre()+" y "+VectorB.getNombre(),MagnitudX,MagnitudY,MagnitudZ);
	   
	   Apunte nuevoApunte_RestaVectores = new Apunte("Resta de los vectores \""+ VectorA.getNombre()+"\" - \""+VectorB.getNombre()+"\"");
	  
	   nuevoApunte_RestaVectores.AgregarParrafo(new Parrafo("\nDefinicion: La resta de vectores representa restar individulmente las componentes de cada vector para dar resultado con ellas a uno nuevo."));
	   
	   nuevoApunte_RestaVectores.AgregarParrafo(new Parrafo("\nFormula: vecA - vecB = Vector( (Ax - Bx) î, (Ay - By) ĵ, (Az - Bz) ž)"));
	  
	   nuevoApunte_RestaVectores.AgregarParrafo(new Parrafo("\nProcedimiento:"));
	   nuevoApunte_RestaVectores.AgregarParrafo(new Parrafo("1.- vecA - vecB = Vector( ("+VectorA.getMagnitudX()+" - "+VectorB.getMagnitudX()+") î, ("+VectorA.getMagnitudY()+" - "+VectorB.getMagnitudY()+") ĵ, ("+VectorA.getMagnitudZ()+" - "+VectorB.getMagnitudZ()+") ž)"));
	   nuevoApunte_RestaVectores.AgregarParrafo(new Parrafo("2.- vecA - vecB = "+Resultado.toStringReducido()));
	 
	 MostrarInformacion(nuevoApunte_RestaVectores);
	 return Resultado;
	}
  
  public static Vector3D Dividir(Vector3D Vector, UnidadAritmetica Unidad) {
	  Vector3D Resultado =  new Vector3D("Vector Dividir vector "+Vector.getNombre()+" / "+Unidad,Vector.getMagnitudX()/Unidad.getValor(),Vector.getMagnitudY()/Unidad.getValor(),Vector.getMagnitudZ()/Unidad.getValor());
	  
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

  
  

  public static Vector3D Multiplicar(Vector3D Vector, UnidadAritmetica Unidad) {
	  Vector3D Resultado = new Vector3D("Vector Multiplicar Magnitud de "+Vector.getNombre()+" y "+Unidad,Vector.getMagnitudX()*Unidad.getValor(),Vector.getMagnitudY()*Unidad.getValor(),Vector.getMagnitudZ()*Unidad.getValor());
	  
	  
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


	
}
/*		
		Producto_Cruz("Operacion Producto Cruz:\n Operacion para calcular el producto cruz entre 2 vectores."),
		Sumar("Suma:\n Operacion para sumar 2 unidades o 2 vectores"),
		Restar("Resta:\n Operacion para restar 2 unidades o 2 vectores"),
		Dividir("Dividir:\n Operacion para dividir 2 unidades o la magnitud de un vector por una unidad"),
		Multiplicar(
				"Multiplicar:\n Operacion para multiplicar 2 unidades o la magnitud de un vector entre una unidad."),
		Parentesis_Izquierdo(
				"Parentesis Izquierdo:\n Simbologia para subdividir una operacion en multiples jerarquias."),
		Parentesis_Derecho("Parentesis Derecho:\n Simbologia para subdividir una operacion en multiples jerarquias.");
 */