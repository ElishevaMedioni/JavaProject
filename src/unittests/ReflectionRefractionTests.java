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
		scene.background=new Color(5,5,94);
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.geometries.add( //
				//door
				new Polygon(new Point3D(-80,-50,0), new Point3D(-60,-50,0),
						new Point3D(-60,-100,0), new Point3D(-80,-100,0))
						.setEmission(new Color(155,9,9)),

				//windows 1 left
				new Polygon(new Point3D(-95,-10,0), new Point3D(-75,-10,0),
						new Point3D(-75,-30,0), new Point3D(-95,-30,0))
						.setEmission(new Color(224,224,224))
						.setMaterial(new Material().setNShininess(30).setKT(1).setKR(1)),

				//windows 2 right
				new Polygon(new Point3D(-65,-10,0), new Point3D(-45,-10,0),
						new Point3D(-45,-30,0), new Point3D(-65,-30,0))
						.setEmission(new Color(224,224,224))
						.setMaterial(new Material().setNShininess(30).setKT(1).setKR(1)),

				//house
				new Polygon(new Point3D(-100,0,0), new Point3D(-40,0,0),
						new Point3D(-40,-100,0), new Point3D(-100,-100,0))
				.setEmission(new Color(219,170,120)),

				//dome
				new Sphere(new Point3D(40,20,-30),30.0)
						.setEmission(new Color(187,161,79)),

				//rectangle pour dome
				new Polygon(new Point3D(10,20,0), new Point3D(70,20,0),
						new Point3D(70,0,0), new Point3D(10,0,0))
						.setEmission(new Color(255,0,0)),

				//pierre du mur 1
				new Polygon(new Point3D(-40,-75,0), new Point3D(0,-75,0),
						new Point3D(0,-100,0), new Point3D(-40,-100,0))
						.setEmission(new Color(214,175,120)),

				//pierre du mur 2
				new Polygon(new Point3D(0,-75,0), new Point3D(20,-75,0),
						new Point3D(20,-100,0), new Point3D(0,-100,0))
						.setEmission(new Color(204,156,89)),

				//pierre du mur 3
				new Polygon(new Point3D(20,-75,0), new Point3D(70,-75,0),
						new Point3D(70,-100,0), new Point3D(20,-100,0))
						.setEmission(new Color(212,171,114)),

				//pierre du mur 4
				new Polygon(new Point3D(70,-75,0), new Point3D(100,-75,0),
						new Point3D(100,-100,0), new Point3D(70,-100,0))
						.setEmission(new Color(206,160,95)),

				//pierre du mur 5
				new Polygon(new Point3D(-40,-50,0), new Point3D(-10,-50,0),
						new Point3D(-10,-75,0), new Point3D(-40,-75,0))
						.setEmission(new Color(202,152,82)),

				//pierre du mur 6
				new Polygon(new Point3D(-10,-50,0), new Point3D(30,-50,0),
						new Point3D(30,-75,0), new Point3D(-10,-75,0))
						.setEmission(new Color(210,167,107)),

				//pierre du mur 7
				new Polygon(new Point3D(30,-50,0), new Point3D(80,-50,0),
						new Point3D(80,-75,0), new Point3D(30,-75,0))
						.setEmission(new Color(200,149,76)),

				//pierre du mur 8
				new Polygon(new Point3D(80,-50,0), new Point3D(100,-50,0),
						new Point3D(100,-75,0), new Point3D(80,-75,0))
						.setEmission(new Color(208,164,101)),

				//pierre du mur 9
				new Polygon(new Point3D(-40,-25,0), new Point3D(-20,-25,0),
						new Point3D(-20,-50,0), new Point3D(-40,-50,0))
						.setEmission(new Color(200,149,76)),

				//pierre du mur 10
				new Polygon(new Point3D(-20,-25,0), new Point3D(10,-25,0),
						new Point3D(10,-50,0), new Point3D(-20,-50,0))
						.setEmission(new Color(204,156,89)),

				//pierre du mur 11
				new Polygon(new Point3D(10,-25,0), new Point3D(50,-25,0),
						new Point3D(50,-50,0), new Point3D(10,-50,0))
						.setEmission(new Color(214,175,120)),

				//pierre du mur 12
				new Polygon(new Point3D(50,-25,0), new Point3D(100,-25,0),
						new Point3D(100,-50,0), new Point3D(50,-50,0))
						.setEmission(new Color(206,160,95)),

				//pierre du mur 13
				new Polygon(new Point3D(-40,0,0), new Point3D(10,0,0),
						new Point3D(10,-25,0), new Point3D(-40,-25,0))
						.setEmission(new Color(212,171,114)),

				//pierre du mur 14
				new Polygon(new Point3D(10,0,0), new Point3D(30,0,0),
						new Point3D(30,-25,0), new Point3D(10,-25,0))
						.setEmission(new Color(202,152,82)),

				//pierre du mur 15
				new Polygon(new Point3D(30,0,0), new Point3D(60,0,0),
						new Point3D(60,-25,0), new Point3D(30,-25,0))
						.setEmission(new Color(208,164,101)),

				//pierre du mur 16
				new Polygon(new Point3D(60,0,0), new Point3D(100,0,0),
						new Point3D(100,-25,0), new Point3D(60,-25,0))
						.setEmission(new Color(210,167,107))

		);


		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(60, 50, 0), new Vector(0, 0, -1)) //
				.setKL(4E-5).setKQ(2E-7));

		ImageWriter imageWriter = new ImageWriter("producingPicture", 600, 600);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTracerBase(new RayTracerBasic(scene));

		render.renderImage();
		render.writeToImage();
	}
}
