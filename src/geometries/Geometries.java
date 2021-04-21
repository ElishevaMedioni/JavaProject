package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {
    private List<Intersectable> list;

    /**
     * we choose to implement LinkedList because LinkedList is better for manipulating data
     */
    public Geometries() {
        list = new LinkedList<>();
    }

    public Geometries(Intersectable... geometries){
        list= Arrays.asList(geometries);
    }

    public void add(Intersectable... geometries){
        list.addAll(Arrays.asList(geometries));
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }


}
