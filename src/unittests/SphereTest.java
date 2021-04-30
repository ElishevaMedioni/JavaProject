package unittests;

import geometries.Plane;
import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.awt.*;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class SphereTest extends Object {

    @Test
    void tesGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test that the normal of the sphere is calculated properly
        Sphere sp= new Sphere(Point3D.ZERO,5);
        assertEquals("Bad normal to sphere", new Vector(0, 3/5.0, 4/5.0), sp.getNormal(new Point3D(0, 3, 4)));

    }
    @Test
    void testFindIntersections(){
        Sphere sphere = new Sphere(new Point3D(1, 0, 0),1d);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),
                        new Vector(1, 1, 0))),"Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
        List<Point3D> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals("Ray crosses sphere", List.of(p1, p2), result);


        Sphere sphere1=new Sphere(new Point3D(3,0,0), 5d);

        // TC03: Ray starts inside the sphere (1 point)
        Point3D p= new Point3D(8, 0, 0);

        List<Point3D> result1=sphere1.findIntersections(new Ray(new Point3D(6 , 0 , 0), new Vector(8 , 0 , 0)));
        assertEquals("Wrong number of points", 1, result1.size());
        assertEquals("Wrong intersection", List.of(p), result1);

        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere1.findIntersections(new Ray(new Point3D(0,6,0), new Vector(0,2,0))),
                "Ray starts after the sphere");


        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        List<Point3D> result2=sphere1.findIntersections(new Ray(new Point3D(3,5,0),new Vector(0,-12,0)));
        assertEquals("Wrong number of points", 1, result2.size());
        assertEquals("Ray starts at sphere and goes inside", List.of(new Point3D(3,-5,0)), result2);

        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere1.findIntersections(new Ray(new Point3D(0,0,5), new Vector(0,-4,3))),
                "Ray starts at sphere and goes outside");


        // **** Group: Ray's line goes through the center

        // TC13: Ray starts before the sphere (2 points)
        List<Point3D> result3=sphere1.findIntersections(new Ray(new Point3D(3,7,0), new Vector(0,-13,0)));
        assertEquals("Wrong number of points", 2, result3.size());
        if (result3.get(0).getY() > result3.get(1).getY())
            result3 = List.of(result3.get(1), result3.get(0));
        assertEquals("Ray starts before the sphere", List.of(new Point3D(3,-5,0), new Point3D(3,5,0)), result3);

        // TC14: Ray starts at sphere and goes inside (1 points)
        List<Point3D> result4=sphere1.findIntersections(new Ray(new Point3D(8,0,0), new Vector(-10,0,0)));
        assertEquals("Wrong number of points", 1, result4.size());
        assertEquals("Ray starts at sphere and goes inside", List.of(new Point3D(-2,0,0)), result4);

        // TC15: Ray starts inside (1 points)
        List<Point3D> result5=sphere1.findIntersections(new Ray(new Point3D(6,0,0), new Vector(4,0,0)));
        assertEquals("Wrong number of points", 1, result5.size());
        assertEquals("Ray starts inside", List.of(new Point3D(8,0,0)), result5);

        // TC16: Ray starts at the center (1 points)
        List<Point3D> result6=sphere1.findIntersections(new Ray(new Point3D(3,0,0), new Vector(0,5,0)));
        assertEquals("Wrong number of points", 1, result6.size());
        assertEquals("Ray starts at the center", List.of(new Point3D(3,5,0)), result6);

        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere1.findIntersections(new Ray(new Point3D(3,-5,0), new Vector(0,-3,0))),
                "Ray starts at sphere and goes outside");

        // TC18: Ray starts after sphere (0 points)
        assertNull(sphere1.findIntersections(new Ray(new Point3D(3,-6,0), new Vector(0,-1,0))),
                "Ray starts after sphere");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertNull(sphere1.findIntersections(new Ray(new Point3D(0,5,-3), new Vector(0,0,3))),
                "Ray starts before the tangent point");

        // TC20: Ray starts at the tangent point
        assertNull(sphere1.findIntersections(new Ray(new Point3D(0,5,0), new Vector(0,0,3))),
                "Ray starts at the tangent point");

        // TC21: Ray starts after the tangent point
        assertNull(sphere1.findIntersections(new Ray(new Point3D(0,5,3), new Vector(0,0,3))),
                "Ray starts after the tangent point");

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull(sphere1.findIntersections(new Ray(new Point3D(0,6,0), new Vector(0,0,3))),
                "Ray's line is outside, ray is orthogonal to ray start to sphere's center line");

    }
}