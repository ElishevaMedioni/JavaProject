package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

/**
 * The tube is a three-dimensional solid infinite
 */
public class Tube extends Geometry{
    //FIELDS
    private Ray axisRay;
    private double radius;

    //CONSTRUCTOR
    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    //GETTER
    public Ray getAxisRay() {
        return axisRay;
    }

    public double getRadius() {
        return radius;
    }

   //METHODS

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
        if(isZero(t))
            o=axisRay.getP0();
        else
            o=axisRay.getPoint(t);
        Vector vector = point3D.subtract(o);
        return vector.normalize();
    }

    public List<GeoPoint> findGeoIntersections(Ray ray){return null;}

    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }
}
