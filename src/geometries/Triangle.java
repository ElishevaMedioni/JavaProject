package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

import static primitives.Util.*;

/**
 * A triangle is a polygon with three edges and three vertices
 */
public class Triangle extends Polygon {

    /**
     * CONSTRUCTOR - call the polygon constructor parameter
     * @param p1 first value of point3D
     * @param p2 second value of point3D
     * @param p3 third value of point3D
     */
    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1, p2, p3);
    }

    //METHODS
    public List<Point3D> findIntersections(Ray ray) {
        /**
         * Condition 1 - check if the q0 (point3D of the plane) isn't equal to the p0 (Point3D of the ray)
         * Condition 2 - checking it the denominator of t isn't equal to zero
         * Condition 3 - checking it the numerator of t isn't equal to zero
         */
        if (plane.getQ0().equals(ray.getP0()) || isZero(plane.getNormal().dotProduct(ray.getDir())) ||
                isZero(plane.getNormal().dotProduct(plane.getQ0().subtract(ray.getP0()))))
            return null;
        double t = (plane.getNormal().dotProduct(plane.getQ0().subtract(ray.getP0()))) / (plane.getNormal().dotProduct(ray.getDir()));
        if (t < 0)
            return null;
        Point3D p =ray.getPoint(t);
        //Calculating the vector of the vertices
        Vector v1 = vertices.get(0).subtract(ray.getP0()), v2 = vertices.get(1).subtract(ray.getP0()),
                v3 = vertices.get(2).subtract(ray.getP0());
        //Calculating three normal of the vector
        Vector N1 = (v1.crossProduct(v2)).normalize(), N2 = (v2.crossProduct(v3)).normalize(),
                N3 = (v3.crossProduct(v1)).normalize();
        double d1 = ray.getDir().dotProduct(N1), d2 = ray.getDir().dotProduct(N2), d3 = ray.getDir().dotProduct(N3);
        //Checking if the ray is parallel to the triangle -> No intersection
        if (isZero(alignZero(d1)) || isZero(alignZero(d2)) || isZero(alignZero(d3)))
            return null;
        List<Point3D> list = List.of(p);
        //Checking if three of the dot product have the same sign to check if the intersection is inside the triangle
        if(checkSign(d1,d2)&&checkSign(d1,d3))
            return list;
        return null;
    }

}
