package renderer;

import elements.Camera;
import geometries.Intersectable;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * class for the improvement of the picture with the Super Sampling algorithm
 */
public class RayTracerSuperSampling extends RayTracerBasic{

    /**
     * number maximum of rays through each pixel
     */
    private final static int NUM_OF_RAYS=81;

    /**
     * use the parent (RayTracerBasic class) traceRay function
     * @param ray value of the ray that goes through a pixel
     * @return the color of a specific point
     */
    public Color traceRay(Ray ray){return null;}

    /**
     * constructor, goes to the father constructor
     */
    public RayTracerSuperSampling(Scene scene) {
        super(scene);
    }

    /**
     * function that return the average of the color of 64 rays, improvement with recursive call
     * @param p0 corner upLeft
     * @param p1 corner upRight
     * @param p2 corner downRight
     * @param p3 corner downLeft
     * @param cameraP0 camera position
     * @param level level of the recursion
     * @return the average of the colors of the rays
     */
    public Color traceRaySS(Point3D p0, Point3D p1, Point3D p2, Point3D p3, Point3D cameraP0, int level) {
        List<Color> listColors=new ArrayList<>();
        List<Point3D> listOfCorners=List.of(p0, p1, p2, p3);
        Ray ray;

        for(int i=0; i<4;i++){
            ray=new Ray(cameraP0, listOfCorners.get(i).subtract(cameraP0).normalized());
            Intersectable.GeoPoint gp= super.findClosestIntersection(ray);
            if(gp!=null)
                listColors.add(super.calcColor(gp,ray));
            else
                listColors.add(scene.background);
        }
    // condition for stopping the recursion
        if((listColors.get(0).equals(listColors.get(1)) && listColors.get(0).equals(listColors.get(2))
            && listColors.get(0).equals(listColors.get(3))) || level==0)
            return listColors.get(0);

        Point3D midUp=findMiddlePoint(p0, p1);
        Point3D midDown=findMiddlePoint(p2, p3);
        Point3D midRight=findMiddlePoint(p2, p1);
        Point3D midLeft=findMiddlePoint(p0, p3);
        Point3D center=findMiddlePoint(midRight, midLeft);

        // recursive call, call the function 4 times (corners square)
        return traceRaySS(p0, midUp, center, midLeft, cameraP0, level-1).scale(0.25)
                .add(traceRaySS(midUp, p1, midRight, center, cameraP0, level-1).scale(0.25))
                .add(traceRaySS(center, midRight, p2, midDown, cameraP0, level-1).scale(0.25))
                .add(traceRaySS(midLeft, center, midDown, p3, cameraP0, level-1).scale(0.25));
    }

    /**
     * help function to find the middle point between 2 points
     * @param p1 point1
     * @param p2 point2
     * @return middle point
     */
    public Point3D findMiddlePoint(Point3D p1, Point3D p2){
        double x= (p1.getX()+ p2.getX())/2d;
        double y= (p1.getY()+ p2.getY())/2d;
        double z= (p1.getZ()+ p2.getZ())/2d;
        return new Point3D(x, y, z);
    }


   /* public List<Ray> constructBeamRayThroughPixel(int nX, int nY, int j, int i, Camera camera){
        List<Ray> listOfRays= new ArrayList<>();
        Ray mainRay= camera.constructRayThroughPixel(nX, nY, j, i);
        listOfRays.add(mainRay);
        Point3D mainPoint= mainRay.getPoint(camera.getDistance());
        double rY= camera.getHeight()/nY; //height of a pixel
        double rX= camera.getWidth()/nX; //width of a pixel

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
    }*/
}