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
    private static final double INITIAL_K=1.0;

    private boolean unshaded(LightSource light,Vector l, Vector n, Intersectable.GeoPoint geoPoint) {
        Vector lightDirection = l.scale(-1); //change to the opposite direction of the vector

        //if the direction of the vector normal is the same to the direction of the light multiplied by DELTA
        Ray lightRay = new Ray(geoPoint.point, lightDirection, n);

        //if there is intersection -> there is shadow
        List<Intersectable.GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        if(intersections == null) return true;
        double lightDistance = light.getDistance(geoPoint.point);

        //checking if the intersections are behind the camera (there is no shadow)
        for(Intersectable.GeoPoint gp : intersections)
            if(alignZero(gp.point.distance(geoPoint.point) - lightDistance)<=0
            && gp.geometry.getMaterial().kT==0)
                return false;
        return true;
    }

    private double transparency(LightSource light,Vector l, Vector n, Intersectable.GeoPoint geoPoint) {
        Vector lightDirection = l.scale(-1); //change to the opposite direction of the vector

        //if the direction of the vector normal is the same to the direction of the light multiplied by DELTA
        Ray lightRay = new Ray(geoPoint.point, lightDirection, n);
        //if there is intersection -> there is shadow
        List<Intersectable.GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        if(intersections == null) return 1.0;
        double lightDistance = light.getDistance(geoPoint.point);
        double ktr=1.0;
        //checking if the intersections are behind the camera (there is no shadow)
        for(Intersectable.GeoPoint gp : intersections)
            if(alignZero(gp.point.distance(geoPoint.point) - lightDistance)<=0 ){
                ktr*=gp.geometry.getMaterial().kT;
                if(ktr<MIN_CALC_COLOR_K) return 0.0;
            }
        return ktr;
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
        Intersectable.GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint==null? scene.background:calcColor(closestPoint, ray);
    }

    public Color calcDiffusive(double kD, Vector l, Vector n,Color intensity){
        return intensity.scale(kD*(Math.abs(l.dotProduct(n))));
    }

    public Color calcSpecular(double kS, Vector l, Vector n, Vector v, int nShininess, Color intensity){
        Vector r=l.subtract(n.scale(2*n.dotProduct(l)));
        return intensity.scale(Math.pow(Math.max(0, -v.dotProduct(r)),nShininess));
    }

    private Color calcLocalEffects(Intersectable.GeoPoint intersection, Ray ray, double k) {
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
                double ktr=transparency(lightSource,l,n,intersection);
                if(ktr*k>MIN_CALC_COLOR_K){
                    Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
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
        /**Vector delta = n.scale(n.dotProduct(ray.getDir()) > 0 ? DELTA : -DELTA);
        return new Ray(point.add(delta), ray.getDir().subtract(n.scale(2*n.dotProduct(ray.getDir()))));*/
        Vector v= ray.getDir();
        double vn= v.dotProduct(n);
        Vector vnn=n.scale(vn);
        Vector r=v.subtract(vnn.scale(2));
        double nr=n.dotProduct(r);
        Vector delta=null;
        if(Util.isZero(nr))
            return new Ray(point, r);
        return new Ray(point, r, n); //use the new constructor for the ray
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
        return new Ray(point, ray.getDir(), n);
    }

    private Intersectable.GeoPoint findClosestIntersection(Ray ray){
        List<Intersectable.GeoPoint> list = scene.geometries.findGeoIntersections(ray);
        return list==null ? null:ray.findClosestGeoPoint(list);
    }

    private Color calcColor(Intersectable.GeoPoint intersection, Ray ray, int level, double k){
        Color color=intersection.geometry.getEmission();
        color=color.add(calcLocalEffects(intersection, ray, k));
        return 1==level? color:color.add(calcGlobalEffects(intersection,ray,level,k));
    }

    private Color calcColor(Intersectable.GeoPoint geoPoint, Ray ray){
        return calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());
    }

    private Color calcGlobalEffects(Intersectable.GeoPoint geoPoint, Ray ray, int level, double k){
        Color color= Color.BLACK;
        Material material=geoPoint.geometry.getMaterial();
        double kr=material.kR;
        double kkr=kr*k;
        if(kkr>MIN_CALC_COLOR_K){
            //pas sur à verifier
            Ray reflectedRay=constructReflectedRay(geoPoint.geometry.getNormal(geoPoint.point),geoPoint.point,ray);
            Intersectable.GeoPoint reflectedPoint=findClosestIntersection(reflectedRay);
            if(reflectedPoint!=null)
                color=color.add(calcColor(reflectedPoint,reflectedRay,level-1,kkr)).scale(kr);
            else
                color=color.add(scene.background.scale(kr));
        }
        double kt=material.kT;
        double kkt=kt*k;
        if(kkt>MIN_CALC_COLOR_K){
            //pas sur à verifier
            Ray refractedRay=constructRefractedRay(geoPoint.geometry.getNormal(geoPoint.point),geoPoint.point,ray);
            Intersectable.GeoPoint refractedPoint=findClosestIntersection(refractedRay);
            if(refractedPoint!=null)
                color=color.add(calcColor(refractedPoint,refractedRay,level-1,kkt)).scale(kt);
            else
                color=color.add(scene.background.scale(kt));
        }
        return color;
    }
}
