import java.util.Scanner;

public class Helado extends Planeta implements tieneAsentamientos {
    private int temperatura;
    private boolean enSuperficie = false;
    private double consumoEnergia;
    private Scanner scanner = new Scanner(System.in);
    private boolean primeraVisita;

     /**
     * Constructor de Helado.
     * @param radio El radio del planeta.
     * @param cristalesDeHidrogeno La cantidad de cristales de hidrógeno en el planeta.
     * @param floresDeSodio La cantidad de flores de sodio en el planeta.
     * @param temperatura La temperatura del planeta.
     */
    public Helado(int radio, int cristalesDeHidrogeno, int floresDeSodio, int temperatura) {
        super(radio, cristalesDeHidrogeno, floresDeSodio, "\u001B[36mHelado\u001B[0m");
        this.temperatura = temperatura;
        this.consumoEnergia = 0.15 * Math.abs(temperatura);
        this.primeraVisita = true;
    }

    /**
     * Obtiene la temperatura del planeta.
     * @return La temperatura del planeta.
     */
    public int getTemperatura() {
        return temperatura;
    }

    /**
     * Establece el consumo de energía del jugador en el planeta.
     * @param jugador El objeto Jugador.
     */
    public void setConsumoEnergia(Jugador jugador){
        jugador.setConsumoEnergia(consumoEnergia);
    }

    /**
     * Visita y da informacion acerca del planeta Helado.
     * @param jugador El objeto Jugador.
     * @return true si la visita es exitosa.
     */
    @Override
    public boolean visitar(Jugador jugador) {
        if (temperatura < -65) {
        System.out.println("\u001B[31mAdvertencia:\u001B[0m El clima es extremadamente frío.");}
        System.out.println("La temperatura es de " + temperatura + "°C. Vas a consumir " + String.format("%.2f", consumoEnergia) + " unidades de energía al extraer recursos.");
        int limite = (int) ((2 * jugador.getUnidadesEnergia()*100) / ((consumoEnergia ) * (1 - jugador.getEficiencia())));
        System.out.println("La nave hace un anuncio: \u001B[31mAviso: altas probabilidades de morir si extraes una cantidad de recursos superior a "+ limite + "\u001B[0m");
        return true;  
    }

    /**
     * Sale del planeta Helado.
     * @return true si la salida es exitosa.
     */
    @Override
    public boolean salir() {
        if (enSuperficie) {
            enSuperficie = false;
            System.out.println("Has dejado el planeta Helado.");
            return true;  // Salida exitosa
        } else {
            System.out.println("No estás en la superficie del planeta.");
            return false;  // No se puede salir porque no se está en la superficie
        }
    }

    /**
     * Extrae recursos del planeta Helado.
     * @param tipoRecurso El tipo de recurso a extraer.
     * @return La cantidad de recursos extraídos.
     */
    @Override
    public int extraerRecursos(int tipoRecurso) {
        
        switch (tipoRecurso){
            case 1:
                System.out.println("Tras un sondeo, logras detectar un total de \u001B[32m" +getCristalesDeHidrogeno()+"\u001B[0m Cristales de hidrogeno. ¿Cuanto deseas extraer?");
                break;
            case 2:
                System.out.println("Tras un sondeo, logras detectar un total de \u001B[32m"+ getFloresDeSodio() + "\u001B[0m Flores de sodio. ¿Cuanto deseas extraer?");
                break;
        }
        System.out.println("Ingresa una cantidad, (0 para regresar):");
        int cantidad = scanner.nextInt();
        if (cantidad == 0) {
            System.out.println("Has decidido no extraer recursos.");
            return 0;
        }
        switch (tipoRecurso){
            case 1:
            if (getCristalesDeHidrogeno() - cantidad > 0){
                restarCristalesDeHidrogeno(cantidad);
                System.out.println("Has extraido " + cantidad + " Cristales de hidrogeno");
                
            } else {
                System.out.println("No hay suficientes recursos para extraer la cantidad deseada.");
                return 0;
            }
                break;
            case 2:
            if (getFloresDeSodio() - cantidad > 0){
                restarFloresDeSodio(cantidad);
                System.out.println("Has extraido " + cantidad + " Flores de sodio");
            } else {
                System.out.println("No hay suficientes recursos para extraer la cantidad deseada.");
                return 0;
            }
                break;
        }
        return cantidad;
    }

    /**
     * Visita los asentamientos en el planeta Helado.
     * @param jugador El objeto Jugador.
     */
    @Override
    public void visitarAsentamientos(Jugador jugador) {
        System.out.println("Te adentras en los frios páramos del planeta buscando alguna señal de vida...");
        try {
            if (primeraVisita){
                VisualEffects.reproducirSonido("winterwinds.wav");
                Thread.sleep(3000);
                
                System.out.println("Tras un tiempo explorando, encuentras lo que parece ser un pequeño asentamiento de alienigenas, que parecen ser amistosos, asi que te acercas");
                Thread.sleep(1000);
                VisualEffects.mostrarTextoConColor("&@$!^$*$%$#@$@%&^ ^%#$#$ @#%$^*!", "verde");
                System.out.println("No entiendes nada, pero por suerte, tu exotraje cuenta con un traductor especial para estos casos.");
                Thread.sleep(2000);
                VisualEffects.reproducirSonido("botonmenu.wav");
                VisualEffects.mostrarTextoConColor("Bienvenido! Hace mucho tiempo no teniamos visitantes de afuera, ¿Buscas algo en particular?", "verde");
                primeraVisita = false;   
        
        } else {
            System.out.println("Has regresado al asentamiento alienigena, y te reciben con los brazos abiertos!");
            VisualEffects.mostrarTextoConColor("!@#@$#%!#$ !@$# #@$#@%$# !@$@#$#@%#$!@","verde");
            Thread.sleep(1000);
            VisualEffects.reproducirSonido("botonmenu.wav");
            VisualEffects.mostrarTextoConColor("¡Bienvenido de nuevo, forastero!", "verde");
        }
        } catch (InterruptedException e) { Thread.currentThread().interrupt();} 
        System.out.println("¿Qué deseas hacer?");
        System.out.println("1. Comerciar");
        System.out.println("2. Regresar");
        int opcion = scanner.nextInt();
        switch (opcion){
            case 1:
                comerciar(jugador);
                break;
            case 2:
                System.out.println("Decides regresar al espacio.");
                break;
        }
    }

    /**
     * Realiza comercio con el jugador en el planeta Helado.
     * @param jugador El objeto Jugador.
     */
    public void comerciar(Jugador jugador) {
        VisualEffects.mostrarTextoConColor("@#$#@%$#% 3@% #$ %#$2&^*&^$%", "verde");
        VisualEffects.reproducirSonido("botonmenu.wav");
        VisualEffects.mostrarTextoConColor("¡Claro! Tenemos algunos objetos que te podrian servir!", "verde");
        System.out.println("¿Qué deseas comprar? (Debes regresar a la nave para aplicar las mejoras)");
        boolean comprando = true;
        while (comprando){
            System.out.println("1. Mejorar exotraje (5000 Platino)");
            System.out.println("2. Mejorar nave (5000 Uranio)");
            System.out.println("3. Regresar");
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    if (jugador.getInventario().getOrDefault("Platino", 0) >= 5000) {
                        jugador.restarMaterial("Platino", 5000);
                        jugador.agregarMaterial("MejoraExotraje", 1); // Agregar mejora al inventario
                        System.out.println("Has comprado una mejora para tu exotraje!");
                        VisualEffects.reproducirSonido("mejora.wav");
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
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
        
    }

    /**
     * Devuelve una representación en cadena del planeta Helado (se utilizo para debuggear).
     * @return Una cadena que representa el planeta Helado.
     */
    @Override
    public String toString() {
        return "\u001B[36mHelado [radio=" + getRadio() + ", cristales de hidrógeno=" + getCristalesDeHidrogeno() + ", flores de sodio=" + getFloresDeSodio() + ", temperatura=" + getTemperatura() + "°C]\u001B[0m";
    }
}