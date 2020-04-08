package main;

import javafx.application.Application;

/**
 * <p>
 * Es la clase principal del programa
 * </p>
 * <p>
 * A lo largo de la ejecucion del main, se lanza la aplicacion que contiene a
 * las diferentes escenas
 * <p>
 * 
 * @author Saul Fernando Gonzalez Dominguez
 * @version 1.0
 */
public class App  {
    /**
     * Metodo de prueba, para posibles test,
     * se genero autometicamente al crear el proyecto con gradle
     * @return Una cadena "Hello world"
     */
    public String getGreeting() {
        return "Hello world.";
    }

    /**
     * Es el hilo principal de ejecucion del programa y manda a llamar
     * a la ventana principal del programa
     * @param args Argumentos de ejecucion del programa
     */
    public static void main(String[] args) {
        try{
            Application.launch(Aplicacion.class, args);
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
}
