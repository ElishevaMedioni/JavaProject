package renderer;

import elements.LightSource;
import geometries.Intersectable;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase{

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
        Vector r=l.substract(n.scale(2*n.dotProduct(l)));
        return intensity.scale(Math.pow(Math.max(0, -v.dotProduct(r)),nShininess));
    }

    private Color calcLocalEffects(Intersectable.GeoPoint intersection, Ray ray) {
        Vector v = ray.getDir ();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        Material material = intersection.geometry.getMaterial();
        int nShininess = material.nShininess;
        double kd = material.kD, ks = material.kS;
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color lightIntensity = lightSource.getIntensity(intersection.point);
                color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                        calcSpecular(ks, l, n, v, nShininess, lightIntensity));
            }
        }
        return color;
    }

    public Color calcColor(Intersectable.GeoPoint intersection, Ray ray){
        return scene.ambientLight.getIntensity()
                .add(intersection.geometry.getEmission())
                .add(calcLocalEffects(intersection, ray));
    }
}
