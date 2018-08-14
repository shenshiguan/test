package filetools.all;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import filetools.log.LogOut;

/***
 * 查询jpg图片文件
 * 
 * @author Administrator
 *
 */
public class FileFindAll3 {
	static LogOut logout = new LogOut("D:/find_all_2018-06-26.txt");
	static long count = 0;

	static String DIRECTORY = "";
	
	static Set<String> set = new HashSet<String>();
	
	public static void main(String[] args) {
		String filePath = "D:/资料包/无水印系统上传_10811/";
		File file = new File(filePath);
		if (file != null) {
			recursionDirectory(file);
		}
		
		for(String s:set){
			System.out.println(s);
		}
		
		try {
			logout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void recursionDirectory(File file_1){
		try {
			if(file_1.isDirectory()){
				
				if(file_1.getName().equals("首都师范大学")){
					
					
					copyFile(file_1.getAbsolutePath(),"D:\\首都师范大学\\");
					
				}
				
				File[] files = file_1.listFiles();
				for(File file :files){
					recursionDirectory(file);
				}
			}else{
//				String fileName = file_1.getName();
//				String sufixx= fileName.substring(fileName.indexOf("."));
//				if(!fileName.endsWith(".pdf")&&file_1.getAbsolutePath().indexOf("首都师范大学")>0){
//					System.out.println(file_1.getAbsolutePath());
//					set.add(sufixx);
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	static void copyFile(String file1,String file2) throws IOException{
		System.out.println(file1);
		System.out.println(file2);
		
		String cmd  = " xcopy /e "+ "	 "+file1+ "	 "+file2;
		System.out.println(cmd);
		java.lang.Runtime.getRuntime().exec(cmd);
		
	}
}