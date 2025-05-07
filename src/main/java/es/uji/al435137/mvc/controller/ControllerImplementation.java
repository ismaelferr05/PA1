package es.uji.al435137.mvc.controller;

import es.uji.al435137.mvc.model.Model;
import es.uji.al435137.mvc.view.View;

public class ControllerImplementation implements Controller {
    private Model model;
    private View view;

    public void setModelo(Model modelo) {
        this.model = modelo;
    }

    public void setVista(View view) {
        this.view = view;
    }

    @Override
    public void trainRecommendations() {
        int algorithmType = view.getAlgorithm();
        int distanceType = view.getDistance();
        int amount = view.getRecommendationsAmount();
        String song = view.getSelectedSong();
        if (amount <= 0) {
            view.showError("Enter a valid number of recommendations.");
        }else{
            if (song == null || song.isEmpty()) {
                view.showError("You must select a song.");
            }else{
                view.clearError();
                try {
                    model.setAlgorithm(algorithmType, distanceType);
                    model.calculateRecommendations(song, amount);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void moreRecommendations() {
        int recommendationsAmount = view.getRecommendationsAmount();
        model.calculateRecommendations(view.getSelectedSong(), recommendationsAmount * 2);
    }
}