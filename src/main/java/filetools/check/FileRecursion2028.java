package filetools.check;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;

import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.JPEGEncodeParam;

import filetools.log.LogOut;

/***
 * 对26的数据进行uuid重命名
 * 
 * @author Administrator
 *
 */
public class FileRecursion2028 {
	static LogOut logout = new LogOut("D:/insertbatch2018-06-14_del.sql");
	static long count = 0;

	static String DIRECTORY = "";
	public static void main(String[] args) {
		logout.fileChaseFW("=======================================================");
//		String filePath = "E:/已核对历年真题/华南理工大学/城市规划设计（做图）2005/信号与系统-2005/";
		String filePath = "E:/已核对历年真题/";
		File file = new File(filePath);
		if (file != null) {
			recursionDirectory(file);
		}
		System.out.println("共有pdf文件：" + count + "份.");
		try {
			logout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void recursionDirectory(File file_1){
		try {
			if(file_1.isDirectory()){
				File[] files = file_1.listFiles();
				if(files.length==0){
					System.out.println(file_1.getAbsolutePath());
					file_1.delete();
					
				}
				
				for(File file :files){
					if(file.isDirectory()){
//						if(file.getName().endsWith("大纲")){
//					forceDelete(file);
//						file.delete();
//						count++;
//						System.out.println(file.getAbsolutePath());
//						}
						boolean res =  true;
						if(file.getName().endsWith("（A）")){
							System.out.println(file.getAbsolutePath());
							res = file.renameTo(new File(file.getAbsolutePath().replaceAll("（A）", "")));
							if(!res){
								file.delete();
							}
							
						}else if(file.getName().endsWith("（B）")){
							System.out.println(file.getAbsolutePath());
							res = file.renameTo(new File(file.getAbsolutePath().replaceAll("（B）", "")));
							if(!res){
								file.delete();
							}
						}
						else
							recursionDirectory(file);
					}
					else{

						//tif
						try {
							if(file.getName().toLowerCase().endsWith(".tif")){
//								count++;
//								System.out.println(file.getAbsolutePath());
								String fileNamePath = file.getAbsolutePath().toLowerCase().replaceAll(".tif", ".jpg");
								RenderedOp src2 = JAI.create("fileload", file.getAbsolutePath()); 
								OutputStream os2 = new FileOutputStream(fileNamePath); 
								JPEGEncodeParam param2 = new JPEGEncodeParam(); 
								//指定格式类型，jpg 属于 JPEG 类型 
								ImageEncoder enc2 = ImageCodec.createImageEncoder("JPEG", os2, param2); 
								enc2.encode(src2); 
								os2.close();
								
								forceDelete(file);
							}
						} catch (Exception e) {
							System.out.println(file.getAbsolutePath());
						}
					}
				}
			}else{
//				findDirectoryLength4(file_1);
//				findDirectoryLength5(file_1);
//				findDirectoryLength6(file_1);
//				findDirectoryLength7(file_1);
//				findDirectoryLength8(file_1);
//				findDirectory(file_1);
//				deleteTiff(file_1);
//				pdfAndJpg(file_1);
//				delFile32(file_1);
				
				
				
				
				
				
				
				
				
				
			}
		} catch (Exception e) {
//			e.printStackTrace();
//			System.exit(0);
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
	
	private static void delFile32(File file_1) {
		String fileName = file_1.getName();
		if(fileName.length()>26){
			if(fileName.endsWith(".pdf")||fileName.endsWith(".jpg")){
				logout.fileChaseFW(file_1.getAbsolutePath());
				file_1.delete();
			}else{
//				logout.fileChaseFW(file_1.getAbsolutePath());
			}
		}
		
		
	}

	static void pdfAndJpg(File file_1){
		if(file_1.getName().endsWith(".pdf")){
			count++;
			String fileName_1 = file_1.getAbsolutePath().replaceAll(".pdf", ".jpg");
			boolean aa = false;
			for(File file:file_1.getParentFile().listFiles()){
				if(fileName_1.equals(file.getAbsolutePath().toString())){
					aa = true;
				}
			}
			if(!aa){
				System.out.println(file_1.getAbsolutePath());
				System.out.println(file_1.getParentFile().getAbsolutePath());
				if(file_1.length()==0){
					file_1.delete();
				}
			}
		}
	}
	
	static void deleteTiff(File file_1){
		if(file_1.getName().endsWith(".pdf")){
			String fileName_1 = file_1.getAbsolutePath().replaceAll(".pdf", ".jpg");
			for(File file:file_1.getParentFile().listFiles()){
				if(fileName_1.equals(file.getAbsolutePath().toString())){
					File[] files = file_1.getParentFile().listFiles();
					if(files.length>2){
//						String fileName_2 = file_1.getAbsolutePath().replaceAll(".pdf", ".tif");
//						for(File file1:file_1.getParentFile().listFiles()){
//							if(fileName_2.equals(file1.getAbsolutePath())){
//								System.out.println(file_1.getParentFile());
//								file1.delete();
//							}
//						}
					}
				}	
			}
		}
	}
	
	static void findDirectory(File file_1){
//		String[] strs = file_1.getAbsolutePath().toString().split("\\\\");
		logout.fileChaseFW(file_1.getAbsolutePath());
		System.out.println(file_1.getAbsolutePath());
		count++;
	}
	static void findDirectoryLength4(File file_1){
		String[] strs = file_1.getAbsolutePath().toString().split("\\\\");
		if(strs.length<4){
			logout.fileChaseFW(file_1.getAbsolutePath());
			System.out.println(file_1.getAbsolutePath());
		}
	}
	static void findDirectoryLength5(File file_1){
		String[] strs = file_1.getAbsolutePath().toString().split("\\\\");
		if(strs.length<5){
			logout.fileChaseFW(file_1.getAbsolutePath());
			System.out.println(file_1.getAbsolutePath());
		}
	}
	static void findDirectoryLength6(File file_1){
		String[] strs = file_1.getAbsolutePath().toString().split("\\\\");
		if(strs.length<6){
			logout.fileChaseFW(file_1.getAbsolutePath());
			System.out.println(file_1.getAbsolutePath());
		}
	}
	static void findDirectoryLength7(File file_1){
		String[] strs = file_1.getAbsolutePath().toString().split("\\\\");
		if(strs.length<7){
			logout.fileChaseFW(file_1.getAbsolutePath());
			System.out.println(file_1.getAbsolutePath());
		}
	}
	static void findDirectoryLength8(File file_1){
		String[] strs = file_1.getAbsolutePath().toString().split("\\\\");
		if(strs.length>6){
			logout.fileChaseFW(file_1.getAbsolutePath());
			System.out.println(file_1.getAbsolutePath());
		}
	}
	static String ossFile(String fileName,String filePath){
	    String osskey = "kuakao/filebank_batch1/" + fileName;
	    try {
			OssUtil.doUploadFileInputStream(osskey, new FileInputStream(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
	    String netUrl = OssUtil.getObjectUrl(osskey, 365*50);
	    return netUrl;
	}

	//删除rar文件
	static void delFile_rar(File file_1){
		if(file_1.getName().endsWith(".rar")){
			file_1.delete();
		}
	}
	//删除zip文件
	static void delFile_zip(File file_1){
		if(file_1.getName().endsWith(".zip")){
			file_1.delete();
		}
	}
	//删除ini文件
	static void delFile_ini(File file_1){
		if(file_1.getName().endsWith(".ini")){
			file_1.delete();
		}
	}
	//删除db文件
	static void delFile_db(File file_1){
		if(file_1.getName().endsWith(".db")){
			file_1.delete();
		}
	}
	//统计不是pdf的文件数量
	static void countNotPdf(File file_1){
		if(!file_1.getName().endsWith(".pdf")){
			System.out.println(file_1.getAbsolutePath());
			count++;
		}
	}
	//统计pdf的文件数量
	static void countPdf(File file_1){
		if(file_1.getName().endsWith(".pdf")){
//			System.out.println(file_1.getAbsolutePath());
			count++;
		}
	}	
	//统计word数量
	static void countWord(File file_1){
		if(file_1.getName().toLowerCase().endsWith(".doc")||file_1.getName().toLowerCase().endsWith(".docx")){
			System.out.println(file_1.getAbsolutePath());
			count++;
		}
	}
	//word 大小写文件名称统一
	static void DOC2doc(File file_1){
		if(file_1.getName().endsWith(".DOC")||file_1.getName().endsWith(".DOCX")){
			file_1.renameTo(new File(file_1.getAbsolutePath().replaceAll(".DOCX", ".docx").replaceAll(".DOC", ".doc")));
			System.out.println(file_1.getAbsolutePath());
			count++;
		}
	}
	//word转pdf
	static void doc3pdf(File file_1){
		if(file_1.getName().toLowerCase().endsWith(".doc")||file_1.getName().toLowerCase().endsWith(".docx")){
			String filePath = file_1.getAbsolutePath();
			String filePath1 = filePath.replaceAll(".docx", ".pdf").replaceAll(".doc", ".pdf");
			System.out.println(filePath);
			System.out.println(filePath1);
			
			File ff = new File(filePath1);
			if(!ff.exists()){
				boolean res = WordConvertPdf.wordToPDF(filePath, filePath1);
				System.out.println(res);
			}else{
				file_1.delete();
			}	
		}
	}
	
	static void mkindex(File file_1){
		if(file_1.getName().endsWith(".pdf")){
			System.out.println(file_1.getAbsolutePath());
			try {
				Pdf2Image.pdf2Image(file_1.getAbsolutePath());
			} catch (Exception e) {
				logout.fileChaseFW(file_1.getAbsolutePath());
				count++;
			}
		}
	}

	
	
	static void renameFile(File file_1){
		if(file_1.getAbsolutePath().endsWith(".JPG")){
			System.out.println(file_1.getAbsolutePath());
			file_1.renameTo(new File(file_1.getAbsolutePath().replaceAll(".JPG", ".jpg")));
		}
		else if(file_1.getAbsolutePath().endsWith(".PNG")){
			System.out.println(file_1.getAbsolutePath());
			file_1.renameTo(new File(file_1.getAbsolutePath().replaceAll(".PNG", ".png")));
		}
		else if(file_1.getAbsolutePath().endsWith(".GIF")){
			System.out.println(file_1.getAbsolutePath());
			file_1.renameTo(new File(file_1.getAbsolutePath().replaceAll(".GIF", ".gif")));
		}
		else if(file_1.getAbsolutePath().endsWith(".BMP")){
			System.out.println(file_1.getAbsolutePath());
			file_1.renameTo(new File(file_1.getAbsolutePath().replaceAll(".BMP", ".bmp")));
		}
	}
}