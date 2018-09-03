package org.ssg.files;

import java.io.File;
import java.io.IOException;

import filetools.log.LogOut;

public class FindFileByLength0 {

	static LogOut logout = new LogOut("D:/2018-08-15.log");
	static long count = 0;
	public static void main(String[] args) {
		String filePath = "C:/aaa/";
		File file = new File(filePath);
		if(file!=null){
			recursionDirectory(file);
		}
		System.out.println("共有pdf文件："+count+"份.");
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
				if(file_1.length() == 0){
					System.out.println(file_1.getAbsolutePath());
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
