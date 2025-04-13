package es.uji.al435137.reading.FileReader;

import es.uji.al435137.reading.Table;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class FileReader<T extends Table> extends ReaderTemplate<T> {
    protected Scanner scanner;

    public FileReader(String source) {
        super(source);
    }

    //Método para abrir la fuente de datos
    @Override
    void openSource(String source) throws FileNotFoundException {
        File file = new File(source);
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
}