package ObjetosLogicos.interfazGrafica;

import java.io.Serializable;
import java.time.LocalDateTime;
import VentanasProyecto.JPanelAlgebra;
import VentanasProyecto.JPanelNotas;
import VentanasProyecto.JPanelPizarron;
import VentanasProyecto.JPanelVisualizar;
import VentanasProyecto.JpanelOperaciones;

/*
 * Representacion de un archivo tipo cuaderno de apuntes 
 * viene incluido con sus operacioens, notas, visualizacion grafica, pizarra, etc...
 */
public class CuadernoDeApuntesBaseLogica implements Serializable {
	String NombreDelApunte = "No Especificado";
	String Autor;
	LocalDateTime FechaDeCreacion = LocalDateTime.now();

	public JPanelAlgebra Algebra;
	public JPanelNotas Notas;
	public JPanelVisualizar Visualizar;
	public JPanelPizarron Pizarra;
	public JpanelOperaciones Operaciones;

	/*
	 * creacion de un nuevo apunte cada que se decide cargar desde la computadora o
	 * crear uno en blanco
	 */
	public CuadernoDeApuntesBaseLogica(String Titulo, String Autor) {
		// TODO Auto-generated constructor stub
		NombreDelApunte = Titulo;
		this.Autor = Autor;
		Algebra = new JPanelAlgebra();
		Pizarra = new JPanelPizarron();
		Operaciones = new JpanelOperaciones();
		Notas = new JPanelNotas();
		Visualizar = new JPanelVisualizar();

	}

	public String getNombreDelApunte() {
		return NombreDelApunte;
	}

	public String getFechaDeCreacion() {
		return FechaDeCreacion.toString();
	}

	public String getAutor() {
		return Autor;
	}

	public JPanelAlgebra getAlgebra() {
		return Algebra;
	}

	public void setAlgebra(JPanelAlgebra algebra) {
		Algebra = algebra;
	}

	public JPanelPizarron getPizarra() {
		return Pizarra;
	}

	public void setPizarra(JPanelPizarron pizarra) {
		Pizarra = pizarra;
	}

	public JpanelOperaciones getOperaciones() {
		return Operaciones;
	}

	public void setOperaciones(JpanelOperaciones Operaciones) {
		Operaciones = Operaciones;
	}

	public void setNombreDelApunte(String nombreDelApunte) {
		NombreDelApunte = nombreDelApunte;
	}

	public void setAutor(String autor) {
		Autor = autor;
	}

	public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
		FechaDeCreacion = fechaDeCreacion;
	}

	public JPanelNotas getNotas() {
		return Notas;
	}

	public void setNotas(JPanelNotas notas) {
		Notas = notas;
	}

	public JPanelVisualizar getVisualizar() {
		return Visualizar;
	}

	public void setVisualizar(JPanelVisualizar Visualizar) {
		this.Visualizar = Visualizar;
	}

}
