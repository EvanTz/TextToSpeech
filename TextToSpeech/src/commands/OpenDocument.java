package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Document;

public class OpenDocument implements ActionListener{
	private Document doc;
	
	public OpenDocument() {
		//TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JFileChooser chooseFile = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel or Word Document", "docx", "xlsx");
		chooseFile.setFileFilter(filter);
		int returnVal = chooseFile.showOpenDialog(null);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String inputDoc = chooseFile.getSelectedFile().getAbsolutePath();
			
			System.out.println("Document selected: " + inputDoc);
			
			if(!inputDoc.endsWith("docx") && !inputDoc.endsWith("xlsx")) {
				JOptionPane.showMessageDialog(chooseFile, "Wrong type of document selected.","Warning",JOptionPane.PLAIN_MESSAGE);
			}
		}
		else JOptionPane.showMessageDialog(chooseFile, "No document selected.","Warning",JOptionPane.PLAIN_MESSAGE);
		
	}
	
	public void setDocument(Document document) {
		this.doc = document;
	}
	
	public void setReplayManager(ReplayManager rm) {
		
	}
	
	
	
	
	
	

}
