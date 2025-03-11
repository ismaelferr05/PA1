package es.uji.al435137.algorithms;

public interface Algorithm<T,G,H> {
    /*
    T -> Tipo de tabla
    G -> Valor devuelto de estimate
    H -> Tipo de datos a estimar
    */

    G estimate(H sample);
    void train(T data);
}
