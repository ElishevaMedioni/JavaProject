package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;

/**
 * we create the interface Intersectable because all of the geometry use the method findIntersections
 */
public interface Intersectable {
    /**
     * fnd intersections of the ray with the geometry
     * @param ray value of the ray
     * @return list of the intersections
     */
     List<Point3D> findIntersections(Ray ray);
}
