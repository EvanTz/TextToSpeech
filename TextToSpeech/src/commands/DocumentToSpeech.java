package commands;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import model.Document;

public class DocumentToSpeech implements ActionListener{

	private Document doc;
	private JButton playButton;
	private JTextArea textArea;
	private DefaultTableModel model;
	private JTable table;
	private ReplayManager rm;
	private int pitch;
	private int rate;
	private double volume;
	private boolean played = false;
	
	public DocumentToSpeech() {
		rm = new ReplayManager();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// play and stop audio (change parameters in document), DONE
		// all contents or selected contents, DONE 
		// TODO if replay record is true record the move(add it to a list like rm.addplayContents(List<string> content).
		if(event.getSource() == playButton && playButton.getText()=="Play Audio") {
			// if user has not selected a part of the text/any cells
			if(doc.getPathTypeEncoding().get(1) == "docx" && textArea.getSelectedText()==null) {
				playAllContents();
			}
			else if (doc.getPathTypeEncoding().get(1) == "xlsx" && table.getSelectedRows().length== 0) {
				playAllContents();
			}
			// else play selected text/cells
			else if(doc.getPathTypeEncoding().get(1) == "docx" && textArea.getSelectedText()!=null) {
				playSelectedContents();
			}
			else if (doc.getPathTypeEncoding().get(1) == "xlsx" && table.getSelectedRows().length != 0) {
				playSelectedContents();
			}
			
			if (!doc.getOpenState()) {
				JOptionPane.showMessageDialog(null, "No file opened to be played to audio.","Warning",JOptionPane.PLAIN_MESSAGE);
			}
			else{
				playButton.setText("Stop Audio");
				playButton.setBackground(new Color(232, 102, 93));
			}
			
		}
		else if(event.getSource() == playButton && playButton.getText()=="Stop Audio") {
			doc.stopPlayingContents();
			playButton.setText("Play Audio");
			playButton.setBackground(new Color(102, 219, 81));
		}
	}
	
	public void playAllContents() { // method is public just for testing
		if(doc.getOpenState()) {
			if(doc.getPathTypeEncoding().get(1) == "docx") {
				List<String> play = new ArrayList<>();
				play.add(textArea.getText());
				if(rm.isActiveRecording()) {
					// TODO here save the play value to a list in replay manager
				}
				doc.setContents(play);
				
				
				doc.setVolRatePitchDoc(volume, rate, pitch);
				doc.playContents();
			}
			else if (doc.getPathTypeEncoding().get(1) == "xlsx") {
				if(!table.isEditing()) {
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
					if(rm.isActiveRecording()) {
						// TODO here save the out value(s) to a list in replay manager
					}
					doc.setContents(out);
					doc.setVolRatePitchDoc(volume, rate, pitch);
					doc.playContents();
				}
				else {
					JOptionPane.showMessageDialog(null,
							"Cannot play file contents while cell is being edited. Press enter or select another cell to exit edit mode!",
							"Warning",JOptionPane.PLAIN_MESSAGE);
				}
			}
		}
	}

	public void playSelectedContents() { // method is public just for testing
		if(doc.getOpenState()) {
			if(doc.getPathTypeEncoding().get(1) == "docx") {
				List<String> play = new ArrayList<>();
				play.add(textArea.getSelectedText());
				if(rm.isActiveRecording()) {
					// TODO here save the play value to a list in replay manager
				}
				
				doc.setVolRatePitchDoc(volume, rate, pitch);
				doc.playPartContents(play);
				
			}
			else if (doc.getPathTypeEncoding().get(1) == "xlsx") {
				if(!table.isEditing()) {
					//String line = "";
					List<String> out = new ArrayList<>();
					int[] selectedRows = table.getSelectedRows();
					int[] selectedColumns = table.getSelectedColumns();
					for(int i :selectedRows) {
						for(int j:selectedColumns) {
							out.add(String.valueOf(table.getValueAt(i, j)));
						}
					}
					if(rm.isActiveRecording()) {
						// TODO here save the out value(s) to a list in replay manager
					}
					
					doc.setVolRatePitchDoc(volume, rate, pitch);
					doc.playPartContents(out);

				}
				else {
					JOptionPane.showMessageDialog(null,
							"Cannot play file contents while cell is being edited. Press enter or select another cell to exit edit mode!",
							"Warning",JOptionPane.PLAIN_MESSAGE);
				}
			}
		}
	}
	
	public void setDocument(Document document) {
		this.doc = document;
	}
	
	public void setPlayButton(JButton playButton) {
		this.playButton = playButton;
	}
	
	public void ReplayManager(ReplayManager rm) {
		this.rm = rm;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
		
	}

	public void setTableModel(DefaultTableModel model) {
		this.model = model;
		
	}

	public void setTable(JTable table) {
		this.table = table;
		
	}

	public void setVolRatePitch(double speechVolume, int speechRate, int speechPitch) {
		this.volume = speechVolume;
		this.rate = speechRate;
		this.pitch = speechPitch;
	}
	
}
