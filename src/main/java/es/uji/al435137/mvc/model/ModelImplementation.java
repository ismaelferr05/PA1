package es.uji.al435137.mvc.model;

public class ModelImplementation implements Model {

    @Override
    public void train() {
        // Lógica para entrenar el modelo
        System.out.println("Entrenando el modelo...");
        // Aquí puedes agregar la lógica específica de entrenamiento
    }

    @Override
    public void generateMoreRecommendations() {
        // Lógica para generar más recomendaciones
        System.out.println("Generando más recomendaciones...");
        // Aquí puedes agregar la lógica específica para generar recomendaciones
    }
}