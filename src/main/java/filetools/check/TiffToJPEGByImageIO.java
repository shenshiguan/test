package filetools.check;
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.IOException;  
  
import javax.imageio.ImageIO;  
import javax.imageio.ImageReader;  
import javax.imageio.stream.ImageInputStream; 


public class TiffToJPEGByImageIO {
    public static void main(String[] args){
    	File file = new File("E:/已核对历年真题/北京工业大学/水力学2003/");
    	if(file.isDirectory()){
    		for(File ff:file.listFiles()){
    			tiffToJPEGByImageIO(ff.getAbsolutePath());  
    		}
    		
    	}
    	
    	
//    	tiffToJPEGByImageIO("E:\\已核对历年真题\\复旦大学\\社会学概论2000\\社会学概论2000.tif");  
    }  
  
    private static void tiffToJPEGByImageIO(String tiff) {  
        ImageInputStream input;  
        try {  
            input = ImageIO.createImageInputStream(new File(tiff));//以图片输入流形式读取到tif  
            // Get the reader  
            ImageReader reader = ImageIO.getImageReaders(input).next();//获得image阅读器，阅读对象为tif文件转换的流  
            String path,tiffName;  
            path = tiff.substring(0, tiff.lastIndexOf("."));  
            tiffName = tiff.substring(tiff.lastIndexOf("\\"),tiff.lastIndexOf("."));  
            try {  
                reader.setInput(input);  
                // Read page 2 of the TIFF file  
                int count = reader.getNumImages(true);//tif文件页数  
                //System.out.println(count);  
                for(int i = 0; i < count; i++){  
                    BufferedImage image = reader.read(i, null);//取得第i页  
                    File f = new File(path,"\\"+tiffName+"_"+i+".jpg");  
                    try {  
                        if(!f.exists()){  
                            f.getParentFile().mkdirs();  
                            f.createNewFile();  
                        }  
                    } catch (IOException e) {  
                        e.printStackTrace();  
                    }  
                    ImageIO.write(image, "JPEG", f);//保存图片  
                }  
            }  
            finally {  
                reader.dispose();  
                input.close();  
            }  
        }catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}
