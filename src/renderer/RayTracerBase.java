package renderer;

import elements.Camera;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public abstract class RayTracerBase {
    protected Scene scene;


    public RayTracerBase(Scene _scene){
        scene=_scene;
    }

    public abstract Color traceRay(Ray ray);
    public abstract Color traceRaySS(Point3D p0, Point3D p1, Point3D p2, Point3D p3, Point3D cameraP0, int level);
}
