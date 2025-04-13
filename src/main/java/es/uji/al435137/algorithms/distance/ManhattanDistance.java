package es.uji.al435137.algorithms.distance;

import java.util.List;

public class ManhattanDistance implements Distance{

    //Calcula la distancia euclidiana entre dos puntos p y q
    @Override
    public double calculateDistance(List<Double> p, List<Double> q) {
        if (p.size() != q.size()) {
            throw new IllegalArgumentException();
        }
        double distancia = 0;

        //Formula de la distancia de Manhattan: |p1 - q1| + |p2 - q2| + ... + |pn - qn|
        for (int i = 0; i < p.size(); i++) {
            distancia += Math.abs(p.get(i) - q.get(i));
        }

        return distancia;
    }
}
