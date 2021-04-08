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

    /**
     * getNormal for the finite Cylinder
     * The normal at a base will be simply equal to central ray's direction vector 𝑣
     * @param point3D
     * @return Vector
     */
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
