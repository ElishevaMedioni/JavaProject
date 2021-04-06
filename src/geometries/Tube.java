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

    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    private double radius;

    public Vector getNormal(Point3D point3D){
        double t=axisRay.getDir().dotProduct(point3D.subtract(axisRay.getP0()));
        Point3D o=axisRay.getP0().add(axisRay.getDir().scale(t));
        Vector vector = point3D.subtract(o);
        return vector.normalize();
    }

    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }
}
