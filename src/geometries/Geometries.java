package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * list of several geometries
 * we use composite design patterns in order to treat the same way each geometry via intersectable
 */
public class Geometries implements Intersectable {
    private List<Intersectable> geometries;

    /**
     * we choose to implement LinkedList because LinkedList is better for manipulating data
     * better for the complexity (when you add an element to the list)
     * however the ArrayList is better for positional access and in our case we don't need it
     */
    public Geometries() {
        geometries = new LinkedList<>();
    }

    /**
     * constructor of the Geometries class
     * contruct the list with the geometries that he gets
     * @param geometries several geometries
     */
    public Geometries(Intersectable... geometries){
        this.geometries = Arrays.asList(geometries);
    }

    /**
     * add a geometries to the list
     * @param geometries one or more geometries
     */
    public void add(Intersectable... geometries){
        this.geometries.addAll(Arrays.asList(geometries));
    }

    /**
     * calculate all the intersection of the ray with all the geometries inside of the list
     * @param ray value of the ray
     * @return list of all the intersections
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {

        List<Point3D> intersections= new LinkedList<>(); //create a lists of the intersections (big list)
        List<Point3D> inter; //little list, a helper list

        //if the list of the geometries is empty return null
        if(geometries.size()==0)
            return null;

        //the for loop go throught all the geometries of the list and check the intersections with the ray
        for(int i = 0; i< geometries.size(); i++){
            inter= geometries.get(i).findIntersections(ray);
            if(inter!=null){ //if inter is null we can't add it to the big list
                intersections.addAll(inter);
            }
        }
        if(intersections.size()==0) //if the big list is empty there is no intersection -> return null
            return null;
        return intersections;
    }


}
