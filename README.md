# Tarea3
Integrantes:
Javiera Antonia Diaz Grandon 
Tomas Ignacio Pizarro Abarca
Pablo Sebastian Bascuñan Espina

### Para ejecutar el proyecto:
1. Clonar el repositorio.
2. Abrir el proyecto en un IDE (intelliJ IDEA recomendado).
3. Configurar el SDK (java 17 o superior).
4. Ejecutar la clase "Main" ubicada en "src/main/java/Tarea2/Main.java".

### Funcionamiento:

1.La maquina expendedora puede recibir las monedas presionandolas o insertandolas en la ranura de una en una para realizar la comprar.
2.Posterior a la seleccion del producto si el saldo fue suficiente, se entregara el producto en la ranura para que el cliente pueda recoger la compra.
3.Luego de que recoja la compra, el cliente puede consumir su producto o hacer otra compra.

El **diagrama UML** se completo mediante las herramientas de git:

### Diagrama principal
```mermaid

classDiagram
    direction TB

    %% GRUPO INTERFAZ GRÁFICA (GUI)
    subgraph Interfaz_Grafica
        Ventana *-- PanelPrincipal : posee
        PanelPrincipal *-- PanelExpendedor : posee
        PanelPrincipal *-- PanelComprador : posee
        PanelExpendedor *-- Estante : posee
    end

    %% GRUPO SISTEMA CENTRAL LÓGICO
    subgraph Sistema_Logico
        PanelPrincipal --> Expendedor : instancia y conecta
        PanelPrincipal --> Comprador : instancia y conecta
        PanelExpendedor --> Expendedor : interactua / consulta
        PanelComprador --> Comprador : interactua / consulta
    end

    %% GRUPO PRODUCTOS
    subgraph Jerarquia_Productos
        Producto <|-- Bebida
        Producto <|-- Dulce
        Bebida <|-- CocaCola
        Bebida <|-- Sprite
        Bebida <|-- Fanta
        Dulce <|-- Snickers
        Dulce <|-- Super8
    end

    %% GRUPO MONEDAS
    subgraph Jerarquia_Monedas
        Moneda <|-- Moneda100
        Moneda <|-- Moneda500
        Moneda <|-- Moneda1000
        Comparable <|.. Moneda : implementa 
    end

    %% RELACIONES ESTRUCTURALES LÓGICAS
    Expendedor *-- Deposito : posee multiples
    Comprador *-- Deposito : posee monedero
    Deposito ..> Producto : almacena
    Deposito ..> Moneda : almacena

    %% DEFINICIONES CLASES GUI (SWING)
    class Ventana {
        -PanelPrincipal pri
        +Ventana()
    }

    class PanelPrincipal {
        -PanelComprador com
        -PanelExpendedor exp
        -Expendedor llenarExp
        +PanelPrincipal()
        +getPanelComprador() PanelComprador
    }

    class PanelExpendedor {
        -Expendedor exp
        -Estante[] estantes
        +PanelExpendedor(exp: Expendedor)
        -crearBotonesCompra() void
        +obtenerImagen(deposito: Deposito) Image
        +obtenerImagenDeProducto(p: Producto) Image
    }

    class PanelComprador {
        -Comprador com
        -Expendedor exp
        -JButton botonConsumir
        +Moneda monedaSeleccionada$
        +PanelComprador(comprador: Comprador, expendedor: Expendedor)
        -obtenerImagenProducto(p: Producto) Image
        +getCom() Comprador
    }

    class Estante {
        -Deposito[] depositos
        -int posY
        +Estante(depositos: Deposito[], posY: int)
        +dibujar(g2d: Graphics2D, panel: JPanel) void
    }

    %% DEFINICIONES DE CLASES LÓGICAS
    class Expendedor {
        -Deposito<Producto> coca
        -Deposito<Producto> sprite
        -Deposito<Producto> fanta
        -Deposito<Producto> super8
        -Deposito<Producto> snickers
        -Deposito<Moneda> monVu
        -Deposito<Producto> depProducto
        -int saldo
        +Expendedor(num: int)
        +comprarProducto(TipoProducto: EnumProducto) void
        +ingresarMoneda(m: Moneda) void
        +getVuelto() Moneda
        +getSaldo() int
        +getDepProducto() Deposito<Producto>
    }

    class Comprador {
        -String tipo
        -Deposito<Moneda> monedero
        -Producto productoListo
        +Comprador()
        +escogerMoneda() Moneda
        +recogerVuelto(m: Moneda) void
        +recogerProducto(p: Producto) void
        +getMonedero() Deposito<Moneda>
        +getProductoListo() Producto
        +getTipo() String
    }

    class Producto {
        <<abstract>>
        -int serie
        +Producto(serie: int)
        +consumir()* String
        +getSerie() int
    }

    class Bebida {
        <<abstract>>
        +Bebida(s: int)
    }

    class Dulce {
        <<abstract>>
        +Dulce(s: int)
    }

    class CocaCola {
        +CocaCola(s: int)
        +consumir() String
    }

    class Sprite {
        +Sprite(s: int)
        +consumir() String
    }

    class Fanta {
        +Fanta(s: int)
        +consumir() String
    }

    class Snickers {
        +Snickers(s: int)
        +consumir() String
    }

    class Super8 {
        +Super8(s: int)
        +consumir() String
    }

    class Comparable<Moneda> { 
        <<interface>> 
        +compareTo(m: Moneda) int 
    } 

    class Moneda {
        <<abstract>>
        -int serie
        +Moneda(serie: int)
        +getValor()* int
        +getSerie() int
        +compareTo(m: Moneda) int
    }
    class Moneda100 {
        +Moneda100(serie: int)
        +getValor() int
    }
    class Moneda500 {
        +Moneda500(serie: int)
        +getValor() int
    }
    class Moneda1000 {
        +Moneda1000(serie: int)
        +getValor() int
    }

    class Deposito<T> {
        -ArrayList<T> al
        +addElemento(obj: T) void
        +getElemento() T
        +getElementoEn(index: int) T
        +retirarElemento(obj: T) void
        +tamaño() int
        +estaVacio() boolean
    }

    class EnumProducto {
        <<enumeration>>
        COCA
        SPRITE
        FANTA
        SNICKERS
        SUPER8
        -int precio
        +getPrecio() int
    }

    %% EXCEPCIONES Y DEPENDENCIAS
    class RuntimeException { <<Java Class>> }
    
    RuntimeException <|-- PagoIncorrectoException
    RuntimeException <|-- NoHayProductoException
    RuntimeException <|-- PagoInsuficienteException

    Expendedor ..> EnumProducto : usa
    PanelExpendedor ..> EnumProducto : usa
    Expendedor ..> PagoIncorrectoException : throws
    Expendedor ..> NoHayProductoException : throws
    Expendedor ..> PagoInsuficienteException : throws
