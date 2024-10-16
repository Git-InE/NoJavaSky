import java.util.Scanner;

public class HandlerPlaneta {
    private Planeta planetaActual;
    private Jugador jugador;
    private Scanner scanner;

    /**
     * Constructor de HandlerPlaneta.
     * @param planetaActual El planeta actual en el que se encuentra el jugador.
     * @param jugador El jugador que interactúa con el planeta.
     * @param nave La nave del jugador.
     */
    public HandlerPlaneta(Planeta planetaActual, Jugador jugador) {
        this.planetaActual = planetaActual;
        this.jugador = jugador;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Maneja las interacciones del jugador con el planeta.
     * @throws InterruptedException Si alguna animación es interrumpida.
     */
    public void manejarInteraccionesPlaneta() throws InterruptedException {
        boolean continuarEnPlaneta = true;
        boolean exitoEnExtraccion = true;
        VisualEffects.reproducirSonido("landing.wav");
        VisualEffects.mostrarAnimacion("landing");
        planetaActual.setConsumoEnergia(jugador);
        while (continuarEnPlaneta) {
            System.out.println("\nEstás en el planeta " + planetaActual.getTipoPlaneta() + ". ¿Qué deseas hacer?");
            System.out.println("1. Obtener informacion del planeta");
            System.out.println("2. Extraer recursos");
            System.out.println("3. Visitar asentamientos");
            System.out.println("4. Ver estado de "+jugador.getName());
            System.out.println("5. Recargar Exotraje");
            System.out.println("0. Regresar a la nave");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    VisualEffects.reproducirSonido("nivelRpg2.wav");
                    explorarPlaneta();
                    break;
                case 2:
                    exitoEnExtraccion = extraerRecursos();
                    if (!exitoEnExtraccion){ continuarEnPlaneta = false; }
                    break;
                case 3:
                    visitarAsentamientos();
                    break;
                case 4:
                    verEstadoJugador();
                    break;
                case 5:
                    recargarExotraje();
                    break;
                case 0:
                    continuarEnPlaneta = false;  // Vuelve al HandlerSpace
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    /**
     * Muestra información sobre el planeta actual y llama a visitar.
     */
    private void explorarPlaneta() {
        System.out.println("Tras aterrizar, decides explorar el planeta...");
        planetaActual.visitar(jugador);
    }
    /**
     * Maneja la logica detras de extraer recursos.
     */
    private boolean extraerRecursos() throws InterruptedException {
        System.out.println("Selecciona el tipo de recurso que deseas extraer:");
        System.out.println("1. Cristales de hidrógeno");
        System.out.println("2. Flores de sodio");
        int tipoRecurso;
        int cantidad;
        if (planetaActual instanceof Volcanico) {
            System.out.println("3. Platino");
            tipoRecurso = scanner.nextInt();
            cantidad = planetaActual.extraerRecursos(tipoRecurso);
        }
        else if (planetaActual instanceof Radiactivo){
            System.out.println("3. Uranio");
            tipoRecurso = scanner.nextInt();
            if (tipoRecurso == 3){
                tipoRecurso++;
            } else if(tipoRecurso == 4) {
                VisualEffects.mostrarTextoConColor("te lo dejo pasar...", "rojo");
            }
            if (jugador.getInventario().getOrDefault("Llave antigua", 0) > 0){
                cantidad = ((Radiactivo) planetaActual).extraerRecursosAlt(tipoRecurso, jugador);
            } else{
                cantidad = planetaActual.extraerRecursos(tipoRecurso);
            }
        }
        else{
            tipoRecurso = scanner.nextInt();
            cantidad = planetaActual.extraerRecursos(tipoRecurso);     
        }

        double unidadesConsumidas;
        if (cantidad > 0 && 0 < tipoRecurso && tipoRecurso <= 4){
            unidadesConsumidas = jugador.getTasaDeConsumo() * cantidad;

            double energiaActual = jugador.restarEnergia(unidadesConsumidas);
            if (energiaActual >0){
                switch (tipoRecurso){
                    case 1:
                    jugador.agregarMaterial("Cristales de hidrogeno", cantidad);
                    break;
                    case 2:
                    jugador.agregarMaterial("Flores de sodio", cantidad);
                    break;
                    case 3:
                    jugador.agregarMaterial("Platino", cantidad);
                    break;
                    case 4:
                    jugador.agregarMaterial("Uranio", cantidad);
                    break;
                }
            } else {
                VisualEffects.reproducirSonido("deathplayer.wav");
                VisualEffects.mostrarTextoConColor("\nHas intentado extraer demasiados recursos y tu exotraje ha cedido ante las adversidades del clima...", "rojo");
                jugador.setUnidadesEnergia(0);
                return false;

            }
            }
        VisualEffects.reproducirSonido("confirm.wav");
        return true;
        }

    /**
     * Permite al jugador visitar asentamientos en el planeta si es que tiene.
     */
    private void visitarAsentamientos() {
        if (planetaActual instanceof tieneAsentamientos) {
            ((tieneAsentamientos) planetaActual).visitarAsentamientos(jugador);
        } else {
            System.out.println("Este planeta no tiene asentamientos.");
        }
    }

    /**
     * Permite al jugador recargar su exotraje haciendo los calculos necesarios de manera automatica
     * a menos que el jugador prefiera elegir cuantos recursos gastar.
     */
    private void recargarExotraje() throws InterruptedException {
        System.out.println("\nEl exotraje utiliza el Sodio extraido de las Flores de Sodio para abastecerse.");
        System.out.println("¿Deseas que el exotraje seleccione la cantidad de manera automatica?");
        System.out.println("1. Sí");
        System.out.println("2. No, deseo seleccionar la cantidad yo.");
        boolean recargado;
        int opcion = scanner.nextInt();
        double unidadesARecargar;
        switch (opcion){
            case 1:
                unidadesARecargar = (100 - jugador.getUnidadesEnergia())/(0.65 * (1+jugador.getEficiencia()));
                recargado = jugador.restarMaterial("Flores de sodio", (int) unidadesARecargar);
                if (!recargado){
                    VisualEffects.mostrarTextoConColor("No tienes suficientes Flores de Sodio para recargar tu exotraje.", "rojo");
                    return;
                } else {
                    jugador.recargarEnergiaProteccion(unidadesARecargar);
                    break;
                    }
            case 2:
                System.out.println("Ingresa la cantidad de Flores de Sodio que deseas utilizar para recargar tu exotraje:");
                int cantidad = scanner.nextInt();
                unidadesARecargar = 0.65 * cantidad * (1+jugador.getEficiencia());
                recargado = jugador.restarMaterial("Flores de sodio", cantidad);
                if (!recargado){
                    VisualEffects.mostrarTextoConColor("No tienes suficientes Flores de Sodio para recargar tu exotraje.", "rojo");
                    return;
                } else {
                    jugador.recargarEnergiaProteccion(cantidad);
                    break;
                }
        }
        VisualEffects.mostrarTextoConColor("\nTu exotraje ha sido recargado!", "verde");
        VisualEffects.reproducirSonido("recargaHP.wav");
        VisualEffects.mostrarBarraEnergiaJugador(jugador.getUnidadesEnergia(), jugador.getEnergiaMaxima());
    }

    /**
     * Muestra el estado actual del jugador.
     */
    private void verEstadoJugador() throws InterruptedException {
        VisualEffects.mostrarBarraEnergiaJugador(jugador.getUnidadesEnergia(), jugador.getEnergiaMaxima());
        System.out.println("Eficiencia de energía del exotraje: "+ jugador.getEficiencia()*100 + "%");
        VisualEffects.mostrarInventarioJugador(jugador.getInventario(), jugador);
    }
}