package es.uji.al435137.mvc.view;

import es.uji.al435137.mvc.controller.Controller;
import es.uji.al435137.mvc.model.Model;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.scene.layout.Region;

public class ViewImplementation implements View {
    private Controller controller;
    private Model model;

    //Atributos relacionados con la eleccion de parametros
    private ChoiceBox<String> algorithmChoice;
    private ChoiceBox<String> distanceChoice;

    // Componentes relacionados con la interacción principal del usuario
    private TextField amountField;
    private Button trainButton;
    private Label statusLabel;

    // Componentes para mostrar listas de elementos
    private ListView<String> songsListView;
    private ListView<String> recommendationsListView;

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void setModel(Model model) {
        this.model = model;
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

        trainButton = new Button("Train and Recommend");
        trainButton.setOnAction(e -> controller.trainRecommendations());

        statusLabel = new Label();
        statusLabel.setWrapText(true);
        statusLabel.setMinHeight(Region.USE_PREF_SIZE);
        statusLabel.setAlignment(Pos.CENTER);
        statusLabel.setTextAlignment(TextAlignment.CENTER);

        songsListView = new ListView<>();
        ObservableList<String> songs = model.getSongsList();
        songsListView.setItems(songs);
        VBox.setVgrow(songsListView, Priority.ALWAYS);

        recommendationsListView = new ListView<>();
        VBox.setVgrow(recommendationsListView, Priority.ALWAYS);

        root.getChildren().addAll(
                new Label("Algorithm:"), algorithmChoice,
                new Label("Distance:"), distanceChoice,
                new Label("Number of recommendations:"), amountField,
                new Label("Select a song:"), songsListView, trainButton, statusLabel,
                new Label("Recommendations:"), recommendationsListView
        );

        Scene scene = new Scene(root, 400, 600);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("dark-theme.css").toExternalForm());

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
        if(distanceChoice.getValue().equals("Euclidean")){
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
            return 0;
        }
    }

    @Override
    public void notifyNewRecommendations() {

        recommendationsListView.getItems().setAll(model.getRecommendations());
    }

    public void setText(String text){
        statusLabel.setText(text);
    }

    @Override
    public void clearText() {
        statusLabel.setText("");
    }
}
