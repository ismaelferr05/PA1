package es.uji.al435137.reading;

import java.util.ArrayList;
import java.util.List;

public class Row {
    protected List<Double> items;

    public Row(){
        items = new ArrayList<>();
    }

    public Row(List<Double> items){
        items = new ArrayList<>();
    }

    public List<Double> getData(){
        return items;
    }

    public void addData(double dato){
        items.add(dato);
    }
}
