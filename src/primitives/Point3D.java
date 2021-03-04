package primitives;

public class Point3D {
    private Coordinate x;
    private Coordinate y;
    private Coordinate z;

    //static const Point3D ZERO = new Point3D(new Coordinate(0),new Coordinate(0),new Coordinate(0));

    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        this.x = x;
        this.y = y;
        this.z = z;
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
        //est ce que je met this.x.equals ??
    }

    @Override
    public String toString() {
        return "Point3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public Vector substract(Point3D point3D)
    {
        Coordinate coordinateX = new Coordinate(this.x.coord - point3D.x.coord);
        Coordinate coordinateY = new Coordinate(this.y.coord - point3D.y.coord);
        Coordinate coordinateZ = new Coordinate(this.z.coord - point3D.z.coord);
        Point3D newPoint3D = new Point3D(coordinateX,coordinateY,coordinateZ);

        return new Vector(newPoint3D);

    }

    public Point3D add(Vector vec)
    {
        Coordinate coordinateX = new Coordinate(this.x.coord + vec.getHead().x.coord);
        Coordinate coordinateY = new Coordinate(this.y.coord + vec.getHead().y.coord);
        Coordinate coordinateZ = new Coordinate(this.z.coord + vec.getHead().z.coord);
        Point3D newPoint3D = new Point3D(coordinateX,coordinateY,coordinateZ);
        return newPoint3D;
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

    //verifier pr le const static ZERO
    //verifier pr les fonctions des distances laquel est au carre/racine



}
