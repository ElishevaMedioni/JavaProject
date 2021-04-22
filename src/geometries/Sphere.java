package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.awt.*;
import java.util.List;

public class Sphere {
    private Point3D center;
    private double radius;

    public Point3D getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    /**
     * getNormal for the sphere: n = normalize(P - O)
     * @param point3D
     * @return Vector
     */
    public Vector getNormal(Point3D point3D) {
        Vector vector = point3D.subtract(center);
        return vector.normalize();
    }

    public List<Point3D> findIntersections(Ray ray){
        List<Point3D> list;
        Vector u=getCenter().subtract(ray.getP0());
        double tM=ray.getDir().dotProduct(u);
        double d= Math.sqrt(u.lengthSquared()-tM*tM);
        if(d>=radius)
            return null;
        double tH=Math.sqrt(radius*radius-d*d);
        double t1=tM+tH,t2=tM-tH;
        Point3D p1=ray.getP0().add(ray.getDir().scale(t1));
        Point3D p2=ray.getP0().add(ray.getDir().scale(t1));
        if(t1>0 && t2<0) {
            list=List.of(p1);
            return list;
        }
        if(t2>0 && t1<0) {
            list=List.of(p2);
            return list;
        }
        list=List.of(p1,p2);
        return list;
    }

    public Sphere(Point3D point3D, double num)
    {
        center=point3D;
        radius=num;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }
}
