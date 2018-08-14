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
public class FileRecursion2025 {
	static LogOut logout = new LogOut("D:/insertbatch2018-06-06_02.sql");
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
				if(files.length==1&&!files[0].isDirectory()&&(files[0].getName().toLowerCase().endsWith(".jpg")||files[0].getName().toLowerCase().endsWith(".jpeg")
						||files[0].getName().toLowerCase().endsWith(".gif")||files[0].getName().toLowerCase().endsWith(".png"))){
					
					count++;
					String[] sources = files[0].getAbsolutePath().toString().split("\\\\");

					System.out.println(sources[2]);//学校
//					
					
					String fileType = files[0].getName().substring(files[0].getName().length()-3);
					
					String school = sources[2];
					String subject =  "";
					String rfileName = "";
					String fileType2 ="1";//真题
					
//					System.out.println(sources[3]);//科目年份
					String subject_year =  "";
					if(sources.length==7){
						subject_year =  sources[5];
						rfileName= sources[6];
					}else{
						subject_year =  sources[3];
						rfileName= sources[3];
					}
					

					
					subject_year = subject_year.trim().replaceAll("A", "").replaceAll("B", "").replaceAll("（1）", "").
							replaceAll("（A卷）", "").replaceAll("（A）", "").replaceAll("I", "");
					if(subject_year.lastIndexOf("，")>0){
						subject_year = subject_year.substring(0, subject_year.lastIndexOf("，"));
					}
					if(subject_year.endsWith("）")){
						if(subject_year.lastIndexOf("（")>0){
							subject_year = subject_year.substring(0, subject_year.lastIndexOf("（"));
						}
					}
					
					subject_year = subject_year.trim().replaceAll("（）", "");
					String fileType1 = "真题";
					if(subject_year.endsWith("答案")){
						subject_year = subject_year.trim().replaceAll("答案", "");
						fileType1 = "答案";
						fileType2 = "2";
					}
					if(subject_year.endsWith("复试")){
						subject_year = subject_year.trim().replaceAll("复试", "");
						fileType1 = "真题";
					}
					String year_str  = subject_year.substring(subject_year.length()-4, subject_year.length());
					subject = subject_year.substring(0, subject_year.length()-4);
					
					
					System.out.println(subject);
					int year = 0;
					try {
						year = Integer.parseInt(year_str);
						if(year>=1980&&year<=2018){
							
							String jpgUrl = "";
							//上传jpg
							jpgUrl = ossFile(files[0].getName(),files[0].getAbsolutePath());
							
							long fileLength = files[0].length();
							
							String sql = "INSERT INTO `kuakao_live`.`paper_file` (`file_name`, `school`, `subject`, `cover_url`, `net_url`, "
									+ "	`local_url`, `file_size`, `file_type`, `path3`, `path4`, `file_year`, `filebank_type_id`, `is_filebank`, "
									+ "	`local_url1`, `pdf_url`) "
									+ "	VALUES ('"+files[0].getName()+"', '"+school+"', '"+subject+"', "
									+ "	'"+jpgUrl+"', '"+jpgUrl+"', '"+files[0].getName()+"', '"+fileLength+"', "
									+ "	'"+fileType+"', '试卷', '"+fileType1+"', '"+year+"', '"+fileType2+"', '1', '"+files[0].getName()+"', '"+jpgUrl+"');";
							System.out.println(sql);
							logout.fileChaseFW(sql);
							
							
						}else{
							System.out.println(year_str);
							System.out.println(subject_year);
//							logout.fileChaseFW(files[0].getAbsolutePath());
						}
					} catch (Exception e) {
						System.out.println(year_str);
						System.out.println(subject_year);
//						logout.fileChaseFW(files[0].getAbsolutePath());
					}
					
//					if(sources.length<5){
//						System.out.println(sources[3]);
//						logout.fileChaseFW(sources[3]);
//					}
					
				}else{
//					logout.fileChaseFW(sources[3]);
					
					
					
					
				}
				for(File file :files){
					recursionDirectory(file);
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
				
			}
		} catch (Exception e) {
			e.printStackTrace();
//			System.exit(0);
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