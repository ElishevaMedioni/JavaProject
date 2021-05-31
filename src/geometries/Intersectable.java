package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.awt.*;
//import java.awt.Polygon;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * we create the interface Intersectable because all of the geometry use the method findIntersections
 */
public interface Intersectable {
    /**
     * fnd intersections of the ray with the geometry
     * @param ray value of the ray
     * @return list of the intersections
     */
    default List<Point3D> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).collect(Collectors.toList());
    }


    List<GeoPoint> findGeoIntersections(Ray ray);

    /**
     * the class is PDS (Passive Data Structure)
     * it stores the intersection of the ray with the geometry and the geometry itself for the color
     */
    class GeoPoint {
        public Geometry geometry;
        public Point3D point;

        /**
         * Constructor of GeoPoint
         * @param _geometry value of the geometry
         * @param _point3D value of the point3D
         */
        public GeoPoint(Geometry _geometry, Point3D _point3D){
            geometry=_geometry;
            point=_point3D;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GeoPoint)) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            if(!((geoPoint.geometry instanceof Sphere)&&(this.geometry instanceof Sphere))
            ||!((geoPoint.geometry instanceof Tube)&&(this.geometry instanceof Tube))
            ||!((geoPoint.geometry instanceof Polygon)&&(this.geometry instanceof Polygon))
            ||!((geoPoint.geometry instanceof Plane)&&(this.geometry instanceof Plane)))
                return false;
            return Objects.equals(geometry, geoPoint.geometry) && Objects.equals(point, geoPoint.point);
        }
    }

}
