package unittests;

import geometries.Plane;
import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class SphereTest extends Object {

    @Test
    void getNormal() {
        Sphere sp= new Sphere(Point3D.ZERO,5);
        assertEquals("Bad normal to sphere", new Vector(0, 3/5.0, 4/5.0), sp.getNormal(new Point3D(0, 3, 4)));

    }
}