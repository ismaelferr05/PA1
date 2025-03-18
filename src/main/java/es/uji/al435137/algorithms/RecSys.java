package es.uji.al435137.algorithms;
import es.uji.al435137.reading.Table;
import es.uji.al435137.reading.TableWithLabels;

import java.util.ArrayList;
import java.util.List;

public class RecSys<T extends Table, G, H> {
    private Algorithm<T, G, H> algorithm;
    private T testData;
    private List<String> testItemNames;
    private List<G> predictions;



    public RecSys(Algorithm<T, G, H> algorithm) {
        this.algorithm = algorithm;
    }

    public void train(Table trainData) {
        algorithm.train((T) trainData);

    }

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

    public List<String> recommend(String nameLikedItem, int numRecommendations) {
        int indexLikedItem = testItemNames.indexOf(nameLikedItem);
        if (indexLikedItem == -1) {
            throw new IllegalArgumentException("Nombre no encontrado");
        }
        G likedPrediction = predictions.get(indexLikedItem);

        List<String> candidates = new ArrayList<>();
        for (int i = 0; i < predictions.size(); i++) {
            if (i != indexLikedItem && predictions.get(i).equals(likedPrediction)) {
                candidates.add(testItemNames.get(i));
            }
        }
        if (candidates.size() > numRecommendations) {
            return candidates.subList(0, numRecommendations);
        }
        return candidates;
    }

}
