package elements;

import primitives.Color;

/**
 * There is several type of light
 * 1- Directional light (the sun)
 * 2- Ambient light
 * 3- Point light
 * 4- Spot light
 */
public abstract class Light {
    private Color intensity;

    /**
     * Constructor of light
     * @param intensity get the value of the color and set it on the field
     */
    protected Light(Color intensity){
        this.intensity=intensity;
    }

    /**
     * get the value of the color
     * @return value of the color
     */
    public Color getIntensity(){
        return intensity;
    }
}
