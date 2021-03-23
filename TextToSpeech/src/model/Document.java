package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import input.DocumentReader;
import input.DocumentReaderFactory;
import input.ExcelReader;
import output.DocumentWriter;
import output.DocumentWriterFactory;

public class Document {
	
	private ArrayList<String> contents;
	private TTSFacade audioManager;
	private DocumentReader documentReader;
	private DocumentReaderFactory docReaderFactory;
	private DocumentWriter documentWriter;
	private DocumentWriterFactory docWriterFactory;
	private boolean isOpened = false;
	private String docPath = "";	
	private String docType = "";	
	private String docEncoding = "";
	

	public Document() {
		docReaderFactory = new DocumentReaderFactory();
		docWriterFactory = new DocumentWriterFactory();
	}
	
	public void setAudioManager(TTSFacade tts) {
		
	}
	public void setDocReaderFactory(DocumentReaderFactory drf) {
		this.docReaderFactory = drf;
	}
	public void setDocWriterFactory(DocumentWriterFactory dwf) {
		
	}
	
	public void open(String docPath, String docType, String docEncoding) {
		
		documentReader = docReaderFactory.createReader(docPath, docType, docEncoding);
		contents = (ArrayList<String>) documentReader.read();
		isOpened  = true;
		this.docPath = docPath;
		this.docType = docType;
		this.docEncoding = docEncoding;
		
	}
	
	public ArrayList<String> getContents(){
		return this.contents;
	}
	
	public void setContents(List<String> inCon) {
		this.contents = (ArrayList<String>) inCon;
	}
	
	public boolean getOpenState() {
		return isOpened;
	}
	public List<String> getPathTypeEncoding(){
		List<String> docList = new ArrayList<>();
		docList.add(docPath);
		docList.add(docType);
		docList.add(docEncoding);
		return docList;
	}
	
	public void playContents() {
		
	}
	
	public void playLine(int line) {
		
	}
	
	public void save(String docPath, String docType, String docEncoding) {

		docWriterFactory.setContents(contents);
		documentWriter = docWriterFactory.createWriter(docPath, docType, docEncoding);
		documentWriter.write();
		
	}
	
	
}
