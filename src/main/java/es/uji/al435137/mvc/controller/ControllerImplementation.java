package es.uji.al435137.mvc.controller;

import es.uji.al435137.mvc.model.Model;
import es.uji.al435137.mvc.view.View;

public class ControllerImplementation implements Controller {
    private Model model;
    private View view;

    public void setModelo(Model modelo) {
        this.model = modelo;
    }

    public void setVista(View vista) {
        this.view = vista;
    }

    @Override
    public void trainRecommendations(){
        int tipoAlgoritmo = view.getAlgorithm();
        int tipoDistancia = view.getDistance();
        String cancion = view.getSelectedSong();
        model.setAlgorithm(tipoAlgoritmo,tipoDistancia);
        model.calculateRecommendations(cancion, 30);
    }

    @Override
    public void moreRecommendations() {
        int recommendationsAmount = view.getRecommendationsAmount();
        model.calculateRecommendations(view.getSelectedSong(), recommendationsAmount * 2);
    }
}