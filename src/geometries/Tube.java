package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Tube implements Geometry{
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
        if(t==0.0)
            o=axisRay.getP0();
        else
            o=axisRay.getPoint(t);
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

    //we try to do the findIntersections method for the bonus but it's not working
    public List<Point3D> findIntersections(Ray ray) {
        double disc = 4*(axisRay.getP0().getX()*axisRay.getDir().getHead().getX()+
                axisRay.getP0().getY()*axisRay.getDir().getHead().getY())
                *(axisRay.getP0().getX()*axisRay.getDir().getHead().getX()+
                axisRay.getP0().getY()*axisRay.getDir().getHead().getY())
                -4*(axisRay.getDir().getHead().getX()*axisRay.getDir().getHead().getX()
        + axisRay.getDir().getHead().getY()*axisRay.getDir().getHead().getY())
                *(axisRay.getP0().getX()*axisRay.getP0().getX()+
                axisRay.getP0().getY()*axisRay.getP0().getY()-getRadius());
        if (disc < 0)
            return null;
        double denominator = 2*(axisRay.getDir().getHead().getX()*axisRay.getDir().getHead().getX()
                + axisRay.getDir().getHead().getY()*axisRay.getDir().getHead().getY());
        if (denominator < 0 )
            return null;
        double t1 = (-2*(axisRay.getP0().getX()*axisRay.getDir().getHead().getX()
        + axisRay.getP0().getY()* axisRay.getDir().getHead().getY())+ Math.sqrt(disc))/denominator;

        double t2 = (-2*(axisRay.getP0().getX()*axisRay.getDir().getHead().getX()
                + axisRay.getP0().getY()* axisRay.getDir().getHead().getY())- Math.sqrt(disc))/denominator;

        double x1 = axisRay.getP0().getX()+ t1* axisRay.getDir().getHead().getX();
        double y1 = axisRay.getP0().getY()+ t1* axisRay.getDir().getHead().getY();
        double z1 = axisRay.getP0().getZ()+ t1* axisRay.getDir().getHead().getZ();
        Point3D p1 = new Point3D(x1,y1,z1);

        double x2 = axisRay.getP0().getX()+ t2* axisRay.getDir().getHead().getX();
        double y2 = axisRay.getP0().getY()+ t2* axisRay.getDir().getHead().getY();
        double z2 = axisRay.getP0().getZ()+ t2* axisRay.getDir().getHead().getZ();
        Point3D p2 = new Point3D(x2,y2,z2);

        return List.of(p1,p2);

    }
}
