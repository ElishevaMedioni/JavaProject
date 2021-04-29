package unittests;

import elements.Camera;
import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTests {

    public List<Point3D> calculateIntersections(Camera camera, Intersectable inter){
        List<Point3D> list=new LinkedList<>();
        Ray ray;
        int i, j;
        for( i = 0; i < 3; i++)
            for (j = 0; j < 3; j++) {
                ray = camera.constructRayThroughPixel(3, 3, j, i);
                List<Point3D> tmp=inter.findIntersections(ray);
                if(tmp!=null)
                     list.addAll(tmp);
            }
        if(list.size()==0)
            return null;
        return list;
    }

    @Test
    public void testRayIntersection(){

        //TC01: camera in front of the sphere with the radius of 1 (2 point)
        Camera camera=new Camera(Point3D.ZERO, new Vector(0,1,0), new Vector(0,0,-1))
                .setDistance(1).setViewPlaneSize(3,3);
        Sphere sp=new Sphere(new Point3D(0,0,-3),1);

        assertEquals("camera in front of the sphere with the radius of 1",2,
                calculateIntersections(camera, sp).size());

        //TC02: camera in front of the sphere with the radius of 2.5 (18 point)
        Camera camera2=new Camera(new Point3D(0,0,0.5), new Vector(0,1,0), new Vector(0,0,-1))
                .setDistance(1).setViewPlaneSize(3,3);
        Sphere sp2=new Sphere(new Point3D(0,0,-2.5),2.5);

        assertEquals("camera in front of the sphere with the radius of 2.5",18,
                calculateIntersections(camera2, sp2).size());

        //TC03: camera in front of the sphere with the radius of 2 (1O point)
        Sphere sp3=new Sphere(new Point3D(0,0,-2),2);

        assertEquals("camera in front of the sphere with the radius of 2",10,
                calculateIntersections(camera2, sp3).size());

        //TC04: the sphere covers the camera and the whole view plane (9 point)
        Camera camera3=new Camera(new Point3D(0,0,2), new Vector(0,1,0), new Vector(0,0,-1))
                .setDistance(1).setViewPlaneSize(3,3);
        Sphere sp4=new Sphere(Point3D.ZERO,4);

        assertEquals("the sphere covers the camera and the whole view plane",9,
                calculateIntersections(camera3, sp4).size());

        //TC05: the sphere is behind of the camera (O point)
        Camera camera4=new Camera(new Point3D(0,0,-1),  new Vector(0,1,0), new Vector(0,0,-1))
                .setDistance(1).setViewPlaneSize(3,3);
        Sphere sp5=new Sphere(new Point3D(0,0,1),0.5);

        assertNull(calculateIntersections(camera4, sp5),
                "the sphere is behind of the camera");


        Camera camera5=new Camera(Point3D.ZERO, new Vector(0,1,0), new Vector(0,0,-1))
                .setDistance(1).setViewPlaneSize(3,3);

        //TC06: camera in front of the plane (9 points)

       // Plane pl=new Plane(new Point3D(3,3,1), new Point3D(0,3,1), new Point3D(-3,3,1));
        Plane pl=new Plane(new Point3D(0,0,-3), new Vector(0,0,1));
        assertEquals("camera in front of the plane",9,
                calculateIntersections(camera5, pl).size());

        //TC07: the camera is in front and the plane is incline to the camera (9 points)
        //Plane pl1=new Plane(new Point3D(-4,4,-4), new Point3D(4,4,-4), new Point3D(0,2,6));
        Plane pl1=new Plane(new Point3D(0,0,-3), new Vector(0,-1,3));
        assertEquals("the camera is in front and the plane is incline to the camera",9,
                calculateIntersections(camera5, pl1).size());


        //TC08: the camera is in front and the plane is incline to the camera with less intersection (6 points)
        //Plane pl2=new Plane(new Point3D(-2,4,4.5), new Point3D(2,4,4.5), new Point3D(0,2,6));
        Plane pl2=new Plane(new Point3D(0,0,-3), new Vector(0,-2,-2));
        assertEquals("the camera is in front and the plane is incline to the camera with less intersection",
                6, calculateIntersections(camera5, pl2).size());

        //TC09: camera in front of a little triangle (1 point)
        Triangle tr=new Triangle(new Point3D(0,1,-2), new Point3D(1,-1,-2),new Point3D(-1,-1,-2));

        assertEquals("camera in front of a little triangle",
                1, calculateIntersections(camera5, tr).size());

        //TC09: camera in front of a nig triangle (1 point)
        Triangle tr1=new Triangle(new Point3D(0,20,-2), new Point3D(1,-1,-2),new Point3D(-1,-1,-2));

        assertEquals("camera in front of a big triangle",
                2, calculateIntersections(camera5, tr1).size());
    }
}
