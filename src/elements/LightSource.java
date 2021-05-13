package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public interface LightSource {
    Color getIntensity();
    Vector getL(Point3D p);
}
