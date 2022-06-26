package Motomaticas.ObjetosLogicos.interfazGrafica;

import java.io.Serializable;
import java.time.LocalDateTime;
import Motomaticas.VentanasProyecto.JPanelProcedimientoMatematico;
import Motomaticas.configuracionProyecto;
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

	private JPanelProcedimientoMatematico OperacionesMatematicasExplicadas;
	private JPanelNotas Notas;
	private JPanelVisualizar Visualizar;
	private JPanelPizarron Pizarra;
	private JpanelOperaciones operacionesMatematicasProceso;//

	public void setMatematico(JPanelProcedimientoMatematico OperacionesMatematicasExplicadas) {
		this.OperacionesMatematicasExplicadas = OperacionesMatematicasExplicadas;
	}
	public void setNotas(JPanelNotas Notas) {
		this.Notas = Notas;
	}
	public void setVisualizar(JPanelVisualizar Visualizar) {
		this.Visualizar = Visualizar;
	}
	public void setPizarra(JPanelPizarron Pizarra) {
		this.Pizarra = Pizarra;
	}
	public void setOperaciones(JpanelOperaciones operacionesMatematicasProceso) {
		this.operacionesMatematicasProceso = operacionesMatematicasProceso;
	}

	public JPanelProcedimientoMatematico getMatematico() {
		return this.OperacionesMatematicasExplicadas;
	}


	public JPanelNotas getNotas() {
		return this.Notas;
	}


	public JPanelVisualizar getVisualizar() {
		return this.Visualizar;
	}


	public JPanelPizarron getPizarra() {
		return this.Pizarra;
	}


	public JpanelOperaciones getOperaciones() {
		return this.operacionesMatematicasProceso;
	}

	/*
	 * creacion de un nuevo apunte cada que se decide cargar desde la computadora o
	 * crear uno en blanco
	 */
	public CuadernoDeApuntesBaseLogica(String Titulo, String Autor) {
		// TODO Auto-generated constructor stub
		NombreDelApunte = Titulo;
		this.Autor = Autor;
		activarProcedimientoMatematico();
		activarNotas();
		activarOperaciones();
		activarPizarra();
		activarVisualizar();
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
	public void activarProcedimientoMatematico() {
		if(configuracionProyecto.isPanelProcesoExplicadoMatematicoActivado()) this.OperacionesMatematicasExplicadas = new JPanelProcedimientoMatematico();
		else this.OperacionesMatematicasExplicadas  = null;
	}

	public void activarPizarra() {
		if(configuracionProyecto.isPanelPizzarraoActivado()) this.Pizarra = new JPanelPizarron();
		else this.Pizarra  = null;

	}

	public void activarOperaciones() {
		if(configuracionProyecto.isPanelOperacionesActivado()) this.operacionesMatematicasProceso = new JpanelOperaciones();
		else this.operacionesMatematicasProceso  = null;

	}
	public void activarNotas() {
		if(configuracionProyecto.isPanelNotasActivado()) this.Notas = new JPanelNotas();
		else this.Notas  = null;

	}

	public void activarVisualizar() {
		if(configuracionProyecto.isPanelVisualizarActivado()) this.Visualizar = new JPanelVisualizar();
		else this.Visualizar  = null;

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




}
