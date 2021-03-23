package commands;

//"D:\\Users\\Vaggelis\\Documents\\GitHub\\soft eng textToSpeech\\TextToSpeech\\Resources\\InputSamples"

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import model.Document;

public class SaveDocument implements ActionListener{
	private Document doc;
	private String encoding;
	private JTable table;
	private DefaultTableModel model;
	private JTextArea textArea;
	private JScrollPane scroll;
	
	public SaveDocument() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(doc.getOpenState()) {
			// CHANGE THE PATH TO YOUR OWN
			JFileChooser chooseSavePath = new JFileChooser("D:\\Users\\Vaggelis\\Documents\\GitHub\\soft eng textToSpeech\\TextToSpeech\\Resources\\InputSamples");
			
			if (doc.getPathTypeEncoding().get(1) == "docx") {
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Word Document", "docx");
				chooseSavePath.setFileFilter(filter);
				
				int returnVal = chooseSavePath.showSaveDialog(null);
				
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					
					String inputDoc = chooseSavePath.getSelectedFile().getAbsolutePath();
					//System.out.println("File to be saved selected: " + inputDoc);
					
					if (!inputDoc.endsWith("docx")) {
						JOptionPane.showMessageDialog(chooseSavePath, "Save File is not of the same type as opened file.","Warning",JOptionPane.PLAIN_MESSAGE);
					}
					else {

						List<String> out = new ArrayList<>();
						out.add(textArea.getText());
						doc.setContents(out);
						doc.save(inputDoc, "docx", encoding);
						
					}
					
				}
				else JOptionPane.showMessageDialog(chooseSavePath, "Save cancelled.","Warning",JOptionPane.PLAIN_MESSAGE);
			}
			else if (doc.getPathTypeEncoding().get(1) == "xlsx") {
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Document", "xlsx");
				chooseSavePath.setFileFilter(filter);
				
				int returnVal = chooseSavePath.showSaveDialog(null);
				
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					
					String inputDoc = chooseSavePath.getSelectedFile().getAbsolutePath();
					//System.out.println("File to be saved selected: " + inputDoc);
					
					if (!inputDoc.endsWith("xlsx")) {
						JOptionPane.showMessageDialog(chooseSavePath, "Save File is not of the same type as opened file.","Warning",JOptionPane.PLAIN_MESSAGE);
					}
					else {
					
						String line = "";
						List<String> out = new ArrayList<>();
						for (int i = 0; i < model.getRowCount(); i++) {
							line = "";
							for (int j = 0; j < model.getColumnCount(); j++) {
								line = line + model.getValueAt(i, j) + ",";
							}
							line = line.substring(0,line.length()-1);
							out.add(line);
						}
						doc.setContents(out);
						doc.save(inputDoc, "xlsx", encoding);
					}
				}
				else JOptionPane.showMessageDialog(chooseSavePath, "Save cancelled.","Warning",JOptionPane.PLAIN_MESSAGE);
			}
			else JOptionPane.showMessageDialog(chooseSavePath, "Save cancelled.","Warning",JOptionPane.PLAIN_MESSAGE);
		}
		else JOptionPane.showMessageDialog(scroll, "No file opened to be saved.","Warning",JOptionPane.PLAIN_MESSAGE);
		
	}
	
	public void setDocument(Document document) {
		this.doc = document;
	}
	
	public void setReplayManager(ReplayManager rm) {
		
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
		
	}

	public void setTable(JTable table) {
		this.table = table;
		
	}

	public void setTableModel(DefaultTableModel model) {
		this.model = model;
		
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
		
	}

	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
		
	}
	

	

}
