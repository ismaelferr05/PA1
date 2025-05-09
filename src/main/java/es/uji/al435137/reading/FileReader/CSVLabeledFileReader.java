package es.uji.al435137.reading.FileReader;

import es.uji.al435137.reading.RowWithLabel;
import es.uji.al435137.reading.TableWithLabels;

import java.util.ArrayList;
import java.util.List;

public class CSVLabeledFileReader extends FileReader<TableWithLabels> {

    public CSVLabeledFileReader(String source) {
        super(source);
    }

    //Procesa la primera linea del archivo CSV, que contiene los encabezados (headers)
    @Override
    void processHeaders(String headers) {
        this.table=new TableWithLabels();
        List<String> cols = new ArrayList<>(List.of(headers.split(",")));
        cols.remove(cols.size() - 1);
        table.setHeaders(cols);
    }

    //Procesa cada linea de datos del archivo CSV
    @Override
    void processData(String data) {
        String[] values = data.split(",");
        List<Double> numericalValues = extractNumericalValues(values, values.length - 1);
        table.addRow(new RowWithLabel(numericalValues, values[values.length - 1]));
    }
}