package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {
    private List<Intersectable> geometries;

    /**
     * we choose to implement LinkedList because LinkedList is better for manipulating data
     */
    public Geometries() {
        geometries = new LinkedList<>();
    }

    public Geometries(Intersectable... geometries){
        this.geometries = Arrays.asList(geometries);
    }

    public void add(Intersectable... geometries){
        this.geometries.addAll(Arrays.asList(geometries));
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> intersections= new LinkedList<>(), inter;
        if(geometries.size()==0)
            return null;
        for(int i = 0; i< geometries.size(); i++){
            inter= geometries.get(i).findIntersections(ray);
            if(inter!=null){
                intersections.addAll(inter);
            }
        }
        if(intersections.size()==0)
            return null;
        return intersections;
    }


}
