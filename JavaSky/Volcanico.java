import java.util.Scanner;

public class Volcanico extends Planeta {
    private int temperatura;
    private double consumoEnergia;
    private Scanner scanner;
    private int platino;
    
    /**
     * Constructor de Volcanico.
     * @param radio El radio del planeta.
     * @param cristalesDeHidrogeno La cantidad de cristales de hidrógeno en el planeta.
     * @param floresDeSodio La cantidad de flores de sodio en el planeta.
     * @param actividadVolcanica El nivel de actividad volcánica del planeta.
     */
    public Volcanico(int radio, int cristalesDeHidrogeno, int floresDeSodio, int temperatura) {
        super(radio, cristalesDeHidrogeno, floresDeSodio, "\u001B[31mVolcánico\u001B[0m");
        this.temperatura = temperatura;
        this.consumoEnergia = 0.08 * temperatura;
        this.scanner = new Scanner(System.in);
        this.platino = (int) (0.25 * (4 * Math.PI * Math.pow(radio, 2))- 20.5*temperatura);
    }

    // Getters y Setters

    /**
     * Obtiene la temperatura del planeta.
     * @return La temperatura del planeta.
     */
    public int getTemperatura() {
        return temperatura;
    }

    /**
     * Establece la temperatura del planeta.
     * @param temperatura La nueva temperatura del planeta.
     */
    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    /**
     * Obtiene la cantidad de platino en el planeta.
     * @return La cantidad de platino.
     */
    public int getPlatino() {
        return this.platino;
    }

    /**
     * Resta una cantidad de platino del planeta.
     * @param cantidad La cantidad de platino a restar.
     */
    public void restarPlatino(int platino){
        this.platino -= platino;
    }



    /**
     * Obtiene el consumo de energía del planeta.
     * @return El consumo de energía.
     */
    public double getConsumoEnergia() {
        return this.consumoEnergia;
    }

    /**
     * Método para establecer el consumo de energía del jugador en el planeta Volcanico.
     * @param jugador El jugador que visita el planeta.
     */
    public void setConsumoEnergia(Jugador jugador) {
        jugador.setConsumoEnergia(consumoEnergia);
    }

    /**
     * Método para visitar el planeta Volcanico, informa sobre el planeta.
     * @param jugador El jugador que visita el planeta.
     * @return true si la visita es exitosa, false en caso contrario.
     */
    @Override
    public boolean visitar(Jugador jugador) {
        consumoEnergia = 0.08 * Math.abs(temperatura);  // Consumo basado en la temperatura
        if (temperatura > 180) {
            System.out.println("\u001B[31mAdvertencia:\u001B[0m La temperatura es extremadamente alta. El exotraje puede fallar.");
        }
        System.out.println("La temperatura es de " + temperatura + " °C. Vas a consumir " + String.format("%.2f", consumoEnergia) + " unidades de energía al extraer recursos.");
        jugador.setConsumoEnergia(consumoEnergia);

        // Límite de extracción basado en la energía del jugador
        int limite = (int) ((2 * jugador.getUnidadesEnergia() * 100) / ((consumoEnergia) * (1 - jugador.getEficiencia())));
        System.out.println("La nave hace un anuncio: \u001B[31mAviso: altas probabilidades de derretir el exotraje si extraes una cantidad de recursos superior a " + limite + "\u001B[0m");

        return true;
    }

    /**
     * Método para extraer recursos del planeta Volcanico.
     * @param tipo El tipo de recurso a extraer.
     * @return La cantidad de recursos extraídos.
     */
    @Override
    public int extraerRecursos(int tipoRecurso) {
        // Extracción similar a Helado pero con advertencias por la temperatura
        switch (tipoRecurso) {
            case 1:
                System.out.println("Has detectado " + getCristalesDeHidrogeno() + " Cristales de hidrógeno en el planeta. ¿Cuánto deseas extraer?");
                break;
            case 2: 
                System.out.println("No has podido encontrar Flores de Sodio mediante el sondeo... Parece que tales flores no pueden germinar con tan altas temperaturas.");
                return 0;
            case 3:
                System.out.println("Has detectado " + getPlatino() + " Platino en el planeta. ¿Cuánto deseas extraer?");
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
            case 3:
                if (getPlatino() - cantidad >= 0) {
                    restarPlatino(cantidad);
                    System.out.println("Has extraído " + cantidad + " de Platino.");
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
     * Devuelve una representación en cadena del objeto Volcanico.
     * @return Una cadena que representa el objeto Volcanico.
     */
    @Override
    public String toString() {
        return "\u001B[31mVolcánico [radio=" + getRadio() + ", cristales de hidrógeno=" + getCristalesDeHidrogeno() + ", platino=" + getPlatino() + ", temperatura=" + getTemperatura() + " °C]\u001B[0m";
    }

    /**
     * Método para salir del planeta Volcanico.
     * @return true si la salida es exitosa, false en caso contrario.
     */
    @Override
    public boolean salir() {
        System.out.println("Saliendo del planeta Volcánico.");
        return true;
    }
}