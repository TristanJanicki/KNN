import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Cluster {

    ArrayList<Point2D.Float> members = new ArrayList<Point2D.Float>();
    Point2D.Float centroid = new Point2D.Float();

    Point2D computeCentroid(){
        float avgX = 0;
        float avgY = 0;

        for(Point2D p2d : members){
            avgX += p2d.getX();
            avgY += p2d.getY();
        }

        avgX /= members.size();
        avgY /= members.size();

        return centroid = new Point2D.Float(avgX, avgY);
    }
}
