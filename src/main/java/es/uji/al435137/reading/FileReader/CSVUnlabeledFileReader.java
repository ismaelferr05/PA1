package es.uji.al435137.reading.FileReader;

import es.uji.al435137.reading.Row;
import es.uji.al435137.reading.Table;

import java.util.ArrayList;
import java.util.List;

public class CSVUnlabeledFileReader extends FileReader<Table> {

    public CSVUnlabeledFileReader(String source) {
        super(source);
    }

    //Procesa la primera linea del archivo CSV, que contiene los encabezados (headers)
    @Override
    void processHeaders(String headers) {
        this.table=new Table();
        String[] headerArray = headers.split(",");
        List<String> headerList = new ArrayList<>();

        for (String header : headerArray) {
            headerList.add(header);
        }

        table.setHeaders(headerList);
    }

    //Procesa cada linea de datos del archivo CSV
    @Override
    void processData(String data) {
        String[] values = data.split(",");
        List<Double> rowData = new ArrayList<>();
        for (String value : values) {
            rowData.add(Double.parseDouble(value));
        }
        table.addRow(new Row(rowData));
    }
}