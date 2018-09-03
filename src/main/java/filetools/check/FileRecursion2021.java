package filetools.check;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.ssg.utils.OssUtil;

import com.alibaba.dubbo.common.utils.StringUtils;

import filetools.log.LogOut;

/***
 * 查询所有文件夹下 文件的数量 是否是2
 * 
 * @author Administrator
 *
 */
public class FileRecursion2021 {
	static LogOut logout = new LogOut("D:/insertbatch.sql");
	static long count = 0;

	static String DIRECTORY = "";
	public static void main(String[] args) {
//		String filePath = "D:/资料包/无水印手动整理_19031/首都师范大学/872_语文专业素养/";
		String filePath = "D:/资料包/无水印手动整理_19031/";
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
//				DIRECTORY = file_1.getName();
				File[] files = file_1.listFiles();
				for(File file :files){
					recursionDirectory(file);
					if(!file.isDirectory()){
						return;
					}
					
				}
			}else{
//				String filename = file_1.getName().substring(0, file_1.getName().lastIndexOf("."));
//				
//				String filename_uuid = UUID.randomUUID().toString();
				
				File file = file_1.getParentFile();
				//文件夹内容是否是2
				if(file.listFiles().length!=2){
					System.out.println(file.getAbsoluteFile());
					return;
				}
				String pdfUrl = "";
				String jpgUrl = "";
				long fileLength = 0;
				String fileName = "";
				for(File ff:file.listFiles()){
					if(ff.getAbsolutePath().endsWith(".pdf")){
						//上传pdf
						pdfUrl = ossFile(ff.getName(),ff.getAbsolutePath());
						fileLength = ff.length();
						fileName = ff.getName();
					}
					if(ff.getAbsolutePath().endsWith(".jpg")){
						//上传jpg
						jpgUrl = ossFile(ff.getName(),ff.getAbsolutePath());
					}
					//生成insert语句
					String[] sources = file.getAbsoluteFile().toString().split("\\\\");
					
//					if(sources.length<6){
//						System.out.println(file.getAbsoluteFile());
//					}
//					System.out.println(file.getAbsoluteFile());
					String nian ="";
					try
					{
						nian = StringUtils.isBlank(sources[5])?"":sources[5].length()>3?sources[5].substring(0,4):"";
					}catch(Exception e){
						e.printStackTrace();
						System.exit(1);
					}

//						D:
//						资料包
//						无水印手动整理_19031
//						首都师范大学
//						872_语文专业素养
//						2011213
//						D:\资料包\无水印手动整理_19031\首都师范大学\872_语文专业素养\2011213\b6860948-0dd9-4b61-a2ce-1f97c8913bad.jpg
					
						
					
					
					
//					无水印手动整理_19031	
//INSERT INTO `kuakao_live`.`paper_file` (`file_name`, `school`, `subject`, `cover_url`, `net_url`, `local_url`, `file_size`, `file_type`, 
//		`path3`, `path4`, `file_year`, `filebank_type_id`, `is_filebank`, `local_url1`, `pdf_url`) 
//VALUES ('"+sources[4]+sources[5]+".pdf', '"+sources[3]+"', '"+sources[4]+"', 
//		'"+jpgUrl+"', 
//		'"+pdfUrl+"', 
//		'"+sources[4]+sources[5]+".pdf', '"+fileLength+"', 'pdf', '试卷', '真题', '"+nian+"', '1', '1', 
//		'"+fileName+"', 
//		'"+pdfUrl+"'
//);


					String sql = "INSERT INTO `kuakao_live`.`paper_file` (`file_name`, `school`, `subject`, `cover_url`, `net_url`, "
							+ "	`local_url`, `file_size`, `file_type`, `path3`, `path4`, `file_year`, `filebank_type_id`, `is_filebank`, "
							+ "	`local_url1`, `pdf_url`) "
							+ "	VALUES ('"+sources[4]+sources[5]+".pdf', '"+sources[3]+"', '"+sources[4]+"', "
							+ "	'"+jpgUrl+"', '"+pdfUrl+"', '"+sources[4]+sources[5]+".pdf', '"+fileLength+"', "
							+ "	'pdf', '试卷', '真题', '"+nian+"', '1', '1', '"+fileName+"', '"+pdfUrl+"');";
					System.out.println(sql);
					logout.fileChaseFW(sql);
				}
//				System.out.println(sql);
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

	
}