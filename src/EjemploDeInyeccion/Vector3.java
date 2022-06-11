package EjemploDeInyeccion;

import java.lang.Math;

import ObjetosLogicos.motorMatematico.ObjetoAlgebraico;
import ValoresDefault.Constantes;;

public class Vector3 extends ObjetoAlgebraico {
	private String Nombre;

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

	/*
	 * Vector 3D con nombre y direcciones creado a partir de 3 magnitudes en sus
	 * ejes
	 */
	public Vector3(String Nombre, double magnitudX, double magnitudY, double magnitudZ) {
		super(Constantes.TipoObjetoAlgebraico.Vector);
		this.Nombre = Nombre;
		this.magnitudX = magnitudX;
		this.magnitudY = magnitudY;
		this.magnitudZ = magnitudZ;
		CalcularDatosTheta();
		if (Nombre == null || Nombre.isEmpty()) {
			Nombre = this.toStringReducido();
		}
	}

	/*
	 * Vector 3D con nombre y direcciones creado a partir de 3 de sus angulos y una
	 * magnitud
	 */
	public Vector3(String Nombre, double ThetaX, double ThetaY, double ThetaZ, double Magnitud) {
		super(Constantes.TipoObjetoAlgebraico.Vector);
		this.Nombre = Nombre;
		this.ThetaX = ThetaX;
		this.ThetaY = ThetaY;
		this.ThetaZ = ThetaZ;

		this.magnitud = Magnitud;

		CalcularPlano();
		CalcularDatosMagnitud(Plano);
		if (Nombre == null || Nombre.isEmpty()) {
			Nombre = this.toStringReducido();
		}
	}

	/* Vector 3D con nombre y variables vacias */
	public Vector3(String Nombre) {
		super(Constantes.TipoObjetoAlgebraico.Vector);
		this.Nombre = Nombre;
		if (Nombre == null || Nombre.isEmpty()) {
			Nombre = this.toStringReducido();
		}
	}

	/* */
	private double TransformarArango360(double angulo) {
		return angulo % 360;
	}

	/*
	 * Calcula los angulos si se tienen de entrada todas las magnitudes o dependiendo el plano en el cual se encuentre
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
	 * Si alguna de las magnitudes es igual a 0 significa que es un plano 2D por tanto se manejara como tal 
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
	 * Calcula las magnitudes si se tienen de entrada todos los angulos y una magnitud o dependiendo los angulos que se encuentren
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
	 * retornar la descripcion del vector 3D a un tipo string legible a cualquier usuario
	 */
	public String toString() {
		return "Nombre del Vector: " + Nombre + "\n" +
				"Magnitud: " + redondeo(magnitud, 4) + "\n" +
				"Magnitud X: " + redondeo(magnitudX, 4) + "\n" +
				"Magnitud Y: " + redondeo(magnitudY, 4) + "\n" +
				"Magnitud Z: " + redondeo(magnitudZ, 4) + "\n" +
				"Theta X: " + redondeo(ThetaX, 4) + "\n" +
				"Theta Y: " + redondeo(ThetaY, 4) + "\n" +
				"Theta Z: " + redondeo(ThetaZ, 4) + "\n";

	}

		/*
	 * retornar la descripcion del vector 3D a un tipo string legible a cualquier usuario de forma reducida
	 */
	public String toStringReducido() {
		return "Vector(" + redondeo(magnitudX, 3) + "," + redondeo(magnitudY, 3) + "," + redondeo(magnitudZ, 3) + ")";
	}

	
	public String getNombre() {
		return Nombre;
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

	public void setNombre(String nombre) {
		Nombre = nombre;
		if (Nombre == null) {
			Nombre = "Vector Sin Nombre";
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

}
