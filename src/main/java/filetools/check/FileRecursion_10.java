package filetools.check;

import java.io.File;
import java.io.IOException;

import filetools.log.LogOut;

/***
 * 统计所有文件分数pdf
 * @author Administrator
 *
 */
public class FileRecursion_10 {
	static LogOut logout = new LogOut("D:/count-pdf.log");
	static long count = 0;
	public static void main(String[] args) {
		String filePath = "E:/资料包/";
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
				if(file_1.getName().endsWith(".pdf")){
					count++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
//			System.exit(0);
		}
	}
}