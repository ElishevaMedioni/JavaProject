package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Tube {
    private Ray axisRay;
    private double radius;

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

    /**
     * Tube (infinite cylinder normal)
     * 𝑡 = 𝑣 ∙ (𝑃 − 𝑃o)
     * 𝑂=𝑃 +𝑡∙𝑣
     * n = normalize(P - O)
     * @param point3D
     * @return Vector
     */
    public Vector getNormal(Point3D point3D){
        double t = axisRay.getDir().dotProduct(point3D.subtract(axisRay.getP0()));
        Point3D o;
        //case when the point is in front of the head of ray
        if(t==0.0)
            o=axisRay.getP0();
        else
            o=axisRay.getP0().add(axisRay.getDir().scale(t));
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
