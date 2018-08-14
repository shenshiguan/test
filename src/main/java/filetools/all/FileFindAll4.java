package filetools.all;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import filetools.log.LogOut;

/***
 * 查询jpg图片文件
 * 
 * @author Administrator
 *
 */
public class FileFindAll4 {
	static LogOut logout = new LogOut("D:/find_all_2018-06-26.txt");
	static long count = 0;

	static String DIRECTORY = "";
	
	static Set<String> set = new HashSet<String>();
	
	public static void main(String[] args) {
		String filePath = "D:\\安徽大学\\";
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
				
				String[] paths = file_1.getAbsolutePath().split("\\\\");
//				System.out.println(paths.length);
				
				String fileName = file_1.getName();
				String suffix= fileName.substring(fileName.indexOf("."));
				
				if(paths.length == 6){
					if(fileName.endsWith(".pdf")){
						file_1.delete();
					}else if(fileName.endsWith(".jpg")){
						String subjNum = paths[2].split("_")[1];
						String subjName = paths[2].split("_")[0];
						System.out.println(subjName);
						if(subjName.indexOf("(")>0||subjName.indexOf("（")>0){
							System.out.println(subjName.replace("（", "("));
							subjName = subjName.replace("（", "(");
							subjName = subjName.substring(0,subjName.indexOf("("));
						}
						
						String year_Tmp = paths[4].substring(0, 4);
						String fileName_ =  paths[1]+"("+subjNum+")"
											+subjName+year_Tmp+"年真题";
						String filePath = "D:\\"+paths[1]+"1\\"+fileName_
								+"\\";
						File file_ = new File(filePath);
						if(!file_.exists()){
							file_.mkdirs();
						}
						file_1.renameTo(new File(filePath+""+fileName_+suffix));
					}				
				}else if(paths.length == 5){
					if(fileName.endsWith(".pdf")){
						file_1.delete();
					}else if(fileName.endsWith(".jpg")){
						String subjNum = paths[2].split("_")[0];
						String subjName = paths[2].split("_")[1];
						System.out.println(subjName);
						if(subjName.indexOf("(")>0||subjName.indexOf("（")>0){
							System.out.println(subjName.replace("（", "("));
							subjName = subjName.replace("（", "(");
							subjName = subjName.substring(0,subjName.indexOf("("));
						}
						
						String year_Tmp = paths[3].substring(0, 4);
						String fileName_ =  paths[1]+"("+subjNum+")"
											+subjName+year_Tmp+"年真题";
						String filePath = "D:\\"+paths[1]+"1\\"+fileName_
								+"\\";
						File file_ = new File(filePath);
						if(!file_.exists()){
							file_.mkdirs();
						}
						file_1.renameTo(new File(filePath+""+file_1.getName()));
					}
				}else if(paths.length == 4){
					if(fileName.endsWith(".pdf")){
						file_1.delete();
					}else if(fileName.endsWith(".jpg")){
						String subjNum = paths[2].split("_")[0];
						String subjName = paths[2].split("_")[1];
						System.out.println(subjName);
						if(subjName.indexOf("(")>0||subjName.indexOf("（")>0){
							System.out.println(subjName.replace("（", "("));
							subjName = subjName.replace("（", "(");
							subjName = subjName.substring(0,subjName.indexOf("("));
						}
						String year_Tmp = paths[3].substring(0, 4);
						String fileName_ =  paths[1]+"("+subjNum+")"
											+subjName+year_Tmp+"年真题";
						String filePath = "D:\\"+paths[1]+"1\\"+fileName_
								+"\\";
						File file_ = new File(filePath);
						if(!file_.exists()){
							file_.mkdirs();
						}
						file_1.renameTo(new File(filePath+""+file_1.getName()));
					}
				}else{
					System.out.println(paths.length);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	static void copyFile(String file1,String file2) throws IOException{
		System.out.println(file1);
		System.out.println(file2);
		
		String cmd  = " xcopy /e "+ "	 "+file1+ "	 "+file2;
		System.out.println(cmd);
		java.lang.Runtime.getRuntime().exec(cmd);
		
	}
}