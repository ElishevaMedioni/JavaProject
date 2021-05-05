package unittests;
import org.junit.jupiter.api.Test;
import primitives.Color;
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
                if(i%50==0 || j%50==0)
                    iw.writePixel(i,j, Color.BLACK);
                else
                    iw.writePixel(i,j, blue);
            }
        iw.writeToImage();
    }

}