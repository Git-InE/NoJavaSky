
public abstract class Planeta {
    private int radio;
    private int cristalesDeHidrogeno;
    private int floresDeSodio;
    private String tipoPlaneta;

    /**
     * Muestra una serie de frames con un retraso específico.
     * @param filePath La ruta del archivo de frames.
     * @param delay El retraso entre frames en milisegundos.
     * @throws InterruptedException Si la animación es interrumpida.
     */
    public Planeta(int radio, int cristalesDeHidrogeno, int floresDeSodio, String tipoPlaneta) {
        this.radio = radio;
        this.cristalesDeHidrogeno = cristalesDeHidrogeno;
        this.floresDeSodio = floresDeSodio;
        this.tipoPlaneta = tipoPlaneta;
    }

    /**
     * Obtiene el radio del planeta.
     * @return El radio del planeta.
     */
    public int getRadio() {
        return radio;
    }

    /**
     * Establece el radio del planeta.
     * @param radio El nuevo radio del planeta.
     */
    public void setRadio(int radio) {
        this.radio = radio;
    }
    /**
     * Obtiene la cantidad de cristales de hidrógeno en el planeta.
     * @return La cantidad de cristales de hidrógeno.
     */
    public int getCristalesDeHidrogeno() {
        return cristalesDeHidrogeno;
    }

    /**
     * Establece la cantidad de cristales de hidrógeno en el planeta.
     * @param cristalesDeHidrogeno La nueva cantidad de cristales de hidrógeno.
     */
    public void setCristalesDeHidrogeno(int cristalesDeHidrogeno) {
        this.cristalesDeHidrogeno = cristalesDeHidrogeno;
    }

    /**
     * Resta una cantidad de cristales de hidrógeno del planeta.
     * @param cantidad La cantidad a restar.
     */
    public void restarCristalesDeHidrogeno(int cantidad){
        this.cristalesDeHidrogeno -= cantidad;
    }

    /**
     * Obtiene la cantidad de flores de sodio en el planeta.
     * @return La cantidad de flores de sodio.
     */
    public int getFloresDeSodio() {
        return floresDeSodio;
    }

    /**
     * Establece la cantidad de flores de sodio en el planeta.
     * @param floresDeSodio La nueva cantidad de flores de sodio.
     */
    public void setFloresDeSodio(int floresDeSodio) {
        this.floresDeSodio = floresDeSodio;
    }

    /**
     * Resta una cantidad de flores de sodio del planeta.
     * @param cantidad La cantidad a restar.
     */
    public void restarFloresDeSodio(int cantidad){
        this.floresDeSodio -= cantidad;
    }

    /**
     * Obtiene el tipo de planeta.
     * @return El tipo de planeta.
     */
    public String getTipoPlaneta(){
        return tipoPlaneta;
    }
    // Métodos abstractos que deben ser implementados por las subclases
    public abstract boolean visitar(Jugador jugador);
    public abstract int extraerRecursos(int tipo);
    public abstract boolean salir();
    public abstract void setConsumoEnergia(Jugador jugador);
}