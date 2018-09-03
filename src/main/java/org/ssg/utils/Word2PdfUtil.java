package org.ssg.utils;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
 
import org.aspectj.weaver.ast.Test;
 
import com.aspose.words.Document;
import com.aspose.words.FontSettings;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;

public class Word2PdfUtil {

	
	public static void main(String[] args) {
		if (!getLicense()) { // 验证License 若不验证则转化出的pdf文档会有水印产生
			return;
		}
		try { 
			long old = System.currentTimeMillis();
			FontSettings.getDefaultInstance().setFontsFolder("C:/Windows/Fonts/", true);
//			File file = new File("C:/aaa/2018年杭州师范大学817专业日语考研真题研究生入学考试试卷.pdf"); // 新建一个空白pdf文档
//			FileOutputStream os = new FileOutputStream(file);
			Document doc = new Document("C:/aaa/2018年杭州师范大学817专业日语考研真题研究生入学考试试卷.doc"); // Address是将要被转化的word文档
//			doc.save(os, SaveFormat.PDF);// 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF,// EPUB, XPS, SWF 相互转换
			System.out.println(doc.toTxt());
			doc.save("C:/aaa/2018年杭州师范大学817专业日语考研真题研究生入学考试试卷.doc");
			long now = System.currentTimeMillis();
			System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒"); // 转化用时
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public static boolean getLicense() {
        boolean result = false;
        try {
            InputStream is = Test.class.getClassLoader().getResourceAsStream("license.xml"); // license.xml应放在..\WebRoot\WEB-INF\classes路径下
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
 
    public static void doc2pdf(String inPath, String outPath) {
        if (!getLicense()) { // 验证License 若不验证则转化出的pdf文档会有水印产生
            return;
        }
        try { 
            long old = System.currentTimeMillis();
            File file = new File(outPath); // 新建一个空白pdf文档
            FileOutputStream os = new FileOutputStream(file);
            Document doc = new Document(inPath); // Address是将要被转化的word文档
            doc.save(os, SaveFormat.PDF);// 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF,
                                         // EPUB, XPS, SWF 相互转换
            long now = System.currentTimeMillis();
            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒"); // 转化用时
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
}
