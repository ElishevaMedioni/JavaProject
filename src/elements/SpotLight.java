package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
import primitives.Util;

/**
 * a lamp projecting a narrow, intense beam of light directly on to a person, an object, a place etc..
 */
public class SpotLight extends PointLight{
    private Vector direction;

    public SpotLight(Color intensity, Point3D position, Vector direction){
        super(intensity, position);
        this.direction=direction.normalized();
    }

    @Override
    public Color getIntensity(Point3D p) {
        double cos=Util.alignZero(this.direction.dotProduct(getL(p)));
        //take the max between 0-cos if negative number or zero -> cos=0
        cos= cos > 0 ? cos : 0;
        return super.getIntensity(p).scale(cos);
    }


    @Override
    public Vector getL(Point3D p) {
        return super.getL(p);
    }
}
