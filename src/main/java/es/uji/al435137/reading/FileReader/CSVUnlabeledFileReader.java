// Java
package es.uji.al435137.reading.FileReader;

import es.uji.al435137.reading.Row;
import es.uji.al435137.reading.Table;

public class CSVUnlabeledFileReader extends FileReader<Table> {

    @Override
    void processHeaders(String headers) {
        table.setHeaders(java.util.List.of(headers.split(",")));
    }

    @Override
    void processData(String data) {
        String[] values = data.split(",");
        java.util.List<Double> rowData = new java.util.ArrayList<>();
        for (String value : values) {
            rowData.add(Double.parseDouble(value));
        }
        table.addRow(new Row(rowData));
    }
}