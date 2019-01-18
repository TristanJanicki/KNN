import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Random;

public class Clusterer {

    // TODO: Get the data! google maps api

    Cluster[] clusters;

    public Clusterer(int k, Point2D.Float[] points){
        clusters = new Cluster[k];
    }

    void setClusters(Point2D.Float[] points){

        // <editor-fold desc="pick random centroids">
        Random r = new Random();
        HashSet<Integer> usedNumbers = new HashSet<Integer>();
        for(int i = 0; i < clusters.length; i ++){


            int ri = r.nextInt(points.length);
            while(usedNumbers.contains(ri)) ri = r.nextInt(points.length);

            usedNumbers.add(ri);
            clusters[i].centroid = points[ri];
        }
        //</editor-fold>



        while(true){
            Cluster closest = null;
            boolean centroidsSame = true;

            for(Point2D.Float p : points){
                for(Cluster c : clusters){
                    if(closest == null) closest = c;

                    if(Math.pow(p.distanceSq(c.centroid), 2) < Math.pow(p.distanceSq(closest.centroid), 2)){
                        closest = c;
                    }
                }
                closest.members.add(p);
            }


            // Recalculates
            for (Cluster cluster : clusters) {
                Point2D oc = cluster.centroid; // Old Centroid for this clusters
                Point2D nc = cluster.computeCentroid(); // New Centroid for this cluster
                if(!oc.equals(nc)) centroidsSame = false;
            }

            for (Cluster c : clusters) {
                c.members.clear();
            }

            if (centroidsSame) break;

        }

    }

}
