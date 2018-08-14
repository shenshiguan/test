package filetools.check;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Image;

public class ImageReduce {

    public static void reduce(String imagePath_old, String imagePath_new) {
        try {
            String imagePath = null;
            // 读取图片流
            BufferedImage img = null;
            // 实例化图片
            Image image = null;
            // 获取图片文件夹对象
            File ff = new File(imagePath_old);
                    
            img = ImageIO.read(ff);
            
//          img = resize(img,600, 760);
            System.out.println(img == null);
            image = Image.getInstance(imagePath);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadElementException e) {
            e.printStackTrace();
        }
    }    
    
    public static void main(String[] args) throws IOException {
    	File ff = new File("D:\\首都师范大学1\\首都师范大学(333)教育综合2012年真题\\1.jpg");
    	BufferedImage img = ImageIO.read(ff);
    	BufferedImage result = null;

    	int toWidth = img.getWidth();
    	int toHeight= img.getHeight();
    	
		/* 新生成结果图片 */
		result = new BufferedImage(toWidth, toHeight,
				BufferedImage.TYPE_INT_RGB);
		result.getGraphics().drawImage(
				img.getScaledInstance(toWidth, toHeight,
						java.awt.Image.SCALE_SMOOTH), 0, 0, null);
		

    	
    }
}
