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
        VBox layout = new VBox(); // Contenedor principal
        Label label = new Label("Hola mundo!"); // Texto no editable
        Button button = new Button("Un botón :D"); // Un botón, no hace nada
        layout.getChildren().addAll(label, button); // Añade elementos al contenedor
        layout.setAlignment(Pos.CENTER); // Centra los elementos
        // Añade el contenedor a la escena, y la escena al escenario
        stage.setScene(new Scene(layout, 200, 100));
        stage.setTitle("Aplicación MVC");
        stage.show();
    }
}