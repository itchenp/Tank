import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ImageTest {

    @Test
    public void test(){

        try {
            BufferedImage image1 = ImageIO.read(new FileInputStream("src/main/resources/images/tankL.gif"));
            assertNotNull(image1);
            System.out.println("==============");
            BufferedImage image2 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("images/tankL.gif"));
            assertNotNull(image2);
        }catch (Exception e){
            System.out.println(e);
        }

    }

}
