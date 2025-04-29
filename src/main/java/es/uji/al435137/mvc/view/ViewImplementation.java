package es.uji.al435137.mvc.view;

import es.uji.al435137.mvc.controller.Controller;
import es.uji.al435137.mvc.model.Model;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewImplementation implements View {
    private Controller controller;
    private Model model;

    @Override
    public void setControlador(Controller controlador) {
        this.controller = controlador;
    }

    @Override
    public void setModelo(Model modelo) {
        this.model = modelo;
    }

    @Override
    public void createGUI(Stage stage) {
        // Añade el contenedor a la escena, y la escena al escenario
        stage.setScene(new Scene(layout, 200, 100));
        stage.setTitle("Recommendations");
        stage.show();
    }

}