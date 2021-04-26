package unittests;

import geometries.Tube;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class TubeTest extends Object {

    @Test
    void getNormal() {
        Tube tu = new Tube(new Ray(new Point3D(1, 0, 0), new Vector(1, 0, 0)), 5);
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test that the normal of the tube is calculated properly
        assertEquals("Bad normal to tube", new Vector(0, 3/5.0, 4/5.0), tu.getNormal(new Point3D(0, 3, 4)));


        // =============== Boundary Values Tests ==================
        //TC11: case test when the point is in front of the head of ray
        assertEquals("Bad normal to tube", new Vector(0,-3/5.0,-4/5.0), tu.getNormal(new Point3D(1,-3,-4)));
    }
/**
    @Test
    void testFindIntersections() {
        Tube tu = new Tube(new Ray(new Point3D(0,0,-4),new Vector(0,0,8)), 5);
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test in case there are 2 intersection
        List<Point3D> result = tu.findIntersections(new Ray(new Point3D(-8,0,0), new Vector(13,0,0)));
        assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0).getY() > result.get(1).getY())
            result = List.of(result.get(1), result.get(0));
        assertEquals("2 intersections", List.of(new Point3D(5,0,0), new Point3D(-5,0,0)), result);

    }
*/

}