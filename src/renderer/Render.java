package renderer;

import elements.Camera;
import primitives.Color;
import primitives.Ray;
import scene.Scene;

import java.util.MissingFormatArgumentException;
import java.util.MissingResourceException;

public class Render {
    private ImageWriter imageWriter=null;
    private Camera camera=null;
    private RayTracerBase rayTracerBase=null;

    public Render setImageWriter(ImageWriter _imageWriter){
        imageWriter=_imageWriter;
        return this;
    }


    public Render setCamera(Camera _camera){
        camera=_camera;
        return this;
    }

    public Render setRayTracerBase(RayTracerBase _rayTracerBase){
        rayTracerBase=_rayTracerBase;
        return this;
    }

    /**
     * color every pixel of the image
     * check if none of the field aren't null
     */
    public void renderImage(){
        if(imageWriter==null)
            throw new MissingResourceException("Missing field","UnsupportedOperationException",
                    "imageWriter");
        if(camera==null)
            throw new MissingResourceException("Missing field","UnsupportedOperationException",
                    "camera");
        if(rayTracerBase==null)
            throw new MissingResourceException("Missing field","UnsupportedOperationException",
                    "rayTracerBase");
        Ray ray;
        Color color;
        for(int i=0;i<imageWriter.getNx();i++)
            for(int j=0;j<imageWriter.getNy();j++){
                ray= camera.constructRayThroughPixel(imageWriter.getNx(),imageWriter.getNy(),j,i);
                color=rayTracerBase.traceRay(ray);
                imageWriter.writePixel(j,i,color);
            }
    }


    public void checkMissingImageWriter(){
        if(imageWriter==null)
            throw new MissingResourceException("Missing field","UnsupportedOperationException",
                "imageWriter");
    }

    /**
     * print the grid according to the interval it gets
     * @param interval value of the interval
     * @param color
     */
    public void printGrid(int interval, Color color){
        checkMissingImageWriter();
        for(int i=0;i<imageWriter.getNx();i++)
            for(int j=0;j<imageWriter.getNy();j++){
                if(i%interval==0 || j%interval==0)
                    imageWriter.writePixel(i,j, color);
            }
        imageWriter.writeToImage();
    }

    /**
     * checking that the image exist and then print the image on a file
     */
    public void writeToImage(){
        checkMissingImageWriter();
        imageWriter.writeToImage();
    }
}
