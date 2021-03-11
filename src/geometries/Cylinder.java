package geometries;
import primitives.Point3D;
import primitives.Vector;
import geometries.Geometry;

public class Cylinder extends Tube{
    private double height;

    public double getHeight() {
        return height;
    }

    public Vector getNormal(Point3D point3D)
    {
        return null;
    }
}
