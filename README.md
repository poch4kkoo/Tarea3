# Tarea3
Integrantes:
Javiera Antonia Diaz Grandon 
Tomas Ignacio Pizarro Abarca
Pablo Sebastian Bascuñan Espina

### Para ejecutar el proyecto:
1. Clonar el repositorio.
2. Abrir el proyecto en un IDE (intelliJ IDEA recomendado).
3. Configurar el SDK (java 17 o superior).
4. Ejecutar la clase "Main" ubicada en "src/main/java/org/Main.java".

### Funcionamiento:

1.La maquina expendedora puede recibir las monedas presionandolas e insertandolas en la ranura de una en una para realizar la comprar.
2.Posterior a la seleccion del producto si el saldo fue suficiente, se entregara el producto en la ranura para que el cliente pueda recoger la compra.
3.Luego de que recoja la compra, el cliente puede consumir su producto o hacer otra compra.

El **diagrama UML** se completo mediante las herramientas de git:

### Diagrama principal
```mermaid
classDiagram
    direction TB

    %% Clases Java base
    class JFrame { <<Java Class>> }
    class JPanel { <<Java Class>> }
    class RuntimeException { <<Java Class>> }

    %% Herencias Java
    JFrame <|-- Ventana
    JPanel <|-- PanelPrincipal
    JPanel <|-- PanelExpendedor
    JPanel <|-- PanelComprador

    RuntimeException <|-- NoHayProductoException
    RuntimeException <|-- PagoInsuficienteException
    RuntimeException <|-- PagoIncorrectoException


    %% GUI
    subgraph Interfaz_Grafica
        Ventana *-- PanelPrincipal : posee
        PanelPrincipal *-- PanelExpendedor : posee
        PanelPrincipal *-- PanelComprador : posee
        PanelExpendedor *-- Estante : posee
    end

    subgraph Sistema_Logico
        PanelPrincipal --> Expendedor : instancia y conecta
        PanelPrincipal --> Comprador : instancia y conecta
        PanelExpendedor --> Expendedor : interactua
        PanelComprador --> Comprador : interactua
    end

    subgraph Jerarquia_Productos
        Producto <|-- Bebida
        Producto <|-- Dulce
        Bebida <|-- CocaCola
        Bebida <|-- Sprite
        Bebida <|-- Fanta
        Dulce <|-- Snickers
        Dulce <|-- Super8
    end

    subgraph Jerarquia_Monedas
        Comparable <|.. Moneda : implementa
        Moneda <|-- Moneda100
        Moneda <|-- Moneda500
        Moneda <|-- Moneda1000
    end

    Expendedor *-- Deposito : posee multiples
    Comprador *-- Deposito : posee monedero
    Deposito ..> Producto : almacena
    Deposito ..> Moneda : almacena
    Expendedor ..> EnumProducto : usa
    PanelExpendedor ..> EnumProducto : usa
    Expendedor ..> NoHayProductoException : throws
    Expendedor ..> PagoInsuficienteException : throws

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
        +PanelExpendedor(exp Expendedor)
        -crearBotonesCompra() void
        +obtenerImagen(deposito Deposito) Image
        +obtenerImagenDeProducto(p Producto) Image
        +paintComponent(g Graphics) void
        -dibujarMuebleEstructura(g2d Graphics2D) void
    }

    class PanelComprador {
        -Comprador com
        -JButton botonConsumir
        +Moneda monedaSeleccionada$
        +PanelComprador(comprador Comprador)
        -obtenerImagenProducto(p Producto) Image
        +getCom() Comprador
    }

    class Estante {
        -Deposito[] depositos
        -int yEstante
        +Estante(depositos Deposito[], yEstante int)
        +dibujar(g2d Graphics2D, panel PanelExpendedor) void
    }

    class Expendedor {
        -Deposito coca
        -Deposito sprite
        -Deposito fanta
        -Deposito super8
        -Deposito snickers
        -Deposito monVu
        -Deposito DepMonedas
        -Deposito depProducto
        -Deposito pagoTemporal
        -int saldo
        -int series
        +Expendedor(num int)
        +comprarProducto(tipo EnumProducto) void
        +ingresarMoneda(m Moneda) void
        +rellenarVacios() void
        +getVuelto() Moneda
        +getProducto() Producto
        +getSaldo() int
        +getCoca Deposito
        +getFanta Deposito
        +getSprite Deposito
        +getSnickers Deposito
        +getSuper8 Deposito
        +getDepProducto() Deposito
    }

    class Comprador {
        -String tipo
        -Deposito monedero
        -Producto productoListo
        +Comprador()
        -ordenarMonedero() void
        +recogerVuelto(m Moneda) void
        +recogerProducto(p Producto) void
        +getMonedero() Deposito
        +getProductoListo() Producto
        +getTipo() String
    }

    class Producto {
        <<abstract>>
        -int serie
        +Producto(serie int)
        +consumir()* String
        +getSerie() int
    }

    class Bebida {
        <<abstract>>
        +Bebida(s int)
    }

    class Dulce {
        <<abstract>>
        +Dulce(s int)
    }

    class CocaCola {
        +CocaCola(s int)
        +consumir() String
    }

    class Sprite {
        +Sprite(s int)
        +consumir() String
    }

    class Fanta {
        +Fanta(s int)
        +consumir() String
    }

    class Snickers {
        +Snickers(s int)
        +consumir() String
    }

    class Super8 {
        +Super8(s int)
        +consumir() String
    }

    class Comparable {
        <<interface>>
        +compareTo(m Moneda) int
    }

    class Moneda {
        <<abstract>>
        -int serie
        +Moneda(s int)
        +getValor()* int
        +getSerie() int
        +compareTo(m Moneda) int
    }

    class Moneda100 {
        +Moneda100(s int)
        +getValor() int
    }

    class Moneda500 {
        +Moneda500(s int)
        +getValor() int
    }

    class Moneda1000 {
        +Moneda1000(s int)
        +getValor() int
    }

    class Deposito~T~ {
        -ArrayList~T~ al
        +Deposito()
        +addElemento(obj T) void
        +getElemento() T
        +getElementoEn(index int) T
        +retirarElemento(obj T) void
        +tamano() int
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

    class RuntimeException {
        <<Java Class>>
    }

    RuntimeException <|-- NoHayProductoException
    RuntimeException <|-- PagoInsuficienteException
    RuntimeException <|-- PagoIncorrectoException
```

