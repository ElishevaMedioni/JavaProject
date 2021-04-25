package unittests;

import geometries.Plane;
import geometries.Polygon;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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

    @Test
    void testFindIntersections() {

        Plane plane= new Plane(new Point3D(0,0,3),new Point3D(-3,0,0),new Point3D(3,0,0));
        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray intersects the plane (1 point)
        List<Point3D> result1=plane.findIntersections(new Ray(new Point3D(0,2,0), new Vector(0,-2,1)));
        assertEquals("Wrong number of points", 1, result1.size());
        assertEquals("Wrong intersection", List.of(new Point3D(0,0,1)), result1);

        // TC02: Ray does not intersect the plane (0 points)
        //assertNull(plane.findIntersections(new Ray(new Point3D(0,2,0), new Vector(0,-2,4))),
          //      "Ray does not intersect the plane");
        assertNull(plane.findIntersections(new Ray(new Point3D(0,2,0), new Vector(0,4,0))),
                "Ray does not intersect the plane");

        // =============== Boundary Values Tests ==================

        // TC03: Ray is parallel to the plane and included in it (0 points)
        assertNull(plane.findIntersections(new Ray(new Point3D(0,0,1), new Vector(0,0,2))),
                "Ray is parallel to the plane and included in it");

        // TC04: Ray is parallel to the plane and is not included in it (0 points)
        assertNull(plane.findIntersections(new Ray(new Point3D(0,1,1), new Vector(0,0,2))),
                "Ray is parallel to the plane and is not included in it");

        // TC05: Ray is orthogonal to the plane and before it (1 points)
        List<Point3D> result2=plane.findIntersections(new Ray(new Point3D(0,-2,1), new Vector(0,3,0)));
        assertEquals("Wrong number of points", 1, result2.size());
        assertEquals("Ray is orthogonal to the plane and before it", List.of(new Point3D(0,0,1)), result2);

        // TC06: Ray is orthogonal to the plane and in it (0 points)
        assertNull(plane.findIntersections(new Ray(new Point3D(0,0,1), new Vector(0,3,0))),
                " Ray is orthogonal to the plane and in it");

        // TC07: Ray is orthogonal to the plane and after it (0 points)
        assertNull(plane.findIntersections(new Ray(new Point3D(0,1,1), new Vector(0,2,0))),
                " Ray is orthogonal to the plane and after it ");

        // TC08:  Ray is neither orthogonal nor parallel to and begins at the plane (0 points)
        assertNull(plane.findIntersections(new Ray(new Point3D(0,0,1), new Vector(0,2,2))),
                " Ray is neither orthogonal nor parallel to and begins at the plane ");

        // TC09: Ray is neither orthogonal nor parallel to the plane and begins in the same point which appears as reference point in the plane (0 points)
        assertNull(plane.findIntersections(new Ray(new Point3D(3,0,0), new Vector(-3,2,3))),
                " Ray is neither orthogonal nor parallel to the plane and begins in the same point which appears as reference point in the plane ");

    }
}