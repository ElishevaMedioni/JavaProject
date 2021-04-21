package unittests;

import geometries.Geometries;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point3D;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest extends Object {

    @Test
    void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============
        Geometries geos=new Geometries();

        //TC01:
        geos.add(new Triangle(new Point3D(0,0,3),new Point3D(-3,0,0),new Point3D(3,0,0)));
        //geos.add(new Sphere(new Point3D(0,-4,0),1.0));

        // =============== Boundary Values Tests ==================

    }
}