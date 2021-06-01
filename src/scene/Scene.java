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
    public AmbientLight ambientLight=new AmbientLight(background, 0);
    public Geometries geometries;
    public List<LightSource> lights=new LinkedList<LightSource>();

    //CONSTRUCTOR
    public Scene(String name){
        this.name=name;
        this.geometries=new Geometries();
    }

    /**
     * for the setter of this class, we use the builder design pattern.
     * In order to improve the construction of the object (better than a constructor with a lot of parameters)
     */
    public Scene setBackground(Color background){
        this.background=background;
        return this;
    }

    public Scene setAmbientLight(AmbientLight ambientLight){
        this.ambientLight=ambientLight;
        return this;
    }

    public Scene setGeometries(Geometries geometries){
        this.geometries=geometries;
        return this;
    }

    public Scene setLights(List<LightSource> lights){
        this.lights=lights;
        return this;
    }
}
