package scene;

import elements.AmbientLight;
import elements.LightSource;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 *Scene is a passive class it's just a class for storage without any method
 */
public class Scene {
    public String name;
    public Color background=Color.BLACK;
    public AmbientLight ambientLight=new AmbientLight();
    public Geometries geometries;
    public List<LightSource> lights=new LinkedList<LightSource>();

    //CONSTRUCTOR
    public Scene(String _name){
        name=_name;
        geometries=new Geometries();
    }

    /**
     * for the setter of this class, we use the builder design pattern.
     * In order to improve the construction of the object (better than a constructor with a lot of parameters)
     */
    public Scene setBackground(Color _background){
        background=_background;
        return this;
    }

    public Scene setAmbientLight(AmbientLight _ambientLight){
        ambientLight=_ambientLight;
        return this;
    }

    public Scene setGeometries(Geometries _geometries){
        geometries=_geometries;
        return this;
    }

    public Scene setLights(List<LightSource> lights){
        this.lights=lights;
        return this;
    }
}
