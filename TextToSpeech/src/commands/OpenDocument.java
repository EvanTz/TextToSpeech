package commands;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.FileInputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


import model.Document;

public class OpenDocument implements ActionListener{
	private Document doc;
	private String encoding;
	
	public OpenDocument() {
		this.doc = new Document();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// CHANGE THE PATH TO YOUR OWN
		JFileChooser chooseFile = new JFileChooser("C:\\Users\\dimsi\\git\\TextToSpeech\\TextToSpeech\\Resources");
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel or Word Document", "docx", "xlsx");
		chooseFile.setFileFilter(filter);
		
		
		int returnVal = chooseFile.showOpenDialog(null);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String inputDoc = chooseFile.getSelectedFile().getAbsolutePath();
			
			System.out.println("Document selected: " + inputDoc);
			
			
			if(!inputDoc.endsWith("docx") && !inputDoc.endsWith("xlsx")) {
				JOptionPane.showMessageDialog(chooseFile, "Wrong type of document selected.","Warning",JOptionPane.PLAIN_MESSAGE);
			}
			
			if(inputDoc.endsWith("docx")) doc.open(inputDoc, "docx", encoding); 
			else if(inputDoc.endsWith("xlsx")) doc.open(inputDoc, "xlsx", encoding);
			
		}
		else JOptionPane.showMessageDialog(chooseFile, "No document selected.","Warning",JOptionPane.PLAIN_MESSAGE);
		
	}
	
	public void setDocument(Document document) {
		this.doc = document;
	}
	
	public Document getDocument() {
		return doc;
	}
	
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
	public String getEncoding() {
		return encoding;
	}
	
	public void setReplayManager(ReplayManager rm) {
		
	}
	
}
