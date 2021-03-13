package model;

import java.util.ArrayList;

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
		// TODO Auto-generated constructor stub
	}
	
	public void setAudioManager(TTSFacade tts) {
		
	}
	public void setDocReaderFactory(DocumentReaderFactory drf) {
		
	}
	public void setDocWriterFactory(DocumentWriterFactory dwf) {
		
	}
	
	public void open(String str1, String str2, String str3) {
		
	}
	
	public void playContents() {
		
	}
	
	public void playLine(int line) {
		
	}
	
	public void save(String str1, String str2, String str3) {
		
	}
	
	
}
