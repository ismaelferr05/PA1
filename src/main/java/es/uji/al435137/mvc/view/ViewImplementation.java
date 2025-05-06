package es.uji.al435137.mvc.view;

import es.uji.al435137.mvc.controller.Controller;
import es.uji.al435137.mvc.model.Model;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewImplementation implements View {
    private Controller controller;
    private Model model;
    private ChoiceBox<String> algorithmChoice;
    private ChoiceBox<String> distanceChoice;
    private TextField amountField;
    private Button trainButton;
    private Label resultLabel;

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
        amountField.setPromptText("Cantidad de canciones");

        trainButton = new Button("Entrenar y Recomendar");
        trainButton.setOnAction(e -> {
            controller.trainRecommendations();
            resultLabel.setText("Entrenamiento y recomendaciones listos");
        });

        resultLabel = new Label();

        root.getChildren().addAll(new Label("Algoritmo:"), algorithmChoice,
                new Label("Distancia:"), distanceChoice,
                new Label("Cantidad:"), amountField,
                trainButton, resultLabel);

        Scene scene = new Scene(root, 300, 250);
        stage.setTitle("Recomendaciones");
        stage.setScene(scene);
        stage.show();
    }

    public int getAlgorithm() {
        return algorithmChoice.getValue().equals("K-Means") ? 0 : 1;
    }

    public int getDistance() {
        return distanceChoice.getValue().equals("Euclidean") ? 0 : 1;
    }

    public String getSelectedSong() {
        return "EjemploDeCancion";
    }

    public int getRecommendationsAmount() {
        try {
            return Integer.parseInt(amountField.getText());
        } catch (NumberFormatException e) {
            return 1;
        }
    }

    public void notifyNewRecommendations() {
        resultLabel.setText("Nuevas recomendaciones disponibles");
    }
}