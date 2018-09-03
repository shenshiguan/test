package org.ssg.files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.ssg.utils.TiffUtilTools;

import com.alibaba.dubbo.common.utils.StringUtils;

import filetools.check.PrintToPdfUtil;
import filetools.log.LogOut;

/***
 * 
 * 
 * 
 * @author Administrator
 *
 */
public class Jpg2Pdf {

	static LogOut logout = new LogOut("D:/insertbatch2018-06-17_1.sql");
	
	static long count = 0;
	
	
	public static void main(String[] args) {
		logout.fileChaseFW("-- =======================================================");
		String filePath = "C:/aaa/imgs/";
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
					if(file.isDirectory()){
						recursionDirectory(file);
					}else{
						break;
					}
				}
				
				//读取文件数量
//				if(files.length<=1&&!files[0].isDirectory()){
				if(!files[0].isDirectory()){	
					System.out.println(file_1.getAbsolutePath());
//					List<File> file_list = sortFiles(files);
//					List<File> file_list = Arrays.asList(files);
//					for(File file :file_list){
//						System.out.println(file == null);
//						System.out.println(file.getName());
//					}
					//imgs生成pdf
					String directorPath = file_1.getAbsolutePath().replaceFirst("\\\\imgs\\\\", "\\\\pdfs\\\\");
					File file_dire = new File(directorPath);
					if(file_dire.exists() == false){
						file_dire.mkdirs();
					}
					PrintToPdfUtil.toPdf(file_1.getAbsolutePath(), file_1.getAbsolutePath().replaceFirst("\\\\imgs\\\\", "\\\\pdfs\\\\")+"\\"+file_1.getName()+".pdf");
				}
			}else{
				
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
	
}
