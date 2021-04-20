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
	private JButton stopButton;
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
		// if replay record is true record the move(add it to a list like rm.addplayContents(List<string> content). DONE
		if(event.getSource() == playButton){
			if (!doc.getOpenState()) {
				JOptionPane.showMessageDialog(null, "No file opened to be played to audio.","Warning",JOptionPane.PLAIN_MESSAGE);
			}
			// if user has not selected a part of the text/any cells
			else if(doc.getPathTypeEncoding().get(1) == "docx" && textArea.getSelectedText()==null) {
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
			if(played) {
				playButton.setEnabled(!played);
				playButton.setText("Click Stop");
			}
		}
		else if(event.getSource() == stopButton && played) { 
			doc.stopPlayingContents();
			played = false;
			playButton.setEnabled(!played);
			playButton.setText("Play Audio");
		}
	}
	
	public void playAllContents() { // method is public just for testing
		if(doc.getOpenState() && !played) {
			if(doc.getPathTypeEncoding().get(1) == "docx") {
				List<String> play = new ArrayList<>();
				play.add(textArea.getText());
				if(rm.isActiveRecording()) {
					//save the play value to a list in replay manager
					rm.addContentsToRecList(play);
				}
				doc.setContents(play);
				
				
				doc.setVolRatePitchDoc(volume, rate, pitch);
				doc.playContents();
				played = true;
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
						//save the out value(s) to a list in replay manager
						rm.addContentsToRecList(out);
					}
					doc.setContents(out);
					doc.setVolRatePitchDoc(volume, rate, pitch);
					doc.playContents();
					played = true;
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
		if(doc.getOpenState() && !played) {
			if(doc.getPathTypeEncoding().get(1) == "docx") {
				List<String> play = new ArrayList<>();
				play.add(textArea.getSelectedText());
				if(rm.isActiveRecording()) {
					//save the play value to a list in replay manager
					rm.addContentsToRecList(play);
				}
				
				doc.setVolRatePitchDoc(volume, rate, pitch);
				doc.playPartContents(play);
				played = true;
				
			}
			else if (doc.getPathTypeEncoding().get(1) == "xlsx") {
				if(!table.isEditing()) {
					List<String> out = new ArrayList<>();
					int[] selectedRows = table.getSelectedRows();
					int[] selectedColumns = table.getSelectedColumns();
					for(int i :selectedRows) {
						for(int j:selectedColumns) {
							out.add(String.valueOf(table.getValueAt(i, j)));
						}
					}
					if(rm.isActiveRecording()) {
						//save the out value(s) to a list in replay manager
						rm.addContentsToRecList(out);
					}
					
					doc.setVolRatePitchDoc(volume, rate, pitch);
					doc.playPartContents(out);
					played = true;

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
	
	public void setStopButton(JButton stopButton) {
		this.stopButton = stopButton;
	}
	
	public void setReplayManager(ReplayManager rm) {
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
	
	public void setPlayed(boolean played) {
		this.played = played;
	}
}
