package filetools.check;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.alibaba.dubbo.common.utils.StringUtils;

import filetools.log.LogOut;

/***
 * 查询所有文件夹下 文件的数量 是否是2
 * 
 * @author Administrator
 *
 */
public class FileRecursion2022 {
	static LogOut logout = new LogOut("D:/insertbatch.sql");
	static long count = 0;

	static String DIRECTORY = "";
	public static void main(String[] args) {
//		String filePath = "D:/资料包/无水印系统上传_10811/中国石油大学(北京)/汉语写作与百科知识_448/";
		String filePath = "D:/资料包/无水印系统上传_10811/";
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
				String[] sources = file.getAbsoluteFile().toString().split("\\\\");
				String nian ="";
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

					
					if(sources.length!=7){
						System.out.println(file.getAbsoluteFile());
//						count++;
					}
					
					if(!"试卷".equals(sources[5])&&!"答案".equals(sources[5])){
//						if(sources[5].indexOf("真题")>0){
//
//						}

//						System.out.println(file.getAbsoluteFile());
//						count++;
					}else{
						
//						D:\资料包\无水印系统上传_10811\福建师范大学\马克思主义基本原理_616\试卷\2015_真题
						try
						{
							nian = StringUtils.isBlank(sources[6])?"":sources[6].length()>3?sources[6].substring(0,4):"";
						}catch(Exception e){
							e.printStackTrace();
							System.exit(1);
						}
						
//						int nn = Integer.parseInt(nian);
//						if(nn<1996||nn>2018){
//							System.out.println(nian);
//							System.out.println(nn);
//							System.out.println(file.getAbsoluteFile());
//							count++;
//						}
						
					}
				}
				String sql = "INSERT INTO `kuakao_live`.`paper_file` (`file_name`, `school`, `subject`, `cover_url`, `net_url`, "
						+ "	`local_url`, `file_size`, `file_type`, `path3`, `path4`, `file_year`, `filebank_type_id`, `is_filebank`, "
						+ "	`local_url1`, `pdf_url`) "
						+ "	VALUES ('"+sources[3]+sources[4]+sources[6]+".pdf', '"+sources[3]+"', '"+sources[4]+"', "
						+ "	'"+jpgUrl+"', '"+pdfUrl+"','"+sources[3]+sources[4]+sources[6]+".pdf', '"+fileLength+"', "
						+ "	'pdf', '试卷', '真题', '"+nian+"', '1', '1', '"+fileName+"', '"+pdfUrl+"');";
				System.out.println(sql);
				count++;
				logout.fileChaseFW(sql);
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