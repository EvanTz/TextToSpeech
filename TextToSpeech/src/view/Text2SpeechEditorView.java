package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import commands.*;
import model.Document;

public class Text2SpeechEditorView implements ActionListener{
	private String encoding = "Atbash";
	private OpenDocument openDoc;
	private SaveDocument saveDoc;
	private JTextArea textArea;
	private JTable table;
	private DefaultTableModel model;
	private JComboBox<String> encodingsList;
	private JButton openButton;
	private JButton saveButton;
	private JScrollPane scroll;
	private JLabel encodingLabel;
	private JButton settingsButton;
	private JButton playContentsButton;
	private JButton stopPlayingButton;
	private double speechVolume= 0.9;
	private int speechRate = 150;
	private int speechPitch = 150;
	private DocumentToSpeech docToSp;
	private JButton activateRecording;
	private JButton playRecording;
	private JButton stopRecording;
	
	private ReplayManager rm;
	private StartRecording startRec;
	private EndRecording endRec;
	private ReplayCommand replayRec;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == encodingsList) {
			@SuppressWarnings("unchecked")
			JComboBox<String> cb = (JComboBox<String>)e.getSource();
	        encoding = (String)cb.getSelectedItem();
	        if(encoding != null) {
	        	openDoc.setEncoding(encoding);
	        	saveDoc.setEncoding(encoding);
	        }
	        else {
	        	openDoc.setEncoding("None");
	        	saveDoc.setEncoding("None");
	        }
		}
		else if(e.getSource() == settingsButton) {
			JPanel settingsPanel = new JPanel(new GridLayout(3, 2));
			
			JSlider volume = new JSlider(0,100,(int)(speechVolume*100));
			JLabel vol = new JLabel("Volume (0-100):");
			
			JLabel rt = new JLabel("Rate (words per minute):");
			JSlider rate = new JSlider(20,250,speechRate);
			
			JLabel pth = new JLabel("Pitch (Hz):");
			JSlider pitch = new JSlider(50,300,speechPitch);
			
			volume.setMajorTickSpacing(20);
			volume.setPaintTicks(true);
			volume.setPaintLabels(true);
			
			rate.setMajorTickSpacing(230/2);
			rate.setPaintTicks(true);
			rate.setPaintLabels(true);
			
			pitch.setMajorTickSpacing(250/2);
			pitch.setPaintTicks(true);
			pitch.setPaintLabels(true);
			
			settingsPanel.add(vol);
			settingsPanel.add(volume);
			settingsPanel.add(rt);
			settingsPanel.add(rate);
			settingsPanel.add(pth);
			settingsPanel.add(pitch);
			
			int result = JOptionPane.showConfirmDialog(null , settingsPanel, "Settings", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			 if (result == JOptionPane.OK_OPTION) {
	            speechVolume = (double)volume.getValue()/100;
	            speechRate = rate.getValue();
	            speechPitch = pitch.getValue();
	            docToSp.setVolRatePitch(speechVolume,speechRate,speechPitch);
	            rm.setVolRatePitch(speechVolume, speechRate, speechPitch);
			 }
			
		}
	}
	
	
	public Text2SpeechEditorView() {
		String[] encodings = {"None", "Atbash", "Rot13"};
		encoding = encodings[0];
		CommandsFactory comFactory = new CommandsFactory();
		
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JPanel textPanel = new JPanel();	

		openButton = new JButton("Open file");
		openButton.setFocusable(false);
		openButton.setBackground(new Color(162, 235, 245));

		saveButton = new JButton("Save File");
		saveButton.setFocusable(false);
		saveButton.setBackground(new Color(162, 235, 245));
		encodingLabel = new JLabel("Encoding:");
		encodingsList = new JComboBox<String>(encodings);

		encodingsList.setFocusable(false);
		encodingsList.setBackground(new Color(162, 235, 245));
		
		playContentsButton = new JButton("Play Audio");
		playContentsButton.setFocusable(false);
		playContentsButton.setPreferredSize(new Dimension(100,26));
		playContentsButton.setBackground(new Color(102, 219, 81));
		
		stopPlayingButton = new JButton("Stop Audio");
		stopPlayingButton.setFocusable(false);
		stopPlayingButton.setPreferredSize(new Dimension(100,26));
		stopPlayingButton.setBackground(new Color(232, 102, 93));
		
		settingsButton = new JButton("TTS settings");
		settingsButton.setFocusable(false);
		settingsButton.setBackground(Color.GRAY.brighter());

		activateRecording = new JButton("Record Actions");
		activateRecording.setFocusable(false);
		activateRecording.setPreferredSize(new Dimension(150,26));
		activateRecording.setBackground(Color.ORANGE);
		
		playRecording = new JButton("Replay Recorded Actions");
		playRecording.setFocusable(false);
		playRecording.setPreferredSize(new Dimension(180,26));
		playRecording.setBackground(Color.ORANGE);
		
		stopRecording = new JButton("Clear Recorded Actions");
		stopRecording.setFocusable(false);
		stopRecording.setBackground(Color.ORANGE);
		
		rm = new ReplayManager();
		rm.setVolRatePitch(speechVolume, speechRate, speechPitch);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		
		model = new DefaultTableModel();
		model.setColumnCount(2);
		model.setRowCount(2);
		table = new JTable(model);
		
		scroll = new JScrollPane (textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVisible(false);
		
		openDoc = (OpenDocument) comFactory.createCommand("OpenDocument");
		openDoc.setTextArea(textArea);
		openDoc.setTableModel(model);
		openDoc.setTable(table);
		openDoc.setScroll(scroll);
		openDoc.setFrame(frame);
		openDoc.setEncoding(encoding);
		
		openButton.addActionListener(openDoc);
		
		saveDoc = (SaveDocument) comFactory.createCommand("SaveDocument");
		saveDoc.setDocument(openDoc.getDocument());
		saveDoc.setTextArea(textArea);
		saveDoc.setTableModel(model);
		saveDoc.setTable(table);
		saveDoc.setEncoding(encoding);
		saveDoc.setScroll(scroll);
		
		saveButton.addActionListener(saveDoc);
		
		docToSp = (DocumentToSpeech) comFactory.createCommand("DocumentToSpeech");
		docToSp.setDocument(openDoc.getDocument());
		docToSp.setPlayButton(playContentsButton);
		docToSp.setStopButton(stopPlayingButton);
		docToSp.setTextArea(textArea);
		docToSp.setTableModel(model);
		docToSp.setTable(table);
		docToSp.setVolRatePitch(speechVolume,speechRate,speechPitch);
		docToSp.setReplayManager(rm);
		
		playContentsButton.addActionListener(docToSp);
		stopPlayingButton.addActionListener(docToSp);
		
		startRec = (StartRecording) comFactory.createCommand("StartRecording");
		startRec.setReplayManager(rm);
		startRec.setActivateRecButton(activateRecording);
		
		activateRecording.addActionListener(startRec);
		
		endRec = (EndRecording) comFactory.createCommand("EndRecording");
		endRec.setReplayManager(rm);
		endRec.setActivateRecButton(activateRecording);
		
		stopRecording.addActionListener(endRec);
		
		replayRec = (ReplayCommand) comFactory.createCommand("ReplayCommand");
		replayRec.setReplayManager(rm);
		replayRec.setPlayRecButton(playRecording);
		replayRec.setStopAudioButton(stopPlayingButton);
		
		playRecording.addActionListener(replayRec);
		stopPlayingButton.addActionListener(replayRec);
		
		rm.setDoc(openDoc.getDocument());
		
		encodingsList.addActionListener(this);

		settingsButton.addActionListener(this);

		textPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		textPanel.setLayout(new GridLayout(1,0));
		panel.add(openButton);
		panel.add(saveButton);
		panel.add(encodingLabel);
		panel.add(encodingsList);
		panel.add(settingsButton);
		panel.add(playContentsButton);
		panel.add(stopPlayingButton);
		panel.add(playRecording);
		panel.add(activateRecording);
		panel.add(stopRecording);
		textPanel.add(scroll);
		frame.add(panel, BorderLayout.PAGE_START);
		frame.add(textPanel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Text2Speech");
		frame.setPreferredSize(new Dimension(1200,600));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
		
		new Text2SpeechEditorView();

	}

}
