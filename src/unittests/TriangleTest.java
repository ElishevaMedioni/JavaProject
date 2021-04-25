package unittests;

import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.awt.*;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class TriangleTest extends Object {

    @Test
    void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============
        Triangle triangle=new Triangle(new Point3D(3,0,0), new Point3D(-3,0,0), new Point3D(0,0,3));

        // TC01:Inside polygon/triangle(1 points)
        List<Point3D> result1=triangle.findIntersections(new Ray(new Point3D(0,2,0), new Vector(0,-2,1)));
        assertEquals("Wrong number of points", 1, result1.size());
        assertEquals("Inside polygon/triangle", List.of(new Point3D(0,0,1)), result1);

        // TC02:Outside against edge(0 points)
        assertNull(triangle.findIntersections(new Ray(new Point3D(4,0,3), new Vector(0,2,0))),
                "Outside against edge");

        // TC03:Outside against vertex(0 points)
        assertNull(triangle.findIntersections(new Ray(new Point3D(0,0,4), new Vector(0,2,0))),
                "Outside against vertex");


        // =============== Boundary Values Tests ==================

        // TC04: the ray begins "before" the plane on edge (0 points)
        assertNull(triangle.findIntersections(new Ray(new Point3D(-6,3,2), new Vector(4,-1,-2))),
                "the ray begins before the plane on edge");

        // TC05: the ray begins "before" the plane in vertex (0 points)
        assertNull(triangle.findIntersections(new Ray(new Point3D(-3,-1,0), new Vector(0,3,0))),
                "the ray begins before the plane in vertex");

        // TC06: the ray begins "before" the plane on edge continuation (0 points)
        assertNull(triangle.findIntersections(new Ray(new Point3D(-4,-2,0), new Vector(0,4,0))),
                "the ray begins before the plane on edge continuation");

    }
}