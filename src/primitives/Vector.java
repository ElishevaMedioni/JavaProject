package primitives;
import java.lang.Math.*;
public class Vector
{
    private Point3D head;

    public Point3D getHead() {
        return head;
    }

    public void setHead(Point3D head) {
        this.head = head;
    }

    public Vector(double x, double y, double z) {
        Point3D point3D = new Point3D(new Coordinate(x),new Coordinate(y),new Coordinate(z));
        if(point3D.equals(Point3D.ZERO))
            throw new IllegalArgumentException("ERROR, The vector is ZERO vector");
        this.head = point3D;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Vector)) return false;
        if (!super.equals(object)) return false;
        Vector vector = (Vector) object;
        return java.util.Objects.equals(head, vector.head);
    }

    public Vector add(Vector vector){
        return new Vector(new Point3D(new Coordinate(getHead().x.coord+vector.getHead().x.coord),
                new Coordinate(getHead().y.coord+vector.getHead().y.coord),
                new Coordinate(getHead().z.coord+vector.getHead().z.coord)));
    };
    public Vector subtract(Vector vector){
        return new Vector(new Point3D(new Coordinate(getHead().x.coord-vector.getHead().x.coord),
                new Coordinate(getHead().y.coord-vector.getHead().y.coord),
                new Coordinate(getHead().z.coord-vector.getHead().z.coord)));
    };
    public Vector scale(double doub){
        return new Vector(new Point3D(new Coordinate(getHead().x.coord*doub),
                new Coordinate(getHead().y.coord*doub),
                new Coordinate(getHead().z.coord*doub)));
    };
    public Vector crossProduct(Vector vector){
       return new Vector(new Point3D(
               new Coordinate((getHead().y.coord*vector.getHead().z.coord-
                       getHead().z.coord*vector.getHead().y.coord)),
               new Coordinate((getHead().z.coord*vector.getHead().x.coord-
                       getHead().x.coord*vector.getHead().z.coord)),
               new Coordinate((getHead().x.coord*vector.getHead().y.coord-
                       getHead().y.coord*vector.getHead().x.coord))));
    };
    public double dotProduct(Vector vector){
        return getHead().x.coord*vector.getHead().x.coord+
                getHead().y.coord*vector.getHead().y.coord+
                getHead().z.coord*vector.getHead().z.coord;
    };
    public double lengthSquared(){
        return getHead().x.coord*getHead().x.coord+getHead().y.coord*getHead().y.coord+
                getHead().z.coord*getHead().z.coord;
    };
    public double length(){
        return Math.sqrt(lengthSquared());
    };
    public Vector normalize(){
        double num=length();
        head=new Point3D(new Coordinate(getHead().x.coord/num),new Coordinate(getHead().y.coord/num),
                new Coordinate(getHead().z.coord/num));
        return new Vector(head);
    };
    public Vector normalized(){
        double num=length();
        return new Vector(new Point3D(new Coordinate(getHead().x.coord/num),new Coordinate(getHead().y.coord/num),
                new Coordinate(getHead().z.coord/num)));
    };
}
