package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
import primitives.Util.*;

import static primitives.Util.isZero;

public class Camera {
    private Point3D p0;
    private Vector vUp;
    private Vector vTo;
    private Vector vRight;
    private double width;
    private double height;
    double distance;

    public Camera setViewPlaneSize(double _width, double _height){
        width=_width;
        height=_height;
        return this;
    }

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

    public Camera(Point3D _p0,Vector _vUp, Vector _vTo){
        p0=_p0;
        if(_vUp.length()==1&&_vTo.length()==1){
            vRight=_vUp.crossProduct(_vTo);
            vUp=_vUp;
            vTo=_vTo;
            if(vRight.length()!=1)
                throw new IllegalArgumentException("The vector vRight isn't normalize");
        }
        else
            throw new IllegalArgumentException("The vectors aren't normalize");
    }

    public Ray constructRayThroughPixel(int nX, int nY, int j, int i){

        //Image center
        Point3D pC=p0.add(vTo.scale(distance));
        //Ratio (pixel width & height)
        double rY=height/nY;
        double rX=width/nX;
        //Pixel[i,j] center
        double yI=-(i-(nY-1)/2d)*rY;
        double xJ=(j-(nX-1)/2d)*rX;
        Point3D pIJ=pC;
        if(!isZero(xJ))
            pIJ=pIJ.add(vRight.scale(xJ));
        if(!isZero(yI))
            pIJ=pIJ.add(vUp.scale(yI));
        Vector vIJ=pIJ.subtract(p0);
        return new Ray(p0, vIJ);



    }

}
