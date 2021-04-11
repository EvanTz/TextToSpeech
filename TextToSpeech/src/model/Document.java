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
	private float pitch;
	private float rate;
	private float volume;
	

	public Document() {
		docReaderFactory = new DocumentReaderFactory();
		docWriterFactory = new DocumentWriterFactory();
		audioManager = new TTSFacade();
	}
	
	public void setAudioManager(TTSFacade tts) {
		this.audioManager = tts;
	}
	
	public void setDocReaderFactory(DocumentReaderFactory drf) {
		this.docReaderFactory = drf;
	}
	public void setDocWriterFactory(DocumentWriterFactory dwf) {
		this.docWriterFactory = dwf;
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
	public void setOpenState(boolean state) {
		this.isOpened = state;
	}
	
	public List<String> getPathTypeEncoding(){
		List<String> docList = new ArrayList<>();
		docList.add(docPath);
		docList.add(docType);
		docList.add(docEncoding);
		return docList;
	}
	
	public void playContents() {
		audioManager.play(String.join(" ", contents));
	}
	
	public void playPartContents(List<String> partCon) {
		audioManager.play(String.join(" ", partCon));
	}
	
	public void stopPlayingContents() {
		audioManager.stopPlay();
	}
	
	public void setVolRatePitchDoc(double speechVolume, int speechRate, int speechPitch) {
		this.volume = (float)speechVolume;
		this.rate = (float)speechRate;
		this.pitch = (float)speechPitch;
		
		audioManager.setPitch(pitch);
		audioManager.setVolume(volume);
		audioManager.setRate(rate);
		
	}
	

	

	public void save(String docPath, String docType, String docEncoding) {

		docWriterFactory.setContents(contents);
		documentWriter = docWriterFactory.createWriter(docPath, docType, docEncoding);
		documentWriter.write();

		
	}
	
	
}
