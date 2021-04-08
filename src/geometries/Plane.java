package geometries;

import primitives.Point3D;
import primitives.Vector;

import java.awt.*;

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

    @Override
    public String toString() {
        return "Plane{" +
                "q0=" + q0 +
                ", normal=" + normal +
                '}';
    }
}
