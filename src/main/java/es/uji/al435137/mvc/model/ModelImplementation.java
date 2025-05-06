package es.uji.al435137.mvc.model;

import es.uji.al435137.algorithms.Algorithm;
import es.uji.al435137.algorithms.KMeans;
import es.uji.al435137.algorithms.KNN;
import es.uji.al435137.algorithms.distance.Distance;
import es.uji.al435137.algorithms.distance.EuclideanDistance;
import es.uji.al435137.algorithms.distance.ManhattanDistance;
import es.uji.al435137.mvc.view.View;
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

    public ModelImplementation() throws FileNotFoundException {
        songsList = convertFileToObservableList("resources/recommend/songs_test_names.csv");
    }

    public void setVista(View view) {
        this.view = view;
    }

    @Override
    public ObservableList<String> getSongsList(){
        return songsList;
    }

    private ObservableList<String> convertFileToObservableList(String file) throws FileNotFoundException {
        ObservableList<String> listaOutput = FXCollections.observableArrayList();
        Scanner sc = new Scanner(new File(file));
        while(sc.hasNextLine()){
            listaOutput.add(sc.nextLine());
        }

        return listaOutput;
    }

    public void trainKmeans(Distance d) throws FileNotFoundException, URISyntaxException{
        ReaderTemplate<Table> lector1 = new CSVUnlabeledFileReader("resources/recomend/songs_train_withoutnames.csv");
        ReaderTemplate<Table> lector2 = new CSVUnlabeledFileReader("resources/recommend/songs_test_withoutnames.csv");
        Table trainTable = lector1.readTableFromSource();
        Table testTable = lector2.readTableFromSource();

        Algorithm<Table, Integer, List<Double>> kmeans = new KMeans(d, 15, 50, 43422);
        RecSys<Table, Integer, List<Double>> recsys = new RecSys<>(kmeans);
        recsys.train(trainTable);

        if (d instanceof EuclideanDistance) {
            recsys_kMeansED = recsys;
        } else if (d instanceof ManhattanDistance) {
            recsys_kMeansMD = recsys;
        }
    }

    public void trainKnn(Distance d) throws FileNotFoundException, URISyntaxException {
        ReaderTemplate<Table> lector1 = new CSVUnlabeledFileReader("resources/recommend/songs_train_withoutnames.csv");
        ReaderTemplate<Table> lector2 = new CSVUnlabeledFileReader("resources/recommend/songs_test_withoutnames.csv");
        Table trainTable = lector1.readTableFromSource();
        Table testTable = lector2.readTableFromSource();

        Algorithm<TableWithLabels, Integer, List<Double>> knn = new KNN(d);
        RecSys<TableWithLabels, Integer, List<Double>> recsys = new RecSys<>(knn);
        recsys.train(trainTable);

        if (d instanceof EuclideanDistance) {
            recsys_knnED = recsys;
        } else if (d instanceof ManhattanDistance) {
            recsys_knnMD = recsys;
        }

    }

    @Override
    public void setAlgorithm(int algorithmType, int distanceType) throws FileNotFoundException, URISyntaxException {
        if (algorithmType == 0) {
            if (distanceType == 0) {
                trainKmeans(new EuclideanDistance());
                recsysActual = recsys_kMeansED;
            } else {
                trainKmeans(new ManhattanDistance());
                recsysActual = recsys_kMeansMD;
            }
        } else {
            if (distanceType == 0) {
                trainKnn(new EuclideanDistance());
                recsysActual = recsys_knnED;
            } else {
                trainKnn(new ManhattanDistance());
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

    private List<String> convertFileToList(String file) throws FileNotFoundException {
        List<String> listaOutput = new LinkedList<>();
        Scanner sc = new Scanner(new File(file));
        while(sc.hasNextLine()){
            listaOutput.add(sc.nextLine());
        }
        return listaOutput;
    }
}