package filetools.check;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import filetools.log.LogOut;

/***
 * jpg文件夹---pdf
 * 
 * @author Administrator
 *
 */
public class FileRecursion2031 {
	static LogOut logout = new LogOut("D:/insertbatch2018-06-17_8.txt");
	static long count = 0;

	static String DIRECTORY = "";
	public static void main(String[] args) {
		logout.fileChaseFW("-- ===========================多种文件类型============================");
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
				File parentFile = file_1.getParentFile();
				String parentName = parentFile.getName();
				File[] ff = parentFile.listFiles();
				Set<String> set = null;
				set =  new HashSet<String>();
				for(File f:ff){
					String suffix = f.getName().substring(f.getName().lastIndexOf("."), f.getName().length());
					set.add(suffix);
				}
				if(set.size()>1){
					logout.fileChaseFW(parentFile.getAbsolutePath());
					System.out.println(parentFile.getAbsolutePath());
					return 1;
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