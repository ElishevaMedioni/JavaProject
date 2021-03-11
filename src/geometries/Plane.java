package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Plane {
    private Point3D q0;
    private Vector normal;

    public Plane(Point3D p1,Point3D p2,Point3D p3)
    {
        q0 = p1;
        Vector vec1 = (new Vector(p1)).subtract(new Vector(p2));
        Vector vec2 = (new Vector(p1)).subtract(new Vector(p3));
        Vector vecN = vec1.crossProduct(vec2); //on va attribuer a normal a la prochaine etape
        normal = null;
    }

    public Plane(Point3D point3D, Vector myNormal)
    {
        normal = myNormal;
        q0 = point3D;
    }

    public Vector getNormal()
    {
        return null;
    }
}
