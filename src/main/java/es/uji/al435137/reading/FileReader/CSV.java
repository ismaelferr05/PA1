package es.uji.al435137.reading.FileReader;

import es.uji.al435137.reading.Row;
import es.uji.al435137.reading.RowWithLabel;
import es.uji.al435137.reading.Table;
import es.uji.al435137.reading.TableWithLabels;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class CSV {

    public Table readTable(String fileName) throws IOException, URISyntaxException {
        String filePath=getClass().getClassLoader().getResource(fileName).toURI().getPath();
        Table table = new Table();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (firstLine) {
                    table.setHeaders(List.of(values));
                    firstLine = false;
                    continue;
                }

                List<Double> rowData = new ArrayList<>();
                for (String value : values) {
                    rowData.add(Double.parseDouble(value));
                }
                table.addRow(new Row(rowData));
            }
        }
        return table;
    }

    public TableWithLabels readTableWithLabels(String fileName) throws IOException, URISyntaxException {
        String filePath=getClass().getClassLoader().getResource(fileName).toURI().getPath();

        TableWithLabels table = new TableWithLabels();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (firstLine) {
                    List<String> headers = new ArrayList<>(List.of(values));
                    headers.remove(headers.size() - 1); // Quitar la etiqueta de clase
                    table.setHeaders(headers);
                    firstLine = false;
                    continue;
                }

                List<Double> rowData = new ArrayList<>();
                for (int i = 0; i < values.length - 1; i++) {
                    rowData.add(Double.parseDouble(values[i]));
                }
                String label = values[values.length - 1];
                RowWithLabel row = new RowWithLabel(rowData, label);
                table.addRow(row);
            }
        }
        return table;
    }
}


