package unittests;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest extends Object {

    @Test
    void testFindIntersections() {

        Geometries geos=new Geometries();
        geos.add(new Triangle(new Point3D(0,0,4),new Point3D(-3,0,0),new Point3D(3,0,0)));
        geos.add(new Triangle(new Point3D(0,3,3),new Point3D(-3,3,0),new Point3D(3,3,0)));
        geos.add(new Sphere(new Point3D(0,-4,0),1d));

        // ============ Equivalence Partitions Tests ==============
        //TC01:Several shapes but not all are cut
        List<Point3D> result=geos.findIntersections(new Ray(new Point3D(0,5,2), new Vector(0,-5,0)));
        assertEquals("Wrong number of points", 2, result.size());
        assertEquals("Several shapes but not all are cut", List.of(new Point3D(0,0,2),
                new Point3D(0,3,2)), result);

        // =============== Boundary Values Tests ==================
        //TC02:Empty list
        Geometries geos1 = new Geometries();
        assertNull(geos1.findIntersections(new Ray(new Point3D(0,5,2), new Vector(0,-5,0))),
                "Empty list");

        //TC03:No shapes intersect
        assertNull(geos1.findIntersections(new Ray(new Point3D(0,5,5), new Vector(0,-5,0))),
                "No shapes intersect");

        //TC04: Only one shape intersect
        List<Point3D> result1=geos.findIntersections(new Ray(new Point3D(-3,-4,0), new Vector(6,0,0)));
        assertEquals("Wrong number of points", 2, result1.size());
        if (result1.get(0).getX() > result1.get(1).getX())
            result1 = List.of(result1.get(1), result1.get(0));
        assertEquals("Only one shape intersect", List.of(new Point3D(-1,-4,0),
                new Point3D(1,-4,0)), result1);

        Geometries geos2=new Geometries();
        geos2.add(new Triangle(new Point3D(0,0,4),new Point3D(-3,0,-1),new Point3D(3,0,-1)));
        geos2.add(new Triangle(new Point3D(0,3,3),new Point3D(-3,3,-1),new Point3D(3,3,-1)));
        geos2.add(new Sphere(new Point3D(0,-4,0),1d));

        //TC05:Every shapes intersect
        List<Point3D> result3=geos2.findIntersections(new Ray(new Point3D(0,4,0), new Vector(0,-10,0)));
        assertEquals("Wrong number of points", 4, result3.size());
        if (result3.get(2).getY() > result3.get(3).getY()) {
            Point3D temp=result3.get(2);
            result3.set(2, result3.get(3));
        }
        assertEquals("Every shapes intersect", List.of(Point3D.ZERO, new Point3D(0,3,0),
                new Point3D(0,-5,0), new Point3D(0,-3,0)), result3);
    }
}