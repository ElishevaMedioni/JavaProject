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
	public void FinalPicture1() {
		Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(200, 200).setDistance(1000);
		scene.background=new Color(5,5,94);
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.geometries.add( //
				/*//door
				new Polygon(new Point3D(-80,-50,-500), new Point3D(-60,-50,-500),
						new Point3D(-60,-100,-500), new Point3D(-80,-100,-500))
						.setEmission(new Color(155,9,9)),

				//windows 1 left
				new Polygon(new Point3D(-95,-10,-500), new Point3D(-75,-10,-500),
						new Point3D(-75,-30,-500), new Point3D(-95,-30,-500))
						.setEmission(new Color(224,224,224))
						.setMaterial(new Material().setNShininess(30).setKT(1).setKR(1)),

				//windows 2 right
				new Polygon(new Point3D(-65,-10,-500), new Point3D(-45,-10,-500),
						new Point3D(-45,-30,-500), new Point3D(-65,-30,-500))
						.setEmission(new Color(224,224,224))
						.setMaterial(new Material().setNShininess(30).setKT(1).setKR(1)),

				//house
				new Polygon(new Point3D(-100,0,-500), new Point3D(-40,0,-500),
						new Point3D(-40,-100,-500), new Point3D(-100,-100,-500))
				.setEmission(new Color(219,170,120)),*/

				//house
				new Polygon(new Point3D(-70,20,-100), new Point3D(-30,20,-100),
						new Point3D(-30,-20,-100), new Point3D(-70,-20,-100))
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

	/**
	 * Produce a picture
	 */
	@Test
	public void FinalPicture() {
		Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(200, 200).setDistance(1000);
		scene.background=new Color(25,25,112);
		//scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
		new SpotLight(new Color(400, 240, 0), new Point3D(-100, -100, 200), new Vector(1, 1, -3)) //
				.setKL(1E-5).setKQ(1.5E-7);
		scene.geometries.add( //
				//lune
				new Sphere(new Point3D(0,-110,0),100.0)
						.setEmission(new Color(255,213,94))
						.setMaterial(new Material().setKD(0.8).setKS(0.8).setNShininess(200).setKT(0).setKR(0.7)),
				//tete
				new Sphere(new Point3D(-16.5, 28.5,0), 8.5)
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setKR(0.5)),
				//pied droit
				new Polygon(new Point3D(-13,0,0), new Point3D(-10,0,0),
						new Point3D(-10,-15,0), new Point3D(-13,-15,0))
						.setEmission(new Color(255,255,255)),
				//pied gauche
				new Polygon(new Point3D(-23,0,0), new Point3D(-20,0,0),
						new Point3D(-20,-15,0), new Point3D(-23,-15,0))
						.setEmission(new Color(255,255,255)),
				//corp
				new Polygon(new Point3D(-25,0,0), new Point3D(-8,0,0),
						new Point3D(-8,20,0), new Point3D(-25,20,0))
						.setEmission(new Color(255,255,255)),
				//bras droit
				new Triangle(new Point3D(-8,20,0), new Point3D(-5,4,0),
						new Point3D(-8,4,0))
						.setEmission(new Color(255,255,255)),
				//bras gauche
				new Triangle(new Point3D(-25,20,0), new Point3D(-25,4,0),
						new Point3D(-28,4,0))
						.setEmission(new Color(255,255,255)),
				//mars
				new Sphere(new Point3D(-50,50,200), 10d)
							.setEmission(new Color(188,39,50)),
				//etoile jaune
				new Sphere(new Point3D(75,75,0), 0.7d)
							.setEmission(new Color(255,255,0)),
				//etoile jaune
				new Sphere(new Point3D(48,70,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//etoile jaune
				new Sphere(new Point3D(-75,50,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//etoile jaune
				new Sphere(new Point3D(35,47,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//etoile jaune
				new Sphere(new Point3D(89,95,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//etoile jaune
				new Sphere(new Point3D(-89,-43,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//etoile jaune
				new Sphere(new Point3D(10,-24,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//etoile jaune
				new Sphere(new Point3D(-50,-39,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//etoile jaune
				new Sphere(new Point3D(10,-75,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//etoile jaune
				new Sphere(new Point3D(-75,10,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//etoile jaune
				new Sphere(new Point3D(-50,40,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//etoile jaune
				new Sphere(new Point3D(-10,85,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//etoile jaune
				new Sphere(new Point3D(90,-10,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//etoile jaune
				new Sphere(new Point3D(85,20,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//etoile jaune
				new Sphere(new Point3D(20,10,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//etoile jaune
				new Sphere(new Point3D(50,50,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//etoile jaune
				new Sphere(new Point3D(65,75,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//etoile jaune
				new Sphere(new Point3D(35,66,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//etoile jaune
				new Sphere(new Point3D(40,-25,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//etoile jaune
				new Sphere(new Point3D(78,10,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//etoile jaune
				new Sphere(new Point3D(-34,78,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//etoile jaune
				new Sphere(new Point3D(50,0,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//etoile jaune
				new Sphere(new Point3D(-80,80,0), 0.7d)
						.setEmission(new Color(255,255,0)),
				//etoile jaune
				new Sphere(new Point3D(-15,50,0), 0.7d)
						.setEmission(new Color(255,255,0))
		);

		//etoile
		for(double i=42; i>31;i-=1.5)
			for(double j=8.5;j<25;j+=2)
				scene.geometries.add(new Sphere(new Point3D(j, i, 0.5),0.5 )
						.setEmission(new Color(255,255,255)));
		scene.geometries.add( //
				//barre noir drapeau
				new Polygon(new Point3D(7,43,0), new Point3D(6,43,0),
						new Point3D(6,-15,0),new Point3D(7,-15,0))
							.setEmission(Color.BLACK),
				//carre bleu du drapeau
				new Polygon(new Point3D(7,43,0), new Point3D(25, 43, 0),
						new Point3D(25, 31, 0), new Point3D(7, 31, 0))
						.setEmission(new Color(0,0,255)),
				//bandes rouge 1
				new Polygon(new Point3D(25, 43, 0), new Point3D(43, 43, 0),
						new Point3D(43, 41, 0), new Point3D(25, 41, 0))
						.setEmission(new Color(255,0,0)),
				//bandes rouge 2
				new Polygon(new Point3D(25, 39, 0), new Point3D(43, 39, 0),
						new Point3D(43, 37, 0), new Point3D(25, 37, 0))
						.setEmission(new Color(255,0,0)),
				//bandes rouge 3
				new Polygon(new Point3D(25, 35, 0), new Point3D(43, 35, 0),
						new Point3D(43, 33, 0), new Point3D(25, 33, 0))
						.setEmission(new Color(255,0,0)),
				//bandes rouge 4
				new Polygon(new Point3D(7, 31, 0), new Point3D(43, 31, 0),
						new Point3D(43, 29, 0), new Point3D(7, 29, 0))
						.setEmission(new Color(255,0,0)),
				//bandes rouge 5
				new Polygon(new Point3D(7, 27, 0), new Point3D(43, 27, 0),
						new Point3D(43, 25, 0), new Point3D(7, 25, 0))
						.setEmission(new Color(255,0,0)),
				//bandes rouge 6
				new Polygon(new Point3D(7, 23, 0), new Point3D(43, 23, 0),
						new Point3D(43, 21, 0), new Point3D(7, 21, 0))
						.setEmission(new Color(255,0,0)),
				//bandes rouge 7
				new Polygon(new Point3D(7, 19, 0), new Point3D(43, 19, 0),
						new Point3D(43, 17, 0), new Point3D(7, 17, 0))
						.setEmission(new Color(255,0,0)),
				//drapeau
				new Polygon(new Point3D(7, 17, 0), new Point3D(7, 43, 0),
						new Point3D(43, 43, 0), new Point3D(43, 17, 0))
						.setEmission(new Color(255,255,255))

		);


		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(60, 50, 0), new Vector(0, 0, -1)) //
				.setKL(4E-5).setKQ(2E-7));

		ImageWriter imageWriter = new ImageWriter("moon", 600, 600);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTracerBase(new RayTracerBasic(scene));

		render.renderImage();
		render.writeToImage();
	}
}
