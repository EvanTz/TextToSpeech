package commands;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import model.Document;

public class OpenDocument implements ActionListener{
	private Document doc;
	private String encoding;
	private JTextArea textArea = new JTextArea();
	private JTable table = new JTable();
	private DefaultTableModel model;
	private JScrollPane scroll = new JScrollPane();
	private JFrame frame;
	
	public OpenDocument() {
		this.doc = new Document();
	}

	@Override
	public void actionPerformed(ActionEvent event) {


		JFileChooser chooseFile = new JFileChooser(System.getProperty("user.dir")+"\\Resources\\InputSamples");

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
				openDoc(inputDoc, "docx", encoding);
				
				textArea.setText(null);
				for (String s : doc.getContents()) {
					textArea.append(s);
				}
				
				scroll.setVisible(true);
				scroll.setViewportView(textArea);
				frame.setTitle("Text2Speech: " + chooseFile.getSelectedFile().getName());
			}
			else if(inputDoc.endsWith("xlsx")) {
				openDoc(inputDoc, "xlsx", encoding);
		
				table.setCellSelectionEnabled(true);
				
				setExcelTable();
				
				scroll.setVisible(true);
				scroll.setViewportView(table);
				frame.setTitle("Text2Speech: " + chooseFile.getSelectedFile().getName());
			}
			
		}
		else JOptionPane.showMessageDialog(chooseFile, "No document selected.","Warning",JOptionPane.PLAIN_MESSAGE);
		
	}
	
	public void openDoc(String inputDoc, String fileType, String encoding) {
		doc.open(inputDoc, fileType, encoding);
	}
	
	private void setExcelTable() {
		ArrayList<ArrayList<String>> cells = new ArrayList<>();
		for(String i : doc.getContents()){
			cells.add( new ArrayList<String>(Arrays.asList((i.split(",")))));
		}
		if (!cells.isEmpty()) {
			model.setColumnCount(cells.get(0).size());
		}
		else {
			model.setColumnCount(0);
		}
		
		model.setRowCount(cells.size());
		
		for(List<String> row : cells) {
			for (String col : row) {
				model.setValueAt(col, cells.indexOf(row), row.indexOf(col));
			}
		}
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
	public void setFrame(JFrame frame) {
		this.frame = frame;
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
