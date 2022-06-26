package Motomaticas.ObjetosLogicos.motorMatematico;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import java.util.concurrent.CopyOnWriteArrayList;

import org.w3c.dom.events.EventException;

import Motomaticas.ObjetosLogicos.motorMatematico.operaciones.OperacionMatematica;
import Motomaticas.ObjetosLogicos.motorMatematico.operaciones.operacionesMatematicasGenericasInterface;
import Motomaticas.ObjetosLogicos.motorMatematico.variables.UnidadMatematica;
import Motomaticas.ValoresDefault.Constantes;
import Motomaticas.ValoresDefault.Constantes.TipoDeErrorMatematico;
import Motomaticas.ValoresDefault.Constantes.TipoObjetoMatematico;

public class OperacionGeneral implements Serializable {

	private List<ObjetoMatematico> ObjetosMatematicos = new LinkedList<ObjetoMatematico>();
	private List<ObjetoMatematico> ObjetosMatematicos_PrimerOrden = new LinkedList<ObjetoMatematico>();
	private List<ObjetoMatematico> ObjetosMatematicos_SegundoOrden = new LinkedList<ObjetoMatematico>();
	private List<ObjetoMatematico> ObjetosMatematicos_TercerOrden = new LinkedList<ObjetoMatematico>();
	private List<ObjetoMatematico> ObjetosMatematicos_CuartoOrden = new LinkedList<ObjetoMatematico>();

	private ObjetoMatematico Resultado = null;
	private TipoDeErrorMatematico Error = null;

	public OperacionGeneral(List<ObjetoMatematico> sublistaDeObjetos) {
		this.ObjetosMatematicos = sublistaDeObjetos;

	}

	public OperacionGeneral() {
	}

	public List<ObjetoMatematico> getObjetosMatematicos() {
		return ObjetosMatematicos;
	}

	private List<ObjetoMatematico> SubdividirOperacion(List<ObjetoMatematico> ListaOriginal,
			ObjetoMatematico PrimerObjeto, ObjetoMatematico UltimoObjeto) {

		List<ObjetoMatematico> SubLista = new CopyOnWriteArrayList<ObjetoMatematico>(
				(ListaOriginal.subList(ListaOriginal.indexOf(PrimerObjeto) + 1, ListaOriginal.indexOf(UltimoObjeto))));

		Iterator<ObjetoMatematico> IteradorDeLista = ListaOriginal.iterator();

		while (IteradorDeLista.hasNext()) {
			ObjetoMatematico ObjetoABorrar = (ObjetoMatematico) IteradorDeLista.next();
			if (ObjetoABorrar.equals(PrimerObjeto) || ObjetoABorrar.equals(UltimoObjeto)
					|| SubLista.contains(ObjetoABorrar)) {// Iterador para un correcto eliminacion
				ListaOriginal.remove(ObjetoABorrar);
			}
		}

		return SubLista;

	}

	private ObjetoMatematico BuscarParentesisCierre(ObjetoMatematico Inicio) {
		int NumeroDeParentesis = 0;

		for (int Index = ObjetosMatematicos.indexOf(Inicio); Index < ObjetosMatematicos.size(); Index++) {
			if (ObjetosMatematicos.get(Index).TipoDeObjetoMatematico == TipoObjetoMatematico.Operacion) {
				OperacionMatematica operacion = (OperacionMatematica) ObjetosMatematicos.get(Index);
				if (operacion.getConLlave()) {
					if (operacion.getNombreObjetoMatematico()
							.equals((new operacionesMatematicasGenericasInterface.llaveDerecha())
									.getNombreObjetoMatematico()))
						NumeroDeParentesis--;

					else
						NumeroDeParentesis++;

				}

				if (NumeroDeParentesis == 0) {
					return ObjetosMatematicos.get(Index);
				}
			}
		}

		return null;

	}

	private TipoDeErrorMatematico ResolverPrimerOrden() {

		Repetidor: while (ObjetosMatematicos.size() > 0) {// bucle externo para recalcular el iterador y el for
															// rellamando el repetidor

			for (Iterator<ObjetoMatematico> IteradorObjetosMatematicos0 = ObjetosMatematicos
					.iterator(); IteradorObjetosMatematicos0.hasNext();) {

				ObjetoMatematico ObjetoActual = (ObjetoMatematico) IteradorObjetosMatematicos0.next();

				switch (ObjetoActual.getTipoDeObjetoMatematico()) { /// IDENTIFICAR EL TIPO DE OBJETO ACTUAL
					case Unidad:
						ObjetosMatematicos_PrimerOrden.add(ObjetoActual);// Pasar al siguiente Orden
						ObjetosMatematicos.remove(ObjetoActual);
						break;
					case Operacion:
						if (((OperacionMatematica) ObjetoActual).getConLlave()) {
							ObjetoMatematico ParentesisCierre = BuscarParentesisCierre(ObjetoActual);
							if (ParentesisCierre != null) {
								OperacionGeneral subOperacion = new OperacionGeneral(
										SubdividirOperacion(ObjetosMatematicos, ObjetoActual, ParentesisCierre));
								TipoDeErrorMatematico PosibleError = subOperacion.CalcularOperacion();
								if (PosibleError == null) {
									ObjetosMatematicos_PrimerOrden.add(((OperacionMatematica) ObjetoActual)
											.calcularOperacion((UnidadMatematica) subOperacion.getResultado()));
									continue Repetidor;
								} else {/// si si tiene regresar el error
									return PosibleError;
								}

							} else {
								return TipoDeErrorMatematico.ParentesisAbiertos;
							}
						} else {
							ObjetosMatematicos_PrimerOrden.add(ObjetoActual);// Pasar al siguiente Orden
							ObjetosMatematicos.remove(ObjetoActual);
						}
						break;
				}
			}
		}
		return null;

	}

	private TipoDeErrorMatematico ResolverSegundoOrden(List<ObjetoMatematico> ListaParResolverSegundoOrden,
			List<ObjetoMatematico> listaDeResultado[], int prioridad) {

		List<ObjetoMatematico> listaAuxiliar = new CopyOnWriteArrayList<ObjetoMatematico>(ListaParResolverSegundoOrden);
		boolean OperadorEncontrado = false;
		TipoDeErrorMatematico posibleError = null;

		for (int Index = 0; Index < ListaParResolverSegundoOrden.size(); Index++) {
			if (ListaParResolverSegundoOrden.get(Index).getTipoDeObjetoMatematico() == TipoObjetoMatematico.Operacion
					&& ((OperacionMatematica) ListaParResolverSegundoOrden.get(Index))
							.getPrioridadDeOperacion() == prioridad) {
				try {

					ObjetoMatematico objetoMatematicoIzquierdo = ListaParResolverSegundoOrden.get(Index - 1);
					ObjetoMatematico objetoMatematicoDerecho = ListaParResolverSegundoOrden.get(Index + 1);

					// validacion que a los operadores no esten juntos sino sea operando operador
					// operando (4 + 2) no (1 ++ 2)
					if (objetoMatematicoIzquierdo.getTipoDeObjetoMatematico() != TipoObjetoMatematico.Operacion &&
							objetoMatematicoDerecho.getTipoDeObjetoMatematico() != TipoObjetoMatematico.Operacion) {
						/////////// Si el try no arroja nada de operador incompleto o tampoco de
						/////////// operador repitido, avanza
						String[] OperandosResolver = {
								((UnidadMatematica) objetoMatematicoIzquierdo).getNombreObjetoMatematico(),
								((UnidadMatematica) objetoMatematicoDerecho).getNombreObjetoMatematico()
						};
						String[] OperandosResolverReves = {
								((UnidadMatematica) objetoMatematicoDerecho).getNombreObjetoMatematico(),
								((UnidadMatematica) objetoMatematicoIzquierdo).getNombreObjetoMatematico()
						};
						if (((OperacionMatematica) ListaParResolverSegundoOrden.get(Index))
								.comprobarOperandosIguales(OperandosResolver) ||
								(((OperacionMatematica) ListaParResolverSegundoOrden.get(Index)).getIsConmutativa()
										&& ((OperacionMatematica) ListaParResolverSegundoOrden.get(Index))
												.comprobarOperandosIguales(OperandosResolverReves))) {
							listaAuxiliar.clear();
							listaAuxiliar.addAll(ListaParResolverSegundoOrden.subList(0, Index - 1));

							if (((OperacionMatematica) ListaParResolverSegundoOrden.get(Index))
									.comprobarOperandosIguales(OperandosResolver)) {
								listaAuxiliar.add(((OperacionMatematica) ListaParResolverSegundoOrden.get(Index))
										.calcularOperacion((UnidadMatematica) objetoMatematicoIzquierdo,
												(UnidadMatematica) objetoMatematicoDerecho));
							} else {
								listaAuxiliar.add(((OperacionMatematica) ListaParResolverSegundoOrden.get(Index))
										.calcularOperacion((UnidadMatematica) objetoMatematicoDerecho,
												(UnidadMatematica) objetoMatematicoIzquierdo));
							}

							try {
								listaAuxiliar.addAll(ListaParResolverSegundoOrden.subList(Index + 2,
										ListaParResolverSegundoOrden.size()));
							} catch (EventException e) {
								e.printStackTrace();
							}
							listaAuxiliar.removeIf(n -> (n == null));// por si se agrego algun valor nulo
							OperadorEncontrado = true;// Termina el bucle for
						} else {// error
							return Constantes.TipoDeErrorMatematico.ErrorMatematico;
						}
					} else {
						new IndexOutOfBoundsException("operador repetido");
					}
				} catch (IndexOutOfBoundsException i) {
					/// error -1 por objeto vacio
					// -3 por operador repetido
					System.err.print("Error de sintaxis " + i.getMessage());
					return Constantes.TipoDeErrorMatematico.EntradaInvalida;
				}

			}
			if (OperadorEncontrado) {
				break; // Terminar la busqueda del for
			}
		}
		if (OperadorEncontrado == false) {
			listaDeResultado[0] = listaAuxiliar;
		} else {
			posibleError = ResolverSegundoOrden(listaAuxiliar, listaDeResultado, prioridad);
		}
		return posibleError;
	}

	private TipoDeErrorMatematico ResolverOrdenN(List<ObjetoMatematico> ListaDeOrdenPrevia,
			List<ObjetoMatematico> ListaDeOrdenN[], int prioridad) {

		List<ObjetoMatematico>[] ListaArraySegundoOrden = new List[1];// puesto como array para poder usarlo como un
																		// apuntador y
		// asignarle su valor aun durante las iteaciones recursivas
		ListaArraySegundoOrden[0] = new CopyOnWriteArrayList<ObjetoMatematico>();

		TipoDeErrorMatematico posibleError = ResolverSegundoOrden(ListaDeOrdenPrevia, ListaArraySegundoOrden,
				prioridad);

		ListaDeOrdenN[0].clear();
		ListaDeOrdenN[0].addAll(ListaArraySegundoOrden[0]);

		return posibleError;
	}

	

	public void HardReset() {
		Resultado = null;
		ObjetosMatematicos = new CopyOnWriteArrayList<ObjetoMatematico>();
		ObjetosMatematicos_PrimerOrden = new CopyOnWriteArrayList<ObjetoMatematico>();
		ObjetosMatematicos_SegundoOrden = new CopyOnWriteArrayList<ObjetoMatematico>();

	}

	private void reset() {
		Resultado = null;
		Error = null;
		// ObjetosMatematicos = new CopyOnWriteArrayList<ObjetoMatematico>();
		ObjetosMatematicos_PrimerOrden = new CopyOnWriteArrayList<ObjetoMatematico>();
		ObjetosMatematicos_SegundoOrden = new CopyOnWriteArrayList<ObjetoMatematico>();
	}

	public TipoDeErrorMatematico CalcularOperacion() {
		reset();

		TipoDeErrorMatematico PrimerOrdenError = ResolverPrimerOrden();
		if (PrimerOrdenError != null) {
			Error = PrimerOrdenError;
			return PrimerOrdenError;
		}

		List<ObjetoMatematico>[] punteroDeLista = new List[1];
		punteroDeLista[0] = ObjetosMatematicos_SegundoOrden;
		TipoDeErrorMatematico SegundoOrdenError = ResolverOrdenN(ObjetosMatematicos_PrimerOrden, punteroDeLista, 3);
		if (SegundoOrdenError != null) {
			Error = SegundoOrdenError;
			return SegundoOrdenError;
		}

		List<ObjetoMatematico>[] punteroDeLista2 = new List[1];
		punteroDeLista2[0] = ObjetosMatematicos_TercerOrden;
		TipoDeErrorMatematico TercerOrden = ResolverOrdenN(ObjetosMatematicos_SegundoOrden, punteroDeLista2, 2);
		if (TercerOrden != null) {
			Error = TercerOrden;
			return TercerOrden;
		}

		List<ObjetoMatematico>[] punteroDeLista3 = new List[1];
		punteroDeLista3[0] = ObjetosMatematicos_CuartoOrden;
		TipoDeErrorMatematico CuartoOrden = ResolverOrdenN(ObjetosMatematicos_TercerOrden, punteroDeLista3, 1);
		if (CuartoOrden != null) {
			Error = CuartoOrden;
			return CuartoOrden;
		} else {
			try{
			Resultado = ObjetosMatematicos_CuartoOrden.get(0);// respuesta final de la sumatoria
			}catch(Throwable e){
				//System.err.println(e);

			}

		}

		if (Resultado == null) {
			Error = TipoDeErrorMatematico.ResultadoNulo;

			return TipoDeErrorMatematico.ResultadoNulo;
		}

		return null;
	}

	public ObjetoMatematico getResultado() {
		return Resultado;
	}

	public TipoDeErrorMatematico getError() {
		return Error;
	}

}
