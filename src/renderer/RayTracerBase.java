package renderer;

import elements.Camera;
import primitives.Color;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public abstract class RayTracerBase {
    protected Scene scene;

    public RayTracerBase(Scene _scene){
        scene=_scene;
    }

    public abstract Color traceRay(Ray ray);
    public abstract List<Ray> constructBeamRayThroughPixel(int nX, int nY, int j, int i, Camera camera);
}
