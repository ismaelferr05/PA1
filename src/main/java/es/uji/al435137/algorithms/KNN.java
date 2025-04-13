package es.uji.al435137.algorithms;

import es.uji.al435137.algorithms.distance.Distance;
import es.uji.al435137.algorithms.distance.EuclideanDistance;
import es.uji.al435137.reading.Row;
import es.uji.al435137.reading.RowWithLabel;
import es.uji.al435137.reading.TableWithLabels;

import java.util.List;

public class KNN implements Algorithm<TableWithLabels, Integer, List<Double>>{
    private Distance distance; //Distancia calculada ya sea de la manera euclidiana como de la manera Manhattan
    private TableWithLabels trainingData;
    private final int k = 1;

    public KNN(Distance distance) {
        this.distance=distance;
        trainingData = new TableWithLabels();
    }

    public KNN() {
        this.distance= new EuclideanDistance();
        trainingData = new TableWithLabels();
    }

    public void train(TableWithLabels data) {
        this.trainingData = data;
        for (int i =0;i<trainingData.getRowCount();i++) {
            RowWithLabel r = trainingData.getRowAt(i);
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
            double distance = this.distance.calculateDistance(sample, rowWithLabel.getData());
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


}