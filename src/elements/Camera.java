package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
import primitives.Util.*;

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
    double distance;


    /**
     * Camera constructor, normalize vectors vTo & vUp and calculate vRight with crossProduct
     * @param _p0 the point of the location of the camera
     * @param _vTo vector on axis z
     * @param _vUp vector on axis y
     */
    public Camera(Point3D _p0,Vector _vTo, Vector _vUp){
        p0=_p0;
        vUp=_vUp.normalize();
        vTo=_vTo.normalize();

        vRight=vTo.crossProduct(vUp);
        /*if(_vUp.length()==1 && _vTo.length()==1){
            vUp=_vUp.normalize();
            vTo=_vTo.normalize();

            vRight=vUp.crossProduct(vTo);

            if(vRight.length()!=1)
                throw new IllegalArgumentException("The vector vRight isn't normalize");
        }
        else
            throw new IllegalArgumentException("The vectors aren't normalize");*/
    }


    //getter & setter

    /**
     * set the width and the height of the viewplane
     * @param _width value of the width
     * @param _height value of the height
     * @return the Camera with width and height set
     */
    public Camera setViewPlaneSize(double _width, double _height){
        width=_width;
        height=_height;
        return this;
    }

    /**
     * set the distance of the camera
     * @param _distance value of the distance of the camera to the viewplane
     * @return the camera with the distance set
     */
    public Camera setDistance(double _distance){
        distance=_distance;
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

        //calculate the center of the image
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

}
