package es.uji.al435137.reading.FileReader;

import es.uji.al435137.reading.Table;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class FileReader<T extends Table> extends ReaderTemplate<T> {
    protected Scanner scanner;

    public FileReader(String source) {
        super(source);
    }

    //Método para abrir la fuente de datos
    @Override
    void openSource(String source) throws FileNotFoundException, URISyntaxException {
        File file = new File(getClass().getClassLoader().getResource(source).toURI().getPath());
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        scanner = new Scanner(file);
    }

    //Método para cerrar la fuente de datos
    @Override
    void closeSource() {
        if (scanner != null) {
            scanner.close();
        }
    }

    //Indica si hay mas lineas por leer en el archivo
    @Override
    boolean hasMoreData() {
        return scanner.hasNextLine();
    }

    //Obtiene la siguiente línea del archivo
    @Override
    String getNextData() {
        return scanner.nextLine();
    }

    protected List<Double> extractNumericalValues(String[] values, int endIndex) {
        List<Double> rowData = new ArrayList<>();
        for (int i = 0; i < endIndex; i++) {
            rowData.add(Double.parseDouble(values[i]));
        }
        return rowData;
    }

}