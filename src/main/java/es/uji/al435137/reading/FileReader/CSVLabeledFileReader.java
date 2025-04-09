// Java
package es.uji.al435137.reading.FileReader;

import es.uji.al435137.reading.RowWithLabel;
import es.uji.al435137.reading.TableWithLabels;

import java.util.List;

public class CSVLabeledFileReader extends FileReader<TableWithLabels> {

    public CSVLabeledFileReader(String source) {
        super(source);
    }

    @Override
    void processHeaders(String headers) {
        List<String> cols = new java.util.ArrayList<>(java.util.List.of(headers.split(",")));
        // Remove label column
        cols.remove(cols.size() - 1);
        table.setHeaders(cols);
    }

    @Override
    void processData(String data) {
        String[] values = data.split(",");
        List<Double> rowData = new java.util.ArrayList<>();
        for (int i = 0; i < values.length - 1; i++) {
            rowData.add(Double.parseDouble(values[i]));
        }
        table.addRow(new RowWithLabel(rowData, values[values.length - 1]));
    }
}