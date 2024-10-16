public class CentroGalactico extends Planeta {

    // Constructor ajustado para mantener valores arbitrarios de int
    public CentroGalactico() {
        super(1000000, 0, 0, "\u001B[32mCentro Galactico\u001B[0m");  
    }
    //Aunque aparezcan aca, ninguna de las funciones se utiliza basicamente ya que llegar al centro galactico es el fin del juego.
    //Por lo tanto, no se extraen recursos, no se consume energia y no se visita asentamientos.

    /**
     * Visita el centro galactico.
     * @param jugador El objeto Jugador.
     * @return true si la visita es exitosa.
     */
    @Override
    public boolean visitar(Jugador jugador) {
        System.out.println(""); // no se hace nada dentro debido a que la manera en la que implemente dos finales posibles requiere conocer el estado de la nave
        return true;
    }

    @Override
    public int extraerRecursos(int tipo) {
        // No se extraen recursos en el Centro Galáctico
        System.out.println("No hay recursos disponibles en el Centro Galáctico.");
        return 0;
    }
    @Override
    public String toString() {
        return "\u001B[37mCentro Galáctico [radio=" + getRadio() + "]\u001B[0m";
    }
    @Override
    public boolean salir(){
        return true;
    }

    public void setConsumoEnergia(Jugador jugador){
        return;
    }

}