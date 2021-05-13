package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class SpotLight extends PointLight{
    private Vector direction;

    public SpotLight(Color intensity, Point3D position, Vector direction){
        super(intensity, position);
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
