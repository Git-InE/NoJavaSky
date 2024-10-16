import java.util.HashMap;
import java.util.Map;

public class Jugador {
    private double unidadesEnergia;
    private double energiaMaxima;
    private double eficienciaEnergia;
    private double tasaDeConsumo;
    private String name;
    private Map<String, Integer> inventario;

    /**
     * Constructor de la clase Jugador.
     * @param name El nombre del jugador.
     */
    public Jugador(String name) {
        this.unidadesEnergia = 100.0;
        this.eficienciaEnergia = 0.0;
        this.energiaMaxima = 100.0;
        this.name = name;
        this.inventario = new HashMap<>();
    }

    // Getters y setters

    /**
     * Obtiene las unidades de energía actuales del jugador.
     * @return Las unidades de energía actuales.
     */
    public double getUnidadesEnergia() { return unidadesEnergia; }
    
    /**
     * Establece las unidades de energía del jugador.
     * @param unidadesEnergia Las unidades de energía a establecer.
     */
    public void setUnidadesEnergia(double unidadesEnergia) { this.unidadesEnergia = unidadesEnergia; }

    /**
     * Resta una cantidad de energía al jugador.
     * @param energiaConsumida La cantidad de energía a restar.
     * @return Las unidades de energía restantes.
     */
    public double restarEnergia(double energiaConsumida) { 
        this.unidadesEnergia -= energiaConsumida;
        return this.unidadesEnergia;
    }
    //ENERGIA

    /**
     * Recarga la energía del jugador con protección, asegurando que no exceda el máximo.
     * @param unidadesSodio Las unidades de sodio a utilizar para la recarga.
     */
    public void recargarEnergiaProteccion(int unidadesSodio){
        this.unidadesEnergia += unidadesSodio;
        if (this.unidadesEnergia > 100) {
            this.unidadesEnergia = 100;
        }
    }
    
    /**
     * Obtiene la energía máxima del jugador.
     * @return La energía máxima.
     */
    public double getEnergiaMaxima() { return energiaMaxima;}
    
    /**
     * Obtiene el nombre del jugador.
     * @return El nombre del jugador.
     */
    public String getName(){ return name; }
    
    /**
     * Obtiene la eficiencia de energía del jugador.
     * @return La eficiencia de energía.
     */
    public double getEficiencia() { return eficienciaEnergia; }

    /**
     * Establece la eficiencia de energía del jugador.
     * @param eficiencia La eficiencia de energía a establecer.
     */
    public void setEficiencia(double eficienciaEnergia) { 
        this.eficienciaEnergia = eficienciaEnergia;
        if (this.eficienciaEnergia > 1){
            this.eficienciaEnergia = 1;
        }   
    }
    
    /**
     * Establece la tasa de consumo de energía del jugador.
     * @param tasaDeConsumo La tasa de consumo de energía a establecer.
     */
    public void setConsumoEnergia(double consumoEnergia){
        this.tasaDeConsumo = 0.5 * (consumoEnergia/100) * (1-eficienciaEnergia);
    }

    /**
     * Obtiene la tasa de consumo de energía del jugador.
     * @return La tasa de consumo de energía.
     */
    public double getTasaDeConsumo(){ return tasaDeConsumo; }
    
    //INVENTARIO
    
    /**
     * Obtiene el inventario del jugador.
     * @return El inventario del jugador.
     */
    public Map<String, Integer> getInventario() {
        return inventario;
    }
    
    /**
     * (sobrecarga)
     * Recarga la energía del jugador con protección, asegurando que no exceda el máximo.
     * @param unidadesSodio Las unidades de sodio a utilizar para la recarga.
     */
    public void recargarEnergiaProteccion(double cantidad) {
        this.unidadesEnergia += cantidad;
        if (this.unidadesEnergia > 100) {
            this.unidadesEnergia = 100;  
        }
    }

    /**
     * Agrega un material al inventario del jugador.
     * @param material El material a agregar.
     * @param cantidad La cantidad del material a agregar.
     */
    public void agregarMaterial(String material, int cantidad) {
        inventario.put(material, inventario.getOrDefault(material, 0) + cantidad);
    }
    
    /**
     * Resta una cantidad de un material del inventario del jugador, si llega a 0, se elimina del inventario.
     * @param material El material a restar.
     * @param cantidad La cantidad del material a restar.
     */
    public boolean restarMaterial(String material, int cantidad) {
        if (inventario.containsKey(material)) {
            int nuevaCantidad = inventario.get(material) - cantidad;
            if (nuevaCantidad <= 0) {
                inventario.remove(material); 
                return false;
            } else {
                inventario.put(material, nuevaCantidad);
                return true;
            }
        }
        return false;
    }

    /**
     * Reinicia el inventario del jugador.
     */
    public void reiniciarInventario() {
        inventario.clear();
    }
}
