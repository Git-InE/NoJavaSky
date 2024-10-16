# Definir el compilador
JAVAC = javac

# Definir las opciones del compilador
JFLAGS = -g -encoding UTF-8  # Añadir -encoding UTF-8

# Definir los archivos .java que forman parte del proyecto
SRCS = NoJavaSky.java Planeta.java Helado.java Oceanico.java Radiactivo.java \
       Volcanico.java CentroGalactico.java MapaGalactico.java Jugador.java Nave.java HandlerPlaneta.java HandlerSpace.java \
       tieneAsentamientos.java VisualEffects.java

# Definir los archivos .class generados
CLASSES = $(SRCS:.java=.class)

# El archivo ejecutable
MAIN = NoJavaSky

# Determinar el comando correcto para eliminar archivos según el SO
RM = rm -f
ifeq ($(OS),Windows_NT)
    RM = del /f
endif

# La regla para compilar todo
all: $(CLASSES)

%.class: %.java
	$(JAVAC) $(JFLAGS) $<   

# Limpiar los archivos compilados
clean:
	@$(RM) *.class   

# Ejecutar el programa con UTF-8
run: all
	java -Dfile.encoding=UTF-8 $(MAIN)   
#make
#make clean
#make run :D