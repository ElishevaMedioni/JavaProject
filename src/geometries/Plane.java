package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

import java.util.List;

/**
 * a plane is a flat, two-dimensional surface that extends infinitely far.
 */
public class Plane extends Geometry{
    //fields
    private Point3D q0;
    private Vector normal;


    /**
     * Plane constructor, also calculate the normal of the plane
     * @param p1 value of p1
     * @param p2 value of p2
     * @param p3 value of p3
     */
    public Plane(Point3D p1,Point3D p2,Point3D p3)
    {
        //check if two points are the same
        if(p1==p2||p1==p3||p2==p3)
            throw new IllegalArgumentException("The points can't be the same");
        //check if the three points are on the same line
        if(p1.checkLinearDependent(p2)&&p1.checkLinearDependent(p3))
            throw new IllegalArgumentException("The three point can't be on the same line");

        //calculate the normal of the plane:
        q0 = p1;
        //calculate two vectors that are on the plane
        Vector vec1 = (new Vector(p1)).substract(new Vector(p2));
        Vector vec2 = (new Vector(p1)).substract(new Vector(p3));
        Vector vecN = vec1.crossProduct(vec2);
        normal = vecN.normalize();
    }

    public Plane(Point3D point3D, Vector myNormal)
    {
        normal = myNormal;
        q0 = point3D;
    }

    //GETTER
    public Point3D getQ0() {
        return q0;
    }

    public Vector getNormal()
    {
        return normal;
    }

    public Vector getNormal(Point3D point3D){
        return normal;
    }

    //METHODS
    public List<GeoPoint> findGeoIntersections(Ray ray){
        List<GeoPoint> intersections;
        double x1=normal.dotProduct(ray.getDir()),
                x2=normal.dotProduct(q0.subtract(ray.getP0()));
        // 1- checking if the p0 (Point3D) of the ray is equal to q0 (Point3D) of the plane
        // 2- checking if the  ray is orthogonal to the plane
        // 3- checking if the ray is parallel to the plane
        if(q0.equals(ray.getP0())||isZero(x1)|| isZero(x2))
            return null;
        double t=x2/x1;
        if(t<=0)
            return null;
        Point3D p=ray.getPoint(t);
        intersections=List.of(new GeoPoint(this,p));
        return intersections;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "q0=" + q0 +
                ", normal=" + normal +
                '}';
    }
}
