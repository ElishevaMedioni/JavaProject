package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import primitives.*;

import java.awt.*;
import java.util.List;
import static primitives.Util.*;


import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * A sphere is a geometrical object in three-dimensional space that is the surface of a ball
 * The sphere is defined by a radius and a point3D (the center of the sphere)
 */
public class Sphere extends Geometry {
    //FIELDS
    private Point3D center;
    private double radius;

    //CONSTRUCTOR
    public Sphere(Point3D point3D, double _radius)
    {
        center=point3D;
        radius=_radius;
    }

    //GETTER
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

    //METHODS
    public List<GeoPoint> findGeoIntersections(Ray ray){
        Vector u;

        if(ray.getP0().equals(center)){
            Vector v=ray.getDir().normalize();
            Point3D point3D=center.add(v.scale(radius));
            return List.of(new GeoPoint(this, point3D));
        }

        u=getCenter().subtract(ray.getP0());
        double tM=ray.getDir().dotProduct(u);
        double d= alignZero(Math.sqrt(u.lengthSquared()-tM*tM));
        //if the distance between the center and the ray is bigger than the radius -> No intersections
        if(d>=radius)
            return null;
        double tH=Math.sqrt(radius*radius-d*d);
        double t1=tM+tH,t2=tM-tH;
        //Case: No intersections
        if(t1<=0&&t2<=0)
            return null;
        Point3D p1, p2;
        //Case: were there is one intersection
        if(t1>0&&t2<=0) {
            p1=ray.getPoint(t1);
            //Case: p0 is equal to the intersection -> No intersection
            if(!p1.equals(ray.getP0()))
                return List.of(new GeoPoint(this, p1));
            return null;
        }
        if(t1<=0&&t2>0){
            p2=ray.getPoint(t2);
            //Case: p0 is equal to the intersection -> No intersection
            if(!p2.equals(ray.getP0()))
                return List.of(new GeoPoint(this, p2));
            return null;
        }
        //Case: there is two intersections
        if(t1>0&&t2>0) {
            p2=ray.getPoint(t2);
            p1=ray.getPoint(t1);
            if(p1.equals(ray.getP0()))
                return List.of(new GeoPoint(this, p2));
            if(p2.equals(ray.getP0()))
                return List.of(new GeoPoint(this, p1));
            return List.of(new GeoPoint(this, p1),new GeoPoint(this, p2));
        }
        return null;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }
}
