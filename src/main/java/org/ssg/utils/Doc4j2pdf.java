package org.ssg.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.docx4j.Docx4J;
import org.docx4j.convert.out.FOSettings;
import org.docx4j.fonts.IdentityPlusMapper;
import org.docx4j.fonts.Mapper;
import org.docx4j.fonts.PhysicalFonts;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.org.apache.poi.util.IOUtils;

public class Doc4j2pdf {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			convertDocxToPDF("C:/aaa/2018年杭州师范大学817专业日语考研真题研究生入学考试试卷.doc","C:/aaa/2018年杭州师范大学817专业日语考研真题研究生入学考试试卷.pdf");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * docx文档转换为PDF
	 *
	 * @param  docx文档
	 * @param pdfPath PDF文档存储路径
	 * @throws Exception 可能为Docx4JException, FileNotFoundException, IOException等
	 */
	public static void convertDocxToPDF(String docxPath, String pdfPath) throws Exception {
	   OutputStream os = null;
	   try {
	      InputStream is = new FileInputStream(new File(docxPath));
	      WordprocessingMLPackage mlPackage = WordprocessingMLPackage.load(is);
	      Mapper fontMapper = new IdentityPlusMapper();
	      fontMapper.put("隶书", PhysicalFonts.get("LiSu"));
	      fontMapper.put("宋体",PhysicalFonts.get("SimSun"));
	      fontMapper.put("微软雅黑",PhysicalFonts.get("Microsoft Yahei"));
	      fontMapper.put("黑体",PhysicalFonts.get("SimHei"));
	      fontMapper.put("楷体",PhysicalFonts.get("KaiTi"));
	      fontMapper.put("新宋体",PhysicalFonts.get("NSimSun"));
	      fontMapper.put("华文行楷", PhysicalFonts.get("STXingkai"));
	      fontMapper.put("华文仿宋", PhysicalFonts.get("STFangsong"));
	      fontMapper.put("宋体扩展",PhysicalFonts.get("simsun-extB"));
	      fontMapper.put("仿宋",PhysicalFonts.get("FangSong"));
	      fontMapper.put("仿宋_GB2312",PhysicalFonts.get("FangSong_GB2312"));
	      fontMapper.put("幼圆",PhysicalFonts.get("YouYuan"));
	      fontMapper.put("华文宋体",PhysicalFonts.get("STSong"));
	      fontMapper.put("华文中宋",PhysicalFonts.get("STZhongsong"));

	      mlPackage.setFontMapper(fontMapper);

	      os = new java.io.FileOutputStream(pdfPath);

	      FOSettings foSettings = Docx4J.createFOSettings();
	      foSettings.setWmlPackage(mlPackage);
	      Docx4J.toFO(foSettings, os, Docx4J.FLAG_EXPORT_PREFER_XSL);

	   }catch(Exception ex){
	      ex.printStackTrace();
	   }finally {
	      IOUtils.closeQuietly(os);
	   }
	}

}
