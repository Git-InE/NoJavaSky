import java.util.Scanner;

public class HandlerSpace {
    private MapaGalactico mapa;
    private Nave nave;
    private Scanner scanner;
    private Jugador jugador;

    /**
     * Constructor de HandlerSpace.
     * @param jugador El jugador que interactúa en el espacio.
     * @param nave La nave del jugador.
     * @param mapaGalactico El mapa galáctico que contiene los planetas.
     */
    public HandlerSpace(MapaGalactico mapa, Nave nave, Jugador jugador) {
        this.mapa = mapa;
        this.nave = nave;
        this.scanner = new Scanner(System.in);
        this.jugador = jugador;
    }

    /**
     * Maneja las interacciones del jugador en el espacio. Encargado del final del juego tambien.
     * @throws InterruptedException Si alguna animación es interrumpida.
     */
    public boolean manejarViajeEspacial() throws InterruptedException {
        boolean continuarEnEspacio = true;
        VisualEffects.reproducirSonido("takeoff.wav");
        VisualEffects.mostrarAnimacion("takeoff");
        while (continuarEnEspacio) {
            // Obtener el nombre del planeta que se está orbitando
            Planeta planetaActual = mapa.getPlanetas().get(mapa.getPosicion());
            String nombrePlaneta = planetaActual.getTipoPlaneta();
            if (planetaActual instanceof CentroGalactico){
                if (nave.getEficiencia() < 0.5){
                    VisualEffects.mostrarTextoConColor("ATENCION, ACERCANDOSE AL CENTRO GALACTICO, LA NAVE NO CUENTA CON LA FUERZA DE PROPULSION SUFICIENTE PARA CONTINUAR, REALIZANDO MANIOBRAS EVASIVAS, VIAJANDO AL PLANETA MAS CERCANO", "rojo");
                    VisualEffects.reproducirSonido("ALARM.wav");
                    Thread.sleep(3000);
                    int saltoValido = nave.viajarPlaneta(mapa, -1, 1);
                    if (saltoValido == 0){
                        VisualEffects.mostrarTextoConColor("MANIOBRAS EVASIVAS REALIZADAS CON EXITO, REALIZANDO ATERRIZAJE DE EMERGENCIA", "verde");
                        Thread.sleep(4000);
                        return true;
                    } else if (saltoValido == 1){
                        VisualEffects.mostrarTextoConColor("PROBLEMAS AL INTENTAR REALIZAR MANIOBRA EVASIVA, INTENTANDO NUEVAMENTE", "amarillo");
                        saltoValido = nave.viajarPlaneta(mapa, 1, 1);
                        if (saltoValido == 0){
                            VisualEffects.mostrarTextoConColor("MANIOBRAS EVASIVAS REALIZADAS CON EXITO, REALIZANDO ATERRIZAJE DE EMERGENCIA", "verde");
                            Thread.sleep(2000);
                            return true;
                        }else {
                            VisualEffects.mostrarTextoConColor("HEMOS CHOCADO CON UN ASTEROIDE, ACTIVANDO SISTEMA DE EMERGENCIA", "rojo");
                            VisualEffects.reproducirSonido("ALARM.wav");
                            Thread.sleep(2000);
                            nave.setCombustible(0);
                            continuarEnEspacio = false;
                            return true;
                        }
                    }
                    else if (saltoValido == 2){
                        VisualEffects.reproducirSonido("deathmario.wav");
                        continuarEnEspacio = false;
                        return true;}
                }
                else if (nave.getEficiencia() < 0.99){
                    System.out.println("La "+nave.getNombreNave()+" hace un anuncio:");
                    VisualEffects.reproducirSonido("qemiedo.wav");
                    VisualEffects.mostrarTextoConColor("ATENCION, TE ESTAS ACERCANDO AL CENTRO GALACTICO, SE SIENTE UNA GRAN FUERZA GRAVITATORIA PROVENIENTE DE EL...", "rojo");
                    VisualEffects.mostrarTextoConColor("UNA VEZ DECIDAS ENTRAR NO HAY VUELTA ATRAS, ¿DESEAS CONTINUAR? ", "rojo");
                    System.out.println("1. Sí");
                    System.out.println("2. No, deseo hacer un aterrizaje de emergencia en otro destino.");
                    int opcion = scanner.nextInt();
                    if (opcion == 1){
                        planetaActual.visitar(jugador); //para utilizar algo de centro galactico xd
                        VisualEffects.reproducirSonido("explosion.wav");
                        VisualEffects.mostrarFramesConRetraso("final.txt", 600);
                        VisualEffects.mostrarTextoConColor("Gracias por jugar!", "verde");
                        return false;
                    }
                    else {
                        boolean viajeExitoso = viajarAPlaneta();
                    if (!viajeExitoso) {
                        VisualEffects.mostrarTextoConColor("Has muerto intentando escapar del centro galactico...", "rojo");
                        VisualEffects.reproducirSonido("qemiedo.wav");
                        VisualEffects.mostrarTextoConColor("No todos los finales son felices...", "rojo");
                        VisualEffects.mostrarTextoConColor("Si juegas otra vez, intenta conseguir una eficiencia del 100! el color de este mensaje te dará una pista como...", "azul");
                        VisualEffects.reproducirSonido("woosh.wav");
                        continuarEnEspacio = false; }
                        return true;
                    }
                }
                else { //FINAL SECRETO!!!
                    System.out.println("La "+nave.getNombreNave()+" hace un anuncio:");
                    VisualEffects.reproducirSonido("qemiedo.wav");
                    VisualEffects.mostrarTextoConColor("ATENCION, TE ESTAS ACERCANDO AL CENTRO GALACTICO, SE SIENTE UNA GRAN FUERZA GRAVITATORIA PROVENIENTE DE EL...", "rojo");
                    Thread.sleep(3000);
                    VisualEffects.mostrarTextoConColor("Decides atravesar el anillo de asteroides sin dudarlo, confias demasiado en tu nave...", "verde");
                    Thread.sleep(3000);
                    VisualEffects.reproducirSonido("explosion.wav");
                    VisualEffects.mostrarFramesConRetraso("finalsecreto.txt", 600);
                    VisualEffects.mostrarTextoConColor("Has logrado atravesar el centro galactico!", "verde");
                    VisualEffects.reproducirSonido("moneda.wav");
                    Thread.sleep(2000);
                    VisualEffects.mostrarTextoConColor("Unas horas mas tarde...", "amarillo");
                    VisualEffects.reproducirSonido("piano.wav");
                    Thread.sleep(3500);
                    VisualEffects.mostrarFramesConRetraso("finalsecreto2.txt", 1500);
                    Thread.sleep(2000);
                    VisualEffects.mostrarTextoConColor("Has logrado escapar del centro galactico y regresar a tu sistema solar!", "verde");
                    VisualEffects.mostrarTextoConColor("Gracias por jugar! ", "verde");
                    VisualEffects.reproducirSonido("tada.wav");
                    return false;
                }   
            }
            else{
                System.out.println("\nLa " + nave.getNombreNave() + " se encuentra orbitando el planeta "+ mapa.getPosicion()+". " + nombrePlaneta + ". ¿Qué deseas hacer?");
            System.out.println("1. Ver el mapa galáctico");
            System.out.println("2. Saltar a otro planeta");
            VisualEffects.mostrarTextoConColor("3. Aterrizar en el planeta", "verde");
            System.out.println("4. Ver estado de la nave");
            System.out.println("5. Recargar la nave");
            System.out.println("6. Explorar el espacio (descubrir un nuevo planeta)");
            System.out.println("7. Ver estado de " + jugador.getName());
            System.out.println("8. Aplicar mejoras obtenidas");
            System.out.println("0. Salir del juego");

            int opcion = scanner.nextInt();
            boolean viajeExitoso;
            switch (opcion) {
                case 1:
                    verMapa();
                    break;
                case 2:
                    viajeExitoso = viajarAPlaneta();
                    if (!viajeExitoso) { continuarEnEspacio = false; }
                    break;
                case 3:
                    continuarEnEspacio = false;  // Cambiar al modo planeta
                    break;
                case 4:
                    verEstadoNave();
                    break;
                case 5:
                    recargarNave();
                    break;
                case 6:
                    viajeExitoso = explorarEspacio();
                    if (!viajeExitoso) { continuarEnEspacio = false; }
                    break;
                case 7:
                    verEstadoJugador();
                    break;
                case 8:
                    aplicarMejoras();
                    break;
                case 0:
                    return false;  // Salir del juego
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
    return true;  // Continuar en el juego si no se elige salir
    }


    /**
     * Explora el espacio y maneja las opciones de viaje y generación de planetas.
     * @param opcion La opción seleccionada por el jugador.
     * @return true si la exploración continúa, false si termina.
     * @throws InterruptedException Si alguna animación es interrumpida.
     */
    private boolean explorarEspacio() throws InterruptedException {
        // Verificar si el jugador está en el último planeta
        if (!mapa.estaEnUltimoPlaneta()) {
            System.out.println("Para añadir nuevos planetas a tu mapa debes explorar mas allá de tus limites actuales.");
            System.out.println("¿Deseas viajar al último planeta presente en tu mapa? (1 = Sí, 2 = No)");
            VisualEffects.mostrarTextoConColor("(3 = Soy ayudante, hazme la correccion mas facil :c)", "amarillo");
            int opcion = scanner.nextInt();

            if (opcion == 1) {
                int ultimaPosicion = mapa.getPlanetas().size() - 1;
                int posicionActual = mapa.getPosicion();
                System.out.println("Viajando al planeta mas lejano conocido...");
                int saltoValido = nave.viajarPlaneta(mapa, opcion, ultimaPosicion-posicionActual);
                if (saltoValido == 2){VisualEffects.reproducirSonido("deathmario.wav");
                    return false;}
                else{
                    VisualEffects.animacionSalto();
                    VisualEffects.mostrarBarraEnergia(nave.getCombustible(), nave.getCombustibleMaximo());
                    return true;
                }
            
            } else if (opcion == 3){
                VisualEffects.mostrarTextoConColor("\nINICIANDO MODO FACIL: GENERANDO 20 PLANETAS DE MANERA ALEATORIA", "rojo");
                VisualEffects.reproducirSonido("lesgogamblin.wav");
                mapa.metodoAyudante();
                return true;
            }
            else { return true; }
        } else { 
            mapa.explorarEspacio();
            return true;
        }

    }

    /**
     * Permite al jugador viajar a un planeta.
     * @return true si el viaje es exitoso, false en caso contrario.
     * @throws InterruptedException Si alguna animación es interrumpida.
     */
    private boolean viajarAPlaneta() throws InterruptedException {
        // Preguntar por el tamaño del salto
        System.out.println("Ingresa el tamaño del salto (número de planetas):");
        int tamanoSalto = scanner.nextInt();

        // Preguntar por la dirección del salto (1 para avanzar, 2 para retroceder)
        System.out.println("Ingresa la dirección del salto (1 = avanzar, 2 = retroceder):");
        int direccion = scanner.nextInt();

        // Transformar la entrada de dirección (1 = avanzar, 2 = retroceder) a (1 o -1)
        int direccionConvertida = (direccion == 1) ? 1 : -1;

        // Realizar el viaje utilizando el método viajarPlaneta de la nave
        int saltovalido = nave.viajarPlaneta(mapa, direccionConvertida, tamanoSalto);
        if (saltovalido == 0){
            VisualEffects.animacionSalto();
            VisualEffects.mostrarBarraEnergia(nave.getCombustible(), nave.getCombustibleMaximo());
            return true;
        } else if (saltovalido == 2){
            VisualEffects.reproducirSonido("deathmario.wav");
            return false;}
        return true;
    }

    /**
     * Muestra el mapa galáctico.
     */
    private void verMapa() {
        mapa.getMapa();
    }

    /**
     * Recarga la nave utilizando cristales de hidrógeno.
     * @throws InterruptedException Si alguna animación es interrumpida.
     */
    private void recargarNave() throws InterruptedException {
        System.out.println("La "+ nave.getNombreNave()+ " utiliza el Hidrogeno extraido de los Cristales de Hidrogeno para abastecerse.");
        System.out.println("¿Deseas que que la nave seleccione la cantidad de manera automatica?");
        System.out.println("1. Sí");
        System.out.println("2. No, deseo seleccionar la cantidad yo.");
        boolean recargado;
        double unidadesARecargar;
        int opcion = scanner.nextInt();
        switch (opcion){
            case 1:
                unidadesARecargar = (100 - nave.getCombustible())/(0.6 * (1+nave.getEficiencia()));
                recargado = jugador.restarMaterial("Cristales de hidrogeno", (int) unidadesARecargar);
                if (!recargado){
                    VisualEffects.mostrarTextoConColor("No tienes suficientes Cristales de Hidrogeno para recargar la nave.", "rojo");
                    return;
                } else {
                    nave.recargarPropulsores((int) unidadesARecargar);
                    break;
                    }
            case 2:
                System.out.println("Ingresa la cantidad de Cristales de Hidrogeno que deseas utilizar para recargar a La "+ nave.getNombreNave()+".");
                int cantidad = scanner.nextInt();
                unidadesARecargar = 0.6 * cantidad * (1+ nave.getEficiencia());
                recargado = jugador.restarMaterial("Cristales de hidrogeno", (int) unidadesARecargar);
                if (!recargado){
                    VisualEffects.mostrarTextoConColor("No tienes suficientes Cristales de Hidrogeno para recargar la nave.", "rojo");
                    return;
                } else {
                    nave.recargarPropulsores(cantidad);
                    break;
                }
        }
        VisualEffects.mostrarTextoConColor("\nLa " + nave.getNombreNave() + " ha sido recargada!", "verde");
        VisualEffects.reproducirSonido("progreso.wav");
        VisualEffects.mostrarBarraEnergia(nave.getCombustible(), nave.getCombustibleMaximo());
    }

    /**
     * Aplica las mejoras al jugador y a la nave si están disponibles en el inventario del jugador.
     */
    private void aplicarMejoras() throws InterruptedException {
        boolean mejoraExo = false;
        boolean mejoraNave = false;
        if (jugador.getInventario().getOrDefault("PPE", 0) > 0){
            jugador.setEficiencia(jugador.getEficiencia() + 1); // Aplicar mejora del 100%
            jugador.restarMaterial("PPE", 1);
            VisualEffects.mostrarTextoConColor("Te has equipado un nuevo exotraje potenciado por plutonio! Has aumentado la eficiencia en un 100%!", "verde");
            mejoraExo = true;
            VisualEffects.reproducirSonido("lesgogamblin.wav");
            Thread.sleep(1000);
        }
        if (jugador.getInventario().getOrDefault("NFP", 0) > 0){
            nave.setEficiencia(nave.getEficiencia() + 1); // Aplicar mejora del 100%
            jugador.restarMaterial("NFP", 1);
            VisualEffects.mostrarTextoConColor("Le has equipado a La "+nave.getNombreNave()+ " unos nuevos propulsores! Has aumentado la eficiencia en un 100% gracias a la tecnologia de fusión nuclear!", "verde");
            mejoraExo = true;
            VisualEffects.reproducirSonido("lesgogamblin.wav");
        }
        if (jugador.getInventario().getOrDefault("MejoraExotraje", 0) > 0) {
            jugador.setEficiencia(jugador.getEficiencia() + 0.05); // Aplicar mejora del 5%
            jugador.restarMaterial("MejoraExotraje", 1);
            System.out.println("Se ha aplicado una mejora del 5% al exotraje!");
            mejoraExo = true;
            VisualEffects.reproducirSonido("mejora.wav");
        }
        if (jugador.getInventario().getOrDefault("MejoraNave", 0) > 0) {
            nave.setEficiencia(nave.getEficiencia() + 0.05); // Aplicar mejora del 5%
            jugador.restarMaterial("MejoraNave", 1);
            System.out.println("Se ha aplicado una mejora del 5% a la nave!");
            mejoraNave = true;
            VisualEffects.reproducirSonido("mejora.wav");
        }
        if (!mejoraExo && !mejoraNave) {
            VisualEffects.mostrarTextoConColor("No tienes mejoras para aplicar.", "rojo");
        }
    }

    /**
     * Muestra el estado actual de la nave.
     */
    private void verEstadoNave() throws InterruptedException {
        System.out.println("\nEstado de la nave:");
        System.out.println("Combustible restante:");
        VisualEffects.mostrarBarraEnergia(nave.getCombustible(), nave.getCombustibleMaximo());
        System.out.println("Eficiencia del propulsor: "+String.format("%.2f", nave.getEficiencia()*100) + "%");
        VisualEffects.reproducirSonido("interfaz.wav");
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