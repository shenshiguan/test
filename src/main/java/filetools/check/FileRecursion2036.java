package filetools.check;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import filetools.log.LogOut;

/***
 * 单一图片文件重命名及上传---生成sql
 * 
 * @author Administrator
 *
 */
public class FileRecursion2036 {
//	static LogOut logout = new LogOut("D:/insertbatch2018-06-18_one.sql");
	static LogOut logout_error = new LogOut("D:/insertbatch2018-06-18_more.txt");
	static long count = 0;

	static String DIRECTORY = "";
	static Set<String> set = new HashSet<String>();
	
	public static void main(String[] args) {
//		logout.fileChaseFW("-- ===========================单一图片文件重命名============================");
		String filePath = "E:/已核对历年真题/";
		File file = new File(filePath);
		if (file != null) {
			recursionDirectory(file);
		}
		System.out.println("共有pdf文件：" + count + "份.");
		
		try {
			logout_error.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int recursionDirectory(File file_1){
		try {
			if(file_1.isDirectory()){
				File[] files = file_1.listFiles();
				for(File file :files){
					int res = recursionDirectory(file);
					if(res==1){
						break;
					}
				}
			}else{
				String suffix = file_1.getName().substring(file_1.getName().lastIndexOf("."), file_1.getName().length());
				set.add(suffix);
				if(suffix.equals(".png")||suffix.equals(".jpg")){

					
					String pdfUrl = "";
					String jpgUrl = "";
					long fileLength = 0;
					String fileName = "";
					
					File parentFile =	file_1.getParentFile();
//					System.out.println(parentFile.getAbsolutePath());
					
					File[] ff = parentFile.listFiles();
					if(ff.length==1){
//						logout.fileChaseFW(parentFile.getAbsolutePath());
//						System.out.println(parentFile.getAbsolutePath());
				
						
						String[] sources = parentFile.getAbsoluteFile().toString().split("\\\\");
						
						
						//上传jpg
						jpgUrl = ossFile(file_1.getName(),file_1.getAbsolutePath());
						
						fileLength = file_1.length();
						fileName = file_1.getName();
						
						String nian ="";
						String subject = "";
						try
						{
							nian = StringUtils.isBlank(sources[3])?"":sources[3].length()>3?sources[3].substring(sources[3].length()-4,sources[3].length()):"";
							subject = StringUtils.isBlank(sources[3])?"":sources[3].length()>3?sources[3].substring(0,sources[3].length()-4):"";
						}catch(Exception e){
							e.printStackTrace();
//							System.exit(1);
							logout_error.fileChaseFW(parentFile.getAbsolutePath());
						}
						int nian_i = 0;
						try {
							nian_i = Integer.parseInt(nian);
						} catch (Exception e) {
							logout_error.fileChaseFW(parentFile.getAbsolutePath());
						}
						
//						System.out.println(subject);
//						System.out.println(nian);
//						String sql = "INSERT INTO `kuakao_live`.`paper_file` (`file_name`, `school`, `subject`, `cover_url`, `net_url`, "
//								+ "	`local_url`, `file_size`, `file_type`, `path3`, `path4`, `file_year`, `filebank_type_id`, `is_filebank`, "
//								+ "	`local_url1`, `pdf_url`) "
//								+ "	VALUES ('"+sources[2]+sources[3]+suffix+"', '"+sources[2]+"', '"+subject+"', "
//								+ "	'"+jpgUrl+"', '"+jpgUrl+"', '"+sources[2]+sources[3]+suffix+"', '"+fileLength+"', "
//								+ "	'"+suffix.replaceAll(".", "")+"', '试卷', '真题', '"+nian_i+"', '1', '1', '"+fileName+"', '"+jpgUrl+"');";
//						System.out.println(sql);
//						logout.fileChaseFW(sql);
						return 1;
					}
				}
			}
			
		} catch (Exception e) {
			logout_error.fileChaseFW(file_1.getParentFile().getAbsolutePath());
			logout_error.fileChaseFW("==="+file_1.getAbsolutePath());
		}
		return 0;
	}
	
	public static void bmp2jpgall(File file_1){
		String suffix = file_1.getName().substring(file_1.getName().lastIndexOf("."), file_1.getName().length());
		set.add(suffix);
		if(suffix.equals(".bmp")){
			
			ImageTools.bmp2jpeg(file_1.getAbsolutePath(), file_1.getAbsolutePath().replaceAll(".bmp", ".jpg"));
			file_1.delete();
			
			File parentFile =	file_1.getParentFile();
			System.out.println(parentFile.getAbsolutePath());
//			logout.fileChaseFW(parentFile.getAbsolutePath());
			count++;
		}
	}
	
	
	
	public static void rename(File file_1) {
		if(file_1.getName().indexOf(" ")>0){
			file_1.renameTo(new File(file_1.getAbsolutePath().replaceAll(" ", "")));
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