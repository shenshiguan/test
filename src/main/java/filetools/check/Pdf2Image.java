package filetools.check;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import filetools.log.LogOut;


public class Pdf2Image {
	static LogOut logout = new LogOut("D:/pdf2jpg_Exception_111.log");
	public static void main(String[] args) {
		Pdf2Image Pdf2Image = new Pdf2Image();
		Pdf2Image.pdf2Image("E:/已核对历年真题/山东大学/概率论1999/5a589b0f-5ca6-4b80-8ae8-3d0a40cba2c0.pdf");
	}

	/***
	 *               
	 * 
	 * @return
	 */
	public static  void pdf2Image(String PdfFilePath) {
		File file = new File(PdfFilePath);
		PDDocument pdDocument = null;
		StringBuffer imgFilePath = null;
		PDFRenderer renderer = null;
		File dstFile = null;
		String jpgPath = file.getAbsolutePath().trim().replaceAll(".pdf", ".jpg");
		try {
			pdDocument = PDDocument.load(file);
			renderer = new PDFRenderer(pdDocument);
			imgFilePath = new StringBuffer();
			imgFilePath.append(jpgPath);
			dstFile = new File(imgFilePath.toString());
			try {
				BufferedImage image = renderer.renderImageWithDPI(0, 100);
				ImageIO.write(image, "jpg", dstFile);
			} catch (Exception e) {
				System.out.println("=================");
			}
		} catch (IOException e) {
			e.printStackTrace();
			logout.fileChaseFW(PdfFilePath+"\r\n"+e.getMessage());
		}finally{
			try {
				if(renderer!=null){
					renderer = null;
				}
				if(dstFile!=null){
					dstFile = null;
				}
				if(pdDocument!=null){
					pdDocument.close();
				}
				file = null;
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}


}
