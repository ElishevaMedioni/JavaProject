package unittests;

import geometries.Plane;
import geometries.Polygon;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class PlaneTest extends Object {

    @Test
    void getNormal() {
        Plane pl= new Plane(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);

        // ============ Equivalence Partitions Tests ==============
        //Test that the normal of the plane is calculated properly
        assertEquals("Bad normal to plane", new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)));
    }
}