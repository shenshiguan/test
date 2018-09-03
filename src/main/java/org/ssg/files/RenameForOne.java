package org.ssg.files;

import java.io.File;
import java.io.IOException;

import org.ssg.utils.Word2PdfUtil;

import filetools.log.LogOut;

public class RenameForOne {

	static LogOut logout = new LogOut("D:/2018-08-18.log");
	static long count = 0;
	public static void main(String[] args) {
		String filePath = "C:/xin4/";
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


	/***
	 * 更改姓名
	 *  广西民族大学611伦理学原理2015年考研真题.doc
	 *  2015年广西民族大学611伦理学原理考研真题研究生入学考试试卷
	 *  2015年广西民族大学611伦理学原理研究生入学考试试卷考研真题
	 * @param file_1
	 */
	public static void recursionDirectory1(File file_1){
		try {
			if(file_1.isDirectory()){
				File[] files = file_1.listFiles();
				for(File file :files){
					recursionDirectory(file);
				}
			}else{
				
//				String fileName = file_1.getName();
				String filePath = file_1.getName();
				int nian_ =  filePath.lastIndexOf("年");
				int nianFen_ =  nian_- 4;
				String file_ = filePath.substring(nianFen_, nian_+1);
				int tmp_ = filePath.lastIndexOf("\\");
				String newPath = filePath.substring(0, tmp_+1) + file_
						+ filePath.substring(tmp_+1, filePath.length()).replace(file_, "");
				newPath = newPath.replace(".", "研究生入学考试试卷.");
				
				System.out.println(newPath);
				
				String newPath2 =  file_+ filePath.replace(file_, "研究生入学考试试卷");
				System.out.println(newPath2);
				
				
//				file_1.renameTo(new File("c:/xin3/"+newPath));
				file_1.renameTo(new File("c:/xin4/"+newPath2));
			}
		} catch (Exception e) {
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
				String fileName =  file_1.getName();
//				System.out.println(fileName);
//				System.out.println(fileName.indexOf("考研真题"));
				int a = fileName.lastIndexOf(".");
//				System.out.println(a);
//				System.out.println(fileName.substring(a,fileName.length()));
				System.out.println(fileName.subSequence(0, fileName.indexOf("考研真题"))+"研究生入学考试试卷考研真题"+fileName.substring(a,fileName.length()));
				String newPath2 = fileName.subSequence(0, fileName.indexOf("考研真题"))+"研究生入学考试试卷考研真题"+fileName.substring(a,fileName.length());
				file_1.renameTo(new File("c:/xin4/"+newPath2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
