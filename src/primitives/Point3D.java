package primitives;

public class Point3D {
    protected Coordinate x;
    protected Coordinate y;
    protected Coordinate z;

    public static final Point3D ZERO = new Point3D(0,0,0);

    public Point3D(double x, double y, double z) {
        this.x = new Coordinate(x);
        this.y = new Coordinate(y);
        this.z = new Coordinate(z);
    }



    public void setX(Coordinate x) {
        this.x = x;
    }

    public void setY(Coordinate y) {
        this.y = y;
    }

    public void setZ(Coordinate z) {
        this.z = z;
    }

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

    public Vector subtract(Point3D point3D)
    {

        return new Vector( this.x.coord - point3D.x.coord,this.y.coord - point3D.y.coord,this.z.coord - point3D.z.coord);

    }

    public Point3D add(Vector vec)
    {
        return new Point3D(this.x.coord + vec.getHead().x.coord,this.y.coord + vec.getHead().y.coord,this.z.coord + vec.getHead().z.coord);

    }

    public double distanceSquared(Point3D point3D)
    {
        return (this.x.coord - point3D.x.coord)*(this.x.coord - point3D.x.coord)
                + (this.y.coord - point3D.y.coord)*(this.y.coord - point3D.y.coord)
                + (this.z.coord - point3D.z.coord)*(this.z.coord - point3D.z.coord);
    }

    public double distance(Point3D point3D)
    {
        return Math.sqrt(distanceSquared(point3D));
    }






}
