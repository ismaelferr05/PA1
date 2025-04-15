package es.uji.al435137.mvc.view;

import es.uji.al435137.mvc.controlator.Controller;
import es.uji.al435137.mvc.model.Model;
import javafx.stage.Stage;

public class ViewImplementation implements View {
    private Controller controlador;
    private Model modelo;

    @Override
    public void setControlador(Controller controlador) {
        this.controlador = controlador;
    }

    @Override
    public void setModelo(Model modelo) {
        this.modelo = modelo;
    }

    @Override
    public void createGUI(Stage stage) {
        // Lógica para crear la interfaz gráfica
        System.out.println("Creando la interfaz gráfica...");
        stage.setTitle("Aplicación MVC");
        stage.show();
    }
}