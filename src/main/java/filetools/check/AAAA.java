package filetools.check;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecodeParam;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.PNGEncodeParam;
import com.sun.media.jai.codec.TIFFDecodeParam;

/**
 * 将多张图片合并转为PDF；需要用到iTextpdf包，
 * 
 * @author ssg
 * 
 */
public class AAAA {
    
	public static void saveTifFile(InputStream inputStream) {
	     ImageDecodeParam imageDecodeParam = new TIFFDecodeParam();
	     PNGEncodeParam pngEncodeParam = new PNGEncodeParam.RGB();
	     ImageDecoder imageDecoder = ImageCodec.createImageDecoder("tiff", inputStream, imageDecodeParam);
	     int size = 0;
	     try {
	         size = imageDecoder.getNumPages();
	         System.out.println(size);
	         long date = new Date().getTime();
//	         String filePath = filestoreConfig.getPath() + "/tifFile/" + date;
	         
	         String filePath = "c:\\bbb\\";
	         
	         File file = new File(filePath);
	         if (!file.exists()) {
	             file.mkdirs();
	         }
	         for (int i = 0; i < size; i++) {
	             RenderedImage renderedImage = imageDecoder.decodeAsRenderedImage(i);
	             ParameterBlock args = new ParameterBlock();
	             String id = UUID.randomUUID().toString();
	             String path = filePath + "/" + (i + 1);
	             File file2 = new File(path);
	             args.addSource(renderedImage);
	             args.add(file2.toString());
	             args.add("PNG");
	             args.add(pngEncodeParam);
	             RenderedOp r = JAI.create("filestore", args);
	             r.dispose();
	         }
	     } catch (IOException e) {
	         e.printStackTrace();
	     }
	 }	
	
	
	/**
     * 
     * @param imageFolderPath
     *            图片文件夹地址
     * @param pdfPath
     *            PDF文件保存地址
     * 
     */
    public static void toPdf(String imageFolderPath, String pdfPath) {
        try {
            // 图片文件夹地址
            // String imageFolderPath = "D:/Demo/ceshi/";
            // 图片地址
            String imagePath = null;
            // PDF文件保存地址
            // String pdfPath = "D:/Demo/ceshi/hebing.pdf";
            // 输入流
            FileOutputStream fos = new FileOutputStream(pdfPath);
            // 创建文档
            Document doc = new Document(null, 0, 0, 0, 0);
            doc.open();
            // 写入PDF文档
            PdfWriter.getInstance(doc, fos);
            // 读取图片流
            BufferedImage img = null;
            // 实例化图片
            Image image = null;
            // 获取图片文件夹对象
            
//            orderByName(imageFolderPath);
            
            File file = new File(imageFolderPath);
            File[] files = file.listFiles();
            List<FileString> lists = new ArrayList<FileString>();
//            
            for(File file1:files){
            	lists.add(new FileString(file1.getAbsolutePath()));
            }
//            
//            
            Collections.sort(lists);
            
            // 循环获取图片文件夹内的图片
            for (FileString file1 : lists) {
                if (file1.toString().toLowerCase().endsWith(".png")
                        || file1.toString().toLowerCase().endsWith(".jpg")
                        || file1.toString().toLowerCase().endsWith(".gif")
                        || file1.toString().toLowerCase().endsWith(".jpeg")
                      || file1.toString().toLowerCase().endsWith(".tif")
                        || file1.toString().toLowerCase().endsWith(".bmp")) {
                    System.out.println(file1);
                    imagePath = file1.toString();
                    System.out.println(imagePath);
                    
           		 	NarrowImage.writeHighQuality(NarrowImage.zoomImage(imagePath), imagePath);
                    
                    // 读取图片流
                    File ff = new File(imagePath);
                    
                    img = ImageIO.read(ff);

                    // 根据图片大小设置文档大小
                    doc.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
//                  doc.setPageSize(new Rectangle(600, 760));
//                  doc.setPageSize(new Rectangle(756, 1042));
                    // 实例化图片
                    image = Image.getInstance(imagePath);
                    // 添加图片到文档
                    doc.open();
                    doc.add(image);
                }
            }
            // 关闭文档
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
    
	public static void main(String[] args) throws FileNotFoundException {
		File file  =  new File("C:\\aaa\\河南师范大学632基础日语（A卷）2013年考研真题.tif");
		saveTifFile(new FileInputStream(file));
	}
   
    
//    public static void main(String[] args) {
//        long time1 = System.currentTimeMillis();
//        System.out.println("========================");
////      toPdf("E:/已核对历年真题/河海大学/俄语（二外）2005/", "E:/已核对历年真题/河海大学/俄语（二外）2005/俄语（二外）2005.pdf");
//        toPdf("C:/aaa/", "C:/aaa/"+UUID.randomUUID().toString()+".pdf");       
//        
////      toPdf("D:/用户目录/我的图片/梦幻壁纸/", "D:/用户目录/我的图片/梦幻壁纸/123.pdf");
//        long time2 = System.currentTimeMillis();
//        int time = (int) ((time2 - time1)/1000);
//        System.out.println("执行了："+time+"秒！");
//
//    }
    
}