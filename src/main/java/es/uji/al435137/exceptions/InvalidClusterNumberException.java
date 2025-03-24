package es.uji.al435137.exceptions;

public class InvalidClusterNumberException extends RuntimeException {
    private int numberOfClusters;
    private int totalData;

    public InvalidClusterNumberException(int numberOfClusters, int totalData) {
        super("Número de clústeres inválido: " + numberOfClusters+ " (total de datos: "+ totalData +")");
        this.numberOfClusters = numberOfClusters;
        this.totalData = totalData;
    }

    public int getNumberOfCusters() {
        return numberOfClusters;
    }

    public int getTotalData() {
        return totalData;
    }
}