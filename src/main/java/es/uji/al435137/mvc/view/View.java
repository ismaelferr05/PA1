package es.uji.al435137.mvc.view;

import es.uji.al435137.mvc.controller.Controller;
import es.uji.al435137.mvc.model.Model;
import javafx.stage.Stage;

public interface View {
    void setController(Controller controller);
    void setModel(Model model);
    void createGUI(Stage stage);

    int getAlgorithm();
    int getDistance();
    String getSelectedSong();
    int getRecommendationsAmount();
    void notifyNewRecommendations();

    void clearText();
    void setText(String s);
}