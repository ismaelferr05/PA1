package es.uji.al435137.mvc.model;

public interface Model {
    void trainKmeans(); // Método para entrenar el modelo
    void generateMoreRecommendations(); // Método para generar más recomendaciones

    void setAlgorithm(int tipoAlgoritmo, int tipoDistancia);

    void calculateRecommendations(String cancion, int i);
}