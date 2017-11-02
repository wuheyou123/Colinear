import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
    private final int x;
    private final int y;
    private static final double EPSILON = 0.0000001;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }                    // constructs the point (x, y)

    public void draw() {
        StdDraw.point(x, y);
    }               // draws this point

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }               // draws the line segment from this point to that point

    public String toString() {
        return "(" + x + ", " + y + ")";
    }                    // string representation

    @Override
    public  int compareTo(Point that) {
        if (this.y != that.y) {
            return this.y - that.y;
        }
        else {
            return this.x - that.x;
        }
    } // compare two points by y-coordinates, breaking ties by x-coordinates

    public  double slopeTo(Point that) {
        if (this.compareTo(that) == 0) return Double.NEGATIVE_INFINITY;
        if (that.x == this.x) return Double.POSITIVE_INFINITY;
        if (that.y == this.y) return +0.0;

        return (double) (that.y - this.y) / (that.x - this.x);

    }   // the slope between this point and that point
    public Comparator<Point> slopeOrder() {
        return new SlopeComparator(this);
    }   // compare two points by slopes they make with this point

    private class SlopeComparator implements Comparator<Point> {

        private final Point p;

        public SlopeComparator(Point p) {
            this.p = p;
        }

        @Override
        public int compare(Point o1, Point o2) {
            if(o1.compareTo(o2) == 0) return 0;
            double slope1 = p.slopeTo(o1);
            double slope2 = p.slopeTo(o2);
            if(slope1 == Double.POSITIVE_INFINITY && slope2 == Double.POSITIVE_INFINITY) return 0;
            if (Math.abs(slope1 - slope2) < EPSILON) return 0;
            else if (slope1 - slope2 > 0) return  +1;
            else return -1;

        }
    }

    public static void main(String[] args) {
        /*
        Point p = new Point(56, 302);
        Point q = new Point( 56, 375);
        Point r = new Point( 56, 404);
        System.out.println(p.slopeTo(q));
        System.out.println(p.slopeTo(r)+ "  " + (p.slopeTo(q) - p.slopeTo(p)) + " ");
        System.out.println(p.slopeOrder().compare(q, r));
        System.out.println(p.slopeOrder().compare(r, q));

*/
    }


}
