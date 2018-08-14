package filetools.check;

import java.io.File;
import java.io.FileOutputStream; 
import java.io.OutputStream; 

import javax.media.jai.JAI; 
import javax.media.jai.RenderedOp; 

import com.sun.media.jai.codec.BMPEncodeParam; 
import com.sun.media.jai.codec.ImageCodec; 
import com.sun.media.jai.codec.ImageEncoder; 
import com.sun.media.jai.codec.JPEGEncodeParam; 
public class Test {

	public static void main(String[] args) throws Exception {
		
		String imageFolderPath = "E:/已核对历年真题/复旦大学/社会学概论2000/";
		
		/* tif转换到jpg格式 */ 
//		String input2 = "d:/img/a.tif"; 
//		String output2 = "d:/img/a.jpg"; 
//		RenderedOp src2 = JAI.create("fileload", input2); 
//		OutputStream os2 = new FileOutputStream(output2); 
//		JPEGEncodeParam param2 = new JPEGEncodeParam(); 
//		//指定格式类型，jpg 属于 JPEG 类型 
//		ImageEncoder enc2 = ImageCodec.createImageEncoder("JPEG", os2, param2); 
//		enc2.encode(src2); 
//		os2.close();
		
//		/*tif转换到bmp格式*/ 
//		String inputFile = "d:/img/b.tif"; 
//		String outputFile = "d:/img/b.bmp"; 
//		RenderedOp src = JAI.create("fileload", inputFile); 
//		OutputStream os = new FileOutputStream(outputFile); 
//		BMPEncodeParam param = new BMPEncodeParam(); 
//		ImageEncoder enc = ImageCodec.createImageEncoder("BMP", os,param); 
//		enc.encode(src); 
//		os.close();//关闭流 
		
		//其他的一样的方式转换 
	
        File file = new File(imageFolderPath);
        File[] files = file.listFiles();
        for(File file1:files){
        	if(file1.getName().endsWith(".tif")||file1.getName().endsWith(".TIF")){
        		String fileNamePath = file1.getAbsolutePath().toLowerCase().replaceAll(".tif", ".jpg");
        		RenderedOp src2 = JAI.create("fileload", file1.getAbsolutePath()); 
        		OutputStream os2 = new FileOutputStream(fileNamePath); 
        		JPEGEncodeParam param2 = new JPEGEncodeParam(); 
        		//指定格式类型，jpg 属于 JPEG 类型 
        		ImageEncoder enc2 = ImageCodec.createImageEncoder("JPEG", os2, param2); 
        		enc2.encode(src2); 
        		os2.close();
        	}
        }
//        Thread.sleep(9000);
        for(File file1:files){
        	if(file1.getName().endsWith(".tif")||file1.getName().endsWith(".TIF")){
        		forceDelete(file1);
        	}
        	
        }
		
		
	}
	public static boolean forceDelete(File f)    
    {    
        boolean result = false;    
        int tryCount = 0;    
        while(!result && tryCount++ <10)    
        {         
        System.gc();    
        result = f.delete();    
        }    
        return result;    
    }  
}
