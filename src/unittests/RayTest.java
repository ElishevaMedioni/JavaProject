package unittests;

import geometries.Geometries;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class RayTest extends Object {

    @Test
    void findClosestPoint() {
        Sphere sp=new Sphere(new Point3D(4,0,0), 4);
        Triangle tr1=new Triangle(new Point3D(-5,0,4), new Point3D(-5,-3,-1),
                new Point3D(-5,3,-1));
        Geometries geo=new Geometries(sp, tr1);
        Point3D p1=new Point3D(8,0,0), p2=new Point3D(-5,0,0);
        Ray ray1=new Ray(new Point3D(10,0,0), new Vector(-18,0,0));
        // =============== Boundary Values Tests ==================
        //TC01: the closest intersection is the first Point3D of the list
        List<Point3D> result1= geo.findIntersections(ray1);
        assertEquals("the closest intersection is the first Point3D of the list",
                p1, ray1.findClosestPoint(result1));

        Ray ray2=new Ray(new Point3D(-8,0,0), new Vector(18,0,0));
        //TC02: the closest intersection is the last Point3D of the list
        List<Point3D> result2= geo.findIntersections(ray2);
        assertEquals("the closest intersection is the last Point3D of the list",
                p2, ray2.findClosestPoint(result2));

        Ray ray3=new Ray(new Point3D(-8,0,7), new Vector(18,0,0));
        //TC03: the list of the intersections is null
        assertNull(ray3.findClosestPoint(geo.findIntersections(ray3)),"the list of the intersections is null");

        Triangle tr2=new Triangle(new Point3D(-3,0,10),new Point3D(-3,-3,1), new Point3D(-3,3,1));

        // ============ Equivalence Partitions Tests ==============
        //TC04: the closest intersection is the middle Point3D of the list
        Geometries geo1=new Geometries(tr2,tr1,sp);
        List<Point3D> result3=geo1.findIntersections(ray2);
        assertEquals("the closest intersection is the middle Point3D of the list",
                p2, ray2.findClosestPoint(result3));
    }
}