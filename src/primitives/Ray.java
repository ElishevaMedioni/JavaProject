package primitives;
import geometries.Intersectable.GeoPoint;

import java.awt.*;
import java.util.List;
import java.util.Objects;

/**
 * Ray Class has a direction and a starting point
 */
public class Ray {
    private static final double DELTA=0.1;

    /**
     * point3D and vector field
     */
    Point3D p0;
    Vector dir;

    /**
     * get the starting point of the ray
     * @return the point3D value
     */
    public Point3D getP0() {
        return p0;
    }

    /**
     * set the point3D value
     * @param p0 point3D value
     */
    public void setP0(Point3D p0) {
        this.p0 = p0;
    }

    /**
     * get the vector value
     * @return the vector value
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * set the vector value of the ray
     * @param dir vector value
     */
    public void setDir(Vector dir) {
        this.dir = dir;
    }

    /**
     * ray constructor receiving the point3D and the vector
     * @param p0 the point3D value
     * @param dir the vector value
     */
    public Ray(Point3D p0, Vector dir) {
        if (dir.length()!=1)
            dir = dir.normalized();

        this.p0 = p0;
        this.dir = dir;
    }

    public Ray(Point3D p, Vector dir, Vector n) {
        Vector delta = n.scale(n.dotProduct(dir)     > 0 ? DELTA : -DELTA);
        this.p0 = p.add(delta);
        this.dir=dir;
    }

    /**
     * calculate the point with the ray
     * @param t value of the scale
     * @return the value of the point3D
     */
    public Point3D getPoint(double t){
        return getP0().add(getDir().scale(t));
    }

    /**
     * go through all the list of the intersection, calculate the distance between the ray and the intersection (point3D)
     * @param list list of all the intersections with the ray
     * @return intersection with the smallest distance with the ray
     */
    public Point3D findClosestPoint(List<Point3D> list){
        if(list==null)
            return null;
        Point3D min=list.get(0);
        double minDistance= p0.distance(list.get(0)), dist;
        for(Point3D point3D: list){
            dist=p0.distance(point3D);
            if(minDistance>dist){
                minDistance=dist;
                min=point3D;
            }
        }
        return min;
    }

    public GeoPoint findClosestGeoPoint(List<GeoPoint> list){
        if(list==null)
            return null;
        GeoPoint min=list.get(0);
        double minDistance= p0.distance(list.get(0).point), dist;
        for(GeoPoint geoPoint: list){
            dist=p0.distance(geoPoint.point);
            if(minDistance>dist){
                minDistance=dist;
                min=geoPoint;
            }
        }
        return min;
    }

    /*************** Admin *****************/

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Ray)) return false;
        Ray ray = (Ray) object;
        return java.util.Objects.equals(p0, ray.p0) && java.util.Objects.equals(dir, ray.dir);
    }

    @Override
    public java.lang.String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }

}
