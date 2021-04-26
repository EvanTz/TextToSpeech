package output;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class WordWriter implements DocumentWriter {
	
	private String docPath;
	private List<String> content;

	public WordWriter(String docPath, List<String> content)  {
		//  Auto-generated constructor stub
		this.docPath = docPath;
		this.content = content;

	}
	@Override
	public void write() {
		//File file = new File(docPath);
		//file.delete();
		try(XWPFDocument doc = new XWPFDocument()) {
			XWPFParagraph p = doc.createParagraph();
			p.setAlignment(ParagraphAlignment.LEFT);  // changed from CENTER to LEFT
			
			XWPFRun r = p.createRun();
			//r.setBold(true);
            //r.setItalic(true);
            r.setFontSize(22);
            r.setFontFamily("New Roman");
            r.setText(content.get(0));// to string pou tha pairnoume apo to gui
			
            try(FileOutputStream out = new FileOutputStream(docPath)){
            	doc.write(out);
            	doc.close();
            }
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}