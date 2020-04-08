package controladores;

import java.util.Stack;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import logica.Congruencia;
import logica.Ecuacion;
import main.Aplicacion;
import otros.ErrorV;

/**
 * <p>
 * Es el controlador de la interfaz grafica de la ventana Diofantinas, por lo
 * que se encarga de configurar el comportamiento de cada uno de los componentes
 * </p>
 * 
 * @author Saul Fernando Gonzalez Dominguez
 * @version 1.0
 */
public class CongruenciasControlador implements Controlador {
    @FXML
    private TextArea txtaResultado;
    @FXML
    private TextField txtfX, txtfMod, txtfa, txtfb;
    @FXML
    private Button btnAgregar, btnResolver, btnMcd;
    @FXML
    private ListView<String> listSistema;

    private Stack<Ecuacion> sistemaLogico;

    private Aplicacion aplicacion;

    /**
     * Es el contructor de la clase, recibe los controles de la aplicacion para el
     * cambio de ventana
     * 
     * @param aplicacion Son los controles de la aplicacion
     */
    public CongruenciasControlador(Aplicacion aplicacion) {
        this.aplicacion = aplicacion;
        this.sistemaLogico = new Stack<>();
    }

    /**
     * En este metodo se definen las acciones de cada uno de los elementos graficos
     * que interactuan con el usuario
     */
    public void initialize() {
        this.txtaResultado.setEditable(false);
        txtfX.setTextFormatter(new TextFormatter<>(change -> {
            String text = change.getText();
            if (text.matches("[0-9]*")) {
                return change;
            }
            return null;
        }));
        txtfMod.setTextFormatter(new TextFormatter<>(change -> {
            String text = change.getText();
            if (text.matches("[0-9]*")) {
                return change;
            }
            return null;
        }));
        txtfa.setTextFormatter(new TextFormatter<>(change -> {
            String text = change.getText();
            if (text.matches("[0-9]*")) {
                return change;
            }
            return null;
        }));
        txtfb.setTextFormatter(new TextFormatter<>(change -> {
            String text = change.getText();
            if (text.matches("[0-9]*")) {
                return change;
            }
            return null;
        }));
        btnAgregar.setOnAction(e -> {
            if (txtfX.toString().isEmpty() || txtfMod.toString().isEmpty()) {
                new ErrorV("Dejaste un campo vacio");
            } else {
                sistemaLogico.add(new Ecuacion(Integer.parseInt(txtfX.getText().toString().trim()),
                        Integer.parseInt(txtfMod.getText().toString().trim())));
                listSistema.getItems().add(listSistema.getItems().size(), sistemaLogico.peek().toString());
                txtfX.clear();
                txtfMod.clear();
            }
        });

        btnResolver.setOnAction(e -> {
            System.out.println(sistemaLogico.size());
            if (sistemaLogico.size()>1) {
                Congruencia congruencia = new Congruencia();

                congruencia.tieneSolucionP(sistemaLogico);
                if (congruencia.tieneSolucion(sistemaLogico)) {
                    congruencia.resolverSistemaP(sistemaLogico);
                }
                txtaResultado.setText(congruencia.getCache());
                sistemaLogico.clear();
                listSistema.getItems().clear();
            }else{
                new ErrorV("Pon al menos dos ecuaciones");
            }
        });

        btnMcd.setOnAction(e -> {
            Congruencia congruencia = new Congruencia();
            if (txtfa.toString().isEmpty() || txtfb.toString().isEmpty()) {
                new ErrorV("Dejaste un campo vacio");
            } else {
                congruencia.mcdP(Integer.parseInt(txtfa.getText().toString().trim()),
                        Integer.parseInt(txtfb.getText().toString().trim()));
                congruencia.combinacionLinealP();
                txtaResultado.setText(congruencia.getCache());
            }
        });
    }
}