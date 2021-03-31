package output;

import java.util.List;

public class DocumentWriterFactory {
	private WriterAtbashDecorator writerAtbashDecorator;
	private WriterRot13Decorator writerRot13Decorator;
	private DocumentWriter docWriter;
	private List<String> contents;
	private List<String> encodedContents;


	public DocumentWriterFactory() {

	}

	public DocumentWriter createWriter(String docPath, String docType, String docEncoding) {
	
		if(docEncoding == "Atbash") {
			writerAtbashDecorator = new WriterAtbashDecorator(contents);
			writerAtbashDecorator.write();
			encodedContents = writerAtbashDecorator.getOutput();
		}
		else if(docEncoding == "Rot13") {
			writerRot13Decorator = new WriterRot13Decorator(contents);
			writerRot13Decorator.write();
			encodedContents = writerRot13Decorator.getOutput();
		}
		else if(docEncoding == "None") {  // Decorator not required
			encodedContents = contents;
		}
		
				
		if (docType == "docx") {
			docWriter = new WordWriter(docPath, encodedContents);
			return docWriter;
		}
		else if(docType == "xlsx") {
			docWriter = new ExcelWriter(docPath, encodedContents);
			return docWriter;
		}
		return null;
	}
	
	public void setContents(List<String> contents) {
		this.contents = contents;
	}
}
