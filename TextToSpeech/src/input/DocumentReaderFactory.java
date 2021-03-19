package input;

public class DocumentReaderFactory {
	private ReaderAtbashDecorator readerAtbashDecorator;
	private ReaderRot13Decorator readerRot13Decorator;
	private DocumentReader docReader;

	public DocumentReaderFactory() {
		
	}
	
	public DocumentReader createReader(String docPath, String docType, String docEncoding) {
		
		// TODO should word reader and excel reader take as input the path of the document ????????? :  WordReader(docPath) ExcelReader(docPath)
		
		if (docType == "docx") {
			docReader = new WordReader();
		}
		else if(docType == "xlsx") {
			docReader = new ExcelReader();
		}
		
		if(docEncoding == "Atbash") {
			readerAtbashDecorator = new ReaderAtbashDecorator(docReader);
			return readerAtbashDecorator;
		}
		else if(docEncoding == "Rot13") {
			readerRot13Decorator = new ReaderRot13Decorator(docReader);
			return readerRot13Decorator;
		}
		return null;
	}
	
}
