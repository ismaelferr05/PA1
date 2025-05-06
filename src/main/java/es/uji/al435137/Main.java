package es.uji.al435137;

import es.uji.al435137.mvc.controller.Controller;
import es.uji.al435137.mvc.controller.ControllerImplementation;
import es.uji.al435137.mvc.model.Model;
import es.uji.al435137.mvc.model.ModelImplementation;
import es.uji.al435137.mvc.view.View;
import es.uji.al435137.mvc.view.ViewImplementation;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            View view = new ViewImplementation();
            Model model = new ModelImplementation();
            Controller controller = new ControllerImplementation();

            model.setVista(view);

            view.setControlador(controller);
            view.setModelo(model);

            controller.setModelo(model);
            controller.setVista(view);

            view.createGUI(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}