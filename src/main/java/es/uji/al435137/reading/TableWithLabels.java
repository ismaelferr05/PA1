package es.uji.al435137.reading;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableWithLabels extends Table {
    protected Map<String,Integer> labelsToIndex;

    public TableWithLabels(){
        super();
        labelsToIndex = new HashMap<>();
    }

    public Integer labelToIndex(String label){
        return labelsToIndex.get(label);
    }

    public void insertLabelToIndex(String label){
        labelsToIndex.put(label,labelsToIndex.size());
    }

    public Integer getLabelAsInteger(String label) {
        if (!labelsToIndex.containsKey(label)) {
            labelsToIndex.put(label, labelsToIndex.size());
        }
        return labelsToIndex.get(label);
    }

    @Override
    public RowWithLabel getRowAt(int rowNumber){
        return (RowWithLabel) rows.get(rowNumber);
    }
}
