package unittests;

import org.junit.jupiter.api.Test;
import primitives.Vector;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;
import static org.junit.Assert.*;

class VectorTests extends Object {

    Vector v = new Vector(1, 2, 3);
    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);

    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        //TA01: Test that the add works properly
        Vector vAdd=v1.add(v2), vEx=new Vector(-1,-2,-3);
        assertEquals("ERROR: add() wrong value", vAdd, vEx);
    }

    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        //TS01: Test that the subtract works properly
        Vector vSub=v1.substract(v2), vEx=new Vector(3,6,9);
        assertEquals("ERROR: subtract() wrong value", vSub, vEx);
    }

    @Test
    void scale() {
        // ============ Equivalence Partitions Tests ==============
        //TS01: Test that the scale works properly
        Vector vScale=v1.scale(2), vEx=new Vector(2,4,6);
        assertEquals("ERROR: scale() wrong value", vScale, vEx);
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v2.length(), vr.length(), 0.00001);

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v2)));

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-product of co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),"crossProduct() for parallel vectors does not throw an exception");
        // try {
        //     v1.crossProduct(v2);
        //     fail("crossProduct() for parallel vectors does not throw an exception");
        // } catch (Exception e) {}
    }



    @Test
    void testDotProduct() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test that the dot product of orthogonal vectors is zero
        assertTrue("ERROR: dotProduct() for orthogonal vectors is not zero",isZero(v1.dotProduct(v3)));
        //TC02: Test that the dot product works properly
        assertTrue("ERROR: dotProduct() wrong value",isZero(v1.dotProduct(v2) + 28));
    }

    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test that the lengthSquared works properly
        assertTrue("ERROR: lengthSquared() wrong value",isZero(v1.lengthSquared() - 14));
    }

    @Test
    void testLength() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test that the length works properly
        assertTrue("ERROR: length() wrong value",isZero(new Vector(0, 3, 4).length() - 5));

    }

    @Test
    void testNormalize() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test that the normalize function works properly
        // test that it doesn't create a new vector
        // test that the vector is a unit vector
        Vector vCopy = new Vector(v.getHead());
        Vector vCopyNormalize = vCopy.normalize();
        assertEquals("ERROR: normalize() function creates a new vector", vCopy, vCopyNormalize);
        assertTrue("ERROR: normalize() result is not a unit vector",isZero(vCopyNormalize.length() - 1));
    }

    @Test
    void testNormalized() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test that the normalized function works properly
        // test that the function create a new vector
        Vector u = v.normalized();
        assertNotEquals("ERROR: normalized() function does not create a new vector",u,v);

    }
}