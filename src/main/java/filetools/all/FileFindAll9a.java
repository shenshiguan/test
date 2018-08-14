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
 * pdf 迁移
 * 
 * @author Administrator
 *
 */
public class FileFindAll9a {
	static LogOut logout = new LogOut("D:/find_all_2018-08-09.txt");
	static long count = 0;

	static String DIRECTORY = "";
	
	static Set<String> set = new HashSet<String>();
	
	public static void main(String[] args) {
//		String filePath = "D:\\首都师范大学1\\";
		String filePath = "C:\\李伟\\pdfs_1\\";
		File file = new File(filePath);
		if (file != null) {
			recursionDirectory(file);
		}
		
//		for(String s:set){
//			System.out.println(s);
//		}
		
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
				copyFile(file_1.getAbsolutePath(),"C:\\李伟\\aaa\\");	
			}
		} catch (Exception e) {
			e.printStackTrace();
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
//					e.printStackTrace();
//					System.out.println("------------------>"+obj1);
//					System.out.println("------------------>"+obj2);
                    return obj1.compareTo(obj2);
				}
            	

                // 降序排序
//                return obj2.compareTo(obj1);
            }
        });
		for(File file:files){
			String fileName = "";
				fileName = file.getName().substring(0, file.getName().indexOf(".")>0?file.getName().indexOf("."):0);
			map.put(fileName, file);
		}
		
		for(String key :map.keySet()){
//			System.out.println(map.get(key));
			flist.add(map.get(key));
		}
		
		return flist;
	}
	
	
	static void copyFile(String file1,String file2) throws IOException{
		System.out.println(file1);
		System.out.println(file2);
		
		String cmd  = " xcopy /e "+ "	 "+file1+ "	 "+file2;
		System.out.println(cmd);
		java.lang.Runtime.getRuntime().exec(cmd);
		
	}
}