package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import commands.*;

public class Text2SpeechEditorView implements ActionListener{
	private String encoding = "Atbash";
	private OpenDocument openDoc;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox<String> cb = (JComboBox<String>)e.getSource();
        encoding = (String)cb.getSelectedItem();
        if(encoding != null)openDoc.setEncoding(encoding);
        else openDoc.setEncoding("Atbash");
	}
	
	public Text2SpeechEditorView() {
		CommandsFactory comFactory = new CommandsFactory();
		
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		
		JButton button = new JButton("Open file");
		JButton saveButton = new JButton("Save File");
		
		String[] encodings = {"Atbash", "Rot13"};
		encoding = encodings[0];
		JComboBox<String> encodingsList = new JComboBox<String>(encodings);
		
		openDoc = (OpenDocument) comFactory.createCommand("OpenDocument");
		button.addActionListener(openDoc);
		
		openDoc.setEncoding(encoding);
		
		SaveDocument saveDoc = (SaveDocument) comFactory.createCommand("SaveDocument");
		saveButton.addActionListener(saveDoc);
		
		encodingsList.addActionListener(this);
		
		panel.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
		//panel.setLayout(new GridLayout(3,3));
		panel.add(button);
		panel.add(saveButton);
		panel.add(encodingsList);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Text2Speech");
		frame.pack();
		frame.setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
		
		new Text2SpeechEditorView();

	}

}
