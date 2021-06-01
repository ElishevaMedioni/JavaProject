/**
 * 
 */
package unittests;

import geometries.Polygon;
import org.junit.Test;

import elements.*;
import geometries.Sphere;
import geometries.Triangle;
import primitives.*;
import primitives.Color;
import renderer.*;
import scene.Scene;

import java.awt.*;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 * 
 * @author dzilb
 */
public class ReflectionRefractionTests {
	private Scene scene = new Scene("Test scene");

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheres() {
		Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(150, 150).setDistance(1000);

		scene.geometries.add( //
				new Sphere( new Point3D(0, 0, -50),50) //
						.setEmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100).setKT(0.3)),
				new Sphere( new Point3D(0, 0, -50),25) //
						.setEmission(new Color(java.awt.Color.RED)) //
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(100)));
		scene.lights.add( //
				new SpotLight(new Color(1000, 600, 0), new Point3D(-100, -100, 500), new Vector(-1, -1, -2)) //
						.setKL(0.0004).setKQ(0.0000006));

		Render render = new Render() //
				.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
				.setCamera(camera) //
				.setRayTracerBase(new RayTracerBasic(scene));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheresOnMirrors() {
		Camera camera = new Camera(new Point3D(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(2500, 2500).setDistance(10000); //

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

		scene.geometries.add( //
				new Sphere(new Point3D(-950, -900, -1000),400) //
						.setEmission(new Color(0, 0, 100)) //
						.setMaterial(new Material().setKD(0.25).setKS(0.25).setNShininess(20).setKT(0.5)),
				new Sphere( new Point3D(-950, -900, -1000), 200) //
						.setEmission(new Color(100, 20, 20)) //
						.setMaterial(new Material().setKD(0.25).setKS(0.25).setNShininess(20)),
				new Triangle(new Point3D(1500, -1500, -1500), new Point3D(-1500, 1500, -1500),
						new Point3D(670, 670, 3000)) //
								.setEmission(new Color(20, 20, 20)) //
								.setMaterial(new Material().setKR(1)),
				new Triangle(new Point3D(1500, -1500, -1500), new Point3D(-1500, 1500, -1500),
						new Point3D(-1500, -1500, -2000)) //
								.setEmission(new Color(20, 20, 20)) //
								.setMaterial(new Material().setKR(0.5)));

		scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point3D(-750, -750, -150), new Vector(-1, -1, -4)) //
				.setKL(0.00001).setKQ(0.000005));

		ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTracerBase(new RayTracerBasic(scene));

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially
	 * transparent Sphere producing partial shadow
	 */
	@Test
	public void trianglesTransparentSphere() {
		Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(200, 200).setDistance(1000);

		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.geometries.add( //
				new Triangle(new Point3D(-150, -150, -115), new Point3D(150, -150, -135), new Point3D(75, 75, -150)) //
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(60)), //
				new Triangle(new Point3D(-150, -150, -115), new Point3D(-70, 70, -140), new Point3D(75, 75, -150)) //
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(60)), //
				new Sphere( new Point3D(60, 50, -50),30) //
						.setEmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setKD(0.2).setKS(0.2).setNShininess(30).setKT(0.6)));

		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(60, 50, 0), new Vector(0, 0, -1)) //
				.setKL(4E-5).setKQ(2E-7));

		ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTracerBase(new RayTracerBasic(scene));

		render.renderImage();
		render.writeToImage();

	}

	/**
	 * Produce a picture
	 */
	@Test
	public void FinalPicture() {
		Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(200, 200).setDistance(1000);
		scene.background=new Color(25,25,112);
		scene.setAmbientLight(new AmbientLight(new Color(500,500,500), 0.015));
		scene.lights.add( //
				new SpotLight(new Color(java.awt.Color.YELLOW), new Point3D(0, 100, -100), new Vector(0, -6, 10)) //
						.setKL(4E-4).setKQ(2E-5));
		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(60, 50, 0), new Vector(0, 0, -1)) //
				.setKL(4E-5).setKQ(2E-7));
//		scene.lights.add( //
//				new SpotLight(new Color(java.awt.Color.YELLOW), new Point3D(-50, 140, 0), new Vector(0, -9, 20)) //
//						.setKL(4E-4).setKQ(2E-5));
		scene.geometries.add( //
				//lune
				new Sphere(new Point3D(0,-110,-40),100.0)
						.setEmission(new Color(255,153,51).add(new Color(47,7,7)))
						.setMaterial(new Material().setKD(0.8).setKS(0.8).setNShininess(200).setKT(0).setKR(0.7)),
				//head (helmet)
				new Sphere(new Point3D(-16.5, 28.5,0), 8.5)
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setKR(0.5)),
				//right foot
				new Polygon(new Point3D(-13,0,0), new Point3D(-10,0,0),
						new Point3D(-10,-15,0), new Point3D(-13,-15,0))
						.setEmission(new Color(255,255,255)),
				//left foot
				new Polygon(new Point3D(-23,0,0), new Point3D(-20,0,0),
						new Point3D(-20,-15,0), new Point3D(-23,-15,0))
						.setEmission(new Color(255,255,255)),
				//body
				new Polygon(new Point3D(-25,0,0), new Point3D(-8,0,0),
						new Point3D(-8,20,0), new Point3D(-25,20,0))
						.setEmission(new Color(255,255,255)),
				//right arm
				new Triangle(new Point3D(-8,20,0), new Point3D(-5,4,0),
						new Point3D(-8,4,0))
						.setEmission(new Color(255,255,255)),
				//left arm
				new Triangle(new Point3D(-25,20,0), new Point3D(-25,4,0),
						new Point3D(-28,4,0))
						.setEmission(new Color(255,255,255)),
				//mars transparent
				new Sphere(new Point3D(-50,50,200), 10d)
							//.setEmission(new Color(java.awt.Color.RED).add(new Color(25,25,112)))
							.setMaterial(new Material().setKT(0.6).setNShininess(200).setKR(0.4)),
				//mars
				new Sphere(new Point3D(-50,50,195), 10d)
						.setEmission(new Color(153,0,0)),
				//star
				new Sphere(new Point3D(75,75,0), 0.7d)
							.setEmission(new Color(255,255,0)),
				//star
				new Sphere(new Point3D(48,70,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//star
				new Sphere(new Point3D(-75,50,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//star
				new Sphere(new Point3D(35,47,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//star
				new Sphere(new Point3D(89,95,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//star
				new Sphere(new Point3D(-89,-43,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//star
				new Sphere(new Point3D(10,-24,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//star
				new Sphere(new Point3D(-50,-39,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//star
				new Sphere(new Point3D(10,-75,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//star
				new Sphere(new Point3D(-75,10,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//star
				new Sphere(new Point3D(-50,40,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//star
				new Sphere(new Point3D(-10,85,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//star
				new Sphere(new Point3D(90,-10,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//star
				new Sphere(new Point3D(85,20,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//star
				new Sphere(new Point3D(20,10,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//star
				new Sphere(new Point3D(50,50,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//star
				new Sphere(new Point3D(65,75,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//star
				new Sphere(new Point3D(35,66,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//star
				new Sphere(new Point3D(40,-25,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//star
				new Sphere(new Point3D(78,10,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//star
				new Sphere(new Point3D(-34,78,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//star
				new Sphere(new Point3D(50,0,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//star
				new Sphere(new Point3D(-80,80,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//star
				new Sphere(new Point3D(-15,50,0), 0.7d)
						.setEmission(new Color(255,255,0))
		);

		//stars on the american flag
		for(double i=42; i>31;i-=1.5)
			for(double j=8.5;j<25;j+=2)
				scene.geometries.add(new Sphere(new Point3D(j, i, 0.5),0.5 )
						.setEmission(new Color(255,255,255)));
		scene.geometries.add( //
				//black bar flag
				new Polygon(new Point3D(7,43,0), new Point3D(6,43,0),
						new Point3D(6,-15,0),new Point3D(7,-15,0))
							.setEmission(Color.BLACK),
				//blue square of the flag
				new Polygon(new Point3D(7,43,0), new Point3D(25, 43, 0),
						new Point3D(25, 31, 0), new Point3D(7, 31, 0))
						.setEmission(new Color(0,0,255)),
				//red strip 1
				new Polygon(new Point3D(25, 43, 0), new Point3D(43, 43, 0),
						new Point3D(43, 41, 0), new Point3D(25, 41, 0))
						.setEmission(new Color(255,0,0)),
				//red strip 2
				new Polygon(new Point3D(25, 39, 0), new Point3D(43, 39, 0),
						new Point3D(43, 37, 0), new Point3D(25, 37, 0))
						.setEmission(new Color(255,0,0)),
				//red strip 3
				new Polygon(new Point3D(25, 35, 0), new Point3D(43, 35, 0),
						new Point3D(43, 33, 0), new Point3D(25, 33, 0))
						.setEmission(new Color(255,0,0)),
				//red strip 4
				new Polygon(new Point3D(7, 31, 0), new Point3D(43, 31, 0),
						new Point3D(43, 29, 0), new Point3D(7, 29, 0))
						.setEmission(new Color(255,0,0)),
				//red strip 5
				new Polygon(new Point3D(7, 27, 0), new Point3D(43, 27, 0),
						new Point3D(43, 25, 0), new Point3D(7, 25, 0))
						.setEmission(new Color(255,0,0)),
				//red strip 6
				new Polygon(new Point3D(7, 23, 0), new Point3D(43, 23, 0),
						new Point3D(43, 21, 0), new Point3D(7, 21, 0))
						.setEmission(new Color(255,0,0)),
				//red strip 7
				new Polygon(new Point3D(7, 19, 0), new Point3D(43, 19, 0),
						new Point3D(43, 17, 0), new Point3D(7, 17, 0))
						.setEmission(new Color(255,0,0)),
				//flag (white background)
				new Polygon(new Point3D(7, 17, 0), new Point3D(7, 43, 0),
						new Point3D(43, 43, 0), new Point3D(43, 17, 0))
						.setEmission(new Color(255,255,255))

		);



		ImageWriter imageWriter = new ImageWriter("moon", 600, 600);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTracerBase(new RayTracerBasic(scene));

		render.renderImage();
		render.writeToImage();
	}
}
