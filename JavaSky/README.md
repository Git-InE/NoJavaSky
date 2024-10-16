============================================================
                     Proyecto NoJavaSky
============================================================
||  :     `  :  |  |+|: | : : :|   .        `              .
||  |:  :    `  |  | :| : | : |:   |  .                    :
||  |:  |  '       ` || | : | |: : |   .  `           .   :.
`'  ||  |  ' |   *    ` : | | :| |*|  :   :               :|
     `  |  : :  |  .      ` ' :| | :| . : :         *   :.||
           | |  |  : .:|       ` | || | : |: |          | ||
 .         + `  |  :  .: .         '| | : :| :    .   |:| ||
         .    ` *|  || :       `    | | :| | :      |:| |  ,
╔────────────────────────────────────────────────────────╗
│_  _ ____     _ ____ _  _ ____ . ____    ____ _  _ _   _│   
│|\ | |  |     | |__| |  | |__| ' [__     [__  |_/   \_/ │
│| \| |__|    _| |  |  \/  |  |   ___]    ___] | \_   |  │ .
╚────────────────────────────────────────────────────────╝  
 '      +        +  :|| |`     :.+. || || | |:`|| `
            .      .||` .    ..|| | |: '` `| | |`  +   *
    +              ||        !|!: `       :| |
        .  ,    .    | .      `|||.:      .||    .      .  
           +          `|.   .  `:|||   + ||'     `
                         `'       `'|.    `:

============================================================

Nombre del Alumno: [Bastian Leandro Rios Lastarria]
Rol: [202351551-5]

============================================================
Descripción del Proyecto
============================================================
El proyecto cuenta con dos finales alternativos y un par de
secretos en algunos planetas, espero que esto lo motive a
investigar!

============================================================
Requisitos del Sistema
============================================================
- Java Development Kit (JDK) versión [openjdk "17.0.6"]
- Sistema operativo:                      
        -WINDOWS 10 con chocolatey y make instalados 
        (o simplemente JDK si no se utiliza el make)
        -LINUX Ubuntu 22.0.4 (problemas detectados)
        Ejecutar comando "sudo apt install alsa-utils pulseaudio"
        en caso de encontrar errores en la ejecución en Linux

============================================================
CONSIDERACIONES ANTES DE EJECUTAR
============================================================

El proyecto fue realizado en el Sistema Operativo de Windows
10, con la version de OpenJDK 17.0.6, durante el testeo
tambien se utilizó una WSL con linux Ubuntu 24.0 y 22.0,
debido al bajo rendimiento del equipo en el que se encontraba
la WSL continué el proyecto solo en windows con chocolatey
para utilizar make, el problema es que ciertas cosas que
vienen por defecto en windows parece que no es asi en WSL o
Linux, por lo tanto pueden existir algunos errores no previstos
SE SUGIERE Y SE PIDE POR FAVOR EN LO POSIBLE, si desea disfrutar
de una mejor experiencia, utilizar Windows, ya que debido a las
limitaciones fisicas de mi equipo con Linux no se que tan bien
corra el programa, sobre todo en WSL ya que he leido que muchos
usuarios presentan los mismos problemas de rendimiento que yo.

- Asegurese que todos los archivos .java se encuentran en la misma carpeta
que makefile y que todos los archivos .txt y .wav se encuentran en la 
carpeta /FX para una correcta ejecución.

- Si se encuentra con errores con el audio, se debe ejecutar el comando
sudo apt install alsa-utils pulseaudio, estos errores solo ocurren con
Linux, si desea una mejor experiencia, repito, utilice Windows.

============================================================
Instrucciones de Compilación y Ejecución
============================================================
0. Primero, unos consejos previos a comenzar, parece que la
terminal presenta algunos problemas al detectar simbolos 
españoles, antes de partir, si desea puede poner en la terminal:
        -Linux: export LC_ALL=en_US.UTF-8
                export LANG=en_US.UTF-8

-Windows: [console]::OutputEncoding = [System.Text.Encoding]::UTF8
Recordar: es completamente opcional, solo para una mejor experiencia

-Es posible que si se evalua en Linux, sobre todo con WSL se encuentre
con problemas de rendimiento, y tal vez errores a la hora de buscar
los archivos de audio .wav, esto es debido a como la WSL maneja la
entrada y salida de archivos. en caso de encontrar errores al inicio
de la partida, luego de que la terminal pregunte por nombre del
jugador y la nave, se debe ejecutar el siguiente comando en Linux:
sudo apt install alsa-utils pulseaudio
por alguna razon al menos mi WSL no soporta archivos de audio de base,
cosa que no ocurre en windows y viene por defecto, esto es solo en caso
de que exista el error previamente mencionado. Por esto mismo se 
recomienda encarecidamente evaluar en Windows 10 para una mejor
experiencia, y que todo salga de manera correcta.
Incluso, para el final del juego al llegar al centro galactico, hay unas
animaciones hechas en ASCII que se coordinan con el sonido de fondo,
esto funciona de manera excelente en Windows pero por alguna razon
de manera descoordinada en Linux...

1.
Para compilar y ejecutar el proyecto, primero debemos considerar:
- Instalar Java 17.0.6 o superior
- Instalar make (puede ser con chocolatey si se está en Windows)

2.
Una vez hecho esto, basta con abrir la terminal, asegurarse de estar
en la carpeta del proyecto y ejecutar el siguiente comando:
- make run
Con esto, el programa deberia compilar y ejecutarse sin ningun problema,
en caso de existir alguno, ejecutar lo siguiente:
- make clean
- make
- make run

======================================================================
CONSIDERACIONES AL MOMENTO DE JUGAR
======================================================================

- En el juego existen 3 finales posibles, 2 de ellos con una animación
en ASCII y sonido de fondo, espero encarecidamente que pueda llegar a
ambos finales y disfrutar de la experiencia, uno de ellos se podria
considerar como final secreto, conseguirlo es más fácil luego de conseguir
el primero, el cual le dará una pista de como llegar al otro.

- En caso de no poder encontrar el secreto oculto en los dos planetas, 
dejaré una seccion con spoilers debajo de este mensaje, solo leer en caso
de no saber que hacer.

=====================================================================
SPOILERS
=====================================================================

- Se ha agregado una opción especial para ayudantes al explorar planetas,
normalmente para explorar el planeta n+1 debe estar previamente en el
planeta n, como se indica en la tarea, pero puede resultar algo lento
y aburrido, asi que al momento de explorar planetas, se ha integrado
una opcion que genera 20 planetas de manera aleatoria de manera inmediata.
Espero que esta opción facilite la exploración y lo motive a sacar ambos
finales!

- El primer secreto se encuentra al visitar un planeta oceánico muy
profundo. éste dará una pista de como llegar al siguiente secreto.

- El segundo secreto, es hacer un minimo de 5 intercambios en el mismo
asentamiento oceánico, donde obtendrá una llave que puede ser utilizada
posteriormente en algun planeta radiactivo.

- El tercer secreto es justamente dentro del planeta radiactivo, donde
al extraer recursos teniendo la llave en el inventario, existe un 30%
de probabilidades de activar un evento especial, donde se le dará la
opción de entrar en una bóveda oculta.

- Una vez hecho todo esto (o haber conseguido una eficiencia de nave
del 100% de otra forma) se desbloqueará el verdadero final del juego.
