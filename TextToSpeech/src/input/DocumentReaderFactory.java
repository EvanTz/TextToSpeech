package input;

public class DocumentReaderFactory {
	private ReaderAtbashDecorator readerAtbashDecorator;
	private ReaderRot13Decorator readerRot13Decorator;
	private DocumentReader docReader;

	public DocumentReaderFactory() {
		
	}
	
	public DocumentReader createReader(String docPath, String docType, String docEncoding) {
		
		if (docType == "docx") {
			docReader = new WordReader(docPath);
		}
		else if(docType == "xlsx") {
			docReader = new ExcelReader(docPath);
		}
		
		if(docEncoding == "Atbash") {
			readerAtbashDecorator = new ReaderAtbashDecorator(docReader);
			return readerAtbashDecorator;
		}
		else if(docEncoding == "Rot13") {
			readerRot13Decorator = new ReaderRot13Decorator(docReader);
			return readerRot13Decorator;
		}
		else if(docEncoding == "None") {  // Decorator not required
			return docReader;
		}
		return null;
	}
	
}
