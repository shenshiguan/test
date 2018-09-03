package org.ssg.files;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import filetools.log.LogOut;

/**
 * 文件重命名 
 * 重命名 文件名中 包含 a b 的文件
 * @author Administrator
 *
 */
public class FileRename {

	static LogOut logout = new LogOut("D:/find_all_2018-08-15.txt");
	static long count = 0;

	static String DIRECTORY = "";
	
	static Set<String> set = new HashSet<String>();
	
	public static void main(String[] args) {
		String filePath = "C:\\aaa\\new_pdf\\";
		File file = new File(filePath);
		if (file != null) {
			recursionDirectory(file);
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
				File[] files = file_1.listFiles();
				for(File file :files){
					recursionDirectory(file);
				}
			}else{
				String filePath = "";
				if(file_1.getName().indexOf("a")>0){
					filePath =  file_1.getAbsolutePath().replace("a", "A");
				}
				if(file_1.getName().indexOf("b")>0){
					filePath =  file_1.getAbsolutePath().replace("b", "B");
				}
				if(!"".equals(filePath)){
					file_1.renameTo(new File(filePath));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
