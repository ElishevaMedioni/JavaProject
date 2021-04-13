package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;

public interface Intersectable {
    /**
     * fnd intersections of the ray with the geometry
     * @param ray value of the ray
     * @return list of the intersections
     */
    public List<Point3D> findIntersections(Ray ray);
}
