package es.uji.al435137.algorithms;

import es.uji.al435137.reading.Table;
import es.uji.al435137.exceptions.InvalidClusterNumberException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KMeans implements Algorithm<Table, Integer, List<Double>> {
    private int numClusters;
    private int numIterations;
    private long seed;
    private List<List<Double>> centroids;

    public KMeans(int numClusters, int numIterations, long seed) {
        this.numClusters = numClusters;
        this.numIterations = numIterations;
        this.seed = seed;
        this.centroids = new ArrayList<>();
    }

    //Entrena el modelo K-Menas con los datos proporcionados
    public void train(Table data) {
        List<List<Double>> points = new ArrayList<>();
        for (int i = 0; i < data.getRowCount(); i++) {
            points.add(data.getRowAt(i).getData());
        }
        if (this.numClusters > data.getRowCount()) {
            throw new InvalidClusterNumberException(this.numClusters, data.getRowCount());
        }

        Random random = new Random(seed);
        for (int i = 0; i < this.numClusters; i++) {
            this.centroids.add(new ArrayList<>(points.get(random.nextInt(points.size()))));
        }

        for (int iter = 0; iter < this.numIterations; iter++) {
            List<List<List<Double>>> clusters = new ArrayList<>();
            for (int i = 0; i < this.numClusters; i++) {
                clusters.add(new ArrayList<>());
            }
            for (List<Double> p : points) {
                clusters.get(estimate(p)).add(p);
            }
            for (int c = 0; c < this.numClusters; c++) {
                if (!clusters.get(c).isEmpty()) {
                    this.centroids.set(c, calculateCentroid(clusters.get(c)));
                }
            }
        }
    }

    //Calcula el centroide de un cluster/grupo promediando sus puntos
    private List<Double> calculateCentroid(List<List<Double>> cluster) {
        List<Double> centroid = new ArrayList<>();
        for (int i = 0; i < cluster.get(0).size(); i++) {
            double sum = 0;
            for (List<Double> p : cluster){
                sum += p.get(i);
            }
            centroid.add(sum / cluster.size());
        }
        return centroid;
    }

    //Estima a qué cluster/grupo pertenece un punto dado basándose en la distancia euclidiana
    public Integer estimate(List<Double> sample) {
        if (this.centroids == null || this.centroids.isEmpty()) {
            throw new IllegalStateException("El modelo no ha sido entrenado aún");
        }
        int nearestCentroid = -1;
        double minDistance = Double.MAX_VALUE;

        for (int i = 0; i < this.centroids.size(); i++) {
            double distance = euclideanDistance(sample, this.centroids.get(i));
            if (distance < minDistance) {
                minDistance = distance;
                nearestCentroid = i;
            }
        }

        return nearestCentroid;
    }

    //Calcula la distancia euclidiana entre dos puntos
    private double euclideanDistance(List<Double> point1, List<Double> point2) {
        double sum = 0;
        for (int i = 0; i < point1.size(); i++) {
            sum += Math.pow(point1.get(i) - point2.get(i), 2);
        }
        return Math.sqrt(sum);
    }

}