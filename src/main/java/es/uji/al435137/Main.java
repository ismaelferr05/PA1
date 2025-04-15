package es.uji.al435137;

import es.uji.al435137.mvc.controlator.ControllerImplementation;
import es.uji.al435137.mvc.model.ModelImplementation;
import es.uji.al435137.mvc.view.ViewImplementation;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ViewImplementation vista = new ViewImplementation();
        ModelImplementation modelo = new ModelImplementation();
        ControllerImplementation controlador = new ControllerImplementation();

        vista.setControlador(controlador);
        vista.setModelo(modelo);

        modelo.setVista(vista);

        controlador.setModelo(modelo);
        controlador.setVista(vista);

        vista.createGUI(stage);
    }
}
