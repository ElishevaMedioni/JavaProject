package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * interface because we need to use those methods for all the light class
 */
public interface LightSource {
    Color getIntensity(Point3D p);
    Vector getL(Point3D p);
    double getDistance(Point3D point);
}
