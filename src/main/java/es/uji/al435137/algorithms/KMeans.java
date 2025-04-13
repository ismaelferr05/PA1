package es.uji.al435137.algorithms;

import es.uji.al435137.algorithms.distance.Distance;
import es.uji.al435137.algorithms.distance.EuclideanDistance;
import es.uji.al435137.reading.Table;

import java.util.*;

public class KMeans implements Algorithm<Table, Integer, List<Double>> {
    private Distance distance; //Distancia calculada ya sea de la manera euclidiana como de la manera Manhattan
    private int numClusters;
    private int numIterations;
    private long seed;
    private List<List<Double>> centroids;

    public KMeans(Distance distance, int numClusters, int numIterations, long seed) {
        this.distance=distance;
        this.numClusters = numClusters;
        this.numIterations = numIterations;
        this.seed = seed;
        this.centroids = new ArrayList<>();
    }

    public KMeans(int numClusters, int numIterations, long seed) {
        this.distance= new EuclideanDistance();
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

        initializeCentroids(points);
        runIterations(points);
    }

    //Inicializa los centroides seleccionando puntos aleatorios distintos del conjunto de datos
    private void initializeCentroids(List<List<Double>> points){
        Random random = new Random(seed);
        Set<Integer> chosenIndices = new HashSet<>();
        while (this.centroids.size() < this.numClusters) {
            int index = random.nextInt(points.size());
            if (!chosenIndices.contains(index)) {
                chosenIndices.add(index);
                this.centroids.add(new ArrayList<>(points.get(index)));
            }
        }
    }

    //Se llevan a cabo las iteraciones que asignan puntos a clusters y actualizan centroides
    private void runIterations(List<List<Double>> points){
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
            double distance = this.distance.calculateDistance(sample, this.centroids.get(i));
            if (distance < minDistance) {
                minDistance = distance;
                nearestCentroid = i;
            }
        }

        return nearestCentroid;
    }
}