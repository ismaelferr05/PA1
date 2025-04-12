// TODO: Remplazar <nombre> por el nombre de tu paquete
package es.uji.al435137.reading.FileReader;

// TODO: Pon los imports especificos a tu proyecto

import es.uji.al435137.reading.Table;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVUnlabeledFileReaderTest {

    private CSVUnlabeledFileReader reader;

    @BeforeEach
    void setUp() throws URISyntaxException {
        reader = new CSVUnlabeledFileReader("miles_dollars.csv");
    }

    @AfterEach
    void tearDown() {
        reader = null;
    }

    // TODO: Dependiendo de cómo manejas las excepciones, puedes añadir un try/catch o un lanzamiento de excepción aquí.
    @Test
    void readTableFromSource() throws FileNotFoundException, URISyntaxException {
        Table table = reader.readTableFromSource();

        // assert that the table is not null
        assertNotNull(table);
        // assert that the table object is actually an instance of Table
        assertInstanceOf(Table.class, table);

        // assert that the correct number of rows are read
        assertEquals(25, table.getRowCount());

        // assert that the headers are correctly read
        assertEquals(List.of("Miles", "Dollars"), table.getHeaders());

        // assert that the first and last row are correctly read
        assertEquals(List.of(1211.0,1802.0), table.getRowAt(0).getData());
        assertEquals(List.of(5439.0,6964.0), table.getRowAt(24).getData());
        // assert that an arbitrary row is correctly read
        assertEquals(List.of(3852.0,4801.0), table.getRowAt(16).getData());
    }
}