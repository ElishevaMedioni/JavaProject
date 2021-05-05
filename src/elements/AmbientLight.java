package elements;

import primitives.Color;

/**
 *An ambient light source represents a fixed-intensity and fixed-color
 * light source that affects all objects in the scene equally.
 */
public class AmbientLight {
    private Color intensity;

    /**
     * constructor of the AmbientLight
     * calculate the intensity according to the coefficient and the color
     * @param iA color
     * @param kA coefficient
     */
    public AmbientLight(Color iA, double kA){
        intensity=iA.scale(kA);
    }

    public Color getIntensity(){return intensity;}


}
