
import java.util.*;
import edu.princeton.cs.algs4.StdDraw;


public class FastCollinearPoints {
    private List<LineSegment> lSegments;

    public FastCollinearPoints(Point[] points)  {
        if (points == null) {
            throw new IllegalArgumentException("Ilegal input, check!");
        }

        int length = points.length;
        for (int i = 0; i < length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("Input contains null point, check!");
            }
        }

        checkRepeated(points);

        lSegments = new ArrayList<>(20);

        for (int i = 0; i < points.length - 2; i++) {
            getLineSegment(points, i);
        }

    }    // finds all line segments containing 4 or more points
    public           int numberOfSegments()  {
        return lSegments.size();
    }      // the number of line segments
    public LineSegment[] segments()  {

        LineSegment[] segarray = new LineSegment[lSegments.size()];
        return lSegments.toArray(segarray);
    }              // the line segments

    private void checkRepeated(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("Input contains repeated points!");
                }
            }
        }

    }

    private void getLineSegment(Point[] p, int index) {

        Point original = p[index];

        Point[] newp = new Point[p.length];
        System.arraycopy(p, 0, newp, 0, p.length);
        Arrays.sort(newp, original.slopeOrder());

        for (int i = 1; i < newp.length - 2;) {
            int n = 1;
            while (i + n < newp.length  && original.slopeTo(newp[i]) == original.slopeTo(newp[i + n])) {
                n++;
            }

            if (n >= 3) {

                Point[] fourpoints = new Point[n + 1];
                fourpoints[0] = original;
                for (int j = 0; j < n; j++) {
                    fourpoints[j + 1] = newp[i + j];

                }
                Arrays.sort(fourpoints);

                LineSegment newseg = new LineSegment(fourpoints[0], fourpoints[n]);
                String newsegstring = newseg.toString();

                boolean repeatedline = false;

                for (LineSegment ls: lSegments) {
                    if (ls.toString().equals(newsegstring)) {
                        repeatedline = true;
                    }
                }

                if (!repeatedline) {

                    lSegments.add(newseg);
                    // System.out.print(original+ "  " + n + "  " + fourpoints[0].toString() + "  " + fourpoints[n].toString() + "\n");

                }

            }
            i += n;
        }
    }


    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        // In in = new In("input40.txt");
        // In in = new In("equidistant.txt");
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.clear();

        StdDraw.enableDoubleBuffering();
        StdDraw.setPenRadius(0.02);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();


        // print and draw the line segments
        StdDraw.setPenRadius(0.005);
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

    }

}
