package es.uji.al435137.mvc.model;

import es.uji.al435137.algorithms.Algorithm;
import es.uji.al435137.algorithms.KMeans;
import es.uji.al435137.algorithms.distance.Distance;
import es.uji.al435137.algorithms.distance.EuclideanDistance;
import es.uji.al435137.mvc.view.View;
import es.uji.al435137.reading.Table;

public class ModelImplementation implements Model {
    private View view;

    public ModelImplementation(){

    }

    public void setVista(View vista) {
        this.view = vista;
    }

    @Override
    public void trainKmeans(Distance d) {
        ReaderTemplate lector = new CSVUnlabeledFileReader();
        Table trainTable = lector.readTableFromSource("datos/songs_train_withoutnames.csv");
        Table testTable = lector.readTableFromSource("datos/songs_test_withoutnames.csv");

        Algorithm KMeans = new Kmeans(d,15,50,43422);

        RecSys recsys = new RecSys(KMeans);

        recsys.train(trainTable);
        if(d.getClass() == EuclideanDistance.class){
            recsys_kMeansED = recsys;
        } else if(d.getClass() == ManhattanDistance.class){
            recsys_kMeansMD = recsys;
        }
    }


    Public void trainK1nn
    @Override
    public void generateMoreRecommendations() {

    }

    @Override
    public void setAlgorithm(int algorithmType, int distanceType) {
        if (algorithmType == 0) {
            if (distanceType == 0) {
                trainKmeans(new EuclideanDistance());
                recsysActual = recsys_kMeansED;
            } else if (distanceType == 1) {
                trainKmeans(new ManhattanDistance());
                recsysActual = recsys_kMeansMD;
            }
        } else if (algorithmType == 1) {
            if (distanceType == 0) {
                trainK1nn(new EuclideanDistance());
                recsysActual = recsys_k1nnED;
            } else if (distanceType == 1) {
                trainK1nn(new ManhattanDistance());
                recsysActual = recsys_k1nnMD;
            }
        }

    }




    @Override
    public void calculateRecommendations(String cancion, int i) {

    }
}