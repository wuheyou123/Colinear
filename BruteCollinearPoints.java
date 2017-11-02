import edu.princeton.cs.algs4.StdDraw;

import java.util.*;

public class BruteCollinearPoints {

    private List<LineSegment> lSegments;


    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Ilegal input, check!");
        }

        int length = points.length;
        for (int i = 0; i < length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("Input contains null point, check!");
            }
            checkRepeated(points);
            // this.points[i] = points[i];
        }

        lSegments = new ArrayList<>();


        for (int i = 0; i < length - 3; i++) {
            for (int j = i + 1; j < length - 2; j++) {
                for (int k = j + 1; k < length - 1; k++) {
                    for (int m = k + 1; m < length; m++) {
                        if (checkLine(points, i, j, k, m)) {

                            Point[] fourpoints = {points[i], points[j], points[k], points[m]};
                            Arrays.sort(fourpoints);

                            LineSegment newseg = new LineSegment(fourpoints[0], fourpoints[3]);
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

                    }
                }
            }
        }



    }  // finds all line segments containing 4 points

    public int numberOfSegments() {
        return lSegments.size();
    }    // the number of line segments


    public LineSegment[] segments() {

        LineSegment[] segarray = new LineSegment[lSegments.size()];
        return lSegments.toArray(segarray);
    }      // the line segments


    private void checkRepeated(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("Input contains repeated points!");
                }
            }
        }

    }

    private boolean checkLine(Point[] p, int i, int j, int k, int m) {
        double slp = p[i].slopeTo(p[j]);
        if (p[i].slopeTo(p[k]) != slp) return false;
        if (p[i].slopeTo(p[m]) != slp) return false;
        return true;
    }



    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        // In in = new In("equidistant.txt");
        // In in = new In("input3.txt");
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

    }
}
