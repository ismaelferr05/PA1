package es.uji.al435137.algorithms;

import es.uji.al435137.reading.Row;
import es.uji.al435137.reading.RowWithLabel;
import es.uji.al435137.reading.TableWithLabels;

import java.util.List;

public class KNN implements Algorithm<TableWithLabels, Integer, List<Double>>{
    private TableWithLabels trainingData;
    private final int k = 1;

    public KNN() {
        trainingData = new TableWithLabels();
    }

    public void train(TableWithLabels data) {
        this.trainingData = data;
        for (int i =0;i<trainingData.getRowCount();i++) {
            RowWithLabel r = (RowWithLabel) trainingData.getRowAt(i);
            trainingData.getLabelAsInteger(r.getLabel());
        }
    }

    // Lanza una excepción en caso de error
    public Integer estimate(List<Double> sample) {
        if (trainingData == null || trainingData.getRowCount()==0) {
            throw new IllegalStateException("El modelo no ha sido entrenado aún");
        }

        RowWithLabel nearestRow = null;
        double minDistance = Double.MAX_VALUE;

        for (int i =0;i<trainingData.getRowCount();i++) {
            RowWithLabel rowWithLabel = (RowWithLabel) trainingData.getRowAt(i);
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

    // Abstracción para calcular la distancia
    private double euclideanDistance(List<Double> a, List<Double> b) {
        double sum = 0;
        for (int i = 0; i < a.size(); i++) {
            sum += Math.pow(a.get(i) - b.get(i), 2);
        }
        return Math.sqrt(sum);
    }

}