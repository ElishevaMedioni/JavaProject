package renderer;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.util.List;

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
        List<Point3D> intersections=scene.geometries.findIntersections(ray);
        if(intersections==null)
            return scene.background;
        Point3D closestPoint= ray.findClosestPoint(intersections);
        return calcColor(closestPoint);
    }


    public Color calcColor(Point3D point3D){
        return scene.ambientLight.getIntensity();
    }
}
