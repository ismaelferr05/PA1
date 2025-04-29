package es.uji.al435137.mvc.view;

import es.uji.al435137.mvc.controller.Controller;
import es.uji.al435137.mvc.model.Model;
import javafx.stage.Stage;

public interface View {
    public void setControlador(Controller controlador);

    public void setModelo(Model modelo);

    public void createGUI(Stage stage);
}
