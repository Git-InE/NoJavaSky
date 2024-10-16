import java.util.Scanner;

public class NoJavaSky {
    /**
     * Hay que comentar el main? por si acaso...
     * Método principal del programa. Maneja la logica entre estar en la nave y el espacio a estar en tierra dentro de un planeta.
     * Tambien verifica el estado de la nave por si se queda sin energia (muerte).
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        VisualEffects.mostrarTextoConColor("en caso de evaluar en Linux y encontrar error posterior los siguientes dos mensajes, leer README por favor!", "verde");
        System.out.println("Bienvenido a No Java's Sky. ¿Cuál es tu nombre?");
        String nombreJugador = scanner.nextLine();
        System.out.println("Ingresa el nombre de tu nave: (se mostrará como {La (nombre nave)})");
        String nombreNave = scanner.nextLine();

        Jugador jugador = new Jugador(nombreJugador);
        MapaGalactico mapa = new MapaGalactico();
        Nave nave = new Nave(nombreNave);
        Planeta planetaActual;

        boolean juegoEnCurso = true;
        boolean enEspacio = true;

        while (juegoEnCurso) {
            if (enEspacio) {
                HandlerSpace handlerSpace = new HandlerSpace(mapa, nave, jugador);
                juegoEnCurso = handlerSpace.manejarViajeEspacial();  // Manejar la lógica en el espacio
                if (nave.getCombustible() == 0){
                    nave.SistemaEmergencia(mapa, jugador); 
                    Thread.sleep(3500);
                    continue;
                }
                
                enEspacio = false;  // Cambiar a modo planeta
            } else {
                planetaActual = mapa.getPlanetas().get(mapa.getPosicion());
                HandlerPlaneta handlerPlaneta = new HandlerPlaneta(planetaActual, jugador);
                handlerPlaneta.manejarInteraccionesPlaneta();  // Manejar la lógica dentro del planeta
                if (jugador.getUnidadesEnergia() == 0){
                    nave.SistemaEmergencia(mapa, jugador); 
                    Thread.sleep(3500);}
                enEspacio = true;  // Cambiar a modo espacio
            }
        }

        System.out.println("Saliendo del juego...");
        scanner.close();
    }
}