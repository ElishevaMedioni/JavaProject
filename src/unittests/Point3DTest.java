package unittests;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static primitives.Util.isZero;
import static org.junit.Assert.*;

class Point3DTest extends Object {

    Point3D p1 = new Point3D(1, 2, 3);
    Point3D p2= new Point3D(1, 5,7);

    @Test
    void subtract() {
        Vector vector=new Point3D(2, 3, 4).subtract(p1);

        // ============ Equivalence Partitions Tests ==============
        //Test that the substraction of two vectors is calculated properly
        assertEquals("ERROR: Point - Point does not work correctly",vector, new Vector(1, 1, 1));
    }

    @Test
    void add() {
        Point3D point3D=p1.add(new Vector(-1, -2, -3));

        // ============ Equivalence Partitions Tests ==============
        //Test that the addition of two vectors is calculated properly
        assertEquals("ERROR: Point + Vector does not work correctly",point3D, Point3D.ZERO);
    }

    @Test
    void distanceSquared() {

        // Test that the distance squared of two vectors is calculated properly
        assertTrue("ERROR: distanceSquared() wrong value", isZero(p2.distanceSquared(p1)-25));
    }

    @Test
    void distance() {

        // ============ Equivalence Partitions Tests ==============
        //Test that the distance of two vectors is calculated properly
        assertTrue("ERROR: distance() wrong value", isZero(p2.distance(p1)-5));
    }
}