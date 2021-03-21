package model;

import java.util.ArrayList;
import java.util.Arrays;

import input.DocumentReader;
import input.DocumentReaderFactory;
import output.DocumentWriter;
import output.DocumentWriterFactory;

public class Document {
	
	private ArrayList<String> contents;
	private TTSFacade audioManager;
	private DocumentReader documentReader;
	private DocumentReaderFactory docReaderFactory;
	private DocumentWriter documentWriter;
	private DocumentWriterFactory docWriterFactory; 
	

	public Document() {
		docReaderFactory = new DocumentReaderFactory();
	}
	
	public void setAudioManager(TTSFacade tts) {
		
	}
	public void setDocReaderFactory(DocumentReaderFactory drf) {
		this.docReaderFactory = drf;
	}
	public void setDocWriterFactory(DocumentWriterFactory dwf) {
		
	}
	
	public void open(String docPath, String docType, String docEncoding) {
		//System.out.println(docPath);
		//System.out.println(docType);
		//System.out.println(docEncoding);
		
		documentReader = docReaderFactory.createReader(docPath, docType, docEncoding);
		contents = (ArrayList<String>) documentReader.read();
		
		// TODO test
		//contents = new ArrayList<String>();
		//contents.addAll(Arrays.asList("this","is","a","test",docPath,docType,docEncoding));
	}
	
	public ArrayList<String> getContents(){
		return this.contents;
	}
	
	public void playContents() {
		
	}
	
	public void playLine(int line) {
		
	}
	
	public void save(String str1, String str2, String str3) {
		
	}
	
	
}
