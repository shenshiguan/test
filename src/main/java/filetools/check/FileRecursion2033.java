package filetools.check;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import filetools.log.LogOut;

/***
 * 单个图片文件上传
 * 
 * @author Administrator
 *
 */
public class FileRecursion2033 {
	static LogOut logout = new LogOut("D:/insertbatch2018-06-17_20.txt");
	static long count = 0;

	static String DIRECTORY = "";
	static Set<String> set = new HashSet<String>();
	
	public static void main(String[] args) {
		logout.fileChaseFW("-- ===========================单个图片文件夹============================");
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
//				if(suffix.equals(".gif")||suffix.equals(".bmp")||suffix.equals(".jpg")||suffix.equals(".png")){
//					
//					File parentFile =	file_1.getParentFile();
//					String pname = parentFile.getName();
//					
//					File[] files = parentFile.listFiles();
//					if(files.length==1){
//						System.out.println(parentFile.getAbsolutePath());
//						logout.fileChaseFW(parentFile.getAbsolutePath());
//						count++;
//					}
//				}
				if(suffix.equals(".bmp")){
					
//					ImageTools.bmp2jpeg(file_1.getAbsolutePath(), file_1.getAbsolutePath().replaceAll(".bmp", ".jpg"));
//					file_1.delete();
//					
//					File parentFile =	file_1.getParentFile();
////					String pname = parentFile.getName();
////					
////					File[] files = parentFile.listFiles();
////					if(files.length==1){
//						System.out.println(parentFile.getAbsolutePath());
//						logout.fileChaseFW(parentFile.getAbsolutePath());
//						count++;
////					}
//						
						file_1.delete();
				}
			}
			
		} catch (Exception e) {

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
			logout.fileChaseFW(parentFile.getAbsolutePath());
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
}