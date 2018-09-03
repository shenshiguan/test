package org.ssg.files;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import filetools.log.LogOut;

/***
 * 
 * 修改文件名规则
 * 年份放到前面 后面增加 研究生入学考试试卷
 * 2012年河南师范大学213翻译硕士日语（B卷）考研真题研究生入学考试试卷
 * @author Administrator
 *
 */
public class RenamePdf2Directory {

	static LogOut logout = new LogOut("D:/find_all_2018-08-15.txt");
	static long count = 0;

	static String DIRECTORY = "";
	
	static Set<String> set = new HashSet<String>();
	
	public static void main(String[] args) {
		String filePath = "C:/aaa/new_pdf_rename/";
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
					
					String filePath = file_1.getAbsolutePath();
					int nian_ =  filePath.lastIndexOf("年");
					int nianFen_ =  nian_- 4;
					String file_ = filePath.substring(nianFen_, nian_+1);
					int tmp_ = filePath.lastIndexOf("\\");
					String newPath = filePath.substring(0, tmp_+1) + file_
							+ filePath.substring(tmp_+1, filePath.length()).replace(file_, "");
					newPath = newPath.replace(".", "研究生入学考试试卷.").replaceAll("a", "A").replaceAll("b", "B");
					
//					rename(file_1.getParent(),file_1.getName(),newPath.replaceAll("a", "A").replaceAll("b", "B"));
					file_1.renameTo(new File(newPath));
					
					
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
	
	static void rename(String directory,String file1,String file2) throws IOException{
//		String cmd  = " cd "+directory+" && ren "+file1+" "+file2;
		String cmd  = " cmd /c C:\\aaa\\new_pdf_rename && ren "+file1+" "+file2;
		System.out.println(cmd);
		java.lang.Runtime.getRuntime().exec(cmd);
		
	}
}
