package es.uji.al435137.reading.FileReader;

import es.uji.al435137.reading.Row;
import es.uji.al435137.reading.Table;

import java.util.ArrayList;
import java.util.List;

public class CSVUnlabeledFileReader extends FileReader<Table> {

    public CSVUnlabeledFileReader(String source) {
        super(source);
    }

    @Override
    void processHeaders(String headers) {
        String[] headerArray = headers.split(",");
        List<String> headerList = new ArrayList<>();

        for (String header : headerArray) {
            headerList.add(header);
        }

        table.setHeaders(headerList);
    }

    @Override
    void processData(String data) {
        String[] values = data.split(",");
        List<Double> rowData = new java.util.ArrayList<>();
        for (String value : values) {
            rowData.add(Double.parseDouble(value));
        }
        table.addRow(new Row(rowData));
    }
}