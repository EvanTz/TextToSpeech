package commands;

//"D:\\Users\\Vaggelis\\Documents\\GitHub\\soft eng textToSpeech\\TextToSpeech\\Resources\\InputSamples"

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.FileInputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import model.Document;

public class OpenDocument implements ActionListener{
	private Document doc;
	private String encoding;
	private JTextArea textArea = new JTextArea();
	private JTable table = new JTable();
	private DefaultTableModel model;
	private JScrollPane scroll = new JScrollPane();
	
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
			
			//System.out.println("Document selected: " + inputDoc);
			
			
			if(!inputDoc.endsWith("docx") && !inputDoc.endsWith("xlsx")) {
				JOptionPane.showMessageDialog(chooseFile, "Wrong type of document selected.","Warning",JOptionPane.PLAIN_MESSAGE);
			}
			
			if(inputDoc.endsWith("docx")) {
				doc.open(inputDoc, "docx", encoding); 
				textArea.setText(getDocument().getContents().toString());
				scroll.setVisible(true);
				scroll.setViewportView(textArea);
			}
			else if(inputDoc.endsWith("xlsx")) {
				doc.open(inputDoc, "xlsx", encoding);
				
				
				
				// TODO this should be implemented in a private method ideally
				table.setCellSelectionEnabled(true);
				model.setColumnCount(getDocument().getContents().size());
				model.setRowCount(1);
				
				for (String i : getDocument().getContents()){
					model.setValueAt(i, 0, getDocument().getContents().indexOf(i)); }
				 
				
				
				scroll.setVisible(true);
				scroll.setViewportView(table);
			}
			
		}
		else JOptionPane.showMessageDialog(chooseFile, "No document selected.","Warning",JOptionPane.PLAIN_MESSAGE);
		
	}
	
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
	public void setTable(JTable table) {
		this.table = table;
		
	}
	public void setTableModel(DefaultTableModel model) {
		this.model = model;
	}
	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
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
