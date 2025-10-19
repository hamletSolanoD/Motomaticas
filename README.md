# 🧮 Motomaticas: Motor Matemático OpenSource con Inyección de Dependencias Dinámicas

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=java&logoColor=white) ![Estado](https://img.shields.io/badge/Estado-Producci%C3%B3n-success) ![Arquitectura](https://img.shields.io/badge/Arquitectura-SOLID-blue) ![Licencia](https://img.shields.io/badge/Licencia-OpenSource-green) ![Patrón](https://img.shields.io/badge/Patr%C3%B3n-Dependency_Injection-orange)
## 📝 Descripción
**Motomaticas** es un motor matemático modular de código abierto que resuelve el problema de la fragmentación en herramientas de cálculo matemático. Mediante **inyección de dependencias dinámicas**, permite a programadores y educadores crear, compartir y reutilizar módulos matemáticos sin necesidad de desarrollar toda la infraestructura desde cero ni aprender lenguajes especializados como MATLAB o Mathematica.
### Motivación Técnica
El proyecto nace de la necesidad de democratizar el desarrollo de herramientas matemáticas interactivas. Tradicionalmente, crear una calculadora científica personalizada requiere:
- Dominio de lenguajes matemáticos especializados (elevado tiempo de capacitación)
- Implementación completa de jerarquía de operaciones
- Desarrollo de sistema de parsing y evaluación
- Creación de interfaz gráfica desde cero
**Motomaticas** abstrae toda esta complejidad mediante un sistema de **plugins matemáticos** donde el desarrollador solo se enfoca en la lógica específica de su operación o unidad.
## 🎮 Demostración
### Panel de Operaciones
_Interfaz principal mostrando operaciones complejas con vectores 3D y números reales_
<img width="1029" height="543" alt="image" src="https://github.com/user-attachments/assets/c91d4eea-f024-4ba2-9a50-5725aed7cfeb" />
<img width="882" height="495" alt="image" src="https://github.com/user-attachments/assets/c61b98e9-9863-4445-8287-864e54b131b1" />
<img width="860" height="403" alt="image" src="https://github.com/user-attachments/assets/e2435501-3ae5-4b73-bfea-8aece423ea54" />


### Resolución Paso a Paso
<img width="845" height="435" alt="image" src="https://github.com/user-attachments/assets/70923b73-03a0-4f97-9031-96bf906c56bf" />
<img width="883" height="477" alt="image" src="https://github.com/user-attachments/assets/c2c5cf69-fd5f-4476-b827-2648129d6cdf" />

### Creación de Unidades Personalizadas
<img width="807" height="299" alt="image" src="https://github.com/user-attachments/assets/9aeb6338-9270-448e-b55d-7075cb230d97" />

## ✨ Características
### Funcionalidades Core
- 🔌 **Inyección de Dependencias Dinámicas**: Sistema plugin para agregar unidades y operaciones sin modificar código base
- 📊 **Jerarquía de Operaciones Automática**: Resolución correcta según orden matemático (PEMDAS) con 4 niveles de prioridad
- 📚 **Explicaciones Auto-Generadas**: Cada operación genera documentación humana de su proceso
- 🎨 **Interfaz Modular**: Paneles independientes (Operaciones, Procedimiento, Notas, Pizarra, Visualización)
- 💾 **Sistema de Apuntes**: Serialización completa de sesiones de trabajo para compartir y reutilizar
### Capacidades Técnicas
- ⚡ **Algoritmo Divide & Conquer**: Resolución recursiva de sub-operaciones con complejidad O(n log n)
- 🧩 **Polimorfismo Extremo**: Arquitectura 100% basada en abstracciones (ObjetoMatematico base)
- 🔄 **Evaluación de Expresiones Anidadas**: Soporte para operaciones con múltiples niveles de paréntesis
- 🎯 **Type Safety**: Sistema de validación de operandos en tiempo de ejecución
- 📦 **Serialización Completa**: Persistencia de estado mediante Serializable

## 🏗️ Arquitectura
### Diagrama de Clases Principal

```
ObjetoMatematico (Abstract)
├── UnidadMatematica (Abstract)
│   ├── Vector3D
│   ├── UnidadNumerosRacionales
│   └── [Otras unidades inyectables]
├── OperacionMatematica (Abstract)
│   ├── Suma
│   ├── Resta
│   ├── Multiplicacion
│   ├── Division
│   └── [Otras operaciones inyectables]
└── FuncionMatematica (Abstract)
    └── [Funciones inyectables]
```

### Jerarquía de Resolución
El motor implementa un sistema de **4 niveles de prioridad**:
1. **Nivel 4 - Paréntesis y Funciones**: Operaciones encapsuladas `{...}` o funciones `f(x)`
2. **Nivel 3 - Potencias**: Exponenciación, raíces
3. **Nivel 2 - Multiplicación/División**: Operaciones de segundo orden
4. **Nivel 1 - Suma/Resta**: Operaciones de primer orden y resultado final

### Patrón de Inyección
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

Este patrón permite **inyección en tiempo de ejecución** sin recompilación.

## 🎯 Patrones de Diseño
### Implementación SOLID
#### Single Responsibility Principle (SRP)
Cada clase tiene una única responsabilidad bien definida:
- `OperacionGeneral`: Solo resuelve operaciones
- `ObjetoMatematico`: Solo define estructura base
- `UnidadMatematica`: Solo representa unidades cuantificables
#### Open-Closed Principle (OCP)
Sistema abierto para extensión mediante herencia, cerrado para modificación:
```java
public abstract class UnidadMatematica extends ObjetoMatematico {
    public abstract UnidadMatematica crearUnidad(JFrame padre);
    public abstract String toString();
    public abstract String toStringReducido();
}
```

Desarrolladores extienden sin modificar el motor core.
#### Liskov Substitution Principle (LSP)
Cualquier subtipo de `ObjetoMatematico` es intercambiable:

```java
List<ObjetoMatematico> operacion = new LinkedList<>();
operacion.add(new Vector3D());
operacion.add(new Suma());
operacion.add(new UnidadNumerosRacionales());
```

#### Interface Segregation Principle (ISP)
Interfaces especializadas evitan dependencias innecesarias:
```java
public interface operacionesMatematicasGenericasInterface {
    class llaveIzquierda extends OperacionMatematica { }
    class llaveDerecha extends OperacionMatematica { }
}
```

#### Dependency Inversion Principle (DIP)
**Núcleo de la arquitectura**: Motor depende de abstracciones, no de implementaciones concretas:
```java
public class OperacionGeneral {
    private List<ObjetoMatematico> ObjetosMatematicos;
    
    public TipoDeErrorMatematico CalcularOperacion() {
    }
}
```

El motor trabaja exclusivamente con `ObjetoMatematico`, permitiendo inyección de cualquier tipo.

### Otros Patrones
**Template Method**: Clases abstractas definen estructura, subclases implementan detalles **Factory Pattern**: Creación dinámica de objetos matemáticos según tipo **Iterator Pattern**: Recorrido de operaciones mediante `CopyOnWriteArrayList`
## 🧠 Algoritmos

### Algoritmo de Resolución Jerárquica
**Complejidad**: O(n log n) donde n es el número de objetos matemáticos
**Estrategia**: Divide & Conquer con recursión
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

### Búsqueda de Paréntesis Correspondiente
**Complejidad**: O(n)
**Algoritmo**: Contador de balance de paréntesis
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

### Resolución de Orden N
**Estrategia**: Recursión con array apuntador para mantener estado
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

### Análisis de Complejidad
**Mejor caso**: O(n) - Operación lineal sin paréntesis **Caso promedio**: O(n log n) - Operaciones con anidamiento moderado **Peor caso**: O(n²) - Anidamiento profundo con múltiples sub-operaciones recursivas
**Optimizaciones implementadas**:
- `CopyOnWriteArrayList` para iteración thread-safe
- Búsqueda de operadores por prioridad evitando recorridos completos
- Reutilización de sub-resultados (memoization implícita)
## 📦 Módulos Principales
### 1. Motor Matemático (OperacionGeneral)
**Propósito**: Núcleo de evaluación de expresiones matemáticas
**Implementación**:
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

**Patrón aplicado**: Template Method + Chain of Responsibility

**Desafíos superados**:
- Manejo correcto de precedencia de operadores
- Detección de errores sintácticos (paréntesis desbalanceados, operadores consecutivos)
- Evaluación perezosa para optimizar rendimiento

### 2. Sistema de Inyección (motomaticas)
**Propósito**: Gestión de plugins matemáticos
**Implementación**:
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

**Patrón aplicado**: Dependency Injection + Registry Pattern

**Interacción con otros módulos**:
- Registra plugins en listas globales estáticas
- Provee acceso centralizado a todos los objetos matemáticos disponibles
- Gestiona ciclo de vida de la aplicación

### 3. Jerarquía de Objetos Matemáticos
**Propósito**: Abstracción unificada para todos los elementos matemáticos
**Implementación**:
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

**Patrón aplicado**: Abstract Factory

**Desafíos superados**:
- Diseño de jerarquía flexible que permita cualquier tipo de unidad matemática
- Sistema de identificación único mediante UUID
- Serialización para persistencia de sesiones

### 4. Sistema de Configuración
**Propósito**: Control de paneles UI mediante flags
**Implementación**:
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

**Patrón aplicado**: Singleton implícito (variables estáticas)

**Interacción**:
- Controla visibilidad de componentes UI desde punto de entrada
- Permite personalización modular de interfaz
- Reduce acoplamiento entre módulos de visualización

## 🛠️ Tecnologías
### Core
- **Java 8+**: Lenguaje principal, orientado a objetos
- **Swing/AWT**: Bibliotecas de interfaz gráfica multiplataforma
- **Java Serialization**: Persistencia de objetos

### Justificación Técnica
**¿Por qué Java?**
- Multiplataforma (Write Once, Run Anywhere)
- Amplia adopción en educación
- Ecosistema maduro de bibliotecas
- Fuerte tipado para seguridad en cálculos matemáticos

**¿Por qué Swing/AWT?**
- Bibliotecas estándar, sin dependencias externas
- Documentación extensa y comunidad grande
- Look & Feel nativo multiplataforma
- Drag & Drop integrado para UX interactiva

### Estructura de Directorios
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

## 📦 Instalación
### Requisitos del Sistema
- **JDK**: 8 o superior
- **RAM**: 512 MB mínimo
- **SO**: Windows, macOS, Linux (cualquier sistema con JVM)

```
 __  __       _                        _   _               
|  \/  | ___ | |_ ___  _ __ ___   __ _| |_(_) ___ __ _ ___ 
| |\/| |/ _ \| __/ _ \| '_ ` _ \ / _` | __| |/ __/ _` / __|
| |  | | (_) | || (_) | | | | | | (_| | |_| | (_| (_| \__ \
|_|  |_|\___/ \__\___/|_| |_| |_|\__,_|\__|_|\___\__,_|___/
```

_Herramienta matemática modular desarrollada con ❤️ en Java_
**⭐ Si este proyecto te resulta útil, considera darle una estrella en GitHub**
