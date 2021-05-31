package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import java.awt.*;

/**
 *Light source is far away
 * no attenuation with distance (like sun)
 */
public class DirectionalLight extends Light implements LightSource{
    private Vector direction;

    /**
     * Constructor of the DirectionalLight
     * @param intensity value of the color
     * @param direction value of the vector
     */
    public DirectionalLight(Color intensity, Vector direction){
        super(intensity);
        this.direction=direction;
    }

    /**
     * color of the object on a specific point
     * @param p poitn3D on the object
     * @return color
     */
    @Override
    public Color getIntensity(Point3D p) {
        return super.getIntensity();
    }

    /**
     * vector from the light source to a point3D on the object
     * @param p point3D on the object
     * @return the vector
     */
    @Override
    public Vector getL(Point3D p) {
        return direction.normalized();
    }

    /**
     * distance betwween light and object
     * @param point point3D on the object
     * @return infinity - the light source is far away (like sun)
     */
    @Override
    public double getDistance(Point3D point){ return Double.POSITIVE_INFINITY;}
}
