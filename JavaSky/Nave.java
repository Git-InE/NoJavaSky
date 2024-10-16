public class Nave {
    private String nombreNave;
    private double unidadesCombustible;
    private double eficienciaPropulsor;
    private double CombustibleMax;

    /**
     * Constructor de la clase Nave.
     * @param nombreNave El nombre de la nave.
     */
    public Nave(String nombreNave) {
        this.nombreNave = nombreNave;
        this.unidadesCombustible = 100;  // Combustible inicial
        this.CombustibleMax = this.unidadesCombustible;
        this.eficienciaPropulsor = 0;
    }

    /**
     * Obtiene el combustible máximo de la nave.
     * @return El combustible máximo.
     */
    public double getCombustibleMaximo() {
        return CombustibleMax;
    }

    /**
     * Obtiene el nombre de la nave.
     * @return El nombre de la nave.
     */
    public String getNombreNave() {
        return nombreNave;
    }

    /**
     * Obtiene las unidades de combustible actuales de la nave.
     * @return Las unidades de combustible actuales.
     */
    public double getCombustible() {
        return unidadesCombustible;
    }

    /**
     * Establece las unidades de combustible de la nave.
     * @param unidadesCombustible Las unidades de combustible a establecer.
     */
    public void setCombustible(double unidadesCombustible){
        this.unidadesCombustible = unidadesCombustible;
    }

    /**
     * Obtiene la eficiencia del propulsor de la nave.
     * @return La eficiencia del propulsor.
     */
    public double getEficiencia() {
        return eficienciaPropulsor;
    }

    /**
     * Establece la eficiencia del propulsor de la nave.
     * @param eficienciaPropulsor La eficiencia del propulsor a establecer.
     */
    public void setEficiencia(double eficienciaPropulsor) {
        this.eficienciaPropulsor = eficienciaPropulsor;
        if (this.eficienciaPropulsor > 1){this.eficienciaPropulsor = 1;}
    }
    //UTILIDADES

    /**
     * Recarga los propulsores de la nave.
     * @param unidadesHidrogeno Las unidades de hidrógeno a utilizar para la recarga.
     */
    public void recargarPropulsores(int unidadesHidrogeno){
        this.unidadesCombustible += unidadesHidrogeno;
        if (this.unidadesCombustible > 100) {
            this.unidadesCombustible = 100; 
        }
    }

    /**
     * Permite a la nave viajar a otro planeta.
     * @param mapa El mapa galáctico.
     * @param direccion La dirección del viaje.
     * @param tamanoSalto El tamaño del salto.
     * @return Un entero que indica el resultado del viaje.
     */
    public int viajarPlaneta(MapaGalactico mapa, int direccion, int tamanoSalto) { //ES INT SOLO PARA VERIFICAR EL CASO DONDE SE EQUIVOQUEN EN EL INPUT, NO DESCUENTEN PLS :C
        double unidadesConsumidas = 0.75 * Math.pow(tamanoSalto, 2) * (1 - eficienciaPropulsor);

        if (this.unidadesCombustible - unidadesConsumidas >= 0) {
            int nuevaPosicion = mapa.getPosicion() + (direccion * tamanoSalto);
            if (nuevaPosicion >= 0 && nuevaPosicion < mapa.getPlanetas().size()) {
                mapa.setPosicion(nuevaPosicion);
                this.unidadesCombustible -= unidadesConsumidas;
                System.out.println("La " + nombreNave + " ha consumido " + unidadesConsumidas + " unidades de combustible.");
                System.out.println("Ahora te encuentras viajando a un planeta de tipo " + mapa.getPlanetaActual(nuevaPosicion)+".");
                return 0;
            } else {
                System.out.println("La posición " + nuevaPosicion + " no es válida.");
                return 1;
            }
        } else {
            VisualEffects.mostrarTextoConColor("Has intentado realizar el salto, pero lastimosamente, se te ha agotado el combustible en la mitad del camino...", "rojo");
            System.out.println("Afortunadamente, la "+ nombreNave +" cuenta con un sistema de emergencias, el cual te ha devuelto al primer planeta visitado...");
            setCombustible(0);
            return 2;
        }
    }

    /**
     * Activa el sistema de emergencia de la nave.
     * @param mapa El mapa galáctico.
     * @param jugador El jugador que utiliza la nave.
     */
    public void SistemaEmergencia(MapaGalactico mapa, Jugador jugador){
        System.out.println("Se ha activado el sistema de emergencia de tu nave, el cual te ha devuelto al primer planeta visitado");
        jugador.reiniciarInventario();
        jugador.setUnidadesEnergia(jugador.getEnergiaMaxima());
        mapa.setPosicion(0);
        this.unidadesCombustible = this.CombustibleMax;
    }
}