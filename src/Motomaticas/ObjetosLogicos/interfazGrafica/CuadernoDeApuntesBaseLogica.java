package Motomaticas.ObjetosLogicos.interfazGrafica;

import java.io.Serializable;
import java.time.LocalDateTime;
import Motomaticas.VentanasProyecto.JPanelProcedimientoMatematico;
import Motomaticas.VentanasProyecto.JPanelNotas;
import Motomaticas.VentanasProyecto.JPanelPizarron;
import Motomaticas.VentanasProyecto.JPanelVisualizar;
import Motomaticas.VentanasProyecto.JpanelOperaciones;

/*
 * Representacion de un archivo tipo cuaderno de apuntes 
 * viene incluido con sus operacioens, notas, visualizacion grafica, pizarra, etc...
 */
public class CuadernoDeApuntesBaseLogica implements Serializable {
	String NombreDelApunte = "No Especificado";
	String Autor;
	LocalDateTime FechaDeCreacion = LocalDateTime.now();

	public JPanelProcedimientoMatematico Matematico;
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
		Matematico = new JPanelProcedimientoMatematico();
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

	public JPanelProcedimientoMatematico getMatematico() {
		return Matematico;
	}

	public void setMatematico(JPanelProcedimientoMatematico Matematico) {
		Matematico = Matematico;
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
		this.Operaciones = Operaciones;
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
