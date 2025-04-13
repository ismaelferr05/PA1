package es.uji.al435137.algorithms;

import es.uji.al435137.algorithms.distance.Distance;
import es.uji.al435137.algorithms.distance.EuclideanDistance;
import es.uji.al435137.reading.FileReader.CSVLabeledFileReader;
import es.uji.al435137.reading.TableWithLabels;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KMeansTest {

    private TableWithLabels iris;
    private KMeans kMeans;

    private int irisClusters = 3;
    private int numIterations = 10;
    private long seed = 53;

    @BeforeEach
    void setUp() throws InvalidClusterNumberException, IOException, URISyntaxException {
        iris = new CSVLabeledFileReader("iris.csv").readTableFromSource();
        Distance distance = new EuclideanDistance();
        kMeans = new KMeans(distance,irisClusters, numIterations, seed);
        kMeans.train(iris);
    }

    @AfterEach
    void tearDown() {
        kMeans = null;
    }

    @Test
    @DisplayName("KMeans - estimate")
    void estimate() {
        int class1 = kMeans.estimate(List.of(5.1,3.1,1.0,0.3)); // Setosa
        int class2 = kMeans.estimate(List.of(5.0,3.6,1.7,0.4)); // Setosa
        int class3 = kMeans.estimate(List.of(6.1,3.3,4.4,1.7)); // Versicolor
        int class4 = kMeans.estimate(List.of(6.3,2.6,4.1,1.2)); // Versicolor
        int class5 = kMeans.estimate(List.of(7.8,3.1,6.1,2.2)); // Virginica
        int class6 = kMeans.estimate(List.of(6.6,3.0,5.3,2.1)); // Virginica

        assertEquals(class1, class2);
        assertEquals(class3, class4);
        assertEquals(class5, class6);

        assertNotEquals(class1, class3);
        assertNotEquals(class1, class5);
        assertNotEquals(class3, class5);
    }

    @Test
    @DisplayName("KMeans train - more clusters than samples")
    void train_invalidClusters() {
        Distance distance = new EuclideanDistance();
        kMeans = new KMeans(distance,200, numIterations, seed);
        Exception e = assertThrows(InvalidClusterNumberException.class, () -> kMeans.train(iris));
        System.out.println("Clusters: "+((InvalidClusterNumberException)e).getNumberOfCusters());
        assertTrue(((InvalidClusterNumberException)e).getNumberOfCusters() > iris.getRowCount());
    }
}