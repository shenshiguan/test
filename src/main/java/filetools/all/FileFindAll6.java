package filetools.all;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.alibaba.dubbo.common.utils.StringUtils;

import filetools.log.LogOut;

/***
 * 
 * 
 * @author Administrator
 *
 */
public class FileFindAll6 {
	static LogOut logout = new LogOut("D:/find_all_2018-08-09.txt");
	static long count = 0;

	static String DIRECTORY = "";
	
	static Set<String> set = new HashSet<String>();
	
	public static void main(String[] args) {
//		String filePath = "D:\\首都师范大学1\\";
		String filePath = "C:\\李伟\\pdfs\\";
		File file = new File(filePath);
		if (file != null) {
			try {
				recursionDirectory(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			logout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void recursionDirectory(File file_1) throws IOException{
		if(file_1.isDirectory()){
			File[] files = file_1.listFiles();
			for(File file :files){
				recursionDirectory(file);
			}
		}else{
			System.out.println("===================================");
			System.out.println(file_1.getName());
			System.out.println(file_1.getAbsolutePath());
			if(file_1.getName().endsWith(".pdf")){
				
				String filePath = file_1.getAbsolutePath().replaceFirst("C:\\\\李伟\\\\pdfs\\\\", "C:\\\\李伟\\\\new_pdfs\\\\");
				if(filePath.indexOf("年")<1){
					System.out.println("..............没有找到标示为'年'的位置。");
					System.out.println(file_1.getAbsolutePath());
				}else{
					int nian_ =  filePath.indexOf("年");
					int nianFen_ =  nian_- 4;
					String file_ = filePath.substring(nianFen_, nian_+1);
					int tmp_ = filePath.lastIndexOf("\\");
					String newPath = filePath.substring(0, tmp_+1) + file_
							+ filePath.substring(tmp_+1, filePath.length()).replace(file_, "");
					
					newPath = newPath.replace(".", "研究生入学考试试卷.");
					System.out.println(newPath);
//					copyFile(file_1.getAbsolutePath(),newPath);
//					file_1.renameTo(new File(newPath));
					
//					InputStream is = new FileInputStream(file_1.getAbsolutePath());
//					Files.copy(new Inputs, newPath);
				}
			}
		}
	}
	

	
	static List<File> sortFiles(File[] files){
		List<File>  flist =  new ArrayList<File>();
		Map<String,File> map = new TreeMap<String,File>(new Comparator<String>() {
            public int compare(String obj1, String obj2) {
            	int o1 = 0;
				int o2 = 0;
				try {
					if(!StringUtils.isBlank(obj1)&&!StringUtils.isBlank(obj2)){
						o1 = Integer.parseInt(obj1);
						o2 = Integer.parseInt(obj2);
					}
	            	if(o1>o2){
	                    return obj1.compareTo(obj2);
	            	}else{
	                    return obj2.compareTo(obj1);
	            	}
				} catch (NumberFormatException e) {
                    return obj1.compareTo(obj2);
				}
            }
        });
		for(File file:files){
			String fileName = "";
				fileName = file.getName().substring(0, file.getName().indexOf(".")>0?file.getName().indexOf("."):0);
			map.put(fileName, file);
		}
		for(String key :map.keySet()){
			flist.add(map.get(key));
		}
		return flist;
	}
	
	
	static void copyFile(String file1,String file2) throws IOException{
		String cmd  = " xcopy /e "+ "	 "+file1+ "	 "+file2;
		System.out.println(cmd);
		java.lang.Runtime.getRuntime().exec(cmd);
		
	}
}