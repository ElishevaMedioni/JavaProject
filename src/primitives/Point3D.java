package primitives;

/**
 * Point3D Class - represent a 3 dimensional point in a 3D space
 */
public class Point3D {
    /**
     * Has three coordinate x, y, z
     */
    protected Coordinate x;
    protected Coordinate y;
    protected Coordinate z;

    public static final Point3D ZERO = new Point3D(0,0,0);

    /**
     * Constructor of point3D receiving 3 coordinates
     * and accord each value with his field
     * @param x value of the coordinate x
     * @param y value of the coordinate y
     * @param z value of the coordinate z
     */
    public Point3D(double x, double y, double z) {
        this.x = new Coordinate(x);
        this.y = new Coordinate(y);
        this.z = new Coordinate(z);
    }

    /**
     * substract point3D to another point3d
     * @param point3D point3D value
     * @return the result of the substraction as a vector
     */
    public Vector subtract(Point3D point3D)
    {
        return new Vector( this.x.coord - point3D.x.coord,this.y.coord - point3D.y.coord,this.z.coord - point3D.z.coord);
    }

    /**
     * add a point3D to the head of the vector
     * @param vec point3D value
     * @return the result of the addition
     */
    public Point3D add(Vector vec)
    {
        return new Point3D(this.x.coord + vec.getHead().x.coord,this.y.coord + vec.getHead().y.coord,this.z.coord + vec.getHead().z.coord);

    }

    /**
     *calculate the distance between two point3D without the sqr
     * @param point3D point3D value
     * @return the distance between two Point3D without the sqr
     */
    public double distanceSquared(Point3D point3D)
    {
        return (this.x.coord - point3D.x.coord)*(this.x.coord - point3D.x.coord)
                + (this.y.coord - point3D.y.coord)*(this.y.coord - point3D.y.coord)
                + (this.z.coord - point3D.z.coord)*(this.z.coord - point3D.z.coord);
    }

    /**
     * calculate the distance between two point3D
     * @param point3D  point3D value
     * @return the distance between two Point3D
     */
    public double distance(Point3D point3D)
    {
        return Math.sqrt(distanceSquared(point3D));
    }

    /**
     * check if two point are linear dependent
     * @param point3D point3D value
     * @return if they're linear dependent or not
     */
    public boolean checkLinearDependent(Point3D point3D)
    {
        double num= x.coord/ point3D.x.coord;
        return y.coord/ point3D.y.coord==num && z.coord/ point3D.z.coord==num;
    }

    /**
     * set the value of coord x
     * @param x coord value
     */
    public void setX(Coordinate x) {
        this.x = x;
    }

    /**
     * set the value of coord y
     * @param y coord value
     */
    public void setY(Coordinate y) {
        this.y = y;
    }

    /**
     * set the value of coord z
     * @param z coord value
     */
    public void setZ(Coordinate z) {
        this.z = z;
    }


    /*************** Admin *****************/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return x.equals(point3D.x) && y.equals(point3D.y) && z.equals(point3D.z);

    }

    @Override
    public String toString() {
        return "Point3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

}
