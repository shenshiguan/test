package filetools.check;
import java.io.File;
import java.io.IOException;

import filetools.log.LogOut;

/***
 * 查询所有文件夹下 文件的数量 是否是2
 * @author Administrator
 *
 */
public class FileRecursion2019 {
	static LogOut logout = new LogOut("D:/count-pdf.log");
	static long count = 0;
	public static void main(String[] args) {
		String filePath = "D:/资料包/";
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
				String filename = file_1.getName().substring(0, file_1.getName().lastIndexOf("."));
				File file = file_1.getParentFile();
				//文件夹内容是否是2
				if(file.listFiles().length!=2){
					System.out.println(file.getAbsoluteFile());
					return;
				}
				//文件夹内容 同名且 jpg pdf
//				System.out.println("========1=========");
//				System.out.println(filename+".jpg");
//				System.out.println(filename+".pdf");
//				System.out.println(file.listFiles()[0].getName());
//				System.out.println(file.listFiles()[1].getName());
//				System.out.println("========2=========");
				if(file.listFiles()[0].getName().equals(filename+".jpg")&&file.listFiles()[1].getName().equals(filename+".pdf")){
//					System.out.println("111111");
				}else if(file.listFiles()[0].getName().equals(filename+".pdf")&&file.listFiles()[1].getName().equals(filename+".jpg")){
//					System.out.println("2222");
				}else if(file.listFiles()[0].getName().endsWith(".BMP")||file.listFiles()[0].getName().endsWith(".bmp")){
					System.out.println(file.listFiles()[0].getAbsolutePath());
					file.listFiles()[0].delete();
					file.listFiles()[1].delete();
					file.delete();
				}else{
					System.out.println("file:"+file.getAbsolutePath());
//					logout.fileChaseFW("file:"+file.getAbsolutePath());
//					System.exit(0);
				}
//				System.out.println("========3=========");
			}
		} catch (Exception e) {
			e.printStackTrace();
//			System.exit(0);
		}
	}
}