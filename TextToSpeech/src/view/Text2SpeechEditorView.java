package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import commands.*;

public class Text2SpeechEditorView {
	
	public Text2SpeechEditorView() {
		CommandsFactory comFactory = new CommandsFactory();
		
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		
		JButton button = new JButton("Open file");
		JButton saveButton = new JButton("Save File");
		
		OpenDocument openDoc = (OpenDocument) comFactory.createCommand("OpenDocument");
		button.addActionListener(openDoc);
		
		SaveDocument saveDoc = (SaveDocument) comFactory.createCommand("SaveDocument");
		saveButton.addActionListener(saveDoc);
		
		
		panel.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
		//panel.setLayout(new GridLayout(3,3));
		panel.add(button);
		panel.add(saveButton);
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
