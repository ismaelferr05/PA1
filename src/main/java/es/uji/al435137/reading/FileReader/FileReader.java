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

    @Override
    void openSource(String source) throws FileNotFoundException {
        File file = new File(source);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        scanner = new Scanner(file);
    }

    @Override
    void closeSource() {
        if (scanner != null) {
            scanner.close();
        }
    }

    @Override
    boolean hasMoreData() {
        return scanner.hasNextLine();
    }

    @Override
    String getNextData() {
        return scanner.nextLine();
    }
}