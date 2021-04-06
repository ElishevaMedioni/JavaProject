package geometries;

import primitives.Point3D;
import primitives.Vector;

import java.awt.*;

public class Sphere {
    private Point3D center;
    private double radius;

    public Point3D getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    public Vector getNormal(Point3D point3D) {
        Vector vector = point3D.subtract(center);
        return vector.normalize();
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
