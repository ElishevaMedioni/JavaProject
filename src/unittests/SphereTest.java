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
        Sphere sp= new Sphere(new Point3D(0,0,0),5);
        assertEquals("Bad normal to sphere", new Vector(0, 3/5, 4/5), sp.getNormal(new Point3D(0, 3, 4)));

    }
}