package primitives;
//import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;

import java.lang.Math.*;

/**
 *Class Vector - a vector represent a direction
 */
public class Vector
{
    /**
     *point3D field
     */
    private Point3D head;

    /**
     * @return the point3D value
     */
    public Point3D getHead() {
        return head;
    }

    /**
     * set point3D value
     * @param head point3D value
     */
    public void setHead(Point3D head) {
        this.head = head;
    }

    /**
     * vector constructor receiving three double
     * check if the point3D is not zero if it is throw Exception
     * if not set the value
     * @param x double x value
     * @param y double y value
     * @param z double z value
     */
    public Vector(double x, double y, double z) {
        Point3D point3D = new Point3D(x,y,z);
        if(point3D.equals(Point3D.ZERO))
            throw new IllegalArgumentException("ERROR, The vector is ZERO vector");
        this.head = point3D;
    }

    /**
     * vector constructor receinving a point3D
     * set the point3d value of the vector
     * @param point3D value of the point3D
     */
    public Vector(Point3D point3D) {

        this.head = point3D;
    }

    /**
     * vector constructor receiving three coordinates
     * set the value of the point3D
     * @param x coord x value
     * @param y coord y value
     * @param z coord z value
     */
    public Vector(Coordinate x, Coordinate y, Coordinate z) {
        Point3D point3D = new Point3D(x.coord,y.coord,z.coord);
        if(point3D.equals(Point3D.ZERO))
            throw new IllegalArgumentException("ERROR, The vector is ZERO vector");
        this.head = point3D;
    }

    /**
     * adding a vector to another vector
     * @param vector value vector
     * @return the addition of the vectors
     */
    public Vector add(Vector vector){
        return new Vector(getHead().x.coord+vector.getHead().x.coord,
                getHead().y.coord+vector.getHead().y.coord,
                getHead().z.coord+vector.getHead().z.coord);
    };

    /**
     * substract a vector to another vector
     * @param vector vector value
     * @return the substraction of the vectors
     */
    public Vector subtract(Vector vector){
        return new Vector(getHead().x.coord-vector.getHead().x.coord,
                getHead().y.coord-vector.getHead().y.coord,
                getHead().z.coord-vector.getHead().z.coord);
    };

    /**
     * changing the vector length by a scalar value
     * @param doub scalar value
     * @return the vector after the multiplicaton
     */
    public Vector scale(double doub){
        return new Vector(getHead().x.coord*doub,getHead().y.coord*doub,getHead().z.coord*doub);
    };

    /**
     * calculate the vector perpendicular of two vectors
     * @param vector vector value
     * @return the perpendicular vector
     */
    public Vector crossProduct(Vector vector){
       return new Vector(
               getHead().y.coord*vector.getHead().z.coord-getHead().z.coord*vector.getHead().y.coord,
               getHead().z.coord*vector.getHead().x.coord- getHead().x.coord*vector.getHead().z.coord,
               getHead().x.coord*vector.getHead().y.coord-getHead().y.coord*vector.getHead().x.coord);
    };

    /**
     * the sum of the products of the components
     * @param vector vector value
     * @return return the value of the dot product
     */
    public double dotProduct(Vector vector){
        return getHead().x.coord*vector.getHead().x.coord+
                getHead().y.coord*vector.getHead().y.coord+
                getHead().z.coord*vector.getHead().z.coord;
    };

    /**
     * calculate the length of the vector without sqr
     * @return the length without sqr
     */
    public double lengthSquared(){
        return getHead().x.coord*getHead().x.coord+getHead().y.coord*getHead().y.coord+
                getHead().z.coord*getHead().z.coord;
    };

    /**
     * calculate the length of the vector
     * @return the length of the vector
     */
    public double length(){
        return Math.sqrt(lengthSquared());
    };

    /**
     * changing the length of the vector to 1 on the same direction
     * @return the normalize vector
     */
    public Vector normalize(){
        double num=length();
        head=new Point3D(getHead().x.coord/num,getHead().y.coord/num,
                getHead().z.coord/num);
        return this;
    };

    /**
     * creating a normalize vector
     * @return a new vector normalize
     */
    public Vector normalized(){
        double num=length();
        return new Vector(getHead().x.coord/num,getHead().y.coord/num,getHead().z.coord/num);
    };

    /*************** Admin *****************/
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Vector)) return false;
        Vector vector = (Vector) object;
        return head.equals(vector.getHead());
    }
}
