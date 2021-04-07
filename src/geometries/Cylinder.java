package geometries;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import geometries.Geometry;

public class Cylinder extends Tube{
    private double height;

    public double getHeight() {
        return height;
    }

    public Vector getNormal(Point3D point3D){
        return getAxisRay().getDir();
    }

    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                '}';
    }
}
