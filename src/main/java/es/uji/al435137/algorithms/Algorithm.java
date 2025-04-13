package es.uji.al435137.algorithms;

import es.uji.al435137.reading.Table;

public interface Algorithm<T extends Table,G,H> {
    /*
    T = Tipo de tabla
    G = Valor devuelto de estimate
    H = Tipo de datos a estimar
    */

    G estimate(H sample);
    void train(T data);
}
