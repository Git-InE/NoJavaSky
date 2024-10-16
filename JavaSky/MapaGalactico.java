
import java.util.ArrayList;
import java.util.Random;
public class MapaGalactico {
    private ArrayList<Planeta> planetas;
    private int posicion;
    private boolean centroGalacticoGenerado;

    /**
     * Constructor de MapaGalactico.
     * Inicializa la lista de planetas y la posición actual.
     */
    public MapaGalactico() {
        planetas = new ArrayList<>();
        posicion = 0;
        centroGalacticoGenerado = false;
        planetas.add(generadorPlaneta());
    }

    /**
     * Agrega un planeta aleatorio a la lista de planetas.
     * @return El planeta generado.
     */
    public Planeta generadorPlaneta() {
        Random rand = new Random();
        int probabilidad = rand.nextInt(100);  // Genera un número entre 0 y 99

        // Generación de un planeta basado en probabilidades
        if (probabilidad < 30) {
            // Planeta Helado (30% de probabilidad)
            int radio = generarNumeroAleatorio(1000, 1000000);
            int cristalesDeHidrogeno = (int) (0.65 * (4 * Math.PI * Math.pow(radio, 2)));
            int floresDeSodio = (int) (0.35 * (4 * Math.PI * Math.pow(radio, 2)));
            int temperatura = generarNumeroAleatorio(-120, -30);  // En grados Celsius
            return new Helado(radio, cristalesDeHidrogeno, floresDeSodio, temperatura);

        } else if (probabilidad < 60) {
            // Planeta Oceánico (30% de probabilidad)
            int radio = generarNumeroAleatorio(10000, 1000000);
            int cristalesDeHidrogeno = (int) (0.2 * (4 * Math.PI * Math.pow(radio, 2)));
            int floresDeSodio = (int) (0.65 * (4 * Math.PI * Math.pow(radio, 2)));
            int profundidad = generarNumeroAleatorio(30, 1000);  // En metros
            return new Oceanico(radio, cristalesDeHidrogeno, floresDeSodio, profundidad);

        } else if (probabilidad < 80) {
            // Planeta Radiactivo (20% de probabilidad)
            int radio = generarNumeroAleatorio(10000, 100000);
            int cristalesDeHidrogeno = (int) (0.2 * (4 * Math.PI * Math.pow(radio, 2)));
            int floresDeSodio = (int) (0.2 * (4 * Math.PI * Math.pow(radio, 2)));
            int radiacion = generarNumeroAleatorio(10, 50);  // En Rad
            return new Radiactivo(radio, cristalesDeHidrogeno, floresDeSodio, radiacion);

        } else if (probabilidad < 99) {
            // Planeta Volcánico (19% de probabilidad)
            int radio = generarNumeroAleatorio(1000, 100000);
            int cristalesDeHidrogeno = (int) (0.3 * (4 * Math.PI * Math.pow(radio, 2)));
            //no hay flores de sodio
            int temperatura = generarNumeroAleatorio(120, 256);  // En grados Celsius
            return new Volcanico(radio, cristalesDeHidrogeno, 0, temperatura);

        } else {
            // Centro Galáctico (1% de probabilidad, solo se genera una vez)
            if (!centroGalacticoGenerado) {
                centroGalacticoGenerado = true;
                return new CentroGalactico();
            } else {
                // Si ya se generó el Centro Galáctico, generar otro planeta Volcánico
                int radio = generarNumeroAleatorio(1000, 100000);
            int cristalesDeHidrogeno = (int) (0.3 * (4 * Math.PI * Math.pow(radio, 2)));
            int temperatura = generarNumeroAleatorio(120, 256);  // En grados Celsius
            return new Volcanico(radio, cristalesDeHidrogeno, 0, temperatura);
            }
        }
    }
    /**
     *  Método auxiliar para generar un número aleatorio entre min y max (inclusive)
     * @param int min: minimo
     * @param int max: maximo
     * @return int: numero aleatorio entre min y max
     */ 
    private int generarNumeroAleatorio(int min, int max) {
        return min + (int) (Math.random() * (max - min + 1));
    }

    /**
     * Obtiene la lista de planetas.
     * @return La lista de planetas.
     */
    public ArrayList<Planeta> getPlanetas() {
        return planetas;
    }

    /**
     * Obtiene el planeta actual en el mapa galáctico.
     * @return El planeta actual.
     */
    public void getMapa() {
        System.out.println("Mapa de la Galaxia: ");
        for (int i = 0; i < planetas.size(); i++) {
            System.out.println(i + ": " + planetas.get(i).getTipoPlaneta());
        }
    }

    /**
     * Obtiene el planeta actual en el mapa galáctico.
     * @return El planeta actual en forma de string.
     */
    public String getPlanetaActual(int posicion){
        return planetas.get(posicion).getTipoPlaneta();
    }

    /**
     * Obtiene la posición actual en el mapa galáctico.
     * @return La posición actual.
     */
    public int getPosicion() {
        return posicion;
    }

    /**
     * Establece la posición actual en el mapa galáctico.
     * @param posicion La posición a establecer.
     */
    public void setPosicion(int nuevaPosicion) {
        if (nuevaPosicion >= 0 && nuevaPosicion < planetas.size()) {
            this.posicion = nuevaPosicion;
        } else {
            System.out.println("Posición no válida.");
        }
    }

    /**
     * Explora el espacio y descubre un nuevo planeta.
     */
    public void explorarEspacio() {
        Planeta nuevoPlaneta = generadorPlaneta(); // Generate new planet
        planetas.add(nuevoPlaneta);
        System.out.println("\nHas descubierto un nuevo planeta de tipo " + nuevoPlaneta.getTipoPlaneta()+"!");
        VisualEffects.reproducirSonido("NivelRpg.wav");
        
    }

    /**
     * Método auxiliar para generar planetas de manera aleatoria.
     * SOLO UTILIZADO PARA PRUEBAS Y PARA FACILITAR LA CORRECCION.
     */
    public void metodoAyudante(){
        for (int i = 0; i < 20; i++){
        planetas.add(generadorPlaneta());
        }
    }

    /**
     * Verifica si el jugador está en el último planeta.
     * @return true si el jugador está en el último planeta, false de lo contrario.
     */
    public boolean estaEnUltimoPlaneta() {
        return posicion == planetas.size() - 1;
    }

    /**
     * Obtiene el último planeta en el mapa galáctico.
     * @return El último planeta.
     */
    public Planeta getUltimoPlaneta() {
        return planetas.get(planetas.size() - 1);
    }
}