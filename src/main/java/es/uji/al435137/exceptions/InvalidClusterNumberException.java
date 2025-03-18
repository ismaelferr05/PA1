package es.uji.al435137.exceptions;

public class InvalidClusterNumberException extends RuntimeException {
    private int numberOfClusters;
    private int totalData;

    public InvalidClusterNumberException(int numberOfClusters, int totalData) {
        super("Invalid cluster number: " + numberOfClusters
                + " (total data: " + totalData + ")");
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