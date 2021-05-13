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

    @Override
    public Color getIntensity() {

        return super.getIntensity();
    }

    @Override
    public Vector getL(Point3D p) {
        Vector vector=p.subtract(position);
        return vector.normalized();
    }
}
