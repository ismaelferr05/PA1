package es.uji.al435137.mvc.view;

import es.uji.al435137.mvc.controller.Controller;
import es.uji.al435137.mvc.model.Model;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.ObservableList;

public class ViewImplementation implements View {
    private Controller controller;
    private Model model;
    private ChoiceBox<String> algorithmChoice;
    private ChoiceBox<String> distanceChoice;
    private TextField amountField;
    private Button trainButton;
    private Label statusLabel;
    private ListView<String> songsListView;
    private ListView<String> recommendationsListView;

    @Override
    public void setControlador(Controller controlador) {
        this.controller = controlador;
    }

    @Override
    public void setModelo(Model modelo) {
        this.model = modelo;
    }

    @Override
    public void createGUI(Stage stage) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        algorithmChoice = new ChoiceBox<>();
        algorithmChoice.getItems().addAll("K-Means", "K-NN");
        algorithmChoice.setValue("K-Means");

        distanceChoice = new ChoiceBox<>();
        distanceChoice.getItems().addAll("Euclidean", "Manhattan");
        distanceChoice.setValue("Euclidean");

        amountField = new TextField();
        amountField.setPromptText("Number of recommendations");

        songsListView = new ListView<>();
        ObservableList<String> songs = model.getSongsList();
        songsListView.setItems(songs);
        songsListView.setMaxHeight(100);

        trainButton = new Button("Train and Recommend");
        trainButton.setOnAction(e -> controller.trainRecommendations());

        statusLabel = new Label();

        recommendationsListView = new ListView<>();
        recommendationsListView.setMaxHeight(150);

        root.getChildren().addAll(
                new Label("Algorithm:"), algorithmChoice,
                new Label("Distance:"), distanceChoice,
                new Label("Number of recommendations:"), amountField,
                new Label("Selected song:"), songsListView, trainButton, statusLabel,
                new Label("Recomendations:"), recommendationsListView
        );

        Scene scene = new Scene(root, 400, 600);
        stage.setTitle("Song Recommendation System");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public int getAlgorithm() {
        if(algorithmChoice.getValue().equals("K-Means")){
            return 0;
        }else{
            return 1;
        }
    }

    @Override
    public int getDistance() {
        if(distanceChoice.getValue().equals("K-Means")){
            return 0;
        }else{
            return 1;
        }
    }

    @Override
    public String getSelectedSong() {
        return songsListView.getSelectionModel().getSelectedItem();
    }

    @Override
    public int getRecommendationsAmount() {
        try {
            return Integer.parseInt(amountField.getText());
        } catch (NumberFormatException e) {
            return 1;
        }
    }

    @Override
    public void notifyNewRecommendations() {
        recommendationsListView.getItems().setAll(model.getRecommendations());
    }
}
