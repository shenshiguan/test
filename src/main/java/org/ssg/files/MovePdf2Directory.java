package org.ssg.files;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import filetools.log.LogOut;

/***
 * 
 * @author Administrator
 *
 */
public class MovePdf2Directory {

	static LogOut logout = new LogOut("D:/find_all_2018-08-15.txt");
	static long count = 0;

	static String DIRECTORY = "";
	
	static Set<String> set = new HashSet<String>();
	
	public static void main(String[] args) {
		String filePath = "C:/aaa/pdfs/";
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
				File[] files = file_1.listFiles();
				for(File file :files){
					recursionDirectory(file);
				}
			}else{
				if(file_1.getName().endsWith(".pdf")){
//					new_pdf
					copyFile(file_1.getAbsolutePath(),"c:\\aaa\\new_pdf\\");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	static void copyFile(String file1,String file2) throws IOException{
		String cmd  = " xcopy /e "+ "	 "+file1+ "	 "+file2;
		System.out.println(cmd);
		java.lang.Runtime.getRuntime().exec(cmd);
		
	}
}
