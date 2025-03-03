package es.uji.al435137.reading;

import java.util.ArrayList;
import java.util.List;

public class Table {
    protected List<String> headers;
    protected List<Row> rows;

    public Table(){
        headers = new ArrayList<>();
        rows = new ArrayList<>();
    }

    public Row getRowAt(int rowNumber) {
        return rows.get(rowNumber);
    }

    public List<Double> getColumnAt(int index) {
        List<Double> column = new ArrayList<>();
        for (Row row : rows) {
            if (row.getData() != null && index < row.getData().size()) {
                column.add(row.getData().get(index));
            }
        }
        return column;
    }


    public void addHeader(String header){
        this.headers.add(header);
    }
    public void addRow(Row row){
        rows.add(row);
    }
    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String>headers){
        this.headers=headers;
    }

    public List<Row> getRows(){
        return rows;
    }

    public int getRowCount(){
        return rows.size();
    }

}
