package org.ssg.utils;

import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.IOException;

import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;

import com.sun.media.jai.codec.FileSeekableStream;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.JPEGEncodeParam;
import com.sun.media.jai.codec.TIFFDecodeParam;
import com.sun.media.jai.codec.TIFFEncodeParam;

public class TiffUtilTools {

	
	public static void main(String[] args) throws IOException {
		doitJAI("C:\\aaa\\河南师范大学632基础日语（A卷）2013年考研真题.tif","C:\\bbb\\");
	}
	
	public static void doitJAI(String file,String fileDirectory) throws IOException {
		System.out.println(file);
		System.out.println(fileDirectory);
		
		FileSeekableStream ss = new FileSeekableStream(file);
		TIFFDecodeParam param0 = null;
		TIFFEncodeParam param = new TIFFEncodeParam();
		JPEGEncodeParam param1 = new JPEGEncodeParam();
		ImageDecoder dec = ImageCodec.createImageDecoder("tiff", ss, param0);
		int count = dec.getNumPages();
		param.setCompression(TIFFEncodeParam.COMPRESSION_GROUP4);
		param.setLittleEndian(false); // Intel
		System.out.println("This TIF has " + count + " image(s)");
		for (int i = 0; i < count; i++) {
			RenderedImage page = dec.decodeAsRenderedImage(i);
			File f = new File(fileDirectory + i + ".jpg");
			System.out.println("Saving " + f.getCanonicalPath());
			ParameterBlock pb = new ParameterBlock();
			pb.addSource(page);
			pb.add(f.toString());
			pb.add("JPEG");
			pb.add(param1);
			//JAI.create("filestore",pb);
			RenderedOp r = JAI.create("filestore",pb);
			r.dispose();
			//RenderedOp op = JAI.create("filestore", page, "./zhaoming_" + i + ".jpg", "JPEG", param1);
		}
	}	
	
}
