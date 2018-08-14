package filetools.check;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.awt.Image;  
import java.awt.Toolkit;  
import java.awt.image.BufferedImage;  
import java.awt.image.MemoryImageSource;  
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;  
import java.io.IOException;  
import com.sun.image.codec.jpeg.JPEGCodec;  
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.JPEGEncodeParam;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
public class ImageTools {

	public static void main(String[] args) throws IOException {
//		png2jpg("E:/已核对历年真题/首都师范大学/毛泽东思想、邓小平理论与三个代表重要思想2007/0fbd33fb-06b2-46f0-b822-32850b8d8e04.png","E:/已核对历年真题/首都师范大学/毛泽东思想、邓小平理论与三个代表重要思想2007/0fbd33fb-06b2-46f0-b822-32850b8d8e04.jpg");
	
//		gif2jpg("E:/已核对历年真题/上海大学/货币银行学与国际金融1998/99bb7821-8c0e-4ecd-a51a-9bbc9004c341.gif","E:/已核对历年真题/上海大学/货币银行学与国际金融1998/99bb7821-8c0e-4ecd-a51a-9bbc9004c341.jpg");  
		bmp2jpeg("E:/已核对历年真题/首都经济贸易大学/法学综合2001/法学综合2001.bmp", "E:/已核对历年真题/首都经济贸易大学/法学综合2001/法学综合2001.jpg");
	}
	
	
	public static void png2jpg(String pngPath,String jpgPath) {
	    BufferedImage bufferedImage;
	    try {
	      //read image file
	      bufferedImage = ImageIO.read(new File(pngPath));
	      // create a blank, RGB, same width and height, and a white background
	      BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),
	            bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
	      //TYPE_INT_RGB:创建一个RBG图像，24位深度，成功将32位图转化成24位
	      newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);
	      // write to jpeg file
	      ImageIO.write(newBufferedImage, "jpg", new File(jpgPath));
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	}
	
//    public static void gif2jpg(String gifPath, String jpgPath, int quality) {    
//        if (jpgPath == null || jpgPath.trim().equals(""))  
//        	jpgPath = gifPath;  
//        if (!jpgPath.toLowerCase().trim().endsWith("jpg")) {  
//        	jpgPath += ".jpg";  
//            System.out.println("Overriding to JPG, output file: " + jpgPath);  
//        }
//        if (quality < 0 || quality > 100 || (quality + "") == null || (quality + "").equals("")) {  
//            quality = 75;  
//        }   
//        try {  
//            JPGOptions options = new JPGOptions();  
//            options.setQuality(quality);  
//            ImageProducer image = Jimi.getImageProducer(gifPath);  
//            JimiWriter writer = Jimi.createJimiWriter(jpgPath);  
//            writer.setSource(image);  
//            writer.setOptions(options);  
//            writer.putImage(jpgPath);  
//        } catch (JimiException je) {  
//            System.err.println("Error: " + je);  
//        }  
//    }  
  	
    public static void gif2jpg(String gifPath, String jpgPath) throws IOException { 
        OutputStream out = new FileOutputStream(jpgPath);
        ImageIO.write(ImageIO.read(new File(gifPath)),"jpg",out);
        out.close();
    }
    /** 
     * Created on 2010-7-13  
     * <p>Discription:[bmpTojpg]</p> 
     * @param file 
     * @param dstFile 
     */  
    public static void bmp2jpg(String file,String dstFile)  
    {  
        try  
        {  
        	BufferedImage bfi = ImageIO.read(new File(file));
        	
            FileInputStream in = new FileInputStream(file);  
            Image TheImage = read(in);  
            System.out.println(TheImage==null);
//            int wideth = TheImage.getWidth(null);  
//            int height = TheImage.getHeight(null); 
            
            int wideth = bfi.getWidth();
            int height = bfi.getHeight();          
            
            BufferedImage tag = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);  
            tag.getGraphics().drawImage(TheImage, 0, 0, wideth, height, null);  
            FileOutputStream out = new FileOutputStream(dstFile);  
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
            encoder.encode(tag);  
            out.close();  
        }  
        catch (Exception e)  
        {  
        	e.printStackTrace();
            System.out.println(e);  
        }  
    }  
    public static int constructInt(byte[] in, int offset)  
    {  
        int ret = ((int) in[offset + 3] & 0xff);  
        ret = (ret << 8) | ((int) in[offset + 2] & 0xff);  
        ret = (ret << 8) | ((int) in[offset + 1] & 0xff);  
        ret = (ret << 8) | ((int) in[offset + 0] & 0xff);  
        return (ret);  
    }  
    public static int constructInt3(byte[] in, int offset)  
    {  
        int ret = 0xff;  
        ret = (ret << 8) | ((int) in[offset + 2] & 0xff);  
        ret = (ret << 8) | ((int) in[offset + 1] & 0xff);  
        ret = (ret << 8) | ((int) in[offset + 0] & 0xff);  
        return (ret);  
    }  
    public static long constructLong(byte[] in, int offset)  
    {  
        long ret = ((long) in[offset + 7] & 0xff);  
        ret |= (ret << 8) | ((long) in[offset + 6] & 0xff);  
        ret |= (ret << 8) | ((long) in[offset + 5] & 0xff);  
        ret |= (ret << 8) | ((long) in[offset + 4] & 0xff);  
        ret |= (ret << 8) | ((long) in[offset + 3] & 0xff);  
        ret |= (ret << 8) | ((long) in[offset + 2] & 0xff);  
        ret |= (ret << 8) | ((long) in[offset + 1] & 0xff);  
        ret |= (ret << 8) | ((long) in[offset + 0] & 0xff);  
        return (ret);  
    }  
    public static double constructDouble(byte[] in, int offset)  
    {  
        long ret = constructLong(in, offset);  
        return (Double.longBitsToDouble(ret));  
    }  
    public static short constructShort(byte[] in, int offset)  
    {  
        short ret = (short) ((short) in[offset + 1] & 0xff);  
        ret = (short) ((ret << 8) | (short) ((short) in[offset + 0] & 0xff));  
        return (ret);  
    }  
    static class BitmapHeader  
    {  
        public int iSize, ibiSize, iWidth, iHeight, iPlanes, iBitcount, iCompression, iSizeimage, iXpm, iYpm, iClrused, iClrimp;  
        // 读取bmp文件头信息  
        public void read(FileInputStream fs) throws IOException  
        {  
            final int bflen = 14;  
            byte bf[] = new byte[bflen];  
            fs.read(bf, 0, bflen);  
            final int bilen = 40;  
            byte bi[] = new byte[bilen];  
            fs.read(bi, 0, bilen);  
            iSize = constructInt(bf, 2);  
            ibiSize = constructInt(bi, 2);  
            iWidth = constructInt(bi, 4);  
            iHeight = constructInt(bi, 8);  
            iPlanes = constructShort(bi, 12);  
            iBitcount = constructShort(bi, 14);  
            iCompression = constructInt(bi, 16);  
            iSizeimage = constructInt(bi, 20);  
            iXpm = constructInt(bi, 24);  
            iYpm = constructInt(bi, 28);  
            iClrused = constructInt(bi, 32);  
            iClrimp = constructInt(bi, 36);  
        }  
    }  
    public static Image read(FileInputStream fs)  
    {  
        try  
        {  
            BitmapHeader bh = new BitmapHeader();  
            bh.read(fs);  
            System.out.println(bh.iBitcount);
            if (bh.iBitcount == 24)  
            {  
                return (readImage24(fs, bh));  
            }  

            if (bh.iBitcount == 32)  
            {  
                return (readImage32(fs, bh));  
            }  
            fs.close();  
        }  
        catch (IOException e)  
        {  
            System.out.println(e);  
        }  
        return (null);  
    }  
    //24位  
    protected static Image readImage24(FileInputStream fs, BitmapHeader bh) throws IOException  
    {  
        Image image;  
        if (bh.iSizeimage == 0)  
        {  
            bh.iSizeimage = ((((bh.iWidth * bh.iBitcount) + 31) & ~31) >> 3);  
            bh.iSizeimage *= bh.iHeight;  
        }  
        int npad = (bh.iSizeimage / bh.iHeight) - bh.iWidth * 3;  
        int ndata[] = new int[bh.iHeight * bh.iWidth];  
        byte brgb[] = new byte[(bh.iWidth + npad) * 3 * bh.iHeight];  
        fs.read(brgb, 0, (bh.iWidth + npad) * 3 * bh.iHeight);  
        int nindex = 0;  
        for (int j = 0; j < bh.iHeight; j++)  
        {  
            for (int i = 0; i < bh.iWidth; i++)  
            {  
                ndata[bh.iWidth * (bh.iHeight - j - 1) + i] = constructInt3(brgb, nindex);  
                nindex += 3;  
            }  
            nindex += npad;  
        }  
        image = Toolkit.getDefaultToolkit().createImage(  
                new MemoryImageSource(bh.iWidth, bh.iHeight, ndata, 0, bh.iWidth));  
        fs.close();  
        return (image);  
    }  
    //32位  
    protected static Image readImage32(FileInputStream fs, BitmapHeader bh) throws IOException  
    {  
        Image image;  
        int ndata[] = new int[bh.iHeight * bh.iWidth];  
        byte brgb[] = new byte[bh.iWidth * 4 * bh.iHeight];  
        fs.read(brgb, 0, bh.iWidth * 4 * bh.iHeight);  
        int nindex = 0;  
        for (int j = 0; j < bh.iHeight; j++)  
        {  
            for (int i = 0; i < bh.iWidth; i++)  
            {  
                ndata[bh.iWidth * (bh.iHeight - j - 1) + i] = constructInt3(brgb, nindex);  
                nindex += 4;  
            }  
        }  
        image = Toolkit.getDefaultToolkit().createImage(  
                new MemoryImageSource(bh.iWidth, bh.iHeight, ndata, 0, bh.iWidth));  
        fs.close();  
        return (image);  
    } 
    public static void bmp2jpeg(String inputFile,String outputFile) {    
        try {  
   
              
            /*bmp转换到jpg格式*/    
//            String inputFile = "E:/已核对历年真题/首都经济贸易大学/法学综合2001/法学综合2001.bmp";    
//            String outputFile = "E:/已核对历年真题/首都经济贸易大学/法学综合2001/法学综合2001.jpg";    
            RenderedOp src = JAI.create("fileload", inputFile);    
            OutputStream os = new FileOutputStream(outputFile);    
            JPEGEncodeParam param = new JPEGEncodeParam();   
            ImageEncoder enc = ImageCodec.createImageEncoder("JPEG", os,param);    
            enc.encode(src);    
            os.close();//关闭流    
              
            //其他的一样的方式转换  ...  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    } 
}
