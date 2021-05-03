package unittests;
import java.awt.Color;
import org.junit.jupiter.api.Test;
import renderer.ImageWriter;
import primitives.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest extends Object {

    @Test
    void testImageWriter() {
        ImageWriter iw=new ImageWriter("image", 800,500);
        primitives.Color blue= new primitives.Color(0,0,255);
        for(int i=0;i<800;i++)
            for(int j=0;j<500;j++){
                iw.writePixel(i,j, blue);
            }
        iw.writeToImage();
    }

}