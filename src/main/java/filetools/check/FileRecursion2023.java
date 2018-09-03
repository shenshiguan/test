package filetools.check;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import org.ssg.utils.OssUtil;

import filetools.log.LogOut;

/***
 * 查询所有文件夹下 文件的数量 是否是2
 * 
 * @author Administrator
 *
 */
public class FileRecursion2023 {
	static LogOut logout = new LogOut("D:/insertbatch230601.sql");
	static long count = 0;

	static String DIRECTORY = "";
	public static void main(String[] args) {
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
				if(files.length==1&&!files[0].isDirectory()&&(files[0].getName().toLowerCase().endsWith(".jpg")
						||files[0].getName().toLowerCase().endsWith(".gif")||files[0].getName().toLowerCase().endsWith(".png"))){
					count++;
					String fileType = files[0].getName().substring(files[0].getName().length()-4);
					String fileName = UUID.randomUUID().toString()+""+fileType;
					String file_url = files[0].getParentFile().getAbsolutePath()+"/"+fileName;
//					System.out.println(file_url);
					files[0].renameTo(new File(file_url));
					
					
				}
				for(File file :files){
					
//					if(file.isDirectory()){
//						if(file.getAbsolutePath().endsWith(".doc")||file.getAbsolutePath().endsWith(".pdf")){
//							System.out.println("********************");
//							System.out.println(file.getAbsolutePath());
//							System.exit(0);
//						}
//					}
					
					recursionDirectory(file);
				}
			}else{
//				delFile_rar(file_1);
//				delFile_db(file_1);
//				countNotPdf(file_1);
//				countWord(file_1);
//				DOC2doc(file_1);
//				doc3pdf(file_1);
//				countPdf(file_1);
				
				//首页
//				mkindex(file_1);
				
				//文件夹只有一个图片的文件夹
				
//				uuid
				
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
//			System.exit(0);
		}
	}
	static void dirOneJpg(File file_1){
//		if()
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
				
//				e.printStackTrace();
			}
		}
	}
}