package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.InputStream;

import controladores.CongruenciasControlador;
import controladores.Controlador;
 /**
 * <p>
 * Es la aplicacion que contiene las diferentes escenas
 * del programa
 * </p>
 * <p>
 * Es una clase que carga los recursos FXML de la aplicacion 
 * y los conecta con las clases de control, de la misma manera
 * sirve para configurar la barra de navegacion
 * <p>
 * @author Saul Fernando Gonzalez Dominguez
 * @version 1.0
 */
public class Aplicacion extends Application {   
    //Variables de la clase
    private Stage stage;
    private Scene[] escenas;

    /**
     * Es el metodo que inizializa la aplicacion
     * @param stage Es un parametro que se hereda de la clase Aplication y es el contenedor de las escenas
     * @throws Exception Lanza un excepxion si algo falla al cargar algun archivo
     */
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        this.escenas = new Scene[]{
            configurarEscena(new CongruenciasControlador(this), "layouts/CongruenciasVentana.fxml")
        };
        
        this.stage.setResizable(true);
        this.stage.setScene(escenas[0]);
        this.stage.sizeToScene();
        this.stage.setResizable(false);
        this.stage.show();
    }

    /**
     * Es un metodo creado para reutilizar la logica
     * que se emplea para configurar una escena 
     * @param controlador es una instancia del controlador que se desea usar
     * @param fxmlRuta es la ruta del fxml que se va a cargar
     * @return la escena ya configurada con los parametros anteriores, en caso de erro regresa null
     */
    private Scene configurarEscena(Controlador controlador,  String fxmlRuta){
        Scene escena = null;
        try {
            InputStream instream = getClass().getClassLoader().getResourceAsStream(fxmlRuta);
            FXMLLoader loader = new FXMLLoader();
            loader.setController(controlador);
            escena = new Scene(loader.load(instream));
        } catch (Exception e) {
            System.out.println(e);
        }
        return escena;
    }
}   