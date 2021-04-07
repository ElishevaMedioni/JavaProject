package unittests;

import geometries.Tube;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class TubeTest extends Object {

    @Test
    void getNormal() {
        Tube tu = new Tube(new Ray(new Point3D(1, 0, 0), new Vector(1, 0, 0)), 5);
        // ============ Equivalence Partitions Tests ==============
        double x=3/5.0, y=4/5.0;
        assertEquals("Bad normal to tube", new Vector(0, 3/5.0, 4/5.0), tu.getNormal(new Point3D(0, 3, 4)));


        // =============== Boundary Values Tests ==================
        //case test when the point is in front of the head of ray
        assertEquals("Bad normal to tube", new Vector(0,-3/5.0,-4/5.0), tu.getNormal(new Point3D(1,-3,-4)));
    }
}