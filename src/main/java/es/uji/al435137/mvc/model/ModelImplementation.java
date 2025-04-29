package es.uji.al435137.mvc.model;

import es.uji.al435137.mvc.view.View;

public class ModelImplementation implements Model {
    private View view;

    public ModelImplementation(){

    }

    public void setVista(View vista) {
        this.view = vista;
    }

    @Override
    public void train() {
        System.out.println("Entrenando el modelo...");
        // Lógica específica de entrenamiento
    }

    @Override
    public void generateMoreRecommendations() {
        System.out.println("Generando más recomendaciones...");
        // Lógica específica para generar recomendaciones
    }

    @Override
    public void setAlgorithm(int tipoAlgoritmo, int tipoDistancia) {

    }

    @Override
    public void calculateRecommendations(String cancion, int i) {

    }
}