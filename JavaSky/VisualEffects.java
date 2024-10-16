import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.sound.sampled.*;
import java.util.Map;

public class VisualEffects {
     /**
     * Muestra una barra de energía en la consola.
     * @param energiaActual La energía actual del jugador.
     * @param energiaMaxima La energía máxima del jugador.
     * @throws InterruptedException Si la animación es interrumpida.
     */
    public static void mostrarBarraEnergia(double energiaActual, double energiaMaxima) throws InterruptedException {
        int totalCaracteres = 20;  // Tamaño de la barra
        int caracteresLlenos = (int) ((energiaActual / energiaMaxima) * totalCaracteres);

        StringBuilder barra = new StringBuilder("[");
        for (int i = 0; i < totalCaracteres; i++) {
            if (i < caracteresLlenos) {
                barra.append("#");  // Parte llena de la barra
            } else {
                barra.append("-");  // Parte vacía de la barra
            }
        }
        barra.append("]");
        
        // Mostrar barra de energía con color
        if ((energiaActual / energiaMaxima) < 0.25) {
            mostrarTextoConColor("\nCombustible: " + barra + " " + (int) energiaActual + "/" + (int) energiaMaxima, "rojo");
            reproducirSonido("lowHP.wav", 6000);
        } else {
            mostrarTextoConColor("\nCombustible: " + barra + " " + (int) energiaActual + "/" + (int) energiaMaxima, "verde");
        }
    }
    /**
     * Muestra una animación en la consola.
     * @param tipo El tipo de animación a mostrar, puede ser takeoff o landing dependiendo de si es despegue o aterrizaje. (claramente)
     * @throws InterruptedException Si la animación es interrumpida.
     */
    public static void mostrarAnimacion(String tipo) throws InterruptedException {
        String[] takeoffFrames = {
            "         |\n        / \\\n       *---*\n       | o |\n       | o |\n       *---*\n        /_\\\n         ",
            "         |\n        / \\\n       *---*\n       | o |\n       | o |\n       *---*\n        /_\\\n     .'.;'.';.'.\n         ",
            "         |\n        / \\\n       *---*\n       | o |\n       | o |\n       *---*\n    ((()/_\\()))\n   (())([$|$](())\n      .'.#'#';.'.\n         ",
            "         |\n        / \\\n       *---*\n       | o |\n       | o |\n       *---*\n       ((/_\\))\n      (()($)(()\n    (()(($$$))())\n         ",
            "         |\n        / \\\n       *---*\n       | o |\n       | o |\n       *---*\n        /_\\\n       ( | )\n      (( | ))\n     ((       ))\n         ",
            "          | o |\n          | o |\n          *---*\n           /_\\\n           ( | )\n          (( | ))\n         ((       ))\n        ((    :    ))\n         ",
            "          ((       ))\n         ((    :    ))\n         ((         ))\n          ((  :  ))\n           ( . )\n         ",
            "           ( . )\n            (. )\n             ."
        };

        
        if (tipo.equals("landing")) {
            //REVERSA!!
            for (int i = takeoffFrames.length - 2; i >= 0; i--) {
                System.out.println("");
                System.out.print("\r" + takeoffFrames[i]);
                Thread.sleep(400);
                System.out.print("\033[H\033[2J");  //limpiar pantalla (desplazarla)
            }
        } else if (tipo.equals("takeoff")) {
            // Forward animation for takeoff
            for (int i = 0; i < takeoffFrames.length; i++) {
                System.out.println("");
                System.out.print("\r" + takeoffFrames[i]);
                Thread.sleep(500);
                System.out.print("\033[H\033[2J");  
            }
        } else {
            System.out.println("Tipo de animación no reconocido.");
        }

        
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    /**
     * Muestra una animación de salto entre planetas.
     * @throws InterruptedException Si la animación es interrumpida.
     * no retorna ni recibe nada
     */
    public static void animacionSalto() throws InterruptedException {
        String[] frames = {
            "Salto: >----",
            "Salto: ->---",
            "Salto: -->--",
            "Salto: --->-",
            "Salto: ---->",
        };
        
        for (int i = 0; i < frames.length; i++) {
            System.out.print("\r" + frames[i]);  
            reproducirSonido("moneda2.wav");
            Thread.sleep(425); 
        }
        System.out.println("\n¡Llegaste al planeta!");
    }
    /**
     * Muestra un mensaje en la consola con un color específico.
     * @param mensaje El mensaje a mostrar.
     * @param color El color del texto.
     */
    public static void mostrarTextoConColor(String mensaje, String color) {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_BLUE = "\u001B[34m";

        String colorCode;
        switch (color.toLowerCase()) {
            case "rojo":
                colorCode = ANSI_RED;
                break;
            case "verde":
                colorCode = ANSI_GREEN;
                break;
            case "amarillo":
                colorCode = ANSI_YELLOW;
                break;
            case "azul":
                colorCode = ANSI_BLUE;
                break;
            default:
                colorCode = ANSI_RESET;
        }

        System.out.println(colorCode + mensaje + ANSI_RESET); 
    }
    //BARRA PLAYER
    /**
     * Muestra una barra de energía del jugador en la consola.
     * @param energiaActual La energía actual del jugador.
     * @param energiaMaxima La energía máxima del jugador.
     * @throws InterruptedException Si la animación es interrumpida.
     */
    public static void mostrarBarraEnergiaJugador(double energiaActual, double energiaMaxima) throws InterruptedException {
        int totalCaracteres = 20;  
        int caracteresLlenos = (int) ((energiaActual / energiaMaxima) * totalCaracteres);

        StringBuilder barra = new StringBuilder("[");
        for (int i = 0; i < totalCaracteres; i++) {
            if (i < caracteresLlenos) {
                barra.append("#");  
            } else {
                barra.append("-");  
            }
        }
        barra.append("]");

        
        if ((energiaActual / energiaMaxima) < 0.25) {
            mostrarTextoConColor("\nEnergía del exotraje: " + barra + " " + (int) energiaActual + "/" + (int) energiaMaxima, "rojo");
            reproducirSonido("lowHPPLAYER.wav", 6000);
        } else {
            mostrarTextoConColor("\nEnergía del exotraje: " + barra + " " + (int) energiaActual + "/" + (int) energiaMaxima, "azul");
        }
    }
    //INVENTARIO
    /**
     * Muestra el inventario del jugador en la consola.
     * @param inventario El inventario del jugador.
     * @param jugador El jugador actual.
     */
    public static void mostrarInventarioJugador(Map<String, Integer> inventario, Jugador jugador) {
        VisualEffects.reproducirSonido("interfaz.wav");
        System.out.println("\n=== Inventario de "+jugador.getName()+" ===");
        if (inventario.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            for (Map.Entry<String, Integer> entrada : inventario.entrySet()) {
                System.out.println("- " + entrada.getKey() + ": " + entrada.getValue());
            }
        }
        System.out.println("==============================");
    }
    //SONIDO
    /**
     * Reproduce un archivo de sonido ".wav" (porque el mp3 no funciona).
     * @param nombreArchivo El nombre del archivo de sonido.
     * aqui agregue dos casos posibles, FX/ o JavaSky/FX porque dependia del sistema operativo, funciona distinto en windows...
     */
    public static void reproducirSonido(String nombreArchivo) {
        try {
            // Ruta al archivo dentro de la carpeta FX
            File archivoSonido = new File("FX/" + nombreArchivo);
            if (!archivoSonido.exists()) {
                archivoSonido = new File("JavaSky/FX/" + nombreArchivo);
            }
            // Cargar el archivo de sonido
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivoSonido);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            clip.drain();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //REPRODUCCION PERO CON TIMER
    /**
     * Reproduce un sonido con una duración específica.
     * @param nombreArchivo El nombre del archivo de sonido.
     * @param duracionMilisegundos La duración del sonido en milisegundos.
     */
    public static void reproducirSonido(String nombreArchivo, int duracionMilisegundos) {
        new Thread(() -> {
            Clip clip = null;
            try {

                File archivoSonido = new File("FX/" + nombreArchivo);
                if (!archivoSonido.exists()) {
                    archivoSonido = new File("JavaSky/FX/" + nombreArchivo);
                }

                AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivoSonido);

                clip = AudioSystem.getClip();
                clip.open(audioStream);

                clip.start();

                Thread.sleep(duracionMilisegundos);
                clip.stop();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (clip != null) {
                    clip.close();  
                }
            }
        }).start();
    }
    /**
     * Muestra una serie de frames con un retraso específico.
     * @param ruta La ruta del archivo de frames.
     * @param delay El retraso entre frames en milisegundos.
     * @throws InterruptedException Si la animación es interrumpida.
     */
    public static void mostrarFramesConRetraso(String ruta, int delay) throws InterruptedException {
        try (BufferedReader br = new BufferedReader(new FileReader("FX/"+ruta))) {
            StringBuilder frame = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) { // Dividir frames por líneas vacías
                    if (frame.length() > 0) {
                        System.out.print("\033[H\033[2J"); 
                        System.out.println("");
                        System.out.flush();
                        System.out.println(frame.toString());
                        frame.setLength(0);
                        Thread.sleep(delay); 
                    }
                } else {
                    frame.append(line).append("\n");
                }
            }

            
            if (frame.length() > 0) {
                System.out.print("\033[H\033[2J");  
                System.out.flush();
                System.out.println(frame.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

