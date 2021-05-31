package ObjetosLogicos;

import java.io.Serializable;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import RecursosCustomizados.JPanel_Lienzo;
import VentanasProyecto.JPanelAlgebra;
import VentanasProyecto.JPanelNotas;
import VentanasProyecto.JPanelPizarron;
import VentanasProyecto.JPanelVisualizar;
import VentanasProyecto.JpanelVectores;

public  class CuadernoDeApuntesBaseLogica implements Serializable {
	 String NombreDelApunte = "No Especificado";
	 String Autor;
	 LocalDateTime FechaDeCreacion = LocalDateTime.now();  
	 
	public JPanelAlgebra Algebra ;
	public JPanelNotas Notas ;
	public JPanelVisualizar Visualizar;
	public JPanelPizarron Pizarra;
	public JpanelVectores Vectores;
	 
	
	
	public CuadernoDeApuntesBaseLogica(String Titulo,String Autor) {
		// TODO Auto-generated constructor stub
		NombreDelApunte = Titulo;
		this.Autor = Autor;
		Algebra = new JPanelAlgebra();
		Pizarra = new JPanelPizarron();
		Vectores = new JpanelVectores();
		Notas = new JPanelNotas();
		Visualizar = new JPanelVisualizar();

	}
	
	public  String getNombreDelApunte() {
		return NombreDelApunte;
	}
	public  String getFechaDeCreacion() {
		return FechaDeCreacion.toString();
	}
	public  String getAutor() {
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

	public JpanelVectores getVectores() {
		return Vectores;
	}

	public void setVectores(JpanelVectores vectores) {
		Vectores = vectores;
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
