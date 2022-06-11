package ObjetosLogicos.motorMatematico;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import java.util.concurrent.CopyOnWriteArrayList;

import org.w3c.dom.events.EventException;

import EjemploDeInyeccion.Vector3;
import ObjetosLogicos.motorMatematico.*;
import ObjetosLogicos.motorMatematico.operaciones.Operacion;
import ObjetosLogicos.motorMatematico.variables.UnidadAritmetica;
import ValoresDefault.Constantes;
import ValoresDefault.Constantes.TipoDeErrorAlgebraico;
import ValoresDefault.Constantes.TipoObjetoAlgebraico;
import ValoresDefault.Constantes.TipoOperacion;
public class OperacionGeneral implements Serializable{

	private  List<ObjetoAlgebraico> ObjetosAlgebraicos = new LinkedList<ObjetoAlgebraico>();
	private  List<ObjetoAlgebraico> ObjetosAlgebraicos_PrimerOrden = new LinkedList<ObjetoAlgebraico>();
	private  List<ObjetoAlgebraico> ObjetosAlgebraicos_SegundoOrden = new LinkedList<ObjetoAlgebraico>();
	
	private ObjetoAlgebraico Resultado = null ;
	private TipoDeErrorAlgebraico Error= null;
		
	
	
	public OperacionGeneral(List<ObjetoAlgebraico> sublistaDeObjetos) {
		this.ObjetosAlgebraicos = sublistaDeObjetos;
		
	}
	public OperacionGeneral() {	
	}

	public List<ObjetoAlgebraico> getObjetosAlgebraicos() {
		return ObjetosAlgebraicos;
	}
	
	private List<ObjetoAlgebraico> SubdividirOperacion(List<ObjetoAlgebraico> ListaOriginal,ObjetoAlgebraico PrimerObjeto, ObjetoAlgebraico UltimoObjeto) {
		
		
		List<ObjetoAlgebraico> SubLista =new CopyOnWriteArrayList<ObjetoAlgebraico>(
		(ListaOriginal.subList(ListaOriginal.indexOf(PrimerObjeto)+1,ListaOriginal.indexOf(UltimoObjeto))));	
		
		Iterator<ObjetoAlgebraico> IteradorDeLista = ListaOriginal.iterator();
		
		while(IteradorDeLista.hasNext()) {
			ObjetoAlgebraico ObjetoABorrar = (ObjetoAlgebraico)IteradorDeLista.next();
			if(ObjetoABorrar.equals(PrimerObjeto) ||  ObjetoABorrar.equals(UltimoObjeto) || SubLista.contains(ObjetoABorrar)) {// Iterador para un correcto eliminacion
				ListaOriginal.remove(ObjetoABorrar);
			}
		}

		return SubLista;

	}
	
	private ObjetoAlgebraico BuscarParentesisCierre(ObjetoAlgebraico Inicio) {
		int NumeroDeParentesis = 0;
		
		for(int Index =  ObjetosAlgebraicos.indexOf(Inicio); Index < ObjetosAlgebraicos.size(); Index++ ) {
			if(ObjetosAlgebraicos.get(Index).TipoDeObjetoAlgebraico == TipoObjetoAlgebraico.Operacion) {
                if(((Operacion)ObjetosAlgebraicos.get(Index)).getOperacion() == TipoOperacion.Parentesis_Izquierdo) {
					NumeroDeParentesis++;
				}	
                else if(((Operacion)ObjetosAlgebraicos.get(Index)).getOperacion() == TipoOperacion.Vector_Magnitud) {
					NumeroDeParentesis++;
				}	  
                else if(((Operacion)ObjetosAlgebraicos.get(Index)).getOperacion() == TipoOperacion.Vector_Unitario) {
					NumeroDeParentesis++;
				}	
                else if(((Operacion)ObjetosAlgebraicos.get(Index)).getOperacion() == TipoOperacion.Parentesis_Derecho) {

					NumeroDeParentesis--;
				}
                
                if(NumeroDeParentesis == 0) { 
                	return ObjetosAlgebraicos.get(Index);
                } 
			}
		}

		return null;
		
	
	}
	
	private TipoDeErrorAlgebraico ResolverPrimerOrden() {
		
        Repetidor : 
        while(ObjetosAlgebraicos.size() > 0){//bucle externo para recalcular el iterador y el for rellamando el repetidor
        	
		for(Iterator<ObjetoAlgebraico> IteradorObjetosAlgebraicos0 = ObjetosAlgebraicos.iterator(); IteradorObjetosAlgebraicos0.hasNext();) {
			
			ObjetoAlgebraico ObjetoActual = (ObjetoAlgebraico)IteradorObjetosAlgebraicos0.next();
			
		switch(ObjetoActual.getTipoDeObjetoAlgebraico()) { /// IDENTIFICAR EL TIPO DE OBJETO ACTUAL 
		case Unidad :  
			ObjetosAlgebraicos_PrimerOrden.add(ObjetoActual);// Pasar al siguiente Orden
			ObjetosAlgebraicos.remove(ObjetoActual);
			break;
		case Operacion:
			
			/////////////////////////////// OPERACION /////////////////////////////////////////
			switch(((Operacion)ObjetoActual).getOperacion()) {
			case Vector_Magnitud:   
			{
				ObjetoAlgebraico ParentesisCierre = BuscarParentesisCierre(ObjetoActual);
				if(ParentesisCierre != null) {
				OperacionGeneral subOperacion = new OperacionGeneral(SubdividirOperacion(ObjetosAlgebraicos,ObjetoActual, ParentesisCierre));
				
				TipoDeErrorAlgebraico PosibleError = subOperacion.CalcularOperacion();
				if(PosibleError == null) {
					ObjetosAlgebraicos_PrimerOrden.add(new UnidadAritmetica(Operacion.VecMagnitud(((Vector3)subOperacion.getResultado()))));
					
					continue Repetidor;
				}
				else {/// si si tiene regresar el error 
					return PosibleError;
				}
				
				}
				else {
					return TipoDeErrorAlgebraico.ParentesisAbiertos;
				}
				}
			case Vector_Unitario:
			{
				ObjetoAlgebraico ParentesisCierre = BuscarParentesisCierre(ObjetoActual);
				if(ParentesisCierre != null) {
				OperacionGeneral subOperacion = new OperacionGeneral(SubdividirOperacion(ObjetosAlgebraicos,ObjetoActual, ParentesisCierre));
				TipoDeErrorAlgebraico PosibleError = subOperacion.CalcularOperacion();
				if(PosibleError == null) {
					ObjetosAlgebraicos_PrimerOrden.add(Operacion.VecUnitario(((Vector3)subOperacion.getResultado())));
					continue Repetidor;
					}
				else {/// si si tiene regresar el error 
					return PosibleError;
				}
				
				}
				else {
					return TipoDeErrorAlgebraico.ParentesisAbiertos;
				}
				}
			case Producto_Escalar: 
			case Producto_Cruz: 
			case Multiplicar: 
			case Dividir: 
			case Restar: 
			case Sumar: 
				ObjetosAlgebraicos_PrimerOrden.add(ObjetoActual);// Pasar al siguiente Orden
				ObjetosAlgebraicos.remove(ObjetoActual);
			break;
			
			case Parentesis_Izquierdo:
			{
				
			ObjetoAlgebraico ParentesisCierre = BuscarParentesisCierre(ObjetoActual);
			if(ParentesisCierre != null) {
			OperacionGeneral subOperacion = new OperacionGeneral(SubdividirOperacion(ObjetosAlgebraicos,ObjetoActual, ParentesisCierre));
			TipoDeErrorAlgebraico PosibleError = subOperacion.CalcularOperacion();
			if(PosibleError == null) {/// Comprobar que no retorno errores
				 ObjetosAlgebraicos_PrimerOrden.add(subOperacion.getResultado());
				 continue Repetidor;
				 }
			else {/// si si tiene regresar el error 
				return PosibleError;
			}
			}
			else {
				return TipoDeErrorAlgebraico.ParentesisAbiertos;
          

			}
			}
		
			}
					
			break; // break del switch de operaciones 
		case Vector: 
			ObjetosAlgebraicos_PrimerOrden.add(ObjetoActual);// Pasar al siguiente Orden
			ObjetosAlgebraicos.remove(ObjetoActual);
		 break;
		}	
	}
	}
		return null;
		
	}
	
	
	private TipoDeErrorAlgebraico ResolverSegundoOrden(List<ObjetoAlgebraico> ListaParResolverSegundoOrden,List<ObjetoAlgebraico> listaDeResultado[]) {
		List<ObjetoAlgebraico> listaAuxiliar = new CopyOnWriteArrayList<ObjetoAlgebraico>(ListaParResolverSegundoOrden);	
		boolean OperadorEncontrado = false;
		TipoDeErrorAlgebraico posibleError = null;
		for(int  Index = 0; Index < ListaParResolverSegundoOrden.size(); Index++) {
			if(ListaParResolverSegundoOrden.get(Index).getTipoDeObjetoAlgebraico() == TipoObjetoAlgebraico.Operacion) {
				try {
					if(ListaParResolverSegundoOrden.get(Index-1).getTipoDeObjetoAlgebraico() != TipoObjetoAlgebraico.Operacion &&
							ListaParResolverSegundoOrden.get(Index+1).getTipoDeObjetoAlgebraico() != TipoObjetoAlgebraico.Operacion ) {
						/////////// Si el try no arroja nada de operador incompleto o tampoco de operador repitido, avanza	
				switch(((Operacion)ListaParResolverSegundoOrden.get(Index)).getOperacion()){
				case Dividir:
					 // Comprobar que se divide un vector entre una unidad o una unidad entre una unidad;
					//primer caso
					if(ListaParResolverSegundoOrden.get(Index-1).TipoDeObjetoAlgebraico == TipoObjetoAlgebraico.Vector 
					&& ListaParResolverSegundoOrden.get(Index+1).TipoDeObjetoAlgebraico == TipoObjetoAlgebraico.Unidad) {
						///Una vez terminado de  analizar una posicion se hace la comprobacion de si se ha encontrado un operador valido, crear la nueva lista y Terminar 
							listaAuxiliar.clear();
							listaAuxiliar.addAll(ListaParResolverSegundoOrden.subList(0, Index-1));
							listaAuxiliar.add(Operacion.Dividir((Vector3)ListaParResolverSegundoOrden.get(Index-1), (UnidadAritmetica)ListaParResolverSegundoOrden.get(Index+1)));
							
							try {
							listaAuxiliar.addAll(ListaParResolverSegundoOrden.subList(Index+2, ListaParResolverSegundoOrden.size()));}
							catch(EventException e) { System.out.println("Lista mas pequenio de lo esperado");}
							
							listaAuxiliar.removeIf(n -> (n == null));//por si se agrego algun valor nulo
							OperadorEncontrado = true;// Termina el bucle for
					}
					//segundo caso 
					else if(ListaParResolverSegundoOrden.get(Index-1).TipoDeObjetoAlgebraico == TipoObjetoAlgebraico.Unidad 
							&& ListaParResolverSegundoOrden.get(Index+1).TipoDeObjetoAlgebraico == TipoObjetoAlgebraico.Unidad) {
						listaAuxiliar.clear();
						listaAuxiliar.addAll(ListaParResolverSegundoOrden.subList(0, Index-1));
						listaAuxiliar.add(Operacion.Dividir((UnidadAritmetica)ListaParResolverSegundoOrden.get(Index-1), (UnidadAritmetica)ListaParResolverSegundoOrden.get(Index+1)));
						try {
							listaAuxiliar.addAll(ListaParResolverSegundoOrden.subList(Index+2, ListaParResolverSegundoOrden.size()));}
							catch(EventException e) { System.out.println("Lista mas pequenio de lo esperado");}
													listaAuxiliar.removeIf(n -> (n == null));//por si se agrego algun valor nulo
						OperadorEncontrado = true;// Termina el bucle for
							}
					else {// error 
						return Constantes.TipoDeErrorAlgebraico.ErrorMatematico;
					}
					break;
				case Multiplicar:
					 // Comprobar que se divide un vector entre una unidad o una unidad entre una unidad;
					//primer caso
					if(ListaParResolverSegundoOrden.get(Index-1).TipoDeObjetoAlgebraico == TipoObjetoAlgebraico.Vector 
					&& ListaParResolverSegundoOrden.get(Index+1).TipoDeObjetoAlgebraico == TipoObjetoAlgebraico.Unidad) {
						///Una vez terminado de  analizar una posicion se hace la comprobacion de si se ha encontrado un operador valido, crear la nueva lista y Terminar 
							listaAuxiliar.clear();
							listaAuxiliar.addAll(ListaParResolverSegundoOrden.subList(0, Index-1));
							listaAuxiliar.add(Operacion.Multiplicar((Vector3)ListaParResolverSegundoOrden.get(Index-1), (UnidadAritmetica)ListaParResolverSegundoOrden.get(Index+1)));
							try {
								listaAuxiliar.addAll(ListaParResolverSegundoOrden.subList(Index+2, ListaParResolverSegundoOrden.size()));}
								catch(EventException e) { System.out.println("Lista mas pequenio de lo esperado");}
															listaAuxiliar.removeIf(n -> (n == null));//por si se agrego algun valor nulo
							OperadorEncontrado = true;// Termina el bucle for
					}
					//Segundo
					else if(ListaParResolverSegundoOrden.get(Index-1).TipoDeObjetoAlgebraico == TipoObjetoAlgebraico.Unidad 
							&& ListaParResolverSegundoOrden.get(Index+1).TipoDeObjetoAlgebraico == TipoObjetoAlgebraico.Vector) {
								///Una vez terminado de  analizar una posicion se hace la comprobacion de si se ha encontrado un operador valido, crear la nueva lista y Terminar 
									listaAuxiliar.clear();
									listaAuxiliar.addAll(ListaParResolverSegundoOrden.subList(0, Index-1));
									listaAuxiliar.add(Operacion.Multiplicar((Vector3)ListaParResolverSegundoOrden.get(Index+1), (UnidadAritmetica)ListaParResolverSegundoOrden.get(Index-1)));
									try {
										listaAuxiliar.addAll(ListaParResolverSegundoOrden.subList(Index+2, ListaParResolverSegundoOrden.size()));}
										catch(EventException e) { System.out.println("Lista mas pequenio de lo esperado");}
																	listaAuxiliar.removeIf(n -> (n == null));//por si se agrego algun valor nulo
									OperadorEncontrado = true;// Termina el bucle for
							}
					//Tercer caso 
					else if(ListaParResolverSegundoOrden.get(Index-1).TipoDeObjetoAlgebraico == TipoObjetoAlgebraico.Unidad 
							&& ListaParResolverSegundoOrden.get(Index+1).TipoDeObjetoAlgebraico == TipoObjetoAlgebraico.Unidad) {
						listaAuxiliar.clear();
						listaAuxiliar.addAll(ListaParResolverSegundoOrden.subList(0, Index-1));
						listaAuxiliar.add(Operacion.Multiplicar((UnidadAritmetica)ListaParResolverSegundoOrden.get(Index-1), (UnidadAritmetica)ListaParResolverSegundoOrden.get(Index+1)));
						try {
							listaAuxiliar.addAll(ListaParResolverSegundoOrden.subList(Index+2, ListaParResolverSegundoOrden.size()));}
							catch(EventException e) { System.out.println("Lista mas pequenio de lo esperado");}
													listaAuxiliar.removeIf(n -> (n == null));//por si se agrego algun valor nulo
						OperadorEncontrado = true;// Termina el bucle for
							}
					else {// error 
						return Constantes.TipoDeErrorAlgebraico.ErrorMatematico;
					}
						break;
				case Producto_Cruz:
					//primer caso
					if(ListaParResolverSegundoOrden.get(Index-1).TipoDeObjetoAlgebraico == TipoObjetoAlgebraico.Vector 
					&& ListaParResolverSegundoOrden.get(Index+1).TipoDeObjetoAlgebraico == TipoObjetoAlgebraico.Vector) {
						listaAuxiliar.clear();
						listaAuxiliar.addAll(ListaParResolverSegundoOrden.subList(0, Index-1));
						listaAuxiliar.add(Operacion.VecCruz((Vector3)ListaParResolverSegundoOrden.get(Index-1), (Vector3)ListaParResolverSegundoOrden.get(Index+1)));
						try {
							listaAuxiliar.addAll(ListaParResolverSegundoOrden.subList(Index+2, ListaParResolverSegundoOrden.size()));}
							catch(EventException e) { System.out.println("Lista mas pequenio de lo esperado");}
													listaAuxiliar.removeIf(n -> (n == null));//por si se agrego algun valor nulo
						OperadorEncontrado = true;// Termina el bucle for
						
					}
					else {// error 
						return Constantes.TipoDeErrorAlgebraico.ErrorMatematico;
					}
				 break;
				case Producto_Escalar:
					//primer caso
					if(ListaParResolverSegundoOrden.get(Index-1).TipoDeObjetoAlgebraico == TipoObjetoAlgebraico.Vector 
					&& ListaParResolverSegundoOrden.get(Index+1).TipoDeObjetoAlgebraico == TipoObjetoAlgebraico.Vector) {
						listaAuxiliar.clear();
						listaAuxiliar.addAll(ListaParResolverSegundoOrden.subList(0, Index-1));
						listaAuxiliar.add(Operacion.VecProductoEscalar((Vector3)ListaParResolverSegundoOrden.get(Index-1), (Vector3)ListaParResolverSegundoOrden.get(Index+1)));
						try {
							listaAuxiliar.addAll(ListaParResolverSegundoOrden.subList(Index+2, ListaParResolverSegundoOrden.size()));}
							catch(EventException e) { System.out.println("Lista mas pequenio de lo esperado");}
													listaAuxiliar.removeIf(n -> (n == null));//por si se agrego algun valor nulo
						OperadorEncontrado = true;// Termina el bucle for
						
					}
					else {// error 
						return Constantes.TipoDeErrorAlgebraico.ErrorMatematico;
					}
				 break;
				} //cierre switch
					}
					else {
						new IndexOutOfBoundsException("operador repetido");
					}
						}
					catch(IndexOutOfBoundsException i) {
						///error -1 por objeto vacio
						//-3 por operador repetido
						System.err.print("Error de sintaxis"+i.getMessage());
						return Constantes.TipoDeErrorAlgebraico.EntradaInvalida;
					}
				
			}			
			if(OperadorEncontrado) {
				break; //Terminar la busqueda del for
			}
		}
		if(OperadorEncontrado == false) {
			
			listaDeResultado[0] = listaAuxiliar;
		}
		else {
			posibleError = ResolverSegundoOrden(listaAuxiliar, listaDeResultado);
		}
		
		
		
		
	return posibleError;

	}
	
	private TipoDeErrorAlgebraico ResolverSegundoOrden() {
		
		List<ObjetoAlgebraico>[] ListaArraySegundoOrden = new List[1];
		ListaArraySegundoOrden[0] =  new CopyOnWriteArrayList<ObjetoAlgebraico>();
		TipoDeErrorAlgebraico posibleError = ResolverSegundoOrden(ObjetosAlgebraicos_PrimerOrden,ListaArraySegundoOrden);
		ObjetosAlgebraicos_SegundoOrden.clear();
		ObjetosAlgebraicos_SegundoOrden.addAll(ListaArraySegundoOrden[0]);
		return posibleError;
	}
	
	private TipoDeErrorAlgebraico ResolverTercerOrden() {
		
		 ObjetoAlgebraico ObjetoUltimo = null;
		 ObjetoAlgebraico ObjetoPenultimo = null;

		for(ObjetoAlgebraico ObjetoActual : ObjetosAlgebraicos_SegundoOrden) {
			if(ObjetoUltimo != null) {
			switch(ObjetoActual.getTipoDeObjetoAlgebraico()) { /// IDENTIFICAR EL TIPO DE OBJETO ACTUAL 
			case Unidad : 	
				if(ObjetoUltimo.getTipoDeObjetoAlgebraico() == TipoObjetoAlgebraico.Operacion) {
				if(((Operacion)ObjetoUltimo).getOperacion() == TipoOperacion.Sumar) {
					if(ObjetoPenultimo.getTipoDeObjetoAlgebraico() == TipoObjetoAlgebraico.Unidad) {
					    Resultado = Operacion.Sumar((UnidadAritmetica)Resultado, (UnidadAritmetica)ObjetoActual);
					}
					else {
						return TipoDeErrorAlgebraico.EntradaInvalida;
					}
				}	
				else if(((Operacion)ObjetoUltimo).getOperacion() == TipoOperacion.Restar) {
					if(ObjetoPenultimo.getTipoDeObjetoAlgebraico() == TipoObjetoAlgebraico.Unidad) {
					    Resultado = Operacion.Restar((UnidadAritmetica)Resultado, (UnidadAritmetica)ObjetoActual);
					}
					else {
						return TipoDeErrorAlgebraico.EntradaInvalida;
					}
				}
			}
			break;
			case Vector:  
				if(ObjetoUltimo.getTipoDeObjetoAlgebraico() == TipoObjetoAlgebraico.Operacion) {
					if(((Operacion)ObjetoUltimo).getOperacion() == TipoOperacion.Sumar) {
						if(ObjetoPenultimo.getTipoDeObjetoAlgebraico() == TipoObjetoAlgebraico.Vector) {
						    Resultado = Operacion.Sumar((Vector3)Resultado, (Vector3)ObjetoActual);
						}
						else {
							return TipoDeErrorAlgebraico.EntradaInvalida;
						}
					}	
					else if(((Operacion)ObjetoUltimo).getOperacion() == TipoOperacion.Restar) {
						if(ObjetoPenultimo.getTipoDeObjetoAlgebraico() == TipoObjetoAlgebraico.Vector) {
						    Resultado = Operacion.Restar((Vector3)Resultado, (Vector3)ObjetoActual);
						}
						else {
							return TipoDeErrorAlgebraico.EntradaInvalida;
						}
					}
				}
			break;
			}
		}else{ if(
				ObjetoActual.getTipoDeObjetoAlgebraico() == TipoObjetoAlgebraico.Vector 
			||ObjetoActual.getTipoDeObjetoAlgebraico()  == TipoObjetoAlgebraico.Unidad  ) {
			Resultado = ObjetoActual;}
		else { return TipoDeErrorAlgebraico.EntradaInvalida;}
		
		
		}
			ObjetoPenultimo = ObjetoUltimo;
			ObjetoUltimo = ObjetoActual;
			
			
			}
		

		
			return null;
		
	}
	public void HardReset() {
		Resultado = null;
		ObjetosAlgebraicos = new  CopyOnWriteArrayList<ObjetoAlgebraico>(); 
		ObjetosAlgebraicos_PrimerOrden = new CopyOnWriteArrayList<ObjetoAlgebraico>();
		ObjetosAlgebraicos_SegundoOrden = new CopyOnWriteArrayList<ObjetoAlgebraico>();
		
	}
	private void reset() {
		Resultado = null;
		Error = null;
		//ObjetosAlgebraicos = new  CopyOnWriteArrayList<ObjetoAlgebraico>(); 
		ObjetosAlgebraicos_PrimerOrden = new CopyOnWriteArrayList<ObjetoAlgebraico>();
		ObjetosAlgebraicos_SegundoOrden = new CopyOnWriteArrayList<ObjetoAlgebraico>();
	}
	public TipoDeErrorAlgebraico CalcularOperacion() {
		 reset();
			
		TipoDeErrorAlgebraico PrimerOrdenError =  ResolverPrimerOrden();
	     if(PrimerOrdenError != null) { 
	    	 Error = PrimerOrdenError;
	    	 return PrimerOrdenError;}
	     
	     
	    
		TipoDeErrorAlgebraico SegundoOrdenError = ResolverSegundoOrden();
		 if(SegundoOrdenError != null) { 
			 Error = SegundoOrdenError;
			 return SegundoOrdenError;}

		TipoDeErrorAlgebraico TercerOrden =  ResolverTercerOrden();
		 if(TercerOrden != null) {
			 Error = TercerOrden;
			 return TercerOrden;
			 }

		 if(Resultado == null) {
			 Error = TipoDeErrorAlgebraico.ResultadoNulo;;
			 return TipoDeErrorAlgebraico.ResultadoNulo;}

			
	return null;
	}
	
	
	public ObjetoAlgebraico getResultado() {
		return Resultado;
	}


	public TipoDeErrorAlgebraico getError() {
		return Error;
	}
	
	
	
}
