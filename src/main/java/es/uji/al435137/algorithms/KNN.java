package es.uji.al435137.algorithms;

import es.uji.al435137.reading.Row;
import es.uji.al435137.reading.RowWithLabel;
import es.uji.al435137.reading.TableWithLabels;

import java.util.List;

public class KNN {
    private TableWithLabels trainingData;
    private final int k = 1;

    public KNN() {
        trainingData = new TableWithLabels();
    }

    public void train(TableWithLabels data) {
        this.trainingData = data;
        for (Row row : trainingData.getRows()) {
            RowWithLabel r = (RowWithLabel) row;
            trainingData.getLabelAsInteger(r.getLabel());
        }
    }

    public Integer estimate(List<Double> sample) {
        if (trainingData == null || trainingData.getRows().isEmpty()) {
            throw new IllegalStateException("El modelo no ha sido entrenado aún.");
        }

        RowWithLabel nearestRow = null;
        double minDistance = Double.MAX_VALUE;

        for (Row row : trainingData.getRows()) {
            RowWithLabel rowWithLabel = (RowWithLabel) row;
            double distance = euclideanDistance(sample, rowWithLabel.getData());
            if (distance < minDistance) {
                minDistance = distance;
                nearestRow = rowWithLabel;
            }
        }

        if (nearestRow == null) {
            return null;
        } else {
            return trainingData.getLabelAsInteger(nearestRow.getLabel());
        }
    }

    private double euclideanDistance(List<Double> a, List<Double> b) {
        double sum = 0;
        for (int i = 0; i < a.size(); i++) {
            sum += Math.pow(a.get(i) - b.get(i), 2);
        }
        return Math.sqrt(sum);
    }
}