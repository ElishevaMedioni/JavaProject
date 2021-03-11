package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Sphere {
    private Point3D center;
    private double radius;

    public Point3D getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    public Vector getNormal(Point3D point3D)
    {
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
