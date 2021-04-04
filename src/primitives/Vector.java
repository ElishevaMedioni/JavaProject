package primitives;
//import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;

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
        Point3D point3D = new Point3D(x,y,z);
        if(point3D.equals(Point3D.ZERO))
            throw new IllegalArgumentException("ERROR, The vector is ZERO vector");
        this.head = point3D;
    }

    public Vector(Point3D point3D) {

        this.head = point3D;
    }

    public Vector(Coordinate x, Coordinate y, Coordinate z) {
        Point3D point3D = new Point3D(x.coord,y.coord,z.coord);
        if(point3D.equals(Point3D.ZERO))
            throw new IllegalArgumentException("ERROR, The vector is ZERO vector");
        this.head = point3D;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Vector)) return false;
        Vector vector = (Vector) object;
        return head.equals(vector.getHead());
    }

    public Vector add(Vector vector){
        return new Vector(getHead().x.coord+vector.getHead().x.coord,
                getHead().y.coord+vector.getHead().y.coord,
                getHead().z.coord+vector.getHead().z.coord);
    };
    public Vector subtract(Vector vector){
        return new Vector(getHead().x.coord-vector.getHead().x.coord,
                getHead().y.coord-vector.getHead().y.coord,
                getHead().z.coord-vector.getHead().z.coord);
    };
    public Vector scale(double doub){
        return new Vector(getHead().x.coord*doub,getHead().y.coord*doub,getHead().z.coord*doub);
    };
    public Vector crossProduct(Vector vector){
       return new Vector(
               getHead().y.coord*vector.getHead().z.coord-getHead().z.coord*vector.getHead().y.coord,
               getHead().z.coord*vector.getHead().x.coord- getHead().x.coord*vector.getHead().z.coord,
               getHead().x.coord*vector.getHead().y.coord-getHead().y.coord*vector.getHead().x.coord);
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
        head=new Point3D(getHead().x.coord/num,getHead().y.coord/num,
                getHead().z.coord/num);
        return this;
    };
    public Vector normalized(){
        double num=length();
        return new Vector(getHead().x.coord/num,getHead().y.coord/num,getHead().z.coord/num);
    };
}
