package es.uji.al435137.grouping;

    import es.uji.al435137.reading.Table;
    import es.uji.al435137.reading.Row;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Random;

    public class KMeans {
        private int numClusters;
        private int numIterations;
        private long seed;
        private List<List<Double>> centroids;

        public KMeans(int numClusters, int numIterations, long seed) {
            this.numClusters = numClusters;
            this.numIterations = numIterations;
            this.seed = seed;
            this.centroids = new ArrayList<>();
        }

        public void train(Table data) {
            List<List<Double>> points = new ArrayList<>();
            for (int i = 0; i < data.getRowCount(); i++) {
                points.add(data.getRowAt(i).getData());
            }

            Random rnd = new Random(seed);
            // Random initialization of centroids
            for (int i = 0; i < numClusters; i++) {
                centroids.add(new ArrayList<>(points.get(rnd.nextInt(points.size()))));
            }

            for (int iter = 0; iter < numIterations; iter++) {
                // Assign points to nearest centroid
                List<List<List<Double>>> clusters = new ArrayList<>();
                for (int i = 0; i < numClusters; i++) {
                    clusters.add(new ArrayList<>());
                }

                for (List<Double> p : points) {
                    int clusterIdx = estimate(p);
                    clusters.get(clusterIdx).add(p);
                }

                // Update centroids
                for (int c = 0; c < numClusters; c++) {
                    if (!clusters.get(c).isEmpty()) {
                        List<Double> newCentroid = new ArrayList<>();
                        int dim = clusters.get(c).get(0).size();
                        for (int d = 0; d < dim; d++) {
                            double sum = 0.0;
                            for (List<Double> cp : clusters.get(c)) {
                                sum += cp.get(d);
                            }
                            newCentroid.add(sum / clusters.get(c).size());
                        }
                        centroids.set(c, newCentroid);
                    }
                }
            }
        }

        public Integer estimate(List<Double> sample) {
            double minDist = Double.MAX_VALUE;
            int clusterIndex = 0;
            for (int i = 0; i < centroids.size(); i++) {
                double dist = 0.0;
                for (int j = 0; j < sample.size(); j++) {
                    double diff = sample.get(j) - centroids.get(i).get(j);
                    dist += diff * diff;
                }
                if (dist < minDist) {
                    minDist = dist;
                    clusterIndex = i;
                }
            }
            return clusterIndex;
        }
    }