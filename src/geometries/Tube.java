package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Tube {
    private Ray axisRay;

    public Ray getAxisRay() {
        return axisRay;
    }

    public double getRadius() {
        return radius;
    }

    private double radius;

    public Vector getNormal(Point3D point3D)
    {
        return null;
    }
}
