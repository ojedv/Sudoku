# 🎯 Juego de Sudoku

Un juego completo de Sudoku implementado en Java con interfaces de consola y gráfica. Diseñado siguiendo principios de programación orientada a objetos y patrones de diseño para garantizar mantenibilidad y extensibilidad.

## 📋 Tabla de Contenidos

- [🚀 Características](#-características)
- [🏗️ Arquitectura](#️-arquitectura)
- [📁 Estructura del Proyecto](#-estructura-del-proyecto)
- [🎮 Cómo Jugar](#-cómo-jugar)
- [💻 Instalación y Ejecución](#-instalación-y-ejecución)
- [🎨 Interfaces Disponibles](#-interfaces-disponibles)
- [⚙️ Configuración](#️-configuración)
- [🔧 Desarrollo](#-desarrollo)
- [📖 Documentación Técnica](#-documentación-técnica)

## 🚀 Características

### ✨ Funcionalidades Principales
- **Doble Interfaz**: Versión de consola y GUI moderna
- **Tres Niveles de Dificultad**:
  - 🟢 **Fácil**: 51 celdas pre-llenadas (~63%)
  - 🟡 **Medio**: 41 celdas pre-llenadas (~51%)
  - 🔴 **Difícil**: 31 celdas pre-llenadas (~38%)
- **Generación Automática**: Algoritmo de backtracking para puzzles únicos
- **Validación en Tiempo Real**: Verificación inmediata de reglas de Sudoku
- **Verificación de Solución**: Compara entrada del usuario con la solución correcta
- **Ayuda Visual**: Diferentes colores para números fijos y del usuario

### 🎯 Características Técnicas
- **Patrones de Diseño**: Strategy, MVC, Interface Segregation
- **Arquitectura Modular**: Fácil extensión y mantenimiento
- **Manejo Robusto de Errores**: Validación completa de entrada
- **Interfaz Intuitiva**: Navegación fluida entre paneles

## 🏗️ Arquitectura

### 🔧 Patrones de Diseño Implementados

#### **1. Principio de Segregación de Interfaces (ISP)**
```java
ISudoku              → Operaciones básicas del juego
ISudokuResuelto      → Operaciones de puzzle resuelto
IValidadorSudoku     → Operaciones de validación
IControladorJuego    → Control del flujo del juego
PanelReemplazable    → Intercambio de paneles UI
```

#### **2. Patrón Strategy**
- **Estrategia de Validación**: `IValidadorSudoku` permite diferentes implementaciones
- **Estrategia de Paneles**: `PanelReemplazable` habilita comportamientos UI diversos

#### **3. Modelo-Vista-Controlador (MVC)**
- **Modelo**: `Sudoku`, `SudokuResuelto`
- **Vista**: `SudokuConsola`, paneles GUI
- **Controlador**: `SudokuGUI` implementando `IControladorJuego`

## 📁 Estructura del Proyecto

```
src/
├── consola/
│   ├── 🎯 ISudoku.java              # Interfaz principal del juego
│   ├── 🧩 ISudokuResuelto.java      # Interfaz de puzzle resuelto
│   ├── ✅ IValidadorSudoku.java     # Interfaz de validación
│   ├── 📍 puntoMatriz.java          # Representación de coordenadas
│   ├── 🎮 Sudoku.java               # Lógica principal del juego
│   ├── 💻 SudokuConsola.java        # Interfaz de consola
│   ├── 🧠 SudokuResuelto.java       # Generador de puzzles
│   └── 🔍 ValidadorSudoku.java      # Implementación de validación
└── interfazGrafica/
    ├── 🎚️ DifficultyPanel.java      # Panel de selección de dificultad
    ├── 🎲 GamePanel.java            # Panel principal del juego
    ├── 🎛️ IControladorJuego.java    # Interfaz de controlador
    ├── 🔢 NumberSelectionPanel.java # Panel de selección de números
    ├── 🔄 PanelReemplazable.java    # Interfaz de intercambio de paneles
    └── 🖼️ SudokuGUI.java            # Controlador principal GUI
```

## 🎮 Cómo Jugar

### 📖 Reglas del Sudoku
1. **Objetivo**: Llenar una cuadrícula de 9×9 con dígitos del 1 al 9
2. **Restricciones**:
   - Cada **fila** debe contener todos los números del 1 al 9
   - Cada **columna** debe contener todos los números del 1 al 9
   - Cada **subcuadrícula 3×3** debe contener todos los números del 1 al 9

### 🎯 Flujo del Juego
1. **Selecciona Dificultad**: Elige entre Fácil, Medio o Difícil
2. **Observa el Tablero**: Analiza los números ya colocados
3. **Haz tu Jugada**: Haz clic en una celda vacía y selecciona un número
4. **Validación Automática**: El sistema verifica si tu jugada es válida
5. **Completa el Puzzle**: Llena todas las celdas correctamente para ganar

## 💻 Instalación y Ejecución

### 📋 Prerrequisitos
- Java 8 o superior
- IDE compatible con Java (recomendado: IntelliJ IDEA, Eclipse)

### 🚀 Pasos de Instalación

1. **Clona el repositorio**:
```bash
git clone [URL_DEL_REPOSITORIO]
cd sudoku-game
```

2. **Compila el proyecto**:
```bash
javac -d out src/consola/*.java src/interfazGrafica/*.java
```

3. **Ejecuta la aplicación**:

**Versión GUI** (Recomendada):
```bash
java -cp out interfazGrafica.SudokuGUI
```

**Versión Consola**:
```bash
java -cp out consola.SudokuConsola
```

### 🔧 Ejecución desde IDE
1. Importa el proyecto en tu IDE favorito
2. Ejecuta la clase `SudokuGUI.main()` para la versión gráfica
3. O ejecuta `SudokuConsola.main()` para la versión de consola

## 🎨 Interfaces Disponibles

### 🖥️ Interfaz Gráfica (GUI)
- **Diseño Moderno**: Interfaz Swing con diseño limpio y intuitivo
- **Interacción Visual**: Clicks para seleccionar celdas y números
- **Feedback Visual**: Colores diferenciados para números fijos y del usuario
- **Navegación Fluida**: Transiciones suaves entre paneles

#### 🎨 Esquema de Colores
- 🔵 **Azul**: Números ingresados por el usuario
- ⚫ **Gris Oscuro**: Números fijos del puzzle
- 🔴 **Rojo**: Números de la solución (cuando se usa "Ver Solución")

### 💻 Interfaz de Consola
- **Arte ASCII**: Representación visual clara del tablero
- **Entrada de Texto**: Navegación mediante coordenadas y números
- **Validación Robusta**: Manejo completo de errores de entrada
- **Mensajes Informativos**: Feedback claro sobre cada jugada

#### 📝 Ejemplo de Tablero en Consola
```
+-------+-------+-------+
| 5 3 . | . 7 . | . . . |
| 6 . . | 1 9 5 | . . . |
| . 9 8 | . . . | . 6 . |
+-------+-------+-------+
| 8 . . | . 6 . | . . 3 |
| 4 . . | 8 . 3 | . . 1 |
| 7 . . | . 2 . | . . 6 |
+-------+-------+-------+
| . 6 . | . . . | 2 8 . |
| . . . | 4 1 9 | . . 5 |
| . . . | . 8 . | . 7 9 |
+-------+-------+-------+
```

## ⚙️ Configuración

### 🎚️ Niveles de Dificultad

| Dificultad | Celdas Pre-llenadas | Porcentaje | Descripción |
|------------|--------------------:|------------|-------------|
| 🟢 Fácil   | 51/81              | ~63%       | Ideal para principiantes |
| 🟡 Medio   | 41/81              | ~51%       | Desafío equilibrado |
| 🔴 Difícil | 31/81              | ~38%       | Para expertos |

### 🔧 Personalización

Para modificar los niveles de dificultad, edita el método `rellenarDesdeSolucion()` en `Sudoku.java`:

```java
switch (dificultadNormalizada) {
    case "facil":   celdasARellenar = 51; break;
    case "medio":   celdasARellenar = 41; break;
    case "dificil": celdasARellenar = 31; break;
}
```

## 🔧 Desarrollo

### 🏗️ Arquitectura de Componentes

#### 📊 Modelos de Datos
- **`puntoMatriz`**: Sistema de coordenadas (fila, columna)
- **`Sudoku`**: Gestión del estado del juego
- **`SudokuResuelto`**: Generación de puzzles válidos
- **`ValidadorSudoku`**: Aplicación de reglas

#### 🎮 Controladores
- **`SudokuGUI`**: Controlador principal para interfaz gráfica
- **`SudokuConsola`**: Controlador para interfaz de consola

#### 🖼️ Vistas
- **Paneles GUI**: `DifficultyPanel`, `GamePanel`, `NumberSelectionPanel`
- **Vista Consola**: Representación textual del tablero

### 🧠 Algoritmo de Generación
El algoritmo utiliza una estrategia eficiente de dos fases:

1. **Pre-llenado Diagonal**: Llena los bloques 3×3 diagonales con números aleatorios
2. **Backtracking**: Completa el resto del puzzle usando retroceso con aleatorización

```java
// Fase 1: Llenar bloques diagonales (no interfieren entre sí)
rellenarBloquesDiagonales();

// Fase 2: Completar con backtracking
generarSudokuResuelto();
```

### 🔍 Sistema de Validación
Implementa validación de dos capas:

1. **Validación de Reglas**: Verifica restricciones de Sudoku
2. **Validación de Solución**: Confirma corrección contra la solución generada

### 🎯 Extensibilidad
La arquitectura modular permite fáciles extensiones:

- **Nuevos Niveles**: Agregar casos al switch de dificultad
- **Validación Alternativa**: Implementar `IValidadorSudoku` con reglas custom
- **Nuevas UI**: Implementar `IControladorJuego` para web/móvil
- **Funciones Adicionales**: Agregar pistas, temporizadores, puntuación
- **Persistencia**: Añadir guardado/carga a través de nuevas interfaces

## 📖 Documentación Técnica

### 🔌 Interfaces Principales

#### `ISudoku` - Operaciones del Juego
```java
void rellenarNumSudoku(puntoMatriz xy, int valor);
boolean validarNumSudoku(puntoMatriz xy, int valor);
int[][] getSudokuUsuario();
void rellenarDesdeSolucion(String dificultad, ISudokuResuelto solucion);
```

#### `IValidadorSudoku` - Validación
```java
boolean comprobarNumeroCorrecto(puntoMatriz xy, int valor, ISudokuResuelto solucion);
boolean validarNumSudoku(puntoMatriz xy, int valor, int[][] sudoku);
```

#### `IControladorJuego` - Control
```java
void startGame(String difficulty);
void showNumberSelectionDialog(puntoMatriz cell);
void processNumberSelection(int number);
void showDifficultyPanel();
```

### 💡 Ejemplos de Uso

#### Uso Programático
```java
// Crear y configurar juego
Sudoku juego = new Sudoku();
SudokuResuelto solucion = new SudokuResuelto();
juego.rellenarDesdeSolucion("medio", solucion);

// Realizar una jugada
puntoMatriz posicion = new puntoMatriz(0, 0);
if (juego.validarNumSudoku(posicion, 5)) {
    juego.rellenarNumSudoku(posicion, 5);
}

// Verificar si la jugada es correcta
boolean esCorrecto = juego.comprobarNumeroCorrecto(posicion, 5, solucion);
```

#### Lanzar Interfaces
```java
// Versión GUI
SwingUtilities.invokeLater(() -> new SudokuGUI());

// Versión Consola
SudokuConsola juego = new SudokuConsola();
SudokuResuelto solucion = new SudokuResuelto();
juego.iniciar("medio", solucion);
```

---

## 🏆 Características Destacadas

### ✅ Buenas Prácticas Implementadas
- **Principios SOLID**: Especialmente ISP y SRP
- **Separación de Responsabilidades**: Modelo, Vista, Controlador bien definidos
- **Manejo de Errores**: Validación robusta en ambas interfaces
- **Código Limpio**: Nombres descriptivos y estructura clara
- **Reutilización**: Lógica core compartida entre interfaces

### 🚀 Rendimiento y Eficiencia
- **Generación Optimizada**: Algoritmo eficiente de creación de puzzles
- **Validación Rápida**: Verificaciones O(1) para reglas básicas
- **Gestión de Memoria**: Sin almacenamiento persistente, operaciones en memoria
- **UI Responsiva**: Actualizaciones inmediatas en tiempo real

---

**🎮 ¡Disfruta jugando Sudoku y explorando el código!** 

Este proyecto demuestra una implementación sólida de principios de ingeniería de software con una experiencia de usuario engaging en múltiples interfaces.
