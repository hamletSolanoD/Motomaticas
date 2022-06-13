package ObjetosLogicos.motorMatematico;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import java.util.concurrent.CopyOnWriteArrayList;

import org.w3c.dom.events.EventException;

import ModulosImportados.Vector3D.Vector3D;
import ObjetosLogicos.motorMatematico.*;
import ObjetosLogicos.motorMatematico.operaciones.OperacionMatematica;
import ObjetosLogicos.motorMatematico.operaciones.operacionesMatematicasGenericasInterface;
import ObjetosLogicos.motorMatematico.variables.UnidadMatematica;
import ValoresDefault.Constantes;
import ValoresDefault.Constantes.TipoDeErrorAlgebraico;
import ValoresDefault.Constantes.TipoObjetoAlgebraico;

public class OperacionGeneral implements Serializable {

	private List<ObjetoAlgebraico> ObjetosAlgebraicos = new LinkedList<ObjetoAlgebraico>();
	private List<ObjetoAlgebraico> ObjetosAlgebraicos_PrimerOrden = new LinkedList<ObjetoAlgebraico>();
	private List<ObjetoAlgebraico> ObjetosAlgebraicos_SegundoOrden = new LinkedList<ObjetoAlgebraico>();

	private ObjetoAlgebraico Resultado = null;
	private TipoDeErrorAlgebraico Error = null;

	public OperacionGeneral(List<ObjetoAlgebraico> sublistaDeObjetos) {
		this.ObjetosAlgebraicos = sublistaDeObjetos;

	}

	public OperacionGeneral() {
	}

	public List<ObjetoAlgebraico> getObjetosAlgebraicos() {
		return ObjetosAlgebraicos;
	}

	private List<ObjetoAlgebraico> SubdividirOperacion(List<ObjetoAlgebraico> ListaOriginal,
			ObjetoAlgebraico PrimerObjeto, ObjetoAlgebraico UltimoObjeto) {

		List<ObjetoAlgebraico> SubLista = new CopyOnWriteArrayList<ObjetoAlgebraico>(
				(ListaOriginal.subList(ListaOriginal.indexOf(PrimerObjeto) + 1, ListaOriginal.indexOf(UltimoObjeto))));

		Iterator<ObjetoAlgebraico> IteradorDeLista = ListaOriginal.iterator();

		while (IteradorDeLista.hasNext()) {
			ObjetoAlgebraico ObjetoABorrar = (ObjetoAlgebraico) IteradorDeLista.next();
			if (ObjetoABorrar.equals(PrimerObjeto) || ObjetoABorrar.equals(UltimoObjeto)
					|| SubLista.contains(ObjetoABorrar)) {// Iterador para un correcto eliminacion
				ListaOriginal.remove(ObjetoABorrar);
			}
		}

		return SubLista;

	}

	private ObjetoAlgebraico BuscarParentesisCierre(ObjetoAlgebraico Inicio) {
		int NumeroDeParentesis = 0;

		for (int Index = ObjetosAlgebraicos.indexOf(Inicio); Index < ObjetosAlgebraicos.size(); Index++) {
			if (ObjetosAlgebraicos.get(Index).TipoDeObjetoAlgebraico == TipoObjetoAlgebraico.Operacion) {
				OperacionMatematica operacion = (OperacionMatematica) ObjetosAlgebraicos.get(Index);
				if (operacion.getConLlave()) {
					if (operacion.getNombre()
							.equals((new operacionesMatematicasGenericasInterface.llaveDerecha()).getNombre()))
						NumeroDeParentesis--;

					else
						NumeroDeParentesis++;

				}

				if (NumeroDeParentesis == 0) {
					return ObjetosAlgebraicos.get(Index);
				}
			}
		}

		return null;

	}

	private TipoDeErrorAlgebraico ResolverPrimerOrden() {

		Repetidor: while (ObjetosAlgebraicos.size() > 0) {// bucle externo para recalcular el iterador y el for
															// rellamando el repetidor

			for (Iterator<ObjetoAlgebraico> IteradorObjetosAlgebraicos0 = ObjetosAlgebraicos
					.iterator(); IteradorObjetosAlgebraicos0.hasNext();) {

				ObjetoAlgebraico ObjetoActual = (ObjetoAlgebraico) IteradorObjetosAlgebraicos0.next();

				switch (ObjetoActual.getTipoDeObjetoAlgebraico()) { /// IDENTIFICAR EL TIPO DE OBJETO ACTUAL
					case Unidad:
						ObjetosAlgebraicos_PrimerOrden.add(ObjetoActual);// Pasar al siguiente Orden
						ObjetosAlgebraicos.remove(ObjetoActual);
						break;
					case Operacion:
						if (((OperacionMatematica) ObjetoActual).getConLlave()) {
							ObjetoAlgebraico ParentesisCierre = BuscarParentesisCierre(ObjetoActual);
							if (ParentesisCierre != null) {
								OperacionGeneral subOperacion = new OperacionGeneral(
										SubdividirOperacion(ObjetosAlgebraicos, ObjetoActual, ParentesisCierre));
								TipoDeErrorAlgebraico PosibleError = subOperacion.CalcularOperacion();
								if (PosibleError == null) {
									ObjetosAlgebraicos_PrimerOrden.add(((OperacionMatematica) ObjetoActual)
											.calcularOperacion(subOperacion.getResultado()));
									continue Repetidor;
								} else {/// si si tiene regresar el error
									return PosibleError;
								}

							} else {
								return TipoDeErrorAlgebraico.ParentesisAbiertos;
							}
						} else {
							ObjetosAlgebraicos_PrimerOrden.add(ObjetoActual);// Pasar al siguiente Orden
							ObjetosAlgebraicos.remove(ObjetoActual);
						}
						break;
				}
			}
		}
		return null;

	}

	private TipoDeErrorAlgebraico ResolverSegundoOrden(List<ObjetoAlgebraico> ListaParResolverSegundoOrden,
			List<ObjetoAlgebraico> listaDeResultado[]) {

		List<ObjetoAlgebraico> listaAuxiliar = new CopyOnWriteArrayList<ObjetoAlgebraico>(ListaParResolverSegundoOrden);
		boolean OperadorEncontrado = false;
		TipoDeErrorAlgebraico posibleError = null;
		for (int Index = 0; Index < ListaParResolverSegundoOrden.size(); Index++) {
			if (ListaParResolverSegundoOrden.get(Index).getTipoDeObjetoAlgebraico() == TipoObjetoAlgebraico.Operacion) {
				try {
					ObjetoAlgebraico objetoAlgebraicoIzquierdo = ListaParResolverSegundoOrden.get(Index - 1);
					ObjetoAlgebraico objetoAlgebraicoDerecho = ListaParResolverSegundoOrden.get(Index + 1);

					// validacion que a los operadores no esten juntos sino sea operando operador
					// operando (4 + 2) no (1 ++ 2)
					if (objetoAlgebraicoIzquierdo.getTipoDeObjetoAlgebraico() != TipoObjetoAlgebraico.Operacion &&
							objetoAlgebraicoDerecho.getTipoDeObjetoAlgebraico() != TipoObjetoAlgebraico.Operacion) {
						/////////// Si el try no arroja nada de operador incompleto o tampoco de
						/////////// operador repitido, avanza
						String[] OperandosResolver = {
								((UnidadMatematica)objetoAlgebraicoIzquierdo).getNombre(),
								((UnidadMatematica)objetoAlgebraicoDerecho).getNombre()
						};
						if (((OperacionMatematica) ListaParResolverSegundoOrden.get(Index))
								.comprobarOperandosIguales(OperandosResolver)) {
							listaAuxiliar.clear();
							listaAuxiliar.addAll(ListaParResolverSegundoOrden.subList(0, Index - 1));
							listaAuxiliar.add(((OperacionMatematica) ListaParResolverSegundoOrden.get(Index))
									.calcularOperacion(objetoAlgebraicoIzquierdo,
									objetoAlgebraicoDerecho));
							try {
								listaAuxiliar.addAll(ListaParResolverSegundoOrden.subList(Index + 2,
										ListaParResolverSegundoOrden.size()));
							} catch (EventException e) {
								System.out.println("Lista mas pequenio de lo esperado");
							}
							listaAuxiliar.removeIf(n -> (n == null));// por si se agrego algun valor nulo
							OperadorEncontrado = true;// Termina el bucle for
						} else {// error
							return Constantes.TipoDeErrorAlgebraico.ErrorMatematico;
						}
					} else {
						new IndexOutOfBoundsException("operador repetido");
					}
				} catch (IndexOutOfBoundsException i) {
					/// error -1 por objeto vacio
					// -3 por operador repetido
					System.err.print("Error de sintaxis" + i.getMessage());
					return Constantes.TipoDeErrorAlgebraico.EntradaInvalida;
				}

			}
			if (OperadorEncontrado) {
				break; // Terminar la busqueda del for
			}
		}
		if (OperadorEncontrado == false) {
			listaDeResultado[0] = listaAuxiliar;
		} else {
			posibleError = ResolverSegundoOrden(listaAuxiliar, listaDeResultado);
		}
		return posibleError;
	}

	private TipoDeErrorAlgebraico ResolverSegundoOrden() {

		List<ObjetoAlgebraico>[] ListaArraySegundoOrden = new List[1];
		ListaArraySegundoOrden[0] = new CopyOnWriteArrayList<ObjetoAlgebraico>();
		TipoDeErrorAlgebraico posibleError = ResolverSegundoOrden(ObjetosAlgebraicos_PrimerOrden,
				ListaArraySegundoOrden);
		ObjetosAlgebraicos_SegundoOrden.clear();
		ObjetosAlgebraicos_SegundoOrden.addAll(ListaArraySegundoOrden[0]);
		return posibleError;
	}

	private TipoDeErrorAlgebraico ResolverTercerOrden() {

		ObjetoAlgebraico ObjetoUltimo = null;
		ObjetoAlgebraico ObjetoPenultimo = null;

		for (ObjetoAlgebraico ObjetoActual : ObjetosAlgebraicos_SegundoOrden) {
			if (ObjetoUltimo != null) {
				if (ObjetoActual.getTipoDeObjetoAlgebraico() == TipoObjetoAlgebraico.Unidad) { /// IDENTIFICAR EL TIPO DE OBJETO ACTUAL
						if (ObjetoUltimo.getTipoDeObjetoAlgebraico() == TipoObjetoAlgebraico.Operacion) {
								if (ObjetoPenultimo.getTipoDeObjetoAlgebraico() == TipoObjetoAlgebraico.Unidad) {
									Resultado = ((OperacionMatematica)ObjetoUltimo).calcularOperacion((UnidadMatematica) Resultado, (UnidadMatematica) ObjetoActual);
								} else {
									return TipoDeErrorAlgebraico.EntradaInvalida;
								}
						}
						break;
				}
			} else {
				if (ObjetoActual.getTipoDeObjetoAlgebraico() == TipoObjetoAlgebraico.Unidad) {
					Resultado = ObjetoActual;
				} else {
					return TipoDeErrorAlgebraico.EntradaInvalida;
				}

			}
			ObjetoPenultimo = ObjetoUltimo;
			ObjetoUltimo = ObjetoActual;

		}

		return null;

	}

	public void HardReset() {
		Resultado = null;
		ObjetosAlgebraicos = new CopyOnWriteArrayList<ObjetoAlgebraico>();
		ObjetosAlgebraicos_PrimerOrden = new CopyOnWriteArrayList<ObjetoAlgebraico>();
		ObjetosAlgebraicos_SegundoOrden = new CopyOnWriteArrayList<ObjetoAlgebraico>();

	}

	private void reset() {
		Resultado = null;
		Error = null;
		// ObjetosAlgebraicos = new CopyOnWriteArrayList<ObjetoAlgebraico>();
		ObjetosAlgebraicos_PrimerOrden = new CopyOnWriteArrayList<ObjetoAlgebraico>();
		ObjetosAlgebraicos_SegundoOrden = new CopyOnWriteArrayList<ObjetoAlgebraico>();
	}

	public TipoDeErrorAlgebraico CalcularOperacion() {
		reset();

		TipoDeErrorAlgebraico PrimerOrdenError = ResolverPrimerOrden();
		if (PrimerOrdenError != null) {
			Error = PrimerOrdenError;
			return PrimerOrdenError;
		}

		TipoDeErrorAlgebraico SegundoOrdenError = ResolverSegundoOrden();
		if (SegundoOrdenError != null) {
			Error = SegundoOrdenError;
			return SegundoOrdenError;
		}

		TipoDeErrorAlgebraico TercerOrden = ResolverTercerOrden();
		if (TercerOrden != null) {
			Error = TercerOrden;
			return TercerOrden;
		}

		if (Resultado == null) {
			Error = TipoDeErrorAlgebraico.ResultadoNulo;
			
			return TipoDeErrorAlgebraico.ResultadoNulo;
		}

		return null;
	}

	public ObjetoAlgebraico getResultado() {
		return Resultado;
	}

	public TipoDeErrorAlgebraico getError() {
		return Error;
	}

}
