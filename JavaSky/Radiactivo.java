import java.util.Random;
import java.util.Scanner;

public class Radiactivo extends Planeta {
    private int radiacion;
    private double consumoEnergia;
    private int uranio;
    private Scanner scanner;

    /**
     * Constructor de Radiactivo.
     * @param radio El radio del planeta.
     * @param cristalesDeHidrogeno La cantidad de cristales de hidrógeno en el planeta.
     * @param floresDeSodio La cantidad de flores de sodio en el planeta.
     * @param radiacion El nivel de radiación del planeta.
     */
    public Radiactivo(int radio, int cristalesDeHidrogeno, int floresDeSodio, int radiacion) {
        super(radio, cristalesDeHidrogeno, floresDeSodio, "\u001B[33mRadiactivo\u001B[0m");
        this.radiacion = radiacion;
        this.consumoEnergia = 0.3 * radiacion;
        this.uranio = (int) (0.25 * (4 * Math.PI * Math.pow(radio, 2)) * radiacion);
        this.scanner = new Scanner(System.in);
    }

    // Getters y Setters

    /**
     * Obtiene el nivel de radiación del planeta.
     * @return El nivel de radiación.
     */
    public int getRadiacion() {
        return radiacion;
    }
    
    /**
     * Establece el nivel de radiación del planeta.
     * @param radiacion El nuevo nivel de radiación.
     */
    public void setRadiacion(int radiacion) {
        this.radiacion = radiacion;
    }
    
    /**
     * Obtiene la cantidad de uranio en el planeta.
     * @return La cantidad de uranio.
     */
    public int getUranio() {
        return uranio;
    }

    /**
     * Resta una cantidad de uranio del planeta.
     * @param cantidad La cantidad de uranio a restar.
     */
    public void restarUranio(int cantidad) {
        this.uranio -= cantidad;
    }


    /**
     * Obtiene el consumo de energía del planeta.
     * @return El consumo de energía.
     */
    public double getConsumoEnergia() {
        return this.consumoEnergia;
    }

    /**
     * Método para establecer el consumo de energía del jugador en el planeta Radiactivo.
     * @param jugador El jugador que visita el planeta.
     */
    public void setConsumoEnergia(Jugador jugador) {
        jugador.setConsumoEnergia(consumoEnergia);
    }

    /**
     * Método para visitar el planeta Radiactivo.
     * @param jugador El jugador que visita el planeta.
     * @return true si la visita es exitosa, false en caso contrario.
     */
    @Override
    public boolean visitar(Jugador jugador) {
        consumoEnergia = 0.15 * Math.abs(radiacion);  // Consumo basado en la radiación
        if (radiacion > 35) {
            System.out.println("\u001B[31mAdvertencia:\u001B[0m La radiación es extremadamente alta. El exotraje está en riesgo de fallar.");
        }
        System.out.println("La radiación es de " + radiacion + " Rad. Vas a consumir " + String.format("%.2f", consumoEnergia) + " unidades de energía al extraer recursos.");
        jugador.setConsumoEnergia(consumoEnergia);

        // Límite de extracción basado en la energía del jugador
        int limite = (int) ((2 * jugador.getUnidadesEnergia() * 100) / ((consumoEnergia) * (1 - jugador.getEficiencia())));
        System.out.println("La nave hace un anuncio: \u001B[31mAviso: altas probabilidades de desintegrar el exotraje si extraes una cantidad de recursos superior a " + limite + "\u001B[0m");

        return true;
    }

    /**
     * Método para extraer recursos del planeta Radiactivo.
     * @param tipoRecurso El tipo de recurso a extraer.
     * @return La cantidad de recursos extraídos.
     */
    @Override
    public int extraerRecursos(int tipoRecurso) {
        switch (tipoRecurso) {
            case 1:
                System.out.println("Has detectado " + getCristalesDeHidrogeno() + " Cristales de hidrógeno en el planeta. ¿Cuánto deseas extraer?");
                break;
            case 2:
                System.out.println("Has detectado " + getFloresDeSodio() + " Flores de sodio en el planeta. ¿Cuánto deseas extraer?");
                break;
            case 4:
                System.out.println("Has detectado " + getUranio() + " Uranio en el planeta. ¿Cuánto deseas extraer?");
                break;
            case 3:
                System.out.println("No hay recursos de este tipo en el planeta.");
                return 0;
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
            case 4:
                if (getUranio() - cantidad >= 0) {
                    restarUranio(cantidad);
                    System.out.println("Has extraído " + cantidad + " Uranio.");
                } else {
                    System.out.println("No hay suficientes recursos.");
                    return 0;
                }
                break;
            default: return 0;
        }

        return cantidad;
    }

    /**
     * Método para extraer recursos del planeta Radiactivo en caso de que se posea la llave. 
     * @param tipoRecurso El tipo de recurso a extraer.
     * @param jugador El jugador que extrae los recursos.
     * existe un 30% de probabilidad de conseguir dos objetos especiales
     * @return La cantidad de recursos extraídos.
     */
    public int extraerRecursosAlt(int tipoRecurso, Jugador jugador) throws InterruptedException {
        switch (tipoRecurso) {
            case 1:
                System.out.println("Has detectado " + getCristalesDeHidrogeno() + " Cristales de hidrógeno en el planeta. ¿Cuánto deseas extraer?");
                break;
            case 2:
                System.out.println("Has detectado " + getFloresDeSodio() + " Flores de sodio en el planeta. ¿Cuánto deseas extraer?");
                break;
            case 4:
                System.out.println("Has detectado " + getUranio() + " Uranio en el planeta. ¿Cuánto deseas extraer?");
                break;
            case 3:
                System.out.println("No hay recursos de este tipo en el planeta.");
                return 0;
        }
        System.out.println("Ingresa una cantidad (0 para regresar): ");
        int cantidad = scanner.nextInt();
        if (cantidad == 0) {
            System.out.println("Has decidido no extraer recursos.");
            return 0;
        }
        Random rand = new Random();
        int probabilidad = rand.nextInt(0, 99);
        if (probabilidad < 29){ // 30% de probabilidad!
            VisualEffects.mostrarTextoConColor("Extrayendo recursos te has topado con una gran bóveda que emite un sonido extraño...", "rojo");
            VisualEffects.reproducirSonido("qemiedo.wav");
            VisualEffects.mostrarTextoConColor("Recuerdas la llave que te dio aquel alien en el asentamiento oceánico...", "rojo");
            Thread.sleep(1000);
            System.out.println("¿Deseas intentar usar la llave?");
            System.out.println("1. Sí");
            System.out.println("2. No");
            int opcion = scanner.nextInt();
            if (opcion == 1){
                if (jugador.getInventario().getOrDefault("Llave antigua", 0) > 0){
                    VisualEffects.mostrarTextoConColor("La llave se ha activado y la bóveda se ha abierto!", "verde");
                    VisualEffects.reproducirSonido("boveda.wav");
                    Thread.sleep(3000);
                    jugador.restarMaterial("Llave antigua", 1);
                    jugador.agregarMaterial("PPE", 1);
                    jugador.agregarMaterial("NFP", 1);
                    System.out.println("Al descender y desempolvar el interior de la bóveda, logras ver dos grandes objetos, en ellos se lee...");
                    Thread.sleep(2000);
                    System.out.println("'Plutonium Powered Exoskeleton'. un exoesqueleto muy potente y eficiente fabricado por una antigua civilización alienigena del planeta...");
                    Thread.sleep(3000);
                    System.out.println("'Nuclear Fusion Propulsor'. Un motor de fusión nuclear que te permitirá viajar a velocidades increíbles!");
                    Thread.sleep(3000);
                    VisualEffects.reproducirSonido("unlockgamblin.wav");
                    VisualEffects.mostrarTextoConColor("Has encontrado grandes mejoras para tu exotraje y nave!", "verde");
                    VisualEffects.mostrarTextoConColor("Vuelve a la nave para aplicarlas!", "verde");
                    return 0;
                } else {
                    VisualEffects.mostrarTextoConColor("No tienes la llave necesaria para abrir la bóveda...", "rojo");
                    return 0;
                }
            } else {
                VisualEffects.mostrarTextoConColor("Has decidido no intentar abrir la bóveda...", "rojo");
                return 0;
            }
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
            case 4:
                if (getUranio() - cantidad >= 0) {
                    restarUranio(cantidad);
                    System.out.println("Has extraído " + cantidad + " Uranio.");
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
    public String toString() {
        return "\u001B[32mRadiactivo [radio=" + getRadio() + ", cristales de hidrógeno=" + getCristalesDeHidrogeno() + ", flores de sodio=" + getFloresDeSodio() + ", uranio=" + getUranio() + ", radiación=" + getRadiacion() + " Rad]\u001B[0m";
    }

    /**
     * Método para salir del planeta Radiactivo.
     * @return true si la salida es exitosa, false en caso contrario.
     */
    @Override
    public boolean salir() {
        System.out.println("Saliendo del planeta Radiactivo.");
        return true;
    }
}