package es.uji.al435137.recommend;

import es.uji.al435137.algorithms.Algorithm;
import es.uji.al435137.reading.Table;
import es.uji.al435137.algorithms.LikedItemNotFoundException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecSys<T extends Table, G, H> {
    private Algorithm<T, G, H> algorithm;
    private T testData;
    private List<String> testItemNames;
    private List<G> predictions;



    public RecSys(Algorithm<T, G, H> algorithm) {
        this.algorithm = algorithm;
    }

    //Entrena el modelo con los datos proporcionados
    public void train(Table trainData) {
        algorithm.train((T) trainData);
    }

    //Inicializa el sistema de recomendación con datos de prueba y nombres de elementos
    public void initialise(Table testData, List<String> testItemNames) {
        this.testData = (T) testData;
        this.testItemNames = testItemNames;
        this.predictions = new ArrayList<>();

        for (int i = 0; i < this.testData.getRowCount(); i++) {
            List<Double> rowData = this.testData.getRowAt(i).getData();
            G prediction = algorithm.estimate((H) rowData);
            this.predictions.add(prediction);
        }
    }

    //Genera recomendaciones basadas en un ítem que le gustó al usuario
    public List<String> recommend(String nameLikedItem, int numRecommendations) {
        int indexLikedItem = testItemNames.indexOf(nameLikedItem);
        if (indexLikedItem == -1) {
            throw new LikedItemNotFoundException(nameLikedItem);
        }
        G likedPrediction = predictions.get(indexLikedItem);

        List<String> recommendations = new ArrayList<>();
        Set<String> uniqueRecommendations = new HashSet<>();
        uniqueRecommendations.add(nameLikedItem);
        int count = 0;

        for (int i = 0; i < predictions.size() && count < numRecommendations; i++) {
            if (i != indexLikedItem && predictions.get(i).equals(likedPrediction)) {
                String recommendation = testItemNames.get(i);
                if (uniqueRecommendations.add(recommendation)) { // Solo añade si no está en el conjunto
                    recommendations.add(recommendation);
                    count++;
                }
            }
        }
        return recommendations;
    }

}
