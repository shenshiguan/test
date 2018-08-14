package filetools.check;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import filetools.log.LogOut;

/***
 * 所有文件类型
 * 
 * @author Administrator
 *
 */
public class FileRecursion2032 {
	static LogOut logout = new LogOut("D:/insertbatch2018-06-17_10.txt");
	static long count = 0;

	static String DIRECTORY = "";
	static Set<String> set = new HashSet<String>();
	
	public static void main(String[] args) {
		logout.fileChaseFW("-- ===========================文件类型============================");
//		String filePath = "E:/已核对历年真题/华南理工大学/城市规划设计（做图）2005/信号与系统-2005/";
		String filePath = "E:/已核对历年真题/";
		File file = new File(filePath);
		if (file != null) {
			recursionDirectory(file);
		}
		System.out.println("共有pdf文件：" + count + "份.");
		
		for(String s:set){
			System.out.println(s);
		}
		
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
//				if(suffix.equals(".log")||suffix.equals(".swf")||suffix.equals(".RAR")||suffix.equals(".psd")
//						||suffix.equals(".shs")||suffix.equals(".ufo")||suffix.equals(".bbsftp")||suffix.equals(".jc!")
//						||suffix.equals(".dwg")||suffix.equals(".mdi")||suffix.equals(".PST")||suffix.equals(".mht")
//						||suffix.equals(".rtf")||suffix.equals(".html")||suffix.equals(".BKI")||suffix.equals(".lnk")
//						||suffix.equals(".MDI")||suffix.equals(".zma")||suffix.equals(".tmp")){
//					
//					file_1.delete();
//				}
//				if(suffix.toLowerCase().equals(".pdf")){
//					String filepath = file_1.getAbsolutePath().replaceAll(".PDF", ".pdf").replaceAll(".Pdf", ".pdf");
//					file_1.renameTo(new File(filepath));
//				}
				if(suffix.toLowerCase().equals(".jpeg")){
					String filepath = file_1.getAbsolutePath().replaceAll(".JPEG", ".jpg");
					file_1.renameTo(new File(filepath));
				}
				if(suffix.equals(".txt")||suffix.equals(".TXT")){
					System.out.println(file_1.getParentFile().getAbsolutePath());
					file_1.delete();
				}
				if(suffix.equals(".JPEG")){
					System.out.println("=====>"+file_1.getParentFile().getAbsolutePath());
//					file_1.delete();
				}
				if(suffix.equals(".ppt")){
					System.out.println("@@@>"+file_1.getParentFile().getAbsolutePath());
//					file_1.delete();
				}				
			}
			
		} catch (Exception e) {

		}
		return 0;
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