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
    %% Definición de las clases y grupos
    subgraph Paquete GUI
        Ventana
        PanelPrincipal
        PanelExpendedor
        PanelComprador
        Estante
    end

    subgraph Paquete Logica
        Comprador
        Expendedor
        Deposito
        Producto
        Bebida
        Dulce
        CocaCola
        Sprite
        Fanta
        Snickers
        Super8
        Moneda
        Moneda100
        Moneda500
        Moneda1000
        EnumProducto
    end

    %% Relaciones de la GUI
    Ventana *-- PanelPrincipal : contiene
    PanelPrincipal *-- PanelExpendedor : contiene
    PanelPrincipal *-- PanelComprador : contiene
    PanelExpendedor *-- Estante : contiene

    %% Conexión entre GUI y Lógica
    PanelExpendedor --> Expendedor : usa
    PanelComprador --> Comprador : usa
    PanelPrincipal --> Expendedor : instancia

    %% Relaciones de la Lógica
    Expendedor *-- Deposito : almacena
    Comprador *-- Deposito : monedero
    Comprador --> Producto : consume
    Expendedor ..> EnumProducto : catalogo
    
    %% Herencia de Productos
    Producto <|-- Bebida
    Producto <|-- Dulce
    Bebida <|-- CocaCola
    Bebida <|-- Sprite
    Bebida <|-- Fanta
    Dulce <|-- Snickers
    Dulce <|-- Super8

    %% Herencia de Monedas
    Moneda <|-- Moneda100
    Moneda <|-- Moneda500
    Moneda <|-- Moneda1000
