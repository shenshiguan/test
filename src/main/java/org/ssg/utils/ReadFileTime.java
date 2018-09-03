package org.ssg.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReadFileTime {

    public static void main(String[] args) {
    	
    	File file =new File("E:/");
    	System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(file.lastModified())));
    	if(file.isDirectory()){
    		File[] files =  file.listFiles();
    		for(File f1:files){
    	        System.out.println("文件名："+f1.getName()+"    创建时间："+getCreateTime(f1)+"       修改时间："+getModifiedTime_2(f1));
    		}
    	}
    }  
  
    /** 
     * 读取文件创建时间 
     */  
    public static String getCreateTime(File file){  
        String filePath = file.getAbsolutePath();
        String strTime = null;  
        try {  
            Process p = Runtime.getRuntime().exec("cmd /C dir "           
                    + filePath  
                    + "/tc" );  
            InputStream is = p.getInputStream();   
            BufferedReader br = new BufferedReader(new InputStreamReader(is));             
            String line;  
            while((line = br.readLine()) != null){  
                if(line.endsWith(".txt")){  
                    strTime = line.substring(0,17);  
                    break;  
                }                             
             }   
        } catch (IOException e) {  
            e.printStackTrace();  
        }         
//        System.out.println("创建时间    " + strTime);     
        //输出：创建时间   2009-08-17  10:21  
        return strTime;
    }  
    /** 
     * 读取文件修改时间的方法1 
     */   
    @SuppressWarnings("deprecation")  
    public static String getModifiedTime_1(File f){            
        Calendar cal = Calendar.getInstance();  
        long time = f.lastModified();  
        cal.setTimeInMillis(time);    
        //此处toLocalString()方法是不推荐的，但是仍可输出  
//        System.out.println("修改时间[1] " + cal.getTime().toLocaleString());   
        //输出：修改时间[1]    2009-8-17 10:32:38  
        return cal.getTime().toLocaleString();
    }  
      
    /** 
     * 读取修改时间的方法2 
     */  
    public static String getModifiedTime_2(File f){         
        Calendar cal = Calendar.getInstance();  
        long time = f.lastModified();  
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");         
        cal.setTimeInMillis(time);    
//        System.out.println("修改时间[2] " + formatter.format(cal.getTime()));     
        //输出：修改时间[2]    2009-08-17 10:32:38  
        return formatter.format(cal.getTime());
    }  	
	
}
