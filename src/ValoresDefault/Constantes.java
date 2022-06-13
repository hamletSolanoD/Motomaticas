package ValoresDefault;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;

import RecursosCustomizados.MensajeError;

public interface Constantes {

	public final static Color DetallesColor = new Color(230, 57, 70);
	public final static Color DetallesSegundoColor = new Color(29, 53, 87);
	public final static Color PrincipalColor = new Color(241, 250, 238);
	public final static Color SecundarioColor = new Color(196, 231, 232);
	public final static Color TerciarioColor = new Color(69, 123, 157);

	public static int PantallaOrdenadorX = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static int PantallaOrdenadorY = Toolkit.getDefaultToolkit().getScreenSize().height;

	public static Font botones = CrearFuente(PantallaOrdenadorX / 80, 2);
	public static Font textoNormal = CrearFuente(PantallaOrdenadorX / 80, 1);
	public static Font Titulos = CrearFuente(PantallaOrdenadorX / 40, 3);

	public static Font CrearFuente(float Tamanio, int TipoLetra) {

		try {

			switch (TipoLetra) {
				case 1:
					return (Font.createFont(Font.TRUETYPE_FONT, Thread.currentThread().getContextClassLoader()
							.getResourceAsStream("Montserrat-ExtraLight.ttf"))).deriveFont(Tamanio);
				case 2:
					return (Font
							.createFont(Font.TRUETYPE_FONT,
									Thread.currentThread().getContextClassLoader()
											.getResourceAsStream("Montserrat-ExtraLightItalic.ttf")))
							.deriveFont(Tamanio);
				case 3:
					return (Font.createFont(Font.TRUETYPE_FONT,
							Thread.currentThread().getContextClassLoader().getResourceAsStream("Montserrat-Bold.ttf")))
							.deriveFont(Tamanio);
			}
		} catch (IOException | FontFormatException e) {
			// Handle exception
		}
		return null;
	}

	public static JButton BotonRedondeado(String n, Color BG, Color FG) {
		JButton okButton = new JButton(n);
		okButton.setFont(Constantes.botones);
		okButton.setBackground(BG);
		okButton.setForeground(FG);
		okButton.setOpaque(false);
		okButton.setBorder(new LineBorder(BG, 10, true));
		return okButton;
	}

	public static JButton BotonCuadrado(String str, Color BG, Color FG) {
		JButton okButton = new JButton(str);
		okButton.setFont(Constantes.botones);
		okButton.setBackground(BG);
		okButton.setForeground(FG);
		return okButton;
	}

	public static Color Transparente = new Color(0, 0, 0, 0);
	public static Color Fondo = new Color(170, 177, 176);
	public static Color Fondo2 = new Color(170, 17, 176);
	public static Color Fondo3 = new Color(170, 177, 16);

	public static Color ColorTexto = new Color(70, 70, 250);

	public Color ColorBotonSeleccionado = new Color(.35f, .835f, .576f);

	/*
	 * Comprueba que las entradas de textFields sean validas como lo pide
	 */
	public static double ComprobarEntradas(DocumentEvent f, JFrame Padre) {
		boolean palabra = false;
		try {
			String documento = f.getDocument().getText(0, f.getDocument().getLength());
			for (int e = 0; e <= documento.length() - 1; e++) {
				switch (documento.charAt(e)) {
					case '1':
					case '2':
					case '3':
					case '4':
					case '5':
					case '6':
					case '7':
					case '8':
					case '9':
					case '0':
					case '-':
					case '.':
						palabra = false;
						break;
					default:
						palabra = true;
						break;
				}
				if (palabra == true)
					break;
			}

			if (!palabra) {

				return Double.parseDouble(documento);
			} else {
				throw new Exception("Caracter Invalido");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			if (e1.getMessage().equals("Caracter Invalido")) {
				new MensajeError(Padre, "Entrada Invalida");

			}
			return 0;
		}

	}

	/* objetos algebraicos que el motor los reconoce como tal */
	public enum TipoObjetoAlgebraico {
		Unidad, Operacion

	}

	/*
	 * errores matematicos estandar que sirven como homologos para mostrar en la
	 * interfaz final
	 */
	public enum TipoDeErrorAlgebraico {
		ParentesisAbiertos("Parentesis Abiertos"), ResultadoNulo("Resultado Nulo"), EntradaInvalida("Entrada Invalida"),
		ErrorMatematico("Error Matematico");

		String ErrorString;

		TipoDeErrorAlgebraico(String Error) {
			ErrorString = Error;
		}

		public String toString() {
			return ErrorString;
		}
	}

	/*
	 * public class Ilustracion{
	 * String Identificador;
	 * Image Imagen;
	 * public Ilustracion(Image Imagen) {
	 * Identificador = UUID.randomUUID().toString();
	 * this.Imagen = Imagen;
	 * }
	 * public String getIdentificador() {
	 * return Identificador;
	 * }
	 * 
	 * public Image getImagen() {
	 * return Imagen;
	 * }
	 * }
	 */


	 /*
	  * Texto resultado para representar en palabras simples un proceso matematico
	  */
	public class Parrafo implements Serializable {
		String Identificador;
		String Texto;

		public Parrafo(String Texto) {
			Identificador = UUID.randomUUID().toString();
			this.Texto = Texto;
		}

		public String getIdentificador() {
			return Identificador;
		}

		public String getTexto() {
			return Texto;
		}
	}


	/*
	 * Apunte final de la transaccion o resultado de la operacion general terminada
	 */
	public class Apunte implements Serializable {
		private StringBuffer Titulo = new StringBuffer("");
		private ArrayList<Parrafo> Parrafos = new ArrayList<Parrafo>();
		// private ArrayList<Ilustracion> Imagenes = new ArrayList<Ilustracion>();
		private ArrayList<String> OrdenDeAcomodo = new ArrayList<String>();

		public Apunte(String Titulo) {
			this.Titulo.append(Titulo);
		}

		public void AgregarParrafo(Parrafo parrafoNuevo) {
			Parrafos.add(parrafoNuevo);
			OrdenDeAcomodo.add(parrafoNuevo.getIdentificador());
		}

		public void EliminarParrafo(Parrafo parrafo) {
			Parrafos.remove(parrafo);
			OrdenDeAcomodo.remove(parrafo.getIdentificador());
		}

		public String toString() {
			String Cadena = Titulo + "\n\n";
			for (Parrafo i : Parrafos) {
				Cadena = Cadena + i.getTexto() + "\n";
			}
			return Cadena;
		}

		public String getTitulo() {

			return Titulo.toString();
		}

		public String getTituloAdaptado() {

			if (Titulo.length() > 40) {
				return new StringBuffer(Titulo).replace(39, Titulo.length(), "...").toString();
			}
			return Titulo.toString();
		}

	}



}
