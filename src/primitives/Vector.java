package primitives;
import java.lang.Math.*;
public class Vector
{
    Point3D head;
    public Point3D getHead() {
        return head;
    }

    public void setHead(Point3D head) {
        this.head = head;
    }
    public Vector(Point3D head) {
        this.head = head;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Vector)) return false;
        if (!super.equals(object)) return false;
        Vector vector = (Vector) object;
        return java.util.Objects.equals(head, vector.head);
    }

    public Vector add(Vector vector){
        return new Vector(new Point3D(new Coordinate(getHead().getX().coord+vector.getHead().getX().coord),
                new Coordinate(getHead().getY().coord+vector.getHead().getY().coord),
                new Coordinate(getHead().getZ().coord+vector.getHead().getZ().coord)));
    };
    public Vector substract(Vector vector){
        return new Vector(new Point3D(new Coordinate(getHead().getX().coord-vector.getHead().getX().coord),
                new Coordinate(getHead().getY().coord-vector.getHead().getY().coord),
                new Coordinate(getHead().getZ().coord-vector.getHead().getZ().coord)));
    };
    public Vector scale(double doub){
        return new Vector(new Point3D(new Coordinate(getHead().getX().coord*doub),
                new Coordinate(getHead().getY().coord*doub),
                new Coordinate(getHead().getZ().coord*doub)));
    };
    public Vector crossProduct(Vector vector){
       return new Vector(new Point3D(
               new Coordinate((getHead().getY().coord*vector.getHead().getZ().coord-
                       getHead().getZ().coord*vector.getHead().getY().coord)),
               new Coordinate((getHead().getZ().coord*vector.getHead().getX().coord-
                       getHead().getX().coord*vector.getHead().getZ().coord)),
               new Coordinate((getHead().getX().coord*vector.getHead().getY().coord-
                       getHead().getY().coord*vector.getHead().getX().coord))));
    };
    public double dotProduct(Vector vector){
        return getHead().getX().coord*vector.getHead().getX().coord+
                getHead().getY().coord*vector.getHead().getY().coord+
                getHead().getZ().coord*vector.getHead().getZ().coord;
    };
    public double lengthSquared(){
        return getHead().getX().coord*getHead().getX().coord+getHead().getY().coord*getHead().getY().coord+
                getHead().getZ().coord*getHead().getZ().coord;
    };
    public double length(){
        return Math.sqrt(lengthSquared());
    };
    public Vector normalize(){
        double num=length();
        head=new Point3D(new Coordinate(getHead().getX().coord/num),new Coordinate(getHead().getY().coord/num),
                new Coordinate(getHead().getZ().coord/num));
        return new Vector(head);
    };
    public Vector normalized(){
        double num=length();
        return new Vector(new Point3D(new Coordinate(getHead().getX().coord/num),new Coordinate(getHead().getY().coord/num),
                new Coordinate(getHead().getZ().coord/num)));
    };
}
