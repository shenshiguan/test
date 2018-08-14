package filetools.check;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import filetools.log.LogOut;

/***
 * 查询所有文件夹下 文件的数量 是否是2
 * 
 * @author Administrator
 *
 */
public class FileRecursion2020 {
	static LogOut logout = new LogOut("D:/count-pdf.log");
	static long count = 0;

	public static void main(String[] args) {
		String filePath = "D:/资料包/";
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

	public static void recursionDirectory(File file_1){
		try {
			if(file_1.isDirectory()){
				File[] files = file_1.listFiles();
				for(File file :files){
					recursionDirectory(file);
				}
			}else{
				String filename = file_1.getName().substring(0, file_1.getName().lastIndexOf("."));
				
				String filename_uuid = UUID.randomUUID().toString();
				
				File file = file_1.getParentFile();
				//文件夹内容是否是2
				if(file.listFiles().length!=2){
					System.out.println(file.getAbsoluteFile());
					return;
				}
//				if(file.listFiles()[0].getName().equals(filename+".pdf")&&file.listFiles()[1].getName().equals(filename+".jpg")){
//					System.out.println("111111111111");
//					file.listFiles()[0].renameTo(new File(file.getAbsolutePath()+"/"+filename_uuid+".pdf"));
//					file.listFiles()[1].renameTo(new File(file.getAbsolutePath()+"/"+filename_uuid+".jpg")); 
//				}
//				if(file.listFiles()[0].getName().equals(filename+".jpg")&&file.listFiles()[1].getName().equals(filename+".pdf")){
//					System.out.println("222222222"+file.getAbsolutePath()+"\\"+filename_uuid+".jpg");
//
//					file.listFiles()[0].renameTo(new File(file.getAbsolutePath()+"\\"+filename_uuid+".jpg"));
//					Thread.sleep(1000);
//					file.listFiles()[1].renameTo(new File(file.getAbsolutePath()+"\\"+filename_uuid+".pdf")); 
//				}
				
				
				for(File ff:file.listFiles()){
					if(ff.getAbsolutePath().endsWith(".pdf"))
						ff.renameTo(new File(file.getAbsolutePath()+"\\"+filename_uuid+".pdf")); 
					if(ff.getAbsolutePath().endsWith(".jpg"))
						ff.renameTo(new File(file.getAbsolutePath()+"\\"+filename_uuid+".jpg")); 
					
				}
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
//			System.exit(0);
		}
	}
}