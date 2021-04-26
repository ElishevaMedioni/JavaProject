package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Triangle extends Polygon {

    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1, p2, p3);
    }

    public List<Point3D> findIntersections(Ray ray) {
        if (plane.getQ0().equals(ray.getP0()) || isZero(plane.getNormal().dotProduct(ray.getDir())) ||
                isZero(plane.getNormal().dotProduct(plane.getQ0().subtract(ray.getP0()))))
            return null;
        double t = (plane.getNormal().dotProduct(plane.getQ0().subtract(ray.getP0()))) / (plane.getNormal().dotProduct(ray.getDir()));
        if (t <= 0)
            return null;
        Point3D p =ray.getPoint(t);
        Vector v1 = vertices.get(0).subtract(ray.getP0()), v2 = vertices.get(1).subtract(ray.getP0()),
                v3 = vertices.get(2).subtract(ray.getP0());
        Vector N1 = (v1.crossProduct(v2)).normalize(), N2 = (v2.crossProduct(v3)).normalize(),
                N3 = (v3.crossProduct(v1)).normalize();
        double d1 = ray.getDir().dotProduct(N1), d2 = ray.getDir().dotProduct(N2), d3 = ray.getDir().dotProduct(N3);
        if (isZero(alignZero(d1)) || isZero(alignZero(d2)) || isZero(alignZero(d3)))
            return null;
        List<Point3D> list = List.of(p);
        if ((d1 > 0 && d2 > 0 && d3 > 0)||(d1 < 0 && d2 < 0 && d3 < 0))
            return list;
        return null;
    }

}
