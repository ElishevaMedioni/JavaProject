package renderer;

import elements.LightSource;
import geometries.Intersectable;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase{
    private static final double DELTA=0.1;
    private static final int MAX_CALC_COLOR_LEVEL=10;
    private static final double MIN_CALC_COLOR_K=0.001;

    private boolean unshaded(LightSource light,Vector l, Vector n, Intersectable.GeoPoint geoPoint) {
        Vector lightDirection = l.scale(-1); //change to the opposite direction of the vector

        //if the direction of the vector normal is the same to the direction of the light multiplied by DELTA
        Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
        Point3D point = geoPoint.point.add(delta);
        Ray lightRay = new Ray(point, lightDirection);

        //if there is intersection -> there is shadow
        List<Intersectable.GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        if(intersections == null) return true;
        double lightDistance = light.getDistance(geoPoint.point);

        //checking if the intersections are behind the camera (there is no shadow)
        for(Intersectable.GeoPoint gp : intersections)
            if(alignZero(gp.point.distance(geoPoint.point) - lightDistance)<=0)
                return false;
        return true;
    }

    public RayTracerBasic(Scene scene){
        super(scene);
    }

    /**
     * calculate the color of the pixel of the image
     * @param ray value of the ray that goes through a pixel
     * @return color of the closest intersection with the ray
     */
    public Color traceRay(Ray ray){
        List<Intersectable.GeoPoint> intersections=scene.geometries.findGeoIntersections(ray);
        if(intersections==null)
            return scene.background;
        Intersectable.GeoPoint geoPoint= ray.findClosestGeoPoint(intersections);
        return calcColor(geoPoint, ray);
    }

    public Color calcDiffusive(double kD, Vector l, Vector n,Color intensity){
        return intensity.scale(kD*(Math.abs(l.dotProduct(n))));
    }

    public Color calcSpecular(double kS, Vector l, Vector n, Vector v, int nShininess, Color intensity){
        Vector r=l.subtract(n.scale(2*n.dotProduct(l)));
        return intensity.scale(Math.pow(Math.max(0, -v.dotProduct(r)),nShininess));
    }

    private Color calcLocalEffects(Intersectable.GeoPoint intersection, Ray ray) {
        Vector v = ray.getDir ();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return Color.BLACK;
        Material material = intersection.geometry.getMaterial();
        int nShininess = material.nShininess;
        double kd = material.kD, ks = material.kS;
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                if(unshaded(lightSource, l, n, intersection)){
                    Color lightIntensity = lightSource.getIntensity(intersection.point);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }

    public Color calcColor(Intersectable.GeoPoint intersection, Ray ray){
        return scene.ambientLight.getIntensity()
                .add(intersection.geometry.getEmission())
                .add(calcLocalEffects(intersection, ray));
    }

    /**
     * The ray of light that leaves the mirror
     * At the point of incidence where the ray strikes the mirror, a line can be drawn perpendicular to the surface of the mirror
     * @param n normal vector of the geometry
     * @param point intersection with the ray
     * @param ray value of the ray
     * @return reflection ray
     */
    public Ray constructReflectedRay(Vector n, Point3D point, Ray ray){
        Vector delta = n.scale(n.dotProduct(ray.getDir()) > 0 ? DELTA : -DELTA);
        return new Ray(point.add(delta), ray.getDir().subtract(n.scale(2*n.dotProduct(ray.getDir()))));
    }

    /**
     *in our implementation we will assume that all geometries have the same refraction index of 1
     * without angle of incidence
     * @param n normal vector of the geometry
     * @param point intersection with the ray
     * @param ray value of the ray
     * @return refracted ray
     */
    public Ray constructRefractedRay(Vector n, Point3D point, Ray ray){
        Vector delta = n.scale(n.dotProduct(ray.getDir()) > 0 ? DELTA : -DELTA);
        return new Ray(point.add(delta), ray.getDir());
    }


}
