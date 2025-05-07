package es.uji.al435137.mvc.model;

import es.uji.al435137.algorithms.Algorithm;
import es.uji.al435137.algorithms.KMeans;
import es.uji.al435137.algorithms.KNN;
import es.uji.al435137.algorithms.distance.Distance;
import es.uji.al435137.algorithms.distance.EuclideanDistance;
import es.uji.al435137.algorithms.distance.ManhattanDistance;
import es.uji.al435137.mvc.view.View;
import es.uji.al435137.reading.FileReader.CSVLabeledFileReader;
import es.uji.al435137.reading.FileReader.CSVUnlabeledFileReader;
import es.uji.al435137.reading.FileReader.ReaderTemplate;
import es.uji.al435137.reading.Table;
import es.uji.al435137.reading.TableWithLabels;
import es.uji.al435137.recommend.RecSys;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;

import java.io.File;
import java.util.Scanner;

import java.util.List;
import java.util.LinkedList;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class ModelImplementation implements Model {
    private View view;
    private ObservableList<String> songsList;
    private List<String> recommendationsList;
    private RecSys recsys_kMeansED;
    private RecSys recsys_kMeansMD;
    private RecSys recsys_knnED;
    private RecSys recsys_knnMD;
    private RecSys recsysActual;

    private boolean isTrainedKMeansED = false;
    private boolean isTrainedKMeansMD = false;
    private boolean isTrainedKNNED = false;
    private boolean isTrainedKNNMD = false;

    public ModelImplementation() throws FileNotFoundException, URISyntaxException {
        songsList = convertFileToObservableList("recommends/songs_test_names.csv");
    }

    public void setVista(View view) {
        this.view = view;
    }

    @Override
    public ObservableList<String> getSongsList(){
        return songsList;
    }

    private ObservableList<String> convertFileToObservableList(String file) throws FileNotFoundException, URISyntaxException {
        ObservableList<String> listaOutput = FXCollections.observableArrayList();
        Scanner sc = new Scanner(new File(getClass().getClassLoader().getResource(file).toURI().getPath()));
        while(sc.hasNextLine()){
            listaOutput.add(sc.nextLine());
        }

        return listaOutput;
    }

    public void trainKmeans(Distance d) throws FileNotFoundException, URISyntaxException{
        ReaderTemplate<Table> lector1 = new CSVUnlabeledFileReader("recommends/songs_train_withoutnames.csv");
        ReaderTemplate<Table> lector2 = new CSVUnlabeledFileReader("recommends/songs_test_withoutnames.csv");
        Table trainTable = lector1.readTableFromSource();
        Table testTable = lector2.readTableFromSource();

        Algorithm<Table, Integer, List<Double>> kmeans = new KMeans(d, 15, 50, 43422);
        RecSys<Table, Integer, List<Double>> recsys = new RecSys<>(kmeans);
        recsys.train(trainTable);

        List<String> testItemNames = convertFileToList("recommends/songs_test_names.csv");
        recsys.initialise(testTable, testItemNames);

        if (d instanceof EuclideanDistance) {
            recsys_kMeansED = recsys;
        } else if (d instanceof ManhattanDistance) {
            recsys_kMeansMD = recsys;
        }
    }

    public void trainKnn(Distance d) throws FileNotFoundException, URISyntaxException {
        ReaderTemplate<TableWithLabels> lector1 = new CSVLabeledFileReader("recommends/songs_train.csv");
        ReaderTemplate<Table> lector2 = new CSVUnlabeledFileReader("recommends/songs_test_withoutnames.csv");
        TableWithLabels trainTable = lector1.readTableFromSource();
        Table testTable = lector2.readTableFromSource();

        Algorithm<TableWithLabels, Integer, List<Double>> knn = new KNN(d);
        RecSys<TableWithLabels, Integer, List<Double>> recsys = new RecSys<>(knn);
        recsys.train( trainTable);

        List<String> testItemNames = convertFileToList("recommends/songs_test_names.csv");
        recsys.initialise(testTable, testItemNames);

        if (d instanceof EuclideanDistance) {
            recsys_knnED = recsys;
        } else if (d instanceof ManhattanDistance) {
            recsys_knnMD = recsys;
        }

    }

    @Override
    public void setAlgorithm(int algorithmType, int distanceType) throws FileNotFoundException, URISyntaxException {
        if (algorithmType == 0) { //K-Means
            if (distanceType == 0) {//Euclidean
                if (!isTrainedKMeansED) {
                    trainKmeans(new EuclideanDistance());
                    isTrainedKMeansED = true;
                } else {
                    view.setText("K-Means with Euclidean distance is already trained.\nGenerating recommendations...");
                }
                recsysActual = recsys_kMeansED;
            } else {//Manhattan
                if (!isTrainedKMeansMD) {
                    trainKmeans(new ManhattanDistance());
                    isTrainedKMeansMD = true;
                } else {
                    view.setText("K-Means with Manhattan distance is already trained.\nGenerating recommendations...");
                }
                recsysActual = recsys_kMeansMD;
            }
        } else {//KNN
            if (distanceType == 0) {//Euclidean
                if (!isTrainedKNNED) {
                    trainKnn(new EuclideanDistance());
                    isTrainedKNNED = true;
                } else {
                    view.setText("KNN with Euclidean distance is already trained.\nGenerating recommendations...");
                }
                recsysActual = recsys_knnED;
            } else {// Manhattan
                if (!isTrainedKNNMD) {
                    trainKnn(new ManhattanDistance());
                    isTrainedKNNMD = true;
                } else {
                    view.setText("KNN with Manhattan distance is already trained.\nGenerating recommendations...");
                }
                recsysActual = recsys_knnMD;
            }
        }
    }

    @Override
    public void calculateRecommendations(String nameLikedItem, int numRecommendations) {
        recommendationsList = recsysActual.recommend(nameLikedItem, numRecommendations);
        view.notifyNewRecommendations();
    }

    @Override
    public List<String> getRecommendations() {
        return recommendationsList;
    }

    private List<String> convertFileToList(String file) throws FileNotFoundException, URISyntaxException {
        List<String> listaOutput = new LinkedList<>();
        Scanner sc = new Scanner(new File(getClass().getClassLoader().getResource(file).toURI().getPath()));
        while(sc.hasNextLine()){
            listaOutput.add(sc.nextLine());
        }
        return listaOutput;
    }
}