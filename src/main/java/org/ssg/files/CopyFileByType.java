package org.ssg.files;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.ssg.utils.TiffUtilTools;

import filetools.log.LogOut;

/***
 * 
 * 
 * 
 * @author Administrator
 *
 */
public class CopyFileByType {

	static LogOut logout = new LogOut("D:/insertbatch2018-06-17_1.sql");
	
	static long count = 0;
	
	
	public static void main(String[] args) {
		logout.fileChaseFW("-- =======================================================");
		String filePath = "C:/aaa/";
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
					recursionDirectory(file);
				}
			}else{
				if(file_1.getName().toLowerCase().endsWith(".tif")){
					System.out.println(file_1.getAbsolutePath());
					count++;
					
					String fileDirectory  = "C:/aaa/tifs/"+file_1.getName().toLowerCase().replace(".tiff", "").replace(".tif", "")+"/";
					File ff = new File(fileDirectory);
					ff.mkdirs();
					TiffUtilTools.doitJAI(file_1.getAbsolutePath(), fileDirectory);
					
				}
			}
		} catch (Exception e) {
		}
		return 0;
	}
	
		
	
}
