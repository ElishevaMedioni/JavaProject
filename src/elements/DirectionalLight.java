package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 *Light source is far away
 * no attenuation with distance
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

    @Override
    public Color getIntensity() {
        return super.getIntensity();
    }

    @Override
    public Vector getL(Point3D p) {
        return direction.normalized();
    }
}
