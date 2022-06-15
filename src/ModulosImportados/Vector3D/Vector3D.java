package ModulosImportados.Vector3D;

import java.lang.Math;

import javax.swing.JFrame;

import ModulosImportados.NumerosReales.UnidadNumerosReales;
import Motomaticas.ValoresDefault.Constantes.Parrafo;
import Motomaticas.ObjetosLogicos.motorMatematico.ObjetoMatematico;
import Motomaticas.ObjetosLogicos.motorMatematico.operaciones.OperacionMatematica;
import Motomaticas.ObjetosLogicos.motorMatematico.variables.UnidadMatematica;
import Motomaticas.ValoresDefault.Constantes.Apunte;;

public class Vector3D extends UnidadMatematica implements OperacionesVector{

	private static class suma extends OperacionMatematica {
		@Override
        public String getCategoriaMatematica(){
                return "Operaciones Entre Vectores 3D";
        } 

		public suma() {
			super(false,"+", "Suma Vectores 3D", "Operacion para sumar 2 vectores", 1);

		}

		@Override
		public UnidadMatematica calcularOperacion(UnidadMatematica... args) {
			Vector3D VectorA = (Vector3D) args[0];
			Vector3D VectorB = (Vector3D) args[1];
			double MagnitudX = VectorA.getMagnitudX() + VectorB.getMagnitudX();
			double MagnitudY = VectorA.getMagnitudY() + VectorB.getMagnitudY();
			double MagnitudZ = VectorA.getMagnitudZ() + VectorB.getMagnitudZ();
			Vector3D Resultado = new Vector3D("Vector suma de " + VectorA.getNombreVector() + " y " + VectorB.getNombreVector(),
					MagnitudX, MagnitudY, MagnitudZ);

			Apunte nuevoApunte_SumaVectores = new Apunte(
					"Suma de los vectores \"" + VectorA.getNombreVector() + "\" + \"" + VectorB.getNombreVector() + "\"");
			nuevoApunte_SumaVectores.AgregarParrafo(new Parrafo(
					"\nDefinicion: La suma de vectores representa sumar individulmente las componentes de cada vector para dar resultado con ellas a uno nuevo."));
			nuevoApunte_SumaVectores.AgregarParrafo(
					new Parrafo("\nFormula: vecA + vecB = Vector( (Ax + Bx) î, (Ay + By) ĵ, (Az + Bz) ž)"));
			nuevoApunte_SumaVectores.AgregarParrafo(new Parrafo("\nProcedimiento:"));
			nuevoApunte_SumaVectores.AgregarParrafo(
					new Parrafo("1.- vecA + vecB = Vector( (" + VectorA.getMagnitudX() + " + " + VectorB.getMagnitudX()
							+ ") î, (" + VectorA.getMagnitudY() + " + " + VectorB.getMagnitudY() + ") ĵ, ("
							+ VectorA.getMagnitudZ() + " + " + VectorB.getMagnitudZ() + ") ž)"));
			nuevoApunte_SumaVectores.AgregarParrafo(new Parrafo("2.- vecA + vecB = " + Resultado.toStringReducido()));
			MostrarInformacion(nuevoApunte_SumaVectores);

			return Resultado;
		}

		@Override
		protected String[] definirTipoDeOperandoscorrectos() {
			String retorno[] = 	{ (new Vector3D("")).getNombreObjetoMatematico(), (new Vector3D("")).getNombreObjetoMatematico() };
			return retorno;

		}

	}

	private static class resta extends OperacionMatematica {
		@Override
        public String getCategoriaMatematica(){
                return "Operaciones Entre Vectores 3D";
        } 
		public resta() {
			super(false,"-", "Resta Vectores 3D", "Operacion para restar 2 vectores", 1);
		}
		@Override
		protected String[] definirTipoDeOperandoscorrectos() {
			String retorno[] = 	{ (new Vector3D("")).getNombreObjetoMatematico(), (new Vector3D("")).getNombreObjetoMatematico() };
			return retorno;

		}


		@Override
		public UnidadMatematica calcularOperacion(UnidadMatematica... args) {
			Vector3D VectorA = (Vector3D) args[0];
			Vector3D VectorB = (Vector3D) args[1];
			double MagnitudX = VectorA.getMagnitudX() - VectorB.getMagnitudX();
			double MagnitudY = VectorA.getMagnitudY() - VectorB.getMagnitudY();
			double MagnitudZ = VectorA.getMagnitudZ() - VectorB.getMagnitudZ();
			Vector3D Resultado = new Vector3D("Vector resta de " + VectorA.getNombreVector() + " y " + VectorB.getNombreVector(),
					MagnitudX, MagnitudY, MagnitudZ);

			Apunte nuevoApunte_RestaVectores = new Apunte(
					"Resta de los vectores \"" + VectorA.getNombreVector() + "\" - \"" + VectorB.getNombreVector() + "\"");

			nuevoApunte_RestaVectores.AgregarParrafo(new Parrafo(
					"\nDefinicion: La resta de vectores representa restar individulmente las componentes de cada vector para dar resultado con ellas a uno nuevo."));

			nuevoApunte_RestaVectores.AgregarParrafo(
					new Parrafo("\nFormula: vecA - vecB = Vector( (Ax - Bx) î, (Ay - By) ĵ, (Az - Bz) ž)"));

			nuevoApunte_RestaVectores.AgregarParrafo(new Parrafo("\nProcedimiento:"));
			nuevoApunte_RestaVectores.AgregarParrafo(
					new Parrafo("1.- vecA - vecB = Vector( (" + VectorA.getMagnitudX() + " - " + VectorB.getMagnitudX()
							+ ") î, (" + VectorA.getMagnitudY() + " - " + VectorB.getMagnitudY() + ") ĵ, ("
							+ VectorA.getMagnitudZ() + " - " + VectorB.getMagnitudZ() + ") ž)"));
			nuevoApunte_RestaVectores.AgregarParrafo(new Parrafo("2.- vecA - vecB = " + Resultado.toStringReducido()));

			MostrarInformacion(nuevoApunte_RestaVectores);
			return Resultado;
		}

	}

	private static class multiplicacion extends OperacionMatematica {
		@Override
        public String getCategoriaMatematica(){
                return "Operaciones Entre Vectores 3D";
        } 
		public multiplicacion() {
			super(false,"X", "multiplicar Vectores 3D",
					"Operacion para multiplicar la magnitud de un vector por una unidad.", 2);
		}

		@Override
		protected String[] definirTipoDeOperandoscorrectos() {
			String retorno[] = 	{ (new Vector3D("")).getNombreObjetoMatematico(), (new UnidadNumerosReales()).getNombreObjetoMatematico() };
			return retorno;

		}

		@Override
		public UnidadMatematica calcularOperacion(UnidadMatematica... args) {
			Vector3D Vector = (Vector3D) args[0];
			UnidadNumerosReales Unidad = (UnidadNumerosReales) args[1];
			Vector3D Resultado = new Vector3D("Vector Multiplicar Magnitud de " + Vector.getNombreVector() + " y " + Unidad,
					Vector.getMagnitudX() * Unidad.getValor(), Vector.getMagnitudY() * Unidad.getValor(),
					Vector.getMagnitudZ() * Unidad.getValor());

			Apunte nuevoApunte_Multiplicar = new Apunte(
					"Multiplicar Magnitud del vector \"" + Vector.getMagnitud() + "\" x \"" + Unidad.getValor() + "\"");

			nuevoApunte_Multiplicar.AgregarParrafo(new Parrafo(
					"\nDefinicion: Multiplicar un vector por una magnitud representa multiplicar su magnitud total por alguna unidad o multiplicar las magnitudes de cada eje por la unidad para dar como resultado un nuevo vector. "));

			nuevoApunte_Multiplicar.AgregarParrafo(
					new Parrafo("\nFormula: Vector x Unidad = (Vector Magnitud x Unidad) x ( Â(Vector) )"));

			nuevoApunte_Multiplicar.AgregarParrafo(new Parrafo("\nProcedimiento:"));
			nuevoApunte_Multiplicar.AgregarParrafo(new Parrafo("1.- Magnitud X = " + Vector.getMagnitudX() + " x "
					+ Unidad.getValor() + " = " + Resultado.getMagnitudX()));
			nuevoApunte_Multiplicar.AgregarParrafo(new Parrafo("2.- Magnitud Y = " + Vector.getMagnitudY() + " x "
					+ Unidad.getValor() + " = " + Resultado.getMagnitudY()));
			nuevoApunte_Multiplicar.AgregarParrafo(new Parrafo("3.- Magnitud Z = " + Vector.getMagnitudZ() + " x "
					+ Unidad.getValor() + " = " + Resultado.getMagnitudZ()));
			nuevoApunte_Multiplicar.AgregarParrafo(new Parrafo("4.- " + Vector.toStringReducido() + " x "
					+ Unidad.getValor() + " = " + Resultado.toStringReducido()));

			nuevoApunte_Multiplicar.AgregarParrafo(new Parrafo(
					"\nFormula: Vector x Unidad = Vector( MagnitudX x Unidad, MagnitudY x Unidad, MagnitudZ x Unidad)"));

			nuevoApunte_Multiplicar.AgregarParrafo(new Parrafo("\nProcedimiento 2:"));
			nuevoApunte_Multiplicar.AgregarParrafo(
					new Parrafo("1.- Magnitud x Unidad = " + Vector.getMagnitud() + " x " + Unidad.getValor()));
			nuevoApunte_Multiplicar.AgregarParrafo(
					new Parrafo("2.- Magnitud x Unidad = " + (Vector.getMagnitud() * Unidad.getValor())));
			nuevoApunte_Multiplicar.AgregarParrafo(new Parrafo("3.- " + Vector.toStringReducido() + " x "
					+ Unidad.getValor() + " = " + (Vector.getMagnitud() * Unidad.getValor()) + ""
					+ ((new OperacionesVector.operacionVecUnitario()).calcularOperacion(Vector)).toStringReducido()));
			nuevoApunte_Multiplicar.AgregarParrafo(new Parrafo("4.- " + Vector.toStringReducido() + " x "
					+ Unidad.getValor() + " = " + Resultado.toStringReducido()));

			MostrarInformacion(nuevoApunte_Multiplicar);
			return Resultado;
		}

	}

	private static class division extends OperacionMatematica {
		@Override
        public String getCategoriaMatematica(){
                return "Operaciones Entre Vectores 3D";
        } 
		static String[] operandos = { (new Vector3D("")).getNombreObjetoMatematico(), (new UnidadNumerosReales()).getNombreObjetoMatematico() };

		public division() {
			super(false,"/", "Dividir Vectores 3D", "Operacion para dividir la magnitud de un vector por una unidad.", 2);
		}
		@Override
		protected String[] definirTipoDeOperandoscorrectos() {
			String retorno[] = 	{ (new Vector3D("")).getNombreObjetoMatematico(), (new UnidadNumerosReales()).getNombreObjetoMatematico() };
			return retorno;

		}


		@Override
		public UnidadMatematica calcularOperacion(UnidadMatematica... args) {
			Vector3D Vector = (Vector3D) args[0];
			UnidadNumerosReales Unidad = (UnidadNumerosReales) args[1];
			Vector3D Resultado = new Vector3D("Vector Dividir vector " + Vector.getNombreVector() + " / " + Unidad,
					Vector.getMagnitudX() / Unidad.getValor(), Vector.getMagnitudY() / Unidad.getValor(),
					Vector.getMagnitudZ() / Unidad.getValor());

			Apunte nuevoApunte_DividirVector = new Apunte(
					"Dividir vector \"" + Vector.toStringReducido() + "\" / \"" + Unidad.getValor() + "\"");

			nuevoApunte_DividirVector.AgregarParrafo(new Parrafo(
					"\nDefinicion: Dividir un vector representa dividir su magnitud total entre alguna unidad o dividir las magnitudes de cada eje entre la unidad para dar como resultado un nuevo vector. "));

			nuevoApunte_DividirVector.AgregarParrafo(
					new Parrafo("\nFormula: Vector / Unidad = (Vector Magnitud / Unidad) x ( Â(Vector) )"));

			nuevoApunte_DividirVector.AgregarParrafo(new Parrafo("\nProcedimiento:"));
			nuevoApunte_DividirVector.AgregarParrafo(new Parrafo("1.- Magnitud X = " + Vector.getMagnitudX() + " / "
					+ Unidad.getValor() + " = " + Resultado.getMagnitudX()));
			nuevoApunte_DividirVector.AgregarParrafo(new Parrafo("2.- Magnitud Y = " + Vector.getMagnitudY() + " / "
					+ Unidad.getValor() + " = " + Resultado.getMagnitudY()));
			nuevoApunte_DividirVector.AgregarParrafo(new Parrafo("3.- Magnitud Z = " + Vector.getMagnitudZ() + " / "
					+ Unidad.getValor() + " = " + Resultado.getMagnitudZ()));
			nuevoApunte_DividirVector.AgregarParrafo(new Parrafo("4.- " + Vector.toStringReducido() + " / "
					+ Unidad.getValor() + " = " + Resultado.toStringReducido()));

			nuevoApunte_DividirVector.AgregarParrafo(new Parrafo(
					"\nFormula: Vector / Unidad = Vector( MagnitudX / Unidad, MagnitudY / Unidad, MagnitudZ / Unidad)"));

			nuevoApunte_DividirVector.AgregarParrafo(new Parrafo("\nProcedimiento 2:"));
			nuevoApunte_DividirVector.AgregarParrafo(
					new Parrafo("1.- Magnitud / Unidad = " + Vector.getMagnitud() + " / " + Unidad.getValor()));
			nuevoApunte_DividirVector.AgregarParrafo(
					new Parrafo("2.- Magnitud / Unidad = " + (Vector.getMagnitud() / Unidad.getValor())));
			nuevoApunte_DividirVector.AgregarParrafo(new Parrafo("3.- " + Vector.toStringReducido() + " / "
					+ Unidad.getValor() + " = " + (Vector.getMagnitud() / Unidad.getValor()) + ""
					+ ((new OperacionesVector.operacionVecUnitario()).calcularOperacion(Vector)).toStringReducido()));
			nuevoApunte_DividirVector.AgregarParrafo(new Parrafo("4.- " + Vector.toStringReducido() + " / "
					+ Unidad.getValor() + " = " + Resultado.toStringReducido()));

			MostrarInformacion(nuevoApunte_DividirVector);
			return Resultado;
		}

	}

	private String NombreVector;

	private double magnitud;
	private double magnitudX;
	private double magnitudY;
	private double magnitudZ;

	private double ThetaX;
	private double ThetaY;
	private double ThetaZ;

	private int Plano;

	/* redondear la cantidad de decimales */
	public static double redondeo(double Numero, int cantidadRedondeo) {
		double scale = Math.pow(10, cantidadRedondeo);
		return Math.round(Numero * scale) / scale;
	}
	public void agregarFuncionesMatematicas(){
		OperacionMatematica.inyectarOperacionMatematica(new operacionVecUnitario());
		OperacionMatematica.inyectarOperacionMatematica(new operacionVecMagnitud());
		OperacionMatematica.inyectarOperacionMatematica(new operacionVecProductoEscalar());
		OperacionMatematica.inyectarOperacionMatematica(new operacionVecCruz());



	}

	/*
	 * Vector 3D con nombre y direcciones creado a partir de 3 magnitudes en sus
	 * ejes
	 */
	public Vector3D(String NombreVector, double magnitudX, double magnitudY, double magnitudZ) {
		super("Vector 3D","'V",new suma(), new resta(), new multiplicacion(), new division());
		this.NombreVector = NombreVector;
		this.magnitudX = magnitudX;
		this.magnitudY = magnitudY;
		this.magnitudZ = magnitudZ;
		CalcularDatosTheta();
		if (NombreVector== null || NombreVector.isEmpty()) {
			NombreVector= this.toStringReducido();
		}
		agregarFuncionesMatematicas();
	}

	/*
	 * Vector 3D con nombre y direcciones creado a partir de 3 de sus angulos y una
	 * magnitud
	 */
	public Vector3D(String NombreVector, double ThetaX, double ThetaY, double ThetaZ, double Magnitud) {
		super("Vector 3D","'V",new suma(), new resta(), new multiplicacion(), new division());
		this.NombreVector = NombreVector;
		this.ThetaX = ThetaX;
		this.ThetaY = ThetaY;
		this.ThetaZ = ThetaZ;

		this.magnitud = Magnitud;

		CalcularPlano();
		CalcularDatosMagnitud(Plano);
		if (NombreVector == null || NombreVector.isEmpty()) {
			NombreVector = this.toStringReducido();
		}
		agregarFuncionesMatematicas();
	}

	/* Vector 3D con nombre y variables vacias */
	public Vector3D(String NombreVector) {
		super("Vector 3D","'V",new suma(), new resta(), new multiplicacion(), new division());
		this.NombreVector = NombreVector;
		if (NombreVector == null || NombreVector.isEmpty()) {
			NombreVector = this.toStringReducido();
		}
		agregarFuncionesMatematicas();
	}

	/* */
	private double TransformarArango360(double angulo) {
		return angulo % 360;
	}

	/*
	 * Calcula los angulos si se tienen de entrada todas las magnitudes o
	 * dependiendo el plano en el cual se encuentre
	 */
	public void CalcularDatosTheta() {
		if (magnitudX != 0 || magnitudY != 0 || magnitudZ != 0) {
			magnitud = Math.sqrt(Math.pow(magnitudX, 2) + Math.pow(magnitudY, 2) + Math.pow(magnitudZ, 2));

			CalcularPlano();
			switch (Plano) {
				case 0:
					ThetaX = TransformarArango360(
							((magnitudX < 0 ? -1 : 1) * (Math.toDegrees(Math.acos(magnitudX / magnitud)))));
					ThetaY = TransformarArango360(
							((magnitudY < 0 ? -1 : 1) * (Math.toDegrees(Math.acos(magnitudY / magnitud)))));
					ThetaZ = TransformarArango360(
							((magnitudZ < 0 ? -1 : 1) * (Math.toDegrees(Math.acos(magnitudZ / magnitud)))));
					break;
				case 1:

					ThetaX = TransformarArango360(
							((magnitudY < 0 ? -1 : 1) * (Math.toDegrees(Math.acos(magnitudX / magnitud)))));
					ThetaY = TransformarArango360(
							((magnitudX < 0 ? -1 : 1) * (Math.toDegrees(Math.acos(magnitudY / magnitud)))));
					ThetaZ = TransformarArango360(
							((magnitudZ < 0 ? -1 : 1) * (Math.toDegrees(Math.acos(magnitudZ / magnitud)))));

					break;
				case 2:
					ThetaX = TransformarArango360(
							((magnitudZ < 0 ? -1 : 1) * (Math.toDegrees(Math.acos(magnitudX / magnitud)))));
					ThetaY = TransformarArango360(
							((magnitudY < 0 ? -1 : 1) * (Math.toDegrees(Math.acos(magnitudY / magnitud)))));
					ThetaZ = TransformarArango360(
							((magnitudX < 0 ? -1 : 1) * (Math.toDegrees(Math.acos(magnitudZ / magnitud)))));

					break;
				case 3:

					ThetaX = TransformarArango360(
							((magnitudX < 0 ? -1 : 1) * (Math.toDegrees(Math.acos(magnitudX / magnitud)))));
					ThetaY = TransformarArango360(
							((magnitudZ < 0 ? -1 : 1) * (Math.toDegrees(Math.acos(magnitudY / magnitud)))));
					ThetaZ = TransformarArango360(
							((magnitudY < 0 ? -1 : 1) * (Math.toDegrees(Math.acos(magnitudZ / magnitud)))));
					break;
			}

		}
	}

	/*
	 * Si alguna de las magnitudes es igual a 0 significa que es un plano 2D por
	 * tanto se manejara como tal
	 * Si se tienten todas las magnitudes se haran calculos de un plano 3D
	 */
	private void CalcularPlano() {

		/*
		 * Dimension:
		 * 0 = XYZ
		 * 1 = XY
		 * 2 = XZ
		 * 3 = YZ
		 */

		if (magnitudX != 0 && magnitudY != 0 && magnitudZ != 0) {
			Plano = 0;

		} else if (magnitudX != 0 && magnitudY != 0 && magnitudZ == 0) {
			Plano = 1;

		} else if (magnitudX != 0 && magnitudY == 0 && magnitudZ != 0) {
			Plano = 2;

		} else if (magnitudX == 0 && magnitudY != 0 && magnitudZ != 0) {

			Plano = 3;
		} else {
		}

	}

	/*
	 * Calcula las magnitudes si se tienen de entrada todos los angulos y una
	 * magnitud o dependiendo los angulos que se encuentren
	 */
	public void CalcularDatosMagnitud(int Plano) {

		/*
		 * Dimension:
		 * 0 = XYZ
		 * 1 = XY
		 * 2 = XZ
		 * 3 = YZ
		 */

		this.Plano = Plano; // Aqui se usa diferente porque necesito sacar que angulo usa el crear vector
		switch (Plano) {
			/// Debo calcular datos desde la magnitud puesto que solo me dan 1 o 2 angulos
			/// maximo, los demas los calculo yo con la
			// ecuacion cos^2 + cos^2 +cos^2 = 1
			/*
			 * case 0:
			 * if(ThetaX != 0 && ThetaZ != 0 ) {
			 * 
			 * ThetaY =
			 * Math.toDegrees(Math.acos(Math.sqrt(1-Math.pow(Math.cos(Math.toRadians(ThetaX)
			 * ),2)-Math.pow(Math.cos(Math.toRadians(ThetaZ)),2))));
			 * }
			 * 
			 * else if(ThetaX != 0 && ThetaY != 0 ) {
			 * ThetaZ =
			 * Math.toDegrees(Math.acos(Math.sqrt(1-Math.pow(Math.cos(Math.toRadians(ThetaX)
			 * ), 2)-Math.pow(Math.cos(Math.toRadians(ThetaY)),2))));
			 * 
			 * }
			 * else if(ThetaZ != 0 && ThetaY != 0 ) {
			 * ThetaX =
			 * Math.toDegrees(Math.acos(Math.sqrt(1-Math.pow(Math.cos(Math.toRadians(ThetaY)
			 * ), 2)+Math.pow(Math.cos(Math.toRadians(ThetaZ)), 2))));
			 * 
			 * }
			 * break;
			 */
			case 1:
				ThetaZ = 90;
				if (ThetaX != 0) {
					// ThetaY = TransformarArango360(360 + ((ThetaX > 180 ? -1 :
					// 1)*(Math.toDegrees(Math.acos(Math.sqrt(1-Math.pow(Math.cos(Math.toRadians(ThetaX)),
					// 2)))))));
					ThetaY = TransformarArango360(ThetaX - 90);
				} else {
					ThetaX = TransformarArango360(ThetaY + 90);
				}
				break;
			case 2:
				ThetaY = 90;
				if (ThetaX != 0) {// ThetaZ =
									// Math.toDegrees(Math.acos(Math.sqrt(1-Math.pow(Math.cos(Math.toRadians(ThetaX)),
									// 2))));}

					ThetaZ = TransformarArango360(ThetaX - 90);
				} else {
					ThetaX = TransformarArango360(ThetaZ + 90);
				}
				break;
			case 3:
				ThetaX = 90;
				if (ThetaY != 0) {
					// ThetaZ =
					// Math.toDegrees(Math.acos(Math.sqrt(1-Math.pow(Math.cos(Math.toRadians(ThetaY)),
					// 2))));
					ThetaZ = TransformarArango360(ThetaY + 90);

				} else {
					ThetaY = TransformarArango360(ThetaZ - 90);
				}
				break;

		}

		magnitudX = magnitud * Math.cos(Math.toRadians(ThetaX));
		magnitudY = magnitud * Math.cos(Math.toRadians(ThetaY));
		magnitudZ = magnitud * Math.cos(Math.toRadians(ThetaZ));
	}

	/*
	 * limpieza de angulos
	 */
	public void ResetTheta() {
		ThetaX = 0;
		ThetaY = 0;
		ThetaZ = 0;
	}

	/*
	 * limpieza de magnitudes
	 */
	public void ResetMagnitudes() {
		magnitudX = 0;
		magnitudY = 0;
		magnitudZ = 0;

	}

	/*
	 * bool vector es vacio
	 */
	public boolean IsEmpty() {

		return !(magnitud == 0 || (magnitudX != 0 || magnitudY != 0 || magnitudZ != 0));

	}

	/*
	 * comprobar que los angulos sean matematicamente correctos segun la formula
	 * Cos de thetaX^2 + thetaY^2 + thetaZ^2 = 1
	 */
	public boolean ComprobarAngulos() {
		double ThetaYAuxiliar = ThetaY != 0 ? ThetaY : 90;
		double ThetaXAuxiliar = ThetaX != 0 ? ThetaX : 90;
		double ThetaZAuxiliar = ThetaZ != 0 ? ThetaZ : 90;

		double resultadoComprobatorio = Math.pow(Math.cos(Math.toRadians(ThetaXAuxiliar)), 2)
				+ Math.pow(Math.cos(Math.toRadians(ThetaYAuxiliar)), 2)
				+ Math.pow(Math.cos(Math.toRadians(ThetaZAuxiliar)), 2);

		return resultadoComprobatorio <= 1.01;
	}

	/*
	 * retornar la descripcion del vector 3D a un tipo string legible a cualquier
	 * usuario
	 */
	public String toString() {
		return "Nombre del Vector: " + NombreVector + "\n" +
				"Magnitud: " + redondeo(magnitud, 4) + "\n" +
				"Magnitud X: " + redondeo(magnitudX, 4) + "\n" +
				"Magnitud Y: " + redondeo(magnitudY, 4) + "\n" +
				"Magnitud Z: " + redondeo(magnitudZ, 4) + "\n" +
				"Theta X: " + redondeo(ThetaX, 4) + "\n" +
				"Theta Y: " + redondeo(ThetaY, 4) + "\n" +
				"Theta Z: " + redondeo(ThetaZ, 4) + "\n";

	}

	/*
	 * retornar la descripcion del vector 3D a un tipo string legible a cualquier
	 * usuario de forma reducida
	 */
	public String toStringReducido() {
		return "Vector(" + redondeo(magnitudX, 3) + "," + redondeo(magnitudY, 3) + "," + redondeo(magnitudZ, 3) + ")";
	}

	public String getNombreVector() {
		return NombreVector;
	}

	public double getMagnitud() {
		return redondeo(magnitud, 3);
	}

	public double getMagnitudX() {
		return redondeo(magnitudX, 3);
	}

	public double getMagnitudY() {
		return redondeo(magnitudY, 3);
	}

	public double getMagnitudZ() {
		return redondeo(magnitudZ, 3);
	}

	public double getThetaX() {
		return redondeo(ThetaX, 3);
	}

	public double getThetaY() {
		return redondeo(ThetaY, 3);
	}

	public double getThetaZ() {
		return redondeo(ThetaZ, 3);
	}

	public void setNombreVector(String NombreVector) {
		this.NombreVector = NombreVector;
		if (NombreVector == null) {
			NombreVector = "Vector Sin Nombre";
		}

	}

	public void setMagnitudX(double magnitudX) {
		this.magnitudX = magnitudX;
	}

	public void setMagnitudY(double magnitudY) {
		this.magnitudY = magnitudY;
	}

	public void setMagnitudZ(double magnitudZ) {
		this.magnitudZ = magnitudZ;
	}

	public void setMagnitud(double magnitud) {
		this.magnitud = magnitud;
	}

	public void setThetaX(double thetaX) {
		ThetaX = thetaX;
	}

	public void setThetaY(double thetaY) {
		ThetaY = thetaY;
	}

	public void setThetaZ(double thetaZ) {
		ThetaZ = thetaZ;
	}

	@Override
    public UnidadMatematica crearUnidad(JFrame Padre) {
        CrearVector3D CrearVector3DJDialog = new CrearVector3D(Padre);
        return CrearVector3DJDialog.GetVector();
        
    }
}
