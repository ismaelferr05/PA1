package es.uji.al435137.mvc.controller;

import es.uji.al435137.mvc.model.Model;
import es.uji.al435137.mvc.view.View;

public interface Controller {
    void trainRecommendations();

    void setModel(Model model);

    void setView(View view);
}
