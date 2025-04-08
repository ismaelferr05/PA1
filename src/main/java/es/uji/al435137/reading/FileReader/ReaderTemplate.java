package es.uji.al435137.reading.FileReader;

import es.uji.al435137.reading.Table;

import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class ReaderTemplate<T extends Table> {
    protected T table;
    protected String source;

    public ReaderTemplate(String source) {
        this.source = source;
    }

    abstract void openSource(String source) throws FileNotFoundException;
    abstract void processHeaders(String headers);
    abstract void processData(String data);
    abstract void closeSource();
    abstract boolean hasMoreData();
    abstract String getNextData();


    // El siguiente método llama abre el recurso, inserta las cabeceras, lee e inserta los datos y finalmente cierra el recurso
    public final T readTableFromSource() throws FileNotFoundException {
        openSource(source);

        if (hasMoreData())
            processHeaders(getNextData());

        while (hasMoreData())
            processData(getNextData());

        closeSource();
        return table;
    }
}