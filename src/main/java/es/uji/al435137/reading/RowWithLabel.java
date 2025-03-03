package es.uji.al435137.reading;

import java.util.List;

public class RowWithLabel extends Row {
    protected String label;

    public RowWithLabel(){
        super();
    }

    public RowWithLabel(List<Double> items, String label){
        super(items);
        this.label=label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label){
        this.label = label;
    }
}
