# 🧮 Motomaticas: OpenSource Mathematical Engine with Dynamic Dependency Injection

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=java&logoColor=white) ![Status](https://img.shields.io/badge/Status-Production-success) ![Architecture](https://img.shields.io/badge/Architecture-SOLID-blue) ![License](https://img.shields.io/badge/License-OpenSource-green) ![Pattern](https://img.shields.io/badge/Pattern-Dependency_Injection-orange)

## 📝 Description

**Motomaticas** is a modular open-source mathematical engine that solves the fragmentation problem in mathematical calculation tools. Through **dynamic dependency injection**, it allows programmers and educators to create, share, and reuse mathematical modules without needing to develop the entire infrastructure from scratch or learn specialized languages like MATLAB or Mathematica.

### Technical Motivation

The project stems from the need to democratize the development of interactive mathematical tools. Traditionally, creating a custom scientific calculator requires:
- Mastery of specialized mathematical languages (high training time investment)
- Complete implementation of operation hierarchy
- Development of parsing and evaluation system
- GUI creation from scratch

**Motomaticas** abstracts all this complexity through a **mathematical plugin system** where developers only focus on the specific logic of their operation or unit.

## 🎮 Demo

### Operations Panel
_Main interface showing complex operations with 3D vectors and real numbers_
<img width="1029" height="543" alt="image" src="https://github.com/user-attachments/assets/c91d4eea-f024-4ba2-9a50-5725aed7cfeb" />
<img width="882" height="495" alt="image" src="https://github.com/user-attachments/assets/c61b98e9-9863-4445-8287-864e54b131b1" />
<img width="860" height="403" alt="image" src="https://github.com/user-attachments/assets/e2435501-3ae5-4b73-bfea-8aece423ea54" />

### Step-by-Step Resolution
<img width="845" height="435" alt="image" src="https://github.com/user-attachments/assets/70923b73-03a0-4f97-9031-96bf906c56bf" />
<img width="883" height="477" alt="image" src="https://github.com/user-attachments/assets/c2c5cf69-fd5f-4476-b827-2648129d6cdf" />

### Custom Unit Creation
<img width="807" height="299" alt="image" src="https://github.com/user-attachments/assets/9aeb6338-9270-448e-b55d-7075cb230d97" />

## ✨ Features

### Core Functionalities
- 🔌 **Dynamic Dependency Injection**: Plugin system to add units and operations without modifying base code
- 📊 **Automatic Operation Hierarchy**: Correct resolution according to mathematical order (PEMDAS) with 4 priority levels
- 📚 **Auto-Generated Explanations**: Each operation generates human-readable documentation of its process
- 🎨 **Modular Interface**: Independent panels (Operations, Procedure, Notes, Whiteboard, Visualization)
- 💾 **Note-Taking System**: Complete session serialization for sharing and reuse

### Technical Capabilities
- ⚡ **Divide & Conquer Algorithm**: Recursive resolution of sub-operations with O(n log n) complexity
- 🧩 **Extreme Polymorphism**: 100% abstraction-based architecture (ObjetoMatematico base)
- 🔄 **Nested Expression Evaluation**: Support for operations with multiple levels of parentheses
- 🎯 **Type Safety**: Runtime operand validation system
- 📦 **Complete Serialization**: State persistence through Serializable

## 🏗️ Architecture

### Main Class Diagram

```
ObjetoMatematico (Abstract)
├── UnidadMatematica (Abstract)
│   ├── Vector3D
│   ├── UnidadNumerosRacionales
│   └── [Other injectable units]
├── OperacionMatematica (Abstract)
│   ├── Suma
│   ├── Resta
│   ├── Multiplicacion
│   ├── Division
│   └── [Other injectable operations]
└── FuncionMatematica (Abstract)
    └── [Injectable functions]
```

### Resolution Hierarchy
The engine implements a **4-level priority system**:
1. **Level 4 - Parentheses and Functions**: Encapsulated operations `{...}` or functions `f(x)`
2. **Level 3 - Powers**: Exponentiation, roots
3. **Level 2 - Multiplication/Division**: Second-order operations
4. **Level 1 - Addition/Subtraction**: First-order operations and final result

### Injection Pattern
```java
public class motomaticas {
    private ArrayList<UnidadMatematica> unidadesMatematicasUsadas;
    private ArrayList<OperacionMatematica> operacionesMatematicasUsadas;
    private ArrayList<FuncionMatematica> funcionesMatematicasUsadas;
    
    public void importarUnidadesMatematicas(UnidadMatematica... UnidadesMatematicas) {
        for(UnidadMatematica newUnidad : UnidadesMatematicas) {
            unidadesMatematicasUsadas.add(newUnidad);
        }
    }
}
```

This pattern enables **runtime injection** without recompilation.

## 🎯 Design Patterns

### SOLID Implementation

#### Single Responsibility Principle (SRP)
Each class has a single, well-defined responsibility:
- `OperacionGeneral`: Only resolves operations
- `ObjetoMatematico`: Only defines base structure
- `UnidadMatematica`: Only represents quantifiable units

#### Open-Closed Principle (OCP)
System open for extension through inheritance, closed for modification:
```java
public abstract class UnidadMatematica extends ObjetoMatematico {
    public abstract UnidadMatematica crearUnidad(JFrame padre);
    public abstract String toString();
    public abstract String toStringReducido();
}
```

Developers extend without modifying the core engine.

#### Liskov Substitution Principle (LSP)
Any subtype of `ObjetoMatematico` is interchangeable:

```java
List<ObjetoMatematico> operacion = new LinkedList<>();
operacion.add(new Vector3D());
operacion.add(new Suma());
operacion.add(new UnidadNumerosRacionales());
```

#### Interface Segregation Principle (ISP)
Specialized interfaces avoid unnecessary dependencies:
```java
public interface operacionesMatematicasGenericasInterface {
    class llaveIzquierda extends OperacionMatematica { }
    class llaveDerecha extends OperacionMatematica { }
}
```

#### Dependency Inversion Principle (DIP)
**Architecture core**: Engine depends on abstractions, not concrete implementations:
```java
public class OperacionGeneral {
    private List<ObjetoMatematico> ObjetosMatematicos;
    
    public TipoDeErrorMatematico CalcularOperacion() {
    }
}
```

The engine works exclusively with `ObjetoMatematico`, allowing injection of any type.

### Other Patterns
**Template Method**: Abstract classes define structure, subclasses implement details
**Factory Pattern**: Dynamic creation of mathematical objects by type
**Iterator Pattern**: Operation traversal using `CopyOnWriteArrayList`

## 🧠 Algorithms

### Hierarchical Resolution Algorithm
**Complexity**: O(n log n) where n is the number of mathematical objects
**Strategy**: Divide & Conquer with recursion
```java
private TipoDeErrorMatematico ResolverPrimerOrden() {
    Repetidor: while (ObjetosMatematicos.size() > 0) {
        for (Iterator<ObjetoMatematico> IteradorObjetosMatematicos0 = 
             ObjetosMatematicos.iterator(); 
             IteradorObjetosMatematicos0.hasNext();) {
            
            ObjetoMatematico ObjetoActual = IteradorObjetosMatematicos0.next();
            
            switch (ObjetoActual.getTipoDeObjetoMatematico()) {
                case Unidad:
                    ObjetosMatematicos_PrimerOrden.add(ObjetoActual);
                    ObjetosMatematicos.remove(ObjetoActual);
                    break;
                case Operacion:
                    if (((OperacionMatematica) ObjetoActual).getConLlave()) {
                        ObjetoMatematico ParentesisCierre = BuscarParentesisCierre(ObjetoActual);
                        if (ParentesisCierre != null) {
                            OperacionGeneral subOperacion = new OperacionGeneral(
                                SubdividirOperacion(ObjetosMatematicos, ObjetoActual, ParentesisCierre)
                            );
                            TipoDeErrorMatematico PosibleError = subOperacion.CalcularOperacion();
                            if (PosibleError == null) {
                                ObjetosMatematicos_PrimerOrden.add(
                                    ((OperacionMatematica) ObjetoActual)
                                        .calcularOperacion((UnidadMatematica) subOperacion.getResultado())
                                );
                                continue Repetidor;
                            } else {
                                return PosibleError;
                            }
                        } else {
                            return TipoDeErrorMatematico.ParentesisAbiertos;
                        }
                    }
                    break;
            }
        }
    }
    return null;
}
```

### Corresponding Parenthesis Search
**Complexity**: O(n)
**Algorithm**: Parenthesis balance counter
```java
private ObjetoMatematico BuscarParentesisCierre(ObjetoMatematico Inicio) {
    int NumeroDeParentesis = 0;
    
    for (int Index = ObjetosMatematicos.indexOf(Inicio); 
         Index < ObjetosMatematicos.size(); 
         Index++) {
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
```

### Order N Resolution
**Strategy**: Recursion with pointer array to maintain state
```java
private TipoDeErrorMatematico ResolverOrdenN(
    List<ObjetoMatematico> ListaDeOrdenPrevia,
    List<ObjetoMatematico> ListaDeOrdenN[], 
    int prioridad) {
    
    List<ObjetoMatematico>[] ListaArraySegundoOrden = new List[1];
    ListaArraySegundoOrden[0] = new CopyOnWriteArrayList<ObjetoMatematico>();
    
    TipoDeErrorMatematico posibleError = ResolverSegundoOrden(
        ListaDeOrdenPrevia, 
        ListaArraySegundoOrden, 
        prioridad
    );
    
    ListaDeOrdenN[0].clear();
    ListaDeOrdenN[0].addAll(ListaArraySegundoOrden[0]);
    
    return posibleError;
}
```

### Complexity Analysis
**Best case**: O(n) - Linear operation without parentheses
**Average case**: O(n log n) - Operations with moderate nesting
**Worst case**: O(n²) - Deep nesting with multiple recursive sub-operations

**Implemented optimizations**:
- `CopyOnWriteArrayList` for thread-safe iteration
- Operator search by priority avoiding complete traversals
- Sub-result reuse (implicit memoization)

## 📦 Main Modules

### 1. Mathematical Engine (OperacionGeneral)
**Purpose**: Core expression evaluation engine
**Implementation**:
```java
public class OperacionGeneral implements Serializable {
    private List<ObjetoMatematico> ObjetosMatematicos;
    private List<ObjetoMatematico> ObjetosMatematicos_PrimerOrden;
    private List<ObjetoMatematico> ObjetosMatematicos_SegundoOrden;
    private List<ObjetoMatematico> ObjetosMatematicos_TercerOrden;
    private List<ObjetoMatematico> ObjetosMatematicos_CuartoOrden;
    
    public TipoDeErrorMatematico CalcularOperacion() {
        reset();
        
        TipoDeErrorMatematico PrimerOrdenError = ResolverPrimerOrden();
        if (PrimerOrdenError != null) return PrimerOrdenError;
        
        List<ObjetoMatematico>[] punteroDeLista = new List[1];
        punteroDeLista[0] = ObjetosMatematicos_SegundoOrden;
        TipoDeErrorMatematico SegundoOrdenError = 
            ResolverOrdenN(ObjetosMatematicos_PrimerOrden, punteroDeLista, 3);
        
        if (SegundoOrdenError != null) return SegundoOrdenError;
        
        return null;
    }
}
```

**Applied pattern**: Template Method + Chain of Responsibility

**Challenges overcome**:
- Correct operator precedence handling
- Syntax error detection (unbalanced parentheses, consecutive operators)
- Lazy evaluation for performance optimization

### 2. Injection System (motomaticas)
**Purpose**: Mathematical plugin management
**Implementation**:
```java
public class motomaticas {
    private ArrayList<UnidadMatematica> unidadesMatematicasUsadas;
    private ArrayList<OperacionMatematica> operacionesMatematicasUsadas;
    private ArrayList<FuncionMatematica> funcionesMatematicasUsadas;
    
    public void importarUnidadesMatematicas(UnidadMatematica... UnidadesMatematicas) {
        for(UnidadMatematica newUnidad : UnidadesMatematicas) {
            unidadesMatematicasUsadas.add(newUnidad);
        }
    }
}
```

**Applied pattern**: Dependency Injection + Registry Pattern

**Interaction with other modules**:
- Registers plugins in static global lists
- Provides centralized access to all available mathematical objects
- Manages application lifecycle

### 3. Mathematical Object Hierarchy
**Purpose**: Unified abstraction for all mathematical elements
**Implementation**:
```java
public abstract class ObjetoMatematico implements Serializable {
    protected String ID;
    public TipoObjetoMatematico TipoDeObjetoMatematico;
    private String NombreObjetoMatematico;
    protected String SimboloIdentificador;
    
    public ObjetoMatematico(String nombreObjetoMatematico, 
                           String SimboloIdentificador, 
                           TipoObjetoMatematico TipoDeObjetoMatematicoio) {
        this.ID = UUID.randomUUID().toString();
        this.SimboloIdentificador = SimboloIdentificador;
        this.NombreObjetoMatematico = nombreObjetoMatematico;
        this.TipoDeObjetoMatematico = TipoDeObjetoMatematicoio;
    }
    
    public abstract String toStringReducido();
    public abstract String toString();
}
```

**Applied pattern**: Abstract Factory

**Challenges overcome**:
- Design of flexible hierarchy allowing any type of mathematical unit
- Unique identification system using UUID
- Serialization for session persistence

### 4. Configuration System
**Purpose**: UI panel control through flags
**Implementation**:
```java
public class configuracionProyecto {
    public static boolean panelGrafico;
    public static boolean panelPizzarra;
    public static boolean panelNotas;
    public static boolean panelOperaciones;
    public static boolean panelProcesoExplicadoMatematico;
    public static boolean panelVisualizar;
    
    public static void activarPanelOperaciones(boolean panelOperaciones) {
        configuracionProyecto.panelOperaciones = panelOperaciones;
    }
}
```

**Applied pattern**: Implicit Singleton (static variables)

**Interaction**:
- Controls UI component visibility from entry point
- Allows modular interface customization
- Reduces coupling between visualization modules

## 🛠️ Technologies

### Core
- **Java 8+**: Primary language, object-oriented
- **Swing/AWT**: Cross-platform GUI libraries
- **Java Serialization**: Object persistence

### Technical Justification

**Why Java?**
- Cross-platform (Write Once, Run Anywhere)
- Wide adoption in education
- Mature library ecosystem
- Strong typing for safety in mathematical calculations

**Why Swing/AWT?**
- Standard libraries, no external dependencies
- Extensive documentation and large community
- Native cross-platform Look & Feel
- Integrated Drag & Drop for interactive UX

### Directory Structure
```
Motomaticas/
├── src/
│   ├── Motomaticas/
│   │   ├── ObjetosLogicos/
│   │   │   └── motorMatematico/
│   │   │       ├── ObjetoMatematico.java
│   │   │       ├── OperacionGeneral.java
│   │   │       ├── operaciones/
│   │   │       ├── variables/
│   │   │       └── funciones/
│   │   ├── VentanasProyecto/
│   │   ├── RecursosCustomizados/
│   │   ├── ValoresDefault/
│   │   ├── motomaticas.java
│   │   └── configuracionProyecto.java
│   ├── ModulosImportados/
│   │   ├── NumerosReales/
│   │   └── Vector3D/
│   └── Principal/
│       └── main.java
├── resources/
└── README.md
```

## 📦 Installation

### System Requirements
- **JDK**: 8 or higher
- **RAM**: 512 MB minimum
- **OS**: Windows, macOS, Linux (any system with JVM)

```
 __  __       _                        _   _               
|  \/  | ___ | |_ ___  _ __ ___   __ _| |_(_) ___ __ _ ___ 
| |\/| |/ _ \| __/ _ \| '_ ` _ \ / _` | __| |/ __/ _` / __|
| |  | | (_) | || (_) | | | | | | (_| | |_| | (_| (_| \__ \
|_|  |_|\___/ \__\___/|_| |_| |_|\__,_|\__|_|\___\__,_|___/
```

_Modular mathematical tool developed with ❤️ in Java_

**⭐ If you find this project useful, consider giving it a star on GitHub**
