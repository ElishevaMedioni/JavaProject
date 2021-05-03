package scene;

import elements.AmbientLight;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

import java.util.LinkedList;

public class Scene {
    public String name;
    public Color background;
    public AmbientLight ambientLight;
    public Geometries geometries;

    public Scene(String _name){
        name=_name;
        geometries=new Geometries();
    }

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
}
