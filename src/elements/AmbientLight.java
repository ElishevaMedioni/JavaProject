package elements;

import primitives.Color;

/**
 *An ambient light source represents a fixed-intensity and fixed-color
 * light source that affects all objects in the scene equally.
 */
public class AmbientLight extends Light{

    /**
     * constructor of the AmbientLight
     * calculate the intensity according to the coefficient and the color
     * @param iA color
     * @param kA coefficient
     */
    public AmbientLight(Color iA, double kA){
        super(iA.scale(kA));
    }

    public AmbientLight(){super(Color.BLACK);}


}
