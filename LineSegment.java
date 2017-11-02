public class LineSegment {
    private final Point p;
    private final Point q;
    public LineSegment(Point p, Point q)  {
        if (p == null || q == null) {
            throw new NullPointerException("argument is null");
        }
        this.p = p;
        this.q = q;
    }      // constructs the line segment between points p and q
    public   void draw()   {
        p.drawTo(q);
    }                     // draws this line segment
    public String toString()   {
        return p.toString() + "->" + q.toString();
    }               // string representation
/*
    public int hashCode() {
        throw new UnsupportedOperationException();
    }
*/

}
