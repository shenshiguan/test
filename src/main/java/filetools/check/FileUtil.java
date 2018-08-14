package filetools.check;

import java.io.File;
import java.util.Arrays;

public class FileUtil {

    //for test  
    public static void main(String []args) {  
        File dirFile = new File("E://已核对历年真题//南京航空航天大学//");
        for(File file:dirFile.listFiles()){
        	System.out.println(file.getName());
        }
        System.out.println("=======================");
        File [] sortedFiles = listSortedFiles(dirFile);  
        for(File file:sortedFiles){
        	System.out.println(file.getName());
        }
    }
  
    //list sorted files  
    public static File[] listSortedFiles(File dirFile) {  
    assert dirFile.isDirectory();  
  
        File[] files = dirFile.listFiles();  
          
        FileWrapper [] fileWrappers = new FileWrapper[files.length];  
        for (int i=0; i<files.length; i++) {  
            fileWrappers[i] = new FileWrapper(files[i]);  
        }  
          
        Arrays.sort(fileWrappers);  
          
        File []sortedFiles = new File[files.length];  
        for (int i=0; i<files.length; i++) {  
            sortedFiles[i] = fileWrappers[i].getFile();  
        }  
          
        return sortedFiles;  
    }  
}  
  
  
class FileWrapper implements Comparable {  
    /** File */  
    private File file;  
      
    public FileWrapper(File file) {  
        this.file = file;  
    }  
       
    public int compareTo(Object obj) {  
        assert obj instanceof FileWrapper;  
          
        FileWrapper castObj = (FileWrapper)obj;  
                  
        if (this.file.getName().compareTo(castObj.getFile().getName()) > 0) {  
            return 1;  
        } else if (this.file.getName().compareTo(castObj.getFile().getName()) < 0) {  
            return -1;  
        } else {  
            return 0;  
        }  
    }  
      
    public File getFile() {  
        return this.file;  
    }	
	
}
