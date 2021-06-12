package renderer;

import elements.Camera;
import geometries.Intersectable;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RayTracerSuperSampling extends RayTracerBasic{

    private final static int NUM_OF_RAYS=81;

    public Color traceRay(Ray ray){return super.traceRay(ray);}

    public RayTracerSuperSampling(Scene scene) {
        super(scene);
    }

    public List<Ray> constructBeamRayThroughPixel(int nX, int nY, int j, int i, Camera camera){
        List<Ray> listOfRays= new ArrayList<>();
        Ray mainRay= camera.constructRayThroughPixel(nX, nY, j, i);
        listOfRays.add(mainRay);
        Point3D mainPoint= mainRay.getPoint(camera.getDistance());
        double rY= camera.getHeight()/nY;
        double rX= camera.getWidth()/nX;
        Random rand=new Random();
        for(int k=0; k<NUM_OF_RAYS; k++){
            double randX=rX*rand.nextDouble()-rX/2;
            double randY=rY*rand.nextDouble()-rY/2;
            Vector dir= camera.getvRight().scale(randX).add(camera.getvUp().scale(randY));
            Point3D point=mainPoint.add(dir);
            Vector vector=point.subtract(camera.getP0()).normalized();
            listOfRays.add(new Ray(camera.getP0(), vector));
        }
        return listOfRays;
    }
}
