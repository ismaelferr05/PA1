package es.uji.al435137.mvc.controlator;

import es.uji.al435137.mvc.model.Model;
import es.uji.al435137.mvc.view.View;

public class ControllerImplementation implements Controller {
    private Model model;
    private View vista;

    public void setModelo(Model modelo) {
        this.model = modelo;
    }

    public void setVista(View vista) {
        this.vista = vista;
    }

    @Override
    public void trainRecommendations() {
        // Lógica para entrenar recomendaciones en el modelo
        if (model != null) {
            model.train();
        }
    }

    @Override
    public void moreRecommendations() {
        // Lógica para obtener más recomendaciones del modelo
        if (model != null) {
            model.generateMoreRecommendations();
        }
    }
}