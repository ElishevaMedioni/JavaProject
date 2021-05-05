package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * we create the interface Geometry because all of the geometry use the method getNormal
 * extends Intersectable because each geometry use the method findIntersections situate in interface Intersectablle
 */
public interface Geometry extends Intersectable{
    public Vector getNormal(Point3D p);

}
