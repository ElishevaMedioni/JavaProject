package unittests;

import geometries.Cylinder;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class CylinderTest extends Object {

    @Test
    /**
     *
     */
    void getNormal() {
        Cylinder cylinder=new Cylinder(new Ray(new Point3D(1,0,0),new Vector(1,0,0)),5,6);

        // ============ Equivalence Partitions Tests ==============

        //case test when the point is on the tube
        assertEquals("Bad normal to cylinder",new Vector(1,0,0),cylinder.getNormal(new Point3D(0,3,4)));

        //case test when the point is on the left side
        assertEquals("Bad normal to cylinder",new Vector(1,0,0),cylinder.getNormal(new Point3D(-3,1,0)));

        // case test when the point is on the right side
        assertEquals("Bad normal to cylinder",new Vector(1,0,0),cylinder.getNormal(new Point3D(3,1,0)));



        // =============== Boundary Values Tests ==================

        //case test when the point is on the center of the left side
        assertEquals("Bad normal to cylinder",new Vector(1,0,0),cylinder.getNormal(new Point3D(-3,0,0)));

        //case test when the point is on the center of the left side
        assertEquals("Bad normal to cylinder",new Vector(1,0,0),cylinder.getNormal(new Point3D(3,0,0)));

    }
}