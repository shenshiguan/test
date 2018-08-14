package filetools.check;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import filetools.log.LogOut;

/***
 * 查询所有文件夹下 文件的数量 是否是2
 * 
 * @author Administrator
 *
 */
public class FileRecursion2024 {
	static LogOut logout = new LogOut("D:/insertbatch23.sql");
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
				
				/***
				for(File file :files){
					if(!file.isDirectory()){
//						count++;
						return ;
					}
				}
				***/
				for(File file :files){
					
					/***
					 * 

					//查询.doc或者.pdf结尾的文件夹
					if(file.isDirectory()){
						if(file.getAbsolutePath().endsWith(".doc")||file.getAbsolutePath().endsWith(".pdf")){
							System.out.println("********************");
							System.out.println(file.getAbsolutePath());
//							System.exit(0);
						}
					}
					***/

					/***
					 * 删除 空文件夹
					

					***/
					
					if(file.listFiles().length==0){
						System.out.println(file.getAbsolutePath());
						count++;
						file.delete();
					}
					
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
//				BMP2bmp(file_1);
				
				
				
				
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
//			System.exit(0);
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
			System.out.println(file_1.getAbsolutePath());
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
	
	//BMP--->bmp
	static void BMP2bmp(File file_1){
		if(file_1.getName().endsWith(".BMP")){
			file_1.renameTo(new File(file_1.getAbsolutePath().replaceAll(".BMP", ".bmp")));
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
				
			}
		}

	}	
}