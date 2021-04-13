package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;

public class Triangle extends Polygon{

    public Triangle(Point3D p1, Point3D p2, Point3D p3)
    {
        super(p1,p2,p3);
    }

    public List<Point3D> findIntersections(Ray ray){
        return null;
    }

}
