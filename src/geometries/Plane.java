package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

import java.awt.*;
import java.util.List;

public class Plane {
    private Point3D q0;

    public Point3D getQ0() {
        return q0;
    }

    private Vector normal;

    /**
     * Plane constructor, also calculate the normal of the plane
     * @param p1
     * @param p2
     * @param p3
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
        Vector vec1 = (new Vector(p1)).subtract(new Vector(p2));
        Vector vec2 = (new Vector(p1)).subtract(new Vector(p3));
        Vector vecN = vec1.crossProduct(vec2);
        normal = vecN.normalize();
    }

    public Plane(Point3D point3D, Vector myNormal)
    {
        normal = myNormal;
        q0 = point3D;
    }

    public Vector getNormal()
    {
        return normal;
    }

    public Vector getNormal(Point3D point3D){
        return normal;
    }

    public List<Point3D> findIntersections(Ray ray){
        if(q0.equals(ray.getP0())||isZero(normal.dotProduct(ray.getDir()))||
             isZero(normal.dotProduct(q0.subtract(ray.getP0()))))
            return null;
        double t=(normal.dotProduct(q0.subtract(ray.getP0())))/(normal.dotProduct(ray.getDir()));
        if(t<=0)
            return null;
        Point3D p=ray.getP0().add(ray.getDir().scale(t));
        List<Point3D> list=List.of(p);
        return list;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "q0=" + q0 +
                ", normal=" + normal +
                '}';
    }
}
