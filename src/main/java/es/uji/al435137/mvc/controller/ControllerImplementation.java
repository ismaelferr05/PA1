package es.uji.al435137.mvc.controller;

import es.uji.al435137.mvc.model.Model;
import es.uji.al435137.mvc.view.View;
import es.uji.al435137.mvc.view.ViewImplementation;

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
        int tipoAlgoritmo = view.getAlgorithm();
        int tipoDistancia = view.getDistance();
        int amount = view.getRecommendationsAmount();
        String cancion = view.getSelectedSong();
        try {
            model.setAlgorithm(tipoAlgoritmo, tipoDistancia);
            model.calculateRecommendations(cancion, amount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void moreRecommendations() {
        int recommendationsAmount = view.getRecommendationsAmount();
        model.calculateRecommendations(view.getSelectedSong(), recommendationsAmount * 2);
    }
}