import java.util.Scanner;

public class Oceanico extends Planeta implements tieneAsentamientos {
    private int profundidad;
    private double consumoEnergia;
    private Scanner scanner;
    private boolean primeraVisita;
    private int cantidadMejoras;
    private boolean llaveObtenida;

    /**
     * Constructor de Oceanico.
     * @param radio El radio del planeta.
     * @param cristalesDeHidrogeno La cantidad de cristales de hidrógeno en el planeta.
     * @param floresDeSodio La cantidad de flores de sodio en el planeta.
     * @param profundidad La profundidad del océano en el planeta.
     */
    public Oceanico(int radio, int cristalesDeHidrogeno, int floresDeSodio, int profundidad) {
        super(radio, cristalesDeHidrogeno, floresDeSodio, "\u001B[34mOceánico\u001B[0m");
        this.profundidad = profundidad;
        this.consumoEnergia = (0.002 * Math.pow(profundidad, 2)/10); //PELIGRO, VALORES MUY ALTOS! dividir por constante para un valor algo mas aceptable
        this.scanner = new Scanner(System.in);
        this.primeraVisita = true;
        this.cantidadMejoras = 0;
        this.llaveObtenida = false;
    }

    // Getters y Setters

    /**
     * Obtiene la profundidad del océano en el planeta.
     * @return La profundidad del océano.
     */
    public int getProfundidad() {
        return profundidad;
    }

    /**
     * Establece la profundidad del océano en el planeta.
     * @param profundidad la profundidad.
     */
    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }

    /**
     * retorna el consumo de energía en el planeta.
     * @param jugador El objeto Jugador.
     */
    public double getConsumoEnergia() {
        return this.consumoEnergia; 
    }

     /**
     * Establece el consumo de energía del jugador en el planeta.
     * @param jugador El objeto Jugador.
     */
    public void setConsumoEnergia(Jugador jugador){
        jugador.setConsumoEnergia(consumoEnergia);
    }
    /**
     * Visita el planeta Oceanico.
     * @param jugador El objeto Jugador.
     * @return true si la visita es exitosa.
     */
    @Override
    public boolean visitar(Jugador jugador) {
        if (profundidad > 800) {
            System.out.println("\u001B[31mAdvertencia:\u001B[0m El planeta es extremadamente profundo! El exotraje sufrirá una gran presión. te recomiendo mejorar tu exotraje antes de continuar.");
            VisualEffects.mostrarTextoConColor("Tal vez si te haces amigo de los habitantes de este planeta, puedas conseguir algo especial ;)", "azul");
        }
        System.out.println("La profundidad es de " + profundidad + " metros. Vas a consumir " + String.format("%.2f", consumoEnergia) + " unidades de energía al extraer recursos.");

        int limite = (int) ((2 * jugador.getUnidadesEnergia()*100) / ((consumoEnergia ) * (1 - jugador.getEficiencia())));
        System.out.println("La nave hace un anuncio: \u001B[31mAviso: altas probabilidades de destruir el exotraje si extraes una cantidad de recursos superior a " + limite + "\u001B[0m");        
        return true;
    }
    /**
     * Extrae recursos del planeta Oceanico.
     * @param tipoRecurso El tipo de recurso a extraer.
     * @return La cantidad de recursos extraídos.
     */
    @Override
    public int extraerRecursos(int tipoRecurso) {

        switch (tipoRecurso) {
            case 1:
                System.out.println("Has detectado " + getCristalesDeHidrogeno() + " Cristales de hidrógeno en las profundidades del planeta. ¿Cuánto deseas extraer?");
                break;
            case 2:
                System.out.println("Has detectado " + getFloresDeSodio() + " Flores de sodio en las profundidades del planeta. ¿Cuánto deseas extraer?");
                break;
        }
        System.out.println("Ingresa una cantidad (0 para regresar): ");
        int cantidad = scanner.nextInt();
        if (cantidad == 0) {
            System.out.println("Has decidido no extraer recursos.");
            return 0;
        }

        // Verificación de cantidad y actualización de recursos
        switch (tipoRecurso) {
            case 1:
                if (getCristalesDeHidrogeno() - cantidad >= 0) {
                    restarCristalesDeHidrogeno(cantidad);
                    System.out.println("Has extraído " + cantidad + " Cristales de hidrógeno.");
                } else {
                    System.out.println("No hay suficientes recursos.");
                    return 0;
                }
                break;
            case 2:
                if (getFloresDeSodio() - cantidad >= 0) {
                    restarFloresDeSodio(cantidad);
                    System.out.println("Has extraído " + cantidad + " Flores de sodio.");
                } else {
                    System.out.println("No hay suficientes recursos.");
                    return 0;
                }
                break;
            default: return 0;
        }
        return cantidad;
    }

    @Override
    public void visitarAsentamientos(Jugador jugador) {
        System.out.println("Te adentras en las profundidades del planeta buscando alguna señal de vida...");
        try {
            if (primeraVisita){
                VisualEffects.reproducirSonido("bubbles.wav");
                Thread.sleep(3000);
                
                System.out.println("Tras adentrarte en lo mas profundo, encuentras lo que parece ser una cueva, con una luz extraña al interior... Decides entrar.");
                Thread.sleep(3000);
                VisualEffects.reproducirSonido("dialectoOceanico.wav");
                VisualEffects.mostrarTextoConColor("&@$!^$*$%$#@$@%&^ ^%#$#$ @#%$^*!", "azul");
                System.out.println("Te encuentras a un ser acuatico al cual no le entiendes nada, pero por suerte, tu exotraje cuenta con un traductor especial para estos casos.");
                Thread.sleep(3000);
                VisualEffects.reproducirSonido("botonmenu.wav");
                VisualEffects.mostrarTextoConColor("Bienvenido! No sé como lograste llegar acá, no solemos recibir muchas visitas, y mucho menos de seres bipedos y sin agallas...", "azul");
                VisualEffects.mostrarTextoConColor("¿Buscas algo en particular?", "azul");
                primeraVisita = false;   
        
        } else {
            System.out.println("Has regresado al asentamiento alienigena, y te reciben con los brazos abiertos!");
            VisualEffects.mostrarTextoConColor("!@#@$#%!#$ !@$# #@$#@%$# !@$@#$#@%#$!@","azul");
            VisualEffects.reproducirSonido("dialectoOceanico.wav");
            Thread.sleep(2000);
            VisualEffects.reproducirSonido("botonmenu.wav");
            VisualEffects.mostrarTextoConColor("¡Bienvenido de nuevo, ser bipedo!", "azul");
        }
        } catch (InterruptedException e) { Thread.currentThread().interrupt();} 
        System.out.println("¿Qué deseas hacer?");
        System.out.println("1. Comerciar");
        System.out.println("2. Regresar");
        int opcion = scanner.nextInt();
        switch (opcion){
            case 1:
                try {
                    comerciar(jugador);
                } catch (InterruptedException e) { Thread.currentThread().interrupt();}
                break;
            case 2:
                System.out.println("Decides regresar al espacio.");
                break;
        }
    }
    public void comerciar(Jugador jugador) throws InterruptedException {
        VisualEffects.mostrarTextoConColor("@#$#@%$#% 3@% #$ %#$2&^*&^$%", "azul");
        VisualEffects.reproducirSonido("botonmenu.wav");
        VisualEffects.mostrarTextoConColor("¡Claro! Tenemos algunos objetos que te podrian servir!", "azul");
        System.out.println("¿Qué deseas comprar? (Debes regresar a la nave para aplicar las mejoras)");
        boolean comprando = true;
        boolean mensajeLlave = false;
        while (comprando){
            System.out.println("1. Mejorar exotraje (5000 Platino)");
            System.out.println("2. Mejorar nave (5000 Uranio)");
            System.out.println("3. Regresar");
            if (cantidadMejoras >= 5 && !llaveObtenida){
                if (!mensajeLlave){
                    VisualEffects.mostrarTextoConColor("!@#@$#%!#$ !@$# #@$#@%$#!@#$#@$@$@# $#%$#%#$^$%^@$@#$#@%#$!@","azul");
                    VisualEffects.reproducirSonido("dialectoOceanico.wav");
                    Thread.sleep(2000);
                    VisualEffects.mostrarTextoConColor("Hey... ya que hemos hecho un par de intercambios... creo que estas listo para ver esto...", "azul");
                    Thread.sleep(4500);
                    VisualEffects.reproducirSonido("dialectoOceanico.wav");
                    VisualEffects.mostrarTextoConColor("Es una llave capaz de revelar un gran secreto de uno de los planetas radiactivos cercanos, aunque encontrarlo no será facil, pero la recompensa lo vale!","azul");
                    mensajeLlave = true;
                }
                System.out.println("4. Obtener llave");
            }
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    if (jugador.getInventario().getOrDefault("Platino", 0) >= 5000) {
                        jugador.restarMaterial("Platino", 5000);
                        jugador.agregarMaterial("MejoraExotraje", 1); // Agregar mejora al inventario
                        System.out.println("Has comprado una mejora para tu exotraje!");
                        VisualEffects.reproducirSonido("mejora.wav");
                        cantidadMejoras++;
                    } else {
                        VisualEffects.reproducirSonido("error.wav");
                        VisualEffects.mostrarTextoConColor("No tienes suficiente Platino para comprar este objeto.", "rojo");
                    }
                    break;
                case 2:
                    if (jugador.getInventario().getOrDefault("Uranio", 0) >= 5000) {
                        jugador.restarMaterial("Uranio", 5000);
                        jugador.agregarMaterial("MejoraNave", 1); // Agregar mejora al inventario
                        System.out.println("Has comprado una mejora para tu nave!");
                        VisualEffects.reproducirSonido("mejora.wav");
                        cantidadMejoras++;
                    } else {
                        VisualEffects.reproducirSonido("error.wav");
                        VisualEffects.mostrarTextoConColor("No tienes suficiente Uranio para comprar este objeto.", "rojo");
                    }
                    break;
                case 3:
                    System.out.println("Decides no comprar nada.");
                    comprando = false;
                    VisualEffects.reproducirSonido("tornado.wav");
                    break;
                case 4:
                    if (cantidadMejoras >= 5 && !llaveObtenida){
                        VisualEffects.mostrarTextoConColor("Has obtenido la llave! Ahora solo falta encontrar el secreto que guarda...", "verde");
                        jugador.agregarMaterial("Llave antigua", 1);
                        VisualEffects.reproducirSonido("unlockgamblin.wav");
                        VisualEffects.mostrarTextoConColor("¡Recuerda visitar un planeta radiactivo y quizas tengas la suerte de obtener el secreto oculto!", "verde");
                        llaveObtenida = true;
                    } else {
                        System.out.println("Opción no válida.");
                    }
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
        
    }
    /**
     * Sale del planeta Oceanico.
     * @return true si la salida es exitosa(implemente otro metodo equivalente por eso no se usa :p).
     */
    @Override
    public boolean salir() {
        System.out.println("Saliendo de las profundidades del planeta Oceánico.");
        return true;
    }

    /**
     * Devuelve una representación en cadena del planeta Oceanico.
     * @return Una cadena que representa el planeta Oceanico.
     */
    @Override
    public String toString() {
        return "\u001B[34mOceanico [radio=" + getRadio() + ", cristales de hidrógeno=" + getCristalesDeHidrogeno() + ", flores de sodio=" + getFloresDeSodio() + ", profundidad=" + getProfundidad() + " metros]\u001B[0m";
    }
}