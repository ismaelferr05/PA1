package es.uji.al435137.mvc.model;

import es.uji.al435137.algorithms.distance.Distance;
import es.uji.al435137.mvc.view.View;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;

public interface Model {
    void setAlgorithm(int algorithmType, int distanceType) throws FileNotFoundException, URISyntaxException;
    void calculateRecommendations(String nameLikedItem, int numRecommendations);
    ObservableList<String> getSongsList();
    List<String> getRecommendations();

    void setVista(View view);
}