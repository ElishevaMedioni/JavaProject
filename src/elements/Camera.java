package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
import primitives.Util.*;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.isZero;

/**
 * The camera Shoot rays from the center of projection
 * through the view plane pixels
 */
public class Camera {
    //fields
    private Point3D p0;
    private Vector vUp;
    private Vector vTo;
    private Vector vRight;
    private double width;
    private double height;
    private double distance;


    /**
     * Camera constructor, normalize vectors vTo & vUp and calculate vRight with crossProduct
     * @param p0 the point of the location of the camera
     * @param vTo vector on axis z
     * @param vUp vector on axis y
     */
    public Camera(Point3D p0,Vector vTo, Vector vUp){
        this.p0=p0;
        if(!isZero(vTo.dotProduct(vUp))){
            throw new IllegalArgumentException("The vectors vTo and vUp aren't orthogonal");
        }
        if(vUp.length()!=1){
            this.vUp=vUp.normalize();
            if(vTo.length()!=1)
                this.vTo=vTo.normalize();
        }
        else if(vTo.length()!=1)
            this.vTo=vTo.normalize();
        else {
            this.vUp=vUp;
            this.vTo=vTo;
        }
        this.vRight=this.vTo.crossProduct(this.vUp);
        if(this.vRight.length()!=1)
            this.vRight=this.vRight.normalize();

    }


    //getter & setter

    /**
     * set the width and the height of the viewplane
     * @param width value of the width
     * @param height value of the height
     * @return the Camera with width and height set
     */
    public Camera setViewPlaneSize(double width, double height){
        this.width=width;
        this.height=height;
        return this;
    }

    /**
     * set the distance of the camera
     * @param distance value of the distance of the camera to the viewplane
     * @return the camera with the distance set
     */
    public Camera setDistance(double distance){
        this.distance=distance;
        return this;
    }

    public Point3D getP0() {
        return p0;
    }

    public Vector getvUp() {
        return vUp;
    }

    public Vector getvRight() {
        return vRight;
    }

    public Vector getvTo() {
        return vTo;
    }

    public double getDistance() { return distance; }

    public double getHeight() { return height; }

    public double getWidth() { return width; }
    //methods

    /**
     * This function will return the ray through pixel from the camera to the view plane
     *
     * @param nX Number of columns
     * @param nY Number of rows
     * @param j column
     * @param i row
     * @return Ray
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i){

        //calculate the center of the view plane
        Point3D pC=p0.add(vTo.scale(distance));

        //calculate the height and the width of each pixel
        double rY=height/nY;
        double rX=width/nX;

        //calculate how many meter we have to move up/down and left/right from the center
        double yI=-(i-(nY-1)/2d)*rY;
        double xJ=(j-(nX-1)/2d)*rX;

        //to avoid constructing a vector zero
        Point3D pIJ=pC; //Point3D of the ray
        if(!isZero(xJ))
            pIJ=pIJ.add(vRight.scale(xJ));
        if(!isZero(yI))
            pIJ=pIJ.add(vUp.scale(yI));
        Vector vIJ=pIJ.subtract(p0); // vector of the ray
        return new Ray(p0, vIJ);
    }

    /**
     * This function will return a list of the 4 corner in a pixel
     * @param nX Number of columns
     * @param nY Number of rows
     * @param j column
     * @param i row
     * @return List<Point3D>
     */
    public List<Point3D> constructPointsThroughPixel(int nX, int nY, int j, int i){

        //calculate the center of the view plane
        Point3D pC=p0.add(vTo.scale(distance));

        //calculate the height and the width of each pixel
        double rY=height/nY;
        double rX=width/nX;

        //calculate how many meter we have to move up/down and left/right from the center
        double yI=-(i-(nY-1)/2d)*rY;
        double xJ=(j-(nX-1)/2d)*rX;

        //to avoid constructing a vector zero
        Point3D pIJ=pC; //Point3D of the ray
        if(!isZero(xJ))
            pIJ=pIJ.add(vRight.scale(xJ));
        if(!isZero(yI))
            pIJ=pIJ.add(vUp.scale(yI));
        List<Point3D> list=new LinkedList<>();
        double x=pIJ.getX(), y=pIJ.getY(), z=pIJ.getZ();
        Point3D upL=new Point3D(x-rX/2, y+rY/2, z);
        Point3D upR=new Point3D(x+rX/2, y+rY/2, z);
        Point3D downR=new Point3D(x+rX/2, y-rY/2, z);
        Point3D downL=new Point3D(x-rX/2, y-rY/2, z);
        return List.of(upL,upR,downR, downL);
    }
}
