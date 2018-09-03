package org.ssg.files;

import java.io.File;
import java.io.IOException;

import org.ssg.utils.Word2PdfUtil;

import filetools.log.LogOut;

public class FileCreateTime {

	static LogOut logout = new LogOut("D:/2018-08-18.log");
	static long count = 0;
	public static void main(String[] args) {
		String filePath = "C:/xin1/";
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

//	/***
//	 * 
//	 * doc文件穿换成pdf  
//	 * 
//	 ***/
//	public static void recursionDirectory(File file_1){
//		try {
//			if(file_1.isDirectory()){
//				File[] files = file_1.listFiles();
//				for(File file :files){
//					recursionDirectory(file);
//				}
//			}else{
//				if(file_1.getName().toLowerCase().indexOf(".doc")>0){
//					System.out.println(file_1.getAbsolutePath().replaceAll("\\\\", "/"));
//					System.out.println("c:/xin2/"+file_1.getName().replace(".docx", ".pdf").replace(".doc", ".pdf"));
//					Word2PdfUtil.doc2pdf(file_1.getAbsolutePath(), "c:/xin2/"+file_1.getName().replace(".doc", ".pdf"));
//					count++;
//				}
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	
	/***
	 * 
	 * xin文件夹下的所有文件提取到xin1文件夹下
	 */
	public static void recursionDirectory(File file_1){
		try {
			if(file_1.isDirectory()){
				File[] files = file_1.listFiles();
				for(File file :files){
					recursionDirectory(file);
				}
			}else{
				if(file_1.getName().indexOf("年")<1){
					System.out.println(file_1.getAbsolutePath());
					count++;
				}else{
					file_1.renameTo(new File("c:/xin1/"+file_1.getName()));
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
}
