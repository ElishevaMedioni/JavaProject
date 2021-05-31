package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * The light is inside the scene at a specific location and it shines light equally in all directions (table lamp)
 */
public class PointLight extends Light implements LightSource{
    private Point3D position;
    /**
     * kC, kL, kQ are factors for attenuation
     * kC is required to ensure that the denominator > 1
     */
    private double kC=1;
    private double kL=0;
    private double kQ=0;

    /**
     * Constructor of the PointLight
     * @param intensity value of the color
     * @param position value of the Point3D
     */
    public PointLight(Color intensity, Point3D position){
        super(intensity);
        this.position=position;
    }

    /**
     *setKC, setKL, setKQ - for builder design pattern
     */
    public PointLight setKC(double kC){
        this.kC=kC;
        return this;
    }

    public PointLight setKL(double kL){
        this.kL=kL;
        return this;
    }

    public PointLight setKQ(double kQ){
        this.kQ=kQ;
        return this;
    }

    /**
     * color of the object on a specific point
     * @param point poitn3D on the object
     * @return color
     */
    @Override
    public Color getIntensity(Point3D point) {
        double distance=getDistance(point);
        double denominator=kC+kL*distance+kQ*distance*distance;
        return getIntensity().reduce(denominator);
    }

    /**
     * vector from the light source to a point3D on the object
     * @param p point3D on the object
     * @return the vector
     */
    @Override
    public Vector getL(Point3D p) {
        Vector vector=p.subtract(position);
        return vector.normalized();
    }

    /**
     * distance between light and object
     * @param point point3D on the object
     * @return infinity - the light source is far away (like sun)
     */
    @Override
    public double getDistance(Point3D point){
        return position.distance(point);
    }
}
