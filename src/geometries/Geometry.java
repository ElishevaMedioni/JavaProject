package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

/**
 * Geometry class is for the geometries and the colors
 */
public abstract class Geometry implements Intersectable{
    protected Color emission= Color.BLACK;
    private Material material= new Material();
    public abstract Vector getNormal(Point3D p);

    /**
     * get the color of the geometry
     * @return value of the color
     */
    public Color getEmission(){return emission;}

    /**
     * set the value of the color
     * @param _emission value of the color of the geometry
     * @return the object (builder design pattern)
     */
    public Geometry setEmission(Color _emission){
         emission=_emission;
        return this;
    }

    /**
     * set the value of the material
     * @param material value of the material
     * @return the object (builder design pattern)
     */
    public Geometry setMaterial(Material material){
        this.material=material;
        return this;
    }

    /**
     * get the value of the Material
     * @return the value of the material
     */
    public Material getMaterial(){
        return material;
    }

}
