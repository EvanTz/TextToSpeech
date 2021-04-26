package input;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class WordReader implements DocumentReader{
	private String docPath;
	
	public WordReader(String docPath) {
		this.docPath = docPath;
		
	}
	
	public List<String> read(){
	
		List<String> content = new ArrayList<String>();
		
		FileInputStream fps;
		try {
			fps = new FileInputStream(docPath);
			XWPFDocument docu = new XWPFDocument(fps);
			List<XWPFParagraph> data = docu.getParagraphs();
			
			for(XWPFParagraph p : data) 
				content.add(p.getText());
			docu.close();
			
		} catch (FileNotFoundException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return content;
		
	}
}